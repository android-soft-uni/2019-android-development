package com.inveitix.a08_fragments.web;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CatFactService {

    @GET("/facts/random")
    public Call<List<Fact>> getFacts(@Query("animal_type") String animalType, @Query("amount") String amount);
}
