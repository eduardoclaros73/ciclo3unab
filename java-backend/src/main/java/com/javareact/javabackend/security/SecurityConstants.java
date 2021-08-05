package com.javareact.javabackend.security;

import com.javareact.javabackend.shared.SpringApplicationContext;

public class SecurityConstants {

    public static final long EXPIRATION_DATE = 1209600000; // 14d * 24h * 60m * 60s * 1000m
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public static String getTokenSecret() {
        AppProperties applicationProperties = (AppProperties) SpringApplicationContext.getBean("applicationProperties");
        return applicationProperties.getTokenSecret();
    }
    
}
