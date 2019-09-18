package dev.gavin.controller;

import dev.gavin.wx.config.WxConfig;
import dev.gavin.wx.message.WxMessageHandlerAdapter;
import dev.gavin.wx.message.WxMsgData;
import dev.gavin.wx.message.WxMsgProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 微信消息自定义处理实现，针对需要开发的功能进行相应的消息处理
 */
@Component
public class MyWxMsgHandler extends WxMessageHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(MyWxMsgHandler.class);

    @Resource
    private WxConfig config;

    @Override
    public String textHander(WxMsgData data) {

        String ToUserName = data.getToUserName();
        String FromUserName = data.getFromUserName();

        data.setFromUserName(ToUserName);
        data.setToUserName(FromUserName);
        data.setCreateTime(String.valueOf(System.currentTimeMillis()));

        return  WxMsgProcessor.textXML(data);
    }

    @Override
    public String subscribeEventHander(WxMsgData data) {
        String ToUserName = data.getToUserName();
        String FromUserName = data.getFromUserName();

        logger.debug("openid: " + FromUserName + " 用户关注");

        data.setFromUserName(ToUserName);
        data.setToUserName(FromUserName);
        data.setCreateTime(String.valueOf(System.currentTimeMillis()));

        List<WxMsgData> list = new ArrayList<>();
        WxMsgData itemOne = new WxMsgData();
        itemOne.setTitle("感谢关注“XXX”服务号");
        itemOne.setDescription("本服务号提供XXX服务");
        itemOne.setPicUrl(config.getHost() + "/static/image/sub.jpg");
        itemOne.setUrl(config.getHost());
        list.add(itemOne);

        WxMsgData itemTwo = new WxMsgData();
        itemTwo.setTitle("微信网页接口测试");
        itemTwo.setDescription("接口测试");
        itemTwo.setPicUrl(config.getHost() + "/static/image/180.jpg");
        itemTwo.setUrl(config.getHost() + "/users/wxtest");
        list.add(itemTwo);

        return WxMsgProcessor.newsXML(data, list);
    }

    @Override
    public String templateEventHander(WxMsgData data) {
        logger.info("微信向 {} 推送的模板消息结果为 {}", data.getFromUserName(), data.getStatus());
        if(!data.getStatus().equals("success")) {
            logger.error("微信向 {} 推送的模板消息失败，结果为 {}", data.getFromUserName(), data.getStatus());
        }
        return "success";
    }
}
