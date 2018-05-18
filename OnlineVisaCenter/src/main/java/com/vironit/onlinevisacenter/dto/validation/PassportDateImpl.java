package com.vironit.onlinevisacenter.dto.validation;

import com.vironit.onlinevisacenter.dto.request.ApplicationRequestDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class PassportDateImpl implements ConstraintValidator<PassportDate,ApplicationRequestDTO> {
    @Override
    public void initialize(PassportDate constraintAnnotation) {

    }
    @Override
    public boolean isValid(ApplicationRequestDTO applicationRequestDTO, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate dateTo = applicationRequestDTO.getVisaInfo().getDateTo();
        LocalDate dateOfEnding = applicationRequestDTO.getClientInfo().getPassport().getDateOfEnding();
        return dateTo.isBefore(dateOfEnding);
    }
}
