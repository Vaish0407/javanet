package system.score.vms.mock;

import system.score.vms.model.response.VcConnectResponse;
import system.score.vms.model.response.VcGetHubIPDetailsResponse;
import system.score.vms.utils.JsonUtil;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class GenerateMockResponse {

    public  VcConnectResponse mockVcConnectResponse(){
        //URL url = GenerateMockResponse.class.getClassLoader().getResource("mockConnectResponse.json");

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("mockConnectResponse.json");
            String response = new String(is.readAllBytes(),StandardCharsets.UTF_8);
                    // Path path = Path.of(url.toURI());
           // String response = Files.readString(path);
            JsonUtil jsonUtil = new JsonUtil();
            return jsonUtil.fromJson(response, VcConnectResponse.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public VcGetHubIPDetailsResponse mockVcGetHubIPDetailsresponse(){
        //URL url = GenerateMockResponse.class.getClassLoader().getResource("mockVcGetHubIPDetailsResponse.json");

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("mockVcGetHubIPDetailsResponse.json");
            String response = new String(is.readAllBytes(),StandardCharsets.UTF_8);
           // Path path = Path.of(url.toURI());
            //String response = Files.readString(path);
            JsonUtil jsonUtil = new JsonUtil();
            return jsonUtil.fromJson(response, VcGetHubIPDetailsResponse.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
