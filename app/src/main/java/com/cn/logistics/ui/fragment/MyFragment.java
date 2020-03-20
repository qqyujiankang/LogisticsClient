package com.cn.logistics.ui.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cn.logistics.R;
import com.cn.logistics.bean.RegisterBean;
import com.cn.logistics.common.MyLazyFragment;
import com.cn.logistics.network.Constant;
import com.cn.logistics.network.GsonUtils;
import com.cn.logistics.network.ServerUrl;
import com.cn.logistics.presenter.PublicInterfaceePresenetr;
import com.cn.logistics.presenter.view.PublicInterfaceView;
import com.cn.logistics.ui.activity.CopyActivity;
import com.cn.logistics.ui.activity.MessageActivity;
import com.cn.logistics.ui.activity.MyOrderActivity;
import com.cn.logistics.ui.activity.PersonalDataActivity;
import com.cn.logistics.ui.activity.SetActivity;
import com.cn.logistics.ui.activity.UsualAddressActivity;
import com.cn.logistics.utils.SPUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 我的
 */
public final class MyFragment extends MyLazyFragment<CopyActivity> implements PublicInterfaceView {

    @BindView(R.id.ll_my_set)
    LinearLayout llMySet;
    @BindView(R.id.iv_my_message)
    ImageView ivMyMessage;
    @BindView(R.id.tv_my_dfk)
    TextView tvMyDfk;
    @BindView(R.id.tv_my_dfh)
    TextView tvMyDfh;
    @BindView(R.id.tv_my_dsh)
    TextView tvMyDsh;
    @BindView(R.id.iv_my_img)
    ImageView ivMyImg;
    @BindView(R.id.tv_my_name)
    TextView tvMyName;
    @BindView(R.id.ll_my_userinfo)
    LinearLayout llMyUserinfo;
    @BindView(R.id.ll_my_address)
    LinearLayout llMyAddress;
    @BindView(R.id.my_version)
    TextView myVersion;
    @BindView(R.id.ll_my_version)
    LinearLayout llMyVersion;
    @BindView(R.id.ll_my_dfh)
    LinearLayout llMyDfh;
    @BindView(R.id.ll_my_yfh)
    LinearLayout llMyYfh;
    private PublicInterfaceePresenetr presenetr;

    public static MyFragment newInstance() {
        return new MyFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {

        presenetr = new PublicInterfaceePresenetr(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (null == presenetr) {
            presenetr = new PublicInterfaceePresenetr(this);
        }
        if(isLogin()){

            presenetr.getPostRequest(getActivity(), ServerUrl.selectInfoByUserid, Constant.selectInfoByUserid);
        }

    }

    @OnClick({R.id.ll_my_set, R.id.iv_my_message,R.id.ll_my_dfh, R.id.ll_my_yfh, R.id.tv_my_dfk, R.id.tv_my_dfh, R.id.tv_my_dsh, R.id.ll_my_userinfo, R.id.ll_my_address, R.id.ll_my_version})
    public void onViewClicked(View view) {
        Intent intent = new Intent(getActivity(), MyOrderActivity.class);
        switch (view.getId()) {
            case R.id.ll_my_set://设置
                startActivity(SetActivity.class);
                break;
            case R.id.iv_my_message://右上角消息
                startActivity(MessageActivity.class);
                break;
            case R.id.ll_my_dfh://待发货

                break;
            case R.id.ll_my_yfh://已发货

                break;
            case R.id.tv_my_dfk://待付款
//                intent.putExtra("flag",1);
                startActivity(intent);
                break;
            case R.id.tv_my_dfh://待发货
                intent.putExtra("flag", 1);
                startActivity(intent);
                break;
            case R.id.tv_my_dsh://待收货
                intent.putExtra("flag", 2);
                startActivity(intent);
                break;
            case R.id.ll_my_userinfo://点击图像和昵称跳转个人资料
                startActivity(PersonalDataActivity.class);
                break;
            case R.id.ll_my_address://常用地址
                startActivity(UsualAddressActivity.class);
                break;
            case R.id.ll_my_version://版本更新
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
                if (person3.getStatus() == 200) {//查询个人信息
                    RegisterBean.DataBean personData = person3.getData();
                    SPUtils.putString("bean", new Gson().toJson(personData));

                    setUserBean();

                }
                break;


        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {

    }


}