package com.vironit.onlinevisacenter.dto.validation;

import com.vironit.onlinevisacenter.dto.request.VisaInfoRequestDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.time.temporal.ChronoUnit.DAYS;

public class VisaDaysImpl implements ConstraintValidator<VisaDays,VisaInfoRequestDTO> {
    @Override
    public void initialize(VisaDays constraintAnnotation) {
    }

    @Override
    public boolean isValid(VisaInfoRequestDTO visaInfoRequestDTO, ConstraintValidatorContext constraintValidatorContext) {
        return DAYS.between(visaInfoRequestDTO.getDateFrom(),visaInfoRequestDTO.getDateTo()) >= visaInfoRequestDTO.getNumOfDaysResidence();
    }
}
