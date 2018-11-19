package com.vironit.onlinevisacenter.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Integer id) {
        super("Entity with id = " + id + " not found");
    }
}
