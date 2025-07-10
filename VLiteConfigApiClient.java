package system.score.vms.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import system.score.vms.config.RestClientConfig;
import system.score.vms.exception.RestClientException;
import system.score.vms.model.request.CfSetHubTagNameRequest;
import system.score.vms.model.response.CfSetHubTagNameResponse;
import system.score.vms.model.response.VcInitializeResponse;

import java.util.HashMap;
import java.util.Map;

public class VLiteConfigApiClient extends BaseRestClient{

    private static final Logger logger = LoggerFactory.getLogger(VLiteConfigApiClient.class);

    private static final String VLITECONFIG_ENDPOINT = "/api/vlite";

    /**
     * Constructor for VLiteConfigApiClient.
     *
     * @param config REST client configuration
     */
    public VLiteConfigApiClient(RestClientConfig config) {
        super(config);
        logger.info("VLiteConfigApiClient initialized with base URL: {}", config.getBaseUrl());
    }

    /**
     * Invokes cfSetHubTagName
     *
     * @param cfSetHubTagNameRequest   cfSetHubTagName request
     * @return cfSetHubTagNameResponse response object
     * @throws RestClientException If the request fails
     */
    public CfSetHubTagNameResponse cfSetHubTagName(CfSetHubTagNameRequest cfSetHubTagNameRequest) throws RestClientException {
        logger.debug("Calling cfSetHubTagName api with hubId: {}, tagName: {}", cfSetHubTagNameRequest.getHubId(), cfSetHubTagNameRequest.getTagName());
        String endpoint = VLITECONFIG_ENDPOINT + "/cfSetHubTagName";
        Map<String, String> headers = new HashMap<>();
        CfSetHubTagNameResponse cfSetHubTagNameResponse =post(endpoint, cfSetHubTagNameRequest, CfSetHubTagNameResponse.class, headers);
        logger.debug("Successfully invoked cfSetHubTagName api, success: {}", cfSetHubTagNameResponse.getSuccess());
        return cfSetHubTagNameResponse;

    }
}
