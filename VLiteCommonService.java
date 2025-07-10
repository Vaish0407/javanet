package system.score.vms.service;

import system.score.vms.client.VLiteCommonApiClient;
import system.score.vms.config.RestClientConfig;
import system.score.vms.exception.RestClientException;
import system.score.vms.exception.ValidationException;
import system.score.vms.mock.GenerateMockResponse;
import system.score.vms.model.request.VcConnectRequest;
import system.score.vms.model.request.VcInitializeRequest;
import system.score.vms.model.response.*;
import system.score.vms.utils.ValidationUtils;
import system.score.vms.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service class for accessing operations that are part of VLiteCommon Dll  .
 * Provides business logic layer over the VLiteCommonApiClient.
 * Implements enterprise patterns like validation, caching, etc.
 */
public class VLiteCommonService {

    private static final Logger logger = LoggerFactory.getLogger(VLiteCommonService.class);

    private final VLiteCommonApiClient vLiteCommonApiClient;

    /**
     * Constructor for VLiteCommonService.
     *
     * @param config REST client configuration
     */
    public VLiteCommonService(RestClientConfig config) {
        this.vLiteCommonApiClient = new VLiteCommonApiClient(config);
        logger.info("VLiteCommonService initialized");
    }

    /**
     * Constructor with custom UserApiClient.
     * Useful for testing with mocked clients.
     *
     * @param vLiteCommonApiClient Custom VLiteCommonApiClient instance
     */
    public VLiteCommonService(VLiteCommonApiClient vLiteCommonApiClient) {
        this.vLiteCommonApiClient = vLiteCommonApiClient;
        logger.info("VLiteCommonService initialized with custom client");
    }

    /**
     * Invokes vcInitialize function with validation.
     *
     * @param licenseKey   VLite License Key
     * @param versionMajor DLL Major Version Number
     * @param versionMinor DLL Minor Version Number
     * @return JSON String with response from DLL APIs
     * @throws RestClientException If the request fails or validation fails
     */

    public String initialize(String licenseKey, int versionMajor, int versionMinor) throws RestClientException {
        logger.debug("Inside  initialize service, licenseKey: {}, versionMajor: {}, versionMinor: {}", licenseKey, versionMajor, versionMinor);

        try {
            // Validation
            ValidationUtils.requireNonEmpty(licenseKey, "licenseKey");
            ValidationUtils.isGreaterThanZero(versionMajor, "versionMajor");


            VcInitializeRequest vcInitializeRequest = new VcInitializeRequest();
            vcInitializeRequest.setLicenseKey(licenseKey);
            vcInitializeRequest.setVersionMajor(versionMajor);
            vcInitializeRequest.setVersionMinor(versionMinor);


            VcInitializeResponse vcInitializeResponse = vLiteCommonApiClient.initialize(vcInitializeRequest);
            logger.info("Successfully initialized DLL: (Success: {}, ErrorCode: {}, ErrorMessage: {} )", vcInitializeResponse.getSuccess(), vcInitializeResponse.getErrorCode(), vcInitializeResponse.getErrorMessage());
            JsonUtil jsonUtil = new JsonUtil();
            return jsonUtil.toJson(vcInitializeResponse);
        } catch (RestClientException e) {
            logger.error("Failed to initialize DLL :", e);
            ErrorResponse errorResponse = new ErrorResponse(false, e.getErrorCode(), e.getMessage());
            JsonUtil jsonUtil = new JsonUtil();
            return jsonUtil.toJson(errorResponse);
        } catch (ValidationException ve) {
            logger.error("Validation Failed:", ve);
            ErrorResponse errorResponse = new ErrorResponse(false, ve.getMessage(), ValidationUtils.currentTimestamp());
            JsonUtil jsonUtil = new JsonUtil();
            return jsonUtil.toJson(errorResponse);
        }

    }

