package com.vironit.onlinevisacenter.validation;

import com.vironit.onlinevisacenter.dto.request.VisaInfoRequestDTO;
import com.vironit.onlinevisacenter.dto.validation.ValidationSequence;
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
public class VisaInfoValidationTest {

    @Autowired
    private Validator validator;

    @Test
    public void validVisaInfo(){
        VisaInfoRequestDTO visaInfoRequestDTO = new VisaInfoRequestDTO();
        visaInfoRequestDTO.setVisaId(1);
        visaInfoRequestDTO.setDateFrom(LocalDate.parse("2019-05-16"));
        visaInfoRequestDTO.setDateTo(LocalDate.parse("2019-06-16"));
        visaInfoRequestDTO.setNumOfDaysResidence(15);
        Set<ConstraintViolation<VisaInfoRequestDTO>> violations = validator.validate(visaInfoRequestDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void invalidVisaId(){
        VisaInfoRequestDTO visaInfoRequestDTO = new VisaInfoRequestDTO();
        visaInfoRequestDTO.setDateFrom(LocalDate.parse("2019-05-16"));
        visaInfoRequestDTO.setDateTo(LocalDate.parse("2019-06-16"));
        visaInfoRequestDTO.setNumOfDaysResidence(15);
        Set<ConstraintViolation<VisaInfoRequestDTO>> violations = validator.validate(visaInfoRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidDateFrom(){
        VisaInfoRequestDTO visaInfoRequestDTO = new VisaInfoRequestDTO();
        visaInfoRequestDTO.setVisaId(1);
        visaInfoRequestDTO.setDateFrom(LocalDate.parse("2018-05-16"));
        visaInfoRequestDTO.setDateTo(LocalDate.parse("2019-06-16"));
        visaInfoRequestDTO.setNumOfDaysResidence(15);
        Set<ConstraintViolation<VisaInfoRequestDTO>> violations = validator.validate(visaInfoRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidDateTo(){
        VisaInfoRequestDTO visaInfoRequestDTO = new VisaInfoRequestDTO();
        visaInfoRequestDTO.setVisaId(1);
        visaInfoRequestDTO.setDateFrom(LocalDate.parse("2019-06-16"));
        visaInfoRequestDTO.setDateTo(LocalDate.parse("2018-05-16"));
        visaInfoRequestDTO.setNumOfDaysResidence(15);
        Set<ConstraintViolation<VisaInfoRequestDTO>> violations = validator.validate(visaInfoRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidNumberOfDays(){
        VisaInfoRequestDTO visaInfoRequestDTO = new VisaInfoRequestDTO();
        visaInfoRequestDTO.setVisaId(1);
        visaInfoRequestDTO.setDateFrom(LocalDate.parse("2019-05-16"));
        visaInfoRequestDTO.setDateTo(LocalDate.parse("2018-06-16"));
        Set<ConstraintViolation<VisaInfoRequestDTO>> violations = validator.validate(visaInfoRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidDates(){
        VisaInfoRequestDTO visaInfoRequestDTO = new VisaInfoRequestDTO();
        visaInfoRequestDTO.setVisaId(1);
        visaInfoRequestDTO.setDateFrom(LocalDate.parse("2019-06-16"));
        visaInfoRequestDTO.setDateTo(LocalDate.parse("2019-05-16"));
        visaInfoRequestDTO.setNumOfDaysResidence(15);
        Set<ConstraintViolation<VisaInfoRequestDTO>> violations = validator.validate(visaInfoRequestDTO,ValidationSequence.class);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidDaysOfResidence(){
        VisaInfoRequestDTO visaInfoRequestDTO = new VisaInfoRequestDTO();
        visaInfoRequestDTO.setVisaId(1);
        visaInfoRequestDTO.setDateFrom(LocalDate.parse("2019-05-16"));
        visaInfoRequestDTO.setDateTo(LocalDate.parse("2019-06-16"));
        visaInfoRequestDTO.setNumOfDaysResidence(45);
        Set<ConstraintViolation<VisaInfoRequestDTO>> violations = validator.validate(visaInfoRequestDTO,ValidationSequence.class);
        assertFalse(violations.isEmpty());
    }
}
