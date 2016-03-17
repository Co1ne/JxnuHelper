/*
 * Copyright(c) Runsdata Technologies Co., Ltd.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Runsdata
 * Technologies Co., Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with Runsdata.
 * For more information about Runsdata, welcome to http://www.runsdata.com
 *
 * Revision History
 * Date     Version     Name        Description
 * 2016/3/17  1.0     huangwei    Creation File
 */
package com.personal.coine.scorpion.jxnuhelper.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.personal.coine.scorpion.jxnuhelper.R;
import com.personal.coine.scorpion.jxnuhelper.bean.FeedBack;

import cn.bmob.v3.listener.SaveListener;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/17
 */
public class FeedBackActivity extends AppCompatActivity {
    private static final String TAG = FeedBackActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        initActionBar();
        initViews();
    }

    private void initViews() {
        final AppCompatEditText feedbackText = (AppCompatEditText) findViewById(R.id.feedback_text);
        findViewById(R.id.confirm_feedback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedBack newFeedback = new FeedBack(feedbackText.getText().toString());
                newFeedback.save(FeedBackActivity.this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(FeedBackActivity.this, "反馈成功!感谢!", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(FeedBackActivity.this, "对不起!反馈失败", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "反馈失败" + s);
                    }
                });
            }
        });
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("意见反馈");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}