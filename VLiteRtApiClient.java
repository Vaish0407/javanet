package system.score.vms.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import system.score.vms.config.RestClientConfig;

public class VLiteRtApiClient extends BaseRestClient{

    private static final Logger logger = LoggerFactory.getLogger(VLiteRtApiClient.class);

    private static final String VLITECOMMON_ENDPOINT = "/api/vlite";

    /**
     * Constructor for VLiteRTApiClient.
     *
     * @param config REST client configuration
     */
    public VLiteRtApiClient(RestClientConfig config) {
        super(config);
        logger.info("VLiteRTApiClient initialized with base URL: {}", config.getBaseUrl());
    }
}
