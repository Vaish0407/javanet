package system.score.vms.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class VcConnectRequest {
    @JsonProperty("ipAddress")
    private String ipAddress;

    @JsonProperty("port")
    private int port;

    @JsonProperty("password")
    private String password;

    @JsonProperty("readOnly")
    private Boolean readOnly;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    @Override
    public String toString() {
        return "VliteConnectRequest{" +
                "ipAddress='" + ipAddress + '\'' +
                ", port=" + port +
                ", password='" + password + '\'' +
                ", readOnly=" + readOnly +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VcConnectRequest that = (VcConnectRequest) o;
        return port == that.port && Objects.equals(ipAddress, that.ipAddress) && Objects.equals(password, that.password) && Objects.equals(readOnly, that.readOnly);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ipAddress, port, password, readOnly);
    }
}
