package org.example;
import java.util.Objects;

public class Weight {
    private final double value;
    private final WeightUnit unit;

    public Weight(double value, WeightUnit unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Value must be a finite number");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return this.value;
    }

    public WeightUnit getUnit() {
        return this.unit;
    }

    private double convertToBaseUnit() {
        return this.unit.convertToBaseUnit(this.value);
    }

    private Weight addAndConvert(Weight weight, WeightUnit targetUnit) {
        if (weight == null) throw new IllegalArgumentException("Operand weight cannot be null");
        double sumInBase = this.convertToBaseUnit() + weight.convertToBaseUnit();
        return new Weight(targetUnit.convertFromBaseUnit(sumInBase), targetUnit);
    }

    public Weight add(Weight thatWeight) {
        return addAndConvert(thatWeight, this.unit);
    }

    public Weight add(Weight weight, WeightUnit targetUnit) {
        return addAndConvert(weight, targetUnit);
    }

    public Weight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double baseValue = convertToBaseUnit();
        return new Weight(targetUnit.convertFromBaseUnit(baseValue), targetUnit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weight weight = (Weight) o;
        return Math.abs(this.convertToBaseUnit() - weight.convertToBaseUnit()) < 0.01;
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