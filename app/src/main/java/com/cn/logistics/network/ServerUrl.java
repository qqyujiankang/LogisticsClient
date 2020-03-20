package com.cn.logistics.network;

import com.cn.logistics.utils.SPUtils;

/**
 * Created by PC-122 on 2017/12/21.
 */
public class ServerUrl {

    public static String HTTPIP="http://192.168.0.111:8010";

    public String defaultIp="http://192.168.0.110:8010";
    public String useIp="http://192.168.100.190:8080";
    public  boolean s =  SPUtils.getBoolean("isS",false);
    public String ip = s ? useIp: defaultIp;
    public String agentIp=ip;

    //和user用户有关URL
    public String API = agentIp+"/btlcommon/UserCompMapping/";
    public static String getHtml="http://www.391k.com/api/xapi.ashx/info.json";
    public static String getPost="http://192.168.0.111:8012/app/photo/addAlbum";
    public static String uploadFile="http://47.105.171.135:3000/upload/";
    public static String downloadFile="http://p.gdown.baidu.com/67327e8c8685bca04ccdd877dea57de6d44a01c4b3dedcf2a3e14a80da15b6c5633bfee4dafdbfc9a23b0541b9c595fabff514f4f95de457c2d85bcf9c73b5f4fdce21e0a06cd1829cfe52a561251e8dd2cda14c3089995a37860ab1619402fe3e143b38212ed7ae6f73caf02cf2b7eb494c21cce868c5abbf05fce5593b27fcdeecc405ba08a97ec964642314f444ff96c586125818c11dd0ef497961b1c8fd24aa654001e8ce1b7da5d826ad0aa21f3651f5e0abd6be3f1538926fee90cd002550638cf98ad3fe3b05ecc7f9fa138fce273455fa5877c35a225c555a91eaa5ce69af76bc1f38d31220c93f73ecd73854cae34f9a1fc070bae0bcdcea929b9e2dc5a65113bc2c41";
    public static String getImage="http://images.csdn.net/20150817/1.jpg";

    //发送短信
    public static String SENDSMS="/app/login/sendSms";
    public static String sendSms(){
        return HTTPIP+SENDSMS;
    }
    //注册
    public static String REGISTER="/app/user/register";
    public static String register(){
        return HTTPIP+REGISTER;
    }
    //登录
    public static String LOGIN="/app/login/login";
    public static String login(){
        return HTTPIP+LOGIN;
    }
    //忘记密码
    public static String FORGETPASSWORD="/app/user/forgetPassword";
    public static String forgetPassword(){
        return HTTPIP+FORGETPASSWORD;
    }
    //修改密码
    public static String updatePassword = HTTPIP +"/app/user/updatePassword";
    //个人信息
    public static String selectInfoByUserid = HTTPIP +"/app/user/selectInfoByUserid";
    //意见反馈
    public static String addFeedBack = HTTPIP +"/app/user/addFeedBack";
    //上传图片
    public static String upload = HTTPIP +"/app/user/upload";
    //修改头像
    public static String updateHeadImg = HTTPIP +"/app/user/updateHeadImg";
    //修改昵称
    public static String updateNickname = HTTPIP +"/app/user/updateNickname";
    //绑定微信
    public static String updateWechat = HTTPIP +"/app/user/updateWechat";
    //修改手机号
    public static String updatePhone = HTTPIP +"/app/user/updatePhone";
    //辅助账号添加
    public static String addViceAccount = HTTPIP +"/app/user/addViceAccount";
    //辅助账号列表
    public static String selectViceAccount = HTTPIP +"/app/user/selectViceAccount";

    //我的地址列表
    public static String selectAddressByUserid = HTTPIP +"/app/address/selectAddressByUserid";
    //添加用户地址
    public static String addChinaAddress = HTTPIP +"/app/address/addChinaAddress";
    //编辑用户地址
    public static String updateChinaAddress = HTTPIP +"/app/address/updateChinaAddress";

    //删除用户地址
    public static String deleteChinaAddress = HTTPIP +"/app/address/deleteChinaAddress";

    //消息列表
    public static String selectMessageLists = HTTPIP +"/app/customer/selectMessageLists";
    //截图询价
    public static String addScreenshotCustom = HTTPIP +"/app/customer/addScreenshotCustom";
    //查询页 轮播图
    public static String selectShffling = HTTPIP +"/app/customer/selectShffling";
    //货物跟踪
    public static String selectOrderByCode = HTTPIP +"/app/customer/selectOrderByCode";



}
