package com.cn.logistics.bean;

import java.util.List;

/**
 * Created by Xie JiaBin on 2019/12/19.
 */
public class OrderListBean {


    /**
     * status : 200
     * msg : ok
     * data : [{"sendgoodsaddress":"132","or_status":0,"goodsvolume":"123","userphone":"","head_img":"","pickgoodsaddress":"13","sendgoodscity":"杭州市","ordercode":"123","goodsweight":"123","sendgoodsarea":"桐庐区","pickgoodscity":"温州市","pickgoodsarea":"永嘉区","goodsnumber":123}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * sendgoodsaddress : 132
         * or_status : 0
         * goodsvolume : 123          体积
         * userphone :
         * head_img :
         * pickgoodsaddress : 13
         * sendgoodscity : 杭州市
         * ordercode : 123
         * goodsweight : 123          重量
         * sendgoodsarea : 桐庐区
         * pickgoodscity : 温州市
         * pickgoodsarea : 永嘉区
         * goodsnumber : 123          数量
         */

        private String sendgoodsaddress;
        private int or_status;
        private String goodsvolume;
        private String userphone;
        private String head_img;
        private String pickgoodsaddress;
        private String sendgoodscity;
        private String ordercode;
        private String goodsweight;
        private String sendgoodsarea;
        private String pickgoodscity;
        private String pickgoodsarea;
        private int goodsnumber;

        public String getSendgoodsaddress() {
            return sendgoodsaddress;
        }

        public void setSendgoodsaddress(String sendgoodsaddress) {
            this.sendgoodsaddress = sendgoodsaddress;
        }

        public int getOr_status() {
            return or_status;
        }

        public void setOr_status(int or_status) {
            this.or_status = or_status;
        }

        public String getGoodsvolume() {
            return goodsvolume;
        }

        public void setGoodsvolume(String goodsvolume) {
            this.goodsvolume = goodsvolume;
        }

        public String getUserphone() {
            return userphone;
        }

        public void setUserphone(String userphone) {
            this.userphone = userphone;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public String getPickgoodsaddress() {
            return pickgoodsaddress;
        }

        public void setPickgoodsaddress(String pickgoodsaddress) {
            this.pickgoodsaddress = pickgoodsaddress;
        }

        public String getSendgoodscity() {
            return sendgoodscity;
        }

        public void setSendgoodscity(String sendgoodscity) {
            this.sendgoodscity = sendgoodscity;
        }

        public String getOrdercode() {
            return ordercode;
        }

        public void setOrdercode(String ordercode) {
            this.ordercode = ordercode;
        }

        public String getGoodsweight() {
            return goodsweight;
        }

        public void setGoodsweight(String goodsweight) {
            this.goodsweight = goodsweight;
        }

        public String getSendgoodsarea() {
            return sendgoodsarea;
        }

        public void setSendgoodsarea(String sendgoodsarea) {
            this.sendgoodsarea = sendgoodsarea;
        }

        public String getPickgoodscity() {
            return pickgoodscity;
        }

        public void setPickgoodscity(String pickgoodscity) {
            this.pickgoodscity = pickgoodscity;
        }

        public String getPickgoodsarea() {
            return pickgoodsarea;
        }

        public void setPickgoodsarea(String pickgoodsarea) {
            this.pickgoodsarea = pickgoodsarea;
        }

        public int getGoodsnumber() {
            return goodsnumber;
        }

        public void setGoodsnumber(int goodsnumber) {
            this.goodsnumber = goodsnumber;
        }
    }
}
