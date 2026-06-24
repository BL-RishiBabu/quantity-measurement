package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(2.0, Length.LengthUnit.FEET);
        Length sumLength = Main.demonstrateLengthAddition(length1, length2);
        Length expectedLength = new Length(3.0, Length.LengthUnit.FEET);
        assertTrue(Main.demonstrateLengthEquality(sumLength, expectedLength));
    }

    @Test
    public void testAddition_SameUnit_InchPlusInch() {
        Length length1 = new Length(6.0, Length.LengthUnit.INCHES);
        Length length2 = new Length(6.0, Length.LengthUnit.INCHES);
        Length sumLength = Main.demonstrateLengthAddition(length1, length2);
        Length expectedLength = new Length(12.0, Length.LengthUnit.INCHES);
        assertTrue(Main.demonstrateLengthEquality(sumLength, expectedLength));
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);
        Length sumLength = Main.demonstrateLengthAddition(length1, length2);
        Length expectedLength = new Length(2.0, Length.LengthUnit.FEET);
        assertTrue(Main.demonstrateLengthEquality(sumLength, expectedLength));
    }

    @Test
    public void testAddition_CrossUnit_InchPlusFeet() {
        Length length1 = new Length(12.0, Length.LengthUnit.INCHES);
        Length length2 = new Length(1.0, Length.LengthUnit.FEET);
        Length sumLength = Main.demonstrateLengthAddition(length1, length2);
        Length expectedLength = new Length(24.0, Length.LengthUnit.INCHES);
        assertTrue(Main.demonstrateLengthEquality(sumLength, expectedLength));
    }

    @Test
    public void testAddition_CrossUnit_YardPlusFeet() {
        Length length1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length length2 = new Length(3.0, Length.LengthUnit.FEET);
        Length sumLength = Main.demonstrateLengthAddition(length1, length2);
        Length expectedLength = new Length(2.0, Length.LengthUnit.YARDS);
        assertTrue(Main.demonstrateLengthEquality(sumLength, expectedLength));
    }

    @Test
    public void testAddition_CrossUnit_CentimeterPlusInch() {
        Length length1 = new Length(2.54, Length.LengthUnit.CENTIMETERS);
        Length length2 = new Length(1.0, Length.LengthUnit.INCHES);
        Length sumLength = Main.demonstrateLengthAddition(length1, length2);
        Length expectedLength = new Length(5.08, Length.LengthUnit.CENTIMETERS);
        assertTrue(Main.demonstrateLengthEquality(sumLength, expectedLength));
    }

    @Test
    public void testAddition_Commutativity() {
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length sum1 = Main.demonstrateLengthAddition(length1, length2);
        Length sum2 = Main.demonstrateLengthAddition(length2, length1);

        assertTrue(Main.demonstrateLengthEquality(sum1, sum2));
    }

    @Test
    public void testAddition_WithZero() {
        Length length1 = new Length(5.0, Length.LengthUnit.FEET);
        Length length2 = new Length(0.0, Length.LengthUnit.INCHES);
        Length sumLength = Main.demonstrateLengthAddition(length1, length2);
        Length expectedLength = new Length(5.0, Length.LengthUnit.FEET);
        assertTrue(Main.demonstrateLengthEquality(sumLength, expectedLength));
    }

    @Test
    public void testAddition_NegativeValues() {
        Length length1 = new Length(5.0, Length.LengthUnit.FEET);
        Length length2 = new Length(-2.0, Length.LengthUnit.FEET);
        Length sumLength = Main.demonstrateLengthAddition(length1, length2);
        Length expectedLength = new Length(3.0, Length.LengthUnit.FEET);
        assertTrue(Main.demonstrateLengthEquality(sumLength, expectedLength));
    }

    @Test
    public void testAddition_NullSecondOperand() {
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> {
            Main.demonstrateLengthAddition(length1, null);
        });
    }

    @Test
    public void testAddition_LargeValues() {
        Length length1 = new Length(1000000.0, Length.LengthUnit.FEET);
        Length length2 = new Length(1000000.0, Length.LengthUnit.FEET);
        Length sumLength = Main.demonstrateLengthAddition(length1, length2);
        Length expectedLength = new Length(2000000.0, Length.LengthUnit.FEET);
        assertTrue(Main.demonstrateLengthEquality(sumLength, expectedLength));
    }

    @Test
    public void testAddition_SmallValues() {
        Length length1 = new Length(0.001, Length.LengthUnit.FEET);
        Length length2 = new Length(0.002, Length.LengthUnit.FEET);
        Length sumLength = Main.demonstrateLengthAddition(length1, length2);
        Length expectedLength = new Length(0.003, Length.LengthUnit.FEET);
        assertTrue(Main.demonstrateLengthEquality(sumLength, expectedLength));
    }
}