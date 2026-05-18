package com.example.SpringLessonProject.form;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FormController {

	@RequestMapping("/sample")
	public String sample(Model model) {
		model.addAttribute("message", "Hello World"); 
		return "index";
	}
	
	@RequestMapping("/form")
	public String form(Model model, Form form) {
		model.addAttribute("title", "サンプルフォーム");
		return "form/input";
	}
	
	@RequestMapping("/confirm")
	public String confirm(Model model, Form form) {
		model.addAttribute("title","確認ページ");
		return "form/confirm";
	}

}

