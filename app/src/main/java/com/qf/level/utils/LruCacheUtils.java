package com.qf.level.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

/**
 * Created by level on 2016/12/19.
 */

public class LruCacheUtils {

    private int maxSize=4*1024*1024;
    private LruCache<String,Bitmap> lruCache;


    public LruCache<String,Bitmap> getInstance(){

        lruCache=new LruCache<>(maxSize);
        return lruCache;
    }
    //存图片到缓存
    public void saveImage(Bitmap bitmap,String imgUrl){
        if (lruCache.get(imgUrl)==null && bitmap!=null){
            lruCache.put(imgUrl,bitmap);
        }else {
            Log.d("zsp","连接下载的结果为空");
        }
    }
    //从缓存中取图片
    public Bitmap getImage(String imgUrl){
        if (lruCache.get(imgUrl)!=null){
            return lruCache.get(imgUrl);
        }else{
            return null;
        }
    }
    //清除缓存中图片
    public void deleteImage(String imaUrl){
        if (lruCache.get(imaUrl)!=null){
            lruCache.remove(imaUrl);
        }
    }

}
