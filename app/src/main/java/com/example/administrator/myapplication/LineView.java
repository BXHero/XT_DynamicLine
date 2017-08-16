package com.example.administrator.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/8/8.
 */

public class LineView extends View {


    public LineView(Context context) {
        this(context,null);
    }

    public LineView(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LineView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //init();
    }


    private float startX, stopX;
    private Paint mPaint;
    private RectF rectF = new RectF(startX, 0, stopX, 0);
    private int lineHeight = 300;

    public LineView(Context context, int shaderColorStart, int shaderColorEnd, int lineHeight) {
        this(context, null);
//        this.shaderColorStart = shaderColorStart;
//        this.shaderColorEnd = shaderColorEnd;
//        this.lineHeight = lineHeight;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(15);
        mPaint.setShader(new LinearGradient(0, 100, getScreenWidth(getContext()), 100, Color.parseColor("#ffc125"), Color.parseColor("#ff4500"), Shader.TileMode.MIRROR));
    
    }
    public static int getScreenWidth(Context context) {
        //获取屏幕宽度---分辨率宽度
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        Log.d("LZP","dm.widthPixels:"+dm.widthPixels);
        return dm.widthPixels;
    }
    private int mWidth;
    private int mHeight;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //确定线条View的高度
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(lineHeight, MeasureSpec.getMode(heightMeasureSpec));
        mWidth = widthMeasureSpec;
        mHeight = heightMeasureSpec;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    int y = 100;
    @Override
    protected void onDraw(Canvas canvas) {

    	rectF.set(startX, 0, stopX, 10);
        //圆角矩形的圆角的曲率
    	//画的方向...一定是从左到右去画矩形
        canvas.drawRoundRect(rectF, 5, 5, mPaint);
    }

    public void updateView(float startX, float stopX) {
    	/**
    	 *  Android提供了Invalidate方法实现界面刷新，但是Invalidate不能直接在线程中调用，因为他是违背了单线程模型：android UI操作并不是线程安全的，并且这些操作必须在UI线程中调用。 
			invalidate()是用来刷新View的，必须是在UI线程中进行工作。比如在修改某个view的显示时，调用invalidate()才能看到重新绘制的界面。invalidate()的调用是把之前的旧的view从主UI线程队列中pop掉。 一个Android 程序默认情况下也只有一个进程，但一个进程下却可以有许多个线程。
			在这么多线程当中，把主要是负责控制UI界面的显示、更新和控件交互的线程称为UI线程，由于onCreate()方法是由UI线程执行的，所以也可以把UI线程理解为主线程。其余的线程可以理解为工作者线程。
			invalidate()得在UI线程中被调动，在工作者线程中可以通过Handler来通知UI线程进行界面更新。
			而postInvalidate()在工作者线程中被调用
			
			使用postInvalidate则比较简单，不需要handler，直接在线程中调用postInvalidate即可。 
    	 */
        Log.d("LZP","=====+++++++++++++++startX++++++======="+startX);
        Log.d("LZP","=====+++++++++++++++stopX+++++======="+stopX);
        this.startX = startX;
        this.stopX = stopX;
        invalidate();

    }
}
