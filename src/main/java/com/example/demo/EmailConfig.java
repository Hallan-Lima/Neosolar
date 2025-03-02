package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.mail")
public class EmailConfig {

    private String host;
    private String to;
    private String subject;
    private String body;
    private int port;
    private String username;
    private String password;
    private String nameCsv;
    private boolean auth;
    private boolean starttlsEnabled;

    public String getHost() {
        return host;
    }

    public String getBody() {
        return body;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAuth() {
        return auth;
    }

    public boolean isStarttlsEnabled() {
        return starttlsEnabled;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public void setStarttlsEnabled(boolean starttlsEnabled) {
        this.starttlsEnabled = starttlsEnabled;
    }

    public String getNameCsv() {
        return nameCsv;
    }
    public void setNameCsv(String nameCsv) {
        this.nameCsv = nameCsv;
    }
}