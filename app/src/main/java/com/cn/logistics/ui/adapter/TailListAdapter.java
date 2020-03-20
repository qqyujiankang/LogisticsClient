package com.cn.logistics.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cn.logistics.R;
import com.cn.logistics.bean.OrderListBean;
import com.hjq.image.ImageLoader;

/**
 * Created by Xie JiaBin on 2019/12/19.
 */
public class TailListAdapter extends BaseQuickAdapter<OrderListBean.DataBean, BaseViewHolder> {

    private Context context;

    public TailListAdapter(Context context) {
        super(R.layout.item_tail_list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderListBean.DataBean item) {

        ImageView imageView = helper.getView(R.id.item_head_img);
        ImageLoader.with(context).load(item.getHead_img())
                .error(context.getResources().getDrawable(R.drawable.ic_head_placeholder))
                .placeholder(context.getResources().getDrawable(R.drawable.ic_head_placeholder))
                .into(imageView);
        helper.setText(R.id.item_send_city,""+item.getSendgoodscity()+item.getSendgoodsarea());
        helper.setText(R.id.item_pick_city,""+item.getPickgoodscity()+item.getPickgoodsarea());
        helper.setText(R.id.item_order_code,""+item.getOrdercode());
        helper.setText(R.id.item_order_weight,""+item.getGoodsweight());
        helper.setText(R.id.item_order_number,""+item.getGoodsnumber());
        helper.setText(R.id.item_order_volume,""+item.getGoodsvolume());
//        SlidingButtonView sbv = helper.getView(R.id.item_all_task_slid);
//        sbv.setOnTouchListener();
        LinearLayout view = helper.getView(R.id.ll_paly_phone);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone(""+item.getUserphone());
            }
        });

    }

    /**
     * 拨打电话（直接拨打电话）
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum){
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);
    }
}
