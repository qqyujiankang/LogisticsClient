package com.cn.logistics.ui.fragment;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cn.logistics.R;
import com.cn.logistics.bean.MyOrderListBean;
import com.cn.logistics.common.MyLazyFragment;
import com.cn.logistics.ui.activity.CopyActivity;
import com.cn.logistics.ui.adapter.MyOrderListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 待付款
 */
public final class DfkFragment extends MyLazyFragment<CopyActivity> {

    @BindView(R.id.my_order)
    RecyclerView myOrder;
    private MyOrderListAdapter adapter;

    public static DfkFragment newInstance() {
        return new DfkFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_order;
    }

    @Override
    protected void initView() {

        myOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MyOrderListAdapter(getActivity());
        myOrder.setAdapter(adapter);

    }

    @Override
    protected void initData() {

        List<MyOrderListBean> list = new ArrayList<>();
        list.add(new MyOrderListBean());
        list.add(new MyOrderListBean());
        adapter.setNewData(list);

    }
}