package com.sokortech.security.security;

import java.io.IOException;
import java.util.Base64;
import com.sokortech.security.exception.BadCredentialException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BasicAuthenticationFilter extends HttpFilter {
    private static final String AUTHORIZATION_SCHEMA_BASIC = "basic";
    private static final int CUT_POINT_OF_WORD_BASIC = 6;

    private final AuthenticationManager authenticationManager;
    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain) throws IOException, ServletException {

        // 3. save the login and password to the Security Context
        // 4. in OrderController use info from the Security Context

        String url = request.getRequestURI();

        if (PublicAvailableEndpoints
                .getPublicEndpoints()
                .contains(url)) {

            chain.doFilter(request, response);
            return;
        }

        // 1. Extract the Authorization header from the request
        // 2. parse the header from format Base64 to get the login and password
        AuthLoginPasswordObjectToken parsedAuthToken = parseAuthToken(request);
        // check if it is valid
        if (!authenticationManager.isValidAuthentication(parsedAuthToken)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        SecurityContextHolder.getSecurityContext().setAuthLoginPasswordObject(parsedAuthToken);
        chain.doFilter(request, response);
    }

    private AuthLoginPasswordObjectToken parseAuthToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null) {
            return null;
        }

        header = header.trim(); // removes all spaces in the header from user

        // helps to ignore upper or lower cases in the word
        // this condition checks if there word basic (now does not matter if
        // it's case-sensitive
        if (!StringUtils.startsWithIgnoreCase(header,
                AUTHORIZATION_SCHEMA_BASIC)) {
            return null;
        }

        // this condition checks if the token after word 'basic' is present
        if (header.equalsIgnoreCase(AUTHORIZATION_SCHEMA_BASIC)) {
            throw new BadCredentialException("Empty basic authentication "
                                             + "token");
        }

        // takes only token itself without the word 'basic'
        String token = header.substring(CUT_POINT_OF_WORD_BASIC);
        //decoding token:
        byte[] decodedToken = Base64.getDecoder().decode(token);

        // we don't need an array of bytes this is why
        // converting it into a String by creating the object of new String
        String loginAndPassword = new String(decodedToken);

        // extract separately login and password
         // in case user forgot to use ':' sign
         int delimiter = loginAndPassword.indexOf(":");
         if (delimiter == -1) {
             throw new BadCredentialException("Invalid basic authentication "
                                              + "token");
         }

        String login = loginAndPassword.substring(0, delimiter);
        String password = loginAndPassword.substring(delimiter + 1);

        // create one object from these two principal and credential
        // (login and password)
        AuthLoginPasswordObjectToken objectToken =
                new AuthLoginPasswordObjectToken(login, password);
        return objectToken;
    }
}
