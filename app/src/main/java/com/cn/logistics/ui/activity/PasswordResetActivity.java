package com.cn.logistics.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatEditText;

import com.cn.logistics.R;
import com.cn.logistics.bean.RegisterBean;
import com.cn.logistics.bean.StatusBean;
import com.cn.logistics.common.MyActivity;
import com.cn.logistics.network.Constant;
import com.cn.logistics.network.GsonUtils;
import com.cn.logistics.network.ServerUrl;
import com.cn.logistics.presenter.PublicInterfaceePresenetr;
import com.cn.logistics.presenter.view.PublicInterfaceView;
import com.cn.logistics.utils.SPUtils;
import com.google.gson.Gson;
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
 * time   : 2019/02/27
 * desc   : 重置密码
 */
public final class PasswordResetActivity extends MyActivity implements PublicInterfaceView {

    @BindView(R.id.et_password_reset_password1)
    EditText mPasswordView1;
    @BindView(R.id.et_password_reset_password2)
    EditText mPasswordView2;
    @BindView(R.id.et_password_forget_phone)
    RegexEditText etPasswordForgetPhone;
    @BindView(R.id.et_password_forget_code)
    AppCompatEditText etPasswordForgetCode;
    @BindView(R.id.cv_password_forget_countdown)
    CountdownView cvPasswordForgetCountdown;
    private PublicInterfaceePresenetr presenetr;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_password_reset;
    }

    @Override
    protected void initView() {
        presenetr = new PublicInterfaceePresenetr(this);

    }

    @Override
    protected void initData() {

    }


    @Override
    public void onRightClick(View v) {
        super.onRightClick(v);
        if (TextUtils.isEmpty(etPasswordForgetPhone.getText().toString().trim())){
            toast("请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(etPasswordForgetCode.getText().toString().trim())){
            toast("请输入验证码");
            return;
        }
        if (TextUtils.isEmpty(mPasswordView1.getText().toString().trim())){
            toast("请输入6-18位密码");
            return;
        }
        if (TextUtils.isEmpty(mPasswordView2.getText().toString().trim())){
            toast("请确认密码");
            return;
        }
        if (!mPasswordView1.getText().toString().trim().equals(mPasswordView2.getText().toString().trim())){
            toast("两次输入不一致");
            return;
        }

        presenetr.getPostHeaderRequest(PasswordResetActivity.this,ServerUrl.updatePassword,Constant.updatePassword);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.cv_password_forget_countdown})
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.cv_password_forget_countdown:
                if (etPasswordForgetPhone.getText().toString().length() != 11) {
                    // 重置验证码倒计时控件
                    cvPasswordForgetCountdown.resetState();
                    toast(R.string.common_phone_input_error);
                } else {
                    // 获取验证码
//                    toast(R.string.common_code_send_hint);
//                    cvPasswordForgetCountdown.run()/;
                    showLoading();
                    presenetr.getPostRequest(PasswordResetActivity.this, ServerUrl.sendSms(), Constant.sendSms);

                }
                break;
        }
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> paramsMap = new HashMap<>();
        switch (tag) {
            case Constant.sendSms:
                paramsMap.put("loginName", etPasswordForgetPhone.getText().toString());
                return paramsMap;
            case Constant.updatePassword:
                paramsMap.put("userid", userBean().getId());
                paramsMap.put("password", mPasswordView1.getText().toString());
                paramsMap.put("smsCode", etPasswordForgetCode.getText().toString());
                return paramsMap;
            case Constant.selectInfoByUserid:
                paramsMap.put("userid", userBean().getId());
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
                    cvPasswordForgetCountdown.resetState();
                    ToastUtils.show(person.getMsg());
                }
                break;
            case Constant.updatePassword:
                showComplete();
                StatusBean person2 = GsonUtils.getPerson(data, StatusBean.class);
                if (person2.getStatus()==200){
                    ToastUtils.show(person2.getMsg());
                    presenetr.getPostRequest(PasswordResetActivity.this,ServerUrl.selectInfoByUserid,Constant.selectInfoByUserid);


                }else {

                    ToastUtils.show(person2.getMsg());
                }
                break;
            case Constant.selectInfoByUserid:
                RegisterBean person3 = GsonUtils.getPerson(data, RegisterBean.class);
                if (person3.getStatus()==200){//查询个人信息
                    RegisterBean.DataBean personData = person3.getData();
                    SPUtils.putString("bean",new Gson().toJson(personData));

                    setUserBean();

                    PasswordResetActivity.this.finish();
                }
                break;

        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {
        switch (tag) {
            case Constant.sendSms:
                cvPasswordForgetCountdown.resetState();
                ToastUtils.show(error);
                showComplete();
                break;

        }
    }
}