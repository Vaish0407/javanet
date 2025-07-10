package system.score.vms.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class VcGetHubIPDetailsDataResponse {

    @JsonProperty("hubId")
    private int hubId;

    @JsonProperty("ipAddr")
    private String  ipAddr;

    @JsonProperty("subNetMask")
    private String subNetMask;

    @JsonProperty("gateway")
    private String gateway;

    @JsonProperty("hostName")
    private String hostName;

    @JsonProperty("domain")
    private String domain;

    @JsonProperty("dhcpStatus")
    private Boolean dhcpStatus;

    @JsonProperty("portNumber")
    private int portNumber;

    @JsonProperty("dnsServer1")
    private String dnsServer1;

    @JsonProperty("dnsServer2")
    private String dnsServer2;

    @JsonProperty("queriedAt")
    private String queriedAt;

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

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getSubNetMask() {
        return subNetMask;
    }

    public void setSubNetMask(String subNetMask) {
        this.subNetMask = subNetMask;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Boolean getDhcpStatus() {
        return dhcpStatus;
    }

    public void setDhcpStatus(Boolean dhcpStatus) {
        this.dhcpStatus = dhcpStatus;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public String getDnsServer1() {
        return dnsServer1;
    }

    public void setDnsServer1(String dnsServer1) {
        this.dnsServer1 = dnsServer1;
    }

    public String getDnsServer2() {
        return dnsServer2;
    }

    public void setDnsServer2(String dnsServer2) {
        this.dnsServer2 = dnsServer2;
    }

    public String getQueriedAt() {
        return queriedAt;
    }

    public void setQueriedAt(String queriedAt) {
        this.queriedAt = queriedAt;
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
        return "VcGetHubIPDetailsDataResponse{" +
                "hubId=" + hubId +
                ", ipAddr='" + ipAddr + '\'' +
                ", subNetMask='" + subNetMask + '\'' +
                ", gateway='" + gateway + '\'' +
                ", hostName='" + hostName + '\'' +
                ", domain='" + domain + '\'' +
                ", dhcpStatus=" + dhcpStatus +
                ", portNumber=" + portNumber +
                ", dnsServer1='" + dnsServer1 + '\'' +
                ", dnsServer2='" + dnsServer2 + '\'' +
                ", queriedAt='" + queriedAt + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VcGetHubIPDetailsDataResponse that = (VcGetHubIPDetailsDataResponse) o;
        return hubId == that.hubId && portNumber == that.portNumber && Objects.equals(ipAddr, that.ipAddr) && Objects.equals(subNetMask, that.subNetMask) && Objects.equals(gateway, that.gateway) && Objects.equals(hostName, that.hostName) && Objects.equals(domain, that.domain) && Objects.equals(dhcpStatus, that.dhcpStatus) && Objects.equals(dnsServer1, that.dnsServer1) && Objects.equals(dnsServer2, that.dnsServer2) && Objects.equals(queriedAt, that.queriedAt) && Objects.equals(errorCode, that.errorCode) && Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hubId, ipAddr, subNetMask, gateway, hostName, domain, dhcpStatus, portNumber, dnsServer1, dnsServer2, queriedAt, errorCode, errorMessage);
    }
}
