package com.startup.superbug.security;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.startup.superbug.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticaitonManager;
    private SBUserDetailsService userDetailsService;

    private Gson gson;

    public JWTAuthenticationFilter(AuthenticationManager authManager, SBUserDetailsService userDetailsService, Gson gson) {
        this.authenticaitonManager = authManager;
        this.userDetailsService = userDetailsService;
        this.gson = gson;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticaitonManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String token = getToken(authResult);
        User user = ((SBUserDetails) authResult.getPrincipal()).getUser();
        try {
            TokenResult tokenResult = new TokenResult(token, user, Date.from(ZonedDateTime.now(ZoneOffset.UTC).plus(SecurityConstants.EXPIRATION_TIME, ChronoUnit.MILLIS).toInstant()));
            response.getWriter().write(gson.toJson(tokenResult));
            response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getToken(Authentication authResult) {
        ZonedDateTime dateTime = ZonedDateTime.now(ZoneOffset.UTC).plus(SecurityConstants.EXPIRATION_TIME, ChronoUnit.MILLIS);
        SBUserDetails userDetails = ((SBUserDetails) authResult.getPrincipal());
        Map<String, Object> claims = new HashMap<>();
        claims.put(SecurityConstants.USER_ID_KEY, userDetails.getUser().getUserId());
        claims.put(SecurityConstants.USER_NAME_KEY, userDetails.getUser().getEmail());
        return Jwts.builder().
                setSubject(userDetails.getUsername())
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(dateTime.toInstant()))
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)
                .compact();
    }

}
