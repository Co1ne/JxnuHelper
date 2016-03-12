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
package com.personal.coine.scorpion.jxnuhelper.bean;

import cn.bmob.v3.BmobObject;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/12
 */
public class Course extends BmobObject {
    private Integer start, step, dayOfWeek; //开始上课节次， 一共几节课, 星期几
    private String courseName, classroom, teacher, courseNumber;//课程名称、上课教室，教师，课程编号
    private Academy belongAcademy;

    public Course(Integer start, Integer step, Integer dayOfWeek, String courseName, String classroom, String teacher, String courseNumber, Academy belongAcademy) {
        this.start = start;
        this.step = step;
        this.dayOfWeek = dayOfWeek;
        this.courseName = courseName;
        this.classroom = classroom;
        this.teacher = teacher;
        this.courseNumber = courseNumber;
        this.belongAcademy = belongAcademy;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public Academy getBelongAcademy() {
        return belongAcademy;
    }

    public void setBelongAcademy(Academy belongAcademy) {
        this.belongAcademy = belongAcademy;
    }
}