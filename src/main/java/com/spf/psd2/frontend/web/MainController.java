package com.spf.psd2.frontend.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Joe Grandja
 */
@Controller
public class MainController {

	private final Logger logger = LoggerFactory.getLogger(MainController.class);

	@RequestMapping("/")
	public String root() {
		return "redirect:/home";
	}

	@RequestMapping("/code")
	public String authenticationCode(Model model, @RequestParam(name = "username") String username) {
		model.addAttribute("username", username);
		return "authenticationCode";
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/user/index")
	public String userIndex() {
		return "user/index";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/accounts-page")
	public String accounts() {
		return "accounts";
	}

	@RequestMapping("/register")
	public String register() {
		return "register";
	}

	@RequestMapping("/add-bank-account-page")
	public String addBankAccount() {
		return "addNewAccount";
	}

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}
}
