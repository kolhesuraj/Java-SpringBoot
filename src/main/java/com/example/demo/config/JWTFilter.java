package com.example.demo.config;

import com.example.demo.services.JWTTokenService;
import com.example.demo.services.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTokenService jwtTokenService;

    @Autowired
    private ApplicationContext applicationContext;

    private static final Logger logger = LoggerFactory.getLogger(JWTFilter.class);


    // Method to lazily fetch the UserService bean from the ApplicationContext
    // This is done to avoid Circular Dependency issues
    private AuthenticationService getUserService() {
        return applicationContext.getBean(AuthenticationService.class);
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull  HttpServletResponse response, @NotNull FilterChain filterChain) throws IOException {
        try {
            String requestURI = request.getRequestURI();

            // Permit access to certain APIs without authentication
            if (isPermittedAPI(requestURI)) {
                filterChain.doFilter(request, response);
                return;
            }

            // Extracting token from the request header
            String authHeader = request.getHeader("Authorization");
            String token = null;
            String userName = null;

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("Please Authenticate");
                return;
            }

            // Extracting the token from the Authorization header
            token = authHeader.substring(7);

            // Extracting username from the token
            userName = jwtTokenService.extractUsername(token);

            // If username is extracted and there is no authentication in the current SecurityContext
            if (userName == null || SecurityContextHolder.getContext().getAuthentication() != null) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("Please Authenticate");
                return;
            }

            // Loading UserDetails by username extracted from the token
            UserDetails userDetails = getUserService().loadUserByUsername(userName);

            // Validating the token with loaded UserDetails
            if (!jwtTokenService.isTokenValid(token, userDetails)) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("Please Authenticate");
                return;
            }

            // Creating an authentication token using UserDetails
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            // Setting authentication details
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // Setting the authentication token in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authToken);
            // Set userId as request attribute
            request.setAttribute("userName", userDetails.getUsername());

            // Proceeding with the filter chain
            filterChain.doFilter(request, response);
        }catch (Exception e){
            logger.error("Error Occurred",e);
            handleException(e, response);
        }
    }

    private boolean isPermittedAPI(String requestURI) {
        // Add logic to check if the requestURI matches any APIs that should be permitted without authentication
        // For example:
        // return requestURI.equals("/health-check") || requestURI.startsWith("/public/");
        // You can adjust this method based on your specific URL patterns
        return requestURI.equals("/health-check") || requestURI.startsWith("/auth/");
    }

    private void handleException(Exception ex, ServletResponse response) throws IOException {
        // Log the exception or perform any other necessary operations

        // Set appropriate HTTP status and response content
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "An error occurred: " + ex.getMessage());

        httpResponse.getWriter().write(objectMapper.writeValueAsString(responseBody));
    }
}
