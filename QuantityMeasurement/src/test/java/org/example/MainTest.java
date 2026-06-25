package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void volumeLiterEqualsMilliliters() {
        Quantity<VolumeUnit> l = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertTrue(l.equals(ml));
    }

    @Test
    public void convertVolumeLitersToMilliliters() {
        Quantity<VolumeUnit> liter = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = liter.convertTo(VolumeUnit.MILLILITRE);
        assertEquals(1000.0, result.getValue(), 0.001);
    }

    @Test
    public void addVolumeLitersAndMilliliters() {
        Quantity<VolumeUnit> l = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> sum = l.add(ml, VolumeUnit.LITRE);
        assertEquals(2.0, sum.getValue(), 0.001);
    }

    @Test
    public void testFeetEquality() {
        Quantity<LengthUnit> f1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> f2 = new Quantity<>(1.0, LengthUnit.FEET);
        assertTrue(f1.equals(f2));
    }

    @Test
    public void testInchesInequality() {
        Quantity<LengthUnit> in1 = new Quantity<>(1.0, LengthUnit.INCHES);
        Quantity<LengthUnit> in2 = new Quantity<>(2.0, LengthUnit.INCHES);
        assertFalse(in1.equals(in2));
    }

    @Test
    public void testFeetInchesComparison() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
        assertTrue(feet.equals(inches));
    }

    @Test
    public void testFeetInequality() {
        Quantity<LengthUnit> f1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> f2 = new Quantity<>(2.0, LengthUnit.FEET);
        assertFalse(f1.equals(f2));
    }

    @Test
    public void testCrossUnitInequality() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(1.0, LengthUnit.INCHES);
        assertFalse(feet.equals(inches));
    }

    @Test
    public void testMultipleFeetComparison() {
        Quantity<LengthUnit> f1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> f2 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> f3 = new Quantity<>(1.0, LengthUnit.FEET);
        assertTrue(f1.equals(f2) && f2.equals(f3) && f1.equals(f3));
    }

    @Test
    public void yardEquals36Inches() {
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> inches = new Quantity<>(36.0, LengthUnit.INCHES);
        assertTrue(yard.equals(inches));
    }

    @Test
    public void lengthYardsEqualsFeet() {
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> feet = new Quantity<>(3.0, LengthUnit.FEET);
        assertTrue(yard.equals(feet));
    }

    @Test
    public void lengthFeetEqualsInches() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
        assertTrue(feet.equals(inches));
    }

    @Test
    public void addLengthYardsAndFeet() {
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> feet = new Quantity<>(3.0, LengthUnit.FEET);
        Quantity<LengthUnit> sum = yard.add(feet, LengthUnit.FEET);
        assertEquals(6.0, sum.getValue(), 0.001);
    }

    @Test
    public void addLengthFeetAndInches() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> sum = feet.add(inches, LengthUnit.INCHES);
        assertEquals(24.0, sum.getValue(), 0.001);
    }

    @Test
    public void convertLengthFeetToInches() {
        Quantity<LengthUnit> feet = new Quantity<>(2.0, LengthUnit.FEET);
        assertEquals(24.0, feet.convertTo(LengthUnit.INCHES).getValue(), 0.001);
    }

    @Test
    public void convertLengthYardsToInches() {
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARDS);
        assertEquals(36.0, yard.convertTo(LengthUnit.INCHES).getValue(), 0.001);
    }

    @Test
    public void testGenericTypeSafetyWithWeight() {
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.GRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1.0, WeightUnit.GRAM);
        assertTrue(w1.equals(w2));
    }

    @Test
    public void weightKilogramGrams() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertTrue(kg.equals(g));
    }

    @Test
    public void addWeightKilogramsAndPounds() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> lb = new Quantity<>(1.0, WeightUnit.POUND);
        Quantity<WeightUnit> sum = kg.add(lb, WeightUnit.GRAM);
        assertEquals(1453.592, sum.getValue(), 0.001);
    }

    @Test
    public void weightPoundEqualsGrams() {
        Quantity<WeightUnit> lb = new Quantity<>(1.0, WeightUnit.POUND);
        Quantity<WeightUnit> g = new Quantity<>(453.592, WeightUnit.GRAM);
        assertTrue(lb.equals(g));
    }

    @Test
    public void convertWeightKilogramsToGrams() {
        Quantity<WeightUnit> kg = new Quantity<>(2.5, WeightUnit.KILOGRAM);
        assertEquals(2500.0, kg.convertTo(WeightUnit.GRAM).getValue(), 0.001);
    }

    @Test
    public void addWeightKilogramsAndGrams() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = new Quantity<>(500.0, WeightUnit.GRAM);
        Quantity<WeightUnit> result = kg.add(g, WeightUnit.KILOGRAM);
        assertEquals(1.5, result.getValue(), 0.001);
    }

    @Test
    public void addWeightTonnesAndKilograms() {
        Quantity<WeightUnit> tonne = new Quantity<>(1.0, WeightUnit.TONNE);
        Quantity<WeightUnit> kg = new Quantity<>(500.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> result = tonne.add(kg, WeightUnit.KILOGRAM);
        assertEquals(1500.0, result.getValue(), 0.001);
    }

    @Test
    public void preventCrossTypeComparisonLengthVsWeight() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertFalse(length.equals(weight));
    }

    @Test
    public void preventCrossTypeAdditionLengthVsWeight() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertFalse(length.equals(weight));
    }

    @Test
    public void preventCrossTypeConversionLengthToWeight() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        assertFalse(volume.equals(length));
    }

    @Test
    public void backwardCompatibilityConvertWeightKilogramsToGrams() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> expected = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertTrue(kg.convertTo(WeightUnit.GRAM).equals(expected));
    }

    @Test
    public void backwardCompatibilityLengthFeetEqualsInches() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
        assertTrue(feet.equals(inches));
    }

    @Test
    public void backwardCompatibilityChainedAdditionsLength() {
        Quantity<LengthUnit> inch = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = inch.add(feet);
        assertTrue(result.equals(new Quantity<>(24.0, LengthUnit.INCHES)));
    }

    @Test
    public void backwardCompatibilityWeightPoundEqualsGrams() {
        Quantity<WeightUnit> pound = new Quantity<>(1.0, WeightUnit.POUND);
        Quantity<WeightUnit> grams = new Quantity<>(453.592, WeightUnit.GRAM);
        assertTrue(pound.equals(grams));
    }

    @Test
    public void backwardCompatibilityWeightKilogramEqualsGrams() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> grams = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertTrue(kg.equals(grams));
    }

    @Test
    public void backwardCompatibilityAddWeightInSameUnit() {
        Quantity<WeightUnit> kg1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> kg2 = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        assertTrue(kg1.add(kg2).equals(new Quantity<>(3.0, WeightUnit.KILOGRAM)));
    }

    @Test
    public void backwardCompatibilityLengthYardsEqualsFeet() {
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> feet = new Quantity<>(3.0, LengthUnit.FEET);
        assertTrue(yard.equals(feet));
    }

    @Test
    public void backwardCompatibilityConvertLengthFeetToInches() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        assertTrue(feet.convertTo(LengthUnit.INCHES).equals(new Quantity<>(12.0, LengthUnit.INCHES)));
    }

    @Test
    public void backwardCompatibilityAddLengthInSameUnit() {
        Quantity<LengthUnit> f1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> f2 = new Quantity<>(1.0, LengthUnit.FEET);
        assertTrue(f1.add(f2).equals(new Quantity<>(2.0, LengthUnit.FEET)));
    }

    @Test
    public void testSubtraction_SameUnit_FeetMinusFeet() {
        Quantity<LengthUnit> f1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> f2 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = f1.subtract(f2);
        assertEquals(5.0, result.getValue(), 0.001);
    }

    @Test
    public void testSubtraction_CrossUnit_FeetMinusInches() {
        Quantity<LengthUnit> feet = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(6.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = feet.subtract(inches);
        assertEquals(9.5, result.getValue(), 0.001);
    }

    @Test
    public void testDivision_SameUnit_FeetDividedByFeet() {
        Quantity<LengthUnit> f1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> f2 = new Quantity<>(2.0, LengthUnit.FEET);
        double ratio = f1.divide(f2);
        assertEquals(5.0, ratio, 0.001);
    }

    @Test
    public void testDivision_ByZero_ThrowsException() {
        Quantity<LengthUnit> feet = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> zeroFeet = new Quantity<>(0.0, LengthUnit.FEET);
        assertThrows(ArithmeticException.class, () -> feet.divide(zeroFeet));
    }
}