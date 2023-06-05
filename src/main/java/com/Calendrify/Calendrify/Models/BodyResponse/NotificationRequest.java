package com.Calendrify.Calendrify.Models.BodyResponse;

import java.util.ArrayList;
import java.util.List;

public class NotificationRequest {
    private String heading;
    private String contain;
    private ArrayList<String> deviceTokens;

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getContain() {
        return contain;
    }

    public void setContain(String contain) {
        this.contain = contain;
    }

    public List<String> getDeviceTokens() {
        return deviceTokens;
    }

    public void setDeviceTokens(ArrayList<String> deviceTokens) {
        this.deviceTokens = deviceTokens;
    }
}
