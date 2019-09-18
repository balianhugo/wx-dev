package dev.gavin.controller;

import dev.gavin.wx.config.WxConfig;
import dev.gavin.wx.message.WxInterfaceImpl;
import dev.gavin.mvc.WebConsts;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 微信配置校验、消息处理
 */
@Controller
@RequestMapping("wxauth")
public class WxAuthController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private WxConfig config;

    @Resource
    private MyWxMsgHandler myWxMsgHandler;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * timestamp	时间戳
     * nonce	随机数
     * echostr	随机字符串
     */
    @GetMapping
    public void doAuth(
            @RequestParam(value = "signature") String signature,
            @RequestParam(value = "timestamp") String timestamp,
            @RequestParam(value = "nonce") String nonce,
            @RequestParam(value = "echostr") String echostr,
            HttpServletResponse resp) throws IOException {

        String[] msgs = {config.getToken(), timestamp, nonce};
        Arrays.sort(msgs);
        String msg = msgs[0] + msgs[1] + msgs[2];

        logger.info("微信服务校验字符串排序后：{}", msg);
        String temp = DigestUtils.sha1Hex(msg.getBytes());
        logger.info("微信服务校验字符加密后：{}", temp);
        logger.info("比对字串：{} == {}", temp, signature);
        boolean eq = temp.equals(signature);
        logger.info("比对结果：{}", eq);

        String backStr = eq ? echostr : "";
        PrintWriter out = resp.getWriter();
        out.write(backStr);
        out.flush();
    }

    /**
     * 处理微信消息
     */
    @PostMapping
    public void doMsgHandler(HttpServletRequest request, HttpServletResponse response) {

        try {
            String message = myWxMsgHandler.messageHander(request);

            if (!message.isEmpty()) {
                logger.debug("处理微信消息返回：{}", message);
                response.setContentType("text/xml;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.write(message);
                out.flush();
            }

        } catch (Exception e) {
            logger.error("处理微信消息错误：{}", e);
        }

    }


    /**
     * 微信用户网页认证回调处理，获取用户 openID 装入 session 并重定向回用户原访问 url
     *
     * @throws IOException
     */
    @GetMapping("oauth2")
    public void doMsgHandler(
            @RequestParam(value = "url") String url,
            @RequestParam(value = "code") String code,
            @RequestParam(value = "state") String state,
            HttpSession session, HttpServletResponse response) throws IOException {

        String redirect_uri = new String(Base64.decodeBase64(url));

        Map<String, Object> map = WxInterfaceImpl.getWxH5AccessToken(config.getAppID(), config.getAppsecret(), code);
        if(null != map && !map.containsKey("errcode")){
            session.setAttribute(WebConsts.SESSION_WX_OPENID, map.get("openid"));
            logger.debug("获得用户 openid [{}] 回调 URL：{}", map.get("openid"), redirect_uri);
            response.sendRedirect(redirect_uri);
        }
        PrintWriter pw = response.getWriter();
        pw.write("获取用户网页授权失败");
        pw.flush();

    }


    /**
     * 微信网页接口调用签名
     */
    @RequestMapping("sign")
    @ResponseBody
    public Map<String, Object> jssdkSign(
            @RequestParam(value = "url") String url) {
        Map<String, Object> map = new HashMap<>();

        String wxJsaepiTicket = stringRedisTemplate.opsForValue().get("wxJsaepiTicket");

        String nonce_str = UUID.randomUUID().toString();
        String timestamp = Long.toString(System.currentTimeMillis());
        String[] temp = url.split("#");
        String toUrl = temp.length > 0 ? temp[0] : url;

        // 注意这里参数名必须全部小写，且必须按首字母有序
        String singnStr ="jsapi_ticket=" + wxJsaepiTicket + "&noncestr=" + nonce_str
                + "&timestamp=" + timestamp + "&url=" + toUrl;

        logger.debug("JSSDK sign befor：{}", singnStr);
        String signature = DigestUtils.sha1Hex(singnStr.getBytes());
        logger.debug("JSSDK sign after：{}", signature);

        map.put("appId", config.getAppID());
        map.put("nonceStr", nonce_str);
        map.put("timestamp", timestamp);
        map.put("signature", signature);

        return map;
    }

}
