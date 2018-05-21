package com.vironit.onlinevisacenter.controller;

import com.vironit.onlinevisacenter.dto.response.ResponseExceptionDTO;
import com.vironit.onlinevisacenter.exceptions.AuthorizationException;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ResponseExceptionDTO> duplicate(DuplicateException e) {
        return new ResponseEntity<>(new ResponseExceptionDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ResponseExceptionDTO> notAuthorize(AuthorizationException e) {
        return new ResponseEntity<>(new ResponseExceptionDTO(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseExceptionDTO NotValid(MethodArgumentNotValidException e) {
        List<String> validErrors = new ArrayList<>();
        for (ObjectError objectError : e.getBindingResult().getAllErrors()) {
            validErrors.add(objectError.getDefaultMessage());
        }
        return new ResponseExceptionDTO(validErrors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseExceptionDTO invalidJSON(HttpMessageNotReadableException e) {
        return new ResponseExceptionDTO("invalid JSON");
    }

    @ExceptionHandler(ApplicationServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseExceptionDTO applicationServiceException (ApplicationServiceException e) {
        return new ResponseExceptionDTO(e.getMessage());
    }

    @ExceptionHandler(CountryServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseExceptionDTO countryServiceException (CountryServiceException e) {
        return new ResponseExceptionDTO(e.getMessage());
    }

    @ExceptionHandler(DocumentServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseExceptionDTO documentServiceException (DocumentServiceException e) {
        return new ResponseExceptionDTO(e.getMessage());
    }

    @ExceptionHandler(UserServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseExceptionDTO userServiceException (UserServiceException e) {
        return new ResponseExceptionDTO(e.getMessage());
    }

    @ExceptionHandler(VisaServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseExceptionDTO visaServiceException (VisaServiceException e) {
        return new ResponseExceptionDTO(e.getMessage());
    }


}
