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
package com.personal.coine.scorpion.jxnuhelper.presenter;

import android.util.Log;

import com.personal.coine.scorpion.jxnuhelper.bean.StuGrade;
import com.personal.coine.scorpion.jxnuhelper.biz.IGradeQueryBiz;
import com.personal.coine.scorpion.jxnuhelper.biz.impl.GradeQueryBizImpl;
import com.personal.coine.scorpion.jxnuhelper.view.IGradeQueryView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.FindStatisticsListener;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/17
 */
public class GradeQueryPresenter {
    private static final String TAG = GradeQueryPresenter.class.getSimpleName();
    private IGradeQueryView mGradeQueryView;
    private IGradeQueryBiz mGradeQueryBiz;

    public GradeQueryPresenter(IGradeQueryView gradeQueryView) {
        this.mGradeQueryView = gradeQueryView;
        this.mGradeQueryBiz = new GradeQueryBizImpl();
    }

    public void loadSemesters() {
        mGradeQueryBiz.loadSemester(mGradeQueryView.loadThisContext(), new FindStatisticsListener() {
            @Override
            public void onSuccess(Object object) {
                JSONArray jsonArray = (JSONArray) object;
                if (jsonArray != null) {
                    List<String> semesterList = new ArrayList<String>();
                    int length = jsonArray.length();
                    try {
                        for (int i = 0; i < length; i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            semesterList.add(jsonObject.getString("semester"));
                        }
                        mGradeQueryView.initSemesterOptions(semesterList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    mGradeQueryView.showSuccess("查询成功，但无数据");
                    Log.d(TAG, "查询成功，但无数据");
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                mGradeQueryView.showError("对不起，查询失败");
                Log.e(TAG, "查询失败" + msg);
            }
        });
    }

    public void loadGrade() {
        mGradeQueryView.showLoading();
        mGradeQueryBiz.loadGrade(mGradeQueryView.loadThisContext(), mGradeQueryView.loadSelectedSemester(), new FindListener<StuGrade>() {
            @Override
            public void onSuccess(List<StuGrade> list) {
                List<String> courseList = new ArrayList<String>();
                List<String> gradeList = new ArrayList<String>();
                List<String> semesterList = new ArrayList<String>();
                for (StuGrade stuGrade : list) {
                    courseList.add(stuGrade.getCourseName());
                    gradeList.add(String.valueOf(stuGrade.getGrade()));
                    semesterList.add(stuGrade.getSemester());
                }
                mGradeQueryView.initGradeRecyclerView(courseList, gradeList, semesterList);
                mGradeQueryView.hideLoading();
            }

            @Override
            public void onError(int i, String s) {
                mGradeQueryView.hideLoading();
                mGradeQueryView.showError("对不起，查询成绩失败");
                Log.e(TAG, "查询成绩失败" + s);
            }
        });
    }
}