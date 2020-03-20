package com.cn.logistics.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.cn.logistics.R;
import com.cn.logistics.common.MyActivity;
import com.cn.logistics.ui.dialog.PayDialog;
import com.hjq.dialog.base.BaseDialog;
import com.hjq.widget.view.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 立即下单
 */
public final class OrdersActivity extends MyActivity {

    @BindView(R.id.btn_orders_commit)
    AppCompatButton btnOrdersCommit;
    @BindView(R.id.order_sel_address1)
    ImageView orderSelAddress1;
    @BindView(R.id.order_sel_address2)
    ImageView orderSelAddress2;
    @BindView(R.id.cet_s_name)
    ClearEditText cetSName;
    @BindView(R.id.cet_s_phone)
    ClearEditText cetSPhone;
    @BindView(R.id.cet_s_address)
    ClearEditText cetSAddress;
    @BindView(R.id.cet_f_name)
    ClearEditText cetFName;
    @BindView(R.id.cet_f_phone)
    ClearEditText cetFPhone;
    @BindView(R.id.cet_f_address)
    ClearEditText cetFAddress;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_orders;
    }

    @Override
    protected void initView() {

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

    @OnClick({R.id.btn_orders_commit, R.id.order_sel_address1, R.id.order_sel_address2})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.btn_orders_commit:
                new PayDialog.Builder(this).setListener(new PayDialog.OnListener() {
                    @Override
                    public void onPay(BaseDialog dialog, int flag) {
                        if (flag == 1) {
                            //微信支付
                        } else if (flag == 2) {
                            //支付宝支付
                        }
                        dialog.dismiss();
                    }
                }).show();
                break;
            case R.id.order_sel_address1://收件人信息
                Intent intent = new Intent(OrdersActivity.this, UsualAddressActivity.class);
                startActivityForResult(intent, 5001);
                break;
            case R.id.order_sel_address2://发件人信息
                Intent intent2 = new Intent(OrdersActivity.this, UsualAddressActivity.class);
                startActivityForResult(intent2, 5002);
                break;


        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 5003) {
            if (requestCode == 5001) {
                //收件人信息
                cetSName.setText(data.getStringExtra("name"));
                cetSPhone.setText(data.getStringExtra("phone"));
                cetSAddress.setText(data.getStringExtra("address"));
            } else if (requestCode == 5002) {
                //发件人信息
                cetFName.setText(data.getStringExtra("name"));
                cetFPhone.setText(data.getStringExtra("phone"));
                cetFAddress.setText(data.getStringExtra("address"));
            }
        }

    }
}