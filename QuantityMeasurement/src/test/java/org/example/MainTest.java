package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    private void assertWeightSum(double val1, WeightUnit unit1, double val2, WeightUnit unit2, WeightUnit targetUnit, double expectedValue) {
        Weight w1 = new Weight(val1, unit1);
        Weight w2 = new Weight(val2, unit2);
        Weight result = w1.add(w2, targetUnit);
        assertEquals(result, new Weight(expectedValue, targetUnit));
    }

    @Test
    public void testEquality_KilogramToKilogram_SameValue() {
        assertEquals(new Weight(1.0, WeightUnit.KILOGRAM), new Weight(1.0, WeightUnit.KILOGRAM));
    }

    @Test
    public void testEquality_KilogramToGram_EquivalentValue() {
        assertEquals(new Weight(1.0, WeightUnit.KILOGRAM), new Weight(1000.0, WeightUnit.GRAM));
    }

    @Test
    public void testEquality_KilogramToPound_EquivalentValue() {
        assertEquals(new Weight(1.0, WeightUnit.KILOGRAM), new Weight(2.20, WeightUnit.POUND));
    }

    @Test
    public void testEquality_WeightVsLength_Incompatible() {
        Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);
        Length length = new Length(1.0, LengthUnit.FEET);
        assertNotEquals(weight, length);
    }

    @Test
    public void testConversion_KilogramToGram() {
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        assertEquals(1000.0, kg.convertTo(WeightUnit.GRAM).getValue(), 0.01);
    }

    @Test
    public void testConversion_RoundTrip() {
        Weight baseline = new Weight(1.5, WeightUnit.KILOGRAM);
        Weight roundTrip = baseline.convertTo(WeightUnit.GRAM).convertTo(WeightUnit.KILOGRAM);
        assertEquals(baseline.getValue(), roundTrip.getValue(), 0.01);
    }

    @Test
    public void testAddition_SameUnit_KilogramPlusKilogram() {
        assertWeightSum(1.0, WeightUnit.KILOGRAM, 2.0, WeightUnit.KILOGRAM, WeightUnit.KILOGRAM, 3.0);
    }

    @Test
    public void testAddition_CrossUnit_KilogramPlusGram() {
        // Result explicitly mapped out into targeted granular units
        assertWeightSum(1.0, WeightUnit.KILOGRAM, 1000.0, WeightUnit.GRAM, WeightUnit.GRAM, 2000.0);
    }

    @Test
    public void testAddition_WithZero() {
        assertWeightSum(5.0, WeightUnit.KILOGRAM, 0.0, WeightUnit.GRAM, WeightUnit.KILOGRAM, 5.0);
    }

    @Test
    public void testAddition_NegativeValues() {
        assertWeightSum(5.0, WeightUnit.KILOGRAM, -2000.0, WeightUnit.GRAM, WeightUnit.KILOGRAM, 3.0);
    }

    @Test
    public void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new Weight(1.0, null));
    }
}