package com.gk.jwt;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.util.Calendar;



import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * Hello world!
 *
 */
public class App {
    static String key = "vq_3TOSbrUzpGPHFEwjmeoE_Zu3-wU4vaeEvjQzUHXwIefy8bDuMav6OzUiEXhjLX5JRkGhds3lNGR3CSZgartIKWv5Vrc7F2YcBcgzrpO06kVcewRMjdhrPYfUfO6QklAOSCcPq4RUhEvkGEwbAw3awclve1KuhpX6fOIInP8Gp8hrFDd_neBR3AY03JrZpezBdQoE24UHgAlHGb2UZ2KKjl3rLDMPh9HecjTiga3SbdcrhTAOYHYb4LwCSrThrHSyZFBxzTwQMS0NEyKV7_-ADrFunf9cuVcGpQZkvdwODl4tY-l2sd3WpoD_gMDpoFJVojjzF07ovrfntM4o8Bw";

    public static void main(String[] args) {
        System.out.println("Hello World!");

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IllNRUxIVDBndmIwbXhvU0RvWWZvbWpxZmpZVSIsImtpZCI6IllNRUxIVDBndmIwbXhvU0RvWWZvbWpxZmpZVSJ9.eyJhdWQiOiIwMDAwMDAwMi0wMDAwLTAwMDAtYzAwMC0wMDAwMDAwMDAwMDAiLCJpc3MiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC9lYTgwOTUyZS1hNDc2LTQyZDQtYWFmNC01NDU3ODUyYjBmN2UvIiwiaWF0IjoxNTg2NjAxOTcyLCJuYmYiOjE1ODY2MDE5NzIsImV4cCI6MTU4NjYwNTg3MiwiYWlvIjoiNDJkZ1lGaGgvaVF3YmhudkFpV3Y2TTkvQzFVTUFBPT0iLCJhcHBpZCI6Ijg1YWNmOGQ3LTY0ODItNDUzMi1hMWM0LTIyNzY5MGNhMDE5NiIsImFwcGlkYWNyIjoiMSIsImlkcCI6Imh0dHBzOi8vc3RzLndpbmRvd3MubmV0L2VhODA5NTJlLWE0NzYtNDJkNC1hYWY0LTU0NTc4NTJiMGY3ZS8iLCJvaWQiOiI2Mjg1ZWNkNC04YzljLTQzNWUtOWNmYy0zOTE1YmQwNDA1OWIiLCJzdWIiOiI2Mjg1ZWNkNC04YzljLTQzNWUtOWNmYy0zOTE1YmQwNDA1OWIiLCJ0ZW5hbnRfcmVnaW9uX3Njb3BlIjoiRVUiLCJ0aWQiOiJlYTgwOTUyZS1hNDc2LTQyZDQtYWFmNC01NDU3ODUyYjBmN2UiLCJ1dGkiOiJ6ck1nX1NFdzBFMjZUZERhSG9BRkFBIiwidmVyIjoiMS4wIn0.cLizKhNpknvovdvIUUnQ3t6-ykQRT2q7cb9X6mqhKuA7ZFDsl_oiegehWRdRLIScLgl9_X5xuQKTmws_uGK9Rhd_MX8hfjY7EyN2cV1w7_9n_ke2psgk4kyw5mGEFXeN6aQRGKV3oNaNPb4ChS7Vgm4aTlgnUeEgVM_77qrIkYkYdOO839uzXSrGpqR95fu1NM_T-W4Wtvj0Pay4wAkLCdJH6chRBUw1tOm8SkJtmGDyasL5SZAT8-94L6202trI5NfIQY6nw2QPAd6atpI2XLbIRSIWtJryjjeml8vGeew2nwTobTiWP4wLcpCMv8A6SozmEECTroG4VXMpk6XH9Q";

        try {
            validateJWT(token);
        } catch (JwkException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void validateJWT(String token) throws JwkException, MalformedURLException {
 
        //This line will throw an exception if it is not a signed JWS (as expected)
       /* Claims claims = Jwts.parser()         
           .setSigningKey(new SecretKeySpec(key.getBytes(), "RSA"))
           .parseClaimsJws(jwt).getBody();
        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());*/
        //URL url = new URL("https://login.microsoftonline.com/ea80952e-a476-42d4-aaf4-5457852b0f7e/discovery/keys?appid=85acf8d7-6482-4532-a1c4-227690ca0196");
        URL url = new URL("https://login.microsoftonline.com/common/discovery/v2.0/keys");
        DecodedJWT jwt = JWT.decode(token);
JwkProvider provider = new UrlJwkProvider(url);
Jwk jwk = provider.get(jwt.getKeyId());
Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
//Verify signature
algorithm.verify(jwt);

System.out.println("kid: "+ jwt.getKeyId());

System.out.println("Public key"+jwk.getPublicKey().toString());

//Check expiration.
if (jwt.getExpiresAt().before(Calendar.getInstance().getTime())) {
    throw new RuntimeException("Exired token!");
  }

  //Use the token claims
  System.out.println(jwt.getIssuer());
  System.out.println(jwt.getAudience());
    }
}
