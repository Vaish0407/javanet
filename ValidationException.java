package system.score.vms.exception;

public class ValidationException extends RuntimeException{
    private final ErrorCode errorCode;
    private final String fieldName;

    public ValidationException(ErrorCode errorCode, String fieldName) {
        super(fieldName + ": " + errorCode.getMessage());
        this.errorCode = errorCode;
        this.fieldName = fieldName;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getFieldName() {
        return fieldName;
    }

    @Override
    public String toString() {
        return "Validation failed for field '" + fieldName + "' - " + errorCode.toString();
    }
}
