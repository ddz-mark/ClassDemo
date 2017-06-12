package com.markable.classdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.markable.classdemo.base.BaseViewHolder;
import com.markable.classdemo.adapter.viewholder.MainViewHolder;
import com.markable.classdemo.beans.Depart;

import java.util.ArrayList;

/**
 * Created by Markable on 2017/6/10.
 * Github: https://github.com/ddz-mark
 * Info:
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Depart> datas;
    private BaseViewHolder.OnRecyclerViewListener onRecyclerViewListener;
    private Context context;

    public MainAdapter(Context context, ArrayList<Depart> datas) {
        this.context = context;
        this.datas = datas;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(context,parent,onRecyclerViewListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BaseViewHolder) holder).bindData(datas.get(position));
    }



    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }



    public void setOnRecyclerViewListener(BaseViewHolder.OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }
}
