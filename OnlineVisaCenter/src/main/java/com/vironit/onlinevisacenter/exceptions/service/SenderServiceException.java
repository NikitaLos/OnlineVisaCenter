package com.vironit.onlinevisacenter.exceptions.service;

public class SenderServiceException extends Throwable {
    public SenderServiceException() {
    }

    public SenderServiceException(String message) {
        super(message);
    }

    public SenderServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public SenderServiceException(Throwable cause) {
        super(cause);
    }

    public SenderServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
