package com.javareact.javabackend.security;

import com.javareact.javabackend.services.UsuarioServiceInterface;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    
    private final UsuarioServiceInterface usuarioServiceInterface;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(
        UsuarioServiceInterface usuarioServiceInterface,
        BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.usuarioServiceInterface = usuarioServiceInterface;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .cors()
            .and()
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/usuarios")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .addFilter(this.getAuthenticationFilter())
            .addFilter(new AuthorizationFilter(authenticationManager()))
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(
        AuthenticationManagerBuilder authenticationManagerBuilder
    ) throws Exception {
        authenticationManagerBuilder
            .userDetailsService(usuarioServiceInterface)
            .passwordEncoder(bCryptPasswordEncoder);
    }

    public AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());

        filter.setFilterProcessesUrl("/usuarios/login");

        return filter;
    }

}
