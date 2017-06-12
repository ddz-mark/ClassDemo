package com.markable.classdemo.adapter.viewholder;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.markable.classdemo.R;
import com.markable.classdemo.base.BaseViewHolder;
import com.markable.classdemo.beans.Depart;

import butterknife.Bind;

/**
 * Created by Markable on 2017/6/10.
 * Github: https://github.com/ddz-mark
 * Info:
 */

public class MainViewHolder extends BaseViewHolder {


    @Bind(R.id.image)
    ImageView mImage;
    @Bind(R.id.name)
    TextView mName;
    @Bind(R.id.depart)
    TextView mDepart;

    public MainViewHolder(Context context, ViewGroup root, OnRecyclerViewListener onRecyclerViewListener) {
        super(context, root, R.layout.activity_main_item, onRecyclerViewListener);
    }

    @Override
    public void bindData(Object o) {
        final Depart data = (Depart) o;
//        ImageLoader.load(getContext(),data.getImage(),imageItem);
        mName.setText("专业："+data.sName);
        mDepart.setText("学院："+data.depart);
    }
}
