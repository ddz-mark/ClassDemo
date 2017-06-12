package com.markable.classdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.markable.classdemo.R;
import com.markable.classdemo.base.BaseActivity;
import com.markable.classdemo.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Markable on 2017/6/12.
 * Github: https://github.com/ddz-mark
 * Info:
 */

public class UserActivity extends BaseActivity {

    @Bind(R.id.name)
    EditText mName;
    @Bind(R.id.password)
    EditText mPassword;
    @Bind(R.id.login)
    Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login)
    public void onClick() {
        if (mName.getText().toString().equals("admin") && mPassword.getText().toString().equals("123456")) {
            ToastUtil.showToast(this, "登录成功");
            Intent intent = new Intent(UserActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            ToastUtil.showToast(this, "请正确填写帐号密码");
        }
    }
}
