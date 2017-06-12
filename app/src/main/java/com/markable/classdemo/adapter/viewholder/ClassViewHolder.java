package com.markable.classdemo.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.markable.classdemo.R;
import com.markable.classdemo.base.BaseViewHolder;
import com.markable.classdemo.beans.RandomClass;

import butterknife.Bind;

import static android.R.attr.data;

/**
 * Created by Markable on 2017/6/11.
 * Github: https://github.com/ddz-mark
 * Info:
 */

public class ClassViewHolder extends BaseViewHolder {


    @Bind(R.id.image)
    ImageView mImage;
    @Bind(R.id.id)
    TextView mId;
    @Bind(R.id.name)
    TextView mName;
    @Bind(R.id.sex)
    TextView mSex;
    @Bind(R.id.classId)
    TextView mClassId;
    @Bind(R.id.loss_class)
    TextView mLossClass;

    public ClassViewHolder(Context context, ViewGroup root, OnRecyclerViewListener onRecyclerViewListener) {
        super(context, root, R.layout.activity_class_item, onRecyclerViewListener);
    }

    @Override
    public void bindData(Object o) {
        RandomClass.DataBean dataBean = (RandomClass.DataBean) o;
        if (dataBean.sSex.equals("女")) {
            mImage.setImageResource(R.mipmap.girl);
        } else {
            mImage.setImageResource(R.mipmap.student);
        }
        mId.setText("学号：" + dataBean.sNum);
        mName.setText("姓名：" + dataBean.sName);
        mSex.setText("性别：" + dataBean.sSex);
        mClassId.setText("班级：" + dataBean.sClass);
    }
}
