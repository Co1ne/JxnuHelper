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
package com.personal.coine.scorpion.jxnuhelper.biz.impl;

import android.content.Context;

import com.personal.coine.scorpion.jxnuhelper.bean.MyUser;
import com.personal.coine.scorpion.jxnuhelper.bean.StuGrade;
import com.personal.coine.scorpion.jxnuhelper.biz.IGradeQueryBiz;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.FindStatisticsListener;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/17
 */
public class GradeQueryBizImpl implements IGradeQueryBiz {
    @Override
    public void loadSemester(Context context, FindStatisticsListener findListener) {
        BmobQuery<StuGrade> query = new BmobQuery<>();
        query.addWhereEqualTo("student", BmobUser.getCurrentUser(context, MyUser.class));
        query.addQueryKeys("semester");
        query.groupby(new String[]{"semester"});
        query.findStatistics(context, StuGrade.class, findListener);
    }

    @Override
    public void loadGrade(Context context, String selectedSemester, FindListener<StuGrade> findListener) {
        BmobQuery<StuGrade> query = new BmobQuery<>();
        query.addWhereEqualTo("student", BmobUser.getCurrentUser(context, MyUser.class));
        query.addWhereEqualTo("semester", selectedSemester);
        query.findObjects(context,findListener);
    }
}