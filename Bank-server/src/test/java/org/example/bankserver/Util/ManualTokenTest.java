package org.example.bankserver.Util;

import org.junit.jupiter.api.Test;

public class ManualTokenTest {

    @Test
    public void testUserToken() {
        JwtUtil jwtUtil = new JwtUtil();
        String userToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.KMUFsIDTnFmyG3nMiGM6H9FNFUROf3wh7SmqJp-QV30";
        
        System.out.println("Testing Token: " + userToken);
        boolean isValid = jwtUtil.validateToken(userToken);
        
        if (isValid) {
            System.out.println("RESULT: TOKEN_IS_VALID");
        } else {
            System.out.println("RESULT: TOKEN_IS_INVALID");
        }
    }
}
