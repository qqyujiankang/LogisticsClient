package com.cn.logistics.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.cn.logistics.R;
import com.cn.logistics.bean.RegisterBean;
import com.cn.logistics.bean.StatusBean;
import com.cn.logistics.common.MyActivity;
import com.cn.logistics.helper.InputTextHelper;
import com.cn.logistics.network.Constant;
import com.cn.logistics.network.GsonUtils;
import com.cn.logistics.network.ServerUrl;
import com.cn.logistics.other.IntentKey;
import com.cn.logistics.presenter.PublicInterfaceePresenetr;
import com.cn.logistics.presenter.view.PublicInterfaceView;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.toast.ToastUtils;
import com.hjq.widget.view.CountdownView;
import com.hjq.widget.view.ScaleImageView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 注册界面
 */
public final class RegisterActivity extends MyActivity implements PublicInterfaceView {

    @BindView(R.id.et_register_phone)
    EditText mPhoneView;
    @BindView(R.id.cv_register_countdown)
    CountdownView mCountdownView;

    @BindView(R.id.et_register_code)
    EditText mCodeView;

    @BindView(R.id.et_register_password1)
    EditText mPasswordView1;


    @BindView(R.id.btn_register_commit)
    Button mCommitView;
    @BindView(R.id.ll_register_edit1)
    LinearLayout llRegisterEdit1;
    @BindView(R.id.iv_login_qq)
    ScaleImageView ivLoginQq;
    @BindView(R.id.iv_login_wx)
    ScaleImageView ivLoginWx;
    private PublicInterfaceePresenetr presenetr;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

        presenetr = new PublicInterfaceePresenetr(this);

        InputTextHelper.with(this)
                .addView(mPhoneView)
                .addView(mCodeView)
                .addView(mPasswordView1)
                .setMain(mCommitView)
                .setListener(new InputTextHelper.OnInputTextListener() {

                    @Override
                    public boolean onInputChange(InputTextHelper helper) {
                        return mPhoneView.getText().toString().length() == 11 &&
                                mPasswordView1.getText().toString().length() >= 6 &&
                                !TextUtils.isEmpty(mCodeView.getText().toString().trim());
                    }
                })
                .build();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected ImmersionBar statusBarConfig() {
        // 不要把整个布局顶上去
        return super.statusBarConfig().keyboardEnable(true);
    }

    @OnClick({R.id.cv_register_countdown, R.id.btn_register_commit,R.id.iv_login_qq, R.id.iv_login_wx})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_register_countdown:
                // 获取验证码
                if (mPhoneView.getText().toString().length() != 11) {
                    // 重置验证码倒计时控件
                    mCountdownView.resetState();
                    toast(R.string.common_phone_input_error);
                } else {
//                    toast(R.string.common_code_send_hint);
//                    mCountdownView.run();
                    showLoading();
                    presenetr.getPostRequest(RegisterActivity.this, ServerUrl.sendSms(), Constant.sendSms);
                }
                break;
            case R.id.btn_register_commit:
                // 提交注册
//                Intent intent = new Intent();
//                intent.putExtra(IntentKey.PHONE, mPhoneView.getText().toString());
//                intent.putExtra(IntentKey.PASSWORD, mPasswordView1.getText().toString());
//                setResult(RESULT_OK, intent);
//                finish();
                showLoading();
                presenetr.getPostRequest(RegisterActivity.this,ServerUrl.register(),Constant.register);


                break;
            case R.id.iv_login_qq:
                break;
            case R.id.iv_login_wx:
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
            case Constant.register:
                paramsMap.put("userphone", mPhoneView.getText().toString());
                paramsMap.put("password", mPasswordView1.getText().toString());
                paramsMap.put("smscode", mCodeView.getText().toString());
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
            case Constant.register:
                showComplete();
                RegisterBean registerBean = GsonUtils.getPerson(data, RegisterBean.class);
                if (registerBean.getStatus()==200){
                    ToastUtils.show(registerBean.getMsg());
                    Intent intent = new Intent();
                    intent.putExtra(IntentKey.PHONE, mPhoneView.getText().toString());
                    intent.putExtra(IntentKey.PASSWORD, mPasswordView1.getText().toString());
                    setResult(RESULT_OK, intent);
                    RegisterActivity.this.finish();
                }else {
                    ToastUtils.show(registerBean.getMsg());
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