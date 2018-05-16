package com.vironit.onlinevisacenter.dto.response;

import java.util.List;

public class ResponseExceptionDTO {

    private String errorMessage;
    private List<String> errors;

       public ResponseExceptionDTO(List<String> errors) {
        this.errors = errors;
    }

    public ResponseExceptionDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ResponseExceptionDTO(String errorMessage, List<String> errors) {
        this.errorMessage = errorMessage;
        this.errors = errors;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }



    public String getErrorMessage() {
        return errorMessage;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
