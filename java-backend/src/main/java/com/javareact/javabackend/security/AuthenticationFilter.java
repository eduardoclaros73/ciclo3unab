package com.javareact.javabackend.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javareact.javabackend.models.requests.UsuarioLoginRequestModel;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws AuthenticationException {
        try {
            UsuarioLoginRequestModel usuarioModel = new ObjectMapper().readValue(
                request.getInputStream(),
                UsuarioLoginRequestModel.class
            );
            return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    usuarioModel.getCorreo(),
                    usuarioModel.getPassword(),
                    new ArrayList<>()
                )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void successfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain,
        Authentication authentication
    ) throws IOException, ServletException {
        String correo = ((User) authentication.getPrincipal()).getUsername();

        String token = Jwts
            .builder()
            .setSubject(correo)
            .setExpiration(
                new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_DATE)
            )
            .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
            .compact();

        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader(
            SecurityConstants.HEADER_STRING,
            SecurityConstants.TOKEN_PREFIX + token
        );
    }

}
