package com.globant.equattrocchio.domain;

import com.globant.equattrocchio.domain.service.ImagesServices;

import io.reactivex.observers.DisposableObserver;

public class GetImageByIdUseCase extends UseCase<String,Void> {

    private ImagesServices imagesServices;
    private String mImageId;

    public GetImageByIdUseCase(ImagesServices imagesServices, String id) {
        super();
        this.imagesServices = imagesServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<String> observer, Void aVoid) {
        imagesServices.getImageById(observer, mImageId);
    }
}
