package com.globant.equattrocchio.cleanarchitecture.util.bus.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.globant.equattrocchio.data.response.Image;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;


public class ImageLoader extends AsyncTaskLoader<List<Image>> {

    private List<Image> mData;
    private Observer<List<Image>> mObserver;

    public ImageLoader(Context context, @NonNull Observer<List<Image>> observer) {
        super(context);
        mObserver = observer;
    }

    @Override
    public List<Image> loadInBackground() {
        List<Image> images = new ArrayList<>();



        return images;
    }

    @Override
    public void deliverResult(List<Image> data) {
        if (isReset()) {
            releaseResources(data);
        }
        List<Image> oldData = mData;
        mData = data;

        if (isStarted()) {
            super.deliverResult(data);
        }

        if (oldData != null && oldData != data) {
            releaseResources(oldData);
        }
    }

    @Override
    protected void onStartLoading() {
        if (mData != null) {
            deliverResult(mData);
        }

        if (takeContentChanged() || mData == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        onStopLoading();

        if (mData != null) {
            releaseResources(mData);
            mData = null;
        }

        if (mObserver != null) {
            // TODO: unregister the observer
            mObserver = null;
        }
    }

    @Override
    public void onCanceled(List<Image> data) {
        super.onCanceled(data);
        releaseResources(data);
    }


    private void releaseResources(List<Image> data) {
        data.clear();
    }
}
