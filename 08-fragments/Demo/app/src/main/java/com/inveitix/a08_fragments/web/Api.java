package com.inveitix.a08_fragments.web;

import android.util.Log;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static final String TAG = "Api";
    public static final String BASE_URL = "https://cat-fact.herokuapp.com";
    private final CatFactService catFactService;

    public Api() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        catFactService = retrofit.create(CatFactService.class);
    }

    public void getRandomFacts(String animalType, int amount, final DataListener listener) {
        catFactService.getFacts(animalType, String.valueOf(amount))
                .enqueue(new Callback<List<Fact>>() {
            @Override
            public void onResponse(Call<List<Fact>> call, Response<List<Fact>> response) {
                if(response.isSuccessful()) {
                    listener.onFactsReceived(response.body());
                } else {
                    Log.e(TAG, "getRandomFacts failed");
                }
            }

            @Override
            public void onFailure(Call<List<Fact>> call, Throwable t) {
                Log.e(TAG, "getRandomFacts", t);
            }
        });
    }

    public interface DataListener {
        void onFactsReceived(List<Fact> data);
    }
}
