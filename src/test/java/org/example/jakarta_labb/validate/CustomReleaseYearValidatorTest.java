package org.example.jakarta_labb.validate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        int validReleaseYear1 = 1900;
        int validReleaseYear2 = 2024;
        assertTrue(validator.isValid(validReleaseYear1, null));
        assertTrue(validator.isValid(validReleaseYear2, null));
    }

    @Test
    @DisplayName("Should return false for release year outside valid range")
    void shouldReturnFalseForInvalidReleaseYear() {
        int invalidReleaseYear1 = 1899;
        int invalidReleaseYear2 = 2100;
        assertFalse(validator.isValid(invalidReleaseYear1, null));
        assertFalse(validator.isValid(invalidReleaseYear2, null));
    }
}
