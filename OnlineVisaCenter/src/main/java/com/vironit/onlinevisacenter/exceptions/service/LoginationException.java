package com.vironit.onlinevisacenter.exceptions.service;

public class LoginationException extends Exception {
    public LoginationException() {
        super();
    }

    public LoginationException(String message) {
        super(message);
    }

    public LoginationException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginationException(Throwable cause) {
        super(cause);
    }

    protected LoginationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
