package com.tisen.note;

import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.widget.SeekBar;

import com.tisen.note.activity.BaseActivity;
import com.tisen.note.view.ProgressImageView;

public class MainActivity extends BaseActivity {

    private ProgressImageView imageView;
    private SeekBar seekBar;
    private static final String BAIDU_MAP_KEY = "cEguDLVyk2Oe9iDhE91sg9nwqkG0dPL9";
    private static final String QQ_KEY = "nkd2DbUxyVtQWAT1";
    private static final String QQ_ID = "1105727545";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ProgressImageView) findViewById(R.id.image);
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.test_image_small));
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar.setMax(10000);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                imageView.setPercent(progress/100.00f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
