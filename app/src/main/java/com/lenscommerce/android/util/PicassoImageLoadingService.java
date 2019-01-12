package com.lenscommerce.android.util;

import android.widget.ImageView;

import com.lenscommerce.android.ui.widget.slider.ImageLoadingService;
import com.squareup.picasso.Picasso;

public class PicassoImageLoadingService implements ImageLoadingService {
    @Override
    public void loadImage(String url, ImageView imageView) {
        Picasso.get().load(url).into(imageView);
    }

    @Override
    public void loadImage(int resource, ImageView imageView) {
        Picasso.get().load(resource).into(imageView);
    }

    @Override
    public void loadImage(String url, int placeHolder, int errorDrawable, ImageView imageView) {
        Picasso.get().load(url).placeholder(placeHolder).error(errorDrawable).into(imageView);
    }
}
