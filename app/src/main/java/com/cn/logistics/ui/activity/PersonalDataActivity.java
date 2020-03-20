package com.cn.logistics.ui.activity;

import android.view.View;

import com.cn.logistics.R;
import com.cn.logistics.bean.RegisterBean;
import com.cn.logistics.common.MyActivity;
import com.cn.logistics.network.Constant;
import com.cn.logistics.network.GsonUtils;
import com.cn.logistics.network.ServerUrl;
import com.cn.logistics.presenter.PublicInterfaceePresenetr;
import com.cn.logistics.presenter.view.PublicInterfaceView;
import com.cn.logistics.utils.SPUtils;
import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;
import com.hjq.widget.layout.SettingBar;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/04/20
 *    desc   : 个人资料
 */
public final class PersonalDataActivity extends MyActivity implements PublicInterfaceView {


    @BindView(R.id.sb_person_data_id)
    SettingBar mIDView;
    @BindView(R.id.sb_person_data_name)
    SettingBar mNameView;
    @BindView(R.id.sb_person_data_address)
    SettingBar mAddressView;
    @BindView(R.id.sb_person_data_phone)
    SettingBar mPhoneView;
    private PublicInterfaceePresenetr presenetr;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_data;
    }

    @Override
    protected void initView() {

        presenetr = new PublicInterfaceePresenetr(this);

    }

    @Override
    protected void initData() {
        if (isLogin()){
            show();
        }
    }

    private void show() {

        mPhoneView.setRightText(userBean().getUserphone());
        mNameView.setRightText((String) userBean().getWechatno());

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (null==presenetr){
            presenetr = new PublicInterfaceePresenetr(this);
        }
        presenetr.getPostRequest(PersonalDataActivity.this, ServerUrl.selectInfoByUserid, Constant.selectInfoByUserid);

    }

    @OnClick({R.id.sb_person_data_name,R.id.sb_person_data_id, R.id.sb_person_data_address, R.id.sb_person_data_phone})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sb_person_data_name://绑定微信
                startActivity(BindActivity.class);
                break;
            case R.id.sb_person_data_id://修改密码
                startActivity(PasswordResetActivity.class);
                break;
            case R.id.sb_person_data_address://辅助账号
                startActivity(AssistActivity.class);
                break;
            case R.id.sb_person_data_phone://更换手机

                startActivity(BindPhoneActivity.class);

                break;
            default:
                break;
        }
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> paramsMap = new HashMap<>();
        switch (tag) {
            case Constant.selectInfoByUserid:
                paramsMap.put("userid", userBean().getId());
                return paramsMap;

        }
        return null;
    }

    @Override
    public void onPublicInterfaceSuccess(String data, int tag) {
        switch (tag) {
            case Constant.selectInfoByUserid:
                RegisterBean person3 = GsonUtils.getPerson(data, RegisterBean.class);
                if (person3.getStatus()==200){//查询个人信息
                    RegisterBean.DataBean personData = person3.getData();
                    SPUtils.putString("bean",new Gson().toJson(personData));

                    setUserBean();
                    show();

                }
                break;


        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {
        ToastUtils.show(error);
        showComplete();
    }
}