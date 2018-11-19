package com.vironit.onlinevisacenter.validation;

import com.vironit.onlinevisacenter.dto.country.CountryDTO;
import org.junit.Test;
import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.Assert.*;

public class CountryValidationTest extends AbstractValidationTest {

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
