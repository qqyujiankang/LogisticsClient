package com.cn.logistics.bean;

import java.util.List;

/**
 * Created by Xie JiaBin on 2019/12/25.
 */
public class ViceAccountListBean {


    /**
     * status : 200
     * msg : ok
     * data : [{
     * 			"id": "A706045A284B5EFFA06B82B7C6C330DF",
     * 			"type": 2,
     * 			"pid": "FCA4F7A6FEF05B795F9C605513B0F0E9",
     * 			"isVip": 2,
     * 			"userphone": "789",
     * 			"password": "123456",
     * 			"idcardFront": "",
     * 			"idcardBack": "",
     * 			"photoAm": "",
     * 			"driveImg": "",
     * 			"runImg": "",
     * 			"headImg": "",
     * 			"nickname": "",
     * 			"umoney": 0,
     * 			"garde": 0,
     * 			"stime": "",
     * 			"etime": "",
     * 			"ctime": "2019-12-25 15:26:04",
     * 			"status": 1,
     * 			"isReal": 0,
     * 			"isRegister": 0,
     * 			"wechatno": "",
     * 			"realname": "",
     * 			"deviceid": "",
     * 			"wechatOpenid": "",
     * 			"qqOpenid": ""}]
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
         * id : A706045A284B5EFFA06B82B7C6C330DF
         * type : 2
         * pid : FCA4F7A6FEF05B795F9C605513B0F0E9
         * isVip : 2
         * userphone : 789
         * password : 123456
         * idcardFront :
         * idcardBack :
         * photoAm :
         * driveImg :
         * runImg :
         * headImg :
         * nickname :
         * umoney : 0
         * garde : 0
         * stime :
         * etime :
         * ctime : 2019-12-25 15:26:04
         * status : 1
         * isReal : 0
         * isRegister : 0
         * wechatno :
         * realname :
         * deviceid :
         * wechatOpenid :
         * qqOpenid :
         */

        private String id;
        private int type;
        private String pid;
        private int isVip;
        private String userphone;
        private String password;
        private String idcardFront;
        private String idcardBack;
        private String photoAm;
        private String driveImg;
        private String runImg;
        private String headImg;
        private String nickname;
        private int umoney;
        private int garde;
        private String stime;
        private String etime;
        private String ctime;
        private int status;
        private int isReal;
        private int isRegister;
        private String wechatno;
        private String realname;
        private String deviceid;
        private String wechatOpenid;
        private String qqOpenid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public int getIsVip() {
            return isVip;
        }

        public void setIsVip(int isVip) {
            this.isVip = isVip;
        }

        public String getUserphone() {
            return userphone;
        }

        public void setUserphone(String userphone) {
            this.userphone = userphone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getIdcardFront() {
            return idcardFront;
        }

        public void setIdcardFront(String idcardFront) {
            this.idcardFront = idcardFront;
        }

        public String getIdcardBack() {
            return idcardBack;
        }

        public void setIdcardBack(String idcardBack) {
            this.idcardBack = idcardBack;
        }

        public String getPhotoAm() {
            return photoAm;
        }

        public void setPhotoAm(String photoAm) {
            this.photoAm = photoAm;
        }

        public String getDriveImg() {
            return driveImg;
        }

        public void setDriveImg(String driveImg) {
            this.driveImg = driveImg;
        }

        public String getRunImg() {
            return runImg;
        }

        public void setRunImg(String runImg) {
            this.runImg = runImg;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getUmoney() {
            return umoney;
        }

        public void setUmoney(int umoney) {
            this.umoney = umoney;
        }

        public int getGarde() {
            return garde;
        }

        public void setGarde(int garde) {
            this.garde = garde;
        }

        public String getStime() {
            return stime;
        }

        public void setStime(String stime) {
            this.stime = stime;
        }

        public String getEtime() {
            return etime;
        }

        public void setEtime(String etime) {
            this.etime = etime;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getIsReal() {
            return isReal;
        }

        public void setIsReal(int isReal) {
            this.isReal = isReal;
        }

        public int getIsRegister() {
            return isRegister;
        }

        public void setIsRegister(int isRegister) {
            this.isRegister = isRegister;
        }

        public String getWechatno() {
            return wechatno;
        }

        public void setWechatno(String wechatno) {
            this.wechatno = wechatno;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getDeviceid() {
            return deviceid;
        }

        public void setDeviceid(String deviceid) {
            this.deviceid = deviceid;
        }

        public String getWechatOpenid() {
            return wechatOpenid;
        }

        public void setWechatOpenid(String wechatOpenid) {
            this.wechatOpenid = wechatOpenid;
        }

        public String getQqOpenid() {
            return qqOpenid;
        }

        public void setQqOpenid(String qqOpenid) {
            this.qqOpenid = qqOpenid;
        }
    }
}
