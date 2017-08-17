package com.example.administrator.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MHorizontalScrrollView extends HorizontalScrollView {



	public MHorizontalScrrollView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
//		initData(null,null);
	}

	public MHorizontalScrrollView(Context context, AttributeSet attrs) {
		this(context,  attrs, 0);
	}

	public MHorizontalScrrollView(Context context) {
		this(context, null);
	}
	private LinearLayout.LayoutParams contentParams = new LinearLayout.LayoutParams(
			ViewGroup.LayoutParams.WRAP_CONTENT,
			ViewGroup.LayoutParams.WRAP_CONTENT);
	private LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(
			ViewGroup.LayoutParams.WRAP_CONTENT,
			ViewGroup.LayoutParams.WRAP_CONTENT);
	private ArrayList<TextView> textViews = new ArrayList<>();

	LineView dy2;
	String[] titlesTextView;

	/**
	 * 主入口
	 * @param arg
	 * @param viewPager
	 */
    private int lastPosition; //页面显示的最后位置  也是当前位置
    ViewPager viewPager;
	public void initData(String[] arg, ViewPager viewPager){
		titlesTextView = arg;
		initLine();//线条
		initTextViews();//字符
		init();//结合字符和线条 添加入布局



		//让pager和TextViews数组关联起来
		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //参数2是以[0,1)作为位移变量显示
                //参数3是以像素为位置偏移量

            }

            @Override
            public void onPageSelected(int position) {
                setCurrentSelectTextSize(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == ViewPager.SCROLL_STATE_SETTLING){

                }
            }
        });
        this.viewPager = viewPager;
        //默认选中的字符--黑粗
        setCurrentSelectTextSize(0);
	}

	private void init() {
		LinearLayout contentLl = new LinearLayout(getContext());
		contentLl.setBackgroundColor(Color.WHITE);
		contentLl.setLayoutParams(contentParams);
		contentLl.setOrientation(LinearLayout.VERTICAL);
		addView(contentLl);

		contentLl.addView(dy2);
		contentLl.addView(textViewLl);
	}

	private void initLine(){
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		dy2 = new LineView(getContext(), 0, 0, 0);
		dy2.setLayoutParams(params);
	}
	private LinearLayout textViewLl;
	private int margin;
	private void initTextViews(){
		textViewLl = new LinearLayout(getContext());
		textViewLl.setLayoutParams(contentParams);
		textViewLl.setOrientation(LinearLayout.HORIZONTAL);

		margin = getTextViewMarggins(titlesTextView);
		textViewParams.setMargins(margin, 0, margin, 0);
		String[] args = new String[10];
		for (int i = 0; i < titlesTextView.length; i++) {
			TextView textView = new TextView(getContext());
			textView.setText(titlesTextView[i]);
			textView.setTextColor(Color.GRAY);
			textView.setTextSize(defultTextSize);
			textView.setLayoutParams(textViewParams);
			textView.setGravity(Gravity.CENTER_HORIZONTAL);
			textView.setTag(i);
			textView.setOnClickListener(onClickListener);
			textViews.add(textView);
			textViewLl.addView(textView);
			args[i] = textView.getText().toString();
		}
//		getTextViewMarggins(args);
	}
	private int defultTextSize = 20;
	private int defaultTextColor = Color.GRAY;
	private int selectTextSize = 30;
	private int selectTextColor = Color.BLACK;
	private int itemMargins = 30; //每个字符之间的间距
	private int allTextViewLength;

	private int getTextViewMarggins(String[] args){
		//总长度
		float countLength = 0;
		TextView tv = new TextView(getContext());
		tv.setTextSize(defultTextSize);
		TextPaint paint = tv.getPaint();

		for(int i = 0; i < args.length; i++){
			//paint.measureText(args[i]) 获取当前的字符要占用的长度
			countLength = countLength + itemMargins + paint.measureText(args[i]) + itemMargins;
		}

		//获取当前屏幕的宽度
		WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;
		//目前用的模拟器测试屏 宽是1080
		if(countLength <= screenWidth){
			//字符小于屏宽的时候需要让字符的宽度按照屏框来区分
			//每一个字符分到的长度
			int textSize = screenWidth / args.length;
			//每一个字符本身具有的长度
			int textLength = (int) paint.measureText(args[0]);
			//最后除2算出间距
			allTextViewLength = (int) countLength;
			return (int) ((textSize - textLength) / 2);
		}else{
			allTextViewLength = (int) countLength;
			return itemMargins;
		}
	}
//dynamicLine.updateView((position + positionOffset) * everyLength + dis + fixLeftDis, (lastPosition + 1) * everyLength - dis);
	private void setCurrentSelectTextSize(int index){
		for(int i = 0; i < textViews.size();i++){
			if(i == index){
				textViews.get(i).setTextColor(selectTextColor);
				textViews.get(i).setTextSize(selectTextSize);
				final int textViewLength = allTextViewLength/titlesTextView.length;

				final int startX = index*textViewLength;
				final int loastX = startX + textViewLength;
				dy2.updateView(startX,loastX);
                viewPager.setCurrentItem(index);
			}else{
				textViews.get(i).setTextColor(defaultTextColor);
				textViews.get(i).setTextSize(defultTextSize);
			}
		}
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
//		if (scrollViewListener != null) {
//			scrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
//		}
//		dy2.updateView(oldl,l);
	}

	private OnClickListener onClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			setCurrentSelectTextSize((int) v.getTag());
		}
	};


//	private OnTextViewClick onTextViewClick;
//	public interface OnTextViewClick{
//		void TextViewClick(TextView textView, int index);
//	}
//	public void setOnTextViewClickListener(OnTextViewClick mOnTextViewClick){
//		this.onTextViewClick = mOnTextViewClick;
//	}


}
