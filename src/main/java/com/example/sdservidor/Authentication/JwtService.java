package com.example.sdservidor.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.mindrot.jbcrypt.BCrypt;
import com.example.sdservidor.Models.User;
import java.util.Date;

public class JwtService {
    private static final String SECRET_KEY = "AoT3QFTTEkj16rCby/TPVBWvfSQHL3GeEz3zVwEd6LDrQDT97sgDY8HJyxgnH79jupBWFOQ1+7fRPBLZfpuA2lwwHqTgk+NJcWQnDpHn31CVm63Or5c5gb4H7/eSIdd+7hf3v+0a5qVsnyxkHbcxXquqk9ezxrUe93cFppxH4/kF/kGBBamm3kuUVbdBUY39c4U3NRkzSO+XdGs69ssK5SPzshn01axCJoNXqqj+ytebuMwF8oI9+ZDqj/XsQ1CLnChbsL+HCl68ioTeoYU9PLrO4on+rNHGPI0Cx6HrVse7M3WQBPGzOd1TvRh9eWJrvQrP/hm6kOR7KrWKuyJzrQh7OoDxrweXFH8toXeQRD8=";

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public static boolean authenticate(User user, String password) {
        // Simulate retrieving the hashed password from a database
        String storedHashedPassword = user.getPassword();

        // Check if the provided password matches the stored hashed password
        return BCrypt.checkpw(password, storedHashedPassword);
    }

    public static String createJwt(String subject, boolean isAdm) {
        return Jwts.builder()
                .claim("user_id", subject)
                .claim("admin", isAdm)
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static long getUserIdFromJwt(String jwt) {
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(jwt);

        return Long.parseLong(claims.getBody().get("user_id", String.class));
    }

    public static void validateJwt(String jwt) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt);
        } catch (Exception e) {
            // Handle invalid JWT exception (e.g., expired, tampered, etc.)
            e.printStackTrace();
            System.out.println("Invalid JWT.");
        }
    }
}
