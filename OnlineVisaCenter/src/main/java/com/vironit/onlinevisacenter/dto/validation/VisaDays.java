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
import static java.time.temporal.ChronoUnit.DAYS;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = VisaDays.VisaDaysImpl.class)
public @interface VisaDays {
    String message() default "Duration between dateFrom and dateTO must be equal or greater number of days residence";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class VisaDaysImpl implements ConstraintValidator<VisaDays,VisaInfoRequestDTO> {
        @Override
        public void initialize(VisaDays constraintAnnotation) {
        }

        @Override
        public boolean isValid(VisaInfoRequestDTO visaInfoRequestDTO, ConstraintValidatorContext constraintValidatorContext) {
            return DAYS.between(visaInfoRequestDTO.getDateFrom(),visaInfoRequestDTO.getDateTo()) >= visaInfoRequestDTO.getNumOfDaysResidence();
        }
    }
}
