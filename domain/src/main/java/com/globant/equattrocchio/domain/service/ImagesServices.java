package com.globant.equattrocchio.domain.service;

import java.util.List;

import io.reactivex.Observer;

public interface ImagesServices {

    void getLatestImages(Observer<List<Object>> observer);
    void getLatestImagesUpdate(Observer<List<Object>> observer);
    void getImageById(Observer<Object> observer, String imageId);
}
