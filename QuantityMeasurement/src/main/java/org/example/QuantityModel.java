package org.example;

public class QuantityModel<U extends IMeasurable> {
    private final double value;
    private final U unit;

    public QuantityModel(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() { return value; }
    public U getUnit() { return unit; }

    public double getBaseValue() {
        return unit.convertToBaseUnit(value);
    }
}