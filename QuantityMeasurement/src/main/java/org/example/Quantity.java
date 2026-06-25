package org.example;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;
    private static final double EPSILON = 0.001;

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

    @SuppressWarnings("unchecked")
    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        if (this.unit instanceof TemperatureUnit && targetUnit instanceof TemperatureUnit) {
            double converted = ((TemperatureUnit) this.unit).convertTo(this.value, (TemperatureUnit) targetUnit);
            return new Quantity<>(converted, targetUnit);
        }
        double baseValue = this.unit.convertToBaseUnit(this.value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        return new Quantity<>(convertedValue, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        return this.add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        this.unit.validateOperationSupport("ADD");
        validateCategory(other, targetUnit);
        double sum = this.unit.convertToBaseUnit(this.value) + other.unit.convertToBaseUnit(other.value);
        return new Quantity<>(targetUnit.convertFromBaseUnit(sum), targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return this.subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        this.unit.validateOperationSupport("SUBTRACT");
        validateCategory(other, targetUnit);
        double diff = this.unit.convertToBaseUnit(this.value) - other.unit.convertToBaseUnit(other.value);
        return new Quantity<>(targetUnit.convertFromBaseUnit(diff), targetUnit);
    }

    public double divide(Quantity<U> other) {
        this.unit.validateOperationSupport("DIVIDE");
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");
        if (this.unit.getClass() != other.unit.getClass()) throw new IllegalArgumentException("Incompatible types");
        return this.unit.convertToBaseUnit(this.value) / other.unit.convertToBaseUnit(other.value);
    }

    private void validateCategory(Quantity<U> other, U target) {
        if (other == null || target == null) throw new IllegalArgumentException("Null arguments provided");
        if (this.unit.getClass() != other.unit.getClass() || this.unit.getClass() != target.getClass()) {
            throw new IllegalArgumentException("Incompatible measurement categories");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Quantity<?> that = (Quantity<?>) obj;
        if (this.unit.getClass() != that.unit.getClass()) return false;

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = ((IMeasurable) that.unit).convertToBaseUnit(that.value);
        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(this.unit.convertToBaseUnit(this.value));
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
}