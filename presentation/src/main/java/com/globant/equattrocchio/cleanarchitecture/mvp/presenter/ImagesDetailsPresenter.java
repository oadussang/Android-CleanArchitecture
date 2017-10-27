package com.globant.equattrocchio.cleanarchitecture.mvp.presenter;

import android.app.Activity;

import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.ImageItemSelectedObserver;
import com.globant.equattrocchio.data.ImagesServicesImpl;
import com.globant.equattrocchio.data.response.Image;
import com.globant.equattrocchio.domain.GetImageByIdUseCase;
import com.globant.equattrocchio.domain.GetLatestImagesUseCase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class ImagesDetailsPresenter {

    private ImagesView view;
    private GetLatestImagesUseCase getLatestImagesUseCase;
    private GetImageByIdUseCase getImageByIdUseCase;

    public ImagesDetailsPresenter(ImagesView view, GetImageByIdUseCase getImageByIdUseCase) {
        this.view = view;
        this.getImageByIdUseCase = getImageByIdUseCase;
    }
    public void onImageResponseReceived(String jsonResponse) {
        Image image = new Gson().fromJson(jsonResponse, new TypeToken<Image>(){}.getType());
        //view.startDetailsFragment(image);
    }

    private void onImageItemSelected(final String id) {
        getImageByIdUseCase.execute(new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String jsonResponse) {
                onImageResponseReceived(jsonResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.showError();
            }

            @Override
            public void onComplete() {
                new ImagesServicesImpl().getImageById(null, id);
            }
        },null);
    }


    public void register() {
        Activity activity = view.getActivity();

        if (activity==null){
            return;
        }

        RxBus.subscribe(activity, new ImageItemSelectedObserver() {
            @Override
            public void onEvent(ImageItemSelected value) {
                onImageItemSelected(value.getImageId());
            }
        });
    }

    public void unregister() {
        Activity activity = view.getActivity();

        if (activity==null){
            return;
        }
        RxBus.clear(activity);
    }
}
