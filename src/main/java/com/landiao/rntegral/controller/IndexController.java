package com.landiao.rntegral.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@Value("${application.name}")
	private String appName;
	
	@RequestMapping("/index")
    public String department(Model model) {
		model.addAttribute("appName", appName);
        return "index";
    }
	
}
