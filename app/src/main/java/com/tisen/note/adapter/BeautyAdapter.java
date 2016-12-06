package com.tisen.note.adapter;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tisen.note.R;
import com.tisen.note.model.Beauty;
import com.tisen.note.model.Comment;
import com.tisen.note.model.User;
import com.tisen.note.view.CommentView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by tisen on 2016/10/24.
 */
public class BeautyAdapter extends BaseAdapter {

    private ArrayList<Beauty> beauties;
    private Activity activity;
    private int width;


    public BeautyAdapter(ArrayList<Beauty> beauties, Activity activity) {
        this.beauties = beauties;
        this.activity = activity;
        width = activity.getWindowManager().getDefaultDisplay().getWidth();
    }

    @Override
    public int getCount() {
        return beauties.size();
    }

    @Override
    public Object getItem(int position) {
        return beauties.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BeautyItem item;
        if (convertView == null) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.item_beauty, parent, false);
            item = new BeautyItem(convertView);
            convertView.setTag(item);
        } else {
            item = (BeautyItem) convertView.getTag();
        }
        Message message = new Message();
        message.obj = beauties.get(position);
        item.setInfo.sendMessage(message);
        return convertView;
    }
    public void reFresh(List<Beauty>beauties){
        this.beauties = (ArrayList<Beauty>) beauties;
        notifyDataSetChanged();
    }


    class BeautyItem {
        SimpleDraweeView userAvatar;
        TextView userName;
        TextView content;
        SimpleDraweeView image;
        TextView time;
        CommentView comment;
        ImageView collect;
        Beauty beauty;
        ArrayList<Comment> comments = new ArrayList<>();

        public BeautyItem(View convertView) {
            userAvatar = (SimpleDraweeView) convertView.findViewById(R.id.item_beauty_userAvatar);
            userName = (TextView) convertView.findViewById(R.id.item_beauty_userName);
            content = (TextView) convertView.findViewById(R.id.item_beauty_content);
            image = (SimpleDraweeView) convertView.findViewById(R.id.item_beauty_image);
            time = (TextView) convertView.findViewById(R.id.item_beauty_time);
            comment = (CommentView) convertView.findViewById(R.id.item_beauty_comment);
            collect = (ImageView) convertView.findViewById(R.id.item_beauty_collect);
        }

        public void setInfo(Beauty beauty) {
            this.beauty = beauty;
            BmobQuery<User> query = new BmobQuery<>();
            query.getObject(beauty.getUserId(), new QueryListener<User>() {
                @Override
                public void done(User user, BmobException e) {
                    userAvatar.setImageURI(user.getAvatar().getFileUrl());
                    userName.setText(user.getUsername());
                }
            });
            content.setText(beauty.getContent());
            image.setImageURI(beauty.getImage().getFileUrl());
            ViewGroup.LayoutParams layoutParams =image.getLayoutParams();
            layoutParams.width = image.getWidth();
            layoutParams.height = image.getWidth()*beauty.getHeight()/beauty.getWidth();
            image.setLayoutParams(layoutParams);
            time.setText(beauty.getCreatedAt());
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
                Beauty beauty = (Beauty) msg.obj;
                setInfo(beauty);
            }
        };
    }
}
