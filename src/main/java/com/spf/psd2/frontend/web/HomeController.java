package com.spf.psd2.frontend.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spf.psd2.frontend.client.ProductSummaryClient;
import com.spf.psd2.frontend.exception.UnauthorizedException;
import com.spf.psd2.frontend.model.ProductSummaryResponse;
import com.spf.psd2.frontend.utils.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Controller
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final ProductSummaryClient productSummaryClient;

    private final CookieUtils cookieUtils;

    public HomeController(ProductSummaryClient productSummaryClient, CookieUtils cookieUtils) {
        this.productSummaryClient = productSummaryClient;
        this.cookieUtils = cookieUtils;
    }

    @RequestMapping("/home")
    public String home(Model model, HttpServletResponse response, HttpServletRequest req) throws JsonProcessingException {

        Optional<ProductSummaryResponse> productSummaryResponse;
        String authorizationCookie = cookieUtils.getAuthorizationCookie(req);

        try {
            productSummaryResponse = productSummaryClient.getProductSummary(null, authorizationCookie);
        } catch (UnauthorizedException e) {
            logger.error("User is unauthorized");
            cookieUtils.deleteAuthenticationCookie(response);
            return "login";
        }

        if (productSummaryResponse.isPresent()) {
            System.out.println(new ObjectMapper().writeValueAsString(productSummaryResponse));
            ProductSummaryResponse productSummary = productSummaryResponse.get();
            Set<String> months = productSummary.getMonthsSpending().keySet();
            Collection<Double> amounts = productSummary.getMonthsSpending().values();

            model.addAttribute("productSummary", productSummary);
            model.addAttribute("months", new ObjectMapper().writeValueAsString(months));
            model.addAttribute("amounts", new ObjectMapper().writeValueAsString(amounts));
            return "home";
        } else {
            return "home";
        }
    }
}
