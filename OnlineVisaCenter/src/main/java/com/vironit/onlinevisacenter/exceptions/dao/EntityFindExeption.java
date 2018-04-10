package com.vironit.onlinevisacenter.exceptions.dao;

public class EntityFindExeption extends Exception {
    public EntityFindExeption() {
        super();
    }

    public EntityFindExeption(String message) {
        super(message);
    }

    public EntityFindExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityFindExeption(Throwable cause) {
        super(cause);
    }

    protected EntityFindExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
