package dev.gavin.mvc;


import dev.gavin.wx.config.WxConfig;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.Enumeration;


/**
 *  主要拦截器，处理微信公众号用户登陆， session 中否存在用户 openid 为依据
 */
public class AppGlobalInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(AppGlobalInterceptor.class);

    @Resource
    private WxConfig config;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        StringBuffer url = request.getRequestURL();
        logger.debug("访问 URL：{}", url.toString());

        HttpSession session = request.getSession();

        if(null == session.getAttribute(WebConsts.SESSION_WX_OPENID)) {

            StringBuilder params = new StringBuilder();
            Enumeration<String> enumer = request.getParameterNames();
            while (enumer.hasMoreElements()) {
                String pname = enumer.nextElement();
                params.append(pname);
                params.append("=");
                params.append(request.getParameter(pname));
                params.append("&");
            }
            if(params.length() > 0) {
                url.append("?");
                String queryString = params.toString();
                url.append(queryString.substring(0, queryString.length()-1));
            }
            logger.debug("访问 URL 带参数：{}", url.toString());
            String encode64 = Base64.encodeBase64String(url.toString().getBytes());
            logger.debug("encode64 URL 带参数：{}", encode64);
            // 认证地址，带上原来请求地址作为url参数
            String redirect_uri = config.getHost() + "/wxauth/oauth2?url=" + encode64;
            logger.debug("redirect_uri：{}", redirect_uri);
            String urlEncode = URLEncoder.encode(redirect_uri, "utf-8");
            logger.debug("urlEncode redirect_uri：{}", urlEncode);

            String redirectURL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + config.getAppID()
                    + "&redirect_uri=" + urlEncode
                    + "&response_type=code&scope=snsapi_base&state=state#wechat_redirect";
            response.sendRedirect(redirectURL);

            return false;
        }

        return true;
    }
}
