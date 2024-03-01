package org.example.jakarta_labb.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class CustomReleaseYearValidator implements ConstraintValidator<ReleaseYear, Integer> {

    @Override
    public void initialize(ReleaseYear constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer releaseYear, ConstraintValidatorContext context) {
        int currentYear = getCurrentYear();
        return releaseYear >= 1900 && releaseYear <= currentYear;
    }

    public static int getCurrentYear() {
        return LocalDateTime.now().getYear();
    }
}
