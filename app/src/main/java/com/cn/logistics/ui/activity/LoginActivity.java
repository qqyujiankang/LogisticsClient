package com.cn.logistics.ui.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.cn.logistics.R;
import com.cn.logistics.bean.RegisterBean;
import com.cn.logistics.common.MyActivity;
import com.cn.logistics.helper.InputTextHelper;
import com.cn.logistics.network.Constant;
import com.cn.logistics.network.GsonUtils;
import com.cn.logistics.network.ServerUrl;
import com.cn.logistics.other.IntentKey;
import com.cn.logistics.other.KeyboardWatcher;
import com.cn.logistics.presenter.PublicInterfaceePresenetr;
import com.cn.logistics.presenter.view.PublicInterfaceView;
import com.cn.logistics.utils.SPUtils;
import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 登录界面
 */
public final class LoginActivity extends MyActivity
        implements
        KeyboardWatcher.SoftKeyboardStateListener, PublicInterfaceView {

    @BindView(R.id.iv_login_logo)
    ImageView mLogoView;

    @BindView(R.id.ll_login_body)
    LinearLayout mBodyLayout;
    @BindView(R.id.et_login_phone)
    EditText mPhoneView;
    @BindView(R.id.et_login_password)
    EditText mPasswordView;

    @BindView(R.id.btn_login_commit)
    Button mCommitView;

    @BindView(R.id.ll_login_other)
    View mOtherView;
    @BindView(R.id.iv_login_qq)
    View mQQView;
    @BindView(R.id.iv_login_wx)
    View mWeChatView;

    /**
     * logo 缩放比例
     */
    private final float mLogoScale = 0.8f;
    /**
     * 动画时间
     */
    private final int mAnimTime = 300;
    @BindView(R.id.ll_login_bg)
    LinearLayout llLoginBg;
    @BindView(R.id.login_pt)
    TextView loginPt;
    @BindView(R.id.login_vip)
    TextView loginVip;
    @BindView(R.id.tv_login_forget)
    AppCompatTextView tvLoginForget;
    @BindView(R.id.tv_regis)
    AppCompatTextView tvRegis;
    @BindView(R.id.tv_name)
    TextView tvName;
    private PublicInterfaceePresenetr presenetr;
    private String registrationID;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

        presenetr = new PublicInterfaceePresenetr(this);

        InputTextHelper.with(this)
                .addView(mPhoneView)
                .addView(mPasswordView)
                .setMain(mCommitView)
                .setListener(new InputTextHelper.OnInputTextListener() {

                    @Override
                    public boolean onInputChange(InputTextHelper helper) {
                        tvRegis.setVisibility(View.INVISIBLE);
                        return mPhoneView.getText().toString().length() == 11 &&
                                mPasswordView.getText().toString().length() >= 6;
                    }
                })
                .build();

    }

    @Override
    protected void initData() {

        registrationID = JPushInterface.getRegistrationID(this);

        // 如果这两个都没有安装就隐藏提示
        if (mQQView.getVisibility() == View.GONE && mWeChatView.getVisibility() == View.GONE) {
            mOtherView.setVisibility(View.GONE);
        }
    }

