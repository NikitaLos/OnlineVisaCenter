package com.vironit.onlinevisacenter.dto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = VisaDaysImpl.class)
public @interface VisaDays {
    String message() default "Duration between dateFrom and dateTO must be equal or greater number of days residence";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
