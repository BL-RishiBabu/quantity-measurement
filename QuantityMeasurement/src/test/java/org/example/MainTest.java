package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test public void testFeetEquality() {
        Quantity<LengthUnit> f1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> f2 = new Quantity<>(1.0, LengthUnit.FEET);
        assertEquals(f1, f2);
    }

    @Test public void testInchesInequality() {
        Quantity<LengthUnit> in1 = new Quantity<>(1.0, LengthUnit.INCHES);
        Quantity<LengthUnit> in2 = new Quantity<>(2.0, LengthUnit.INCHES);
        assertEquals(in1, in2);
    }

    @Test public void testFeetInchesComparison() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(feet, inches);
    }

    @Test public void testFeetInequality() {
        Quantity<LengthUnit> f1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> f2 = new Quantity<>(2.0, LengthUnit.FEET);
        assertNotEquals(f1, f2);
    }

    @Test public void testCrossUnitInequality() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(1.0, LengthUnit.INCHES);
        assertEquals(feet, inches);
    }

    @Test public void yardEquals36Inches() {
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> inches = new Quantity<>(36.0, LengthUnit.INCHES);
        assertEquals(yard, inches);
    }

    @Test public void lengthYardsEqualsFeet() {
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> feet = new Quantity<>(3.0, LengthUnit.FEET);
        assertEquals(yard, feet);
    }

    @Test public void lengthFeetEqualsInches() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(feet, inches);
    }

    @Test public void testGenericTypeSafetyWithWeight() {
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.GRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1.0, WeightUnit.GRAM);
        assertEquals(w1, w2);
    }

    @Test public void weightKilogramEqualsGrams() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertEquals(kg, g);
    }

    @Test public void volumeLiterEqualsMilliliters() {
        Quantity<VolumeUnit> l = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertEquals(l, ml);
    }

    @Test public void convertVolumeLitersToMilliliters() {
        Quantity<VolumeUnit> liter = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertEquals(1000.0, liter.convertTo(VolumeUnit.MILLILITRE), 0.001);
    }

    @Test public void preventCrossTypeComparisonLengthVsWeight() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertNotEquals(length, weight);
    }

    @Test public void preventCrossTypeAdditionLengthVsWeight() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertThrows(IllegalArgumentException.class, () -> length.add((Quantity) weight));
    }

    @Test public void preventCrossTypeConversionLengthToWeight() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertThrows(IllegalArgumentException.class, () -> volume.convertTo(LengthUnit.FEET));
    }

    @Test public void addLengthFeetAndInches() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(new Quantity<>(24.0, LengthUnit.INCHES), feet.add(inches, LengthUnit.INCHES));
    }

    @Test public void addLengthYardsAndFeet() {
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> feet = new Quantity<>(3.0, LengthUnit.FEET);
        assertEquals(new Quantity<>(6.0, LengthUnit.FEET), yard.add(feet, LengthUnit.FEET));
    }

    @Test public void addWeightKilogramsAndPounds() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> lb = new Quantity<>(1.0, WeightUnit.POUND);
        assertEquals(1453.592, kg.add(lb, WeightUnit.GRAM).getValue(), 0.001);
    }

    @Test public void addVolumeLitersAndMilliliters() {
        Quantity<VolumeUnit> l = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertEquals(2.0, l.add(ml, VolumeUnit.LITRE).getValue(), 0.001);
    }

    @Test public void convertLengthFeetToInches() {
        Quantity<LengthUnit> feet = new Quantity<>(2.0, LengthUnit.FEET);
        assertEquals(24.0, feet.convertTo(LengthUnit.INCHES), 0.001);
    }

    @Test public void convertLengthYardsToInches() {
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARDS);
        assertEquals(36.0, yard.convertTo(LengthUnit.INCHES), 0.001);
    }

    @Test public void convertWeightKilogramsToGrams() {
        Quantity<WeightUnit> kg = new Quantity<>(2.5, WeightUnit.KILOGRAM);
        assertEquals(2500.0, kg.convertTo(WeightUnit.GRAM), 0.001);
    }

    @Test public void addWeightKilogramsAndGrams() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = new Quantity<>(500.0, WeightUnit.GRAM);
        assertEquals(1.5, kg.add(g, WeightUnit.KILOGRAM).getValue(), 0.001);
    }

    @Test public void addWeightTonnesAndKilograms() {
        Quantity<WeightUnit> tonne = new Quantity<>(1.0, WeightUnit.TONNE);
        Quantity<WeightUnit> kg = new Quantity<>(500.0, WeightUnit.KILOGRAM);
        assertEquals(1500.0, tonne.add(kg, WeightUnit.KILOGRAM).getValue(), 0.001);
    }

    @Test public void testSubtractionOfVolumesInSameUnit() {
        Quantity<VolumeUnit> v1 = new Quantity<>(10.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(3.0, VolumeUnit.LITRE);
        assertEquals(7.0, v1.subtract(v2).getValue(), 0.001);
    }

    @Test public void testSubtractionOfVolumesWithDifferentUnits() {
        Quantity<VolumeUnit> v1 = new Quantity<>(2.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        assertEquals(1.5, v1.subtract(v2).getValue(), 0.001);
    }

    @Test public void testSubtractionWithTargetUnit() {
        Quantity<LengthUnit> feet = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(6.0, LengthUnit.INCHES);
        assertEquals(114.0, feet.subtract(inches, LengthUnit.INCHES).getValue(), 0.001);
    }

    @Test public void testSubtractionOfWeightsInSameUnit() {
        Quantity<WeightUnit> w1 = new Quantity<>(500.0, WeightUnit.GRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(200.0, WeightUnit.GRAM);
        assertEquals(300.0, w1.subtract(w2).getValue(), 0.001);
    }

    @Test public void testSubtractionOfWeightsWithDifferentUnits() {
        Quantity<WeightUnit> kg = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = new Quantity<>(500.0, WeightUnit.GRAM);
        assertEquals(1.5, kg.subtract(g).getValue(), 0.001);
    }

    @Test public void testDivisionWithTargetUnit() {
        Quantity<LengthUnit> f1 = new Quantity<>(12.0, LengthUnit.FEET);
        Quantity<LengthUnit> f2 = new Quantity<>(3.0, LengthUnit.FEET);
        assertEquals(4.0, f1.divide(f2), 0.001);
    }

    @Test public void testDivisionOfWeightsWithDifferentUnits() {
        Quantity<WeightUnit> kg = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = new Quantity<>(500.0, WeightUnit.GRAM);
        assertEquals(4.0, kg.divide(g), 0.001);
    }

    @Test public void testDivisionOfWeightsInSameUnit() {
        Quantity<WeightUnit> g1 = new Quantity<>(100.0, WeightUnit.GRAM);
        Quantity<WeightUnit> g2 = new Quantity<>(20.0, WeightUnit.GRAM);
        assertEquals(5.0, g1.divide(g2), 0.001);
    }

    @Test public void testDivisionOfVolumesWithDifferentUnits() {
        Quantity<VolumeUnit> l = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml = new Quantity<>(250.0, VolumeUnit.MILLILITRE);
        assertEquals(4.0, l.divide(ml), 0.001);
    }

    @Test public void testDivisionOfVolumesInSameUnit() {
        Quantity<VolumeUnit> l1 = new Quantity<>(10.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> l2 = new Quantity<>(2.0, VolumeUnit.LITRE);
        assertEquals(5.0, l1.divide(l2), 0.001);
    }

    @Test public void testDivisionByZero_CentralizedException() {
        Quantity<LengthUnit> feet = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> zeroFeet = new Quantity<>(0.0, LengthUnit.FEET);
        assertThrows(ArithmeticException.class, () -> feet.divide(zeroFeet));
    }
}