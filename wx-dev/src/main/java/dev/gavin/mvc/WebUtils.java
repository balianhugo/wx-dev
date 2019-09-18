package dev.gavin.mvc;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取访问用户来源 ip
 */
public class WebUtils {

    public static String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

}