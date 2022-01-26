package com.spf.psd2.frontend.model;

public class SignupResponse {

    private boolean mfa;
    private String secretImageUri;

    public boolean isMfa() {
        return mfa;
    }

    public void setMfa(boolean mfa) {
        this.mfa = mfa;
    }

    public String getSecretImageUri() {
        return secretImageUri;
    }

    public void setSecretImageUri(String secretImageUri) {
        this.secretImageUri = secretImageUri;
    }
}
