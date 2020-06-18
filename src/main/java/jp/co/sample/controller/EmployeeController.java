package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員用コントローラ.
 * 
 * @author ren.akase
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	/**
	 * フォームをインスタンス化.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public UpdateEmployeeForm setUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}

	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * 従業員情報一覧を取得.
	 * 
	 * @param model: モデル
	 * @return　従業員一覧画面
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList", employeeList);
		
		return "employee/list";
	}
	
	/**
	 * 従業員詳細を取得.
	 * 
	 * @param id:ID
	 * @param model:モデル
	 * @return　従業員詳細画面
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		
		model.addAttribute("employee", employee);
		
		
		return "employee/detail";
	}
	
	/**
	 * 扶養人数更新.
	 * 
	 * @param form:フォーム
	 * @return　就業員詳細画面
	 */
	@RequestMapping("/update")
	public String update(UpdateEmployeeForm form) {
		Employee employee = employeeService.showDetail(Integer.parseInt(form.getId()));
		employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
		
		employeeService.update(employee);
		
		return "redirect:/employee/showList";
		
	}
	
}
