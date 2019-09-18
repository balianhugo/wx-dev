package dev.gavin.wx.message;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public abstract class WxMessageHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(WxMessageHandlerAdapter.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public String messageHander(HttpServletRequest request)
            throws DocumentException, IOException {

        long start = System.currentTimeMillis();

        String response = "";

        SAXReader reader = new SAXReader();
        Document doc = reader.read(request.getInputStream());

        String msgId = doc.getRootElement().elementText("MsgId");

        if (null != msgId && stringRedisTemplate.hasKey(msgId)) {
            response = stringRedisTemplate.opsForValue().get(msgId);
            stringRedisTemplate.delete(msgId);
            return response;
        } else {

            String FromUserName = doc.getRootElement().elementText(
                    "FromUserName");
            String CreateTime = doc.getRootElement().elementText("CreateTime");
            String remsg = FromUserName + CreateTime;

            if (stringRedisTemplate.hasKey(remsg)) {
                response = stringRedisTemplate.opsForValue().get(remsg);
                stringRedisTemplate.delete(remsg);
                return response;
            }
        }

        String msgType = doc.getRootElement().elementText("MsgType");

        if (msgType.equals(WxMessageType.WX_MSG_EVENT)) {

            String event = doc.getRootElement().elementText("Event");

            logger.debug("事件类型为：{}", event);

            switch (event) {
                case WxMessageType.WX_EVENT_SUBSCRIBE:

                    response = subscribeEventHander(WxMsgProcessor.eventSubscribeParse(doc));

                    break;
                case WxMessageType.WX_EVENT_UNSUBSCRIBE:

                    response = unSubscribeEventHander(WxMsgProcessor.eventSubscribeParse(doc));

                    break;
                case WxMessageType.WX_EVENT_SCAN:

                    response = scanEventHander(WxMsgProcessor.eventScanParse(doc));

                    break;
                case WxMessageType.WX_EVENT_LOCATION:

                    response = eventLocationEventHander(WxMsgProcessor.eventLocationParse(doc));

                    break;
                case WxMessageType.WX_EVENT_VIEW:

                    response = viewEventHander(WxMsgProcessor.eventViewParse(doc));

                    break;
                case WxMessageType.WX_EVENT_CLICK:

                    response = clickEventHander(WxMsgProcessor.eventClickParse(doc));

                    break;

                case WxMessageType.WX_EVENT_TEMPLATE:

                    response = templateEventHander(WxMsgProcessor.eventTemplateParse(doc));

                    break;
                default:

                    logger.error("未知消息事件：{}", event);

                    break;
            }

        } else {

            logger.debug("消息类型为：{}", msgType);

            switch (msgType) {
                case WxMessageType.WX_MSG_TEXT:

                    response = textHander(WxMsgProcessor.textParse(doc));

                    break;
                case WxMessageType.WX_MSG_IMAGE:

                    response = imageHander(WxMsgProcessor.imageParse(doc));

                    break;
                case WxMessageType.WX_MSG_VOICE:

                    response = voiceHander(WxMsgProcessor.voiceParse(doc));

                    break;
                case WxMessageType.WX_MSG_VIDEO:

                    response = videoHander(WxMsgProcessor.videoParse(doc));

                    break;
                case WxMessageType.WX_MSG_SHORT_VIDEO:

                    response = shortVideoHander(WxMsgProcessor.videoParse(doc));

                    break;
                case WxMessageType.WX_MSG_LOCATION:

                    response = locationHander(WxMsgProcessor.locationParse(doc));

                    break;

                case WxMessageType.WX_MSG_LINK:

                    response = linkHander(WxMsgProcessor.linkParse(doc));

                    break;
                default:

                    logger.error("未知消息类型：{}", msgType);

                    break;
            }

        }

        long end = System.currentTimeMillis();

        int time = (int) ((end - start) / 1000);

        if (time > 5 && time < 10) {
            saveMsgRepeat(doc, response);
        }

        return response;
    }

    /**
     * 对于超时 5 秒的返回的消息进行缓存处理，待微信重试请求时直接读取缓存数据返回
     */
    private void saveMsgRepeat(Document doc, String result) {

        String msgId = doc.getRootElement().elementText("MsgId");

        if (null != msgId) {

            stringRedisTemplate.opsForValue().set(msgId, result, 15, TimeUnit.SECONDS);

        } else {

            String FromUserName = doc.getRootElement().elementText(
                    "FromUserName");
            String CreateTime = doc.getRootElement().elementText("CreateTime");
            String remsg = FromUserName + CreateTime;

            stringRedisTemplate.opsForValue().set(remsg, result, 15, TimeUnit.SECONDS);

        }

    }


    public String textHander(WxMsgData data) {
        return "";
    }

    public String imageHander(WxMsgData data) {
        return "";
    }

    public String voiceHander(WxMsgData data) {
        return "";
    }

    public String videoHander(WxMsgData data) {
        return "";
    }

    public String shortVideoHander(WxMsgData data) {
        return "";
    }

    public String linkHander(WxMsgData data) {
        return "";
    }

    public String locationHander(WxMsgData data) {
        return "";
    }

    public String clickEventHander(WxMsgData data) {
        return "";
    }

    public String viewEventHander(WxMsgData data) {
        return "";
    }

    public String templateEventHander(WxMsgData data) {
        return "";
    }

    public String eventLocationEventHander(WxMsgData data) {
        return "";
    }

    public String scanEventHander(WxMsgData data) {
        return "";
    }

    public String unSubscribeEventHander(WxMsgData data) {
        return "";
    }

    public String subscribeEventHander(WxMsgData data) {
        return "";
    }

}