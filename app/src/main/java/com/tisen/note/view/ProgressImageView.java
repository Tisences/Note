package com.tisen.note.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;

import com.tisen.note.R;

import java.text.DecimalFormat;

/**
 * Created by tisen on 2016/11/3.
 */
public class ProgressImageView extends ImageView {

    private float percent = 0;
    private Paint paint;
    private RectF rectF;

    private boolean complete;

    private String direction;

    private DecimalFormat decimalFormat;

    private BitmapShader bitmapShader = null;
    private Bitmap bitmap = null;
    private int bitmapWidth = 0;
    private int bitmapHeight = 0;
    private ShapeDrawable shapeDrawable = null;



    public ProgressImageView(Context context) {
        this(context,null);
    }

    public ProgressImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ProgressImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Rect rect = new Rect();
        getDrawingRect(rect);
        int radius = (int) Math.sqrt(rect.height()*rect.height()+rect.width()*rect.width())/2;
        rectF = new RectF(rect.width()/2-radius,rect.height()/2-radius,rect.width()/2+radius,rect.height()/2+radius);
        Log.d("rectF",rectF.toString());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        float radian = 3.60f*percent;
        Log.d("onDraw","radian is "+radian);
        canvas.drawArc(rectF,0,radian,true,paint);

    }

    public void setPercent(float percent){
        this.percent = percent;
        postInvalidate();
    }
    public void setDirection(String direction){
        this.direction = direction;
    }

    public class Direction{
        public static final int LEFT = 0;
        public static final int RIGHT = 1;
        public static final int TOP = 2;
        public static final int BOTTOM = 3;
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        this.bitmap = bm;
        bitmapWidth = bm.getWidth();
        bitmapHeight = bm.getHeight();
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.REPEAT);
        paint.setShader(bitmapShader);
    }
}
