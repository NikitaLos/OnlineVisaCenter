package com.vironit.onlinevisacenter.exceptions;

public class UndefinedRoleException extends Throwable {
    public UndefinedRoleException() {
    }

    public UndefinedRoleException(String message) {
        super(message);
    }

    public UndefinedRoleException(String message, Throwable cause) {
        super(message, cause);
    }

    public UndefinedRoleException(Throwable cause) {
        super(cause);
    }

    public UndefinedRoleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
