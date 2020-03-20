package com.cn.logistics.bean;

import java.util.List;

/**
 * Created by Xie JiaBin on 2019/12/20.
 */
public class MessageListBean {


    /**
     * status : 200
     * msg : ok
     * data : [{"id":"1a","appType":1,"type":1,"userid":"FCA4F7A6FEF05B795F9C605513B0F0E9","title":"西安市雁塔区至武汉市武昌区","content":"订单:B45682,已提货","week":"星期日","ctime":"2019-12-09 12:12:12","status":1},{"id":"2b","appType":1,"type":1,"userid":"FCA4F7A6FEF05B795F9C605513B0F0E9","title":"襄阳市襄州区西安市雁塔区","content":"订单:B78944,已送货","week":"星期一","ctime":"2019-12-09 12:12:12","status":1}]
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
         * id : 1a
         * appType : 1
         * type : 1
         * userid : FCA4F7A6FEF05B795F9C605513B0F0E9
         * title : 西安市雁塔区至武汉市武昌区
         * content : 订单:B45682,已提货
         * week : 星期日
         * ctime : 2019-12-09 12:12:12
         * status : 1
         */

        private String id;
        private int appType;
        private int type;
        private String userid;
        private String title;
        private String content;
        private String week;
        private String ctime;
        private int status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getAppType() {
            return appType;
        }

        public void setAppType(int appType) {
            this.appType = appType;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
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
    }
}
