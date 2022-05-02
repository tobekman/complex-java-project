package se.iths.complexjavaproject.auth.jwt;

public class JwtResponse {
    public  final String accessToken;

    public JwtResponse(String accessToken) {
        this.accessToken = accessToken;

    }
}
