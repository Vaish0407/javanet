package system.score.vms.service;

import system.score.vms.exception.RestClientException;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

/**
 * Interface for REST client service operations
 */
public interface RestClientService {
    
    /**
     * Performs a GET request
     * 
     * @param url the URL to request
     * @param headers optional headers
     * @return HTTP response
     * @throws RestClientException if request fails
     */
    HttpResponse<String> get(String url, Map<String, String> headers) throws RestClientException;
    
    /**
     * Performs a POST request
     * 
     * @param url the URL to request
     * @param body request body
     * @param headers optional headers
     * @return HTTP response
     * @throws RestClientException if request fails
     */
    HttpResponse<String> post(String url, String body, Map<String, String> headers) throws RestClientException;
    
    /**
     * Performs a PUT request
     * 
     * @param url the URL to request
     * @param body request body
     * @param headers optional headers
     * @return HTTP response
     * @throws RestClientException if request fails
     */
    HttpResponse<String> put(String url, String body, Map<String, String> headers) throws RestClientException;
    
    /**
     * Performs a DELETE request
     * 
     * @param url the URL to request
     * @param headers optional headers
     * @return HTTP response
     * @throws RestClientException if request fails
     */
    HttpResponse<String> delete(String url, Map<String, String> headers) throws RestClientException;
    
    /**
     * Performs a custom HTTP request
     * 
     * @param request the HTTP request
     * @return HTTP response
     * @throws RestClientException if request fails
     */
    HttpResponse<String> execute(HttpRequest request) throws RestClientException;
}
