package system.score.vms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import system.score.vms.client.VLiteHubApiClient;
import system.score.vms.config.RestClientConfig;

/**
 * Service class for accessing operations that are part of VLiteHub Dll  .
 * Provides business logic layer over the VLiteHubApiClient.
 * Implements enterprise patterns like validation, caching, etc.
 */
public class VLiteHubService {

    private static final Logger logger = LoggerFactory.getLogger(VLiteHubService.class);

    private final VLiteHubApiClient vLiteHubApiClient;

    /**
     * Constructor for VLiteHubServices.
     *
     * @param config REST client configuration
     */
    public VLiteHubService(RestClientConfig config) {
        this.vLiteHubApiClient = new VLiteHubApiClient(config);
        logger.info("VLiteHubServices initialized");
    }

    /**
     * Constructor with custom VLiteHubApiClient.
     * Useful for testing with mocked clients.
     *
     * @param vLiteHubApiClient Custom VLiteHubApiClient instance
     */
    public VLiteHubService(VLiteHubApiClient vLiteHubApiClient) {
        this.vLiteHubApiClient = vLiteHubApiClient;
        logger.info("VLiteHubServices initialized with custom client");
    }
}
