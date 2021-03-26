package com.zarconeg.carRentalRestApi.security;

public class SecurityConstants {

    // Alternativamente queste costanti potrebbero essere messe dentro ad application.yaml

    public static final String SECRET = "l£$nlkjBNMN(y89KJHK38lknLJ$%Jgy345";
    public static final long EXPIRATION_TIME = 900_000; // 15 mins
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/user";

}
