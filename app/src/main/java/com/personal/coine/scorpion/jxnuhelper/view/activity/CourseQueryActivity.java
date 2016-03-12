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
 * 2016/3/12  1.0     huangwei    Creation File
 */
package com.personal.coine.scorpion.jxnuhelper.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.personal.coine.scorpion.jxnuhelper.R;
import com.personal.coine.scorpion.jxnuhelper.bean.Course;
import com.personal.coine.scorpion.jxnuhelper.presenter.CoursePresenter;
import com.personal.coine.scorpion.jxnuhelper.view.ICourseTableView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/12
 */
public class CourseQueryActivity extends AppCompatActivity implements ICourseTableView {


    private OptionsPickerView classPickerView;
    private View queryMasker;
    private CoursePresenter coursePresenter = new CoursePresenter(this);
    private KProgressHUD loadingProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_query);
        initActionBar();
        initData();
        initViews();
    }

    private void initData() {
        loadingProgress = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("正在加载...").setCancellable(false).setAnimationSpeed(2).setDimAmount(0.5f);
        coursePresenter.loadData();
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("课表查询");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initViews() {
        queryMasker = findViewById(R.id.query_vMasker);
        initSearchOptions();

    }

    private void initSearchOptions() {
        classPickerView = new OptionsPickerView(this);
        ArrayList<String> majorList = new ArrayList<>();
        ArrayList<ArrayList<String>> classList = new ArrayList<>();
        majorList.add("软件学院");
        majorList.add("音乐学院");
        majorList.add("国教学院");

        ArrayList<String> tempClassList1 = new ArrayList<>();
        tempClassList1.add("技术一班");
        tempClassList1.add("技术二班");
        tempClassList1.add("技术三班");
        classList.add(tempClassList1);
        ArrayList<String> tempClassList2 = new ArrayList<>();
        tempClassList2.add("民乐一班");
        tempClassList2.add("民乐二班");
        tempClassList2.add("民乐三班");
        classList.add(tempClassList2);
        ArrayList<String> tempClassList3 = new ArrayList<>();
        tempClassList3.add("跨文化一班");
        tempClassList3.add("跨文化二班");
        tempClassList3.add("跨文化三班");
        classList.add(tempClassList3);

        classPickerView.setPicker(majorList, classList, true);
        classPickerView.setTitle("选择班级");
        classPickerView.setCyclic(false, false, false);
        classPickerView.setSelectOptions(0, 0, 0);
        classPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                queryMasker.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_search:
                classPickerView.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Context getThisContext() {
        return this;
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
    public View getContentView() {
        return findViewById(R.id.course_table_view);
    }
}