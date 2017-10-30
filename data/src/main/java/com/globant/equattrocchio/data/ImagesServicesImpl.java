package com.globant.equattrocchio.data;

import com.globant.equattrocchio.data.response.Image;
import com.globant.equattrocchio.data.response.Result;
import com.globant.equattrocchio.data.service.api.SplashbaseApi;
import com.globant.equattrocchio.domain.service.ImagesServices;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import io.reactivex.Observer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImagesServicesImpl implements ImagesServices {

    private static final String URL= "http://splashbase.co/";

    @Override
    public void getLatestImages(final Observer<List<Object>> observer) {
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
                observer.onNext(new ArrayList<Object>(response.body().getImages()));
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                //todo: update the UI with a connection error message
            }
        });
    }

    @Override
    public void getImageById(final Observer<Object> observer, String imageId) {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(URL).
                addConverterFactory(GsonConverterFactory.create())
                .build();
        SplashbaseApi api  = retrofit.create(SplashbaseApi.class);
        Call<Image> call = api.getImageById(imageId);
        call.enqueue(new Callback<Image>() {
            @Override
            public void onResponse(Call<Image> call, Response<Image> response) {
                WeakHashMap<String, String> imageParams = new WeakHashMap<>();
                imageParams.put("key1",String.valueOf(response.body().getId()));
                imageParams.put("key2",String.valueOf(response.body().getUrl()));
                imageParams.put("key3",String.valueOf(response.body().getCopyright()));
                imageParams.put("key4",String.valueOf(response.body().getSite()));
                observer.onNext(response.body());
                //new Gson().toJson(response.body().)
            }
            @Override
            public void onFailure(Call<Image> call, Throwable t) {
                //todo: update the UI with a connection error message
            }
        });
    }
}
