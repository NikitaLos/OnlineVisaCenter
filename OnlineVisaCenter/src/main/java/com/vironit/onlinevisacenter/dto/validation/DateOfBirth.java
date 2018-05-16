package com.vironit.onlinevisacenter.dto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = DateOfBirthImpl.class)
public @interface DateOfBirth {
    String message() default "You need to be 18 years old to make a an application";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
