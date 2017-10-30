package com.globant.equattrocchio.domain;

import com.globant.equattrocchio.domain.service.ImagesServices;

import java.util.WeakHashMap;

import io.reactivex.observers.DisposableObserver;

public class GetImageByIdUseCase extends UseCase<Object,String> {

    private ImagesServices imagesServices;

    public GetImageByIdUseCase(ImagesServices imagesServices) {
        super();
        this.imagesServices = imagesServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<Object> observer, String imageId) {
        imagesServices.getImageById(observer, imageId);
    }
}
