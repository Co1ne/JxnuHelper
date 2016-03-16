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
package com.personal.coine.scorpion.jxnuhelper.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/16
 */
public class ExamSchedule extends BmobObject {
    private Academy belongAcademy;
    private BmobDate examDate;
    private String subject;
    private String examContent;

    public ExamSchedule() {
    }

    public ExamSchedule(Academy belongAcademy, BmobDate examDate, String subject, String examContent) {
        this.belongAcademy = belongAcademy;
        this.examDate = examDate;
        this.subject = subject;
        this.examContent = examContent;
    }

    public Academy getBelongAcademy() {
        return belongAcademy;
    }

    public void setBelongAcademy(Academy belongAcademy) {
        this.belongAcademy = belongAcademy;
    }

    public BmobDate getExamDate() {
        return examDate;
    }

    public void setExamDate(BmobDate examDate) {
        this.examDate = examDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getExamContent() {
        return examContent;
    }

    public void setExamContent(String examContent) {
        this.examContent = examContent;
    }
}