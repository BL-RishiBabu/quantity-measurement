package org.example;

public enum WeightUnit implements IMeasurable {
    GRAM(1.0, "Gram"),
    KILOGRAM(1000.0, "Kilogram"),
    MILLIGRAM(0.001, "Milligram"),
    POUND(453.592, "Pound"),
    TONNE(1000000.0, "Tonne");

    private final double conversionFactor;
    private final String unitName;

    WeightUnit(double conversionFactor, String unitName) {
        this.conversionFactor = conversionFactor;
        this.unitName = unitName;
    }

    @Override
    public double getConversionFactor() {
        return this.conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * this.conversionFactor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / this.conversionFactor;
    }

    @Override
    public String getUnitName() {
        return this.unitName;
    }
}