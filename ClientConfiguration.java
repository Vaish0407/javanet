package system.score.vms.config;

import java.net.http.HttpClient;

/**
 * Client configuration class for API client settings.
 */
public class ClientConfiguration {
    
    private final String baseUrl;
    private final String apiKey;
    private final int connectionTimeoutSeconds;
    private final int requestTimeoutSeconds;
    private final String userAgent;
    private final HttpClient.Version httpVersion;
    
    public ClientConfiguration(String baseUrl, String apiKey, int connectionTimeoutSeconds, 
                             int requestTimeoutSeconds, String userAgent) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.connectionTimeoutSeconds = connectionTimeoutSeconds;
        this.requestTimeoutSeconds = requestTimeoutSeconds;
        this.userAgent = userAgent != null ? userAgent : "Enterprise-REST-Client/1.0.0";
        this.httpVersion = HttpClient.Version.HTTP_2;
    }
    
    public String getBaseUrl() {
        return baseUrl;
    }
    
    public String getApiKey() {
        return apiKey;
    }
    
    public int getConnectionTimeoutSeconds() {
        return connectionTimeoutSeconds;
    }
    
    public int getRequestTimeoutSeconds() {
        return requestTimeoutSeconds;
    }
    
    public String getUserAgent() {
        return userAgent;
    }
    
    public HttpClient.Version getHttpVersion() {
        return httpVersion;
    }
}