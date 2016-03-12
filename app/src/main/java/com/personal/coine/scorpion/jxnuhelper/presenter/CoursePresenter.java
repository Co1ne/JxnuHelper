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
package com.personal.coine.scorpion.jxnuhelper.presenter;

import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.personal.coine.scorpion.jxnuhelper.R;
import com.personal.coine.scorpion.jxnuhelper.bean.Course;
import com.personal.coine.scorpion.jxnuhelper.bean.MyUser;
import com.personal.coine.scorpion.jxnuhelper.biz.ICourseBiz;
import com.personal.coine.scorpion.jxnuhelper.biz.impl.CourseBizImpl;
import com.personal.coine.scorpion.jxnuhelper.view.ICourseTableView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/12
 */
public class CoursePresenter {
    private static final String TAG = CoursePresenter.class.getSimpleName();
    private ICourseTableView courseTableView;
    private ICourseBiz courseBiz;

    public CoursePresenter(ICourseTableView courseView) {
        this.courseTableView = courseView;
        courseBiz = new CourseBizImpl();
    }

    public void loadData() {
        courseTableView.showLoading();
        courseBiz.loadCourseData(courseTableView.getThisContext(), BmobUser.getCurrentUser(courseTableView.getThisContext(), MyUser.class).getStuAcademy(), new FindListener<Course>() {
            @Override
            public void onSuccess(List<Course> courseList) {
                courseTableView.hideLoading();
                courseList.get(0).getClassroom();
                Log.d(TAG, "课程信息获取成功");
                initWeekPanel(courseList);
            }

            @Override
            public void onError(int i, String s) {
                courseTableView.hideLoading();
                Toast.makeText(courseTableView.getThisContext(), "信息获取失败", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "课程信息获取失败:" + s);
            }
        });
    }

    private void initWeekPanel(List<Course> courseList) {
        List<Course> mondayList = new ArrayList<>();
        List<Course> tuesdayList = new ArrayList<>();
        List<Course> wednesdayList = new ArrayList<>();
        List<Course> thursdayList = new ArrayList<>();
        List<Course> fridayList = new ArrayList<>();
        List<Course> saturdayList = new ArrayList<>();
        List<Course> sundayList = new ArrayList<>();
        for (int i = 0; i < courseList.size(); i++) {
            switch (courseList.get(i).getDayOfWeek()) {
                case 1:
                    mondayList.add(courseList.get(i));
                    break;
                case 2:
                    tuesdayList.add(courseList.get(i));
                    break;
                case 3:
                    wednesdayList.add(courseList.get(i));
                    break;
                case 4:
                    thursdayList.add(courseList.get(i));
                    break;
                case 5:
                    fridayList.add(courseList.get(i));
                    break;
                case 6:
                    saturdayList.add(courseList.get(i));
                    break;
                case 7:
                    sundayList.add(courseList.get(i));
                    break;
            }
        }
        List courseData[] = new ArrayList[7];
        courseData[0] = mondayList;
        courseData[1] = tuesdayList;
        courseData[2] = wednesdayList;
        courseData[3] = thursdayList;
        courseData[4] = fridayList;
        courseData[5] = saturdayList;
        courseData[6] = sundayList;
        LinearLayout weekPanels[] = new LinearLayout[7];
        for (int i = 0; i < weekPanels.length; i++) {
            weekPanels[i] = (LinearLayout) courseTableView.getContentView().findViewById(R.id.weekPanel_1 + i);
            doInitPanel(weekPanels[i], courseData[i]);
        }
    }

    private void doInitPanel(LinearLayout ll, List<Course> data) {
        int itemHeight = courseTableView.getThisContext().getResources().getDimensionPixelSize(R.dimen.weekItemHeight);
        int marTop = courseTableView.getThisContext().getResources().getDimensionPixelSize(R.dimen.weekItemMarTop);
        int marLeft = courseTableView.getThisContext().getResources().getDimensionPixelSize(R.dimen.weekItemMarLeft);
        if (ll == null || data == null || data.size() < 1) return;
        Log.i("Msg", "初始化面板");
        Course pre = data.get(0);
        for (int i = 0; i < data.size(); i++) {
            Course c = data.get(i);
            TextView tv = new TextView(courseTableView.getThisContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    itemHeight * c.getStep() + marTop * (c.getStep() - 1));
            if (i > 0) {
                lp.setMargins(marLeft, (c.getStart() - (pre.getStart() + pre.getStep())) * (itemHeight + marTop) + marTop, 0, 0);
            } else {
                lp.setMargins(marLeft, (c.getStart() - 1) * (itemHeight + marTop) + marTop, 0, 0);
            }
            tv.setLayoutParams(lp);
            tv.setGravity(Gravity.TOP);
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            tv.setTextSize(12);
            tv.setTextColor(courseTableView.getThisContext().getResources().getColor(R.color.courseTextColor));
            tv.setText(c.getCourseName() + "\n" + c.getClassroom() + "\n" + c.getTeacher());
            tv.setBackgroundColor(courseTableView.getThisContext().getResources().getColor(R.color.actionBarBg));
//            tv.setBackgroundResource(R.drawable.tvshape);
            ll.addView(tv);
            pre = c;
        }
    }
}