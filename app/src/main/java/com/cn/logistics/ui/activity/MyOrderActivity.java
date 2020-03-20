package com.cn.logistics.ui.activity;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cn.logistics.R;
import com.cn.logistics.common.MyActivity;
import com.cn.logistics.ui.fragment.DfhFragment;
import com.cn.logistics.ui.fragment.DshFragment;
import com.cn.logistics.widget.NoScrollViewPager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 我的订单
 */
public final class MyOrderActivity extends MyActivity {

    @BindView(R.id.mytab)
    TabLayout mytab;
    @BindView(R.id.mViewPager)
    NoScrollViewPager mViewPager;
    List<String> mTitle;
    List<Fragment> mFragment;
    private int flag = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_myorder;
    }

    @Override
    protected void initView() {
        mViewPager.setNoScroll(true);//不能滑动
        mViewPager.setScrollAnim(false);//无动画
    }

    @Override
    protected void initData() {

        flag = getIntent().getIntExtra("flag", 1);

        mTitle = new ArrayList<>();
//        mTitle.add("待付款");
        mTitle.add("待发货");
        mTitle.add("已发货");
        mFragment = new ArrayList<>();
//        mFragment.add(new DfkFragment());
        mFragment.add(new DfhFragment());
        mFragment.add(new DshFragment());

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });

        mytab.setupWithViewPager(mViewPager);

        mViewPager.setCurrentItem(flag-1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}