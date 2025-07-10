package system.score.vms.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ErrorResponse {

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("error")
    private String error;

    @JsonProperty("timestamp")
    private String timestamp;

    public ErrorResponse(){}

    public ErrorResponse(Boolean success, String error, String timestamp) {
        this.success = success;
        this.error = error;
        this.timestamp = timestamp;
    }

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

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "success=" + success +
                ", error=" + error +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ErrorResponse that = (ErrorResponse) o;
        return Objects.equals(success, that.success) && Objects.equals(error, that.error) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, error, timestamp);
    }
}
