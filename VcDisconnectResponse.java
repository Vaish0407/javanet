package system.score.vms.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VcDisconnectResponse {

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("errorCode")
    private int errorCode;

    @JsonProperty("errorMessage")
    private String errorMessage;

    @JsonProperty("hubID")
    private int hubID;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getHubID() {
        return hubID;
    }

    public void setHubID(int hubID) {
        this.hubID = hubID;
    }

    @Override
    public String toString() {
        return "VliteDisconnectResponse{" +
                "success=" + success +
                ", errorCode=" + errorCode +
                ", errorMessage='" + errorMessage + '\'' +
                ", hubID=" + hubID +
                '}';
    }
}
