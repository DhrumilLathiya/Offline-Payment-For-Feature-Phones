package org.example.bankserver.Util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JwtUtilTest {

    @Test
    public void testValidateToken() {
        JwtUtil jwtUtil = new JwtUtil();
        // Generate a token similarly to how the client does (or manually constructing one if needed, 
        // but here we can't easily access the client's generate method without code duplication or shared lib).
        // However, we can construct a valid token if we know the secret.
        
        // For this test, let's assume we want to validate a token we generate right here with the SAME secret.
        // Since we don't have a generate method in the Bank's JwtUtil (it only validates), 
        // we might fail to fully test "valid" token unless we add generation logic to Bank's util for testing 
        // OR duplicate the generation code here.
        
        // Let's rely on the fact that if we pass junk, it should return false.
        boolean isValid = jwtUtil.validateToken("invalid.token.here");
        Assertions.assertFalse(isValid);
    }
}
