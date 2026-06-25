package org.example;

import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {
    CELSIUS(c -> c, c -> c),
    FAHRENHEIT(f -> (f - 32.0) * 5.0 / 9.0, c -> (c * 9.0 / 5.0) + 32.0);

    private final Function<Double, Double> toCelsius;
    private final Function<Double, Double> fromCelsius;

    TemperatureUnit(Function<Double, Double> toCelsius, Function<Double, Double> fromCelsius) {
        this.toCelsius = toCelsius;
        this.fromCelsius = fromCelsius;
    }

    @Override
    public String getUnitName() {
        return this.name();
    }

    @Override
    public double getConversionFactor() {
        return 1.0;
    }

    @Override
    public double convertToBaseUnit(double value) {
        double rawBase = toCelsius.apply(value);
        return Math.round(rawBase * 100.0) / 100.0;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        double rawTarget = fromCelsius.apply(baseValue);
        return Math.round(rawTarget * 100.0) / 100.0;
    }

    private static final SupportsArithmetic supportsArithmeticLambda = () -> false;

    @Override
    public boolean supportsArithmetic() {
        return supportsArithmeticLambda.isSupported();
    }

    @Override
    public void validateOperationSupport(String operation) {
        if (!supportsArithmetic()) throw new UnsupportedOperationException(this.name() + " does not support " + operation + " operations.");
    }

    public double convertTo(double value, TemperatureUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double baseCelsius = this.toCelsius.apply(value);
        return Math.round(targetUnit.fromCelsius.apply(baseCelsius) * 100.0) / 100.0;
    }
}