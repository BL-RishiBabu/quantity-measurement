package org.example;

import java.util.Objects;

public class Length {
    private final double value;
    private final LengthUnit unit;

    public enum LengthUnit {
        INCHES(1.0),
        FEET(12.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    public Length(double value, LengthUnit unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Value must be a finite number");
        this.value = value;
        this.unit = unit;
    }

    private double convertToBaseUnit() {
        return this.value * this.unit.getConversionFactor();
    }

    private double convertFromBaseToTargetUnit(double lengthInInches, LengthUnit targetUnit) {
        double rawConverted = lengthInInches / targetUnit.getConversionFactor();
        return Math.round(rawConverted * 100.0) / 100.0;
    }

    private Length addAndConvert(Length thatLength, LengthUnit targetUnit) {
        if (thatLength == null) throw new IllegalArgumentException("Operand length cannot be null");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");

        double sumInInches = this.convertToBaseUnit() + thatLength.convertToBaseUnit();
        double finalValue = convertFromBaseToTargetUnit(sumInInches, targetUnit);

        return new Length(finalValue, targetUnit);
    }

    public Length add(Length thatLength) {
        return addAndConvert(thatLength, this.unit);
    }

    public Length add(Length thatLength, LengthUnit targetUnit) {
        return addAndConvert(thatLength, targetUnit);
    }

    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double inches = convertToBaseUnit();
        return new Length(convertFromBaseToTargetUnit(inches, targetUnit), targetUnit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Length length = (Length) o;
        return Math.abs(this.convertToBaseUnit() - length.convertToBaseUnit()) < 0.01;
    }

    @Override
    public int hashCode() {
        return Objects.hash(convertToBaseUnit());
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}