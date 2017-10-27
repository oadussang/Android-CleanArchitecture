package com.globant.equattrocchio.cleanarchitecture.util.bus.observers;

import android.widget.TextView;

public abstract class ImageItemSelectedObserver extends BusObserver<ImageItemSelectedObserver.ImageItemSelected> {
    public ImageItemSelectedObserver() {
        super(ImageItemSelected.class);
    }

    public static class ImageItemSelected {
        private String mImageId;

        public ImageItemSelected(String id) {
            mImageId = id;
        }

        public String getImageId() {
            return mImageId;
        }

        public void setImageId(String imageId) {
            mImageId = imageId;
        }
    }
}