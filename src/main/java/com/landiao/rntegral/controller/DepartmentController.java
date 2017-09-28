package com.landiao.rntegral.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.landiao.rntegral.entity.Department;
import com.landiao.rntegral.repository.DepartmentRepository;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentRepository dao;

	@Value("${application.name}")
	private String appName;
	
	@RequestMapping("/list")
    public String department(Model model) {
		model.addAttribute("appName", appName);
		model.addAttribute("departments", dao.findAll());
        return "department";
    }
	
	@RequestMapping("/add")
	@PostMapping
	@ResponseBody
	public Department save(@RequestBody Department department) {
		dao.save(department);
		return department;
	}  
	
}
