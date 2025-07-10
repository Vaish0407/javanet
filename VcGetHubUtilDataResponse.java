package system.score.vms.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class VcGetHubUtilDataResponse {


    @JsonProperty("hubId")
    private int hubId;

    @JsonProperty("utilisation")
    private String utilisation;

    @JsonProperty("errorCode")
    private String errorCode;

    @JsonProperty("errorMessage")
    private String errorMessage;


}
