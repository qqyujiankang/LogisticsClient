package com.cn.logistics.bean;

public class RegisterBean {


    /**
     * status : 200
     * msg : 注册成功
     * data : {"id":"FCA4F7A6FEF05B795F9C605513B0F0E9","type":2,"pid":null,"isVip":2,"userphone":"15686018002","password":"123456","idcardFront":null,"idcardBack":null,"photoAm":null,"headImg":null,"nickname":null,"umoney":null,"garde":null,"stime":null,"etime":null,"ctime":"2019-12-24 16:10:44","status":1,"isReal":null,"wechatno":null,"realname":null}
     */

    private int status;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : FCA4F7A6FEF05B795F9C605513B0F0E9
         * type : 2
         * pid : null
         * isVip : 2
         * userphone : 15686018002
         * password : 123456
         * idcardFront : null
         * idcardBack : null
         * photoAm : null
         * headImg : null
         * nickname : null
         * umoney : null
         * garde : null
         * stime : null
         * etime : null
         * ctime : 2019-12-24 16:10:44
         * status : 1
         * isReal : null
         * wechatno : null
         * realname : null
         */

        private String id;
        private int type;
        private Object pid;
        private int isVip;
        private String userphone;
        private String password;
        private Object idcardFront;
        private Object idcardBack;
        private Object photoAm;
        private Object headImg;
        private Object nickname;
        private Object umoney;
        private Object garde;
        private Object stime;
        private Object etime;
        private String ctime;
        private int status;
        private Object isReal;
        private Object wechatno;
        private Object realname;
        private String deviceid;

        public String getDeviceid() {
            return deviceid;
        }

        public void setDeviceid(String deviceid) {
            this.deviceid = deviceid;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        private String token;

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

        public Object getPid() {
            return pid;
        }

        public void setPid(Object pid) {
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

        public Object getIdcardFront() {
            return idcardFront;
        }

        public void setIdcardFront(Object idcardFront) {
            this.idcardFront = idcardFront;
        }

        public Object getIdcardBack() {
            return idcardBack;
        }

        public void setIdcardBack(Object idcardBack) {
            this.idcardBack = idcardBack;
        }

        public Object getPhotoAm() {
            return photoAm;
        }

        public void setPhotoAm(Object photoAm) {
            this.photoAm = photoAm;
        }

        public Object getHeadImg() {
            return headImg;
        }

        public void setHeadImg(Object headImg) {
            this.headImg = headImg;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public Object getUmoney() {
            return umoney;
        }

        public void setUmoney(Object umoney) {
            this.umoney = umoney;
        }

        public Object getGarde() {
            return garde;
        }

        public void setGarde(Object garde) {
            this.garde = garde;
        }

        public Object getStime() {
            return stime;
        }

        public void setStime(Object stime) {
            this.stime = stime;
        }

        public Object getEtime() {
            return etime;
        }

        public void setEtime(Object etime) {
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

        public Object getIsReal() {
            return isReal;
        }

        public void setIsReal(Object isReal) {
            this.isReal = isReal;
        }

        public Object getWechatno() {
            return wechatno;
        }

        public void setWechatno(Object wechatno) {
            this.wechatno = wechatno;
        }

        public Object getRealname() {
            return realname;
        }

        public void setRealname(Object realname) {
            this.realname = realname;
        }
    }
}
