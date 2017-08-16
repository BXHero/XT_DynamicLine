package com.example.administrator.myapplication;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.R.attr.x;

public class MainActivity extends AppCompatActivity {
    LineView dy2;
    LineView line222;
    MHorizontalScrrollView mHorizontalScrollView;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            dy2.updateView(0, 100);
            dy2.invalidate();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        dy2 = new LineView(this);
//        line222 = (LineView) findViewById(R.id.line222);
////		dy2.updateView(200, 200);
////		mHorizontalScrollView = (MHorizontalScrrollView) findViewById(R.id.mHorizontalScrollView);
//        mHorizontalScrollView = (MHorizontalScrrollView) findViewById(R.id.mHorizontalScrollView);
//        LinearLayout painLayout = new LinearLayout(this);
//        LinearLayout.LayoutParams mLP = new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
//        painLayout.setOrientation(LinearLayout.HORIZONTAL);
//        painLayout.setLayoutParams(mLP);
//
//        LinearLayout.LayoutParams textParam = new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
//        textParam.setMargins(50, 0, 50, 0);
//        for(int i = 0; i < 10; i++){
//            TextView tv = new TextView(this);
//            tv.setText("A"+i);
//            //必须六位
//            tv.setTextColor(Color.parseColor("#FF0000"));
//            tv.setTextSize(50);
//            tv.setLayoutParams(textParam);
//            painLayout.addView(tv);
//        }
//
//        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT);
//        dy2.setLayoutParams(params);
//
//        dy2.updateView(0, 500);
//        painLayout.addView(dy2);

    }

    float DOWNX ;
    float MOVEX ;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                DOWNX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float sum = (event.getX()-DOWNX);
                if(sum > 0){
                    //向右
                   // line222.updateView(DOWNX, event.getX());
                }else{
                    //向左
                    //line222.updateView(event.getX(),DOWNX );
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
            default:
                break;
        }

        return true;
    }
}
