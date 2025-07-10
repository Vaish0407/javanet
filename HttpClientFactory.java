package system.score.vms.client;

import system.score.vms.config.ClientConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpClient;
import java.time.Duration;

/**
 * Factory class for creating configured HTTP clients
 */
public class HttpClientFactory {
    
    private static final Logger logger = LoggerFactory.getLogger(HttpClientFactory.class);
    
    private HttpClientFactory() {
        // Utility class
    }
    
    /**
     * Create a configured HTTP client based on the provided configuration
     */
    public static HttpClient createHttpClient(ClientConfiguration config) {
        logger.debug("Creating HTTP client with configuration");
        
        HttpClient.Builder builder = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(config.getConnectionTimeoutSeconds()))
                .followRedirects(HttpClient.Redirect.NORMAL);
        
        // Set version if specified
        if (config.getHttpVersion() != null) {
            builder.version(config.getHttpVersion());
        }
        
        HttpClient client = builder.build();
        logger.debug("HTTP client created successfully");
        
        return client;
    }
}
