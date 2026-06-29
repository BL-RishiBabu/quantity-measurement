package org.example;

public interface IQuantityMeasurementService {
    boolean compare(QuantityDTO dto1, QuantityDTO unit2);
    QuantityDTO convert(QuantityDTO dto, String targetUnit);
    QuantityDTO add(QuantityDTO dto1, QuantityDTO dto2, String targetUnit);
    QuantityDTO subtract(QuantityDTO dto1, QuantityDTO dto2, String targetUnit);
    double divide(QuantityDTO dto1, QuantityDTO dto2);
}