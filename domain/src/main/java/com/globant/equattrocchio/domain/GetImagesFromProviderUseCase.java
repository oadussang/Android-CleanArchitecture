package com.globant.equattrocchio.domain;

import com.globant.equattrocchio.domain.service.ImagesServices;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class GetImagesFromProviderUseCase extends UseCase<List<Object>,Void> {

    private ImagesServices imagesServices;

    public GetImagesFromProviderUseCase(ImagesServices imagesServices) {
        super();
        this.imagesServices = imagesServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<List<Object>> observer, Void aVoid) {
        imagesServices.getImagesFromProvider(observer);
    }
}
