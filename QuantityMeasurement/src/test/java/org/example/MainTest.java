package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testFeetEquality() {
        Length feet1 = new Length(1.0, Length.LengthUnit.FEET);
        Length feet2 = new Length(1.0, Length.LengthUnit.FEET);
        assertEquals(feet1, feet2);
    }

    @Test
    public void testInchesEquality() {
        Length inches1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length inches2 = new Length(1.0, Length.LengthUnit.INCHES);
        assertEquals(inches1, inches2);
    }

    @Test
    public void testFeetInchesComparison() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length inches = new Length(12.0, Length.LengthUnit.INCHES);
        assertEquals(feet, inches);
    }

    @Test
    public void testFeetInequality() {
        Length feet1 = new Length(1.0, Length.LengthUnit.FEET);
        Length feet2 = new Length(2.0, Length.LengthUnit.FEET);
        assertNotEquals(feet1, feet2);
    }

    @Test
    public void testInchesInequality() {
        Length inches1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length inches2 = new Length(2.0, Length.LengthUnit.INCHES);
        assertNotEquals(inches1, inches2);
    }

    @Test
    public void testCrossUnitInequality() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length inches = new Length(1.0, Length.LengthUnit.INCHES);
        assertNotEquals(feet, inches);
    }

    @Test
    public void testMultipleFeetComparison() {
        Length feet1 = new Length(1.0, Length.LengthUnit.FEET);
        Length feet2 = new Length(1.0, Length.LengthUnit.FEET);
        Length feet3 = new Length(1.0, Length.LengthUnit.FEET);
        assertEquals(feet1, feet2);
        assertEquals(feet2, feet3);
        assertEquals(feet1, feet3);
    }

    @Test
    public void yardEquals36Inches() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length inches = new Length(36.0, Length.LengthUnit.INCHES);
        assertEquals(yard, inches);
    }

    @Test
    public void centimeterEquals39Point3701Inches() {
        Length cm = new Length(100.0, Length.LengthUnit.CENTIMETERS);
        Length inches = new Length(39.3701, Length.LengthUnit.INCHES);
        assertEquals(cm, inches);
    }

    @Test
    public void threeFeetEqualsOneYard() {
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        assertEquals(feet, yard);
    }

    @Test
    public void thirtyPoint48CmEqualsOneFoot() {
        Length cm = new Length(30.48, Length.LengthUnit.CENTIMETERS);
        Length foot = new Length(1.0, Length.LengthUnit.FEET);
        assertEquals(cm, foot);
    }

    @Test
    public void yardNotEqualToInches() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length inches = new Length(1.0, Length.LengthUnit.INCHES);
        assertNotEquals(yard, inches);
    }

    @Test
    public void referenceEqualitySameObject() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        assertEquals(yard, yard);
    }

    @Test
    public void equalsReturnsFalseForNull() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        assertFalse(yard.equals(null));
    }

    @Test
    public void reflexiveSymmetricAndTransitiveProperty() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        Length inches = new Length(36.0, Length.LengthUnit.INCHES);
        assertEquals(yard, feet);
        assertEquals(feet, inches);
        assertEquals(yard, inches);
    }

    @Test
    public void differentValuesSameUnitNotEqual() {
        Length cm1 = new Length(10.0, Length.LengthUnit.CENTIMETERS);
        Length cm2 = new Length(20.0, Length.LengthUnit.CENTIMETERS);
        assertNotEquals(cm1, cm2);
    }

    @Test
    public void crossUnitEqualityDemonstrateMethod() {
        boolean result = Main.demonstrateLengthComparison(1.0, Length.LengthUnit.YARDS, 3.0, Length.LengthUnit.FEET);
        assertTrue(result);
    }

    @Test
    public void convertFeetToInches() {
        Length lengthInInches = Main.demonstrateLengthConversion(
                3.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        Length expectedLength = new Length(36.0, Length.LengthUnit.INCHES);
        assertTrue(Main.demonstrateLengthEquality(lengthInInches, expectedLength));
    }

    @Test
    public void convertYardsToInchesUsingOverloadedMethod() {
        Length lengthInYards = new Length(2.0, Length.LengthUnit.YARDS);
        Length lengthInInches = Main.demonstrateLengthConversion(
                lengthInYards, Length.LengthUnit.INCHES);
        Length expectedLength = new Length(72.0, Length.LengthUnit.INCHES);
        assertTrue(Main.demonstrateLengthEquality(lengthInInches, expectedLength));
    }
}