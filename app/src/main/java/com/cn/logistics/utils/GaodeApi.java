package com.cn.logistics.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Xie JiaBin on 2019/12/23.
 */
public class GaodeApi {

    /**
     * 高德地图通过地址获取经纬度
     */
    public static String httpURLConectionGET(String address) {
        //"http://restapi.amap.com/v3/geocode/geo?address=上海市东方明珠&output=JSON&key=xxxxxxxxx";
        String geturl = "http://restapi.amap.com/v3/geocode/geo?key=e182d9fcec01629acf250ce9eaaa2e1f&address="+address;
        String location = "";
        try {
            URL url = new URL(geturl);    // 把字符串转换为URL请求地址
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开连接
            connection.connect();// 连接会话
            // 获取输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {// 循环读取流
                sb.append(line);
            }
            br.close();// 关闭流
            connection.disconnect();// 断开连接
            System.out.println("Get=="+sb.toString());
            JSONObject a = JSON.parseObject(sb.toString());
            System.out.println(a.get("geocodes"));
            JSONArray sddressArr = JSON.parseArray(a.get("geocodes").toString());
            System.out.println(sddressArr.get(0));
            JSONObject c = JSON.parseObject(sddressArr.get(0).toString());
            location = c.get("location").toString();
            System.out.println(location);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败!");
        }
        return location;
    }



}
