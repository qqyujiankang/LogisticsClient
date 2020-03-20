package com.cn.logistics.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Xie JiaBin on 2019/12/20.
 */
public class AddressListBean implements Serializable {
    /**
     * status : 200
     * msg : ok
     * data : [{"id":"64CAB923D6D6CEDC49660F6B51A8296B",
     *          "username":"test1","userphone":"12345678911",
     *          "province":"陕西省",
     *          "city":"西安市",
     *          "area":"雁塔区",
     *          "addressDetail":"啦啦啦啦",
     *          "isdefault":2,
     *          "status":1,
     *          "ctime":"2019-12-25 16:43:16",
     *          "userid":"FCA4F7A6FEF05B795F9C605513B0F0E9"}]
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

    public static class DataBean implements Serializable {
        /**
         * id : 64CAB923D6D6CEDC49660F6B51A8296B
         * username : test1
         * userphone : 12345678911
         * province : 陕西省
         * city : 西安市
         * area : 雁塔区
         * addressDetail : 啦啦啦啦
         * isdefault : 2
         * status : 1
         * ctime : 2019-12-25 16:43:16
         * userid : FCA4F7A6FEF05B795F9C605513B0F0E9
         */

        private String id;
        private String username;
        private String userphone;
        private String province;
        private String city;
        private String area;
        private String addressDetail;
        private int isdefault;
        private int status;
        private String ctime;
        private String userid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserphone() {
            return userphone;
        }

        public void setUserphone(String userphone) {
            this.userphone = userphone;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public int getIsdefault() {
            return isdefault;
        }

        public void setIsdefault(int isdefault) {
            this.isdefault = isdefault;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }
}
