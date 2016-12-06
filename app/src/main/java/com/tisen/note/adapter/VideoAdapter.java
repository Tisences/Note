package com.tisen.note.adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tisen.note.R;
import com.tisen.note.TestActivity;
import com.tisen.note.activity.VideoActivity;
import com.tisen.note.model.Video;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tisen on 2016/11/8.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.Item> {

    private BitmapFactory.Options options = new BitmapFactory.Options();
    private ContentResolver resolver;
    private Context context;
    private List<TestActivity.Video> videos = new ArrayList<>();
    private SimpleDateFormat duraFormat = new SimpleDateFormat("mm:ss");


    public VideoAdapter(Context context, List<TestActivity.Video> videos) {
        this.context = context;
        this.videos = videos;
        resolver = context.getContentResolver();
    }

    @Override
    public Item onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Item(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false));
    }

    @Override
    public void onBindViewHolder(Item holder, int position) {
        holder.setInfo(videos.get(position));
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    private String getSizeString(long size) {
        if (size < 1024)
            return size + "B";
        if (size < 1024 * 1024)
            return size / 1024 + "KB";
        if (size < 1024 * 1024 * 1024)
            return size / 1024 / 1024 + "MB";
        return size / 1024 / 1024 / 1024 + "GB";

    }

    public class Item extends RecyclerView.ViewHolder {
        View itemView;
        ImageView image;
        TextView duration;
        TextView size;
        TextView fileName;

        public Item(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.video_avatar);
            duration = (TextView) itemView.findViewById(R.id.video_duration);
            size = (TextView) itemView.findViewById(R.id.video_size);
            fileName = (TextView) itemView.findViewById(R.id.video_fileName);
            this.itemView = itemView;
        }

        public void setInfo(final TestActivity.Video video) {
            Log.d("setInfo",video.toString());
            if (video.getAvatar() != null)
                image.setImageBitmap(video.getAvatar());
            duration.setText(duraFormat.format(video.getDuration()));
            size.setText(getSizeString(video.getSize()));
            fileName.setText(video.getTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(context, VideoActivity.class);
                    intent.putExtra("path",video.getPath());
                    context.startActivity(intent);
                }
            });
        }

    }
}
