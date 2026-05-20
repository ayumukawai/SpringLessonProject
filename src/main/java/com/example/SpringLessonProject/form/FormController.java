package com.example.SpringLessonProject.form;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping("/del/{id}")
	public String destory(@PathVariable Long id) {
		sampledao.deleteDb(id);
		return "redirect:/view";
	}

	@RequestMapping("/edit/{id}")
	public String editView(@PathVariable Long id, Model model) {

		// DBからデータを1件取ってくる(データ型はリスト)
		List<EntForm> list = sampledao.selectOne(id);

		// リスト形式のため、インデックス番号が0のオブジェクトを抽出
		EntForm entformdb = list.get(0);

		// 編集画面用のViewに、データ２つを送る
		model.addAttribute("form", entformdb);
		model.addAttribute("title", "編集ページ");
		return "form/edit";
	}

	@RequestMapping("/edit/{id}/exe")
	public String editExe(@PathVariable Long id, Model model, Form form) {
		// フォームの値をエンティティに入れ直し
		EntForm entform = new EntForm();
		System.out.println(form.getName1());// 取得できているかの確認
		entform.setName(form.getName1());
		// 更新の実行
		sampledao.updateDb(id,entform);
		// 一覧画面へリダイレクト
		return "redirect:/view";
	}

}

