package system.score.vms.service.impl;

import system.score.vms.config.HttpClientConfig;
import system.score.vms.config.RestClientConfig;
import system.score.vms.exception.ApiException;
import system.score.vms.exception.NetworkException;
import system.score.vms.exception.RestClientException;
import system.score.vms.service.RestClientService;
import system.score.vms.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

/**
 * Implementation of REST client service using Java HTTP Client
 */
public class RestClientServiceImpl implements RestClientService {
    
    private static final Logger logger = LoggerFactory.getLogger(RestClientServiceImpl.class);
    
    private final HttpClient httpClient;
    private final RestClientConfig config;
    
    public RestClientServiceImpl(RestClientConfig config) {
        this.config = config;
        this.httpClient = HttpClientConfig.createHttpClient(config);
    }
    
    @Override
    public HttpResponse<String> get(String url, Map<String, String> headers) throws RestClientException {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(config.getRequestTimeout())
                .GET();
        
        addHeaders(requestBuilder, headers);
        
        return executeWithRetry(requestBuilder.build());
    }
    
    @Override
    public HttpResponse<String> post(String url, String body, Map<String, String> headers) throws RestClientException {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(config.getRequestTimeout())
                .POST(HttpRequest.BodyPublishers.ofString(body != null ? body : ""));
        
        addHeaders(requestBuilder, headers);
        addContentTypeHeader(requestBuilder);
        
        return executeWithRetry(requestBuilder.build());
    }
    
    @Override
    public HttpResponse<String> put(String url, String body, Map<String, String> headers) throws RestClientException {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(config.getRequestTimeout())
                .PUT(HttpRequest.BodyPublishers.ofString(body != null ? body : ""));
        
        addHeaders(requestBuilder, headers);
        addContentTypeHeader(requestBuilder);
        
        return executeWithRetry(requestBuilder.build());
    }
    
    @Override
    public HttpResponse<String> delete(String url, Map<String, String> headers) throws RestClientException {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(config.getRequestTimeout())
                .DELETE();
        
        addHeaders(requestBuilder, headers);
        
        return executeWithRetry(requestBuilder.build());
    }
    
    @Override
    public HttpResponse<String> execute(HttpRequest request) throws RestClientException {
        return executeWithRetry(request);
    }
    
    private HttpResponse<String> executeWithRetry(HttpRequest request) throws RestClientException {
        int attempts = 0;
        int maxAttempts = config.getMaxRetries() + 1;
        
        while (attempts < maxAttempts) {
            attempts++;
            
            try {
                logger.debug("Executing HTTP request: {} {} (attempt {}/{})", 
                           request.method(), request.uri(), attempts, maxAttempts);
                
                HttpResponse<String> response = httpClient.send(request, 
                                                              HttpResponse.BodyHandlers.ofString());
                
                // Check if response indicates an error that should be retried
                if (shouldRetry(response, attempts, maxAttempts)) {
                    logger.warn("Request failed with status {}, retrying in 1000ms", 
                              response.statusCode());
                    Thread.sleep(1000);
                    continue;
                }
                
                // Check for API errors
                if (!HttpUtil.isSuccessful(response.statusCode())) {
                    throw new ApiException("API request failed with status: " + response.statusCode() + 
                                         ", body: " + response.body(), response.statusCode());
                }
                
                logger.debug("HTTP request completed successfully: {} {}", 
                           request.method(), request.uri());
                
                return response;
                
            } catch (IOException e) {
                if (attempts >= maxAttempts) {
                    throw new NetworkException("Network error after " + attempts + " attempts", e);
                }
                
                logger.warn("Network error on attempt {}/{}, retrying in 1000ms", 
                          attempts, maxAttempts, e);
                
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RestClientException("Request interrupted", ie);
                }
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RestClientException("Request interrupted", e);
            }
        }
        
        throw new RestClientException("Request failed after " + attempts + " attempts");
    }
    
    private boolean shouldRetry(HttpResponse<String> response, int attempts, int maxAttempts) {
        if (attempts >= maxAttempts) {
            return false;
        }
        
        int statusCode = response.statusCode();
        
        // Retry on server errors (5xx) and specific client errors
        return statusCode >= 500 || 
               statusCode == 429 || // Too Many Requests
               statusCode == 408;   // Request Timeout
    }
    
    private void addHeaders(HttpRequest.Builder requestBuilder, Map<String, String> headers) {
        // Add default headers
        requestBuilder.header("User-Agent", "Enterprise-REST-Client/1.0.0");
        
        // Add API key if configured
        String apiKey = config.getApiKey();
        if (apiKey != null && !apiKey.isEmpty()) {
            requestBuilder.header("Authorization", "Bearer " + apiKey);
        }
        
        // Add custom headers
        if (headers != null) {
            headers.forEach(requestBuilder::header);
        }
    }
    
    private void addContentTypeHeader(HttpRequest.Builder requestBuilder) {
        requestBuilder.header("Content-Type", "application/json");
    }
}
