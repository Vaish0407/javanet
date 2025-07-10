package system.score.vms;

import org.python.modules._codecs;
import system.score.vms.config.PropertyLoader;
import system.score.vms.config.RestClientConfig;
import system.score.vms.service.VLiteCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import system.score.vms.service.VLiteConfigService;

import java.time.Duration;

public class VLiteMain {
    private static final Logger logger = LoggerFactory.getLogger(VLiteMain.class);
    private  VLiteCommonService vLiteCommonService;
    private VLiteConfigService vLiteConfigService;

    public VLiteMain() {

        try{
            RestClientConfig config = new RestClientConfig(
                    PropertyLoader.get("api.base.url","http://localhost:5000"),
                    PropertyLoader.get("api.key", "default-api-key"),
                    Duration.ofMillis(PropertyLoader.getLong("http.client.connect.timeout", 30)),
                    Duration.ofMillis(PropertyLoader.getLong("http.client.request.timeout", 30)),
                    PropertyLoader.getInt("http.client.max.retries", 3),
                    PropertyLoader.getBoolean("logging.request.enabled", true));
            this.vLiteCommonService = new VLiteCommonService(config);
            this.vLiteConfigService = new VLiteConfigService(config);
        }
        catch(Exception e ){
            logger.error("Failed to load application properties", e);
        }

    }

    public String vcInitialize(String licenseKey, int versionMajor, int versionMinor) {
        try {
            String response = vLiteCommonService.initialize(licenseKey, versionMajor, versionMinor);
            logger.info("Initialize API invoked successfully: {}", response);
            return response;
        } catch (Exception e) {
            logger.error("Error invoking initialize API", e);
            return "Error: " + e.getMessage();
        }
    }

    public String vcConnect(String ipAddress, int port, String password, boolean readOnly) {
        try {
            String response = vLiteCommonService.connect(ipAddress, port, password, readOnly);
            logger.info("Connect API invoked successfully: {}", response);
            return response;
        } catch (Exception e) {
            logger.error("Error invoking connect API", e);
            return "Error: " + e.getMessage();
        }
    }

    public String vcGetHubTemp(int hubId) {
        try {
            String response = vLiteCommonService.vcGetHubTemp(hubId);
            logger.info("Temperature API invoked successfully: {}", response);
            return response;
        } catch (Exception e) {
            logger.error("Error invoking temperature API", e);
            return "Error: " + e.getMessage();
        }
    }

    public String vcDisconnect(int hubId) {
        try {
            String response = vLiteCommonService.disconnect(hubId);
            logger.info("Disconnect API invoked successfully: {}", response);
            return response;
        } catch (Exception e) {
            logger.error("Error invoking disconnect API", e);
            return "Error: " + e.getMessage();
        }
    }

    public String vcGetHubIPDetails(int hubId) {
        try {
            String response = vLiteCommonService.getHubIPDetails(hubId);
            logger.info("vcGetHubIPDetails API invoked successfully: {}", response);
            return response;
        } catch (Exception e) {
            logger.error("Error invoking vcGetHubIPDetails API", e);
            return "Error: " + e.getMessage();
        }
    }

    public String cfSetHubTagName(int hubId, String tagName) {
        try {
            String response = vLiteConfigService.cfSetHubTagName(hubId, tagName);
            logger.info("cfSetHubTagName API invoked successfully: {}", response);
            return response;
        } catch (Exception e) {
            logger.error("Error invoking cfSetHubTagName API", e);
            return "Error: " + e.getMessage();
        }
    }

    public String vcGetHubUtil(int hubId) {
        try {
            String response = vLiteCommonService.vcGetHubUtil(hubId);
            logger.info("vcGetHubUtil API invoked successfully: {}", response);
            return response;
        } catch (Exception e) {
            logger.error("Error invoking vcGetHubUtil API", e);
            return "Error: " + e.getMessage();
        }
    }

    public String vcGetHubStartUp(int hubId) {
        try {
            String response = vLiteCommonService.vcGetHubStartUp(hubId);
            logger.info("vcGetHubStartUp API invoked successfully: {}", response);
            return response;
        } catch (Exception e) {
            logger.error("Error invoking temperature API", e);
            return "Error: " + e.getMessage();
        }
    }

    public static void main(String[] args){
        VLiteMain vLiteMain = new VLiteMain();
        String result = vLiteMain.vcConnect("10.23.121.10",10,"",false);
        //String result = vLiteMain.vcGetHubIPDetails(1);
        //String result = vLiteMain.vcGetHubTemp(1);
        //String result = vLiteMain.vcGetHubUtil(1);
        //String result = vLiteMain.vcGetHubStartUp(1);
        //String result = vLiteMain.cfSetHubTagName(1,"test");
        System.out.println(result);
    }
}
