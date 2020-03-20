package com.cn.logistics.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.cn.logistics.R;
import com.cn.logistics.common.MyActivity;
import com.cn.logistics.other.IntentKey;
import com.hjq.toast.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 自定义查询
 */
public final class QueryActivity extends MyActivity {

    @BindView(R.id.start_address_query)
    TextView startAddress;
    @BindView(R.id.end_address_query)
    TextView endAddress;
    @BindView(R.id.iv_query_jian_query)
    ImageView ivQueryJian;
    @BindView(R.id.et_query_sum_query)
    EditText etQuerySum;
    @BindView(R.id.iv_query_jia_query)
    ImageView ivQueryJia;
    @BindView(R.id.radioGroup1_query)
    RadioGroup radioGroup1;
    @BindView(R.id.radioGroup2_query)
    RadioGroup radioGroup2;
    @BindView(R.id.radioGroup3_query)
    RadioGroup radioGroup3;
    @BindView(R.id.cd_order_commit_query)
    CardView cdOrderCommit;
    private int num1=1;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_query;
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

    @Override
    public void onRightClick(View v) {
        super.onRightClick(v);
        startActivity(MessageActivity.class);
    }

    @OnClick({R.id.start_address_query, R.id.end_address_query, R.id.cd_order_commit_query,R.id.iv_query_jian_query,R.id.iv_query_jia_query})
    public void onViewClicked(View v) {
        Intent intent = new Intent(QueryActivity.this, StartAddressActivity.class);
        switch (v.getId()) {
            //件数-
            case R.id.iv_query_jian_query:
                iv_1();
                break;
            //件数+
            case R.id.iv_query_jia_query:
                iv_2();
                break;
            case R.id.start_address_query:

                startActivityForResult(intent,5008);

                break;
            case R.id.end_address_query:

                startActivityForResult(intent,5009);

                break;

            case R.id.iv_inquiry://图片询价
                startActivity(ImageInquiryActivity.class);
                break;
            case R.id.cd_order_commit_query://立即查询

//                radioGroup1.getCheckedRadioButtonId();

                ToastUtils.show("立即查询"+radioGroup1.getCheckedRadioButtonId());



//                if (ApkUtils.isQQClientAvailable(getActivity())) {
//                    String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=3287902027&version=1";
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
//                } else {
//                    ToastUtils.show("请安装QQ客户端");
//                }

//                startActivity(OrdersActivity.class);
                break;


        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


            if (resultCode == RESULT_OK){
                String extra = data.getStringExtra(IntentKey.CITY);
                if (requestCode==5008){
                    startAddress.setText(""+extra);
                }else if (requestCode==5009){
                    endAddress.setText(""+extra);
                }
            }




    }

    public void iv_1(){

        num1=Integer.parseInt(etQuerySum.getText().toString());
        if(num1>1){
            num1-=1;
        }

        etQuerySum.setText(Integer.toString(num1));

    }
    public void iv_2(){

        num1=Integer.parseInt(etQuerySum.getText().toString());
        if(num1<999){
            num1+=1;
        }

        etQuerySum.setText(Integer.toString(num1));

    }
}