    /**
     * Invokes vcConnect function with validation.
     *
     * @param ipAddress IP Address of SBU
     * @param port      Port Number of SBU
     * @param password  Password to connect
     * @param readOnly  Connection is Read Only
     * @return JSON String with response from DLL APIs
     * @throws RestClientException If the request fails or validation fails
     */
    public String connect(String ipAddress, int port, String password, Boolean readOnly) throws RestClientException {
        logger.debug("Inside  Connect  service, ipAddress: {}, port: {}, password: {}, readOnly{ {}", ipAddress, port, password, readOnly);


        try {
            // Validation
            ValidationUtils.isValidIPv4(ipAddress, "ipAddress");
            ValidationUtils.isGreaterThanZero(port, "Port");
            ValidationUtils.requireNonEmpty(password, "Password");

            VcConnectRequest vcConnectRequest = new VcConnectRequest();
            vcConnectRequest.setIpAddress(ipAddress);
            vcConnectRequest.setPort(port);
            vcConnectRequest.setPassword(password);
            vcConnectRequest.setReadOnly(readOnly);

            VcConnectResponse vcConnectResponse = vLiteCommonApiClient.connect(vcConnectRequest);
            logger.info("Successfully called connect  DLL: (Success: {}, ErrorCode: {}, ErrorMessage: {} )", vcConnectResponse.getSuccess(), vcConnectResponse.getVcConnectDataResponse().getErrorCode(), vcConnectResponse.getError());
            JsonUtil jsonUtil = new JsonUtil();
            return jsonUtil.toJson(vcConnectResponse);
        } catch (RestClientException e) {
            logger.error("Failed to initialize DLL :", e);
            ErrorResponse errorResponse = new ErrorResponse(false, e.getErrorCode(), e.getMessage());
            JsonUtil jsonUtil = new JsonUtil();
            //return jsonUtil.toJson(errorResponse);
            //Mocking vcConnect Success response for development  purpose
            GenerateMockResponse generateMockResponse = new GenerateMockResponse();
            return jsonUtil.toJson(generateMockResponse.mockVcConnectResponse());
        } catch (ValidationException ve) {
            logger.error("Validation Failed:", ve);
            ErrorResponse errorResponse = new ErrorResponse(false, ve.getMessage(), ValidationUtils.currentTimestamp());
            JsonUtil jsonUtil = new JsonUtil();
            return jsonUtil.toJson(errorResponse);
        }

    }


    /**
     * Invokes vcGetHubTemp function with validation.
     *
     * @param hubId HUB ID  of SBU
     * @return JSON String with response from DLL APIs
     * @throws RestClientException If the request fails or validation fails
     */
    public String vcGetHubTemp(int hubId) throws RestClientException {
        logger.debug("Inside  vcGetHubTemp  service, hubId: {}", hubId);
        try {

            // Validation
            ValidationUtils.isGreaterThanZero(hubId, "hubId");

            VcGetHubTempResponse vcGetHubTempResponse = vLiteCommonApiClient.vcGetHubTemp(hubId);
            logger.info("Successfully called vcGetHubTemp  DLL: (Success: {}, ErrorMessage: {} )", vcGetHubTempResponse.getSuccess(), vcGetHubTempResponse.getError());
            JsonUtil jsonUtil = new JsonUtil();
            return jsonUtil.toJson(vcGetHubTempResponse);
        } catch (RestClientException e) {
            logger.error("Failed to initialize DLL :", e);
            ErrorResponse errorResponse = new ErrorResponse(false, e.getErrorCode(), e.getMessage());
            JsonUtil jsonUtil = new JsonUtil();
            return jsonUtil.toJson(errorResponse);
        } catch (ValidationException ve) {
            logger.error("Validation Failed:", ve);
            ErrorResponse errorResponse = new ErrorResponse(false, ve.getMessage(), ValidationUtils.currentTimestamp());
            JsonUtil jsonUtil = new JsonUtil();
            return jsonUtil.toJson(errorResponse);
        }

    }

    /**
     * Invokes vcDisconnect function with validation.
     *
     * @param hubId HUB ID  of SBU
     * @return JSON String with response from DLL APIs
     * @throws RestClientException If the request fails or validation fails
     */
    public String disconnect(int hubId) throws RestClientException {
        logger.debug("Inside  Disconnect  service, hubId: {}", hubId);
        try {

            // Validation
            ValidationUtils.isGreaterThanZero(hubId, "hubId");


            VcDisconnectResponse vliteDisconnectResponse = vLiteCommonApiClient.disconnect(hubId);
            logger.info("Successfully called Disconnect  DLL: (Success: {}, ErrorCode: {}, ErrorMessage: {} )", vliteDisconnectResponse.getSuccess(), vliteDisconnectResponse.getErrorCode(), vliteDisconnectResponse.getErrorMessage());
            JsonUtil jsonUtil = new JsonUtil();
            return jsonUtil.toJson(vliteDisconnectResponse);
        } catch (RestClientException e) {
            logger.error("Failed to initialize DLL :", e);
            ErrorResponse errorResponse = new ErrorResponse(false, e.getErrorCode(), e.getMessage());
            JsonUtil jsonUtil = new JsonUtil();
            return jsonUtil.toJson(errorResponse);
        } catch (ValidationException ve) {
            logger.error("Validation Failed:", ve);
            ErrorResponse errorResponse = new ErrorResponse(false, ve.getMessage(), ValidationUtils.currentTimestamp());
            JsonUtil jsonUtil = new JsonUtil();
            return jsonUtil.toJson(errorResponse);
        }

    }

