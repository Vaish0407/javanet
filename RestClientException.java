package system.score.vms.exception;

/**
 * Base exception class for all REST client related exceptions.
 * Provides a hierarchy for different types of client errors.
 */
public class RestClientException extends Exception {
    
    private final String errorCode;
    
    /**
     * Default constructor.
     */
    public RestClientException() {
        super();
        this.errorCode = "REST_CLIENT_ERROR";
    }
    
    /**
     * Constructor with message.
     */
    public RestClientException(String message) {
        super(message);
        this.errorCode = "REST_CLIENT_ERROR";
    }
    
    /**
     * Constructor with message and cause.
     */
    public RestClientException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "REST_CLIENT_ERROR";
    }
    
    /**
     * Constructor with message and error code.
     */
    public RestClientException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    
    /**
     * Constructor with message, error code, and cause.
     */
    public RestClientException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
    
    /**
     * Gets the error code associated with this exception.
     * 
     * @return The error code
     */
    public String getErrorCode() {
        return errorCode;
    }
    
    @Override
    public String toString() {
        return "RestClientException{" +
                "errorCode='" + errorCode + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}
