package system.score.vms.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class VcGetHubUtilResponse {

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("data")
    private VcGetHubUtilDataResponse vcGetHubUtilDataResponse;

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

    public VcGetHubUtilDataResponse getVcGetHubUtilDataResponse() {
        return vcGetHubUtilDataResponse;
    }

    public void setVcGetHubUtilDataResponse(VcGetHubUtilDataResponse vcGetHubUtilDataResponse) {
        this.vcGetHubUtilDataResponse = vcGetHubUtilDataResponse;
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
        return "VcGetHubUtilResponse{" +
                "success=" + success +
                ", vcGetHubUtilDataResponse=" + vcGetHubUtilDataResponse +
                ", error='" + error + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VcGetHubUtilResponse that = (VcGetHubUtilResponse) o;
        return Objects.equals(success, that.success) && Objects.equals(vcGetHubUtilDataResponse, that.vcGetHubUtilDataResponse) && Objects.equals(error, that.error) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, vcGetHubUtilDataResponse, error, timestamp);
    }
}
