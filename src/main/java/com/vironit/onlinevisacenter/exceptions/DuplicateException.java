package com.vironit.onlinevisacenter.exceptions;

public class DuplicateException extends RuntimeException {
    public DuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateException(String message) {
        super(message);
    }
}
