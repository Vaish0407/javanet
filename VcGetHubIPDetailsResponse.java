package system.score.vms.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class VcGetHubIPDetailsResponse {

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("data")
    private VcGetHubIPDetailsDataResponse vcGetHubIPDetailsDataResponse;

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

    public VcGetHubIPDetailsDataResponse getVcGetHubIPDetailsDataResponse() {
        return vcGetHubIPDetailsDataResponse;
    }

    public void setVcGetHubIPDetailsDataResponse(VcGetHubIPDetailsDataResponse vcGetHubIPDetailsDataResponse) {
        this.vcGetHubIPDetailsDataResponse = vcGetHubIPDetailsDataResponse;
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
        return "VcGetHubIPDetailsResponse{" +
                "success=" + success +
                ", vcGetHubIPDetailsDataResponse=" + vcGetHubIPDetailsDataResponse +
                ", error='" + error + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VcGetHubIPDetailsResponse that = (VcGetHubIPDetailsResponse) o;
        return Objects.equals(success, that.success) && Objects.equals(vcGetHubIPDetailsDataResponse, that.vcGetHubIPDetailsDataResponse) && Objects.equals(error, that.error) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, vcGetHubIPDetailsDataResponse, error, timestamp);
    }
}
