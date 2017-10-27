package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.data.ImagesServicesImpl;
import com.globant.equattrocchio.domain.GetImageByIdUseCase;


public class ImageDetailsFragment extends Fragment {
    private GetImageByIdUseCase getImageByIdUseCase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getImageByIdUseCase = new GetImageByIdUseCase(new ImagesServicesImpl(), "1");
        return inflater.inflate(R.layout.fragment_image_detail, container, false);

    }
}
