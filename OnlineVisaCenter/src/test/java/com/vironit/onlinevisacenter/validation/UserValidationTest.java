package com.vironit.onlinevisacenter.validation;

import com.vironit.onlinevisacenter.dto.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserValidationTest {

    @Autowired
    private  Validator validator;

    @Test
    public void validUser(){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("admin@admin.com");
        userDTO.setLogin("testLogin");
        userDTO.setPassword("testPassport");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void invalidLogin(){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("admin@admin.com");
        userDTO.setLogin("lo");
        userDTO.setPassword("testPassport");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidPassword(){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("admin@admin.com");
        userDTO.setLogin("lo");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidEmail(){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("adminadmin.com");
        userDTO.setLogin("lo");
        userDTO.setPassword("testPassport");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        assertFalse(violations.isEmpty());
    }
}
