package system.score.vms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import system.score.vms.client.VLiteRtApiClient;
import system.score.vms.config.RestClientConfig;

/**
 * Service class for accessing operations that are part of VLiteRT Dll  .
 * Provides business logic layer over the VLiteRTApiClient.
 * Implements enterprise patterns like validation, caching, etc.
 */
public class VLiteRtService {

    private static final Logger logger = LoggerFactory.getLogger(VLiteRtService.class);

    private final VLiteRtApiClient vLiteRtApiClient;

    /**
     * Constructor for VLiteRtServices.
     *
     * @param config REST client configuration
     */
    public VLiteRtService(RestClientConfig config) {
        this.vLiteRtApiClient = new VLiteRtApiClient(config);
        logger.info("VLiteRtServices initialized");
    }

    /**
     * Constructor with custom VLiteRtApiClient.
     * Useful for testing with mocked clients.
     *
     * @param vLiteRtApiClient Custom VLiteRtApiClient instance
     */
    public VLiteRtService(VLiteRtApiClient vLiteRtApiClient) {
        this.vLiteRtApiClient = vLiteRtApiClient;
        logger.info("VLiteHubServices initialized with custom client");
    }
}
