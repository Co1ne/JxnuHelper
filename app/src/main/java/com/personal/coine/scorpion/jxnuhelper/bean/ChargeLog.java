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
public class ChargeLog extends BmobObject {
    private CampusCard cardInfo;
    private Integer chargeSum;
    private BmobDate chargeDate;

    public ChargeLog() {
    }

    public ChargeLog(CampusCard cardInfo, Integer chargeSum, BmobDate chargeDate) {
        this.cardInfo = cardInfo;
        this.chargeSum = chargeSum;
        this.chargeDate = chargeDate;
    }

    public CampusCard getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(CampusCard cardInfo) {
        this.cardInfo = cardInfo;
    }

    public Integer getChargeSum() {
        return chargeSum;
    }

    public void setChargeSum(Integer chargeSum) {
        this.chargeSum = chargeSum;
    }

    public BmobDate getChargeDate() {
        return chargeDate;
    }

    public void setChargeDate(BmobDate chargeDate) {
        this.chargeDate = chargeDate;
    }
}