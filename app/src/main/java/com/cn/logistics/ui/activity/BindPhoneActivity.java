package com.cn.logistics.ui.activity;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;

import com.cn.logistics.R;
import com.cn.logistics.bean.StatusBean;
import com.cn.logistics.common.MyActivity;
import com.cn.logistics.network.Constant;
import com.cn.logistics.network.GsonUtils;
import com.cn.logistics.network.ServerUrl;
import com.cn.logistics.presenter.PublicInterfaceePresenetr;
import com.cn.logistics.presenter.view.PublicInterfaceView;
import com.hjq.toast.ToastUtils;
import com.hjq.widget.view.CountdownView;
import com.hjq.widget.view.RegexEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 更换手机
 */
public final class BindPhoneActivity extends MyActivity implements PublicInterfaceView {

    @BindView(R.id.et_bind_phone)
    RegexEditText etBindPhone;
    @BindView(R.id.et_bind_code)
    AppCompatEditText etBindCode;
    @BindView(R.id.cv_bind_countdown)
    CountdownView cvBindCountdown;
    private PublicInterfaceePresenetr presenetr;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_phone;
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
    @Override
    public void onRightClick(View v) {
        super.onRightClick(v);
        if (TextUtils.isEmpty(etBindPhone.getText().toString().trim())){
            toast("请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(etBindCode.getText().toString().trim())){
            toast("请输入验证码");
            return;
        }
        showLoading();
        presenetr.getPostHeaderRequest(BindPhoneActivity.this,ServerUrl.updatePhone,Constant.updatePhone);

//        toast(R.string.password_reset_success);
//        finish();
    }
    @OnClick({R.id.cv_bind_countdown})
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.cv_bind_countdown:
                if (etBindPhone.getText().toString().length() != 11) {
                    // 重置验证码倒计时控件
                    cvBindCountdown.resetState();
                    toast(R.string.common_phone_input_error);
                } else {
                    // 获取验证码
//                    toast(R.string.common_code_send_hint);
//                    cvBindCountdown.run();
                    showLoading();
                    presenetr.getPostRequest(BindPhoneActivity.this, ServerUrl.sendSms(), Constant.sendSms);
                }
                break;
        }
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> paramsMap = new HashMap<>();
        switch (tag) {
            case Constant.sendSms:
                paramsMap.put("loginName", etBindPhone.getText().toString());
                return paramsMap;
            case Constant.updatePhone:
                paramsMap.put("type", 2);
                paramsMap.put("userid", userBean().getId());
                paramsMap.put("userphone", etBindPhone.getText().toString());
                paramsMap.put("smsCode", etBindCode.getText().toString());
                return paramsMap;

        }
        return null;
    }

    @Override
    public void onPublicInterfaceSuccess(String data, int tag) {
        switch (tag) {
            case Constant.sendSms:
                showComplete();
                StatusBean person = GsonUtils.getPerson(data, StatusBean.class);
                if (person.getStatus()==200){
                    ToastUtils.show(person.getMsg());
                }else {
                    cvBindCountdown.resetState();
                    ToastUtils.show(person.getMsg());
                }
                break;
            case Constant.updatePhone:
                showComplete();
                StatusBean person2 = GsonUtils.getPerson(data, StatusBean.class);
                if (person2.getStatus() == 200) {
                    ToastUtils.show(person2.getMsg());
                    BindPhoneActivity.this.finish();
                } else {
                    ToastUtils.show(person2.getMsg());
                }
                break;

        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {
        switch (tag) {
            case Constant.sendSms:
                cvBindCountdown.resetState();
                ToastUtils.show(error);
                showComplete();
                break;

        }
    }
}