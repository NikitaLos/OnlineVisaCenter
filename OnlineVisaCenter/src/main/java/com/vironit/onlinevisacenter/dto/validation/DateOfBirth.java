package com.vironit.onlinevisacenter.dto.validation;

import com.vironit.onlinevisacenter.dto.request.ClientInfoRequestDTO;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.LocalDate;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.time.temporal.ChronoUnit.YEARS;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = DateOfBirth.DateOfBirthImpl.class)
public @interface DateOfBirth {
    String message() default "You need to be 18 years old to make a an application";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class DateOfBirthImpl implements ConstraintValidator<DateOfBirth,ClientInfoRequestDTO> {
        @Override
        public void initialize(DateOfBirth constraintAnnotation) {
        }

        @Override
        public boolean isValid(ClientInfoRequestDTO clientInfoRequestDTO, ConstraintValidatorContext constraintValidatorContext) {
            return YEARS.between(clientInfoRequestDTO.getDateOfBirth(), LocalDate.now()) > 18;
        }
    }
}
