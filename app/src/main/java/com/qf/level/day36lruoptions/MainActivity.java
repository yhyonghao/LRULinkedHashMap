package com.qf.level.day36lruoptions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.qf.level.Person;
import com.qf.level.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<Person> datas;
    private String[] urls={
            "http://cdn01.happyjuzi.com/news/201612/19/58577eba36652.gif!ac1.nw",
            "http://cdn01.happyjuzi.com/news/201612/18/58566a5863186.jpg!280.nw.webp",
            "http://cdn01.happyjuzi.com/news/201612/18/5856891f75b00.jpg!280.nw.webp",
            "http://cdn01.happyjuzi.com/content/201611/23/5835b29cb6be1.jpg!52.webp",
            "http://cdn01.happyjuzi.com/news/201612/18/58567b26e528b.jpg!280.nw.webp",
            "http://cdn01.happyjuzi.com/news/201612/18/58567276c2469.gif!400.nw",
            "http://cdn01.happyjuzi.com/news/201612/18/58565d3879c18.jpg!280.nw.webp",
            "http://cdn01.happyjuzi.com/news/201612/18/585642c21d8e2.jpg!480.nw.webp"
    };
    private MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView= (ListView) findViewById(R.id.listviewId);
        initData();
        adapter=new MyAdapter(this,datas);
        listView.setAdapter(adapter);
    }

    private void initData() {
        //初始化数据源
        datas=new ArrayList<>();
        Person person;
        for (int i = 0; i < urls.length; i++) {
            person=new Person("图片"+i,urls[i]);
            datas.add(person);
        }
    }
}
