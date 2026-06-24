package org.example;

public enum LengthUnit {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return this.conversionFactor;
    }

    public double convertToBaseUnit(double value) {
        double converted = value * this.conversionFactor;
        return Math.round(converted * 100.0) / 100.0;
    }

    public double convertFromBaseUnit(double baseValue) {
        double converted = baseValue / this.conversionFactor;
        return Math.round(converted * 100.0) / 100.0;
    }
}