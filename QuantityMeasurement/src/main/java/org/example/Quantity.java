package org.example;
import java.util.Objects;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Value must be a finite number");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return this.value;
    }

    public U getUnit() {
        return this.unit;
    }

    public double convertTo(U targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double baseValue = this.unit.convertToBaseUnit(this.value);
        return targetUnit.convertFromBaseUnit(baseValue);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        if (other == null) throw new IllegalArgumentException("Operand quantity cannot be null");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double sumInBase = this.unit.convertToBaseUnit(this.value) + other.unit.convertToBaseUnit(other.value);
        return new Quantity<>(targetUnit.convertFromBaseUnit(sumInBase), targetUnit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantity<?> that = (Quantity<?>) o;

        if (this.unit.getClass() != that.unit.getClass()) return false;

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double thatBase = that.unit.convertToBaseUnit((Double) that.value);
        return Math.abs(thisBase - thatBase) < 0.01;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.unit.convertToBaseUnit(this.value), this.unit.getClass());
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}