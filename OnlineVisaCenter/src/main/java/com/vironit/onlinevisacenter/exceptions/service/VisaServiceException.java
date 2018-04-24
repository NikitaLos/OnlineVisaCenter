package com.vironit.onlinevisacenter.exceptions.service;

import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;

public class VisaServiceException extends Exception {
    public VisaServiceException() {
    }

    public VisaServiceException(String message) {
        super(message);
    }

    public VisaServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public VisaServiceException(Throwable cause) {
        super(cause);
    }

    public VisaServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
