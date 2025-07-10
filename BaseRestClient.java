package system.score.vms.client;

import system.score.vms.config.HttpClientConfig;
import system.score.vms.config.RestClientConfig;
import system.score.vms.exception.ApiException;
import system.score.vms.exception.RestClientException;
import system.score.vms.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

/**
 * Base REST client providing common functionality for all API clients.
 * Handles HTTP operations, error handling, and request/response processing.
 */
public abstract class BaseRestClient {
    
    private static final Logger logger = LoggerFactory.getLogger(BaseRestClient.class);
    
    protected final RestClientConfig config;
    protected final HttpClient httpClient;
    protected final JsonUtil jsonUtil;
    
    /**
     * Constructor initializing the base client with configuration.
     * 
     * @param config REST client configuration
     */
    protected BaseRestClient(RestClientConfig config) {
        this.config = config;
        this.httpClient = HttpClientConfig.createHttpClient(config);
        this.jsonUtil = new JsonUtil();
    }
    
    /**
     * Executes a GET request and returns the response as a string.
     * 
     * @param endpoint The API endpoint (relative to base URL)
     * @param headers Additional headers to include
     * @return Response body as string
     * @throws RestClientException If the request fails
     */
    protected String get(String endpoint, Map<String, String> headers) throws RestClientException {
        HttpRequest request = buildRequest("GET", endpoint, null, headers);
        return executeRequest(request);
    }
    
    /**
     * Executes a GET request and deserializes the response to the specified type.
     * 
     * @param endpoint The API endpoint
     * @param responseType Class type for deserialization
     * @param headers Additional headers
     * @param <T> Response type
     * @return Deserialized response object
     * @throws RestClientException If the request fails
     */
    protected <T> T get(String endpoint, Class<T> responseType, Map<String, String> headers) 
            throws RestClientException {
        String response = get(endpoint, headers);
        return jsonUtil.fromJson(response, responseType);
    }
    
    /**
     * Executes a POST request with JSON body.
     * 
     * @param endpoint The API endpoint
     * @param requestBody Object to serialize as JSON body
     * @param headers Additional headers
     * @return Response body as string
     * @throws RestClientException If the request fails
     */
    protected String post(String endpoint, Object requestBody, Map<String, String> headers) 
            throws RestClientException {
        String jsonBody = jsonUtil.toJson(requestBody);
        HttpRequest request = buildRequest("POST", endpoint, jsonBody, headers);
        return executeRequest(request);
    }
    
    /**
     * Executes a POST request and deserializes the response.
     * 
     * @param endpoint The API endpoint
     * @param requestBody Object to serialize as JSON body
     * @param responseType Class type for deserialization
     * @param headers Additional headers
     * @param <T> Response type
     * @return Deserialized response object
     * @throws RestClientException If the request fails
     */
    protected <T> T post(String endpoint, Object requestBody, Class<T> responseType, 
                        Map<String, String> headers) throws RestClientException {
        String response = post(endpoint, requestBody, headers);
        return jsonUtil.fromJson(response, responseType);
    }
    
    /**
     * Executes a PUT request with JSON body.
     * 
     * @param endpoint The API endpoint
     * @param requestBody Object to serialize as JSON body
     * @param headers Additional headers
     * @return Response body as string
     * @throws RestClientException If the request fails
     */
    protected String put(String endpoint, Object requestBody, Map<String, String> headers) 
            throws RestClientException {
        String jsonBody = jsonUtil.toJson(requestBody);
        HttpRequest request = buildRequest("PUT", endpoint, jsonBody, headers);
        return executeRequest(request);
    }
    
    /**
     * Executes a PUT request and deserializes the response.
     */
    protected <T> T put(String endpoint, Object requestBody, Class<T> responseType, 
                       Map<String, String> headers) throws RestClientException {
        String response = put(endpoint, requestBody, headers);
        return jsonUtil.fromJson(response, responseType);
    }
    
    /**
     * Executes a DELETE request.
     * 
     * @param endpoint The API endpoint
     * @param headers Additional headers
     * @return Response body as string
     * @throws RestClientException If the request fails
     */
    protected String delete(String endpoint, Map<String, String> headers) throws RestClientException {
        HttpRequest request = buildRequest("DELETE", endpoint, null, headers);
        return executeRequest(request);
    }
    
    /**
     * Builds an HTTP request with common headers and configuration.
     */
    private HttpRequest buildRequest(String method, String endpoint, String body, 
                                   Map<String, String> headers) {
        URI uri = URI.create(config.getBaseUrl() + endpoint);
        
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(uri)
                .timeout(config.getRequestTimeout())
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + config.getApiKey());
        
        // Add custom headers
        if (headers != null) {
            headers.forEach(builder::header);
        }
        
        // Set HTTP method and body
        switch (method.toUpperCase()) {
            case "GET":
                builder.GET();
                break;
            case "POST":
                builder.POST(HttpRequest.BodyPublishers.ofString(body != null ? body : ""));
                break;
            case "PUT":
                builder.PUT(HttpRequest.BodyPublishers.ofString(body != null ? body : ""));
                break;
            case "DELETE":
                builder.DELETE();
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }
        
        return builder.build();
    }
    
    /**
     * Executes the HTTP request with retry logic and error handling.
     */
    private String executeRequest(HttpRequest request) throws RestClientException {
        Exception lastException = null;
        
        for (int attempt = 1; attempt <= config.getMaxRetries(); attempt++) {
            try {
                if (config.isLoggingEnabled()) {
                    logger.debug("Executing {} request to {}, attempt {}/{}", 
                            request.method(), request.uri(), attempt, config.getMaxRetries());
                }
                
                HttpResponse<String> response = httpClient.send(request, 
                        HttpResponse.BodyHandlers.ofString());
                
                if (config.isLoggingEnabled()) {
                    logger.debug("Received response with status code: {}", response.statusCode());
                }
                
                // Check for successful status codes (2xx)
                if (response.statusCode() >= 200 && response.statusCode() < 300) {
                    return response.body();
                }
                
                // Handle error status codes
                throw new ApiException(
                        "API request failed with status code: " + response.statusCode(),
                        response.statusCode(),
                        response.body()
                );
                
            } catch (IOException e) {
                lastException = e;
                logger.warn("Request attempt {}/{} failed: {}", attempt, config.getMaxRetries(), 
                        e.getMessage());
                
                if (attempt < config.getMaxRetries()) {
                    try {
                        Thread.sleep(1000 * attempt); // Simple exponential backoff
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new RestClientException("Request interrupted", ie);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RestClientException("Request interrupted", e);
            }
        }
        
        throw new RestClientException("All retry attempts failed", lastException);
    }
}
