package com.qf.level.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by level on 2016/12/19.
 */

public class ImageOptions {

    public static Bitmap getImageOptions(byte[] bytes){

        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;//是否为一次采样
        BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
        options.inJustDecodeBounds=false;
        options.inSampleSize=3;//设置压缩比例
        Bitmap bitmap=BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);//二次采样

        return bitmap;
    }
}
