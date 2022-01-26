package com.spf.psd2.frontend.web;

import com.spf.psd2.frontend.client.AccountsClient;
import com.spf.psd2.frontend.exception.UnauthorizedException;
import com.spf.psd2.frontend.model.AllAccountsResponse;
import com.spf.psd2.frontend.utils.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class AccountsController {

    private final Logger logger = LoggerFactory.getLogger(AccountsController.class);

    private final String ACCOUNTS_PAGE = "accounts";
    private final String ERROR_PAGE = "error";

    private final AccountsClient accountsClient;
    private final CookieUtils cookieUtils;

    public AccountsController(AccountsClient accountsClient, CookieUtils cookieUtils) {
        this.accountsClient = accountsClient;
        this.cookieUtils = cookieUtils;
    }

    @RequestMapping("/accounts")
    public String addBankAccount(Model model, HttpServletResponse response, HttpServletRequest req) {

        String authorizationCookie = cookieUtils.getAuthorizationCookie(req);
        Optional<AllAccountsResponse> accountsResponseOptional;

        try {
            accountsResponseOptional = accountsClient.getAllAccountBalances(authorizationCookie);
        } catch (UnauthorizedException e) {
            logger.error("User is unauthorized");
            cookieUtils.deleteAuthenticationCookie(response);
            return "login";
        }

        if (accountsResponseOptional.isPresent()) {

            AllAccountsResponse allAccountsResponse = accountsResponseOptional.get();
            model.addAttribute("accountBalanceResponseList", allAccountsResponse.getAccountBalanceResponses());
            model.addAttribute("amountByCurrencies", allAccountsResponse.getAmountByCurrencies());

            logger.debug("Put on model accountBalanceResponseList with size: {}, and amountByCurrencies with keySet size : {}",
                    allAccountsResponse.getAccountBalanceResponses().size(), allAccountsResponse.getAmountByCurrencies().size());

            return ACCOUNTS_PAGE;
        } else {
            logger.error("Display error page!");
            return ERROR_PAGE;
        }
    }
}