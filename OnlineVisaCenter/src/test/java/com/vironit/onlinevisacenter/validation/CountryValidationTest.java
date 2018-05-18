package com.vironit.onlinevisacenter.validation;

import com.vironit.onlinevisacenter.dto.CountryDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryValidationTest {

    @Autowired
    private  Validator validator;

    @Test
    public void haveNameTest(){
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setName("USA");
        Set<ConstraintViolation<CountryDTO>> violations = validator.validate(countryDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void invalidNameTest(){
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setName("g");
        Set<ConstraintViolation<CountryDTO>> violations = validator.validate(countryDTO);
        assertFalse(violations.isEmpty());
    }

}
