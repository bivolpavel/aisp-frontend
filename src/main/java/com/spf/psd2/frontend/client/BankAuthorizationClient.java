package com.spf.psd2.frontend.client;

import com.spf.psd2.frontend.model.BankAuthorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;
import java.util.Optional;

@Component
public class BankAuthorizationClient {

    private final Logger logger = LoggerFactory.getLogger(BankAuthorizationClient.class);

    public static final String AUTHORIZATION_INITIALIZATION_URI = "/banks-integration-service/banks/authorization/url";
    public static final String OBTAIN_ACCESS_TOKEN_URI = "/banks-integration-service/banks/accessToken";

    private final RestTemplate restTemplate;

    @Value("${gateway.host}")
    private String gatewayHost;

    public BankAuthorizationClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<String> getBankAuthorizationUrl(String authorizationHeader) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizationHeader);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        try {
            ResponseEntity<BankAuthorization> response =
                    restTemplate.exchange(gatewayHost.concat(AUTHORIZATION_INITIALIZATION_URI), HttpMethod.POST, entity, BankAuthorization.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return Optional.of(Objects.requireNonNull(response.getBody()).getAuthorizationUrl());
            }

        } catch (Exception e) {
            logger.error("Exception occurred during calling authorization call: ", e);
        }

        return Optional.empty();
    }

    public void getCustomerAccessToken(String code, String state, String authorizationHeader){

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizationHeader);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        try {
            String url = UriComponentsBuilder.fromHttpUrl(gatewayHost.concat(OBTAIN_ACCESS_TOKEN_URI))
                    .queryParam("code", code)
                    .queryParam("state", state)
                    .encode()
                    .toUriString();

            ResponseEntity response = restTemplate.exchange(url, HttpMethod.POST, entity, ResponseEntity.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                logger.debug("AccessToken successfully saved.");
            }

        } catch (Exception e) {
            logger.error("Exception occurred during calling authorization call: ", e);
        }
    }
}
