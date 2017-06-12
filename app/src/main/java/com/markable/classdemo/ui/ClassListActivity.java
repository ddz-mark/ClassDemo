package com.markable.classdemo.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.markable.classdemo.R;
import com.markable.classdemo.adapter.ClassAdapter;
import com.markable.classdemo.base.BaseActivity;
import com.markable.classdemo.base.BaseViewHolder;
import com.markable.classdemo.beans.LossStudent;
import com.markable.classdemo.beans.RandomClass;
import com.markable.classdemo.common.RetrofitSingleton;
import com.markable.classdemo.utils.DateUtils;
import com.markable.classdemo.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by Markable on 2017/6/10.
 * Github: https://github.com/ddz-mark
 * Info:
 */

public class ClassListActivity extends BaseActivity {

    @Bind(R.id.class_recyclerView)
    RecyclerView mClassRecyclerView;
    @Bind(R.id.topic_question_back)
    ImageButton mTopicQuestionBack;
    @Bind(R.id.fab)
    FloatingActionButton mFab;

    private List<RandomClass.DataBean> datas = new ArrayList<>();
    private List<Integer> lossDatas = new ArrayList<>();
    private String classId = null;
    private int num = 0;
    private ClassAdapter mAdapter;
    private StringBuilder list;

    public static Intent getIntentStartActivity(Context context, String classId, int num) {
        Intent intent = new Intent(context, ClassListActivity.class);
        intent.putExtra("classId", classId);
        intent.putExtra("num", num);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
        setStatusBarColor(R.color.defaults);
        ButterKnife.bind(this);
        initView();
        list = new StringBuilder();
        fetchData();
    }

    private void initView() {
        mClassRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mClassRecyclerView.setHasFixedSize(false);
        mAdapter = new ClassAdapter(getApplicationContext(), datas);
        mClassRecyclerView.setAdapter(mAdapter);

        classId = getIntent().getStringExtra("classId");
        num = getIntent().getIntExtra("num", -1);
        mTopicQuestionBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mAdapter.setOnRecyclerViewListener(new BaseViewHolder.OnRecyclerViewListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView tv = (TextView) view.findViewById(R.id.loss_class);
                if (tv.getVisibility() == View.GONE) {
                    tv.setVisibility(View.VISIBLE);
                    if (!lossDatas.contains(datas.get(position))) {
                        lossDatas.add(datas.get(position).sNum);
                    }
                } else {
                    tv.setVisibility(View.GONE);
                    if (lossDatas.contains(datas.get(position))) {
                        lossDatas.remove(position);
                    }
                }
            }

            @Override
            public boolean onItemLongClick(View view, int position) {
                return false;
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExitDialog();
            }
        });

    }

    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("是否上传旷课名单");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                putLoss();
            }
        });
        builder.show();
    }

    private void putLoss() {
        //暂时在这里进行判断,后面改
        if (null != list) {
            list.delete(0, list.length());
        }
        for (int i = 0; i < lossDatas.size(); i++) {
            //拼接字符串
            if (i == lossDatas.size() - 1) {
                list.append(lossDatas.get(i).toString());
            } else {
                list.append(lossDatas.get(i).toString()).append(",");
            }
        }

        Logger.d(list.toString() + "   " + DateUtils.getUnixStamp());

        RetrofitSingleton.getInstance().getLossStudent(list.toString(), DateUtils.getUnixStamp())
                .subscribe(new Observer<LossStudent>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            RetrofitSingleton.disposeFailureInfo(e, getApplication());
                        } catch (Exception e1) {
                            Logger.e(e1.getMessage());
                        }
                    }

                    @Override
                    public void onNext(LossStudent student) {
                        if (student.code == 100) {
                            ToastUtil.showToast(ClassListActivity.this, "旷课名单上传成功");
                            startActivity(new Intent(ClassListActivity.this, MainActivity.class));
                        }
                    }
                });
    }

    private void fetchData() {

        RetrofitSingleton.getInstance().getRandomClass(classId, num)
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {

                    }
                })
                .subscribe(new Observer<RandomClass>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            RetrofitSingleton.disposeFailureInfo(e, getApplication());
                        } catch (Exception e1) {
                            Logger.e(e1.getMessage());
                        }
                    }

                    @Override
                    public void onNext(RandomClass aClass) {
                        datas.clear();
                        datas.addAll(aClass.data);
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }
}
