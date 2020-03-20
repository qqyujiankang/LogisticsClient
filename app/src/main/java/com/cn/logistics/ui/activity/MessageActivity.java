package com.cn.logistics.ui.activity;


import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cn.logistics.R;
import com.cn.logistics.bean.MessageListBean;
import com.cn.logistics.common.MyActivity;
import com.cn.logistics.network.Constant;
import com.cn.logistics.network.GsonUtils;
import com.cn.logistics.network.ServerUrl;
import com.cn.logistics.presenter.PublicInterfaceePresenetr;
import com.cn.logistics.presenter.view.PublicInterfaceView;
import com.cn.logistics.ui.adapter.MessageListAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 消息列表
 */
public final class MessageActivity extends MyActivity implements PublicInterfaceView {

    @BindView(R.id.message_recycler)
    RecyclerView messageRecycler;
    private MessageListAdapter adapter;
    private PublicInterfaceePresenetr presenetr;
    private int page = 1;
    private List<MessageListBean.DataBean> beanList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void initView() {

        presenetr = new PublicInterfaceePresenetr(this);

        messageRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MessageListAdapter(this);
        messageRecycler.setAdapter(adapter);
    }

    @Override
    protected void initData() {

//        List<MessageListBean> list = new ArrayList<>();
//        list.add(new MessageListBean());
//        list.add(new MessageListBean());
//        list.add(new MessageListBean());
//
//        adapter.setNewData(list);

        presenetr.getPostRequest(MessageActivity.this, ServerUrl.selectMessageLists,Constant.selectMessageLists);

    }

    @Override
    protected void onResume() {
        super.onResume();



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String,Object> paramsmap = new HashMap<>();
        switch (tag){
            case Constant.selectMessageLists:
                paramsmap.put("userid",userBean().getId());
                paramsmap.put("page",page);
                paramsmap.put("rows",10);
                return paramsmap;


        }

        return null;
    }

    @Override
    public void onPublicInterfaceSuccess(String data, int tag) {
        switch (tag){
            case Constant.selectMessageLists:
                MessageListBean person = GsonUtils.getPerson(data, MessageListBean.class);
                if (person.getStatus()==200){
                    beanList = person.getData();
                    if (beanList!=null&&beanList.size()>0){

                        adapter.setNewData(beanList);
                    }

                }
                break;


        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {

    }
}