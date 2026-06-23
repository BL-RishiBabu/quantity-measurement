package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void givenSameFeetValues_WhenCompared_ShouldReturnTrue() {
        Main.Feet feet1 = new Main.Feet(1.0);
        Main.Feet feet2 = new Main.Feet(1.0);
        assertTrue(feet1.equals(feet2), "Two Feet objects with the same value should be equal.");
    }

    @Test
    void givenDifferentFeetValues_WhenCompared_ShouldReturnFalse() {
        Main.Feet feet1 = new Main.Feet(1.0);
        Main.Feet feet2 = new Main.Feet(2.0);
        assertFalse(feet1.equals(feet2), "Feet objects with different values should not be equal.");
    }

    @Test
    void givenFeetValueAndNull_WhenCompared_ShouldReturnFalse() {
        Main.Feet feet = new Main.Feet(1.0);
        assertFalse(feet.equals(null), "Comparing a Feet object with null must return false.");
    }

    @Test
    void givenFeetValueAndDifferentType_WhenCompared_ShouldReturnFalse() {
        Main.Feet feet = new Main.Feet(1.0);
        String nonNumericInput = "1.0 ft";
        assertFalse(feet.equals(nonNumericInput), "Comparing a Feet object with a different type should return false.");
    }

    @Test
    void givenSameFeetReference_WhenCompared_ShouldReturnTrue() {
        Main.Feet feet = new Main.Feet(1.0);
        assertTrue(feet.equals(feet), "A Feet object compared with itself must return true.");
    }

    @Test
    void givenSameInchesValues_WhenCompared_ShouldReturnTrue() {
        Main.Inches inch1 = new Main.Inches(1.0);
        Main.Inches inch2 = new Main.Inches(1.0);
        assertTrue(inch1.equals(inch2), "Two Inches objects with the same value should be equal.");
    }

    @Test
    void givenDifferentInchesValues_WhenCompared_ShouldReturnFalse() {
        Main.Inches inch1 = new Main.Inches(1.0);
        Main.Inches inch2 = new Main.Inches(2.0);
        assertFalse(inch1.equals(inch2), "Inches objects with different values should not be equal.");
    }

    @Test
    void givenInchesValueAndNull_WhenCompared_ShouldReturnFalse() {
        Main.Inches inch = new Main.Inches(1.0);
        assertFalse(inch.equals(null), "Comparing an Inches object with null must return false.");
    }

    @Test
    void givenInchesValueAndDifferentType_WhenCompared_ShouldReturnFalse() {
        Main.Inches inch = new Main.Inches(1.0);
        String nonNumericInput = "1.0 inch";
        assertFalse(inch.equals(nonNumericInput), "Comparing an Inches object with a different type should return false.");
    }

    @Test
    void givenSameInchesReference_WhenCompared_ShouldReturnTrue() {
        Main.Inches inch = new Main.Inches(1.0);
        assertTrue(inch.equals(inch), "An Inches object compared with itself must return true.");
    }

    @Test
    void givenFeetAndInchesWithSameValue_WhenCompared_ShouldReturnFalse() {
        Main.Feet feet = new Main.Feet(1.0);
        Main.Inches inch = new Main.Inches(1.0);

        // This validates type safety across units (1 ft is not structurally equal to 1 inch object)
        assertFalse(feet.equals(inch), "Feet and Inches objects should not be equal even if their numeric values match.");
    }
}