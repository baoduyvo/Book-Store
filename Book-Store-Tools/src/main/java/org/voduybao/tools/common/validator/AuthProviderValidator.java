package org.voduybao.tools.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.voduybao.tools.common.ValidAuthProvider;
import org.voduybao.tools.contants.e.AuthProviderEnum;

import java.util.Arrays;

public class AuthProviderValidator implements ConstraintValidator<ValidAuthProvider, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;

        return Arrays.stream(AuthProviderEnum.values())
                .anyMatch(e -> e.name().equalsIgnoreCase(value));
    }
}