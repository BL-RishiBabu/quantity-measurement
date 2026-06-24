package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    private void assertSum(double val1, LengthUnit unit1, double val2, LengthUnit unit2, LengthUnit targetUnit, double expectedValue) {
        Length l1 = new Length(val1, unit1);
        Length l2 = new Length(val2, unit2);
        Length result = l1.add(l2, targetUnit);
        assertEquals(result, new Length(expectedValue, targetUnit));
    }

    @Test
    public void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0, LengthUnit.FEET.getConversionFactor(), 0.01);
    }

    @Test
    public void testLengthUnitEnum_InchesConstant() {
        assertEquals(0.0833, LengthUnit.INCHES.getConversionFactor(), 0.01);
    }

    @Test
    public void testLengthUnitEnum_YardsConstant() {
        assertEquals(3.0, LengthUnit.YARDS.getConversionFactor(), 0.01);
    }

    @Test
    public void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0, LengthUnit.INCHES.convertToBaseUnit(12.0), 0.01);
    }

    @Test
    public void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(1.0), 0.01);
    }

    @Test
    public void testQuantityLengthRefactored_Equality() {
        assertEquals(new Length(1.0, LengthUnit.FEET), new Length(12.0, LengthUnit.INCHES));
    }

    @Test
    public void testQuantityLengthRefactored_AddWithTargetUnit() {
        assertSum(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCHES, LengthUnit.YARDS, 0.67);
    }

    @Test
    public void testQuantityLengthRefactored_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new Length(1.0, null));
    }
}