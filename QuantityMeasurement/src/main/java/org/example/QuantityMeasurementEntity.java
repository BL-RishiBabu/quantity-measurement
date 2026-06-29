package org.example;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {
    private static final long serialVersionUID = 15L;

    private String operand1;
    private String operand2;
    private String operationType;
    private String result;
    private String errorMessage;
    private boolean hasError;

    public QuantityMeasurementEntity() {}

    public QuantityMeasurementEntity(String operand1, String operand2, String operationType, String result) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operationType = operationType;
        this.result = result;
        this.hasError = false;
    }

    public QuantityMeasurementEntity(String operationType, String errorMessage) {
        this.operationType = operationType;
        this.errorMessage = errorMessage;
        this.hasError = true;
    }

    public String getOperand1() { return operand1; }
    public String getOperand2() { return operand2; }
    public String getOperationType() { return operationType; }
    public String getResult() { return result; }
    public String getErrorMessage() { return errorMessage; }
    public boolean isHasError() { return hasError; }

    @Override
    public String toString() {
        if (hasError) return "[AUDIT FAILURE] Op: " + operationType + " | Msg: " + errorMessage;
        return "[AUDIT SUCCESS] Op: " + operationType + " | Args: " + operand1 + ", " + operand2 + " -> Result: " + result;
    }
}