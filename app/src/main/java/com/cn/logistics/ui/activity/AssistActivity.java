package com.cn.logistics.ui.activity;


import android.os.Bundle;
import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cn.logistics.R;
import com.cn.logistics.bean.StatusBean;
import com.cn.logistics.bean.ViceAccountListBean;
import com.cn.logistics.common.MyActivity;
import com.cn.logistics.network.Constant;
import com.cn.logistics.network.GsonUtils;
import com.cn.logistics.network.ServerUrl;
import com.cn.logistics.presenter.PublicInterfaceePresenetr;
import com.cn.logistics.presenter.view.PublicInterfaceView;
import com.cn.logistics.ui.adapter.AssistListAdapter;
import com.cn.logistics.ui.dialog.InputDialog2;
import com.hjq.dialog.base.BaseDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 辅助账号
 */
public final class AssistActivity extends MyActivity implements PublicInterfaceView {


    @BindView(R.id.assist_recycler)
    RecyclerView assistRecycler;
    @BindView(R.id.cd_assist_add)
    CardView cdAssistAdd;
    private AssistListAdapter adapter;
    private PublicInterfaceePresenetr presenetr;
    private String userPhone;
    private String password;
    private int page = 1;
    private List<ViceAccountListBean.DataBean> list = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_assist;
    }

    @Override
    protected void initView() {

        presenetr = new PublicInterfaceePresenetr(this);

        assistRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AssistListAdapter(this);
        assistRecycler.setAdapter(adapter);

    }

    @Override
    protected void initData() {

//        list = new ArrayList<>();
//        list.add(new AssistListBean());
//        list.add(new AssistListBean());
//        list.add(new AssistListBean());
//        list.add(new AssistListBean());
//        adapter.setNewData(list);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null==presenetr){
            presenetr = new PublicInterfaceePresenetr(this);
        }
        if(null==adapter){
            adapter = new AssistListAdapter(this);
        }

        presenetr.getPostRequest(AssistActivity.this,ServerUrl.selectViceAccount,Constant.selectViceAccount);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.cd_assist_add)
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.cd_assist_add:
                new InputDialog2.Builder(this)
                        // 提示可以不用填写
                        .setHint("请输入账号")
                        .setHint2("请输入密码")
                        // 确定按钮文本
                        .setConfirm(getString(R.string.common_confirm))
                        // 设置 null 表示不显示取消按钮
                        .setCancel(getString(R.string.common_cancel))
                        //.setAutoDismiss(false) // 设置点击按钮后不关闭对话框
                        .setListener(new InputDialog2.OnListener() {

                            @Override
                            public void onConfirm(BaseDialog dialog, String content,String content2) {
                                toast("确定了：" + content+"===="+content2);

                                userPhone = content;
                                password = content2;

                                showLoading();
                                presenetr.getPostHeaderRequest(AssistActivity.this, ServerUrl.addViceAccount, Constant.addViceAccount);

                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
//                                toast("取消了");
                            }
                        })
                        .show();
                break;



        }
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String,Object> paramsMap = new HashMap<>();
        switch (tag){
            case Constant.addViceAccount:
                paramsMap.put("userid",userBean().getId());
                paramsMap.put("userphone",userPhone);
                paramsMap.put("password",password);
                return paramsMap;
            case Constant.selectViceAccount:
                paramsMap.put("userid",userBean().getId());
                paramsMap.put("page",page);
                paramsMap.put("rows",10);
                return paramsMap;


        }

        return null;
    }

    @Override
    public void onPublicInterfaceSuccess(String data, int tag) {
        switch (tag){
            case Constant.addViceAccount:
                showComplete();
                StatusBean person = GsonUtils.getPerson(data, StatusBean.class);
                if (person.getStatus()==200){
                    toast(person.getMsg());
                    presenetr.getPostRequest(AssistActivity.this,ServerUrl.selectViceAccount,Constant.selectViceAccount);

                }else {
                    toast(person.getMsg());
                }

                break;
            case Constant.selectViceAccount:
                ViceAccountListBean listBean = GsonUtils.getPerson(data, ViceAccountListBean.class);

                if (listBean.getStatus()==200&&listBean.getData()!=null){
                    list = listBean.getData();

                    adapter.setNewData(list);

                }

                break;


        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {

    }
}