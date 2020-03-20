package com.cn.logistics.ui.dialog;

import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;

import com.cn.logistics.R;
import com.cn.logistics.common.MyDialogFragment;
import com.hjq.dialog.base.BaseDialog;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : 可进行拷贝的副本
 */
public final class PayDialog {

    public static final class Builder
            extends MyDialogFragment.Builder<Builder> implements View.OnClickListener {

        private final LinearLayout pay_ll_wx;
        private final ImageView pay_wx_sel;
        private final LinearLayout pay_ll_zfb;
        private final ImageView pay_zfb_sel;
        private final CardView pay_cancl;
        private final CardView pay;
        private int flag = 1;//1微信支付  2支付宝支付

        public Builder(FragmentActivity activity) {
            super(activity);

            setContentView(R.layout.dialog_pay);
            setAnimStyle(BaseDialog.AnimStyle.BOTTOM);
            setGravity(Gravity.BOTTOM);


            pay_ll_wx = findViewById(R.id.pay_ll_wx);
            pay_wx_sel = findViewById(R.id.pay_wx_sel);
            pay_ll_zfb = findViewById(R.id.pay_ll_zfb);
            pay_zfb_sel = findViewById(R.id.pay_zfb_sel);

            pay_cancl = findViewById(R.id.pay_cancl);
            pay = findViewById(R.id.pay);

            pay_ll_wx.setOnClickListener(this);
            pay_ll_zfb.setOnClickListener(this);

            pay_cancl.setOnClickListener(this);
            pay.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.pay_ll_wx://微信
                    pay_wx_sel.setVisibility(View.VISIBLE);
                    pay_zfb_sel.setVisibility(View.INVISIBLE);
                    flag = 1;
                    break;
                case R.id.pay_ll_zfb://支付宝
                    pay_wx_sel.setVisibility(View.INVISIBLE);
                    pay_zfb_sel.setVisibility(View.VISIBLE);
                    flag = 2;
                    break;
                case R.id.pay_cancl:
                    dismiss();
                    break;
                case R.id.pay:
                    mListener.onPay(getDialog(),flag);
                    break;

            }
        }

        private PayDialog.OnListener mListener;
        public Builder setListener(OnListener listener) {
            mListener = listener;
            return this;
        }
    }



    public interface OnListener {
        /**
         * 选择支付方式回调
         * @param dialog
         * @param flag  1微信支付  2支付宝支付   默认使用微信支付
         */
        void onPay(BaseDialog dialog, int flag);
    }
}