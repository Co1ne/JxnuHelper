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

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.personal.coine.scorpion.jxnuhelper.R;
import com.personal.coine.scorpion.jxnuhelper.adapter.SimpleRecyclerCardAdapter;
import com.personal.coine.scorpion.jxnuhelper.presenter.GradeQueryPresenter;
import com.personal.coine.scorpion.jxnuhelper.view.IGradeQueryView;

import java.util.List;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/17
 */
public class GradeQueryActivity extends AppCompatActivity implements IGradeQueryView {
    private GradeQueryPresenter gradeQueryPresenter = new GradeQueryPresenter(this);
    private String selectedSemester = "";
    private KProgressHUD loadingProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_query);
        loadingProgress = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("正在加载...").setCancellable(false).setAnimationSpeed(2).setDimAmount(0.5f);
        initActionBar();
        gradeQueryPresenter.loadSemesters();
    }


    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("成绩查询");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    void initGradeList(List<String> courseList, List<String> gradeList, List<String> semesterList) {
        RecyclerView gradeRecyclerView = (RecyclerView) findViewById(R.id.grade_list);
        gradeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 设置ItemAnimator
        gradeRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置固定大小
        gradeRecyclerView.setHasFixedSize(true);
        // 初始化自定义的适配器
        SimpleRecyclerCardAdapter adapter = new SimpleRecyclerCardAdapter(this, courseList, gradeList, semesterList);
        gradeRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.reselect_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()){
        case android.R.id.home:
            finish();
            break;
        case R.id.menu_reselect:
            gradeQueryPresenter.loadSemesters();
            break;
    }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Context loadThisContext() {
        return this;
    }

    @Override
    public void initSemesterOptions(List<String> semesterList) {
        final String[] semesterArray = new String[semesterList.size()];
        for (int i = 0; i < semesterList.size(); i++) {
            semesterArray[i] = semesterList.get(i);
        }
        new AlertDialog.Builder(this).setSingleChoiceItems(semesterArray, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedSemester = semesterArray[which];
                gradeQueryPresenter.loadGrade();
                dialog.dismiss();
            }
        }).show();
    }

    @Override
    public void showSuccess(String successMsg) {
        Toast.makeText(GradeQueryActivity.this, successMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(GradeQueryActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
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
    public String loadSelectedSemester() {
        return selectedSemester;
    }

    @Override
    public void initGradeRecyclerView(List<String> courseList, List<String> gradeList, List<String> semesterList) {
        initGradeList(courseList, gradeList, semesterList);
    }
}