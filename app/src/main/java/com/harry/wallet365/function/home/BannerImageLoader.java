package com.harry.wallet365.function.home;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.harry.wallet365.R;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Harry on 2018/8/15.
 * 轮播图的构造类
 */
public class BannerImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions()
                .error(R.drawable.ic_place_hold)
                .placeholder(R.drawable.ic_place_hold);
        Glide.with(context).load((String) path).apply(requestOptions).into(imageView);
    }


}
