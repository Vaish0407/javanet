package system.score.vms.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class VcInitializeRequest {

    @JsonProperty("licenseKey")
    private String licenseKey;

    @JsonProperty("versionMajor")
    private int versionMajor;

    @JsonProperty("versionMinor")
    private int versionMinor;

    public String getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }

    public int getVersionMajor() {
        return versionMajor;
    }

    public void setVersionMajor(int versionMajor) {
        this.versionMajor = versionMajor;
    }

    public int getVersionMinor() {
        return versionMinor;
    }

    public void setVersionMinor(int versionMinor) {
        this.versionMinor = versionMinor;
    }

    @Override
    public String toString() {
        return "InitializeRequest{" +
                "licenseKey='" + licenseKey + '\'' +
                ", versionMajor=" + versionMajor +
                ", versionMinor=" + versionMinor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VcInitializeRequest that = (VcInitializeRequest) o;
        return versionMajor == that.versionMajor && versionMinor == that.versionMinor && Objects.equals(licenseKey, that.licenseKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licenseKey, versionMajor, versionMinor);
    }
}
