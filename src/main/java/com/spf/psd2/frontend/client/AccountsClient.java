package com.spf.psd2.frontend.client;

import com.spf.psd2.frontend.exception.UnauthorizedException;
import com.spf.psd2.frontend.model.AllAccountsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class AccountsClient {

    private final Logger logger = LoggerFactory.getLogger(AccountsClient.class);

    public static final String GET_ACCOUNTS_URI = "/banks-integration-service/accounts";

    private final RestTemplate restTemplate;

    @Value("${gateway.host}")
    private String gatewayHost;

    public AccountsClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<AllAccountsResponse> getAllAccountBalances(String authorizationHeader){
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", authorizationHeader);
            HttpEntity<String> entity = new HttpEntity<>(null, headers);

            ResponseEntity<AllAccountsResponse> response = restTemplate
                    .exchange(gatewayHost.concat(GET_ACCOUNTS_URI), HttpMethod.GET, entity, AllAccountsResponse.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                logger.debug("Accounts information successfully retrieved: {}", response.getBody());
                return Optional.ofNullable(response.getBody());
            }

        } catch (HttpClientErrorException e) {
            logger.error("Exception occurred during calling get accounts call: ", e);
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                throw new UnauthorizedException();
            }
        } catch (Exception e) {
            logger.error("Exception occurred during calling get accounts call: ", e);
        }

        return Optional.empty();
    }
}
