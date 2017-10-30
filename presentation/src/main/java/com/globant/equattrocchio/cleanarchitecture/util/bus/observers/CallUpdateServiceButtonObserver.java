package com.globant.equattrocchio.cleanarchitecture.util.bus.observers;

public abstract class CallUpdateServiceButtonObserver extends BusObserver<CallUpdateServiceButtonObserver.CallUpdateServiceButtonPressed> {
    public CallUpdateServiceButtonObserver() {
        super(CallUpdateServiceButtonPressed.class);
    }

    public static class CallUpdateServiceButtonPressed {
    }
}