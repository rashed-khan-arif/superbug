package com.startup.superbug.security;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.startup.superbug.entity.User;

import java.util.Date;

public class TokenResult {
    @Expose
    @SerializedName("token")
    private String token;
    @Expose
    @SerializedName("user")
    private User user;
    @Expose
    @SerializedName("expirationTime")
    private Date expirationTime;

    public TokenResult(String token, User user, Date expirationTime) {
        this.token = token;
        this.user = user;
        this.expirationTime = expirationTime;
    }
}
