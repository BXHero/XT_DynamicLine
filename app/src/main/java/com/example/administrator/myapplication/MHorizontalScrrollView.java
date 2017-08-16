package com.example.administrator.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MHorizontalScrrollView extends HorizontalScrollView {

	private MScrollViewListener scrollViewListener = null;

	public MHorizontalScrrollView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
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
	private void init() {
		LinearLayout contentLl = new LinearLayout(getContext());
		contentLl.setBackgroundColor(Color.WHITE);
		contentLl.setLayoutParams(contentParams);
		contentLl.setOrientation(LinearLayout.VERTICAL);
		addView(contentLl);

		LinearLayout textViewLl = new LinearLayout(getContext());
		textViewLl.setLayoutParams(contentParams);
		textViewLl.setOrientation(LinearLayout.HORIZONTAL);
		textViewParams.setMargins(10, 0, 10, 0);
		for (int i = 0; i < 10; i++) {
			TextView textView = new TextView(getContext());
			textView.setText("A"+i);
			textView.setTextColor(Color.GRAY);
			textView.setTextSize(50);
			textView.setLayoutParams(textViewParams);
			textView.setGravity(Gravity.CENTER_HORIZONTAL);
			textView.setTag(i);
			textViews.add(textView);
			textViewLl.addView(textView);
		}


		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		dy2 = new LineView(getContext(), 0, 0, 0);
		dy2.setLayoutParams(params);
		contentLl.addView(dy2);
		contentLl.addView(textViewLl);
	}
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
//		if (scrollViewListener != null) {
//			scrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
//		}
		dy2.updateView(oldl,l);
	}

	public interface MScrollViewListener {
		void onScrollChanged(MHorizontalScrrollView scrollView, int x, int y,
                             int oldx, int oldy);
	}

	public void setScrollViewListener(MScrollViewListener scrollViewListener) {
		this.scrollViewListener = scrollViewListener;
	}


}
