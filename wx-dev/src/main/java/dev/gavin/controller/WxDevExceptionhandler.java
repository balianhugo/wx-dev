package dev.gavin.controller;

import dev.gavin.exception.WxDevException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 自定义全局异常处理返回页面
 */
@ControllerAdvice
public class WxDevExceptionhandler {

    @ExceptionHandler(WxDevException.class)
    public String  handler(WxDevException ex, Model model) {
        model.addAttribute("status", "500");
        return "/error/5xx";
    }

}
