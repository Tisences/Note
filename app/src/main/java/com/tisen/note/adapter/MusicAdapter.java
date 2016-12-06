package com.tisen.note.adapter;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tisen.note.R;
import com.tisen.note.model.Comment;
import com.tisen.note.model.Music;
import com.tisen.note.view.CommentView;

import java.util.ArrayList;

/**
 * Created by tisen on 2016/10/25.
 */
public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicItem>{

    private ArrayList<Music>musics;


    @Override
    public MusicItem onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MusicItem(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music,parent,false));
    }

    @Override
    public void onBindViewHolder(MusicItem holder, int position) {
        Message message = new Message();
        message.obj = musics.get(position);
        holder.setInfo.sendMessage(message);
    }

    @Override
    public int getItemCount() {
        return musics.size();
    }

    public class MusicItem extends RecyclerView.ViewHolder{
        SimpleDraweeView userAvatar;
        TextView userName;
        SimpleDraweeView avatar;
        TextView title;
        TextView content;
        TextView time;
        CommentView comment;
        ImageView collect;
        ArrayList<Comment> comments = new ArrayList<>();

        public MusicItem(View itemView) {
            super(itemView);

        }
        private void setInfo(Music music){

        }
        public Handler setInfo = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Music music = (Music) msg.obj;
                setInfo(music);
            }
        };
    }
}
