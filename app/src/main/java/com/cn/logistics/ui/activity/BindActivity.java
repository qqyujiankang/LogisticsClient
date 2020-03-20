package com.cn.logistics.ui.activity;


import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.cn.logistics.R;
import com.cn.logistics.bean.StatusBean;
import com.cn.logistics.common.MyActivity;
import com.cn.logistics.network.Constant;
import com.cn.logistics.network.GsonUtils;
import com.cn.logistics.network.ServerUrl;
import com.cn.logistics.presenter.PublicInterfaceePresenetr;
import com.cn.logistics.presenter.view.PublicInterfaceView;
import com.hjq.toast.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 可进行拷贝的副本
 */
public final class BindActivity extends MyActivity implements PublicInterfaceView {

    @BindView(R.id.et_bind_realname)
    EditText etBindRealname;
    @BindView(R.id.et_bind_accountno)
    EditText etBindAccountno;
    @BindView(R.id.btn_bind_commit)
    Button btnBindCommit;
    private PublicInterfaceePresenetr presenetr;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind;
    }

    @Override
    protected void initView() {
        presenetr = new PublicInterfaceePresenetr(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String,Object> paramsMap = new HashMap<>();
        switch (tag){
            case Constant.updateWechat:
                paramsMap.put("userid",userBean().getId());
                paramsMap.put("wechatno",etBindAccountno.getText().toString().trim());
                return paramsMap;



        }
        return null;
    }

    @Override
    public void onPublicInterfaceSuccess(String data, int tag) {
        switch (tag){
            case Constant.updateWechat:
                showComplete();
                StatusBean person = GsonUtils.getPerson(data, StatusBean.class);
                if (person.getStatus() == 200) {
                    ToastUtils.show(person.getMsg());
                    BindActivity.this.finish();
                } else {
                    ToastUtils.show(person.getMsg());
                }

                break;



        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {

        showComplete();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_bind_commit)
    public void onViewClicked() {

        if (TextUtils.isEmpty(etBindRealname.getText().toString().trim())){
            toast("请输入姓名");
            return;
        }
        if (TextUtils.isEmpty(etBindAccountno.getText().toString().trim())){
            toast("请输入账号");
            return;
        }

        presenetr.getPostHeaderRequest(BindActivity.this, ServerUrl.updateWechat, Constant.updateWechat);

    }
}