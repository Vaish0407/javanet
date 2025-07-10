package system.score.vms.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import system.score.vms.config.RestClientConfig;

public class VLiteHubApiClient extends BaseRestClient{

    private static final Logger logger = LoggerFactory.getLogger(VLiteHubApiClient.class);

    private static final String VLITECOMMON_ENDPOINT = "/api/vlite";

    /**
     * Constructor for VLiteHubApiClient.
     *
     * @param config REST client configuration
     */
    public VLiteHubApiClient(RestClientConfig config) {
        super(config);
        logger.info("VLiteHubApiClient initialized with base URL: {}", config.getBaseUrl());
    }
}
