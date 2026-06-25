package org.example;

import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;
    private static final double EPSILON = 0.0001;

    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Value must be a finite number");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    public <TargetUnit extends IMeasurable> double convertTo(TargetUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double baseValue = this.unit.convertToBaseUnit(this.value);
        return Math.round((baseValue / targetUnit.getConversionFactor()) * 100.0) / 100.0;
    }

    // --- PUBLIC ARITHMETIC INTERFACES ---

    public Quantity<U> add(Quantity<U> other) {
        return this.add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double baseResult = performArithmetic(other, targetUnit, ArithmeticOperation.ADD);
        double roundedValue = Math.round(targetUnit.convertFromBaseUnit(baseResult) * 100.0) / 100.0;
        return new Quantity<>(roundedValue, targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return this.subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double baseResult = performArithmetic(other, targetUnit, ArithmeticOperation.SUBTRACT);
        double roundedValue = Math.round(targetUnit.convertFromBaseUnit(baseResult) * 100.0) / 100.0;
        return new Quantity<>(roundedValue, targetUnit);
    }

    public double divide(Quantity<U> other) {
        validateArithmeticOperands(other, null, false);
        return performArithmetic(other, null, ArithmeticOperation.DIVIDE);
    }

    // --- STEP 2 & 3: CENTRALIZED PRIVATE PROCESSING LOGIC ---

    private enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (Math.abs(b) < EPSILON) {
                throw new ArithmeticException("Division by zero occurs");
            }
            return a / b;
        });

        private final DoubleBinaryOperator operator;

        ArithmeticOperation(DoubleBinaryOperator operator) {
            this.operator = operator;
        }

        public double compute(double left, double right) {
            return this.operator.applyAsDouble(left, right);
        }
    }

    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetUnitRequired) {
        if (other == null) {
            throw new IllegalArgumentException("Operand quantity cannot be null");
        }
        if (this.unit.getClass() != other.unit.getClass()) {
            throw new IllegalArgumentException("Incompatible measurement categories");
        }
        if (targetUnitRequired) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            if (this.unit.getClass() != targetUnit.getClass()) {
                throw new IllegalArgumentException("Incompatible target unit category");
            }
        }
    }

    private double performArithmetic(Quantity<U> other, U targetUnit, ArithmeticOperation operation) {
        double baseValue1 = this.unit.convertToBaseUnit(this.value);
        double baseValue2 = other.unit.convertToBaseUnit(other.value);
        return operation.compute(baseValue1, baseValue2);
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