//    @Override
//    public void onRightClick(View v) {
//        // 跳转到注册界面
//        startActivityForResult(RegisterActivity.class, new ActivityCallback() {
//
//            @Override
//            public void onActivityResult(int resultCode, @Nullable Intent data) {
//                // 如果已经注册成功，就执行登录操作
//                if (resultCode == RESULT_OK && data != null) {
//                    mPhoneView.setText(data.getStringExtra(IntentKey.PHONE));
//                    mPasswordView.setText(data.getStringExtra(IntentKey.PASSWORD));
//                    onClick(mCommitView);
//                }
//            }
//        });
//    }
@OnClick({R.id.tv_login_forget, R.id.btn_login_commit, R.id.iv_login_qq, R.id.iv_login_wx, R.id.login_pt, R.id.login_vip, R.id.tv_regis})
public void onViewClicked(View v) {
    switch (v.getId()) {
        case R.id.login_pt:
            is_vip = 1;
            loginPt.setTextColor(getResources().getColor(R.color.textColor));
            loginVip.setTextColor(getResources().getColor(R.color.gray));
            llLoginBg.setBackgroundResource(R.drawable.login_bg);
            tvName.setText("手机");
            mPhoneView.setHint("输入手机号");
            break;
        case R.id.login_vip:
            is_vip = 2;
            loginPt.setTextColor(getResources().getColor(R.color.gray));
            loginVip.setTextColor(getResources().getColor(R.color.textColor));
            llLoginBg.setBackgroundResource(R.drawable.login_bg2);
            tvName.setText("账号");
            mPhoneView.setHint("输入账号");
            break;
        case R.id.tv_regis://注册
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivityForResult(intent,2001);
            break;
        case R.id.tv_login_forget://忘记密码
            startActivity(PasswordForgetActivity.class);
            break;
        case R.id.btn_login_commit:
            if (mPhoneView.getText().toString().length() != 11) {
                toast(R.string.common_phone_input_error);
            } else if (TextUtils.isEmpty(mPasswordView.getText().toString().trim()) ) {
                toast("请输入密码");
            } else{
                showLoading();
                presenetr.getPostRequest(LoginActivity.this, ServerUrl.login(),Constant.login);

            }
            break;
        case R.id.iv_login_qq:
        case R.id.iv_login_wx:
            toast("记得改好第三方 AppID 和 AppKey，否则会调不起来哦");
            switch (v.getId()) {
                default:
                    throw new IllegalStateException("are you ok?");
            }
        default:
            break;
    }
}


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==2001){
            if (resultCode == RESULT_OK){
                is_vip = 1;
                String phone = data.getStringExtra(IntentKey.PHONE);
                String pass = data.getStringExtra(IntentKey.PASSWORD);

                mPhoneView.setText(phone);
                mPasswordView.setText(pass);

                showLoading();
                presenetr.getPostRequest(LoginActivity.this, ServerUrl.login(),Constant.login);
            }

        }
    }


    @Override
    public void onSoftKeyboardOpened(int keyboardHeight) {
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        int[] location = new int[2];
        // 获取这个 View 在屏幕中的坐标（左上角）
        mBodyLayout.getLocationOnScreen(location);
        //int x = location[0];
        int y = location[1];
        int bottom = screenHeight - (y + mBodyLayout.getHeight());
        if (keyboardHeight > bottom) {
            // 执行位移动画
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mBodyLayout, "translationY", 0, -(keyboardHeight - bottom));
            objectAnimator.setDuration(mAnimTime);
            objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            objectAnimator.start();

            // 执行缩小动画
            mLogoView.setPivotX(mLogoView.getWidth() / 2f);
            mLogoView.setPivotY(mLogoView.getHeight());
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(mLogoView, "scaleX", 1.0f, mLogoScale);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(mLogoView, "scaleY", 1.0f, mLogoScale);
            ObjectAnimator translationY = ObjectAnimator.ofFloat(mLogoView, "translationY", 0.0f, -(keyboardHeight - bottom));
            animatorSet.play(translationY).with(scaleX).with(scaleY);
            animatorSet.setDuration(mAnimTime);
            animatorSet.start();
        }
    }

    @Override
    public void onSoftKeyboardClosed() {
        // 执行位移动画
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mBodyLayout, "translationY", mBodyLayout.getTranslationY(), 0);
        objectAnimator.setDuration(mAnimTime);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.start();

        if (mLogoView.getTranslationY() == 0) {
            return;
        }
        // 执行放大动画
        mLogoView.setPivotX(mLogoView.getWidth() / 2f);
        mLogoView.setPivotY(mLogoView.getHeight());
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mLogoView, "scaleX", mLogoScale, 1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mLogoView, "scaleY", mLogoScale, 1.0f);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(mLogoView, "translationY", mLogoView.getTranslationY(), 0);
        animatorSet.play(translationY).with(scaleX).with(scaleY);
        animatorSet.setDuration(mAnimTime);
        animatorSet.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    int is_vip = 1;
    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> paramsMap = new HashMap<>();
        switch (tag) {
            case Constant.login:
                paramsMap.put("userphone", mPhoneView.getText().toString());
                paramsMap.put("password", mPasswordView.getText().toString());
                paramsMap.put("type", 2);//类别 1为司机端 2为客户端
                paramsMap.put("is_vip", is_vip);//客户端是否VIP登陆 1为客户端登陆  2为客户端VIP登陆
                paramsMap.put("deviceid",registrationID);
                return paramsMap;
        }
        return null;
    }

    @Override
    public void onPublicInterfaceSuccess(String data, int tag) {
        switch (tag) {
            case Constant.login:
                showComplete();
                RegisterBean person = GsonUtils.getPerson(data, RegisterBean.class);
                if (person.getStatus()==200){
                    RegisterBean.DataBean personData = person.getData();
                    SPUtils.putString("bean",new Gson().toJson(personData));
                    ToastUtils.show(person.getMsg());
                    LoginActivity.this.finish();
                }else {
                    ToastUtils.show(person.getMsg());
                }
                break;


        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {
        ToastUtils.show(error);
        showComplete();
//        switch (tag) {
//            case Constant.sendSms:
//
//                ToastUtils.show(error);
//                showComplete();
//                break;
//
//        }
    }
}