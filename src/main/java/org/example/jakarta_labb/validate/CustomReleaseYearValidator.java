package org.example.jakarta_labb.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CustomReleaseYearValidator implements ConstraintValidator<ReleaseYear, LocalDate> {

    @Override
    public void initialize(ReleaseYear constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate releaseYear, ConstraintValidatorContext context) {
        int year = releaseYear.getYear();
        int currentYear = getCurrentYear();
        return year >= 1900 && year <= currentYear;
    }

    public static int getCurrentYear() {
        return LocalDateTime.now().getYear();
    }
}
