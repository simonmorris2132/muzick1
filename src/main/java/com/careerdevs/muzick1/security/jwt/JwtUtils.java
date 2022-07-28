package com.careerdevs.muzick1.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component
public class JwtUtils {
    
    private static Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${muzick.props.jwtSecret}")
    private String jwtSecret;

//    @Value("${muzick.props.jwtExpirationMs}")
//    private int jwtExpirationMs;

    public boolean validationJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (Error e) {
            logger.error("Error {}", e.getMessage());
        }
        return false;
    }

}
