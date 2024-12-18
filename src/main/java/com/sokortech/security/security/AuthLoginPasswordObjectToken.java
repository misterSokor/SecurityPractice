package com.sokortech.security.security;

public class AuthLoginPasswordObjectToken implements AuthCredentialsObjectBuilder {
    private final String userName;
    private final String password;

    public AuthLoginPasswordObjectToken(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public Object getPrincipal() {
        return userName;
    }

    @Override
    public Object getCredentials() {
        return password;
    }
}
