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
 * 2016/2/17  1.0     huangwei    Creation File
 */
package com.personal.coine.scorpion.jxnuhelper.bean;

import java.util.List;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/2/17
 */
public class ProvinceBean {
    private String name;
    private List<CityBean> cityList;

    public ProvinceBean() {
        super();
    }

    public ProvinceBean(String name, List<CityBean> cityList) {
        super();
        this.name = name;
        this.cityList = cityList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityBean> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityBean> cityList) {
        this.cityList = cityList;
    }

    @Override
    public String toString() {
        return "ProvinceModel [name=" + name + ", cityList=" + cityList + "]";
    }
}