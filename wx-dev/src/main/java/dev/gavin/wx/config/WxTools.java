package dev.gavin.wx.config;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class WxTools {

    private static Logger logger = LoggerFactory.getLogger(WxTools.class);

    public static String getWxAccessToken(String appID, String appSecret) {

        String access_token = "";
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" +  appID + "&secret=" + appSecret;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        String jsonString = "";
        try {
            response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            jsonString = EntityUtils.toString(entity);
            logger.info("----------------- 微信获取 AccessToken 接口调用 -------------------\n appID: {}, appSecret: {} \n url: {} \n response: {} \n {}", appID, appSecret, url, response.getStatusLine().toString(), jsonString);
            if(response.getStatusLine().getStatusCode() == 200) {
                JSONObject jo = JSONObject.parseObject(jsonString);
                access_token =  jo.getString("access_token");
            }

            EntityUtils.consume(entity);
        }catch (Exception ex) {
            logger.error("appID: {}, appSecret: {} \n url: {} \n {}", appID, appSecret, url, jsonString);
            StringWriter trace = new StringWriter();
            ex.printStackTrace(new PrintWriter(trace));
            logger.error("微信获取 AccessToken 接口调用 error: {}", trace.toString());
        }finally {
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return access_token;
    }


    public static String getJsapiTicket(String access_token) {

        String jsapi_ticket = "";
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+ access_token +"&type=jsapi";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        String jsonString = "";
        try {
            response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            jsonString = EntityUtils.toString(entity);
            logger.info("----------------- 微信获取 jsapi_ticket 接口调用 -------------------\n url: {} \n response: {} \n {}", url, response.getStatusLine().toString(), jsonString);
            if(response.getStatusLine().getStatusCode() == 200) {
                JSONObject jo = JSONObject.parseObject(jsonString);
                jsapi_ticket =  jo.getString("ticket");
            }

            EntityUtils.consume(entity);
        }catch (Exception ex) {
            logger.error("url: {} \n {}", url, jsonString);
            StringWriter trace = new StringWriter();
            ex.printStackTrace(new PrintWriter(trace));
            logger.error("微信获取 jsapi_ticket 接口调用 error: {}", trace.toString());
        }finally {
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return jsapi_ticket;
    }
}
