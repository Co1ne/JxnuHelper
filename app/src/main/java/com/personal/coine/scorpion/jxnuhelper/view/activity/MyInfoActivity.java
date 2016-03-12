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

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.personal.coine.scorpion.jxnuhelper.Constants;
import com.personal.coine.scorpion.jxnuhelper.R;
import com.personal.coine.scorpion.jxnuhelper.bean.CityBean;
import com.personal.coine.scorpion.jxnuhelper.bean.DistrictBean;
import com.personal.coine.scorpion.jxnuhelper.bean.MyUser;
import com.personal.coine.scorpion.jxnuhelper.bean.ProvinceBean;
import com.personal.coine.scorpion.jxnuhelper.presenter.UserInfoPresenter;
import com.personal.coine.scorpion.jxnuhelper.utils.OthersUtils;
import com.personal.coine.scorpion.jxnuhelper.utils.XmlParserHandler;
import com.personal.coine.scorpion.jxnuhelper.view.IUserInfoView;

import org.xml.sax.SAXException;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import cn.bmob.v3.BmobUser;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/9
 */
public class MyInfoActivity extends AppCompatActivity implements View.OnClickListener, IUserInfoView {
    private static final String TAG = MyInfoActivity.class.getSimpleName();

    private View vMasker;
    private OptionsPickerView areaPickerView;
    private ArrayList<String> provinceList = new ArrayList<>();
    private ArrayList<ArrayList<String>> cityList = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> districtList = new ArrayList<>();
    private UserInfoPresenter userPresenter = new UserInfoPresenter(this);
    private String avadarImagePath;
    private KProgressHUD loginProgress;
    private Uri fileUri;
    private ImageView userAvadarImg;
    private EditText nameInput;
    private String userSex = "";
    private String checkedProvince = "";
    private String checkedCity = "";
    private String checkedDistrict = "";
    private EditText personalSignText;
    private TextView userAcademyText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        initActionBar();
        initViews();
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("个人资料");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initViews() {
        MyUser currentUser = BmobUser.getCurrentUser(this, MyUser.class);
        userAcademyText = ((TextView) findViewById(R.id.user_academy_text));
        userAvadarImg = (ImageView) findViewById(R.id.user_avadar_img);
        userPresenter.loadUserAcademy();
        userPresenter.loadUserAvadar();
        ((TextView) findViewById(R.id.user_name)).setText(currentUser.getUsername());
        ((TextView) findViewById(R.id.phone_number)).setText(currentUser.getMobilePhoneNumber());
        ((TextView) findViewById(R.id.sex)).setText(currentUser.getSex());
        ((TextView) findViewById(R.id.hometown)).setText(currentUser.getProvince() + "," + currentUser.getCity() + "," + currentUser.getDistrict());
        ((TextView) findViewById(R.id.personal_sign)).setText(currentUser.getPersonalSign());

        findViewById(R.id.row_avadar).setOnClickListener(this);
        findViewById(R.id.row_username).setOnClickListener(this);
        findViewById(R.id.row_sex).setOnClickListener(this);
        findViewById(R.id.row_hometown).setOnClickListener(this);
        findViewById(R.id.row_personal_sign).setOnClickListener(this);
        vMasker = findViewById(R.id.vMasker);
        initAreaPicker();
        loginProgress = KProgressHUD.create(this).setStyle(KProgressHUD.Style.PIE_DETERMINATE).setLabel("正在处理...").setCancellable(false).setAnimationSpeed(2).setDimAmount(0.5f).setMaxProgress(100);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.row_avadar:
                choosePic();
                break;
            case R.id.row_username:
                nameInput = new EditText(this);
                new AlertDialog.Builder(this).setTitle("编辑昵称").setView(nameInput).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userPresenter.updateUserInfo();
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
                final AlertDialog choiceDialog = new AlertDialog.Builder(this).setTitle("选择性别").setSingleChoiceItems(sexChoice, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userSex = sexChoice[which];
                    }
                }).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userPresenter.updateUserInfo();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                break;
            case R.id.row_hometown:
                OthersUtils.hideKeyboard(MyInfoActivity.this);
                areaPickerView.show();
                break;
            case R.id.row_personal_sign:
                personalSignText = new EditText(this);
                new AlertDialog.Builder(this).setTitle("编辑个性签名").setView(personalSignText).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userPresenter.updateUserInfo();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                break;
            default:
                break;
        }
    }

    private void choosePic() {
        new AlertDialog.Builder(this).setTitle("选择头像").setPositiveButton("拍照", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                takePhoto();
            }
        }).setNegativeButton("从相册选取", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pickPhoto();
            }
        }).show();
    }

    private void takePhoto() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
            String out_file_path = Constants.SAVED_IMAGE_DIR_PATH;
            File dir = new File(out_file_path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String photoName = Constants.SAVED_IMAGE_DIR_PATH + System.currentTimeMillis() + ".jpg";
            fileUri = Uri.fromFile(new File(photoName));
            getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            getImageByCamera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivityForResult(getImageByCamera, Constants.REQUEST_CODE_CAPTURE_CAMEIA);
        } else {
            Toast.makeText(getApplicationContext(), "请确认已经插入SD卡", Toast.LENGTH_LONG).show();
        }
    }

    private void pickPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent, Constants.REQUEST_CODE_PICK_IMAGE);
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
                checkedProvince = provinceList.get(options1);
                checkedCity = cityList.get(options1).get(option2);
                checkedDistrict = districtList.get(options1).get(option2).get(options3);
                vMasker.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_CODE_PICK_IMAGE) {
            if (data.getData() != null) {
                Uri uri = data.getData();
                crop(uri);
            }
        } else if (requestCode == Constants.REQUEST_CODE_CAPTURE_CAMEIA) {
            crop(fileUri);
        } else if (requestCode == Constants.PHOTO_REQUEST_CUT) {
            try {
                Bitmap bitmap = data.getParcelableExtra("data");
                String filePath = saveBitmapFile(bitmap);
                if (filePath != null) {
                    avadarImagePath = filePath;
                    Log.d(TAG, "用户头像路径:" + avadarImagePath);
                    userPresenter.changeUserAvadar();
                } else {
                    Toast.makeText(MyInfoActivity.this, "头像上传失败，请重试", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String saveBitmapFile(Bitmap bitmap) {
        String filePath = Constants.SAVED_IMAGE_DIR_PATH + System.currentTimeMillis() + ".jpg";
        File file = new File(filePath);//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 图片剪裁
     *
     * @param uri
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        // 图片格式
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);// true:不返回uri，false：返回uri
        startActivityForResult(intent, Constants.PHOTO_REQUEST_CUT);
    }

    @Override
    public Context getThisContext() {
        return this;
    }

    @Override
    public String getUserAvadarPath() {
        return avadarImagePath;
    }

    @Override
    public void showLoading() {
        loginProgress.show();
    }

    @Override
    public void showLoadingProgress(int progress) {
        loginProgress.setProgress(progress);
    }

    @Override
    public void hideLoading() {
        loginProgress.dismiss();
    }

    @Override
    public ImageView getAvadarView() {
        return userAvadarImg;
    }

    @Override
    public String getUserName() {
        if (nameInput != null && nameInput.getText() != null) {
            return nameInput.getText().toString();
        }
        return "";
    }

    @Override
    public String getSex() {
        return userSex;
    }

    @Override
    public String getProvince() {
        return checkedProvince;
    }

    @Override
    public String getCity() {
        return checkedCity;
    }

    @Override
    public String getDistrict() {
        return checkedDistrict;
    }

    @Override
    public String getPersonalSign() {
        if (personalSignText != null && personalSignText.getText() != null) {
            return personalSignText.getText().toString();
        }
        return "";
    }

    @Override
    public void refreshViews() {
        initViews();
    }

    @Override
    public TextView getAcademyTextView() {
        return userAcademyText;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}