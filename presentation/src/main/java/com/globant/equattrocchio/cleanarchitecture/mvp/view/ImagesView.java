package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.ImageItemAdapter;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallUpdateServiceButtonObserver;
import com.globant.equattrocchio.data.response.Image;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImagesView extends ActivityView {

    @BindView(R.id.tv_incoming_json) TextView tvlabel;
    @BindView(R.id.recycler_image_list) RecyclerView imageListRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public ImagesView(AppCompatActivity activity) {
        super(activity);
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

    private static final String DIALOG_TAG = "dialog";
    public void startDetailsFragment(Image image) {
        DialogFragment dialog = new ImageDetailsDialogFragment().newInstance(image.getUrl(), String.valueOf(image.getImageId()), image.getCopyright(), image.getSite());
        dialog.show(getFragmentManager(), DIALOG_TAG);

    }

    @OnClick(R.id.fab_refresh_image_list)
    public void refreshImageList(){ RxBus.post(new CallUpdateServiceButtonObserver.CallUpdateServiceButtonPressed()); }
}
