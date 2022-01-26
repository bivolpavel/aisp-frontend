package com.spf.psd2.frontend.web;

import com.spf.psd2.frontend.client.BankAuthorizationClient;
import com.spf.psd2.frontend.utils.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AddBankAccountController {

    private final Logger logger = LoggerFactory.getLogger(AddBankAccountController.class);

    private final BankAuthorizationClient bankAuthorizationClient;
    private final CookieUtils cookieUtils;

    public AddBankAccountController(BankAuthorizationClient bankAuthorizationClient, CookieUtils cookieUtils) {
        this.bankAuthorizationClient = bankAuthorizationClient;
        this.cookieUtils = cookieUtils;
    }

    @RequestMapping("/oauth/initiate-authorization")
    public String initiateAuthorization(HttpServletRequest req) {
        logger.debug("Initiate authorization flow!");
        String authorizationCookie = cookieUtils.getAuthorizationCookie(req);
        return "redirect:" + bankAuthorizationClient.getBankAuthorizationUrl(authorizationCookie).orElse("/error");
    }

    @RequestMapping("/outh/callback")
    public String redirectBack(@RequestParam String code, @RequestParam(required = false) String state, HttpServletRequest req){
        logger.debug("Receive redirect from bank with code: {}", code);
        String authorizationCookie = cookieUtils.getAuthorizationCookie(req);
        bankAuthorizationClient.getCustomerAccessToken(code, state, authorizationCookie);
        return "success";
    }
}
