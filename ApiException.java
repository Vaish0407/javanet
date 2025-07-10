package system.score.vms.exception;

/**
 * Exception class for API-specific errors.
 * Includes HTTP status code and response body for detailed error handling.
 */
public class ApiException extends RestClientException {
    
    private final int statusCode;
    private final String responseBody;
    
    /**
     * Constructor with message and status code.
     */
    public ApiException(String message, int statusCode) {
        super(message, "API_ERROR");
        this.statusCode = statusCode;
        this.responseBody = null;
    }
    
    /**
     * Constructor with message, status code, and response body.
     */
    public ApiException(String message, int statusCode, String responseBody) {
        super(message, "API_ERROR");
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }
    
    /**
     * Constructor with message, status code, response body, and cause.
     */
    public ApiException(String message, int statusCode, String responseBody, Throwable cause) {
        super(message, "API_ERROR", cause);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }
    
    /**
     * Gets the HTTP status code.
     * 
     * @return The HTTP status code
     */
    public int getStatusCode() {
        return statusCode;
    }
    
    /**
     * Gets the response body from the failed request.
     * 
     * @return The response body, or null if not available
     */
    public String getResponseBody() {
        return responseBody;
    }
    
    /**
     * Checks if this is a client error (4xx status code).
     * 
     * @return true if status code is in 400-499 range
     */
    public boolean isClientError() {
        return statusCode >= 400 && statusCode < 500;
    }
    
    /**
     * Checks if this is a server error (5xx status code).
     * 
     * @return true if status code is in 500-599 range
     */
    public boolean isServerError() {
        return statusCode >= 500 && statusCode < 600;
    }
    
    /**
     * Checks if this is a not found error (404).
     * 
     * @return true if status code is 404
     */
    public boolean isNotFound() {
        return statusCode == 404;
    }
    
    /**
     * Checks if this is an unauthorized error (401).
     * 
     * @return true if status code is 401
     */
    public boolean isUnauthorized() {
        return statusCode == 401;
    }
    
    /**
     * Checks if this is a forbidden error (403).
     * 
     * @return true if status code is 403
     */
    public boolean isForbidden() {
        return statusCode == 403;
    }
    
    @Override
    public String toString() {
        return "ApiException{" +
                "statusCode=" + statusCode +
                ", responseBody='" + responseBody + '\'' +
                ", errorCode='" + getErrorCode() + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}
