package com.nimko.services;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class ValidateServices <T> {
    private final Validator validator;

    public ValidateServices() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

    }

    public boolean isValid(T dto) {
        Set<ConstraintViolation<T>> errors = validator.validate(dto);
        return errors.isEmpty();
    }

}
