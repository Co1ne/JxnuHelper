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

import com.personal.coine.scorpion.jxnuhelper.bean.SchoolCalendar;
import com.personal.coine.scorpion.jxnuhelper.biz.ISchoolCalendarBiz;
import com.personal.coine.scorpion.jxnuhelper.biz.impl.SchoolCalendarBizImpl;
import com.personal.coine.scorpion.jxnuhelper.view.ICalendarView;

import java.util.List;

import cn.bmob.v3.listener.FindListener;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/17
 */
public class SchoolCalendarPresenter {
    private ICalendarView mCalendarView;
    private ISchoolCalendarBiz mCalendarBiz;

    public SchoolCalendarPresenter(ICalendarView calendarView) {
        this.mCalendarView = calendarView;
        this.mCalendarBiz = new SchoolCalendarBizImpl();
    }

    public void loadEventList() {
        mCalendarView.showLoading();
        mCalendarBiz.loadEventList(mCalendarView.loadThisContext(), new FindListener<SchoolCalendar>() {
            @Override
            public void onSuccess(List<SchoolCalendar> list) {
                mCalendarView.renderDate(list);
                mCalendarView.hideLoading();
            }

            @Override
            public void onError(int i, String s) {
                mCalendarView.hideLoading();
                mCalendarView.showError("对不起，获取校历失败");
            }
        });
    }
}