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
package com.personal.coine.scorpion.jxnuhelper.presenter;

import android.util.Log;

import com.personal.coine.scorpion.jxnuhelper.bean.ExamSchedule;
import com.personal.coine.scorpion.jxnuhelper.biz.IExamQueryBiz;
import com.personal.coine.scorpion.jxnuhelper.biz.impl.ExamQueryBizImpl;
import com.personal.coine.scorpion.jxnuhelper.view.IExamQueryView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.listener.FindListener;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/16
 */
public class ExamQueryPresenter {
    private static final String TAG = ExamQueryPresenter.class.getSimpleName();
    private IExamQueryView mExamQueryView;
    private IExamQueryBiz mExamQueryBiz;

    public ExamQueryPresenter(IExamQueryView examQueryView) {
        this.mExamQueryView = examQueryView;
        this.mExamQueryBiz = new ExamQueryBizImpl();
    }

    public void loadExamInfo() {
        mExamQueryView.showLoading();
        mExamQueryBiz.loadExamInfo(mExamQueryView.loadThisContext(), new FindListener<ExamSchedule>() {
            @Override
            public void onSuccess(List<ExamSchedule> list) {
                mExamQueryView.hideLoading();
                List<String> examTitle = new ArrayList<String>();
                List<String> examDate = new ArrayList<String>();
                List<String> examContent = new ArrayList<String>();
                for (ExamSchedule examSchedule : list) {
                    examTitle.add(examSchedule.getSubject());
                    examDate.add(examSchedule.getExamDate().getDate());
                    examContent.add(examSchedule.getExamContent());
                }
                mExamQueryView.appendView(examTitle, examDate, examContent);
            }

            @Override
            public void onError(int i, String s) {
                mExamQueryView.hideLoading();
                mExamQueryView.showError("对不起，查询考试安排失败");
                Log.e(TAG, "查询考试安排失败:" + s);
            }
        });
    }
}