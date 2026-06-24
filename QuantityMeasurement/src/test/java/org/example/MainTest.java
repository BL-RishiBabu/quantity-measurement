package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.Length.LengthUnit;

public class MainTest {

    // Helper method to make individual test cases clean and simple
    private void assertSum(double val1, LengthUnit unit1, double val2, LengthUnit unit2, LengthUnit targetUnit, double expectedValue) {
        Length length1 = new Length(val1, unit1);
        Length length2 = new Length(val2, unit2);

        Length result = Main.demonstrateLengthAddition(length1, length2, targetUnit);
        Length expected = new Length(expectedValue, targetUnit);

        assertTrue(Main.demonstrateLengthEquality(result, expected),
                String.format("Failed: %s %s + %s %s should equal %s %s", val1, unit1, val2, unit2, expectedValue, targetUnit));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {
        assertSum(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCHES, LengthUnit.FEET, 2.0);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Inches() {
        assertSum(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCHES, LengthUnit.INCHES, 24.0);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Yards() {
        assertSum(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCHES, LengthUnit.YARDS, 0.67);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Centimeters() {
        assertSum(1.0, LengthUnit.INCHES, 1.0, LengthUnit.INCHES, LengthUnit.CENTIMETERS, 5.08);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
        assertSum(2.0, LengthUnit.YARDS, 3.0, LengthUnit.FEET, LengthUnit.YARDS, 3.0);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
        assertSum(2.0, LengthUnit.YARDS, 3.0, LengthUnit.FEET, LengthUnit.FEET, 9.0);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Commutativity() {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);

        Length sum1 = Main.demonstrateLengthAddition(length1, length2, LengthUnit.YARDS);
        Length sum2 = Main.demonstrateLengthAddition(length2, length1, LengthUnit.YARDS);

        assertTrue(Main.demonstrateLengthEquality(sum1, sum2));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_WithZero() {
        assertSum(5.0, LengthUnit.FEET, 0.0, LengthUnit.INCHES, LengthUnit.YARDS, 1.67);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NegativeValues() {
        assertSum(5.0, LengthUnit.FEET, -2.0, LengthUnit.FEET, LengthUnit.INCHES, 36.0);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        assertSum(1000.0, LengthUnit.FEET, 500.0, LengthUnit.FEET, LengthUnit.INCHES, 18000.0);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
        assertSum(12.0, LengthUnit.INCHES, 12.0, LengthUnit.INCHES, LengthUnit.YARDS, 0.67);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        assertThrows(IllegalArgumentException.class, () -> Main.demonstrateLengthAddition(length1, length2, null));
    }
}