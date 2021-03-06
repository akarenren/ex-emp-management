package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@Autowired
	private HttpSession session;
	
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
	 * @param form フォーム
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
	
	/**
	 * ログイン処理.
	 * 
	 * @param form
	 * @param model
	 * @return 従業員一覧画面(失敗すればログイン画面)
	 */
	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {
		Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());
		if(administrator == null) {
			model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です");
			return "administrator/login";
		}
		session.setAttribute("administratorName", administrator.getName());
		return "forward:/employee/showList";
		
 	}
	
	/**
	 * ログアウト処理.
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		
		return "forward:/";
	}
}
