package org.codemental.samples.webflux.java.http.adapter.model.outbound;

import java.time.LocalDateTime;

public class ReportRequest {
    
    private String information;
    private LocalDateTime timestamp;

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
