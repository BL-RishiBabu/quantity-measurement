package org.example;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {
    private final IQuantityMeasurementRepository repository;
    private static final double EPSILON = 0.001;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean compare(QuantityDTO dto1, QuantityDTO dto2) {
        try {
            IMeasurable u1 = IMeasurable.getUnitInstance(dto1.getUnit());
            IMeasurable u2 = IMeasurable.getUnitInstance(dto2.getUnit());
            validateCategory(u1, u2);

            double base1 = u1.convertToBaseUnit(dto1.getValue());
            double base2 = u2.convertToBaseUnit(dto2.getValue());
            boolean match = Math.abs(base1 - base2) < EPSILON;

            repository.save(new QuantityMeasurementEntity(dto1.toString(), dto2.toString(), "COMPARE", String.valueOf(match)));
            return match;
        } catch (Exception e) {
            repository.save(new QuantityMeasurementEntity("COMPARE", e.getMessage()));
            throw new QuantityMeasurementException(e.getMessage(), e);
        }
    }

    @Override
    public QuantityDTO convert(QuantityDTO dto, String targetUnitName) {
        try {
            IMeasurable sourceUnit = IMeasurable.getUnitInstance(dto.getUnit());
            IMeasurable targetUnit = IMeasurable.getUnitInstance(targetUnitName);
            validateCategory(sourceUnit, targetUnit);

            double baseValue = sourceUnit.convertToBaseUnit(dto.getValue());
            double targetValue = targetUnit.convertFromBaseUnit(baseValue);

            repository.save(new QuantityMeasurementEntity(dto.toString(), null, "CONVERT", targetValue + " " + targetUnitName));
            return new QuantityDTO(targetValue, targetUnitName);
        } catch (Exception e) {
            repository.save(new QuantityMeasurementEntity("CONVERT", e.getMessage()));
            throw new QuantityMeasurementException(e.getMessage(), e);
        }
    }

    @Override
    public QuantityDTO add(QuantityDTO dto1, QuantityDTO dto2, String targetUnitName) {
        try {
            IMeasurable u1 = IMeasurable.getUnitInstance(dto1.getUnit());
            IMeasurable u2 = IMeasurable.getUnitInstance(dto2.getUnit());
            IMeasurable target = IMeasurable.getUnitInstance(targetUnitName);

            u1.validateOperationSupport("ADD");
            validateCategory(u1, u2, target);

            double baseSum = u1.convertToBaseUnit(dto1.getValue()) + u2.convertToBaseUnit(dto2.getValue());
            double finalVal = target.convertFromBaseUnit(baseSum);

            repository.save(new QuantityMeasurementEntity(dto1.toString(), dto2.toString(), "ADD", finalVal + " " + targetUnitName));
            return new QuantityDTO(finalVal, targetUnitName);
        } catch (Exception e) {
            repository.save(new QuantityMeasurementEntity("ADD", e.getMessage()));
            throw new QuantityMeasurementException(e.getMessage(), e);
        }
    }

    @Override
    public QuantityDTO subtract(QuantityDTO dto1, QuantityDTO dto2, String targetUnitName) {
        try {
            IMeasurable u1 = IMeasurable.getUnitInstance(dto1.getUnit());
            IMeasurable u2 = IMeasurable.getUnitInstance(dto2.getUnit());
            IMeasurable target = IMeasurable.getUnitInstance(targetUnitName);

            u1.validateOperationSupport("SUBTRACT");
            validateCategory(u1, u2, target);

            double baseDiff = u1.convertToBaseUnit(dto1.getValue()) - u2.convertToBaseUnit(dto2.getValue());
            double finalVal = target.convertFromBaseUnit(baseDiff);

            repository.save(new QuantityMeasurementEntity(dto1.toString(), dto2.toString(), "SUBTRACT", finalVal + " " + targetUnitName));
            return new QuantityDTO(finalVal, targetUnitName);
        } catch (Exception e) {
            repository.save(new QuantityMeasurementEntity("SUBTRACT", e.getMessage()));
            throw new QuantityMeasurementException(e.getMessage(), e);
        }
    }

    @Override
    public double divide(QuantityDTO dto1, QuantityDTO dto2) {
        try {
            IMeasurable u1 = IMeasurable.getUnitInstance(dto1.getUnit());
            IMeasurable u2 = IMeasurable.getUnitInstance(dto2.getUnit());

            u1.validateOperationSupport("DIVIDE");
            validateCategory(u1, u2);

            double baseVal2 = u2.convertToBaseUnit(dto2.getValue());
            if (Math.abs(baseVal2) < EPSILON) throw new ArithmeticException("Division by zero occurs");

            double scalar = u1.convertToBaseUnit(dto1.getValue()) / baseVal2;
            repository.save(new QuantityMeasurementEntity(dto1.toString(), dto2.toString(), "DIVIDE", String.valueOf(scalar)));
            return scalar;
        } catch (Exception e) {
            repository.save(new QuantityMeasurementEntity("DIVIDE", e.getMessage()));
            throw new QuantityMeasurementException(e.getMessage(), e);
        }
    }

    private void validateCategory(IMeasurable... units) {
        Class<?> expected = units[0].getClass();
        for (IMeasurable unit : units) {
            if (unit.getClass() != expected) {
                throw new IllegalArgumentException("Incompatible cross-category mapping matching rejected.");
            }
        }
    }
}