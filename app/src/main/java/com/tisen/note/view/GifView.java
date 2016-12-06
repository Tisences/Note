package com.tisen.note.view;

import android.content.Context;
import android.util.AttributeSet;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by tisen on 2016/10/27.
 */
public class GifView extends DraweeView {
    private DraweeHolder draweeHolder;

    public GifView(Context context) {
        super(context);
    }

    public GifView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GifView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public GifView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
