package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.app.DialogFragment;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.ImageItemAdapter;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallUpdateServiceButtonObserver;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.contracts.ImagesContract;
import com.globant.equattrocchio.data.response.Image;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImagesView extends ActivityView {

    @BindView(R.id.tv_incoming_json)
    TextView tvlabel;
    @BindView(R.id.recycler_image_list)
    RecyclerView imageListRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public ImagesView(AppCompatActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
        getLoaderManager().initLoader(1, null, loaderCallback);
    }

    public void showText(String text) {
        tvlabel.setText(text);
    }

    public void resetLoader() {
        getLoaderManager().restartLoader(1, null, loaderCallback);
    }

    public void setCardViewList(List<Image> images) {
        imageListRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        imageListRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ImageItemAdapter(images);
        imageListRecyclerView.setAdapter(mAdapter);
    }

    @OnClick(R.id.btn_call_service)
    public void callServiceBtnPressed() {
        RxBus.post(new CallServiceButtonObserver.CallServiceButtonPressed());
    }

    public void showError() {
        tvlabel.setText(R.string.connection_error);
    }

    public void startDetailsFragment(Image image) {
        DialogFragment dialog = new ImageDetailsDialogFragment().newInstance(image.getUrl(), String.valueOf(image.getImageId()), image.getCopyright(), image.getSite());
        dialog.show(getFragmentManager(), getActivity().getString(R.string.dialog_tag));
    }

    @OnClick(R.id.fab_refresh_image_list)
    public void refreshImageList() {
        RxBus.post(new CallUpdateServiceButtonObserver.CallUpdateServiceButtonPressed());
    }

    LoaderManager.LoaderCallbacks loaderCallback = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader onCreateLoader(int id, Bundle args) {
            CursorLoader cursorLoader = new CursorLoader(getContext(), ImagesContract.BASE_CONTENT_URI, null,
                    null, null, null);
            return cursorLoader;
        }

        @Override
        public void onLoadFinished(Loader loader, Cursor cursor) {
            if (cursor.getCount() != 0) {
                List<Image> images = new ArrayList<>();
                while (cursor.moveToNext()) {
                    Image image = new Image();
                    image.setImageId(cursor.getInt(cursor.getColumnIndex(ImagesContract.ImageColumns.IMAGE_ID)));
                    image.setUrl(cursor.getString(cursor.getColumnIndex(ImagesContract.ImageColumns.URL)));
                    image.setLargeUrl(cursor.getString(cursor.getColumnIndex(ImagesContract.ImageColumns.LARGE_URL)));
                    image.setSourceId(cursor.getString(cursor.getColumnIndex(ImagesContract.ImageColumns.SOURCE_ID)));
                    image.setSite(cursor.getString(cursor.getColumnIndex(ImagesContract.ImageColumns.SITE)));
                    image.setCopyright(cursor.getString(cursor.getColumnIndex(ImagesContract.ImageColumns.COPYRIGHT)));
                    images.add(image);
                }
                setCardViewList(images);
            } else {
                //TODO setUI with -> not images in local db
            }
        }

        @Override
        public void onLoaderReset(Loader loader) {
            loader.deliverCancellation();
        }
    };
}
