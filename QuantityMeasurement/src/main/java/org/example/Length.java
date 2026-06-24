package org.example;

import java.util.Objects;

public class Length {
    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Value must be a finite number");
        this.value = value;
        this.unit = unit;
    }

    private double convertToBaseUnit() {
        return this.unit.convertToBaseUnit(this.value);
    }

    private double convertFromBaseToTargetUnit(double lengthInBase, LengthUnit targetUnit) {
        return targetUnit.convertFromBaseUnit(lengthInBase);
    }

    private Length addAndConvert(Length length, LengthUnit targetUnit) {
        if (length == null) {
            throw new IllegalArgumentException("Operand length cannot be null");
        }
        double sumInBase = this.convertToBaseUnit() + length.convertToBaseUnit();
        return new Length(targetUnit.convertFromBaseUnit(sumInBase), targetUnit);
    }

    public Length add(Length thatLength) {
        return addAndConvert(thatLength, this.unit);
    }

    public Length add(Length length, LengthUnit targetUnit) {
        return addAndConvert(length, targetUnit);
    }

    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double baseValue = convertToBaseUnit();
        return new Length(targetUnit.convertFromBaseUnit(baseValue), targetUnit);
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