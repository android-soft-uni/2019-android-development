package com.inveitix.a06_internet;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.inveitix.a06_internet.data.remote.PostmanRequest;
import com.inveitix.a06_internet.data.remote.PostmanResponse;
import com.inveitix.a06_internet.data.remote.PostmanService;
import com.inveitix.a06_internet.data.remote.WeatherModel;
import com.inveitix.a06_internet.data.remote.WeatherService;
import com.inveitix.a06_internet.databinding.ActivityMainBinding;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

//        setupWebView();
//        setupWeatherRetrofit();
        setupPostmanDemoRetrofit();
    }

    private void setupPostmanDemoRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit postmanRetrofit = new Retrofit.Builder()
                .baseUrl("https://postman-echo.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        PostmanService postmanService = postmanRetrofit.create(PostmanService.class);

        postmanService.ping(new PostmanRequest()).enqueue(new Callback<PostmanResponse>() {
            @Override
            public void onResponse(Call<PostmanResponse> call, Response<PostmanResponse> response) {
                if(response != null && response.isSuccessful()) {
                    PostmanResponse postmanResponse = response.body();
                    Toast.makeText(MainActivity.this, "res: " + postmanResponse.data.hand, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostmanResponse> call, Throwable t) {

            }
        });
    }

    private void setupWeatherRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherModel> currentWeatherCall = service.getCurrentWeather(
                "Sofia", "37426f016190340c55b693d9a76e5015", "metric");
        currentWeatherCall.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if(response != null && response.isSuccessful()) {
                    WeatherModel model = response.body();
                    binding.txtWeatherData.setText(model.toString());
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        service.getForecastWeather("Sofia", "37426f016190340c55b693d9a76e5015", "metric");

        WeatherModel model = new WeatherModel();
        service.sendWeatherData(model).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    private void setupWebView() {
        binding.webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                loadWebpage(url);
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideProgress();
            }
        });
        loadWebpage("https://www.google.com");
    }

    private void hideProgress() {
        binding.progress.setVisibility(View.GONE);
    }

    private void loadWebpage(String url) {
        binding.progress.setVisibility(View.VISIBLE);
        binding.webView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if(binding.webView.canGoBack()) {
            binding.webView.goBack();
        } else {
            super.onBackPressed();
        }
    }


}
