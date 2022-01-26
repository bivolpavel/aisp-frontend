package com.spf.psd2.frontend.web;

import com.spf.psd2.frontend.client.TransactionsClient;
import com.spf.psd2.frontend.exception.UnauthorizedException;
import com.spf.psd2.frontend.utils.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TransactionsController {

    private final Logger logger = LoggerFactory.getLogger(TransactionsController.class);

    private final TransactionsClient transactionsClient;

    private final CookieUtils cookieUtils;

    public TransactionsController(TransactionsClient transactionsClient, CookieUtils cookieUtils) {
        this.transactionsClient = transactionsClient;
        this.cookieUtils = cookieUtils;
    }

    @RequestMapping("/transactions")
    public String transactions(Model model, HttpServletResponse response, HttpServletRequest req) {

        try {
            String authorizationCookie = cookieUtils.getAuthorizationCookie(req);
            transactionsClient.getTransactionsInfo(authorizationCookie)
                    .ifPresent(allAccountsTransactions ->
                            model.addAttribute("allAccountsTransactions", allAccountsTransactions));
        } catch (UnauthorizedException e) {
            logger.error("User is unauthorized");
            cookieUtils.deleteAuthenticationCookie(response);
            return "login";
        }

        return "transactions";
    }
}
