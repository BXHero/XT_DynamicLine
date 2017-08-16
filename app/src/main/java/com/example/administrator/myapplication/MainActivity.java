package com.example.administrator.myapplication;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.x;

public class MainActivity extends AppCompatActivity {
    LineView dy2;
    MHorizontalScrrollView mHorizontalScrollView;
    private View view1;
    private View view2;
    private View view3;

    private View view4;
    private View view5;
    private View view6;
    private View view7;
    private View view8;
    private ViewPager pager;
    private ArrayList<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHorizontalScrollView = (MHorizontalScrrollView) findViewById(R.id.mHorizontalScrollView);
        mHorizontalScrollView.initData(new String[]{"必杀1","必杀2","必杀13","必杀4","必杀5","必杀6","必杀7","必杀8"},null);



        pager = (ViewPager) findViewById(R.id.view_pager);
        LayoutInflater inflater = getLayoutInflater();
        view1 = inflater.inflate(R.layout.layout1, null);
        view2 = inflater.inflate(R.layout.layout2, null);
        view3 = inflater.inflate(R.layout.layout3, null);
        view4 = inflater.inflate(R.layout.layout4, null);
        view5 = inflater.inflate(R.layout.layout5, null);
        view6 = inflater.inflate(R.layout.layout6, null);
        view7 = inflater.inflate(R.layout.layout7, null);
        view8 = inflater.inflate(R.layout.layout8, null);

        views = new ArrayList<>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        views.add(view5);
        views.add(view6);
        views.add(view7);
        views.add(view8);


        pager.setAdapter(new MyPagerAdapter(views));



    }

     class MyPagerAdapter extends PagerAdapter {

        private final ArrayList<View> views;

        public MyPagerAdapter(ArrayList<View> views) {
            this.views = views;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

    }

}
