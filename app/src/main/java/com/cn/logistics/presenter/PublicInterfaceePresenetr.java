package com.cn.logistics.presenter;

import android.app.Activity;

import com.cn.logistics.biz.PublicInterfaceBiz;
import com.cn.logistics.biz.bizimpl.PublicInterfaceImpl;
import com.cn.logistics.presenter.view.PublicInterfaceView;

/**
 * Created by desk 3-2 on 2018/5/23.
 */

public class PublicInterfaceePresenetr {
    private PublicInterfaceBiz biz;
    private PublicInterfaceView view;

    public PublicInterfaceePresenetr(PublicInterfaceView interfaceView) {
        this.view = interfaceView;
        biz = new PublicInterfaceImpl();
    }

    public void getPostStringRequest(Activity context, String url, final int tag) {
            biz.publicPostStringRequest(context, view.setPublicInterfaceData(tag), url, new PublicInterfaceBiz.OnRequestListener() {
                        @Override
                        public void onRequesSuccess(String data) {
                            view.onPublicInterfaceSuccess(data, tag);
                        }

                        @Override
                        public void onRequesError(String meessage) {
                            view.onPublicInterfaceError(meessage, tag);
                        }
                    }
            );
    }
    public void getPostRequest(Activity context, String url, final int tag) {
            biz.publicPostRequest(context, view.setPublicInterfaceData(tag), url, new PublicInterfaceBiz.OnRequestListener() {
                        @Override
                        public void onRequesSuccess(String data) {
                            view.onPublicInterfaceSuccess(data, tag);
                        }

                        @Override
                        public void onRequesError(String meessage) {
                            view.onPublicInterfaceError(meessage, tag);
                        }
                    }
            );
    }
    public void getPostHeaderRequest(Activity context, String url, final int tag) {
        biz.publicPostHeaderRequest(context, view.setPublicInterfaceData(tag),  url, new PublicInterfaceBiz.OnRequestListener() {
                    @Override
                    public void onRequesSuccess(String data) {
                        view.onPublicInterfaceSuccess(data, tag);
                    }

                    @Override
                    public void onRequesError(String meessage) {
                        view.onPublicInterfaceError(meessage, tag);
                    }
                }
        );
    }
    public void getGetRequest(Activity context, String url, final int tag) {
            biz.publicGetRequest(context, view.setPublicInterfaceData(tag), url, new PublicInterfaceBiz.OnRequestListener() {
                        @Override
                        public void onRequesSuccess(String data) {
                            view.onPublicInterfaceSuccess(data, tag);
                        }

                        @Override
                        public void onRequesError(String meessage) {
                            view.onPublicInterfaceError(meessage, tag);
                        }
                    }
            );
    }
}