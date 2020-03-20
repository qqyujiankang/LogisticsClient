package com.cn.logistics.ui.activity;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.cn.logistics.R;
import com.cn.logistics.bean.StatusBean;
import com.cn.logistics.common.MyActivity;
import com.cn.logistics.network.Constant;
import com.cn.logistics.network.GsonUtils;
import com.cn.logistics.network.ServerUrl;
import com.cn.logistics.presenter.PublicInterfaceePresenetr;
import com.cn.logistics.presenter.view.PublicInterfaceView;
import com.cn.logistics.ui.dialog.AddressDialog;
import com.hjq.dialog.base.BaseDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 添加地址
 */
public final class AddressDetailActivity extends MyActivity implements PublicInterfaceView {


    @BindView(R.id.et_address_detail_name)
    EditText etAddressDetailName;
    @BindView(R.id.et_address_detail_address)
    EditText etAddressDetailAddress;
    @BindView(R.id.set_pass)
    Switch setPass;
    @BindView(R.id.address_delete)
    TextView addressDelete;
    @BindView(R.id.et_address_detail_phone)
    EditText etAddressDetailPhone;
    @BindView(R.id.et_address_detail_xx)
    EditText etAddressDetailXx;
    private PublicInterfaceePresenetr presenetr;
    private String mProvince;
    private String mCity;
    private String mArea;
    private int is_default=2;//1为默认   2不是

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_detail;
    }

    @Override
    protected void initView() {

        presenetr = new PublicInterfaceePresenetr(this);

    }

    @Override
    protected void initData() {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.et_address_detail_address, R.id.set_pass, R.id.address_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_address_detail_address:
                // 选择地区对话框
                new AddressDialog.Builder(this)
                        .setTitle(getString(R.string.address_title))
                        // 设置默认省份
                        //.setProvince("广东省")
                        // 设置默认城市（必须要先设置默认省份）
                        //.setCity("广州市")
                        // 不选择县级区域
                        //.setIgnoreArea()
                        .setListener(new AddressDialog.OnListener() {

                            @Override
                            public void onSelected(BaseDialog dialog, String province, String city, String area) {
//                                toast(province + city + area);
                                etAddressDetailAddress.setText(province + " " + city + " " + area);
                                mProvince = province;
                                mCity = city;
                                mArea = area;

                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
//                                toast("取消了");
                            }
                        })
                        .show();
                break;
            case R.id.set_pass:
                if (setPass.isChecked()) {
                    //设为默认地址
                    is_default = 1;
                } else {
                    //取消默认地址
                    is_default = 2;
                }
                break;
            case R.id.address_delete:
                finish();
                break;

        }
    }

    @Override
    public void onRightClick(View v) {
        super.onRightClick(v);

        if (TextUtils.isEmpty(etAddressDetailName.getText().toString().trim())) {
            toast("请输入收货人！！！");
            return;
        }
        if (TextUtils.isEmpty(etAddressDetailPhone.getText().toString().trim())) {
            toast("请输入手机号码！！！");
            return;
        }
        if (TextUtils.isEmpty(etAddressDetailAddress.getText().toString().trim())) {
            toast("请选择所在地区！！！");
            return;
        }
        if (TextUtils.isEmpty(etAddressDetailXx.getText().toString().trim())) {
            toast("请输入详细地址！！！");
            return;
        }

        showLoading();
        presenetr.getPostHeaderRequest(AddressDetailActivity.this, ServerUrl.addChinaAddress, Constant.addChinaAddress);


    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> paramsMap = new HashMap<>();
        switch (tag) {
            case Constant.addChinaAddress:
                paramsMap.put("userid", userBean().getId());
                paramsMap.put("username", etAddressDetailName.getText().toString().trim());
                paramsMap.put("userphone", etAddressDetailPhone.getText().toString().trim());
                paramsMap.put("province", mProvince);
                paramsMap.put("city", mCity);
                paramsMap.put("area", mArea);
                paramsMap.put("addressDetail", etAddressDetailXx.getText().toString().trim());
                paramsMap.put("is_default", is_default);
                return paramsMap;


        }
        return null;
    }

    @Override
    public void onPublicInterfaceSuccess(String data, int tag) {
        switch (tag) {
            case Constant.addChinaAddress:

                showComplete();
                StatusBean person = GsonUtils.getPerson(data, StatusBean.class);
                if (person.getStatus() == 200) {
                    toast(person.getMsg());
                    AddressDetailActivity.this.finish();
                } else {
                    toast(person.getMsg());
                }

                break;


        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {
        showComplete();
    }
}