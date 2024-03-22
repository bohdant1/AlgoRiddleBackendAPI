package com.algoriddle.AlgoRiddleBackendApi.Security.Model;

import com.google.firebase.auth.FirebaseToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FirebaseAuthenticationToken implements Authentication {
    private final Object principal;
    private boolean authenticated = true; // Firebase token is assumed to be already authenticated

    public FirebaseAuthenticationToken(Object principal) {
        this.principal = principal;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        FirebaseToken decodedToken = (FirebaseToken) this.principal;
        Map<String, Object> claims = decodedToken.getClaims();

        // Extract roles from custom claims, assuming they are stored under the "roles" claim
        List<String> roles = (List<String>) claims.get("roles");

        // Map roles to Spring Security authorities
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (!isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return null;
    }
}
