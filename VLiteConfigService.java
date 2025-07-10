package system.score.vms.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import system.score.vms.client.VLiteConfigApiClient;
import system.score.vms.config.RestClientConfig;
import system.score.vms.exception.RestClientException;
import system.score.vms.model.request.CfSetHubTagNameRequest;
import system.score.vms.model.request.VcInitializeRequest;
import system.score.vms.model.response.CfSetHubTagNameResponse;
import system.score.vms.model.response.ErrorResponse;
import system.score.vms.model.response.VcInitializeResponse;
import system.score.vms.utils.JsonUtil;

/**
 * Service class for accessing operations that are part of VLiteConfig Dll  .
 * Provides business logic layer over the VLiteConfigApiClient.
 * Implements enterprise patterns like validation, caching, etc.
 */
public class VLiteConfigService {

    private static final Logger logger = LoggerFactory.getLogger(VLiteConfigService.class);

    private final VLiteConfigApiClient vLiteConfigApiClient;

    /**
     * Constructor for VLiteConfigServices.
     *
     * @param config REST client configuration
     */
    public VLiteConfigService(RestClientConfig config) {
        this.vLiteConfigApiClient = new VLiteConfigApiClient(config);
        logger.info("VLiteCommonService initialized");
    }

    /**
     * Constructor with custom VLiteConfigApiClient.
     * Useful for testing with mocked clients.
     *
     * @param vLiteConfigApiClient Custom VLiteConfigApiClient instance
     */
    public VLiteConfigService(VLiteConfigApiClient vLiteConfigApiClient) {
        this.vLiteConfigApiClient = vLiteConfigApiClient;
        logger.info("VLiteCommonService initialized with custom client");
    }

    /**
     * Invokes cfSetHubTagName function with validation.
     *
     * @param hubId   VLite License Key
     * @param tagName DLL Major Version Number
     * @return JSON String with response from DLL APIs
     * @throws RestClientException  If the request fails or validation fails
     */
    public String cfSetHubTagName(int hubId, String tagName) throws RestClientException {

            logger.debug("Inside  cfSetHubTagName service, hubId: {}, tagName: {}", hubId, tagName);

            try {
                // Validation
                if (hubId <= 0) {
                    throw new RestClientException("Invalid Hu Id", "INVALID_HUB_ID");
                }

                CfSetHubTagNameRequest cfSetHubTagNameRequest = new CfSetHubTagNameRequest();
                cfSetHubTagNameRequest.setHubId(hubId);
                cfSetHubTagNameRequest.setTagName(tagName);


                CfSetHubTagNameResponse cfSetHubTagNameResponse = vLiteConfigApiClient.cfSetHubTagName(cfSetHubTagNameRequest);
                logger.info("Successfully initialized DLL: (Success: {}, ErrorMessage: {} )", cfSetHubTagNameResponse.getSuccess(), cfSetHubTagNameResponse.getError());
                JsonUtil jsonUtil = new JsonUtil();
                return jsonUtil.toJson(cfSetHubTagNameResponse);
            } catch (RestClientException e) {
                logger.error("Failed to initialize DLL :", e);
                ErrorResponse errorResponse = new ErrorResponse(false, e.getErrorCode(), e.getMessage());
                JsonUtil jsonUtil = new JsonUtil();
                return jsonUtil.toJson(errorResponse);
            }

        }
}
