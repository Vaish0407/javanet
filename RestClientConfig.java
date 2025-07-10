package system.score.vms.config;

import java.time.Duration;

/**
 * Configuration class for REST client settings.
 * Manages all configuration parameters including timeouts, URLs, and authentication.
 */
public class RestClientConfig {
    
    private final String baseUrl;
    private final String apiKey;
    private final Duration connectTimeout;
    private final Duration requestTimeout;
    private final int maxRetries;
    private final boolean enableLogging;
    
    /**
     * Default constructor that loads configuration from environment variables
     * with sensible defaults for enterprise use.
     */
    public RestClientConfig() {
        this.baseUrl = getEnvOrDefault("API_BASE_URL", "http://localhost:5000");
        this.apiKey = getEnvOrDefault("API_KEY", "default-api-key");
        //Changing the timeout duration to milli seconds
        //this.connectTimeout = Duration.ofSeconds(getLongEnvOrDefault("CONNECT_TIMEOUT", 30));
        //this.requestTimeout = Duration.ofSeconds(getLongEnvOrDefault("REQUEST_TIMEOUT", 60));
        this.connectTimeout = Duration.ofMillis(getLongEnvOrDefault("CONNECT_TIMEOUT", 30));
        this.requestTimeout = Duration.ofMillis(getLongEnvOrDefault("REQUEST_TIMEOUT", 60));
        this.maxRetries = getIntEnvOrDefault("MAX_RETRIES", 3);
        this.enableLogging = getBooleanEnvOrDefault("ENABLE_REQUEST_LOGGING", true);
    }
    
    /**
     * Constructor for custom configuration.
     */
    public RestClientConfig(String baseUrl, String apiKey, Duration connectTimeout, 
                           Duration requestTimeout, int maxRetries, boolean enableLogging) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.connectTimeout = connectTimeout;
        this.requestTimeout = requestTimeout;
        this.maxRetries = maxRetries;
        this.enableLogging = enableLogging;
    }
    
    public String getBaseUrl() {
        return baseUrl;
    }
    
    public String getApiKey() {
        return apiKey;
    }
    
    public Duration getConnectTimeout() {
        return connectTimeout;
    }
    
    public Duration getRequestTimeout() {
        return requestTimeout;
    }
    
    public int getMaxRetries() {
        return maxRetries;
    }
    
    public boolean isLoggingEnabled() {
        return enableLogging;
    }
    
    /**
     * Utility methods for environment variable handling
     */
    private String getEnvOrDefault(String key, String defaultValue) {
        String value = System.getenv(key);
        if (value == null || value.trim().isEmpty()) {
            value = System.getProperty(key, defaultValue);
        }
        return value;
    }
    
    private long getLongEnvOrDefault(String key, long defaultValue) {
        try {
            String value = getEnvOrDefault(key, String.valueOf(defaultValue));
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    private int getIntEnvOrDefault(String key, int defaultValue) {
        try {
            String value = getEnvOrDefault(key, String.valueOf(defaultValue));
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    private boolean getBooleanEnvOrDefault(String key, boolean defaultValue) {
        String value = getEnvOrDefault(key, String.valueOf(defaultValue));
        return Boolean.parseBoolean(value);
    }
    
    @Override
    public String toString() {
        return "RestClientConfig{" +
                "baseUrl='" + baseUrl + '\'' +
                ", connectTimeout=" + connectTimeout +
                ", requestTimeout=" + requestTimeout +
                ", maxRetries=" + maxRetries +
                ", enableLogging=" + enableLogging +
                '}';
    }
}
