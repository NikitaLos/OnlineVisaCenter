package com.vironit.onlinevisacenter.controller;

import com.vironit.onlinevisacenter.dto.response.ResponseExceptionDTO;
import com.vironit.onlinevisacenter.exceptions.ConverterException;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseExceptionDTO notValid(MethodArgumentNotValidException e) {
        String errorMessage = "Validation errors occurs:";
        List<String> validErrors = new ArrayList<>();
        for (ObjectError objectError : e.getBindingResult().getAllErrors()) {
            validErrors.add(objectError.getDefaultMessage());
        }
        return new ResponseExceptionDTO(errorMessage,validErrors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseExceptionDTO invalidJSON() {
        return new ResponseExceptionDTO("invalid JSON");
    }

    @ExceptionHandler({ServiceException.class,UsernameNotFoundException.class,ConverterException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseExceptionDTO servicesException (Exception e) {
        return new ResponseExceptionDTO(e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseExceptionDTO deleteException() {
        return new ResponseExceptionDTO("Can't be deleted. This object used by others.");
    }

}
