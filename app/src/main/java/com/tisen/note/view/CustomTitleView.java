package com.tisen.note.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.tisen.note.R;

/**
 * Created by tisen on 2016/11/3.
 */
public class CustomTitleView extends View {

    private String mTitleText;
    private int mTitleTextColor;
    private int mTitleTextSize;

    private Rect mBound;
    private Paint mPaint;

    public CustomTitleView(Context context) {
        this(context, null);
        Log.d("CustomTitleView","1");
    }

    public CustomTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        Log.d("CustomTitleView","2");
    }

    public CustomTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d("CustomTitleView","3");
        TypedArray array = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyleAttr, 0);
        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.CustomTitleView_title_text:
                    mTitleText = array.getString(attr);
                    break;
                case R.styleable.CustomTitleView_title_textColor:
                    mTitleTextColor = array.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomTitleView_title_textSize:
                    mTitleTextSize = array.getDimensionPixelSize(attr
                            , (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP
                                    , 16, getResources().getDisplayMetrics()));
                    break;
            }
        }
        array.recycle();

        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mBound = new Rect();
        mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mBound);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("onDraw","1234");
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);
        mPaint.setColor(mTitleTextColor);
        canvas.drawText(mTitleText,getWidth()/2-mBound.width()/2,getHeight()/2+mBound.height()/2,mPaint);
    }
}
