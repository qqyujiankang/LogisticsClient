package com.cn.logistics.ui.activity;


import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatButton;

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
 * desc   : 意见反馈
 */
public final class FeedbackActivity extends MyActivity implements PublicInterfaceView {

    @BindView(R.id.et_special_txt)
    EditText etSpecialTxt;
    @BindView(R.id.btn_special_commit)
    AppCompatButton btnSpecialCommit;
    private PublicInterfaceePresenetr presenetr;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
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

    @OnClick(R.id.btn_special_commit)
    public void onViewClicked() {

        if (TextUtils.isEmpty(etSpecialTxt.getText().toString().trim())){
            ToastUtils.show("请输入反馈内容！！！");
            return;
        }

        presenetr.getPostHeaderRequest(FeedbackActivity.this, ServerUrl.addFeedBack,Constant.addFeedBack);

    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> paramsMap = new HashMap<>();
        switch (tag) {
            case Constant.addFeedBack:
                paramsMap.put("userid", userBean().getId());
                paramsMap.put("content", etSpecialTxt.getText().toString());
                return paramsMap;


        }
        return null;
    }

    @Override
    public void onPublicInterfaceSuccess(String data, int tag) {
        switch (tag) {
            case Constant.addFeedBack:
                showComplete();
                StatusBean person = GsonUtils.getPerson(data, StatusBean.class);
                if (person.getStatus() == 200) {
                    ToastUtils.show(person.getMsg());
                    FeedbackActivity.this.finish();
                } else {
                    ToastUtils.show(person.getMsg());
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