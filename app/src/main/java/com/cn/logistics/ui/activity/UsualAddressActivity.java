package com.cn.logistics.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cn.logistics.R;
import com.cn.logistics.bean.AddressListBean;
import com.cn.logistics.common.MyActivity;
import com.cn.logistics.network.Constant;
import com.cn.logistics.network.GsonUtils;
import com.cn.logistics.network.ServerUrl;
import com.cn.logistics.presenter.PublicInterfaceePresenetr;
import com.cn.logistics.presenter.view.PublicInterfaceView;
import com.cn.logistics.ui.adapter.AddressListAdapter;

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
 * desc   : 常用地址
 */
public final class UsualAddressActivity extends MyActivity implements BaseQuickAdapter.OnItemChildClickListener, PublicInterfaceView {


    @BindView(R.id.usual_null_status)
    LinearLayout usualNullStatus;
    @BindView(R.id.btn_usual_add)
    AppCompatButton btnUsualAdd;
    @BindView(R.id.usual_recycler)
    RecyclerView usualRecycler;
    private AddressListAdapter adapter;
    private PublicInterfaceePresenetr presenetr;
    private List<AddressListBean.DataBean> list = new ArrayList<>();
    private int page = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_usual_address;
    }

    @Override
    protected void initView() {

        presenetr = new PublicInterfaceePresenetr(this);

        usualRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AddressListAdapter(this);
        adapter.setOnItemChildClickListener(this);
        usualRecycler.setAdapter(adapter);

    }

    @Override
    protected void initData() {



    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null==presenetr){
            presenetr = new PublicInterfaceePresenetr(this);
        }
        if(null==adapter){
            adapter = new AddressListAdapter(this);
        }

        presenetr.getPostRequest(this, ServerUrl.selectAddressByUserid, Constant.selectAddressByUserid);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_usual_add})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.btn_usual_add://添加地址
                startActivity(AddressDetailActivity.class);
                break;
        }
    }

    private void setAddress(String name, String phone, String address) {
        Intent info = new Intent();
        info.putExtra("name", name);
        info.putExtra("phone", phone);
        info.putExtra("address", address);
        setResult(5003, info);
        finish();

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.item_address_edit:
//                toast("编辑");
                Intent intent = new Intent(UsualAddressActivity.this, AddressDetailActivity2.class);
                intent.putExtra("item",list.get(position));
                startActivity(intent);
                break;



        }
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String,Object> paramsMap = new HashMap<>();
        switch (tag){
            case Constant.selectAddressByUserid:
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
            case Constant.selectAddressByUserid:
                AddressListBean listBean = GsonUtils.getPerson(data, AddressListBean.class);

                if (listBean.getStatus()==200&&listBean.getData()!=null){
                    usualNullStatus.setVisibility(View.GONE);
                    usualRecycler.setVisibility(View.VISIBLE);

                    list = listBean.getData();

                    adapter.setNewData(list);
                }else {

                    usualNullStatus.setVisibility(View.VISIBLE);
                    usualRecycler.setVisibility(View.GONE);

                }

                break;


        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {

    }
}