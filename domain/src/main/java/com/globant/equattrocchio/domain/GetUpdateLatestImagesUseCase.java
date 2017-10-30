package com.globant.equattrocchio.domain;

import com.globant.equattrocchio.domain.service.ImagesServices;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class GetUpdateLatestImagesUseCase extends UseCase<List<Object>,Void> {

    private ImagesServices imagesServices;

    public GetUpdateLatestImagesUseCase(ImagesServices imagesServices) {
        super();
        this.imagesServices = imagesServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<List<Object>> observer, Void aVoid) {
        imagesServices.getLatestImagesUpdate(observer);
    }
}
