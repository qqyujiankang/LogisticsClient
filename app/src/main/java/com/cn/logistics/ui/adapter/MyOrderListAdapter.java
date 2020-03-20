package com.cn.logistics.ui.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cn.logistics.R;
import com.cn.logistics.bean.MyOrderListBean;

/**
 * Created by Xie JiaBin on 2019/12/19.
 */
public class MyOrderListAdapter extends BaseQuickAdapter<MyOrderListBean, BaseViewHolder> {

    private Context context;

    public MyOrderListAdapter(Context context) {
        super(R.layout.item_my_order);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyOrderListBean item) {

//        ImageView imageView = helper.getView(R.id.item_task_img);
//        ImageLoader.with(context).load(item.getImg_url()).into(imageView);
//        helper.setText(R.id.item_task_name,""+item.getTask_name());
//        SlidingButtonView sbv = helper.getView(R.id.item_all_task_slid);
//        sbv.setOnTouchListener();



    }
}
