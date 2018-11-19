package com.vironit.onlinevisacenter.validation;

import com.vironit.onlinevisacenter.dto.clientinfo.ClientInfoRequestDTO;

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
    String message() default "{date_of_birth}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class DateOfBirthImpl implements ConstraintValidator<DateOfBirth,ClientInfoRequestDTO> {
        @Override
        public boolean isValid(ClientInfoRequestDTO clientInfoRequestDTO, ConstraintValidatorContext constraintValidatorContext) {
            return YEARS.between(clientInfoRequestDTO.getDateOfBirth(), LocalDate.now()) > 18;
        }
    }
}
