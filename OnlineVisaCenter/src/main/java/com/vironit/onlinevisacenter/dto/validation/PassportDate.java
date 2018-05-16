package com.vironit.onlinevisacenter.dto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = PassportDateImpl.class)
public @interface PassportDate {
    String message() default "Your passport will be invalid before dateTo ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
