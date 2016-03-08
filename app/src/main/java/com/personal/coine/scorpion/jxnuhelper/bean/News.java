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
 * 2016/3/8  1.0     huangwei    Creation File
 */
package com.personal.coine.scorpion.jxnuhelper.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/8
 */
public class News extends BmobObject {
    private String newsTitle;
    private BmobDate newsDate;
    private String newsCategory;
    private String newsContent;

    public News() {
    }

    public News(String newsTitle, BmobDate newsDate, String newsCategory, String newsContent) {
        super("News");
        this.newsTitle = newsTitle;
        this.newsDate = newsDate;
        this.newsCategory = newsCategory;
        this.newsContent = newsContent;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public BmobDate getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(BmobDate newsDate) {
        this.newsDate = newsDate;
    }

    public String getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(String newsCategory) {
        this.newsCategory = newsCategory;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }
}