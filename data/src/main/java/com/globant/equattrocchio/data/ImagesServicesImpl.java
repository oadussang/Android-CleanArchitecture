package com.globant.equattrocchio.data;

import com.globant.equattrocchio.data.response.Result;
import com.globant.equattrocchio.data.service.api.SplashbaseApi;
import com.globant.equattrocchio.domain.service.ImagesServices;
import com.google.gson.Gson;

import io.reactivex.Observer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImagesServicesImpl implements ImagesServices {

    private static final String URL= "http://splashbase.co/";

    @Override
    public void getLatestImages(final Observer<String> observer) {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(URL).
                addConverterFactory(GsonConverterFactory.create())
                .build();

        SplashbaseApi api  = retrofit.create(SplashbaseApi.class);

        Call<Result> call = api.getImages();

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                //todo: show the response.body() on the ui
                observer.onNext(new Gson().toJson(response.body().getImages()));

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                //todo: update the UI with a connection error message
            }
        });


    }
}
