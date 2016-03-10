package com.example.quickindex.ui;

import com.example.quickindex.util.Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.view.MotionEventCompat;
import android.text.InputFilter.LengthFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class QuickIndexBar extends View {

	private static final String[] LETTERS = new String[] { "A", "B", "C", "D",
			"E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
			"R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
	private Paint mPaint;
	private float cellWidth;
	private float cellHeight;
	
	private OnLetterUpdateListener mOnLetterUpdateListener;

	/*
	 * 暴露一个字母的监听
	 */
	public interface OnLetterUpdateListener{
		void letterUpdate(String letter);
	}
	
	public OnLetterUpdateListener getOnLetterUpdateListener() {
		return mOnLetterUpdateListener;
	}
	
	/*
	 * 设置字母监听更新
	 * @param listener
	 */
	public void setOnLetterUpdateListener(OnLetterUpdateListener mOnLetterUpdateListener) {
		this.mOnLetterUpdateListener = mOnLetterUpdateListener;
	}
	
	public QuickIndexBar(Context context) {
		this(context, null);
	}

	public QuickIndexBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public QuickIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(0xff888888);
		mPaint.setTextSize(30);
		mPaint.setTypeface(Typeface.DEFAULT_BOLD);

	}

	@Override
	protected void onDraw(Canvas canvas) {

		for (int i = 0; i < LETTERS.length; i++) {
			String text = LETTERS[i];

			// 计算坐标
			int x = (int) (cellWidth / 2.0f - mPaint.measureText(text) / 2.0f);
			// 获取文本高度
			Rect bounds = new Rect();
			mPaint.getTextBounds(text, 0, text.length(), bounds);
			int textHeight = bounds.height();
			int y = (int) (cellHeight / 2.0f + textHeight / 2.0f + i
					* cellHeight);

			// 根据按下的字母, 设置画笔颜色
//			mPaint.setColor(touchIndex == i ? Color.GRAY : Color.BLACK);
			canvas.drawText(text, x, y, mPaint);
		}
	}

	int touchIndex = -1;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int index = -1;
		switch (MotionEventCompat.getActionMasked(event)) {
		case MotionEvent.ACTION_DOWN:
			index = (int) (event.getY() / cellHeight);
			if (index >= 0 && index < LETTERS.length) {
				if (index != touchIndex) {
					mOnLetterUpdateListener.letterUpdate(LETTERS[index]);
				}
				touchIndex = index;
			}
			break;
		case MotionEvent.ACTION_UP:
			index = -1;
			break;
		case MotionEvent.ACTION_MOVE:
			index = (int) (event.getY() / cellHeight);
			if (index >= 0 && index < LETTERS.length) {
				if (index != touchIndex) {
					mOnLetterUpdateListener.letterUpdate(LETTERS[index]);
					Log.d("TAG", LETTERS[index]);
				}
				touchIndex = index;
			}
			break;
		default:
			break;
		}
		invalidate();

		return true;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		cellWidth = getMeasuredWidth();
		int mHeight = getMeasuredHeight();
		cellHeight = mHeight * 1.0f / LETTERS.length;

	}

	

}
