package com.sokortech.security.security;

import java.util.List;
import lombok.Data;

@Data
public class PublicAvailableEndpoints {
    private static List<String> publicEndpoints = List.of (
            "/auth/login",
            "/auth/registration"
    );

    public static List<String> getPublicEndpoints() {
        return publicEndpoints;
    }
}
