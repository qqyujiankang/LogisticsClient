package com.cn.logistics.ui.fragment;


import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cn.logistics.R;
import com.cn.logistics.bean.OrderListBean;
import com.cn.logistics.bean.RegisterBean;
import com.cn.logistics.common.MyLazyFragment;
import com.cn.logistics.network.Constant;
import com.cn.logistics.network.GsonUtils;
import com.cn.logistics.network.ServerUrl;
import com.cn.logistics.presenter.PublicInterfaceePresenetr;
import com.cn.logistics.presenter.view.PublicInterfaceView;
import com.cn.logistics.ui.activity.CopyActivity;
import com.cn.logistics.ui.activity.OrderDetailActivity;
import com.cn.logistics.ui.adapter.TailListAdapter;
import com.cn.logistics.utils.SPUtils;
import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;
import com.hjq.widget.view.ClearEditText;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 货物跟踪
 */
public final class TailFragment extends MyLazyFragment<CopyActivity> implements BaseQuickAdapter.OnItemClickListener, PublicInterfaceView {

    @BindView(R.id.tail_recycler)
    RecyclerView tailRecycler;
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.search_txt)
    ClearEditText searchTxt;
    @BindView(R.id.tail_null_status)
    LinearLayout tailNullStatus;
    private TailListAdapter adapter;
    private PublicInterfaceePresenetr presenetr;
    private List<OrderListBean.DataBean> beanList;

    public static TailFragment newInstance() {
        return new TailFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tail;
    }

    @Override
    protected void initView() {

        presenetr = new PublicInterfaceePresenetr(this);

        tailRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TailListAdapter(getActivity());
        adapter.setOnItemClickListener(this);
        tailRecycler.setAdapter(adapter);

    }

    @Override
    protected void initData() {


    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        startActivity(OrderDetailActivity.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null == presenetr) {
            presenetr = new PublicInterfaceePresenetr(this);
        }
        if (isLogin()) {

            presenetr.getPostRequest(getActivity(), ServerUrl.selectInfoByUserid, Constant.selectInfoByUserid);
        }

    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> paramsMap = new HashMap<>();
        switch (tag) {
            case Constant.selectInfoByUserid:
                paramsMap.put("userid", userBean().getId());
                return paramsMap;
            case Constant.selectOrderByCode:
                paramsMap.put("ordercode", searchTxt.getText().toString().trim());
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
            case Constant.selectOrderByCode:
                OrderListBean person = GsonUtils.getPerson(data, OrderListBean.class);
                if (person.getStatus() == 200) {
                    beanList = person.getData();
                    if (beanList != null && beanList.size() > 0) {
                        tailNullStatus.setVisibility(View.GONE);
                        tailRecycler.setVisibility(View.VISIBLE);
                        adapter.setNewData(beanList);
                    } else {
                        //空
                        tailNullStatus.setVisibility(View.VISIBLE);
                        tailRecycler.setVisibility(View.GONE);
                    }

                }

                break;


        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {

    }


    @OnClick({R.id.search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search://搜索

                hideKeyboard(search);

                if (TextUtils.isEmpty(searchTxt.getText().toString().trim())) {
                    ToastUtils.show("请输入业务单号/进仓号码");
                    return;
                }

                presenetr.getPostRequest(getActivity(), ServerUrl.selectOrderByCode, Constant.selectOrderByCode);

                break;

        }
    }


    /**
     * 隐藏软键盘
     *
     * @param  :上下文环境，一般为Activity实例
     * @param view    :一般为EditText
     */
    public void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}