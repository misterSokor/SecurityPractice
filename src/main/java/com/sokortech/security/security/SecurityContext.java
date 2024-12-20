package com.sokortech.security.security;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SecurityContext {
    private AuthLoginPasswordObjectToken  authLoginPasswordObject;
}
