package system.score.vms.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class VcConnectDataResponse {
    @JsonProperty("hubId")
    private int hubId;

    @JsonProperty("hubSerialNo")
    private String hubSerialNo;

    @JsonProperty("port")
    private int port;

    @JsonProperty("readOnly")
    private Boolean readOnly;

    @JsonProperty("connectedAt")
    private String connectedAt;

    @JsonProperty("connectionKey")
    private String connectionKey;

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

    public String getHubSerialNo() {
        return hubSerialNo;
    }

    public void setHubSerialNo(String hubSerialNo) {
        this.hubSerialNo = hubSerialNo;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    public String getConnectedAt() {
        return connectedAt;
    }

    public void setConnectedAt(String connectedAt) {
        this.connectedAt = connectedAt;
    }

    public String getConnectionKey() {
        return connectionKey;
    }

    public void setConnectionKey(String connectionKey) {
        this.connectionKey = connectionKey;
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
        return "VcConnectDataResponse{" +
                "hubId=" + hubId +
                ", hubSerialNo='" + hubSerialNo + '\'' +
                ", port=" + port +
                ", readOnly=" + readOnly +
                ", connectedAt='" + connectedAt + '\'' +
                ", connectionKey='" + connectionKey + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VcConnectDataResponse that = (VcConnectDataResponse) o;
        return hubId == that.hubId && port == that.port && Objects.equals(hubSerialNo, that.hubSerialNo) && Objects.equals(readOnly, that.readOnly) && Objects.equals(connectedAt, that.connectedAt) && Objects.equals(connectionKey, that.connectionKey) && Objects.equals(errorCode, that.errorCode) && Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hubId, hubSerialNo, port, readOnly, connectedAt, connectionKey, errorCode, errorMessage);
    }
}
