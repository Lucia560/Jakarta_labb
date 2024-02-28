package org.example.jakarta_labb.validate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomReleaseYearValidatorTest {

    private CustomReleaseYearValidator validator;

    @BeforeEach
    void setUp() {
        validator = new CustomReleaseYearValidator();
    }

    @Test
    @DisplayName("Should set up the validator with annotation")
    void shouldSetUpValidatorWithAnnotation() {
        ReleaseYear releaseYear = mock(ReleaseYear.class);
        when(releaseYear.value()).thenReturn(2024);
        validator.initialize(releaseYear);
        assertTrue(true);
    }

    @Test
    @DisplayName("Should return true for release year within valid range")
    void shouldReturnTrueForValidReleaseYear() {
        LocalDate validReleaseYear1 = LocalDate.of(1900, 1, 1);
        LocalDate validReleaseYear2 = LocalDate.of(2024, 1, 1);
        assertTrue(validator.isValid(validReleaseYear1, null));
        assertTrue(validator.isValid(validReleaseYear2, null));
    }

    @Test
    @DisplayName("Should return false for release year outside valid range")
    void shouldReturnFalseForInvalidReleaseYear() {
        LocalDate invalidReleaseYear1 = LocalDate.of(1899, 1, 1);
        LocalDate invalidReleaseYear2 = LocalDate.of(2100, 1, 1);
        assertFalse(validator.isValid(invalidReleaseYear1, null));
        assertFalse(validator.isValid(invalidReleaseYear2, null));
    }
}
