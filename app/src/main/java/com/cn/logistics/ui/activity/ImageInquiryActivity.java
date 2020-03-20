package com.cn.logistics.ui.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;

import com.cn.logistics.R;
import com.cn.logistics.bean.StatusBean;
import com.cn.logistics.common.MyActivity;
import com.cn.logistics.network.Constant;
import com.cn.logistics.network.GsonUtils;
import com.cn.logistics.network.ServerUrl;
import com.cn.logistics.presenter.FileOperationPresenetr;
import com.cn.logistics.presenter.PublicInterfaceePresenetr;
import com.cn.logistics.presenter.view.FileOperationView;
import com.cn.logistics.presenter.view.PublicInterfaceView;
import com.hjq.image.ImageLoader;
import com.hjq.toast.ToastUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 图片询价
 */
public final class ImageInquiryActivity extends MyActivity implements PublicInterfaceView, FileOperationView {

    @BindView(R.id.inquiry_img)
    ImageView inquiryImg;
    @BindView(R.id.inquiry_add_image)
    LinearLayout inquiryAddImage;
    @BindView(R.id.btn_inquiry_commit)
    AppCompatButton btnInquiryCommit;
    private String imlurl = "";
    private PublicInterfaceePresenetr presenetr;
    private FileOperationPresenetr filePresenetr;
    private String mAvatarUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_inquiry;
    }

    @Override
    protected void initView() {
        presenetr = new PublicInterfaceePresenetr(this);
        filePresenetr = new FileOperationPresenetr(this);

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

    @OnClick({R.id.inquiry_add_image, R.id.btn_inquiry_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.inquiry_add_image:
                PhotoActivity.start(getActivity(), new PhotoActivity.OnPhotoSelectListener() {

                    @Override
                    public void onSelect(List<String> data) {
                        mAvatarUrl = data.get(0);
                        showLoading();
                        ImageLoader.with(ImageInquiryActivity.this)
                                .load(new File(mAvatarUrl))
                                .into(inquiryImg);
                        inquiryAddImage.setVisibility(View.GONE);
                        filePresenetr.uploadSingleFileRequest(ImageInquiryActivity.this,"file",new File(mAvatarUrl), ServerUrl.upload,Constant.upload);

                    }

                    @Override
                    public void onCancel() {
                    }
                });
                break;
            case R.id.btn_inquiry_commit:
                //提交查询
                break;
        }
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String , Object> paramsmap = new HashMap<>();
        switch (tag){
            case Constant.addScreenshotCustom:
                paramsmap.put("userid",userBean().getId());
                paramsmap.put("img_url",imlurl);
                return paramsmap;




        }
        return null;
    }

    @Override
    public void onPublicInterfaceSuccess(String data, int tag) {
        switch (tag) {
            case Constant.addScreenshotCustom:
                StatusBean person = GsonUtils.getPerson(data, StatusBean.class);
                if (person.getStatus()==200){
                    toast(""+person.getMsg());

                }

                break;
        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {
        showComplete();
    }

    @Override
    public void FileOperationSuccess(Object data, int tag) {
        switch (tag) {
            case Constant.upload:
                showComplete();
                StatusBean person = GsonUtils.getPerson((String) data, StatusBean.class);
                if (person.getStatus()==200){
                    ToastUtils.show(person.getMsg());
                    mAvatarUrl = (String) person.getData();
                    imlurl = (String) person.getData();
                    ImageLoader.with(getActivity())
                            .load(mAvatarUrl)
                            .error(getResources().getDrawable(R.drawable.ic_head_placeholder))
                            .placeholder(getResources().getDrawable(R.drawable.ic_head_placeholder))
                            .circle()
                            .into(inquiryImg);
                    presenetr.getPostHeaderRequest(ImageInquiryActivity.this, ServerUrl.addScreenshotCustom, Constant.addScreenshotCustom);

                }

                break;


        }
    }

    @Override
    public void FileOperationProgress(float progress, int tag) {

    }

    @Override
    public void FileOperationError(String error, int tag) {
        showComplete();
    }
}