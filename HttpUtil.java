package system.score.vms.utils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Utility class for HTTP-related operations.
 * Provides helper methods for URL encoding, query parameter building, etc.
 */
public class HttpUtil {
    
    /**
     * Private constructor to prevent instantiation.
     */
    private HttpUtil() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
    
    /**
     * Builds a query string from a map of parameters.
     * 
     * @param parameters Map of parameter names to values
     * @return URL-encoded query string (without leading '?')
     */
    public static String buildQueryString(Map<String, String> parameters) {
        if (parameters == null || parameters.isEmpty()) {
            return "";
        }
        
        StringJoiner joiner = new StringJoiner("&");
        parameters.forEach((key, value) -> {
            if (key != null && value != null) {
                String encodedKey = urlEncode(key);
                String encodedValue = urlEncode(value);
                joiner.add(encodedKey + "=" + encodedValue);
            }
        });
        
        return joiner.toString();
    }
    
    /**
     * URL encodes a string using UTF-8 encoding.
     * 
     * @param value The string to encode
     * @return URL-encoded string
     */
    public static String urlEncode(String value) {
        if (value == null) {
            return "";
        }
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
    
    /**
     * Builds a complete URL with base URL, path, and query parameters.
     * 
     * @param baseUrl The base URL
     * @param path The path to append
     * @param queryParams Query parameters
     * @return Complete URL string
     */
    public static String buildUrl(String baseUrl, String path, Map<String, String> queryParams) {
        StringBuilder url = new StringBuilder(baseUrl);
        
        // Ensure base URL doesn't end with slash and path doesn't start with slash
        if (baseUrl.endsWith("/") && path.startsWith("/")) {
            url.setLength(url.length() - 1);
        } else if (!baseUrl.endsWith("/") && !path.startsWith("/")) {
            url.append("/");
        }
        
        url.append(path);
        
        // Add query parameters if present
        String queryString = buildQueryString(queryParams);
        if (!queryString.isEmpty()) {
            url.append("?").append(queryString);
        }
        
        return url.toString();
    }
    
    /**
     * Extracts the content type from a Content-Type header value.
     * Removes any additional parameters like charset.
     * 
     * @param contentTypeHeader The Content-Type header value
     * @return The main content type
     */
    public static String extractContentType(String contentTypeHeader) {
        if (contentTypeHeader == null || contentTypeHeader.trim().isEmpty()) {
            return "";
        }
        
        int semicolonIndex = contentTypeHeader.indexOf(';');
        if (semicolonIndex != -1) {
            return contentTypeHeader.substring(0, semicolonIndex).trim();
        }
        
        return contentTypeHeader.trim();
    }
    
    /**
     * Checks if a content type is JSON.
     * 
     * @param contentType The content type to check
     * @return true if content type indicates JSON
     */
    public static boolean isJsonContentType(String contentType) {
        if (contentType == null) {
            return false;
        }
        
        String lowerCase = contentType.toLowerCase();
        return lowerCase.contains("application/json") || 
               lowerCase.contains("text/json") ||
               lowerCase.contains("+json");
    }
    
    /**
     * Validates if a URL is properly formatted.
     * 
     * @param url The URL to validate
     * @return true if URL is valid
     */
    public static boolean isValidUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            return false;
        }
        
        try {
            java.net.URI.create(url);
            return url.toLowerCase().startsWith("http://") || 
                   url.toLowerCase().startsWith("https://");
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    /**
     * Gets the HTTP status code category.
     * 
     * @param statusCode The HTTP status code
     * @return Status category as string
     */
    public static String getStatusCategory(int statusCode) {
        if (statusCode >= 100 && statusCode < 200) {
            return "Informational";
        } else if (statusCode >= 200 && statusCode < 300) {
            return "Success";
        } else if (statusCode >= 300 && statusCode < 400) {
            return "Redirection";
        } else if (statusCode >= 400 && statusCode < 500) {
            return "Client Error";
        } else if (statusCode >= 500 && statusCode < 600) {
            return "Server Error";
        } else {
            return "Unknown";
        }
    }
    
    /**
     * Checks if an HTTP status code indicates success.
     * 
     * @param statusCode The HTTP status code
     * @return true if status code is in 2xx range
     */
    public static boolean isSuccessful(int statusCode) {
        return statusCode >= 200 && statusCode < 300;
    }
    
    /**
     * Checks if an HTTP status code indicates a client error.
     * 
     * @param statusCode The HTTP status code
     * @return true if status code is in 4xx range
     */
    public static boolean isClientError(int statusCode) {
        return statusCode >= 400 && statusCode < 500;
    }
    
    /**
     * Checks if an HTTP status code indicates a server error.
     * 
     * @param statusCode The HTTP status code
     * @return true if status code is in 5xx range
     */
    public static boolean isServerError(int statusCode) {
        return statusCode >= 500 && statusCode < 600;
    }
}
