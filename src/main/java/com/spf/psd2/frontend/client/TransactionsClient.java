package com.spf.psd2.frontend.client;

import com.spf.psd2.frontend.exception.UnauthorizedException;
import com.spf.psd2.frontend.model.AllAccountsResponse;
import com.spf.psd2.frontend.model.AllAccountsTransactionsResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class TransactionsClient {

    private final Logger logger = LoggerFactory.getLogger(TransactionsClient.class);

    public static final String GET_TRANSACTIONS_URI = "/banks-integration-service/transactions";

    private final RestTemplate restTemplate;

    @Value("${gateway.host}")
    private String gatewayHost;

    public TransactionsClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<AllAccountsTransactionsResponses> getTransactionsInfo(String authorizationHeader){

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizationHeader);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        try {
            ResponseEntity<AllAccountsTransactionsResponses> response = restTemplate
                    .exchange(gatewayHost.concat(GET_TRANSACTIONS_URI), HttpMethod.GET, entity, AllAccountsTransactionsResponses.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                logger.debug("Transactions successfully retrieved: {}", response.getBody());
                return Optional.ofNullable(response.getBody());
            }
        } catch (HttpClientErrorException e) {
            logger.error("Exception occurred during calling get accounts call: ", e);
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                throw new UnauthorizedException();
            }
        } catch (Exception e) {
            logger.error("Exception occurred during calling get transactions call: ", e);
        }

        return Optional.empty();
    }

}
