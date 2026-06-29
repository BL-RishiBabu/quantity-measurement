package org.example;

public class QuantityDTO {
    private double value;
    private String unit;

    public QuantityDTO() {}

    public QuantityDTO(double value, String unit) {
        this.value = value;
        this.unit = unit.toUpperCase();
    }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit.toUpperCase(); }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}