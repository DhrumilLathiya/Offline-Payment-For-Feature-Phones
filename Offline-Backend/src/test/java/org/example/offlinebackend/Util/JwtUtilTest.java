package org.example.offlinebackend.Util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JwtUtilTest {

    @Test
    public void testGenerateToken() {
        JwtUtil jwtUtil = new JwtUtil();
        String token = jwtUtil.generateToken("OfflineBackend");
        Assertions.assertNotNull(token);
        Assertions.assertTrue(token.length() > 0);
        System.out.println("FINAL_TOKEN_START");
        System.out.println(token);
        System.out.println("FINAL_TOKEN_END");
    }
}
