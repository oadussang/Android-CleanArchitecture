package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.domain.GetImageByIdUseCase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageDetailsDialogFragment extends DialogFragment {

    private static final String IMAGE_URL_KEY = "image_url";
    private static final String IMAGE_ID_KEY = "image_id";
    private static final String IMAGE_COPYRIGHT_KEY = "image_copyright";
    private static final String IMAGE_SITE_KEY = "image_site";

    private String mImageUrl;
    private String mImageId;
    private String mImageCopyright;
    private String mImageSite;

    @BindView(R.id.image_detail_image) ImageView imageIv;
    @BindView(R.id.text_detail_image_id) TextView imageIdTv;
    @BindView(R.id.text_detail_image_copyright) TextView imageCopyrightTv;
    @BindView(R.id.text_detail_image_site) TextView imageSiteTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageUrl = getArguments().getString(IMAGE_URL_KEY);
        mImageId = getArguments().getString(IMAGE_ID_KEY);
        mImageCopyright = getArguments().getString(IMAGE_COPYRIGHT_KEY);
        mImageSite = getArguments().getString(IMAGE_SITE_KEY);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    public static ImageDetailsDialogFragment newInstance(String imageUrl, String imageId, String imageCopyright, String imageSite) {
        ImageDetailsDialogFragment fragment = new ImageDetailsDialogFragment();
        Bundle args = new Bundle();
        args.putString(IMAGE_URL_KEY, imageUrl);
        args.putString(IMAGE_ID_KEY, imageId);
        args.putString(IMAGE_COPYRIGHT_KEY, imageCopyright);
        args.putString(IMAGE_SITE_KEY, imageSite);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_detail, container, false);
        ButterKnife.bind(this, view);

        Glide
                .with(view)
                .load(mImageUrl)
                .into(imageIv);

        imageIdTv.setText(mImageId);
        imageCopyrightTv.setText(mImageCopyright);
        imageSiteTv.setText(mImageSite);
        return view;
    }

    @OnClick(R.id.button_close_image_detail)
    public void closeButton(){
        this.dismiss();
    }
}
