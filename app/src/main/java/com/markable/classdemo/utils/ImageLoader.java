package com.markable.classdemo.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.markable.classdemo.R;

/**
 * Created by Markable on 2016/11/28.
 */

public class ImageLoader {

    public static void load(Context context, String url, ImageView view) {
        Glide.with(context).load(url).placeholder(R.mipmap.ic_photo_place).crossFade().into(view);
    }

    public static void loadWithNoBg(Context context,String url,ImageView view){
        Glide.with(context).load(url).crossFade().into(view);
    }

    public static void clear(Context context) {
        Glide.get(context).clearMemory();
    }
}
