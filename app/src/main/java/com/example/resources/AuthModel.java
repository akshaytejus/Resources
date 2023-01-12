package com.example.resources;

public class AuthModel {

    private String value;
    private String access_token;

    public AuthModel(String value, String access_token) {
        this.value = value;
        this.access_token = access_token;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
