package com.globant.equattrocchio.cleanarchitecture.mvp.view.contracts;

import android.net.Uri;

public final class ImagesContract{
    public static final String CONTENT_AUTHORITY = "com.globant.equattrocchio.cleanarchitecture.provider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final class ImageColumns{
        public static final String ID = "_id";
        public static final String IMAGE_ID = "ImageId";
        public static final String URL = "Url";
        public static final String LARGE_URL = "LargeUrl";
        public static final String SOURCE_ID = "SourceId";
        public static final String SITE = "Site";
        public static final String COPYRIGHT = "Copyright";
    }
}
