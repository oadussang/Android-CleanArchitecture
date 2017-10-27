package com.globant.equattrocchio.domain.service;

import io.reactivex.Observer;

public interface ImagesServices {

    void getLatestImages(Observer<String> observer);
    void getImageById(Observer<String> observer, String imageId);
}
