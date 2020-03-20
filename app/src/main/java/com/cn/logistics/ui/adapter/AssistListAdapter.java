package com.cn.logistics.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cn.logistics.R;
import com.cn.logistics.bean.ViceAccountListBean;
import com.hjq.image.ImageLoader;

/**
 * Created by Xie JiaBin on 2019/12/19.
 */
public class AssistListAdapter extends BaseQuickAdapter<ViceAccountListBean.DataBean, BaseViewHolder> {

    private Context context;

    public AssistListAdapter(Context context) {
        super(R.layout.item_assist);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ViceAccountListBean.DataBean item) {

        ImageView imageView = helper.getView(R.id.item_assist_img);
        ImageLoader.with(context).load(item.getHeadImg())
                .error(context.getResources().getDrawable(R.drawable.ic_head_placeholder))
                .placeholder(context.getResources().getDrawable(R.drawable.ic_head_placeholder))
                .into(imageView);
        helper.setText(R.id.item_assist_phone,""+item.getUserphone());
//        SlidingButtonView sbv = helper.getView(R.id.item_all_task_slid);
//        sbv.setOnTouchListener();

    }
}
