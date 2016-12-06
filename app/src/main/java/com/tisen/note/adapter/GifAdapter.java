package com.tisen.note.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.tisen.note.R;
import com.tisen.note.model.Comment;
import com.tisen.note.model.Gif;
import com.tisen.note.model.User;
import com.tisen.note.view.CommentView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by tisen on 2016/10/24.
 */
public class GifAdapter extends RecyclerView.Adapter<GifAdapter.GifItem> {

    private ArrayList<Gif> gifs;
    private Activity activity;
    private int width;


    public GifAdapter(ArrayList<Gif> gifs, Activity activity) {
        this.gifs = gifs;
        this.activity = activity;
    }


    @Override
    public GifItem onCreateViewHolder(ViewGroup parent, int viewType) {
        GifItem item = new GifItem(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gif, parent, false));
        return item;
    }

    @Override
    public void onBindViewHolder(GifItem holder, int position) {
        Log.d("onBindViewHolder", position + "");
        Message message = new Message();
        message.obj = gifs.get(position);
        holder.setInfo.sendMessage(message);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return gifs.size();
    }

    @Override
    public void onViewDetachedFromWindow(GifItem holder) {
        super.onViewDetachedFromWindow(holder);
        holder.detachedFromWindow();
    }


    @Override
    public void onViewAttachedToWindow(GifItem holder) {
        super.onViewAttachedToWindow(holder);
        holder.attachedToWindow();
    }

    public void reFresh(List<Gif> gifs) {
        this.gifs = (ArrayList<Gif>) gifs;
        notifyDataSetChanged();
    }


    class GifItem extends RecyclerView.ViewHolder {
        SimpleDraweeView userAvatar;
        TextView userName;
        TextView content;
        SimpleDraweeView image;
        TextView time;
        CommentView comment;
        ImageView collect;
        ImageView play;
        Gif gif;
        ArrayList<Comment> comments = new ArrayList<>();
        Animatable animatable;
        DraweeController controller;
        boolean canPlay = false;
        boolean isInit = false;

        public GifItem(View convertView) {
            super(convertView);
            userAvatar = (SimpleDraweeView) convertView.findViewById(R.id.item_gif_userAvatar);
            userName = (TextView) convertView.findViewById(R.id.item_gif_userName);
            content = (TextView) convertView.findViewById(R.id.item_gif_content);
            image = (SimpleDraweeView) convertView.findViewById(R.id.item_gif_image);
            time = (TextView) convertView.findViewById(R.id.item_gif_time);
            comment = (CommentView) convertView.findViewById(R.id.item_gif_comment);
            collect = (ImageView) convertView.findViewById(R.id.item_gif_collect);
            play = (ImageView) convertView.findViewById(R.id.item_gif_play);
        }

        public void detachedFromWindow() {
            canPlay = false;
            Log.d("detachedFromWindow", gif.getContent());
            if (animatable != null && animatable.isRunning()) {
                Log.d("animatable", "is not null and running");
                animatable.stop();
            }
            play.setVisibility(View.VISIBLE);
        }

        public void attachedToWindow() {

        }

        public void setInfo(Gif gif) {
            this.gif = gif;
            BmobQuery<User> query = new BmobQuery<>();
            query.getObject(gif.getUserId(), new QueryListener<User>() {
                @Override
                public void done(User user, BmobException e) {
                    userAvatar.setImageURI(user.getAvatar().getFileUrl());
                    userName.setText(user.getUsername());
                }
            });
            content.setText(gif.getContent());
            ViewGroup.LayoutParams layoutParams = image.getLayoutParams();
            layoutParams.width = image.getWidth();
            layoutParams.height = image.getWidth() * gif.getHeight() / gif.getWidth();
            image.setLayoutParams(layoutParams);
            controller = Fresco.newDraweeControllerBuilder()
                    .setLowResImageRequest(ImageRequest.fromUri(gif.getFirstImage().getFileUrl()))
                    .setOldController(image.getController())
                    .build();
            image.setController(controller);
            time.setText(gif.getCreatedAt());
//            BmobQuery<Comment> query1 = new BmobQuery<>();
//            query1.addWhereEqualTo("aimId", beauty.getObjectId());
//            query1.findObjects(new FindListener<Comment>() {
//                @Override
//                public void done(List<Comment> list, BmobException e) {
//                    if (e != null) {
//                        comment.setNum(list.size());
//                        comments = (ArrayList<Comment>) list;
//                        comment.setOnClickListener(commentClick);
//                    }
//                }
//            });
            ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
                @Override
                public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable anima) {
                    animatable = anima;
                    if (canPlay)
                        animatable.start();
                }
            };

            controller = Fresco.newDraweeControllerBuilder()
                    .setControllerListener(controllerListener)
                    .setLowResImageRequest(ImageRequest.fromUri(gif.getFirstImage().getFileUrl()))
                    .setImageRequest(ImageRequest.fromUri(gif.getGif().getFileUrl()))
                    .build();


            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    canPlay = true;
                    if (animatable != null) {
                        animatable.start();
                    } else {
                        image.setController(controller);
                    }
                    play.setVisibility(View.INVISIBLE);
                }
            });
        }

        private View.OnClickListener commentClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

            }
        };
        private View.OnClickListener collectClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };

        public Handler setInfo = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Gif gif = (Gif) msg.obj;
                setInfo(gif);
            }
        };

    }
}
