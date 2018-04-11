package com.gobear.travelservice.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class TokenUtils {
    public static final String LOGIN_TOKEN = "LOGIN_TOKEN";
    public static final String LOGIN_KEY = "LOGIN_KEY";

    public static String generateToken(String subject) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, LOGIN_KEY);

        return builder.compact();
    }

    public static String getSubject(HttpServletRequest httpServletRequest) {
        String token = CookieUtils.getValue(httpServletRequest, LOGIN_TOKEN);
        if (token == null) return null;
        return Jwts.parser().setSigningKey(LOGIN_KEY).parseClaimsJws(token).getBody().getSubject();
    }
}
