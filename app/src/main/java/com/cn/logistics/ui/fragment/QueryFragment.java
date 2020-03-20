package com.cn.logistics.ui.fragment;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cn.logistics.R;
import com.cn.logistics.bean.BannerBean;
import com.cn.logistics.bean.RegisterBean;
import com.cn.logistics.common.MyLazyFragment;
import com.cn.logistics.network.Constant;
import com.cn.logistics.network.GsonUtils;
import com.cn.logistics.network.ServerUrl;
import com.cn.logistics.presenter.PublicInterfaceePresenetr;
import com.cn.logistics.presenter.view.PublicInterfaceView;
import com.cn.logistics.ui.activity.CopyActivity;
import com.cn.logistics.ui.activity.ImageInquiryActivity;
import com.cn.logistics.ui.activity.KefuActivity;
import com.cn.logistics.ui.activity.MessageActivity;
import com.cn.logistics.ui.activity.QueryActivity;
import com.cn.logistics.utils.SPUtils;
import com.google.gson.Gson;
import com.hjq.image.ImageLoader;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.UnreadCountChangeListener;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 查询
 */
public final class QueryFragment extends MyLazyFragment<CopyActivity> implements PublicInterfaceView {

    @BindView(R.id.iv_inquiry)
    TextView ivInquiry;
    @BindView(R.id.query_message)
    TextView queryMessage;
    @BindView(R.id.query_1)
    LinearLayout query1;
    @BindView(R.id.query_2)
    LinearLayout query2;
    @BindView(R.id.query_3)
    LinearLayout query3;
    @BindView(R.id.kefu_txt)
    TextView kefuTxt;
    @BindView(R.id.banner)
    XBanner banner;


    private PublicInterfaceePresenetr presenetr;

    public static QueryFragment newInstance() {
        return new QueryFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_query;
    }

    @Override
    protected void initView() {

        presenetr = new PublicInterfaceePresenetr(this);


    }

    @Override
    protected void initData() {
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null == presenetr) {
            presenetr = new PublicInterfaceePresenetr(this);
        }

        presenetr.getPostRequest(getActivity(),ServerUrl.selectShffling,Constant.selectShffling);

        if (isLogin()) {

            presenetr.getPostRequest(getActivity(), ServerUrl.selectInfoByUserid, Constant.selectInfoByUserid);

            UnreadCountChangeListener listener = new UnreadCountChangeListener() { // 声明一个成员变量
                @Override
                public void onUnreadCountChange(int count) {
                    // 在此更新界面, count 为当前未读数，
                    // 也可以用 Unicorn.getUnreadCount() 获取总的未读数
//                    ToastUtils.show("未读：" + count);
                    kefuTxt.setText("" + count);
                    if (count <= 0) {
                        kefuTxt.setVisibility(View.GONE);
                    } else {
                        kefuTxt.setVisibility(View.VISIBLE);
                    }
                }
            };
            Unicorn.addUnreadCountChangeListener(listener, true);

        }

    }

    @OnClick({R.id.query_message, R.id.iv_inquiry, R.id.query_1, R.id.query_2, R.id.query_3})
    public void onViewClicked(View v) {
        switch (v.getId()) {
//            case R.id.start_address:
//                startActivity(StartAddressActivity.class);
//                break;
//            case R.id.end_address:
//                startActivity(StartAddressActivity.class);
//                break;
            case R.id.query_message:
                startActivity(MessageActivity.class);
                break;
            case R.id.iv_inquiry://查询
                startActivity(QueryActivity.class);
                break;
            case R.id.query_1://自定义查询
                startActivity(QueryActivity.class);
                break;
            case R.id.query_2://客服查询



                startActivity(KefuActivity.class);
//                String title = "窗口的标题";
                /**
                 * 设置访客来源，标识访客是从哪个页面发起咨询的，用于客服了解用户是从什么页面进入。
                 * 三个参数分别为：来源页面的url，来源页面标题，来源页面额外信息（保留字段，暂时无用）。
                 * 设置来源后，在客服会话界面的"用户资料"栏的页面项，可以看到这里设置的值。
                 */
//                ConsultSource source = new ConsultSource("", "客服查询", "custom information string");
                /**
                 * 请注意： 调用该接口前，应先检查Unicorn.isServiceAvailable()，
                 * 如果返回为false，该接口不会有任何动作
                 *
                 * @param context 上下文
                 * @param title   聊天窗口的标题
                 * @param source  咨询的发起来源，包括发起咨询的url，title，描述信息等
                 */
//                Unicorn.openServiceActivity(getActivity(), title, source);

//                String registrationID = JPushInterface.getRegistrationID(getActivity());
//                L.e("极光==" + registrationID);
//                ToastUtils.show(registrationID);

//                if (ApkUtils.isQQClientAvailable(getActivity())) {
//                    String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=3287902027&version=1";
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
//                } else {
//                    ToastUtils.show("请安装QQ客户端");
//                }
                break;
            case R.id.query_3://截图查询
                startActivity(ImageInquiryActivity.class);
                break;
//            case R.id.cd_order_commit://立即下单
//
//
//
////                if (ApkUtils.isQQClientAvailable(getActivity())) {
////                    String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=3287902027&version=1";
////                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
////                } else {
////                    ToastUtils.show("请安装QQ客户端");
////                }
//
////                startActivity(OrdersActivity.class);
//                break;
//            case R.id.query_special://特殊要求
//                new SpecialDialog.Builder(getActivity()).setListener(new SpecialDialog.OnListener() {
//                    @Override
//                    public void onUp(BaseDialog dialog, String text) {
//
//                        dialog.dismiss();
//                    }
//                }).show();
//                break;
//            case R.id.carray_file://提货文件
//                startActivity(CarryFilesActivity.class);
//                break;


        }


    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> paramsMap = new HashMap<>();
        switch (tag) {
            case Constant.selectInfoByUserid:
                paramsMap.put("userid", userBean().getId());
                return paramsMap;
            case Constant.selectShffling:
                return paramsMap;

        }
        return null;
    }

    @Override
    public void onPublicInterfaceSuccess(String data, int tag) {
        switch (tag) {
            case Constant.selectInfoByUserid:


                RegisterBean person3 = GsonUtils.getPerson(data, RegisterBean.class);
                if (person3.getStatus() == 200) {//查询个人信息
                    RegisterBean.DataBean personData = person3.getData();
                    SPUtils.putString("bean", new Gson().toJson(personData));

                    setUserBean();

                }
                break;
            case Constant.selectShffling:

                BannerBean person = GsonUtils.getPerson(data, BannerBean.class);
                if (person.getStatus()==200){
                    List<BannerBean.DataBean> banners = person.getData();
                    if (banners!=null&&banners.size()>0){

                        banner.setBannerData(banners);
                        banner.loadImage(new XBanner.XBannerAdapter() {
                            @Override
                            public void loadBanner(XBanner banner, Object model, View view, int position) {
                                ImageLoader.with(getActivity()).load(banners.get(position).getImgUrl()).into((ImageView) view);
                            }
                        });

                    }
                }

                break;



        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {

    }


}