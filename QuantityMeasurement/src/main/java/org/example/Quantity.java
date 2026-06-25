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

    public Quantity<U> add(Quantity<U> other) {
        return this.add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateIncompatibleOperations(other, targetUnit);
        double baseSum = this.unit.convertToBaseUnit(this.value) + other.unit.convertToBaseUnit(other.value);
        double roundedValue = Math.round((targetUnit.convertFromBaseUnit(baseSum)) * 100.0) / 100.0;
        return new Quantity<>(roundedValue, targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return this.subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateIncompatibleOperations(other, targetUnit);
        double baseDifference = this.unit.convertToBaseUnit(this.value) - other.unit.convertToBaseUnit(other.value);
        double roundedValue = Math.round((targetUnit.convertFromBaseUnit(baseDifference)) * 100.0) / 100.0;
        return new Quantity<>(roundedValue, targetUnit);
    }

    public double divide(Quantity<U> other) {
        if (other == null) throw new IllegalArgumentException("Divisor quantity cannot be null");
        if (this.unit.getClass() != other.unit.getClass()) throw new IllegalArgumentException("Cross-category division is strictly prohibited");
        double divisorBase = other.unit.convertToBaseUnit(other.value);
        if (Math.abs(divisorBase) < EPSILON) throw new ArithmeticException("Division by zero error caught on base unit conversion");
        return this.unit.convertToBaseUnit(this.value) / divisorBase;
    }

    private void validateIncompatibleOperations(Quantity<U> other, U targetUnit) {
        if (other == null) throw new IllegalArgumentException("Quantity operand cannot be null");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit parameter cannot be null");
        if (this.unit.getClass() != other.unit.getClass() || this.unit.getClass() != targetUnit.getClass())
            throw new IllegalArgumentException("Cross-category metrics matching mismatch");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Quantity<?> that = (Quantity<?>) obj;
        if (this.unit.getClass() != that.unit.getClass()) return false;

        double baseValue1 = this.unit.convertToBaseUnit(this.value);
        double baseValue2 = ((IMeasurable) that.unit).convertToBaseUnit(that.value);
        return Math.abs(baseValue1 - baseValue2) < EPSILON;
    }

    @Override
    public int hashCode() {
        double baseValue = this.unit.convertToBaseUnit(this.value);
        long bits = Double.doubleToLongBits(Math.round(baseValue * 100.0) / 100.0);
        return unit.getClass().hashCode() + Long.hashCode(bits);
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit.getUnitName());
    }
}