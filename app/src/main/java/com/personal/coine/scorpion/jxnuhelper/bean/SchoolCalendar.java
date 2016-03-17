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
package com.personal.coine.scorpion.jxnuhelper.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/17
 */
public class SchoolCalendar extends BmobObject {
    private String eventTitle;
    private String eventContent;
    private String eventLocation;
    private BmobDate startDate;
    private BmobDate endDate;
    private Boolean allDay;

    public SchoolCalendar() {
    }

    public SchoolCalendar(String eventTitle, String eventContent, String eventLocation, BmobDate startDate, BmobDate endDate, Boolean allDay) {
        this.eventTitle = eventTitle;
        this.eventContent = eventContent;
        this.eventLocation = eventLocation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.allDay = allDay;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventContent() {
        return eventContent;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public BmobDate getStartDate() {
        return startDate;
    }

    public void setStartDate(BmobDate startDate) {
        this.startDate = startDate;
    }

    public BmobDate getEndDate() {
        return endDate;
    }

    public void setEndDate(BmobDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getAllDay() {
        return allDay;
    }

    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
    }
}