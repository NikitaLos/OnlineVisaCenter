package com.vironit.onlinevisacenter.controller;

import com.vironit.onlinevisacenter.dto.Message;
import com.vironit.onlinevisacenter.exceptions.AuthorizationException;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = {RestController.class,Controller.class})
@RestController
public class ExceptionController {
    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.MULTIPLE_CHOICES)
    public Message duplicate(DuplicateException e) {
        return new Message(e.getMessage());
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.MULTIPLE_CHOICES)
    public Message notAuthorize(AuthorizationException e) {
        return new Message(e.getMessage());
    }
}
