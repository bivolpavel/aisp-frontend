package com.spf.psd2.frontend.client;

import com.spf.psd2.frontend.config.AuthServiceConfig;
import com.spf.psd2.frontend.exception.BadRequestException;
import com.spf.psd2.frontend.exception.InternalServerException;
import com.spf.psd2.frontend.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class AuthenticationClient {

    private RestTemplate restTemplate;

    private AuthServiceConfig authServiceConfig;

    public AuthenticationClient(RestTemplate restTemplate, AuthServiceConfig authServiceConfig) {
        this.restTemplate = restTemplate;
        this.authServiceConfig = authServiceConfig;
    }

    public JwtAuthenticationResponse authenticateUser(LoginRequest loginRequest){

        URI uri = UriComponentsBuilder.fromHttpUrl(authServiceConfig.getHost())
                .pathSegment(authServiceConfig.getSignInEndpoint()).build().toUri();

        try {
            ResponseEntity<JwtAuthenticationResponse> jwtAuthenticationResponse = restTemplate
                    .postForEntity(uri, loginRequest, JwtAuthenticationResponse.class);

            return jwtAuthenticationResponse.getBody();
        } catch (HttpStatusCodeException e) {
            System.out.println(e.getResponseBodyAsString());
            if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST) || e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                throw new BadRequestException("Bad credentials!");
            } else {
                throw new InternalServerException("Internal Error!");
            }
        }
    }

    public JwtAuthenticationResponse verifyCode(VerifyCodeRequest verifyCodeRequest){
        URI uri = UriComponentsBuilder.fromHttpUrl(authServiceConfig.getHost())
                .pathSegment(authServiceConfig.getVerifyCodeEndpoint()).build().toUri();

        try {
            ResponseEntity<JwtAuthenticationResponse> jwtAuthenticationResponse = restTemplate
                    .postForEntity(uri, verifyCodeRequest, JwtAuthenticationResponse.class);

            return jwtAuthenticationResponse.getBody();
        } catch (HttpStatusCodeException e) {
            System.out.println(e.getResponseBodyAsString());
            if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST) || e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                throw new BadRequestException("The code is invalid");
            } else {
                throw new InternalServerException("Internal Error!");
            }
        }
    }

    public SignupResponse createUser(SignUpRequest payload){
        URI uri = UriComponentsBuilder.fromHttpUrl(authServiceConfig.getHost())
                .pathSegment(authServiceConfig.getCreateUserEndpoint()).build().toUri();

        try {
            ResponseEntity<SignupResponse> signupResponse = restTemplate
                    .postForEntity(uri, payload, SignupResponse.class);

            return signupResponse.getBody();
        } catch (HttpStatusCodeException e) {
            System.out.println(e.getResponseBodyAsString());
            if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST) || e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                throw new BadRequestException("Bad credentials!");
            } else {
                throw new InternalServerException("Internal Error!");
            }
        }
    }
}
