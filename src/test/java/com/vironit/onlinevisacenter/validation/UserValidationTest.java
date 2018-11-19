package com.vironit.onlinevisacenter.validation;

import com.vironit.onlinevisacenter.dto.user.UserDTO;
import com.vironit.onlinevisacenter.entity.enums.Role;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserValidationTest extends AbstractValidationTest {

    @Test
    public void validUser(){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("admin@admin.com");
        userDTO.setLogin("testLogin");
        userDTO.setPassword("testPassport");
        userDTO.setRole(Role.CLIENT);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void invalidLogin(){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("admin@e.com");
        userDTO.setLogin("lo");
        userDTO.setPassword("testPassport");
        userDTO.setRole(Role.CLIENT);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidRole(){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("admin@admin.com");
        userDTO.setLogin("login");
        userDTO.setPassword("testPassport");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidPassword(){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("admin@admin.com");
        userDTO.setLogin("login");
        userDTO.setRole(Role.CLIENT);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidEmail(){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("adminadmin.com");
        userDTO.setLogin("login");
        userDTO.setPassword("testPassport");
        userDTO.setRole(Role.CLIENT);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        assertFalse(violations.isEmpty());
    }
}
