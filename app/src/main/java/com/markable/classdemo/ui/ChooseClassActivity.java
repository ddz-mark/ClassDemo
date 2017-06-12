package com.markable.classdemo.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.markable.classdemo.R;
import com.markable.classdemo.base.BaseActivity;
import com.markable.classdemo.base.FlowLayout;
import com.markable.classdemo.beans.ClassList;
import com.markable.classdemo.beans.Depart;
import com.markable.classdemo.common.RetrofitSingleton;
import com.markable.classdemo.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.functions.Action0;
import rx.functions.Func1;


/**
 * Created by Markable on 2017/6/12.
 * Github: https://github.com/ddz-mark
 * Info:
 */

public class ChooseClassActivity extends BaseActivity {

    @Bind(R.id.topic_choose_tvField)
    TextView mTopicChooseTvField;
    @Bind(R.id.topic_choose_view1)
    View mTopicChooseView1;
    @Bind(R.id.topic_choose_fl)
    FlowLayout mTopicChooseFl;
    @Bind(R.id.topic_choose_flFirst)
    FlowLayout mTopicChooseFlFirst;
    @Bind(R.id.topic_choose_flSecond)
    FlowLayout mTopicChooseFlSecond;
    @Bind(R.id.topic_choose_flThird)
    FlowLayout mTopicChooseFlThird;
    @Bind(R.id.toolbar_back)
    ImageButton mToolbarBack;
    @Bind(R.id.topic_choose_toolbar_tvFinish)
    TextView mTopicChooseToolbarTvFinish;
    @Bind(R.id.topic_toolbar)
    Toolbar mTopicToolbar;

    // 记录一级领域按下去的Button，待其他按下去，好恢复状态
    private TextView buttonStatus = null;
    // 记录当前已选择的学院以及专业信息
    private Depart depart = null;
    private Depart special = null;
    // 记录已选择的班级信息
    private ArrayList<ClassList> mClassList = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_choose_field);
        setStatusBarColor(R.color.defaults);
        ButterKnife.bind(this);
        init();
        loadFirstField();
    }

    private void init() {
        depart = new Depart();
        special = new Depart();
        mClassList = new ArrayList<>();

        mToolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTopicChooseToolbarTvFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClassList.size() != 0) {
                    Intent intent = new Intent();
                    intent.putExtra("data", mClassList);
                    setResult(100, intent);
                    finish();
                } else {
                    ToastUtil.showToast(getApplicationContext(), "请选择班级");
                }
            }
        });
    }

    /**
     * 加载学院列表
     */
    private void loadFirstField() {

        mTopicChooseFlFirst.removeAllViews();

        RetrofitSingleton.getInstance().getDepartList()
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {

                    }
                })
                .flatMap(new Func1<ArrayList<Depart>, Observable<Depart>>() {
                    @Override
                    public Observable<Depart> call(ArrayList<Depart> departs) {
                        return Observable.from(departs);
                    }
                })
                .distinct()
                .subscribe(new Observer<Depart>() {
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
                    public void onNext(Depart depart) {
                        addFirstFlowLayout(depart);
                    }
                });
    }


    /**
     * 加载专业列表
     * 方式同一级
     */
    private void loadSecondField(String depart) {

        mTopicChooseFlSecond.removeAllViews();
        RetrofitSingleton.getInstance().getSpecList(depart)
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {

                    }
                })
                .flatMap(new Func1<ArrayList<Depart>, Observable<Depart>>() {
                    @Override
                    public Observable<Depart> call(ArrayList<Depart> departs) {
                        return Observable.from(departs);
                    }
                })
                .subscribe(new Observer<Depart>() {
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
                    public void onNext(Depart depart) {
                        addSecondFlowLayout(depart);
                    }
                });
    }

    /**
     * 加载班级列表
     * 方式同一级
     */
    private void loadThirdField(String special) {

        mTopicChooseFlThird.removeAllViews();
        RetrofitSingleton.getInstance().getClassList(special)
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {

                    }
                })
                .flatMap(new Func1<ArrayList<ClassList>, Observable<ClassList>>() {
                    @Override
                    public Observable<ClassList> call(ArrayList<ClassList> lists) {
                        return Observable.from(lists);
                    }
                })
                .subscribe(new Observer<ClassList>() {
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
                    public void onNext(ClassList list) {
                        addThirdFlowLayout(list);
                    }
                });
    }

    /**
     * 更新一级领域UI
     */
    private void addFirstFlowLayout(final Depart depart) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.topic_flowlayout_field, null);
        final Button button = (Button) frameLayout.findViewById(R.id.topic_choose_btField);
        button.setBackgroundResource(R.drawable.shape_topic_choose_1);
        button.setText(depart.depart);
        mTopicChooseFlFirst.addView(frameLayout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFirstUI();
                button.setBackgroundResource(R.drawable.shape_topic_choose_2);
                button.setTextColor(Color.WHITE);
                loadSecondField(depart.depart);
                buttonStatus = button;
            }
        });
    }

    /**
     * 更新二级领域UI
     */
    private void addSecondFlowLayout(final Depart spcial) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.topic_flowlayout_field, null);
        final Button button = (Button) frameLayout.findViewById(R.id.topic_choose_btField);
        button.setBackgroundResource(R.drawable.shape_topic_choose_1);
        button.setText(spcial.sName);
        mTopicChooseFlSecond.addView(frameLayout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFirstUI();
                button.setBackgroundResource(R.drawable.shape_topic_choose_2);
                button.setTextColor(Color.WHITE);
                loadThirdField(spcial.sName);
                buttonStatus = button;
            }
        });
    }

    /**
     * 更新三级领域UI
     */
    private void addThirdFlowLayout(final ClassList classList) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.topic_flowlayout_field, null);
        final Button button = (Button) frameLayout.findViewById(R.id.topic_choose_btField);
        button.setBackgroundResource(R.drawable.shape_topic_choose_1);
        button.setText(classList.cName);
        mTopicChooseFlThird.addView(frameLayout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFirstUI();
                button.setBackgroundResource(R.drawable.shape_topic_choose_2);
                button.setTextColor(Color.WHITE);
                buttonStatus = button;
                showChooseField(classList);
            }
        });
    }

    private void showChooseField(ClassList classList) {
        mTopicChooseFl.removeAllViews();
        mClassList.clear();
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.topic_flowlayout_field, null);
        //button未知问题 目前用textView改好了
        Button button = (Button) frameLayout.findViewById(R.id.topic_choose_btField);
        button.setVisibility(View.GONE);
        TextView textView = (TextView) frameLayout.findViewById(R.id.topic_choosed_btField);
        textView.setVisibility(View.VISIBLE);
        textView.setBackgroundResource(R.drawable.shape_topic_choose_1);
        textView.setTextColor(ContextCompat.getColor(this, R.color.topicChooseFont1));
        textView.setText(classList.cName);
        mTopicChooseFl.addView(frameLayout);
        mClassList.add(classList);
    }

    /**
     * 重置一级领域的UI
     */
    private void resetFirstUI() {
        if (buttonStatus != null) {
            buttonStatus.setTextColor(getResources().getColor(R.color.topicChooseFont));
            buttonStatus.setBackgroundResource(R.drawable.shape_topic_choose_1);
        }
    }
}
