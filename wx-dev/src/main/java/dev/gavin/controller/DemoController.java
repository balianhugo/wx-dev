package dev.gavin.controller;

import com.alibaba.fastjson.JSONObject;
import dev.gavin.exception.WxDevException;
import dev.gavin.vo.WxUser;

import dev.gavin.wx.message.WxInterfaceImpl;
import dev.gavin.mvc.WebConsts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 处理主页
 */
@Controller
public class DemoController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 主页面
     */
    @GetMapping("/home")
    public String index(Model model, HttpSession session) throws Exception{

        String wxAccessToken = stringRedisTemplate.opsForValue().get("wxAccessToken");
        String openid = (String) session.getAttribute(WebConsts.SESSION_WX_OPENID);

        WxUser wxUser;
        if(null != session.getAttribute(WebConsts.SESSION_WX_USER)) {
            wxUser = (WxUser) session.getAttribute(WebConsts.SESSION_WX_USER);
        } else {
            Map<String, Object> userMap = WxInterfaceImpl.getWxUserInfo(wxAccessToken, openid);
            if(userMap == null || userMap.get("errcode") != null) {
                throw new WxDevException("用户信息读取失败");
            }
            JSONObject jo = (JSONObject) userMap;
            wxUser = jo.toJavaObject(WxUser.class);

            if(wxUser.getSubscribe() == 0) {
                logger.info("wxUser 未关注公众号用户访问 openid:{}", wxUser.getOpenid());
                return "unsubscribe";
            }

            logger.debug("wxUser openid:{}, nickname:{}", wxUser.getOpenid(), wxUser.getNickname());
            session.setAttribute(WebConsts.SESSION_WX_USER, wxUser);
        }

        model.addAttribute("wxUser", wxUser);

        return "index";
    }


    /**
     * 微信网页接口测试页面
     */
    @GetMapping("/wxtest")
    public String wx() {
        return "wxtest";
    }




}
