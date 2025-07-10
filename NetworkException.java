package system.score.vms.exception;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Exception class for network-related errors.
 * Thrown when there are connectivity issues, timeouts, or other network problems.
 */
public class NetworkException extends RestClientException {
    
    private final String networkErrorType;
    private final boolean retryable;
    
    /**
     * Constructor with message.
     */
    public NetworkException(String message) {
        super(message, "NETWORK_ERROR");
        this.networkErrorType = "GENERAL";
        this.retryable = true;
    }
    
    /**
     * Constructor with message and cause.
     */
    public NetworkException(String message, Throwable cause) {
        super(message, "NETWORK_ERROR", cause);
        this.networkErrorType = determineErrorType(cause);
        this.retryable = determineRetryability(cause);
    }
    
    /**
     * Constructor with message, network error type, and cause.
     */
    public NetworkException(String message, String networkErrorType, Throwable cause) {
        super(message, "NETWORK_ERROR", cause);
        this.networkErrorType = networkErrorType;
        this.retryable = determineRetryability(cause);
    }
    
    /**
     * Constructor with message, network error type, retryable flag, and cause.
     */
    public NetworkException(String message, String networkErrorType, boolean retryable, Throwable cause) {
        super(message, "NETWORK_ERROR", cause);
        this.networkErrorType = networkErrorType;
        this.retryable = retryable;
    }
    
    /**
     * Gets the specific type of network error.
     * 
     * @return The network error type
     */
    public String getNetworkErrorType() {
        return networkErrorType;
    }
    
    /**
     * Checks if this error is potentially retryable.
     * 
     * @return true if the operation might succeed on retry
     */
    public boolean isRetryable() {
        return retryable;
    }
    
    /**
     * Checks if this is a connection timeout error.
     * 
     * @return true if this is a timeout error
     */
    public boolean isTimeout() {
        return "TIMEOUT".equals(networkErrorType) || 
               getCause() instanceof SocketTimeoutException;
    }
    
    /**
     * Checks if this is a connection refused error.
     * 
     * @return true if connection was refused
     */
    public boolean isConnectionRefused() {
        return "CONNECTION_REFUSED".equals(networkErrorType) ||
               (getCause() instanceof ConnectException && 
                getCause().getMessage() != null && 
                getCause().getMessage().toLowerCase().contains("connection refused"));
    }
    
    /**
     * Checks if this is a host resolution error.
     * 
     * @return true if host could not be resolved
     */
    public boolean isUnknownHost() {
        return "UNKNOWN_HOST".equals(networkErrorType) ||
               getCause() instanceof UnknownHostException;
    }
    
    /**
     * Determines the network error type based on the cause.
     */
    private String determineErrorType(Throwable cause) {
        if (cause instanceof SocketTimeoutException) {
            return "TIMEOUT";
        } else if (cause instanceof UnknownHostException) {
            return "UNKNOWN_HOST";
        } else if (cause instanceof ConnectException) {
            String message = cause.getMessage();
            if (message != null && message.toLowerCase().contains("connection refused")) {
                return "CONNECTION_REFUSED";
            }
            return "CONNECTION_ERROR";
        } else if (cause instanceof IOException) {
            return "IO_ERROR";
        }
        return "GENERAL";
    }
    
    /**
     * Determines if the error is retryable based on the cause.
     */
    private boolean determineRetryability(Throwable cause) {
        if (cause instanceof SocketTimeoutException) {
            return true; // Timeouts are usually retryable
        } else if (cause instanceof UnknownHostException) {
            return false; // DNS resolution failures are not retryable
        } else if (cause instanceof ConnectException) {
            String message = cause.getMessage();
            if (message != null && message.toLowerCase().contains("connection refused")) {
                return true; // Connection refused might be temporary
            }
            return true; // Other connection errors might be temporary
        }
        return true; // Default to retryable for unknown network errors
    }
    
    /**
     * Creates a NetworkException for timeout scenarios.
     */
    public static NetworkException timeout(String message, Throwable cause) {
        return new NetworkException(message, "TIMEOUT", true, cause);
    }
    
    /**
     * Creates a NetworkException for connection refused scenarios.
     */
    public static NetworkException connectionRefused(String message, Throwable cause) {
        return new NetworkException(message, "CONNECTION_REFUSED", true, cause);
    }
    
    /**
     * Creates a NetworkException for unknown host scenarios.
     */
    public static NetworkException unknownHost(String message, Throwable cause) {
        return new NetworkException(message, "UNKNOWN_HOST", false, cause);
    }
    
    /**
     * Creates a NetworkException for general IO errors.
     */
    public static NetworkException ioError(String message, Throwable cause) {
        return new NetworkException(message, "IO_ERROR", true, cause);
    }
    
    @Override
    public String toString() {
        return "NetworkException{" +
                "networkErrorType='" + networkErrorType + '\'' +
                ", retryable=" + retryable +
                ", errorCode='" + getErrorCode() + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}
