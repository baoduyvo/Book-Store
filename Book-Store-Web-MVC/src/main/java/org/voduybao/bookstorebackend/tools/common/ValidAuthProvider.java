package org.voduybao.bookstorebackend.tools.common;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.voduybao.bookstorebackend.tools.common.validator.AuthProviderValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AuthProviderValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAuthProvider {
    String message() default "Invalid loginType. Must be one of LOCAL, FACEBOOK, GOOGLE.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}