    /**
     * Invokes vcGetHubIPDetails function with validation.
     *
     * @param hubId HUB ID  of SBU
     * @return JSON String with response from DLL APIs
     * @throws RestClientException If the request fails or validation fails
     */
    public String getHubIPDetails(int hubId) throws RestClientException {
        logger.debug("Inside  getHubIPDetails  service, hubId: {}", hubId);
        try {

            // Validation
            ValidationUtils.isGreaterThanZero(hubId, "hubId");

            VcGetHubIPDetailsResponse vcGetHubIPDetailsResponse = vLiteCommonApiClient.getHubIpDetails(hubId);
            logger.info("Successfully called vcGetHubIPDetailsResponse  DLL: (Success: {}, ErrorCode: {}, ErrorMessage: {} )", vcGetHubIPDetailsResponse.getSuccess(), vcGetHubIPDetailsResponse.getVcGetHubIPDetailsDataResponse().getErrorCode(), vcGetHubIPDetailsResponse.getVcGetHubIPDetailsDataResponse().getErrorMessage());
            JsonUtil jsonUtil = new JsonUtil();
            return jsonUtil.toJson(vcGetHubIPDetailsResponse);
        } catch (RestClientException e) {
            logger.error("Failed to initialize DLL :", e);
            ErrorResponse errorResponse = new ErrorResponse(false, e.getErrorCode(), e.getMessage());
            JsonUtil jsonUtil = new JsonUtil();
            //return jsonUtil.toJson(errorResponse);
            //Mocking vcGetHubIPDetails Success response for development  purpose
            GenerateMockResponse generateMockResponse = new GenerateMockResponse();
            return jsonUtil.toJson(generateMockResponse.mockVcGetHubIPDetailsresponse());
        } catch (ValidationException ve) {
            logger.error("Validation Failed:", ve);
            ErrorResponse errorResponse = new ErrorResponse(false, ve.getMessage(), ValidationUtils.currentTimestamp());
            JsonUtil jsonUtil = new JsonUtil();
            return jsonUtil.toJson(errorResponse);
        }

    }

    /**
     * Invokes vcGetHubUtil function with validation.
     *
     * @param hubId HUB ID  of SBU
     * @return JSON String with response from DLL APIs
     * @throws RestClientException If the request fails or validation fails
     */
    public String vcGetHubUtil(int hubId) throws RestClientException {
        logger.debug("Inside  vcGetHubUtil  service, hubId: {}", hubId);
        try {

            // Validation
            ValidationUtils.isGreaterThanZero(hubId, "hubId");

            VcGetHubUtilResponse vcGetHubUtilResponse = vLiteCommonApiClient.vcGetHubUtil(hubId);
            logger.info("Successfully called vcGetHubUtil  DLL: (Success: {}, ErrorMessage: {} )", vcGetHubUtilResponse.getSuccess(), vcGetHubUtilResponse.getError());
            JsonUtil jsonUtil = new JsonUtil();
            return jsonUtil.toJson(vcGetHubUtilResponse);
        } catch (RestClientException e) {
            logger.error("Failed to initialize DLL :", e);
            ErrorResponse errorResponse = new ErrorResponse(false, e.getErrorCode(), e.getMessage());
            JsonUtil jsonUtil = new JsonUtil();
            return jsonUtil.toJson(errorResponse);
        } catch (ValidationException ve) {
            logger.error("Validation Failed:", ve);
            ErrorResponse errorResponse = new ErrorResponse(false, ve.getMessage(), ValidationUtils.currentTimestamp());
            JsonUtil jsonUtil = new JsonUtil();
            return jsonUtil.toJson(errorResponse);
        }

    }

    /**
     * Invokes vcGetHubStartUp function with validation.
     *
     * @param hubId HUB ID  of SBU
     * @return JSON String with response from DLL APIs
     * @throws RestClientException If the request fails or validation fails
     */
    public String vcGetHubStartUp(int hubId) throws RestClientException {
        logger.debug("Inside  vcGetHubStartUp  service, hubId: {}", hubId);
        try {

            // Validation
            ValidationUtils.isGreaterThanZero(hubId, "hubId");

            VcGetHubStartUpResponse vcGetHubStartUpResponse = vLiteCommonApiClient.vcGetHubStartUp(hubId);
            logger.info("Successfully called vcGetHubStartUp  DLL: (Success: {}, ErrorMessage: {} )", vcGetHubStartUpResponse.getSuccess(), vcGetHubStartUpResponse.getError());
            JsonUtil jsonUtil = new JsonUtil();
            return jsonUtil.toJson(vcGetHubStartUpResponse);
        } catch (RestClientException e) {
            logger.error("Failed to initialize DLL :", e);
            ErrorResponse errorResponse = new ErrorResponse(false, e.getErrorCode(), e.getMessage());
            JsonUtil jsonUtil = new JsonUtil();
            return jsonUtil.toJson(errorResponse);
        } catch (ValidationException ve) {
            logger.error("Validation Failed:", ve);
            ErrorResponse errorResponse = new ErrorResponse(false, ve.getMessage(), ValidationUtils.currentTimestamp());
            JsonUtil jsonUtil = new JsonUtil();
            return jsonUtil.toJson(errorResponse);
        }

    }
}
