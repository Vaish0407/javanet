package system.score.vms.utils;

import system.score.vms.exception.ApiException;
import system.score.vms.exception.RestClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpResponse;

/**
 * Response handler for processing HTTP responses.
 */
public class ResponseHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(ResponseHandler.class);
    
    private final JsonUtil jsonUtil;
    
    public ResponseHandler(JsonUtil jsonUtil) {
        this.jsonUtil = jsonUtil;
    }
    
    /**
     * Handles HTTP response and converts to the specified type.
     */
    public <T> T handleResponse(HttpResponse<String> response, Class<T> responseType) throws RestClientException {
        int statusCode = response.statusCode();
        String responseBody = response.body();
        
        logger.debug("Handling response with status code: {}", statusCode);
        
        if (statusCode >= 200 && statusCode < 300) {
            if (responseType == String.class) {
                return (T) responseBody;
            }
            return jsonUtil.fromJson(responseBody, responseType);
        } else {
            throw new ApiException("API request failed with status: " + statusCode, statusCode, responseBody);
        }
    }
}