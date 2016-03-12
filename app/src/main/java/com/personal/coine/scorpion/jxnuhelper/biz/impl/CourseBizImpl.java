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
package com.personal.coine.scorpion.jxnuhelper.biz.impl;

import android.content.Context;

import com.personal.coine.scorpion.jxnuhelper.bean.Academy;
import com.personal.coine.scorpion.jxnuhelper.bean.Course;
import com.personal.coine.scorpion.jxnuhelper.biz.ICourseBiz;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/12
 */
public class CourseBizImpl implements ICourseBiz {
    @Override
    public void loadCourseData(Context context, Academy belongAcademy, FindListener<Course> findListener) {
        BmobQuery<Course> query = new BmobQuery<>();
        query.addWhereEqualTo("belongAcademy",belongAcademy);
        query.order("dayOfWeek");
        query.findObjects(context,findListener);
    }
}