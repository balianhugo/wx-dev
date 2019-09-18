package dev.gavin.mvc;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;

@WebListener
public class OnlineUserListener implements HttpSessionListener, HttpSessionAttributeListener {

    public void sessionCreated(HttpSessionEvent event) {


    }

    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        String username = (String) session.getAttribute(WebConsts.SESSION_WX_OPENID);
        System.out.println(username + "超时退出。");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        System.out.println("attributeAdded");
        System.out.println(se.getName());
        System.out.println(se.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        System.out.println("attributeReplaced");
        System.out.println(se.getName());
        System.out.println(se.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        System.out.println("attributeRemoved");
        System.out.println(se.getName());
        System.out.println(se.getValue());
    }
}