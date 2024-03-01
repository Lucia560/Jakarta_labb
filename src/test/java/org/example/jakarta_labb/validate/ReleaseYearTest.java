package org.example.jakarta_labb.validate;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ReleaseYearValidationTest {

    private Validator validator;

    private record Movie(@ReleaseYear Integer releaseYear) {
        private Movie(Integer releaseYear) {
            this.releaseYear = releaseYear;
        }
    }

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Valid release year")
    void validReleaseYear() {
        Movie releaseYear = new Movie(CustomReleaseYearValidator.getCurrentYear());
        Set<ConstraintViolation<Movie>> violations = validator.validate(releaseYear);
        printViolations("validReleaseYear", violations);
        assertEquals(0, violations.size(),
            "Valid release year should not have violations");
    }

    @Test
    @DisplayName("Invalid release year above max")
    void invalidReleaseYearAboveMax() {
        Movie releaseYear = new Movie(CustomReleaseYearValidator.getCurrentYear() + 100);
        Set<ConstraintViolation<Movie>> violations = validator.validate(releaseYear);
        printViolations("invalidReleaseYearAboveMax", violations);
        assertEquals(1, violations.size(),
            "Release year above the maximum allowed should have violations");
    }

    @Test
    @DisplayName("Invalid empty release year")
    void invalidEmptyReleaseYear() {
        Movie releaseYear = new Movie(null);
        Set<ConstraintViolation<Movie>> violations = validator.validate(releaseYear);
        printViolations("invalidEmptyReleaseYear", violations);
        assertEquals(1, violations.size(),
            "Empty release year should have violations");
    }

    @Test
    @DisplayName("Invalid negative release year")
    void invalidNegativeReleaseYear() {
        Movie releaseYear = new Movie(-2000);
        Set<ConstraintViolation<Movie>> violations = validator.validate(releaseYear);
        printViolations("invalidNegativeReleaseYear", violations);
        // Expected 2 violations because of not positive and not within range
        assertEquals(2, violations.size(),
            "Negative release year should have violations");
    }

    @Test
    @DisplayName("Invalid release year below min")
    void invalidReleaseYearBelowMin() {
        Movie releaseYear = new Movie(1800);
        Set<ConstraintViolation<Movie>> violations = validator.validate(releaseYear);
        printViolations("invalidReleaseYearBelowMin", violations);
        assertEquals(1, violations.size(),
            "Release year below the minimum allowed should have violations");
    }

    private void printViolations(String testName, Set<ConstraintViolation<Movie>> violations) {
        for (ConstraintViolation<?> violation : violations) {
            System.out.println("Test: " + testName + " - Violation: " +
                violation.getPropertyPath() + " - " + violation.getMessage());
        }
        System.out.println(" ");
    }
}

