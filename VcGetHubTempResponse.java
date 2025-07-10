package system.score.vms.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class VcGetHubTempResponse {

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("data")
    private VcGetHubTempResponse vcConnectDataResponse;

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

    public VcGetHubTempResponse getVcConnectDataResponse() {
        return vcConnectDataResponse;
    }

    public void setVcConnectDataResponse(VcGetHubTempResponse vcConnectDataResponse) {
        this.vcConnectDataResponse = vcConnectDataResponse;
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

    @Override
    public String toString() {
        return "VcGetHubTempResponse{" +
                "success=" + success +
                ", vcConnectDataResponse=" + vcConnectDataResponse +
                ", error='" + error + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VcGetHubTempResponse that = (VcGetHubTempResponse) o;
        return Objects.equals(success, that.success) && Objects.equals(vcConnectDataResponse, that.vcConnectDataResponse) && Objects.equals(error, that.error) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, vcConnectDataResponse, error, timestamp);
    }
}
