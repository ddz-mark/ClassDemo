package com.markable.classdemo.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.markable.classdemo.R;
import com.markable.classdemo.base.BaseActivity;
import com.markable.classdemo.base.FlowLayout;
import com.markable.classdemo.beans.ClassList;
import com.markable.classdemo.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Markable on 2017/6/11.
 * Github: https://github.com/ddz-mark
 * Info:
 */

public class RandomClassActivity extends BaseActivity {

    @Bind(R.id.topic_question_publish)
    Button mTopicQuestionPublish;
    @Bind(R.id.topic_question_etTitle)
    EditText mTopicQuestionEtTitle;
    @Bind(R.id.topic_question_tvWordNum)
    TextView mTopicQuestionTvWordNum;
    @Bind(R.id.topic_question_choose_category)
    TextView mTopicQuestionChooseCategory;
    @Bind(R.id.topic_question_btAddCategory)
    TextView mTopicQuestionBtAddCategory;
    @Bind(R.id.topic_question_flCategory)
    FlowLayout mTopicQuestionFlCategory;
    @Bind(R.id.topic_question_back)
    ImageButton mTopicQuestionBack;
    @Bind(R.id.topic_toolbar)
    Toolbar mTopicToolbar;

    private ArrayList<ClassList> listCFs = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_class);
        setStatusBarColor(R.color.defaults);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

    }

    private void updateFlowLayout() {

        mTopicQuestionFlCategory.removeViews(0, mTopicQuestionFlCategory.getChildCount() - 1);

        if (listCFs != null) {
            for (int i = 0; i < listCFs.size(); i++) {
                final ClassList chooseField = listCFs.get(i);
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.topic_framelayout, null);
                //button 未知背景问题
                TextView button = (TextView) frameLayout.findViewById(R.id.topic_question_btCategory);
                button.setBackgroundResource(R.drawable.shape_topic_choose_1);
                button.setText(listCFs.get(i).cName);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listCFs.remove(chooseField);
                        mTopicQuestionFlCategory.removeView(frameLayout);
                        if (mTopicQuestionFlCategory.getChildCount() == 1) {
                            listCFs.clear();
                        }
                    }
                });
                mTopicQuestionFlCategory.addView(frameLayout, i);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 100) {
            try {
                listCFs = (ArrayList<ClassList>) data.getSerializableExtra("data");
                updateFlowLayout();
            } catch (Exception e) {
                Logger.d(e.getMessage());
            }
        }
    }

    @OnClick({R.id.topic_question_publish, R.id.topic_question_btAddCategory, R.id.topic_question_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.topic_question_publish:
                if (listCFs.size() == 0 && mTopicQuestionEtTitle.getText().toString().equals("")) {
                    ToastUtil.showToast(RandomClassActivity.this, "请正确填写信息");
                } else {
                    Intent in = ClassListActivity.getIntentStartActivity(this, listCFs.get(0).cName,
                            Integer.parseInt(mTopicQuestionEtTitle.getText().toString()));
                    startActivity(in);
                }
                break;
            case R.id.topic_question_btAddCategory:
                Intent intent = new Intent(RandomClassActivity.this, ChooseClassActivity.class);
                startActivityForResult(intent, 100);
                break;
            case R.id.topic_question_back:
                finish();
                break;
            default:
                break;
        }
    }


}
