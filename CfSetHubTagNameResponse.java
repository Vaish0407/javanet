package system.score.vms.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CfSetHubTagNameResponse {

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("error")
    private String error;

    @JsonProperty("timestamp")
    private String timestamp;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
