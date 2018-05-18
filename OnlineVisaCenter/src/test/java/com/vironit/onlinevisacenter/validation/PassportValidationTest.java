package com.vironit.onlinevisacenter.validation;

import com.vironit.onlinevisacenter.dto.request.PassportRequestDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PassportValidationTest {

    @Autowired
    private Validator validator;

    @Test
    public void validPassport(){
        PassportRequestDTO passportRequestDTO = new PassportRequestDTO();
        passportRequestDTO.setCountryOfResidence("USA");
        passportRequestDTO.setNumber("12345678");
        passportRequestDTO.setDateOfEnding(LocalDate.parse("2019-05-16"));
        passportRequestDTO.setDateOfReceiving(LocalDate.parse("2017-05-16"));
        Set<ConstraintViolation<PassportRequestDTO>> violations = validator.validate(passportRequestDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void invalidCountry(){
        PassportRequestDTO passportRequestDTO = new PassportRequestDTO();
        passportRequestDTO.setCountryOfResidence("U");
        passportRequestDTO.setNumber("12345678");
        passportRequestDTO.setDateOfEnding(LocalDate.parse("2019-05-16"));
        passportRequestDTO.setDateOfReceiving(LocalDate.parse("2017-05-16"));
        Set<ConstraintViolation<PassportRequestDTO>> violations = validator.validate(passportRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidNumber(){
        PassportRequestDTO passportRequestDTO = new PassportRequestDTO();
        passportRequestDTO.setCountryOfResidence("USA");
        passportRequestDTO.setNumber("12345");
        passportRequestDTO.setDateOfEnding(LocalDate.parse("2019-05-16"));
        passportRequestDTO.setDateOfReceiving(LocalDate.parse("2017-05-16"));
        Set<ConstraintViolation<PassportRequestDTO>> violations = validator.validate(passportRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidDateOfReceiving(){
        PassportRequestDTO passportRequestDTO = new PassportRequestDTO();
        passportRequestDTO.setCountryOfResidence("USA");
        passportRequestDTO.setNumber("12345678");
        passportRequestDTO.setDateOfEnding(LocalDate.parse("2019-05-16"));
        passportRequestDTO.setDateOfReceiving(LocalDate.parse("2019-05-16"));
        Set<ConstraintViolation<PassportRequestDTO>> violations = validator.validate(passportRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidDateOfEnding(){
        PassportRequestDTO passportRequestDTO = new PassportRequestDTO();
        passportRequestDTO.setCountryOfResidence("USA");
        passportRequestDTO.setNumber("12345678");
        passportRequestDTO.setDateOfEnding(LocalDate.parse("2012-05-16"));
        passportRequestDTO.setDateOfReceiving(LocalDate.parse("2017-05-16"));
        Set<ConstraintViolation<PassportRequestDTO>> violations = validator.validate(passportRequestDTO);
        assertFalse(violations.isEmpty());
    }

}
