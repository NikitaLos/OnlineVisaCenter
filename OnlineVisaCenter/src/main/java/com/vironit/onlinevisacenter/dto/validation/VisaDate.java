package com.vironit.onlinevisacenter.dto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = VisaDateImpl.class)
public @interface  VisaDate {
    String message() default "DateFrom must be before dateTo";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
