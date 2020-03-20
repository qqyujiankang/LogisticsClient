package com.cn.logistics.ui.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;

import com.cn.logistics.R;
import com.cn.logistics.common.MyActivity;
import com.hjq.image.ImageLoader;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 提交文件
 */
public final class CarryFilesActivity extends MyActivity {

    @BindView(R.id.carry_img)
    ImageView inCarryImg;
    @BindView(R.id.carry_add_image)
    LinearLayout carryAddImage;
    @BindView(R.id.btn_carry_commit)
    AppCompatButton btnCarryCommit;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_inquiry;
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

    @OnClick({R.id.carry_add_image, R.id.btn_carry_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.carry_add_image:
                PhotoActivity.start(getActivity(), new PhotoActivity.OnPhotoSelectListener() {

                    @Override
                    public void onSelect(List<String> data) {

                        ImageLoader.with(CarryFilesActivity.this)
                                .load(new File(data.get(0)))
                                .into(inCarryImg);

                        carryAddImage.setVisibility(View.GONE);

                    }

                    @Override
                    public void onCancel() {
                    }
                });
                break;
            case R.id.btn_carry_commit:
                //提交文件
                break;
        }
    }
}