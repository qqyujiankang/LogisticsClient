package com.cn.logistics.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.cn.logistics.R;
import com.cn.logistics.common.MyActivity;
import com.cn.logistics.common.MyLazyFragment;
import com.cn.logistics.helper.ActivityStackManager;
import com.cn.logistics.helper.DoubleClickHelper;
import com.cn.logistics.jpush.ExampleUtil;
import com.cn.logistics.jpush.LocalBroadcastManager;
import com.cn.logistics.other.KeyboardWatcher;
import com.cn.logistics.ui.fragment.MyFragment;
import com.cn.logistics.ui.fragment.QueryFragment;
import com.cn.logistics.ui.fragment.TailFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hjq.base.BaseFragmentAdapter;
import com.hjq.toast.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 主页界面
 */
public final class HomeActivity extends MyActivity
        implements
        BottomNavigationView.OnNavigationItemSelectedListener,
        KeyboardWatcher.SoftKeyboardStateListener {

    public static boolean isForeground = false;
    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!ExampleUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
                    setCostomMsg(showMsg.toString());
                }
            } catch (Exception e){
            }
        }
    }

    private void setCostomMsg(String msg){

        ToastUtils.show("通知HomeActivity");

    }
    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }
    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }



    @BindView(R.id.vp_home_pager)
    ViewPager mViewPager;
//    @BindView(R.id.bv_home_navigation)
//    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.home_query)
    TextView homeQuery;
    @BindView(R.id.home_my)
    TextView homeMy;
    @BindView(R.id.iv_home_center)
    ImageView ivHomeCenter;
    @BindView(R.id.tv_home_center)
    TextView tvHomeCenter;
    @BindView(R.id.home_center)
    LinearLayout homeCenter;

    /**
     * ViewPager 适配器
     */
    private BaseFragmentAdapter<MyLazyFragment> mPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
//        mViewPager.addOnPageChangeListener(this);

        // 不使用图标默认变色
//        mBottomNavigationView.setItemIconTintList(null);
//        mBottomNavigationView.setOnNavigationItemSelectedListener(this);



        KeyboardWatcher.with(this)
                .setListener(this);
    }

    @Override
    protected void initData() {

        mPagerAdapter = new BaseFragmentAdapter<>(this);
        mPagerAdapter.addFragment(QueryFragment.newInstance());
        mPagerAdapter.addFragment(TailFragment.newInstance());
        mPagerAdapter.addFragment(MyFragment.newInstance());
//        mPagerAdapter.addFragment(TestFragmentD.newInstance());
//        mPagerAdapter.addFragment(TestFragmentE.newInstance());

        mViewPager.setAdapter(mPagerAdapter);

        // 限制页面数量
        mViewPager.setOffscreenPageLimit(mPagerAdapter.getCount());

        registerMessageReceiver();

    }




    @OnClick({R.id.home_query, R.id.home_my, R.id.home_center})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_query:
                mViewPager.setCurrentItem(0);
                homeQuery.setTextColor(getResources().getColor(R.color.textOrage));
                Drawable nav_up=getResources().getDrawable(R.drawable.bom_serch_sel);
                nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                homeQuery.setCompoundDrawables(null,nav_up,null,null);
                tvHomeCenter.setTextColor(getResources().getColor(R.color.gray));
                ivHomeCenter.setImageResource(R.drawable.bom_center);
                homeMy.setTextColor(getResources().getColor(R.color.gray));
                Drawable nav_up21=getResources().getDrawable(R.drawable.bom_my);
                nav_up21.setBounds(0, 0, nav_up21.getMinimumWidth(), nav_up21.getMinimumHeight());
                homeMy.setCompoundDrawables(null,nav_up21,null,null);
                break;
            case R.id.home_center:
                if (!isLogin()){
                    startActivity(LoginActivity.class);
                    return;
                }

                mViewPager.setCurrentItem(1);
                homeQuery.setTextColor(getResources().getColor(R.color.gray));
                Drawable nav_up12=getResources().getDrawable(R.drawable.bom_serch);
                nav_up12.setBounds(0, 0, nav_up12.getMinimumWidth(), nav_up12.getMinimumHeight());
                homeQuery.setCompoundDrawables(null,nav_up12,null,null);
                tvHomeCenter.setTextColor(getResources().getColor(R.color.textOrage));
                ivHomeCenter.setImageResource(R.drawable.bom_center_sel);
                homeMy.setTextColor(getResources().getColor(R.color.gray));
                Drawable nav_up22=getResources().getDrawable(R.drawable.bom_my);
                nav_up22.setBounds(0, 0, nav_up22.getMinimumWidth(), nav_up22.getMinimumHeight());
                homeMy.setCompoundDrawables(null,nav_up22,null,null);
                break;
            case R.id.home_my:
                mViewPager.setCurrentItem(2);
                homeQuery.setTextColor(getResources().getColor(R.color.gray));
                Drawable nav_up13=getResources().getDrawable(R.drawable.bom_serch);
                nav_up13.setBounds(0, 0, nav_up13.getMinimumWidth(), nav_up13.getMinimumHeight());
                homeQuery.setCompoundDrawables(null,nav_up13,null,null);
                tvHomeCenter.setTextColor(getResources().getColor(R.color.gray));
                ivHomeCenter.setImageResource(R.drawable.bom_center);
                homeMy.setTextColor(getResources().getColor(R.color.textOrage));
                Drawable nav_up23=getResources().getDrawable(R.drawable.bom_my_sel);
                nav_up23.setBounds(0, 0, nav_up23.getMinimumWidth(), nav_up23.getMinimumHeight());
                homeMy.setCompoundDrawables(null,nav_up23,null,null);

                break;
        }
    }
    /**
     * {@link BottomNavigationView.OnNavigationItemSelectedListener}
     */

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                mViewPager.setCurrentItem(0);
                return true;
            case R.id.home_found:

                mViewPager.setCurrentItem(1);
                return true;
            case R.id.home_message:
                mViewPager.setCurrentItem(2);
                return true;
//            case R.id.home_me:
//                mViewPager.setCurrentItem(3);
//                return true;
//            case R.id.home_network:
//                mViewPager.setCurrentItem(4);
//                return true;
            default:
                break;
        }
        return false;
    }

    /**
     * {@link KeyboardWatcher.SoftKeyboardStateListener}
     */
    @Override
    public void onSoftKeyboardOpened(int keyboardHeight) {
//        mBottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    public void onSoftKeyboardClosed() {
//        mBottomNavigationView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 回调当前 Fragment 的 onKeyDown 方法
        if (mPagerAdapter.getCurrentFragment().onKeyDown(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (DoubleClickHelper.isOnDoubleClick()) {
            //移动到上一个任务栈，避免侧滑引起的不良反应
            moveTaskToBack(false);
            postDelayed(new Runnable() {

                @Override
                public void run() {
                    // 进行内存优化，销毁掉所有的界面
                    ActivityStackManager.getInstance().finishAllActivities();
                    // 销毁进程（请注意：调用此 API 可能导致当前 Activity onDestroy 方法无法正常回调）
                    // System.exit(0);
                }
            }, 300);
        } else {
            toast(R.string.home_exit_hint);
        }
    }

    @Override
    protected void onDestroy() {
//        mViewPager.removeOnPageChangeListener(this);
        mViewPager.setAdapter(null);
//        mBottomNavigationView.setOnNavigationItemSelectedListener(null);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}