package org.example;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;
    private static final double EPSILON = 0.0001;

    public Quantity(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (Double.isNaN(value) || Double.isInfinite(value)) throw new IllegalArgumentException("Value must be a finite number");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double baseValue = this.unit.convertToBaseUnit(this.value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        return new Quantity<>(convertedValue, targetUnit);
    }

    public Quantity<U> add(Quantity<U> that) {
        return this.add(that, this.unit);
    }

    public Quantity<U> add(Quantity<U> that, U targetUnit) {
        if (that == null || targetUnit == null) throw new IllegalArgumentException("Arguments cannot be null");
        double baseSum = this.unit.convertToBaseUnit(this.value) + that.unit.convertToBaseUnit(that.value);
        double finalValue = targetUnit.convertFromBaseUnit(baseSum);
        return new Quantity<>(finalValue, targetUnit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quantity<?> that = (Quantity<?>) o;

        if (this.unit.getClass() != that.unit.getClass()) return false;

        double baseValue1 = this.unit.convertToBaseUnit(this.value);
        double baseValue2 = ((IMeasurable) that.unit).convertToBaseUnit(that.value);

        return Math.abs(baseValue1 - baseValue2) < EPSILON;
    }

    @Override
    public int hashCode() {
        double baseValue = this.unit.convertToBaseUnit(this.value);
        long bits = Double.doubleToLongBits(Math.round(baseValue * 10000.0) / 10000.0);
        return unit.getClass().hashCode() + Long.hashCode(bits);
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit.getUnitName());
    }
}