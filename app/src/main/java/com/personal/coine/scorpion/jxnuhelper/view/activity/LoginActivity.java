package com.personal.coine.scorpion.jxnuhelper.view.activity;

import android.app.Activity;
import android.os.Bundle;

import com.personal.coine.scorpion.jxnuhelper.R;
import com.personal.coine.scorpion.jxnuhelper.presenter.LoginPresenter;
import com.personal.coine.scorpion.jxnuhelper.view.ILoginView;

import cn.bmob.v3.BmobUser;

public class LoginActivity extends Activity implements ILoginView {
    private LoginPresenter loginPresenter = new LoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void toMainActivity(BmobUser user) {

    }

    @Override
    public void showFailedError() {

    }
}
