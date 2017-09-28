package com.landiao.rntegral.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmployeeController {
	
	@Value("${application.name}")
	private String appName;
	
	@RequestMapping("/employee")
    public String employee(Model model) {
		model.addAttribute("appName", appName);
        return "employee";
    }
	
}
