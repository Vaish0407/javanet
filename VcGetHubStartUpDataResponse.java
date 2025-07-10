package system.score.vms.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class VcGetHubStartUpDataResponse {
    @JsonProperty("hubId")
    private int hubId;

    @JsonProperty("lastStart")
    private String lastStart;

    @JsonProperty("errorCode")
    private String errorCode;

    @JsonProperty("errorMessage")
    private String errorMessage;

    public int getHubId() {
        return hubId;
    }

    public void setHubId(int hubId) {
        this.hubId = hubId;
    }

    public String getLastStart() {
        return lastStart;
    }

    public void setLastStart(String lastStart) {
        this.lastStart = lastStart;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "VcGetHubStartUpDataResponse{" +
                "hubId=" + hubId +
                ", lastStart='" + lastStart + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VcGetHubStartUpDataResponse that = (VcGetHubStartUpDataResponse) o;
        return hubId == that.hubId && Objects.equals(lastStart, that.lastStart) && Objects.equals(errorCode, that.errorCode) && Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hubId, lastStart, errorCode, errorMessage);
    }
}
