package com.cn.logistics.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;

import com.cn.logistics.R;
import com.cn.logistics.bean.RegisterBean;
import com.cn.logistics.bean.StatusBean;
import com.cn.logistics.common.MyActivity;
import com.cn.logistics.helper.ActivityStackManager;
import com.cn.logistics.network.Constant;
import com.cn.logistics.network.GsonUtils;
import com.cn.logistics.network.ServerUrl;
import com.cn.logistics.presenter.FileOperationPresenetr;
import com.cn.logistics.presenter.PublicInterfaceePresenetr;
import com.cn.logistics.presenter.view.FileOperationView;
import com.cn.logistics.presenter.view.PublicInterfaceView;
import com.cn.logistics.ui.dialog.AddressDialog;
import com.cn.logistics.ui.dialog.InputDialog;
import com.cn.logistics.ui.dialog.MessageDialog;
import com.cn.logistics.utils.L;
import com.cn.logistics.utils.SPUtils;
import com.google.gson.Gson;
import com.hjq.dialog.base.BaseDialog;
import com.hjq.image.ImageLoader;
import com.hjq.toast.ToastUtils;
import com.hjq.widget.layout.SettingBar;
import com.qiyukf.unicorn.api.Unicorn;

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
 * time   : 2019/04/20
 * desc   : 设置
 */
public final class SetActivity extends MyActivity implements PublicInterfaceView, FileOperationView {

    @BindView(R.id.iv_set_avatar)
    ImageView mAvatarView;
    @BindView(R.id.sb_set_name)
    SettingBar mNameView;
    @BindView(R.id.sb_set_address)
    SettingBar mAddressView;
    @BindView(R.id.sb_set_phone)
    SettingBar mPhoneView;
    @BindView(R.id.sb_set_user)
    SettingBar sbSetUser;
    @BindView(R.id.sb_set_yjfk)
    SettingBar sbSetYjfk;
    @BindView(R.id.login_exit)
    CardView loginExit;

