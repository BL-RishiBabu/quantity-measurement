package org.example;

public class Length {
    private final double value;
    private final LengthUnit unit;

    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    public Length(double value, LengthUnit unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (Double.isNaN(value) || Double.isInfinite(value)) throw new IllegalArgumentException("Value must be a finite number");
        this.value = value;
        this.unit = unit;
    }

    private double convertToBaseUnit() {
        double rawInches = this.value * this.unit.getConversionFactor();
        return Math.round(rawInches * 100.0) / 100.0;
    }

    private boolean compare(Length thatLength) {
        if (thatLength == null) return false;
        return Double.compare(this.convertToBaseUnit(), thatLength.convertToBaseUnit()) == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Length other = (Length) o;
        return this.compare(other);
    }

    @Override
    public int hashCode() {
        return Double.hashCode(convertToBaseUnit());
    }

    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double inches = this.value * this.unit.getConversionFactor();
        double rawConverted = inches / targetUnit.getConversionFactor();
        double roundedValue = Math.round(rawConverted * 100.0) / 100.0;
        return new Length(roundedValue, targetUnit);
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", this.value, this.unit.name());
    }

    static void main() {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        System.out.println("Are lengths equal? " + length1.equals(length2));

        Length length3 = new Length(1.0, LengthUnit.YARDS);
        Length length4 = new Length(36.0, LengthUnit.INCHES);
        System.out.println("Are lengths equal? " + length3.equals(length4));

        Length length5 = new Length(100.0, LengthUnit.CENTIMETERS);
        Length length6 = new Length(39.3701, LengthUnit.INCHES);
        System.out.println("Are lengths equal? " + length5.equals(length6));
    }
}