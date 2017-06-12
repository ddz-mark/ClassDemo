package com.markable.classdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.markable.classdemo.adapter.viewholder.ClassViewHolder;
import com.markable.classdemo.base.BaseViewHolder;
import com.markable.classdemo.beans.RandomClass;
import com.markable.classdemo.beans.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Markable on 2017/6/11.
 * Github: https://github.com/ddz-mark
 * Info:
 */

public class ClassAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<RandomClass.DataBean> datas;
    private BaseViewHolder.OnRecyclerViewListener onRecyclerViewListener;
    private Context context;

    public ClassAdapter(Context context, List<RandomClass.DataBean> datas) {
        this.context = context;
        this.datas = datas;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ClassViewHolder(context,parent,onRecyclerViewListener);
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
