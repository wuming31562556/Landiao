package com.landiao.rntegral.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@Value("${application.name}")
	private String appName;
	
	@RequestMapping("/")
    public String login(Model model) {
		model.addAttribute("appName", appName);
        return "login";
    }
}
