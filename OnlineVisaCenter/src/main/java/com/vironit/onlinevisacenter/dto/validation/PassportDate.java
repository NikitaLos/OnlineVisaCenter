package com.vironit.onlinevisacenter.dto.validation;

import com.vironit.onlinevisacenter.dto.request.ApplicationRequestDTO;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.LocalDate;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = PassportDate.PassportDateImpl.class)
public @interface PassportDate {
    String message() default "{passport_invalid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class PassportDateImpl implements ConstraintValidator<PassportDate,ApplicationRequestDTO> {
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

}
