package com.cn.logistics.ui.activity;


import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.cn.logistics.R;
import com.cn.logistics.common.MyActivity;
import com.cn.logistics.utils.DrivingRouteOverlay;
import com.hjq.toast.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * time   : 2019/12/24
 * desc   : 货物跟踪详情
 */
public final class OrderDetailActivity extends MyActivity implements RouteSearch.OnRouteSearchListener {

    @BindView(R.id.orderMap)
    MapView mapView;

    private AMap aMap;
    String latitude;//经度
    String longitude;//纬度
    String supplierLat;//精度
    String supplierLog;//纬度
    private RouteSearch mRouteSearch;
    private LatLonPoint mStartPoint;//起点
    private LatLonPoint mEndPoint ;//终点
    private DriveRouteResult mDriveRouteResult;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void initView() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        supplierLat = getIntent().getStringExtra("supplierLat");
        supplierLog = getIntent().getStringExtra("supplierLng");
        supplierLat = "29.56667";
        supplierLog = "106.45000";
        latitude = getIntent().getStringExtra("latitude");
        longitude = getIntent().getStringExtra("longitude");
        latitude = "34.26667";
        longitude = "108.95000";
        mStartPoint = new LatLonPoint(Double.valueOf(supplierLat), Double.valueOf(supplierLog));//起点，39.942295,116.335891
        mEndPoint = new LatLonPoint(Double.valueOf(latitude), Double.valueOf(longitude));//终点，39.995576,116.481288

        mRouteSearch = new RouteSearch(this);
        mRouteSearch.setRouteSearchListener(this);
        setfromandtoMarker();
        searchRouteResult(2, RouteSearch.DrivingDefault);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        mapView.onCreate(savedInstanceState);// 此方法必须重写

    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


    private void setfromandtoMarker() {
        aMap.addMarker(new MarkerOptions()
                .position(convertToLatLng(mStartPoint))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.start_map)));
        aMap.addMarker(new MarkerOptions()
                .position(convertToLatLng(mEndPoint))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.end_map)));
    }

    /**
     * 把LatLonPoint对象转化为LatLon对象
     */
    public static LatLng convertToLatLng(LatLonPoint latLonPoint) {
        return new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
    }

    /**
     * 开始搜索路径规划方案
     */
    public void searchRouteResult(int routeType, int mode) {
        if (mStartPoint == null) {
            ToastUtils.show("定位中，稍后再试...");
            return;
        }
        if (mEndPoint == null) {
            ToastUtils.show("终点未设置");
        }
        showLoading();
        final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
                mStartPoint, mEndPoint);
        RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, mode, null,
                null, "");// 第一个参数表示路径规划的起点和终点，第二个参数表示驾车模式，第三个参数表示途经点，第四个参数表示避让区域，第五个参数表示避让道路
        mRouteSearch.calculateDriveRouteAsyn(query);// 异步路径规划驾车模式查询
    }

    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult result, int errorCode) {
        showComplete();
        aMap.clear();// 清理地图上的所有覆盖物
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getPaths() != null) {
                if (result.getPaths().size() > 0) {
                    mDriveRouteResult = result;
                    final DrivePath drivePath = mDriveRouteResult.getPaths()
                            .get(0);
                    if(drivePath == null) {
                        return;
                    }
                    DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(
                            this, aMap, drivePath,
                            mDriveRouteResult.getStartPos(),
                            mDriveRouteResult.getTargetPos(), null);
                    drivingRouteOverlay.setNodeIconVisibility(false);//设置节点marker是否显示
                    drivingRouteOverlay.setIsColorfulline(true);//是否用颜色展示交通拥堵情况，默认true
                    drivingRouteOverlay.removeFromMap();
                    drivingRouteOverlay.addToMap();
                    drivingRouteOverlay.zoomToSpan();

                }
            }
        }
    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

