package com.cn.logistics.ui.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cn.logistics.R;
import com.cn.logistics.bean.MessageListBean;

/**
 * Created by Xie JiaBin on 2019/12/19.
 */
public class MessageListAdapter extends BaseQuickAdapter<MessageListBean.DataBean, BaseViewHolder> {

    private Context context;

    public MessageListAdapter(Context context) {
        super(R.layout.item_message);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageListBean.DataBean item) {

//        ImageView imageView = helper.getView(R.id.item_task_img);
//        ImageLoader.with(context).load(item.getImg_url()).into(imageView);
        helper.setText(R.id.item_message_title,""+item.getTitle());
        helper.setText(R.id.item_message_content,""+item.getContent());
        helper.setText(R.id.item_message_week,""+item.getWeek());
//        SlidingButtonView sbv = helper.getView(R.id.item_all_task_slid);
//        sbv.setOnTouchListener();



    }
}
