package com.spf.psd2.frontend.client;

import com.spf.psd2.frontend.exception.UnauthorizedException;
import com.spf.psd2.frontend.model.AllAccountsResponse;
import com.spf.psd2.frontend.model.ProductSummaryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class ProductSummaryClient {

    private final Logger logger = LoggerFactory.getLogger(ProductSummaryClient.class);

    public static final String GET_PRODUCT_SUMMARY_URI = "/banks-integration-service/productSummary";

    private final RestTemplate restTemplate;

    @Value("${gateway.host}")
    private String gatewayHost;

    public ProductSummaryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<ProductSummaryResponse> getProductSummary(String userId, String authorizationHeader){

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizationHeader);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        try {
            ResponseEntity<ProductSummaryResponse> response = restTemplate
                    .exchange(gatewayHost.concat(GET_PRODUCT_SUMMARY_URI), HttpMethod.GET, entity, ProductSummaryResponse.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                logger.debug("Product summary information successfully retrieved: {}", response.getBody());
                return Optional.ofNullable(response.getBody());
            }
        } catch (HttpClientErrorException e) {
            logger.error("Exception occurred during calling get accounts call: ", e);
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                throw new UnauthorizedException();
            }
        } catch (Exception e) {
            logger.error("Exception occurred during calling get product summary call: ", e);
        }

        return Optional.empty();
    }
}
