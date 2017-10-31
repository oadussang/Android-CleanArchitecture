package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.loaders.ImageLoader;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallUpdateServiceButtonObserver;
import com.globant.equattrocchio.data.response.Image;

import java.util.List;
import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class ImagesView extends ActivityView{

    private static final int IMAGES_LOADER_ID = 1;

    @BindView(R.id.tv_incoming_json) TextView tvlabel;
    @BindView(R.id.recycler_image_list) RecyclerView imageListRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public ImagesView(AppCompatActivity activity) {
        super(activity);
        getLoaderManager().initLoader(IMAGES_LOADER_ID, null, imagesLoaderCallback);
        ButterKnife.bind(this, activity);
    }
    
    public void showText(String text) {
        tvlabel.setText(text);
    }

    public void setCardViewList(List<Image> images){
        imageListRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        imageListRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ImageItemAdapter(images);
        imageListRecyclerView.setAdapter(mAdapter);
    }

    @OnClick(R.id.btn_call_service)
    public void callServiceBtnPressed() { RxBus.post(new CallServiceButtonObserver.CallServiceButtonPressed()); }

    public void showError() {
        tvlabel.setText(R.string.connection_error);
    }

    public void startDetailsFragment(Image image) {
        DialogFragment dialog = new ImageDetailsDialogFragment().newInstance(image.getUrl(), String.valueOf(image.getImageId()), image.getCopyright(), image.getSite());
        dialog.show(getFragmentManager(), getContext().getString(R.string.dialog_tag));
    }

    @OnClick(R.id.fab_refresh_image_list)
    public void refreshImageList(){ RxBus.post(new CallUpdateServiceButtonObserver.CallUpdateServiceButtonPressed()); }


    public void resetLoader(){
        getLoaderManager().restartLoader(IMAGES_LOADER_ID, null, imagesLoaderCallback);
    }

    LoaderManager.LoaderCallbacks imagesLoaderCallback = new LoaderManager.LoaderCallbacks<List<Image>>() {

        @Override
        public Loader<List<Image>> onCreateLoader(int id, Bundle args) {
            return new ImageLoader(getContext(), new DisposableObserver<List<Image>>() {
                @Override
                public void onNext(@NonNull List<Image> images) {

                }

                @Override
                public void onError(@NonNull Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
        }

        @Override
        public void onLoadFinished(Loader<List<Image>> loader, List<Image> data) {
            if(data != null && data.size()!=0) {
                //setCardViewList(data);
            }
        }

        @Override
        public void onLoaderReset(Loader<List<Image>> loader) {

        }
    };
}
