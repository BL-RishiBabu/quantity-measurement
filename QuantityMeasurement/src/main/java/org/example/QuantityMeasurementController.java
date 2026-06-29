package org.example;

public class QuantityMeasurementController {
    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public boolean performComparison(QuantityDTO d1, QuantityDTO d2) {
        return service.compare(d1, d2);
    }

    public QuantityDTO performConversion(QuantityDTO d, String targetUnit) {
        return service.convert(d, targetUnit);
    }

    public QuantityDTO performAddition(QuantityDTO d1, QuantityDTO d2, String targetUnit) {
        return service.add(d1, d2, targetUnit);
    }

    public QuantityDTO performSubtraction(QuantityDTO d1, QuantityDTO d2, String targetUnit) {
        return service.subtract(d1, d2, targetUnit);
    }

    public double performDivision(QuantityDTO d1, QuantityDTO d2) {
        return service.divide(d1, d2);
    }
}