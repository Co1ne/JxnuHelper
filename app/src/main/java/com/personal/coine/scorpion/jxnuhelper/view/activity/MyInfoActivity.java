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
 * 2016/3/9  1.0     huangwei    Creation File
 */
package com.personal.coine.scorpion.jxnuhelper.view.activity;

import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.personal.coine.scorpion.jxnuhelper.R;
import com.personal.coine.scorpion.jxnuhelper.bean.CityBean;
import com.personal.coine.scorpion.jxnuhelper.bean.DistrictBean;
import com.personal.coine.scorpion.jxnuhelper.bean.ProvinceBean;
import com.personal.coine.scorpion.jxnuhelper.core.ApplicationDelegate;
import com.personal.coine.scorpion.jxnuhelper.utils.OthersUtils;
import com.personal.coine.scorpion.jxnuhelper.utils.XmlParserHandler;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/9
 */
public class MyInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MyInfoActivity.class.getSimpleName();
    private View vMasker;
    private OptionsPickerView areaPickerView;
    private ArrayList<String> provinceList = new ArrayList<>();
    private ArrayList<ArrayList<String>> cityList = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> districtList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        initViews();
    }

    private void initViews() {
        ((TextView) findViewById(R.id.user_name)).setText(ApplicationDelegate.getInstance().getCurrentUser().getUsername());
        ((TextView) findViewById(R.id.phone_number)).setText(ApplicationDelegate.getInstance().getCurrentUser().getMobilePhoneNumber());

        findViewById(R.id.row_avadar).setOnClickListener(this);
        findViewById(R.id.row_username).setOnClickListener(this);
        findViewById(R.id.row_sex).setOnClickListener(this);
        findViewById(R.id.row_hometown).setOnClickListener(this);
        findViewById(R.id.row_personal_sign).setOnClickListener(this);
        vMasker = findViewById(R.id.vMasker);
        initAreaPicker();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.row_avadar:

                break;
            case R.id.row_username:
                final EditText nameInput = new EditText(this);
                new AlertDialog.Builder(this).setTitle("编辑昵称").setView(nameInput).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nameInput.getText();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                break;
            case R.id.row_sex:
                ListView sexList = new ListView(this);
                final String[] sexChoice = new String[]{"男", "女"};
                ListAdapter adapter1 = new ArrayAdapter<String>(MyInfoActivity.this, android.R.layout.simple_list_item_1, sexChoice);
                sexList.setAdapter(adapter1);
                final AlertDialog choiceDialog = new AlertDialog.Builder(this).setTitle("选择性别").setView(sexList).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                sexList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.d(TAG, sexChoice[position]);
                        choiceDialog.dismiss();
                    }
                });
                break;
            case R.id.row_hometown:
                OthersUtils.hideKeyboard(MyInfoActivity.this);
                areaPickerView.show();
                break;
            case R.id.row_personal_sign:
                break;
            default:
                break;
        }
    }

    private void initAreaPicker() {
        areaPickerView = new OptionsPickerView(this);
        try {
            List<ProvinceBean> provinceBeanList = null;
            List<CityBean> cityTempList = null;
            List<DistrictBean> districtTempList = null;

            AssetManager assetManager = getAssets();
            InputStream provinceXMLInput = assetManager.open("province_data.xml");
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser parser = saxParserFactory.newSAXParser();
            XmlParserHandler parserHandler = new XmlParserHandler();
            parser.parse(provinceXMLInput, parserHandler);
            provinceXMLInput.close();
            provinceBeanList = parserHandler.getDataList();
            for (int i = 0; i < provinceBeanList.size(); i++) {
                ArrayList<String> cityStringTempList = new ArrayList<>();
                ArrayList<ArrayList<String>> districtArrayTempList = new ArrayList<>();
                //省级信息
                provinceList.add(provinceBeanList.get(i).getName());
                //市级信息
                cityTempList = provinceBeanList.get(i).getCityList();
                for (CityBean city : cityTempList) {
                    cityStringTempList.add(city.getName());
                    districtTempList = city.getDistrictList();
                    ArrayList<String> districtStringTempList = new ArrayList<>();
                    for (DistrictBean district : districtTempList) {
                        districtStringTempList.add(district.getName());
                    }
                    districtArrayTempList.add(districtStringTempList);
                }
                cityList.add(cityStringTempList);
                districtList.add(districtArrayTempList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        areaPickerView.setPicker(provinceList, cityList, districtList, true);
        areaPickerView.setTitle("选择城市");
        areaPickerView.setCyclic(false, false, false);
        areaPickerView.setSelectOptions(0, 0, 0);
        areaPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                vMasker.setVisibility(View.GONE);
//                user.setProvince(provinceList.get(options1));
//                user.setCity(cityList.get(options1).get(option2));
//                user.setTown(districtList.get(options1).get(option2).get(options3));
//                initList();
            }
        });
    }
}