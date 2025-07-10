package system.score.vms.client;

import system.score.vms.config.RestClientConfig;
import system.score.vms.exception.RestClientException;
import system.score.vms.model.request.VcConnectRequest;
import system.score.vms.model.request.VcInitializeRequest;
import system.score.vms.model.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * REST client for VLite Common  API operations.
 */
public class VLiteCommonApiClient extends BaseRestClient{

    private static final Logger logger = LoggerFactory.getLogger(VLiteCommonApiClient.class);

    private static final String VLITECOMMON_ENDPOINT = "/api/vlite";

    /**
     * Constructor for VLiteCommonApiClient.
     *
     * @param config REST client configuration
     */
    public VLiteCommonApiClient(RestClientConfig config) {
        super(config);
        logger.info("VLiteCommonApiClient initialized with base URL: {}", config.getBaseUrl());
    }

    /**
     * Invokes vcInitialize
     *
     * @param vcInitializeRequest VLite Initialize request
     * @return vcInitializeResponse VLite Initialize response
     * @throws RestClientException If the request fails
     */
    public VcInitializeResponse initialize(VcInitializeRequest vcInitializeRequest) throws RestClientException {
        logger.debug("Calling initialize api with licenseKey: {}, versionMajor: {}, versionMinor: {}", vcInitializeRequest.getLicenseKey(), vcInitializeRequest.getVersionMajor(), vcInitializeRequest.getVersionMinor());
        String endpoint = VLITECOMMON_ENDPOINT + "/initialize";
        Map<String, String> headers = new HashMap<>();
        VcInitializeResponse vcInitializeResponse =post(endpoint, vcInitializeRequest, VcInitializeResponse.class, headers);
        logger.debug("Successfully invoked initialize api, success: {}", vcInitializeResponse.getSuccess());

        return vcInitializeResponse;
    }

    /**
     * Invokes vcConnect
     *
     * @param vcConnectRequest   VLite connect request
     * @return VcConnectResponse response object
     * @throws RestClientException If the request fails
     */
    public VcConnectResponse connect(VcConnectRequest vcConnectRequest) throws RestClientException {
        logger.debug("Calling Connect api with ipAddress: {}, port: {}, password: {}, readOnly: {}", vcConnectRequest.getIpAddress(), vcConnectRequest.getPort(), vcConnectRequest.getPassword(),vcConnectRequest.getReadOnly());
        String endpoint = VLITECOMMON_ENDPOINT + "/connect";
        Map<String, String> headers = new HashMap<>();
        VcConnectResponse vcConnectResponse = post(endpoint, vcConnectRequest, VcConnectResponse.class, headers);;
        logger.debug("Successfully invoked Connect api, success: {}", vcConnectResponse.getSuccess());
        return vcConnectResponse;
    }

    /**
     * Invokes vcGetHubTemp
     *
     * @param hubId   ID of Hub
     * @return VliteTemperatureResponse object
     * @throws RestClientException If the request fails
     */
    public VcGetHubTempResponse vcGetHubTemp(int hubId) throws RestClientException {
        logger.debug("Calling vcGetHubTemp api with hubId: {}", hubId);
        String endpoint = VLITECOMMON_ENDPOINT + "/hub/" + hubId + "/temperature";
        Map<String, String> headers = new HashMap<>();
        VcGetHubTempResponse vcGetHubTempResponse = get(endpoint, VcGetHubTempResponse.class, headers);
        logger.debug("Successfully invoked vcGetHubTemp api, success: {}", vcGetHubTempResponse.getSuccess());
        return vcGetHubTempResponse;
    }

    /**
     * Invokes vcDisconnect
     *
     * @param hubId   ID of Hub
     * @return VliteDisconnectResponse object
     * @throws RestClientException If the request fails
     */
    public VcDisconnectResponse disconnect(int hubId) throws RestClientException {
        logger.debug("Calling Disconnect api with hubId: {}", hubId);
        String endpoint = VLITECOMMON_ENDPOINT + "/disconnect/" + hubId;
        Map<String, String> headers = new HashMap<>();
        VcDisconnectResponse vcDisconnectResponse = post(endpoint, "", VcDisconnectResponse.class, headers);
        logger.debug("Successfully invoked Disconnect api, success: {}", vcDisconnectResponse.getSuccess());
        return vcDisconnectResponse;
    }

    /**
     * Invokes vcDisconnect
     *
     * @param hubId   ID of Hub
     * @return VliteDisconnectResponse object
     * @throws RestClientException If the request fails
     */
    public VcGetHubIPDetailsResponse getHubIpDetails(int hubId) throws RestClientException {
        logger.debug("Calling VcGetHubIPDetailsResponse api with hubId: {}", hubId);
        String endpoint = VLITECOMMON_ENDPOINT + "/getHubIpDetails/" + hubId;
        Map<String, String> headers = new HashMap<>();
        VcGetHubIPDetailsResponse vcGetHubIPDetailsResponse = post(endpoint, "", VcGetHubIPDetailsResponse.class, headers);
        logger.debug("Successfully invoked GetHubIPDetails api, success: {}", vcGetHubIPDetailsResponse.getSuccess());
        return vcGetHubIPDetailsResponse;
    }

    /**
     * Invokes vcGetHubUtil
     *
     * @param hubId   ID of Hub
     * @return VliteTemperatureResponse object
     * @throws RestClientException If the request fails
     */
    public VcGetHubUtilResponse vcGetHubUtil(int hubId) throws RestClientException {
        logger.debug("Calling vcGetHubUtil api with hubId: {}", hubId);
        String endpoint = VLITECOMMON_ENDPOINT + "/hub/" + hubId + "/vcGetHubUtil";
        Map<String, String> headers = new HashMap<>();
        VcGetHubUtilResponse vcGetHubUtilResponse = get(endpoint, VcGetHubUtilResponse.class, headers);
        logger.debug("Successfully invoked vcGetHubUtil api, success: {}", vcGetHubUtilResponse.getSuccess());
        return vcGetHubUtilResponse;
    }

    /**
     * Invokes vcGetHubStartUp
     *
     * @param hubId   ID of Hub
     * @return VliteTemperatureResponse object
     * @throws RestClientException If the request fails
     */
    public VcGetHubStartUpResponse vcGetHubStartUp(int hubId) throws RestClientException {
        logger.debug("Calling vcGetHubStartUp api with hubId: {}", hubId);
        String endpoint = VLITECOMMON_ENDPOINT + "/hub/" + hubId + "/vcGetHubStartUp";
        Map<String, String> headers = new HashMap<>();
        VcGetHubStartUpResponse vcGetHubStartUpResponse = get(endpoint, VcGetHubStartUpResponse.class, headers);
        logger.debug("Successfully invoked vcGetHubStartUp api, success: {}", vcGetHubStartUpResponse.getSuccess());
        return vcGetHubStartUpResponse;
    }
}
