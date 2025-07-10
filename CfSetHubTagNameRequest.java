package system.score.vms.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CfSetHubTagNameRequest {
    @JsonProperty("hubId")
    private int hubId;

    @JsonProperty("tagName")
    private String tagName;

    public int getHubId() {
        return hubId;
    }

    public void setHubId(int hubId) {
        this.hubId = hubId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
