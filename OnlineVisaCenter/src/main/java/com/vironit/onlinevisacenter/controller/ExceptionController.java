package com.vironit.onlinevisacenter.controller;

import com.vironit.onlinevisacenter.dto.response.ResponseExceptionDTO;
import com.vironit.onlinevisacenter.exceptions.ConverterException;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice(annotations = {RestController.class})
@RestController
@PropertySource("classpath:validation_messages.properties")
public class ExceptionController {

    @Value("${validation_errors}")
    private String validMessage;

    @Value("${invalid_json}")
    private String jsonMessage;

    @Value("${delete_error}")
    private String deleteMessage;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseExceptionDTO notValid(MethodArgumentNotValidException e) {
        List<String> validErrors = new ArrayList<>();
        for (ObjectError objectError : e.getBindingResult().getAllErrors()) {
            validErrors.add(objectError.getDefaultMessage());
        }
        return new ResponseExceptionDTO(validMessage,validErrors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseExceptionDTO invalidJSON() {
        return new ResponseExceptionDTO(jsonMessage);
    }

    @ExceptionHandler({ServiceException.class,ConverterException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseExceptionDTO servicesException (Exception e) {
        return new ResponseExceptionDTO(e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseExceptionDTO deleteException() {
        return new ResponseExceptionDTO(deleteMessage);
    }
}
