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

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/17
 */
public class SelfLearningRoom extends BmobObject {
    private String roomName;
    private String location;
    private String roomType;
    private Integer capacity;
    private String openTime;
    private Integer restCapacity;

    public SelfLearningRoom() {
    }

    public SelfLearningRoom(String roomName, String location, String roomType, Integer capacity, String openTime, Integer restCapacity) {
        this.roomName = roomName;
        this.location = location;
        this.roomType = roomType;
        this.capacity = capacity;
        this.openTime = openTime;
        this.restCapacity = restCapacity;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public Integer getRestCapacity() {
        return restCapacity;
    }

    public void setRestCapacity(Integer restCapacity) {
        this.restCapacity = restCapacity;
    }
}