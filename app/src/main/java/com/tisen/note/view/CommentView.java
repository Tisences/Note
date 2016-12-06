package com.tisen.note.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by tisen on 2016/10/24.
 */
public class CommentView extends ImageView {

    private int num=0;
    private int color=0;
    private float textSize = 0f;

    public void setNum(int num) {
        this.num = num;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public CommentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String string;
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(color);
        paint.setTextSize(textSize);
        if(num>0){
            if(num<=999)
                string = num+"";
            else string = "999+";
            canvas.drawText(string,canvas.getWidth()/2,canvas.getHeight()/2,paint);
        }
    }
}
