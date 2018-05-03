package org.codemental.samples.webflux.java.jooq.adapter.model.inbound;

public class NotificationRequest {
    
    private String userName;
    private String operation;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
