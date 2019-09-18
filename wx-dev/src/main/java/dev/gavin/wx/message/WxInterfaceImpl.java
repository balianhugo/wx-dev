package dev.gavin.wx.message;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

public class WxInterfaceImpl {

    private static Logger logger = LoggerFactory.getLogger(WxInterfaceImpl.class);

    public static boolean sendTemplateMsg(String access_token, String template_json) {

        boolean isSuccess = false;

        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" +  access_token;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        CloseableHttpResponse response = null;
        String jsonString = "";
        try {
            StringEntity s = new StringEntity(template_json, "utf-8");
            logger.info("template json msg: {}", template_json);
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json"); //发送json数据需要设置contentType
            httpPost.setEntity(s);

            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            jsonString = EntityUtils.toString(entity);
            logger.info("----------------- 微信下发模板消息接口调用 -------------------\n url: {} \n response: {} \n {}", url, response.getStatusLine().toString(), jsonString);
            if(response.getStatusLine().getStatusCode() == 200) {
                JSONObject jo = JSONObject.parseObject(jsonString);
                int errcode =  jo.getIntValue("errcode");
                if(errcode == 0) isSuccess = true;
            }

            EntityUtils.consume(entity);
        }catch (Exception ex) {
            logger.error("url: {}\n {}", url, jsonString);
            StringWriter trace = new StringWriter();
            ex.printStackTrace(new PrintWriter(trace));
            logger.error("微信下发模板消息接口调用 error: {}", trace.toString());
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

        return isSuccess;
    }


    public static Map<String, Object> getWxH5AccessToken(String appid, String appsecret, String code) {

        Map<String, Object> map = null;

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" +  appid
                + "&secret=" + appsecret + "&code=" + code + "&grant_type=authorization_code";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = null;
        String jsonString = "";
        try {
            response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            jsonString = EntityUtils.toString(entity);
            logger.info("----------------- 微信用户网页授权接口调用 -------------------\n url: {} \n response: {} \n {}", url, response.getStatusLine().toString(), jsonString);
            if(response.getStatusLine().getStatusCode() == 200) {
                JSONObject jo = JSONObject.parseObject(jsonString);
                map  = (Map) jo;
            }

            EntityUtils.consume(entity);
        }catch (Exception ex) {
            logger.error("url: {}\n {}", url, jsonString);
            StringWriter trace = new StringWriter();
            ex.printStackTrace(new PrintWriter(trace));
            logger.error("微信用户网页授权接口调用 error: {}", trace.toString());
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

        return map;
    }


    public static Map<String, Object> getWxUserInfo(String accessToken, String openid) {

        Map<String, Object> map = null;

        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessToken
                + "&openid=" + openid + "&&lang=zh_CN";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = null;
        String jsonString = "";
        try {
            response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            jsonString = EntityUtils.toString(entity);
            logger.info("----------------- 微信用户信息接口调用 -------------------\n url: {} \n response: {} \n {}", url, response.getStatusLine().toString(), jsonString);
            if(response.getStatusLine().getStatusCode() == 200) {
                JSONObject jo = JSONObject.parseObject(jsonString);
                map  = (Map) jo;
            }

            EntityUtils.consume(entity);
        }catch (Exception ex) {
            logger.error("url: {}\n {}", url, jsonString);
            StringWriter trace = new StringWriter();
            ex.printStackTrace(new PrintWriter(trace));
            logger.error("微信用户信息接口调用 error: {}", trace.toString());
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

        return map;
    }

}


