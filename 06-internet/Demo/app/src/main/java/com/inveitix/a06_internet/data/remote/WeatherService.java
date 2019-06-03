package com.inveitix.a06_internet.data.remote;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("weather")
    Call<WeatherModel> getCurrentWeather(@Query("q") String city,
                                         @Query("appid") String apiKey,
                                         @Query("units") String units);

    @GET("forecast")
    Call<WeatherModel> getForecastWeather(@Query("q") String city,
                                         @Query("appid") String apiKey,
                                         @Query("units") String units);


    @POST("simple-post-request")
    Call<Void> sendWeatherData(@Body WeatherModel weatherModel);
}