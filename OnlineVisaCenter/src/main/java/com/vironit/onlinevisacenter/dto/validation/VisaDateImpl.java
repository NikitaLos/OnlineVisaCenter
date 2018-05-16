package com.vironit.onlinevisacenter.dto.validation;

import com.vironit.onlinevisacenter.dto.request.VisaInfoRequestDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VisaDateImpl implements ConstraintValidator<VisaDate,VisaInfoRequestDTO> {
    @Override
    public void initialize(VisaDate constraintAnnotation) {
    }
    @Override
    public boolean isValid(VisaInfoRequestDTO visaInfoRequestDTO, ConstraintValidatorContext constraintValidatorContext) {
        return !visaInfoRequestDTO.getDateTo().isBefore(visaInfoRequestDTO.getDateFrom());
    }
}
