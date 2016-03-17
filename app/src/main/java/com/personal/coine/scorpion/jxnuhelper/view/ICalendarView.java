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
package com.personal.coine.scorpion.jxnuhelper.view;

import android.content.Context;

import com.personal.coine.scorpion.jxnuhelper.bean.SchoolCalendar;

import java.util.List;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/17
 */
public interface ICalendarView {
    void showLoading();

    Context loadThisContext();

    void hideLoading();

    void showError(String errorMsg);

    void renderDate(List<SchoolCalendar> calendarList);
}