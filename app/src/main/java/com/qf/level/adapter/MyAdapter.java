package com.qf.level.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.level.Person;
import com.qf.level.day36lruoptions.R;
import com.qf.level.utils.DownLoadUtils;
import com.qf.level.utils.ImageOptions;
import com.qf.level.utils.LruCacheUtils;

import java.util.List;

/**
 * Created by level on 2016/12/19.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Person> list;
    private LruCache<String,Bitmap> lruCache;
    private Handler handler=new Handler();//用来在子线程设置图片内容

    public MyAdapter(Context context, List<Person> list) {
        this.context = context;
        this.list = list;
        lruCache= new LruCacheUtils().getInstance();//得到缓存对象

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Person getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_list,null);
            viewHolder=new ViewHolder();
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.imageId);
            viewHolder.textView= (TextView) convertView.findViewById(R.id.nameId);
            viewHolder.imageView.setTag(list.get(position).getImgUrl());
            viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();

            viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
            viewHolder.imageView.setTag(list.get(position).getImgUrl());
        }
        Log.d("zsp","position---"+position);
        Log.d("zsp","tag---"+list.get(position).getImgUrl());
        viewHolder.textView.setText(list.get(position).getName());
        final String imgUrl = list.get(position).getImgUrl();
        if (lruCache.get(imgUrl)!=null){
            viewHolder.imageView.setImageBitmap(lruCache.get(imgUrl));//缓存中有就从缓存中取图片
        }else{//缓存中没有就下载
            final ViewHolder finalViewHolder = viewHolder;
            if (!viewHolder.imageView.getTag().equals(imgUrl)){
                finalViewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    byte[] imageByte = DownLoadUtils.getImageByte(imgUrl);
                    final Bitmap bitmap= ImageOptions.getImageOptions(imageByte);//对图片进行二次采样
                    lruCache.put(imgUrl,bitmap);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            finalViewHolder.imageView.setImageBitmap(bitmap);
                        }
                    });
                }
            }).start();
        }



        return convertView;
    }

    class ViewHolder{
        TextView textView;
        ImageView imageView;
    }
}
