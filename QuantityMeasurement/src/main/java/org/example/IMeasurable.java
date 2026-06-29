package org.example;

public interface IMeasurable {
    String getUnitName();
    double getConversionFactor();
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);

    default boolean supportsArithmetic() {
        return true;
    }

    default void validateOperationSupport(String operation) {

    }

    static IMeasurable getUnitInstance(String unitName) {
        try { return LengthUnit.valueOf(unitName); } catch (Exception ignored) {}
        try { return WeightUnit.valueOf(unitName); } catch (Exception ignored) {}
        try { return VolumeUnit.valueOf(unitName); } catch (Exception ignored) {}
        try { return TemperatureUnit.valueOf(unitName); } catch (Exception ignored) {}
        throw new IllegalArgumentException("Unknown unit conversion format: " + unitName);
    }
}