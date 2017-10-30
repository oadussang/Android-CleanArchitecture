package com.globant.equattrocchio.data.service.api;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SplashbaseApi {

    @GET("api/v1/images/latest")
    Call<JsonObject> getImages();
}
