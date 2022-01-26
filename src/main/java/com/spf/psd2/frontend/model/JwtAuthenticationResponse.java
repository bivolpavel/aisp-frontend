package com.spf.psd2.frontend.model;

public class JwtAuthenticationResponse {

    public JwtAuthenticationResponse() {
    }

    public JwtAuthenticationResponse(String accessToken, boolean mfa) {
        this.accessToken = accessToken;
        this.mfa = mfa;
    }

    private String accessToken;
    private boolean mfa;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public boolean isMfa() {
        return mfa;
    }

    public void setMfa(boolean mfa) {
        this.mfa = mfa;
    }


}
