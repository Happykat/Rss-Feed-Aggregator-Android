package com.project.tsordat.rss_feed_aggregator.Model.Login;



public class LoginRequest {
    public String email;
    public String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
