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
 * 2016/3/16  1.0     huangwei    Creation File
 */
package com.personal.coine.scorpion.jxnuhelper.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.personal.coine.scorpion.jxnuhelper.R;
import com.personal.coine.scorpion.jxnuhelper.adapter.SimpleRecyclerCardAdapter;
import com.personal.coine.scorpion.jxnuhelper.presenter.ExamQueryPresenter;
import com.personal.coine.scorpion.jxnuhelper.view.IExamQueryView;

import java.util.List;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/16
 */
public class ExamQueryActivity extends AppCompatActivity implements IExamQueryView {
    private ExamQueryPresenter examQueryPresenter = new ExamQueryPresenter(this);
    private KProgressHUD loadingProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_query);
        initActionBar();
        loadingProgress = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("正在加载...").setCancellable(false).setAnimationSpeed(2).setDimAmount(0.5f);
        initData();
    }

    private void initData() {
        examQueryPresenter.loadExamInfo();
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("考试查询");
    }

    private void initView(List<String> examTitle, List<String> examDate, List<String> examContent) {
        RecyclerView examList = (RecyclerView) findViewById(R.id.exam_list);
        // 设置LinearLayoutManager
        examList.setLayoutManager(new LinearLayoutManager(this));
        // 设置ItemAnimator
        examList.setItemAnimator(new DefaultItemAnimator());
        // 设置固定大小
        examList.setHasFixedSize(true);
        // 初始化自定义的适配器
        SimpleRecyclerCardAdapter myAdapter = new SimpleRecyclerCardAdapter(this, examTitle, examDate, examContent);
        // 为mRecyclerView设置适配器
        examList.setAdapter(myAdapter);
    }

    @Override
    public Context loadThisContext() {
        return this;
    }

    @Override
    public void appendView(List<String> examTitle, List<String> examDate, List<String> examContent) {
        initView(examTitle, examDate, examContent);
    }

    @Override
    public void showLoading() {
        loadingProgress.show();
    }

    @Override
    public void hideLoading() {
        loadingProgress.dismiss();
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(ExamQueryActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}