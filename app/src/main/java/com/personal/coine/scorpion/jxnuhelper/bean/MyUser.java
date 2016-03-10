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
 * 2016/3/10  1.0     huangwei    Creation File
 */
package com.personal.coine.scorpion.jxnuhelper.bean;

import java.io.File;

import cn.bmob.v3.BmobUser;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/10
 */
public class MyUser extends BmobUser {
    private String sex;
    private String province;
    private String city;
    private String district;
    private String personalSign;
    private File userAvadar;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPersonalSign() {
        return personalSign;
    }

    public void setPersonalSign(String personalSign) {
        this.personalSign = personalSign;
    }

    public File getUserAvadar() {
        return userAvadar;
    }

    public void setUserAvadar(File userAvadar) {
        this.userAvadar = userAvadar;
    }
}