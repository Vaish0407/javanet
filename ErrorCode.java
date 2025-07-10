package system.score.vms.exception;

public enum ErrorCode {
    VLITE_ERR_UNKNOWN_ERROR(99999, "An unknown error has occurred."),
    VLITE_ERR_TIMEOUT(99996, "Action Timed out"),
    VLITE_ERR_INVALID_INPUT(99997, "Invalid Input"),
    VLITE_ERR_UNEXPECTED_ERROR(99998, "Unexpected Error"),
    VLITE_ERR_INVALID_IP_ADDRESS(99995, "Invalid IP Address"),
    VLITE_ERR_NOT_ZERO(99994, "Should be greater than zero"),
    VLITE_ERR_NULL_VALUE(99993, "Value cannot be null."),
    VLITE_ERR_STRING_TOO_SHORT(99992, "String is too short."),
    VLITE_ERR_STRING_TOO_LONG(99991, "String is too long."),
    VLITE_ERR_SUCCESS(-1, "Successful"),
    VLITE_ERR_INVALID_FILE(100, "The filename specified is invalid"),
    VLITE_ERR_NOT_HUBOS(300, "The file specified is not a valid firmware file for a hub"),
    VLITE_ERR_NOT_DAUOS(301, "The file specified is not a valid firmware file for a DAU"),
    VLITE_ERR_DLL_VERSION(9000, "The DLL version is mismatched with the software"),
    VLITE_ERR_DLL_UNLOAD(9100, "The DLL could not be unloaded"),
    VLITE_ERR_SOCKET_EXCEPTION(35003, "The message received by the Hub was the incorrect length");


    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

    public static ErrorCode getErrMsgByCode(int code) {
        for (ErrorCode errorCode : values()) {
            if (errorCode.getCode() == code) {
                return errorCode;
            }
        }
        return VLITE_ERR_UNKNOWN_ERROR;
    }


}
