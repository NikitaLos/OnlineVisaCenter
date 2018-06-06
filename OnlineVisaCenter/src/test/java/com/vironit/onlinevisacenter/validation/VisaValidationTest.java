package com.vironit.onlinevisacenter.validation;

import com.vironit.onlinevisacenter.dto.request.VisaRequestDTO;
import org.junit.Test;
import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VisaValidationTest extends BaseValidationTest{

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
