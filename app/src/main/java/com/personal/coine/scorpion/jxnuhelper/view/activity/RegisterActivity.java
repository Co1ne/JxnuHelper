package com.personal.coine.scorpion.jxnuhelper.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.personal.coine.scorpion.jxnuhelper.R;
import com.personal.coine.scorpion.jxnuhelper.presenter.RegisterPresenter;
import com.personal.coine.scorpion.jxnuhelper.utils.TimerCount;
import com.personal.coine.scorpion.jxnuhelper.view.IRegisterView;

public class RegisterActivity extends Activity implements IRegisterView {

    private EditText phoneNumber;
    private EditText SMSCode;
    private EditText password;
    private ProgressBar registerProgress;
    private RegisterPresenter registerPresenter = new RegisterPresenter(this);
    private BootstrapButton actionRegister;
    private Button requestSMSCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
    }

    private void initViews() {
        phoneNumber = (EditText) findViewById(R.id.et_register_phone_number);
        SMSCode = (EditText) findViewById(R.id.et_sms_code);
        password = (EditText) findViewById(R.id.et_register_password);
        registerProgress = (ProgressBar) findViewById(R.id.register_progress);
        actionRegister = (BootstrapButton) findViewById(R.id.btn_register_action_register);
        actionRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPresenter.signAndLogin();
            }
        });
        actionRegister.setBackgroundResource(R.drawable.bg_corner_button_passive);
        actionRegister.setClickable(false);
        //获取验证码按钮
        requestSMSCode = (Button) findViewById(R.id.btn_request_sms_code);
        requestSMSCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPresenter.requestSMSCode();
                //注册按钮状态
                actionRegister.setClickable(true);
                actionRegister.setBackgroundResource(R.drawable.bg_corner_button_active);
                //计时获取验证码
                TimerCount timerCount = new TimerCount(60000, 1000, requestSMSCode);
                timerCount.start();
            }
        });
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public String getSMSCode() {
        return SMSCode.getText().toString();
    }

    @Override
    public void showLoading() {
        registerProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        registerProgress.setVisibility(View.GONE);
    }

    @Override
    public void loginAndToMain() {
        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void showFailedError(String description) {
        Toast.makeText(RegisterActivity.this, "对不起," + description, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
