package com.vironit.onlinevisacenter.validation;

import com.vironit.onlinevisacenter.dto.request.VisaRequestDTO;
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
public class VisaValidationTest {

    @Autowired
    private  Validator validator;

    @Test
    public void validVisa(){
        VisaRequestDTO visaRequestDTO = new VisaRequestDTO();
        visaRequestDTO.setCountryId(1);
        visaRequestDTO.setPrice(1000.24);
        visaRequestDTO.setType("A");
        Set<ConstraintViolation<VisaRequestDTO>> violations = validator.validate(visaRequestDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void invalidCountryId(){
        VisaRequestDTO visaRequestDTO = new VisaRequestDTO();
        visaRequestDTO.setPrice(1000.24);
        visaRequestDTO.setType("A");
        Set<ConstraintViolation<VisaRequestDTO>> violations = validator.validate(visaRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidPriceFormat(){
        VisaRequestDTO visaRequestDTO = new VisaRequestDTO();
        visaRequestDTO.setCountryId(1);
        visaRequestDTO.setPrice(1000.242);
        visaRequestDTO.setType("A");
        Set<ConstraintViolation<VisaRequestDTO>> violations = validator.validate(visaRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidType(){
        VisaRequestDTO visaRequestDTO = new VisaRequestDTO();
        visaRequestDTO.setCountryId(1);
        visaRequestDTO.setPrice(1000.242);
        Set<ConstraintViolation<VisaRequestDTO>> violations = validator.validate(visaRequestDTO);
        assertFalse(violations.isEmpty());
    }

}
