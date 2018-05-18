package com.vironit.onlinevisacenter.dto.validation;

import com.vironit.onlinevisacenter.dto.request.VisaInfoRequestDTO;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = VisaDate.VisaDateImpl.class)
public @interface  VisaDate {
    String message() default "DateFrom must be before dateTo";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class VisaDateImpl implements ConstraintValidator<VisaDate,VisaInfoRequestDTO> {
        @Override
        public void initialize(VisaDate constraintAnnotation) {
        }
        @Override
        public boolean isValid(VisaInfoRequestDTO visaInfoRequestDTO, ConstraintValidatorContext constraintValidatorContext) {
            return visaInfoRequestDTO.getDateFrom().isBefore(visaInfoRequestDTO.getDateTo());
        }
    }
}
