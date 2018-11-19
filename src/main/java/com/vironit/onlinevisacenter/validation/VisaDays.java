package com.vironit.onlinevisacenter.validation;

import com.vironit.onlinevisacenter.dto.visainfo.VisaInfoRequestDTO;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.time.temporal.ChronoUnit.DAYS;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = VisaDays.VisaDaysImpl.class)
public @interface VisaDays {
    String message() default "{visa_days}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class VisaDaysImpl implements ConstraintValidator<VisaDays,VisaInfoRequestDTO> {
        @Override
        public boolean isValid(VisaInfoRequestDTO visaInfoRequestDTO, ConstraintValidatorContext constraintValidatorContext) {
            return DAYS.between(visaInfoRequestDTO.getDateFrom(),visaInfoRequestDTO.getDateTo()) >= visaInfoRequestDTO.getNumOfDaysResidence();
        }
    }
}
