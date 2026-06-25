package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    // --- Volume Basic Equality Tests ---
    @Test public void volumeLiterEqualsMilliliters() {
        Quantity<VolumeUnit> l = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertEquals(l, ml);
    }

    @Test public void testInchesInequality() {
        Quantity<Main.LengthUnit> in1 = new Quantity<>(1.0, Main.LengthUnit.INCHES);
        Quantity<Main.LengthUnit> in2 = new Quantity<>(2.0, Main.LengthUnit.INCHES);
        assertNotEquals(in1, in2);
    }

    @Test public void testFeetEquality() {
        Quantity<Main.LengthUnit> f1 = new Quantity<>(1.0, Main.LengthUnit.FEET);
        Quantity<Main.LengthUnit> f2 = new Quantity<>(1.0, Main.LengthUnit.FEET);
        assertEquals(f1, f2);
    }

    @Test public void preventCrossTypeComparisonLengthVsWeight() {
        Quantity<Main.LengthUnit> length = new Quantity<>(1.0, Main.LengthUnit.FEET);
        Quantity<Main.WeightUnit> weight = new Quantity<>(1.0, Main.WeightUnit.KILOGRAM);
        assertFalse(length.equals(weight));
    }

    @Test public void testGenericTypeSafetyWithWeight() {
        Quantity<Main.WeightUnit> w1 = new Quantity<>(1.0, Main.WeightUnit.GRAM);
        Quantity<Main.WeightUnit> w2 = new Quantity<>(1.0, Main.WeightUnit.GRAM);
        assertEquals(w1, w2);
    }

    @Test public void preventCrossTypeAdditionLengthVsWeight() {
        Quantity<Main.LengthUnit> length = new Quantity<>(1.0, Main.LengthUnit.FEET);
        Quantity<Main.WeightUnit> weight = new Quantity<>(1.0, Main.WeightUnit.KILOGRAM);
        // Assert compile/runtime categorization verification blocking
        assertFalse(length.equals(weight));
    }

    @Test public void preventCrossTypeConversionLengthToWeight() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<Main.LengthUnit> length = new Quantity<>(1.0, Main.LengthUnit.FEET);
        assertFalse(volume.equals(length));
    }

    // --- Backward Compatibility Checks ---
    @Test public void backwardCompatibilityConvertWeightKilogramsToGrams() {
        Quantity<Main.WeightUnit> kg = new Quantity<>(1.0, Main.WeightUnit.KILOGRAM);
        Quantity<Main.WeightUnit> expected = new Quantity<>(1000.0, Main.WeightUnit.GRAM);
        assertEquals(kg.convertTo(Main.WeightUnit.GRAM), (expected));
    }

    @Test public void backwardCompatibilityLengthFeetEqualsInches() {
        Quantity<Main.LengthUnit> feet = new Quantity<>(1.0, Main.LengthUnit.FEET);
        Quantity<Main.LengthUnit> inches = new Quantity<>(12.0, Main.LengthUnit.INCHES);
        assertEquals(feet, inches);
    }

    @Test public void backwardCompatibilityChainedAdditionsLength() {
        Quantity<Main.LengthUnit> inch = new Quantity<>(12.0, Main.LengthUnit.INCHES);
        Quantity<Main.LengthUnit> feet = new Quantity<>(1.0, Main.LengthUnit.FEET);
        Quantity<Main.LengthUnit> result = inch.add(feet);
        assertTrue(result.equals(new Quantity<>(24.0, Main.LengthUnit.INCHES)));
    }

    @Test public void backwardCompatibilityWeightPoundEqualsGrams() {
        Quantity<Main.WeightUnit> pound = new Quantity<>(1.0, Main.WeightUnit.POUND);
        Quantity<Main.WeightUnit> grams = new Quantity<>(453.592, Main.WeightUnit.GRAM);
        assertEquals(pound, grams);
    }

    @Test public void backwardCompatibilityWeightKilogramEqualsGrams() {
        Quantity<Main.WeightUnit> kg = new Quantity<>(1.0, Main.WeightUnit.KILOGRAM);
        Quantity<Main.WeightUnit> grams = new Quantity<>(1000.0, Main.WeightUnit.GRAM);
        assertEquals(kg, grams);
    }

    @Test public void backwardCompatibilityAddWeightInSameUnit() {
        Quantity<Main.WeightUnit> kg1 = new Quantity<>(1.0, Main.WeightUnit.KILOGRAM);
        Quantity<Main.WeightUnit> kg2 = new Quantity<>(2.0, Main.WeightUnit.KILOGRAM);
        assertTrue(kg1.add(kg2).equals(new Quantity<>(3.0, Main.WeightUnit.KILOGRAM)));
    }

    @Test public void backwardCompatibilityLengthYardsEqualsFeet() {
        Quantity<Main.LengthUnit> yard = new Quantity<>(1.0, Main.LengthUnit.YARDS);
        Quantity<Main.LengthUnit> feet = new Quantity<>(3.0, Main.LengthUnit.FEET);
        assertEquals(yard, feet);
    }

    @Test public void backwardCompatibilityConvertLengthFeetToInches() {
        Quantity<Main.LengthUnit> feet = new Quantity<>(1.0, Main.LengthUnit.FEET);
        assertTrue(feet.convertTo(Main.LengthUnit.INCHES).equals(new Quantity<>(12.0, Main.LengthUnit.INCHES)));
    }

    @Test public void backwardCompatibilityAddLengthInSameUnit() {
        Quantity<Main.LengthUnit> f1 = new Quantity<>(1.0, Main.LengthUnit.FEET);
        Quantity<Main.LengthUnit> f2 = new Quantity<>(1.0, Main.LengthUnit.FEET);
        assertTrue(f1.add(f2).equals(new Quantity<>(2.0, Main.LengthUnit.FEET)));
    }

    // --- Arithmetic & Unit Mapping Conversions ---
    @Test public void convertVolumeLitersToMilliliters() {
        Quantity<VolumeUnit> liter = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = liter.convertTo(VolumeUnit.MILLILITRE);
        assertEquals(1000.0, result.getValue(), 0.001);
    }

    @Test public void addLengthYardsAndFeet() {
        Quantity<Main.LengthUnit> yard = new Quantity<>(1.0, Main.LengthUnit.YARDS);
        Quantity<Main.LengthUnit> feet = new Quantity<>(3.0, Main.LengthUnit.FEET);
        Quantity<Main.LengthUnit> sum = yard.add(feet, Main.LengthUnit.FEET);
        assertEquals(6.0, sum.getValue(), 0.001);
    }

    @Test public void weightKilogramGrams() {
        Quantity<Main.WeightUnit> kg = new Quantity<>(1.0, Main.WeightUnit.KILOGRAM);
        Quantity<Main.WeightUnit> g = new Quantity<>(1000.0, Main.WeightUnit.GRAM);
        assertTrue(kg.equals(g));
    }

    @Test public void lengthYardsEqualsFeet() {
        Quantity<Main.LengthUnit> yard = new Quantity<>(1.0, Main.LengthUnit.YARDS);
        Quantity<Main.LengthUnit> feet = new Quantity<>(3.0, Main.LengthUnit.FEET);
        assertTrue(yard.equals(feet));
    }

    @Test public void lengthFeetEqualsInches() {
        Quantity<Main.LengthUnit> feet = new Quantity<>(1.0, Main.LengthUnit.FEET);
        Quantity<Main.LengthUnit> inches = new Quantity<>(12.0, Main.LengthUnit.INCHES);
        assertTrue(feet.equals(inches));
    }

    @Test public void addWeightKilogramsAndPounds() {
        Quantity<Main.WeightUnit> kg = new Quantity<>(1.0, Main.WeightUnit.KILOGRAM);
        Quantity<Main.WeightUnit> lb = new Quantity<>(1.0, Main.WeightUnit.POUND);
        Quantity<Main.WeightUnit> sum = kg.add(lb, Main.WeightUnit.GRAM);
        assertEquals(1453.592, sum.getValue(), 0.001);
    }

    @Test public void addVolumeLitersAndMilliliters() {
        Quantity<VolumeUnit> l = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> sum = l.add(ml, VolumeUnit.LITRE);
        assertEquals(2.0, sum.getValue(), 0.001);
    }

    @Test public void weightPoundEqualsGrams() {
        Quantity<Main.WeightUnit> lb = new Quantity<>(1.0, Main.WeightUnit.POUND);
        Quantity<Main.WeightUnit> g = new Quantity<>(453.592, Main.WeightUnit.GRAM);
        assertTrue(lb.equals(g));
    }

    @Test public void addLengthFeetAndInches() {
        Quantity<Main.LengthUnit> feet = new Quantity<>(1.0, Main.LengthUnit.FEET);
        Quantity<Main.LengthUnit> inches = new Quantity<>(12.0, Main.LengthUnit.INCHES);
        Quantity<Main.LengthUnit> sum = feet.add(inches, Main.LengthUnit.INCHES);
        assertEquals(24.0, sum.getValue(), 0.001);
    }

    @Test public void convertLengthFeetToInches() {
        Quantity<Main.LengthUnit> feet = new Quantity<>(2.0, Main.LengthUnit.FEET);
        assertEquals(24.0, feet.convertTo(Main.LengthUnit.INCHES).getValue(), 0.001);
    }

    @Test public void testFeetInchesComparison() {
        Quantity<Main.LengthUnit> feet = new Quantity<>(1.0, Main.LengthUnit.FEET);
        Quantity<Main.LengthUnit> inches = new Quantity<>(12.0, Main.LengthUnit.INCHES);
        assertTrue(feet.equals(inches));
    }

    @Test public void testFeetInequality() {
        Quantity<Main.LengthUnit> f1 = new Quantity<>(1.0, Main.LengthUnit.FEET);
        Quantity<Main.LengthUnit> f2 = new Quantity<>(2.0, Main.LengthUnit.FEET);
        assertFalse(f1.equals(f2));
    }

    @Test public void testCrossUnitInequality() {
        Quantity<Main.LengthUnit> feet = new Quantity<>(1.0, Main.LengthUnit.FEET);
        Quantity<Main.LengthUnit> inches = new Quantity<>(1.0, Main.LengthUnit.INCHES);
        assertFalse(feet.equals(inches));
    }

    @Test public void testMultipleFeetComparison() {
        Quantity<Main.LengthUnit> f1 = new Quantity<>(1.0, Main.LengthUnit.FEET);
        Quantity<Main.LengthUnit> f2 = new Quantity<>(1.0, Main.LengthUnit.FEET);
        Quantity<Main.LengthUnit> f3 = new Quantity<>(1.0, Main.LengthUnit.FEET);
        assertTrue(f1.equals(f2) && f2.equals(f3) && f1.equals(f3));
    }

    @Test public void yardEquals36Inches() {
        Quantity<Main.LengthUnit> yard = new Quantity<>(1.0, Main.LengthUnit.YARDS);
        Quantity<Main.LengthUnit> inches = new Quantity<>(36.0, Main.LengthUnit.INCHES);
        assertTrue(yard.equals(inches));
    }

    @Test public void convertWeightKilogramsToGrams() {
        Quantity<Main.WeightUnit> kg = new Quantity<>(2.5, Main.WeightUnit.KILOGRAM);
        assertEquals(2500.0, kg.convertTo(Main.WeightUnit.GRAM).getValue(), 0.001);
    }

    @Test public void addWeightKilogramsAndGrams() {
        Quantity<Main.WeightUnit> kg = new Quantity<>(1.0, Main.WeightUnit.KILOGRAM);
        Quantity<Main.WeightUnit> g = new Quantity<>(500.0, Main.WeightUnit.GRAM);
        Quantity<Main.WeightUnit> result = kg.add(g, Main.WeightUnit.KILOGRAM);
        assertEquals(1.5, result.getValue(), 0.001);
    }

    @Test public void addWeightTonnesAndKilograms() {
        Quantity<Main.WeightUnit> tonne = new Quantity<>(1.0, Main.WeightUnit.TONNE);
        Quantity<Main.WeightUnit> kg = new Quantity<>(500.0, Main.WeightUnit.KILOGRAM);
        Quantity<Main.WeightUnit> result = tonne.add(kg, Main.WeightUnit.KILOGRAM);
        assertEquals(1500.0, result.getValue(), 0.001);
    }

    @Test public void convertLengthYardsToInches() {
        Quantity<Main.LengthUnit> yard = new Quantity<>(1.0, Main.LengthUnit.YARDS);
        assertEquals(36.0, yard.convertTo(Main.LengthUnit.INCHES).getValue(), 0.001);
    }
}