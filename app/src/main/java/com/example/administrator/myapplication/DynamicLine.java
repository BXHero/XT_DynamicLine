package com.example.administrator.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

public class DynamicLine extends View {

	public DynamicLine(Context context, int shaderColorStart,
			int shaderColorEnd, int lineHeight) {
		this(context, null);
		this.shaderColorStart = shaderColorStart;
		this.shaderColorEnd = shaderColorEnd;
		this.lineHeight = lineHeight;
		init();
	}

	public DynamicLine(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		init();
	}

	public DynamicLine(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public void setShaderColorEnd(int shaderColorEnd) {
		this.shaderColorEnd = shaderColorEnd;
	}

	public void setShaderColorStart(int shaderColorStart) {
		this.shaderColorStart = shaderColorStart;
	}

	public void setLineHeight(int lineHeight) {
		this.lineHeight = lineHeight;
	}

	/**
	 * 1-高度 2-渐变色---开始-结束
	 */
	private int lineHeight = 10;
	private int shaderColorEnd = Color.RED;
	private int shaderColorStart= Color.BLUE;

	// 的起始X,终止X坐标。
	private float startX, stopX;
	private Paint paint;
	// RectF指的是float精度的矩形---刚好两点一线
	private RectF rectF = new RectF(startX, 0, stopX, 0);

	public void init() {
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.FILL);// 填充
		paint.setStrokeWidth(5);
		// 渲染效果
		paint.setShader(new LinearGradient(0, 100,
				getScreenWidth(getContext()), 100, shaderColorStart,
				shaderColorEnd, Shader.TileMode.MIRROR));
		paint.setColor(Color.BLUE);

	}

	public static int getScreenWidth(Context context) {
		// 获取屏幕宽度---分辨率宽度
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		heightMeasureSpec = MeasureSpec.makeMeasureSpec(lineHeight,
				MeasureSpec.getMode(heightMeasureSpec));
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		rectF.set(startX, 0, stopX, 10);
		// 圆角矩形的圆角的曲率
		canvas.drawRoundRect(rectF, 5, 5, paint);
	}

	public void updateView(float startX, float stopX) {
		this.startX = startX;
		this.stopX = stopX;
		invalidate();
	}

}
