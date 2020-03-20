package com.cn.logistics.ui.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cn.logistics.R;
import com.cn.logistics.bean.AddressListBean;

/**
 * Created by Xie JiaBin on 2019/12/19.
 */
public class AddressListAdapter extends BaseQuickAdapter<AddressListBean.DataBean, BaseViewHolder> {

    private Context context;

    public AddressListAdapter(Context context) {
        super(R.layout.item_address_list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressListBean.DataBean item) {

//        ImageView imageView = helper.getView(R.id.item_task_img);
//        ImageLoader.with(context).load(item.getImg_url()).into(imageView);
        helper.setText(R.id.item_address_name,""+item.getUsername());
        helper.setText(R.id.item_address_phone,""+item.getUserphone());
        helper.setText(R.id.item_address_address,""+item.getProvince()+" "+item.getCity()+" "+item.getArea()+" "+item.getAddressDetail());
//        SlidingButtonView sbv = helper.getView(R.id.item_all_task_slid);
//        sbv.setOnTouchListener();

        helper.addOnClickListener(R.id.item_address_edit);

    }
}
