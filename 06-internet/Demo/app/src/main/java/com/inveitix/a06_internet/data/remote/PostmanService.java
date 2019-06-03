package com.inveitix.a06_internet.data.remote;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostmanService {

    @POST("post")
    public Call<PostmanResponse> ping(@Body PostmanRequest model);
}
