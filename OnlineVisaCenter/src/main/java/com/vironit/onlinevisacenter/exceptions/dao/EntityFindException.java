package com.vironit.onlinevisacenter.exceptions.dao;

public class EntityFindException extends Exception {
    public EntityFindException() {
        super();
    }

    public EntityFindException(String message) {
        super(message);
    }

    public EntityFindException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityFindException(Throwable cause) {
        super(cause);
    }

    protected EntityFindException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
