package com.vironit.onlinevisacenter.dto.validation;

import com.vironit.onlinevisacenter.dto.request.ClientInfoRequestDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.YEARS;

public class DateOfBirthImpl implements ConstraintValidator<DateOfBirth,ClientInfoRequestDTO> {
    @Override
    public void initialize(DateOfBirth constraintAnnotation) {
    }

    @Override
    public boolean isValid(ClientInfoRequestDTO clientInfoRequestDTO, ConstraintValidatorContext constraintValidatorContext) {
        return YEARS.between(clientInfoRequestDTO.getDateOfBirth(), LocalDate.now()) > 18;
    }
}
