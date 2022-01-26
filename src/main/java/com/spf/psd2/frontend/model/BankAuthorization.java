package com.spf.psd2.frontend.model;

public class BankAuthorization {
    private String authorizationUrl;

    public BankAuthorization() {
    }

    public BankAuthorization(String authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
    }

    public String getAuthorizationUrl() {
        return authorizationUrl;
    }

    public void setAuthorizationUrl(String authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
    }
}
