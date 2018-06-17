package com.vironit.onlinevisacenter.dto.response;

import java.util.List;

public class ResponseExceptionDTO {

    private String errorMessage;
    private List<String> validationErrors;

       public ResponseExceptionDTO(List<String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public ResponseExceptionDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ResponseExceptionDTO(String errorMessage, List<String> validationErrors) {
        this.errorMessage = errorMessage;
        this.validationErrors = validationErrors;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }



    public String getErrorMessage() {
        return errorMessage;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<String> validationErrors) {
        this.validationErrors = validationErrors;
    }
}
