package com.vironit.onlinevisacenter.exceptions.service;

public class CountryServiceException extends Exception {
    public CountryServiceException() {
    }

    public CountryServiceException(String message) {
        super(message);
    }

    public CountryServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CountryServiceException(Throwable cause) {
        super(cause);
    }

    public CountryServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
