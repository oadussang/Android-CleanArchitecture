package com.globant.equattrocchio.cleanarchitecture.mvp.presenter;

import android.app.Activity;

import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallUpdateServiceButtonObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.ImageItemSelectedObserver;
import com.globant.equattrocchio.data.ImagesServicesImpl;
import com.globant.equattrocchio.data.response.Image;
import com.globant.equattrocchio.domain.GetImageByIdUseCase;
import com.globant.equattrocchio.domain.GetLatestImagesUseCase;
import com.globant.equattrocchio.domain.GetUpdateLatestImagesUseCase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class ImagesPresenter {

    private final ImagesView view;
    private final GetLatestImagesUseCase getLatestImagesUseCase;

    public ImagesPresenter(ImagesView view, GetLatestImagesUseCase getLatestImagesUseCase) {
        this.view = view;
        this.getLatestImagesUseCase = getLatestImagesUseCase;
    }

    private void onListResponseReceived(List<Image> images) {
        //view.setCardViewList(images);
        view.resetLoader();
    }

    private void onImageResponseReceived(Image image) {
        view.startDetailsFragment(image);
    }

    private void onImageItemSelected(final String imageId) {
        GetImageByIdUseCase getImageByIdUseCase = new GetImageByIdUseCase(new ImagesServicesImpl());
        getImageByIdUseCase.execute(new DisposableObserver<Object>() {
            @Override
            public void onNext(@NonNull Object response) {
                onImageResponseReceived((Image) response);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.showError();
            }

            @Override
            public void onComplete() {
                new ImagesServicesImpl().getImageById(null, imageId);
            }
        }, imageId);
    }

    private void onCallServiceButtonPressed() {
        getLatestImagesUseCase.execute(new DisposableObserver<List<Object>>() {

            @Override
            public void onNext(@NonNull List<Object> imagesObj) {
                List<Image> images = new ArrayList<>(imagesObj.size());
                for (Object imageObj :imagesObj) {
                    images.add((Image) imageObj);
                }
                onListResponseReceived(images);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.showError();
            }

            @Override
            public void onComplete() {
                new ImagesServicesImpl().getLatestImages(null);
            }
        }, null);
        //todo acá tengo que llamar a la domain layer para que llame a la data layer y haga el llamdo al servicio
    }

    private void onCallUpdateServiceButtonPressed() {
        GetUpdateLatestImagesUseCase getUpdateLatestImagesUseCase = new GetUpdateLatestImagesUseCase(new ImagesServicesImpl());
        getUpdateLatestImagesUseCase.execute(new DisposableObserver<List<Object>>() {

            @Override
            public void onNext(@NonNull List<Object> imagesObj) {
                List<Image> images = new ArrayList<>(imagesObj.size());
                for (Object imageObj :imagesObj) {
                    images.add((Image) imageObj);
                }
                onListResponseReceived(images);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.showError();
            }

            @Override
            public void onComplete() {
                new ImagesServicesImpl().getLatestImagesUpdate(null);
            }
        }, null);
    }

    public void register() {
        Activity activity = view.getActivity();

        if (activity == null) {
            return;
        }

        RxBus.subscribe(activity, new CallServiceButtonObserver() {
            @Override
            public void onEvent(CallServiceButtonPressed event) {
                onCallServiceButtonPressed();
            }
        });
        RxBus.subscribe(activity, new CallUpdateServiceButtonObserver() {
            @Override
            public void onEvent(CallUpdateServiceButtonPressed value) {
                onCallUpdateServiceButtonPressed();
            }
        });
        RxBus.subscribe(activity, new ImageItemSelectedObserver() {
            @Override
            public void onEvent(ImageItemSelected value) {
                onImageItemSelected(value.getImageId());
            }
        });
    }

    public void unregister() {
        Activity activity = view.getActivity();

        if (activity == null) {
            return;
        }
        RxBus.clear(activity);
    }
}
