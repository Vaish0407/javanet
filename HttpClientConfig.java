package system.score.vms.config;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.http.HttpClient;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.time.Duration;

/**
 * Configuration factory for HTTP client instances.
 * Provides configured HttpClient instances based on RestClientConfig.
 */
public class HttpClientConfig {
    
    /**
     * Creates a configured HttpClient instance based on the provided configuration.
     * 
     * @param config The REST client configuration
     * @return Configured HttpClient instance
     */
    public static HttpClient createHttpClient(RestClientConfig config) {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0]; // Return an empty array
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        // Do nothing - trust all client certificates
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        // Do nothing - trust all server certificates
                    }
                }
        };
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());

            HttpClient.Builder builder = HttpClient.newBuilder()
                .connectTimeout(config.getConnectTimeout())
                .followRedirects(HttpClient.Redirect.NORMAL)
                    .sslContext(sslContext);

        // Add additional configurations as needed
        // For enterprise use, you might want to add:
        // - Custom SSL context
        // - Proxy configuration
        // - Authentication handlers
        
        return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Creates a default HttpClient with sensible defaults for enterprise use.
     * 
     * @return Configured HttpClient instance with defaults
     */
    public static HttpClient createDefaultHttpClient() {

        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0]; // Return an empty array
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        // Do nothing - trust all client certificates
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        // Do nothing - trust all server certificates
                    }
                }
        };
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());


        return HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .sslContext(sslContext)
                .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