    private String mAvatarUrl;
    private PublicInterfaceePresenetr presenetr;
    private FileOperationPresenetr filePresenetr;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set;
    }

    @Override
    protected void initView() {

        presenetr = new PublicInterfaceePresenetr(this);
        filePresenetr = new FileOperationPresenetr(this);

        if (isLogin()){
            show();
        }


    }

    private void show() {
        if (userBean().getIsVip() == 1) {//公司名称
            mNameView.setLeftText("企业名称");
        } else {//昵称
            mNameView.setLeftText("昵称");
        }
        mNameView.setRightText((String) userBean().getNickname());
        mAvatarUrl = (String) userBean().getHeadImg();
        L.e("====headimage========="+mAvatarUrl);
        ImageLoader.with(getActivity())
                .load(mAvatarUrl)
                .error(getResources().getDrawable(R.drawable.ic_head_placeholder))
                .placeholder(getResources().getDrawable(R.drawable.ic_head_placeholder))
                .circle()
                .into(mAvatarView);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_set_avatar, R.id.fl_set_head, R.id.sb_set_name,
            R.id.sb_set_address, R.id.sb_set_phone, R.id.sb_set_user,
            R.id.sb_set_yjfk,R.id.login_exit})
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_set_avatar:
                if (mAvatarUrl != null && !"".equals(mAvatarUrl)) {
                    // 查看头像
                    ImageActivity.start(getActivity(), mAvatarUrl);
                } else {
                    // 选择头像
                    onClick(findViewById(R.id.fl_set_head));
                }
                break;
            case R.id.fl_set_head:
                PhotoActivity.start(getActivity(), new PhotoActivity.OnPhotoSelectListener() {

                    @Override
                    public void onSelect(List<String> data) {
                        mAvatarUrl = data.get(0);
                        showLoading();
                        filePresenetr.uploadSingleFileRequest(SetActivity.this,"file",new File(mAvatarUrl),ServerUrl.upload,Constant.upload);

//                        ImageLoader.with(getActivity())
//                                .load(new File(mAvatarUrl))
//                                .placeholder(getResources().getDrawable(R.drawable.ic_head_placeholder))
//                                .into(mAvatarView);
                    }

                    @Override
                    public void onCancel() {
                    }
                });
                break;
            case R.id.sb_set_name:
                new InputDialog.Builder(this)
                        // 标题可以不用填写
                        .setTitle(getString(R.string.personal_data_name_hint))
                        .setContent(mNameView.getRightText())
                        //.setHint(getString(R.string.personal_data_name_hint))
                        //.setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        //.setCancel("取消")
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setListener(new InputDialog.OnListener() {

                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if (!mNameView.getRightText().equals(content)) {
                                    mNameView.setRightText(content);
                                    showLoading();
                                    presenetr.getPostHeaderRequest(SetActivity.this,ServerUrl.updateNickname,Constant.updateNickname);

                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                            }
                        })
                        .show();
                break;
            case R.id.sb_set_user://账号与安全
                startActivity(PersonalDataActivity.class);
                break;
            case R.id.sb_set_yjfk://意见反馈
                startActivity(FeedbackActivity.class);
                break;
            case R.id.sb_set_address:
                new AddressDialog.Builder(this)
                        //.setTitle("选择地区")
                        // 设置默认省份
                        .setProvince("广东省")
                        // 设置默认城市（必须要先设置默认省份）
                        .setCity("广州市")
                        // 不选择县级区域
                        //.setIgnoreArea()
                        .setListener(new AddressDialog.OnListener() {

                            @Override
                            public void onSelected(BaseDialog dialog, String province, String city, String area) {
                                String address = province + city + area;
                                if (!mAddressView.getRightText().equals(address)) {
                                    mAddressView.setRightText(address);
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                            }
                        })
                        .show();
                break;
            case R.id.sb_set_phone:
                // 先判断有没有设置过手机号
                if (true) {
                    startActivity(PhoneVerifyActivity.class);
                } else {
                    startActivity(PhoneResetActivity.class);
                }
                break;
            case R.id.login_exit:
//                SPUtils.remove("bean");
//                SetActivity.this.finish();
//                //七鱼   清除空账号信息，清除缓存
//                Unicorn.setUserInfo(null);
//                Unicorn.clearCache();
                new MessageDialog.Builder(this)
                        .setTitle("提示")
                        .setMessage("确认退出应用?")
                        .setListener(new MessageDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog) {
                                ActivityStackManager.getInstance().finishAllActivities();
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        }).show();
                break;

            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (null==presenetr){
            presenetr = new PublicInterfaceePresenetr(this);
        }
        presenetr.getPostRequest(SetActivity.this,ServerUrl.selectInfoByUserid,Constant.selectInfoByUserid);


    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> paramsMap = new HashMap<>();
        switch (tag) {
            case Constant.updateHeadImg:
                paramsMap.put("userid", userBean().getId());
                paramsMap.put("head_img", mAvatarUrl);
                return paramsMap;
            case Constant.updateNickname:
                paramsMap.put("userid", userBean().getId());
                paramsMap.put("nickname", mNameView.getRightText().toString());
                return paramsMap;
            case Constant.selectInfoByUserid:
                paramsMap.put("userid", userBean().getId());
                return paramsMap;

        }
        return null;
    }

    @Override
    public void onPublicInterfaceSuccess(String data, int tag) {
        switch (tag) {
            case Constant.updateHeadImg://修改头像

            case Constant.updateNickname://修改昵称
                showComplete();
                StatusBean person = GsonUtils.getPerson(data, StatusBean.class);
                if (person.getStatus() == 200) {
                    ToastUtils.show(person.getMsg());

                } else {
                    ToastUtils.show(person.getMsg());
                }

                break;
            case Constant.selectInfoByUserid:
                RegisterBean person3 = GsonUtils.getPerson(data, RegisterBean.class);
                if (person3.getStatus()==200){//查询个人信息
                    RegisterBean.DataBean personData = person3.getData();
                    SPUtils.putString("bean",new Gson().toJson(personData));

                    setUserBean();
                    show();

                }
                break;


        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {
        ToastUtils.show(error);
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
                    ImageLoader.with(getActivity())
                            .load(mAvatarUrl)
                            .error(getResources().getDrawable(R.drawable.ic_head_placeholder))
                            .placeholder(getResources().getDrawable(R.drawable.ic_head_placeholder))
                            .circle()
                            .into(mAvatarView);
                    presenetr.getPostHeaderRequest(SetActivity.this, ServerUrl.updateHeadImg, Constant.updateHeadImg);

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