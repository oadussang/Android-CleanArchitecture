package com.globant.equattrocchio.cleanarchitecture.util.bus.observers;

public abstract class GetImagesFromProviderBusObserver extends BusObserver<GetImagesFromProviderBusObserver.GetImagesFromProvider> {
    public GetImagesFromProviderBusObserver() {
        super(GetImagesFromProvider.class);
    }

    public static class GetImagesFromProvider {
    }
}