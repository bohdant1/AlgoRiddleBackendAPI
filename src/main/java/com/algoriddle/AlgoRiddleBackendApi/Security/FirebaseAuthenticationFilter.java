package com.algoriddle.AlgoRiddleBackendApi.Security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class FirebaseAuthenticationFilter implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();

        // Handle OPTIONS requests
        if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(request, response);
            return;
        }
        // Skip authentication for some endpoints
//        if () ){
//            chain.doFilter(request, response);
//            return ;
//        }

        String idToken = extractTokenFromHeader(httpRequest.getHeader("Authorization"));
        if (idToken != null) {
            try {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseToken decodedToken = firebaseAuth.verifyIdToken(idToken);
                httpRequest.setAttribute("userId", decodedToken.getUid());
                chain.doFilter(httpRequest, httpResponse);
            } catch (FirebaseAuthException e) {
                e.printStackTrace();
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } else {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private String extractTokenFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null; // or handle the absence of token in your application logic
    }

}