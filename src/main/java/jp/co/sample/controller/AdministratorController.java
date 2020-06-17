package jp.co.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者を操作するコントローラ.
 * 
 * @author ren.akase
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;
	
	/**
	 * InsertAdministratorFormをインスタンス化する.
	 * 
	 * @return　InsertAdministratorFormのインスタンス
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	
	/**
	 * LoginFormをインスタンス化する.
	 * 
	 * @return LoginFormのインスタンス
	 */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	/**
	 * 管理者登録画面用.
	 * 
	 * @return　管理者登録画面
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	
	
	/**
	 * 管理者情報を登録する.
	 * 
	 * @param form
	 * @return ログイン画面
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		
		administratorService.insert(administrator);

		return "redirect:/";
	}
	
	/**
	 * ログイン画面を表示する.
	 * 
	 * @return　ログイン画面
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}
}
