package system.score.vms.client;

import system.score.vms.config.ClientConfiguration;
import system.score.vms.exception.ApiException;
import system.score.vms.exception.RestClientException;
import system.score.vms.utils.JsonUtil;
import system.score.vms.utils.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;

/**
 * Generic API client for REST operations using Java HTTP Client
 */
public class ApiClient {
    
    private static final Logger logger = LoggerFactory.getLogger(ApiClient.class);
    
    private final HttpClient httpClient;
    private final ClientConfiguration config;
    private final JsonUtil jsonUtil;
    private final ResponseHandler responseHandler;
    
    public ApiClient(ClientConfiguration config) {
        this.config = config;
        this.httpClient = HttpClientFactory.createHttpClient(config);
        this.jsonUtil = new JsonUtil();
        this.responseHandler = new ResponseHandler(jsonUtil);
    }
    
    /**
     * Perform GET request
     */
    public <T> T get(String endpoint, Class<T> responseType) throws RestClientException {
        return get(endpoint, responseType, null);
    }
    
    /**
     * Perform GET request with headers
     */
    public <T> T get(String endpoint, Class<T> responseType, Map<String, String> headers) throws RestClientException {
        try {
            String url = buildUrl(endpoint);
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(config.getRequestTimeoutSeconds()))
                    .GET();
            
            addHeaders(requestBuilder, headers);
            HttpRequest request = requestBuilder.build();
            
            logger.debug("Sending GET request to: {}", url);
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            
            return responseHandler.handleResponse(response, responseType);
            
        } catch (Exception e) {
            logger.error("GET request failed for endpoint: {}", endpoint, e);
            throw new RestClientException("GET request failed", e);
        }
    }
    
    /**
     * Perform POST request
     */
    public <T> T post(String endpoint, Object requestBody, Class<T> responseType) throws RestClientException {
        return post(endpoint, requestBody, responseType, null);
    }
    
    /**
     * Perform POST request with headers
     */
    public <T> T post(String endpoint, Object requestBody, Class<T> responseType, Map<String, String> headers) throws RestClientException {
        try {
            String url = buildUrl(endpoint);
            String jsonBody = jsonUtil.toJson(requestBody);
            
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(config.getRequestTimeoutSeconds()))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody));
            
            addHeaders(requestBuilder, headers);
            HttpRequest request = requestBuilder.build();
            
            logger.debug("Sending POST request to: {} with body: {}", url, jsonBody);
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            
            return responseHandler.handleResponse(response, responseType);
            
        } catch (Exception e) {
            logger.error("POST request failed for endpoint: {}", endpoint, e);
            throw new RestClientException("POST request failed", e);
        }
    }
    
    /**
     * Perform PUT request
     */
    public <T> T put(String endpoint, Object requestBody, Class<T> responseType) throws RestClientException {
        return put(endpoint, requestBody, responseType, null);
    }
    
    /**
     * Perform PUT request with headers
     */
    public <T> T put(String endpoint, Object requestBody, Class<T> responseType, Map<String, String> headers) throws RestClientException {
        try {
            String url = buildUrl(endpoint);
            String jsonBody = jsonUtil.toJson(requestBody);
            
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(config.getRequestTimeoutSeconds()))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(jsonBody));
            
            addHeaders(requestBuilder, headers);
            HttpRequest request = requestBuilder.build();
            
            logger.debug("Sending PUT request to: {} with body: {}", url, jsonBody);
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            
            return responseHandler.handleResponse(response, responseType);
            
        } catch (Exception e) {
            logger.error("PUT request failed for endpoint: {}", endpoint, e);
            throw new RestClientException("PUT request failed", e);
        }
    }
    
    /**
     * Perform DELETE request
     */
    public void delete(String endpoint) throws RestClientException {
        delete(endpoint, null);
    }
    
    /**
     * Perform DELETE request with headers
     */
    public void delete(String endpoint, Map<String, String> headers) throws RestClientException {
        try {
            String url = buildUrl(endpoint);
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(config.getRequestTimeoutSeconds()))
                    .DELETE();
            
            addHeaders(requestBuilder, headers);
            HttpRequest request = requestBuilder.build();
            
            logger.debug("Sending DELETE request to: {}", url);
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() >= 400) {
                throw new ApiException("DELETE request failed with status: " + response.statusCode(), 
                                     response.statusCode(), response.body());
            }
            
        } catch (Exception e) {
            logger.error("DELETE request failed for endpoint: {}", endpoint, e);
            throw new RestClientException("DELETE request failed", e);
        }
    }
    
    private String buildUrl(String endpoint) {
        String baseUrl = config.getBaseUrl();
        if (baseUrl.endsWith("/") && endpoint.startsWith("/")) {
            return baseUrl + endpoint.substring(1);
        } else if (!baseUrl.endsWith("/") && !endpoint.startsWith("/")) {
            return baseUrl + "/" + endpoint;
        }
        return baseUrl + endpoint;
    }
    
    private void addHeaders(HttpRequest.Builder requestBuilder, Map<String, String> headers) {
        // Add API key if configured
        if (config.getApiKey() != null && !config.getApiKey().isEmpty()) {
            requestBuilder.header("Authorization", "Bearer " + config.getApiKey());
        }
        
        // Add custom headers
        if (headers != null) {
            headers.forEach(requestBuilder::header);
        }
        
        // Add default headers
        requestBuilder.header("User-Agent", config.getUserAgent());
        requestBuilder.header("Accept", "application/json");
    }
}
