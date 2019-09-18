package dev.gavin.exception;

/**
 * 自定义异常
 */
public class WxDevException extends Exception {

    public WxDevException() {
        super();
    }

    public WxDevException(String message) {
        super(message);
    }

    public WxDevException(String message, Throwable cause) {
        super(message, cause);
    }

    public WxDevException(Throwable cause) {
        super(cause);
    }

    protected WxDevException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
