package com.example.SpringLessonProject.form;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.SpringLessonProject.dao.SampleDao;
import com.example.SpringLessonProject.entity.EntForm;

@Controller
public class FormController {

	private final SampleDao sampledao;
	public FormController(SampleDao sampledao) {
		this.sampledao = sampledao;
	}

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
	public String confirm(Model model, @Validated Form form, BindingResult result) {

		if(result.hasErrors()) {
			model.addAttribute("title","入力ページ");
			return "form/input";
		}

		model.addAttribute("title","確認ページ");
		return "form/confirm";
	}

	@RequestMapping ("/complete")
	public String complete(Form form, Model model){
		EntForm entform = new EntForm();
		entform.setName(form.getName1());
		sampledao.insertDb(entform);
		return "form/complete";
	}

	@RequestMapping("/view")
	public String view(Model model) {
		List<EntForm> list = sampledao.searchDb();
		model.addAttribute("dbList",list);
		model.addAttribute("title","一覧ページ");
		return "form/view";
	}

}

