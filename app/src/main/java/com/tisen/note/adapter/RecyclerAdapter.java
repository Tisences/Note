package com.tisen.note.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tisen.note.R;

import java.util.ArrayList;

/**
 * Created by tisen on 2016/10/25.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<String>strings;
    public RecyclerAdapter(ArrayList<String> strings) {
        this.strings = strings;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text,parent,false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(strings.get(position));
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        Log.d("DetachedFromWindow",holder.textView.getText().toString());
    }

}
