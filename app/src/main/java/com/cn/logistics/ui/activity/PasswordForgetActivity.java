package com.cn.logistics.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cn.logistics.R;
import com.cn.logistics.bean.StatusBean;
import com.cn.logistics.common.MyActivity;
import com.cn.logistics.helper.InputTextHelper;
import com.cn.logistics.network.Constant;
import com.cn.logistics.network.GsonUtils;
import com.cn.logistics.network.ServerUrl;
import com.cn.logistics.presenter.PublicInterfaceePresenetr;
import com.cn.logistics.presenter.view.PublicInterfaceView;
import com.hjq.toast.ToastUtils;
import com.hjq.widget.view.CountdownView;
import com.hjq.widget.view.PasswordEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2019/02/27
 * desc   : 忘记密码
 */
public final class PasswordForgetActivity extends MyActivity implements PublicInterfaceView {

    @BindView(R.id.et_password_forget_phone)
    EditText mPhoneView;
    @BindView(R.id.et_password_forget_code)
    EditText mCodeView;
    @BindView(R.id.cv_password_forget_countdown)
    CountdownView mCountdownView;
    @BindView(R.id.btn_password_forget_commit)
    Button mCommitView;
    @BindView(R.id.et_forget_password1)
    PasswordEditText etForgetPassword1;
    @BindView(R.id.et_forget_password2)
    PasswordEditText etForgetPassword2;
    private PublicInterfaceePresenetr presenetr;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_password_forget;
    }

    @Override
    protected void initView() {

        presenetr = new PublicInterfaceePresenetr(this);

        InputTextHelper.with(this)
                .addView(mPhoneView)
                .addView(mCodeView)
                .addView(etForgetPassword1)
                .addView(etForgetPassword2)
                .setMain(mCommitView)
                .setListener(new InputTextHelper.OnInputTextListener() {

                    @Override
                    public boolean onInputChange(InputTextHelper helper) {
                        return mPhoneView.getText().toString().length() == 11 && mCodeView.getText().toString().length() == 6&&
                                etForgetPassword1.getText().toString().trim().length()>=6&&
                                etForgetPassword1.getText().toString().trim().equals(etForgetPassword2.getText().toString().trim());
                    }
                })
                .build();
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.cv_password_forget_countdown, R.id.btn_password_forget_commit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_password_forget_countdown:
                if (mPhoneView.getText().toString().length() != 11) {
                    // 重置验证码倒计时控件
                    mCountdownView.resetState();
                    toast(R.string.common_phone_input_error);
                } else {
                    // 获取验证码
//                    toast(R.string.common_code_send_hint);
//                    mCountdownView.run();
                    showLoading();
                    presenetr.getPostRequest(PasswordForgetActivity.this, ServerUrl.sendSms(), Constant.sendSms);
                }
                break;
            case R.id.btn_password_forget_commit:
                // 重置密码
//                startActivityFinish(PasswordResetActivity.class);
                showLoading();
                presenetr.getPostRequest(PasswordForgetActivity.this, ServerUrl.forgetPassword(), Constant.forgetPassword);

                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> paramsMap = new HashMap<>();
        switch (tag) {
            case Constant.sendSms:
                paramsMap.put("loginName", mPhoneView.getText().toString());
                return paramsMap;
            case Constant.forgetPassword:
                paramsMap.put("type", 2);
                paramsMap.put("userphone", mPhoneView.getText().toString());
                paramsMap.put("password", etForgetPassword1.getText().toString());
                paramsMap.put("smsCode", mCodeView.getText().toString());
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
                    mCountdownView.resetState();
                    ToastUtils.show(person.getMsg());
                }
                break;
            case Constant.forgetPassword:
                showComplete();
                StatusBean person2 = GsonUtils.getPerson(data, StatusBean.class);
                if (person2.getStatus()==200){
                    ToastUtils.show(person2.getMsg());
                    PasswordForgetActivity.this.finish();
                }else {

                    ToastUtils.show(person2.getMsg());
                }
                break;

        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {
        switch (tag) {
            case Constant.sendSms:
                mCountdownView.resetState();
                ToastUtils.show(error);
                showComplete();
                break;

        }
    }
}