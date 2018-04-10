package com.vironit.onlinevisacenter.exceptions.dao;

public class EntityUpdateException extends Exception {
    public EntityUpdateException() {
    }

    public EntityUpdateException(String message) {
        super(message);
    }

    public EntityUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityUpdateException(Throwable cause) {
        super(cause);
    }

    protected EntityUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
