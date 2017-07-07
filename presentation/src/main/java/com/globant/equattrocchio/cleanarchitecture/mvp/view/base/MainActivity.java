package com.globant.equattrocchio.cleanarchitecture.mvp.view.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.imagesPresenter;

public class MainActivity extends AppCompatActivity {

    private imagesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //presenter = new imagesPresenter(new CountModel(), new CountView(this));
        //Todo: crear el presenter
    }

    @Override
    protected void onResume() {
        super.onResume();
        //presenter.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
       // presenter.unregister();
    }
}