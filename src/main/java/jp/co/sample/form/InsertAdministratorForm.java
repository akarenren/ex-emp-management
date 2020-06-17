package jp.co.sample.form;

/**
 * 管理者用フォーム
 * 
 * @author ren.akase
 *
 */
public class InsertAdministratorForm {

	/** 名前*/
	private String name;
	/** メールアドレス*/
	private String mailAddress;
	/** パスワード*/
	private String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "InsertAdministractorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ "]";
	}
	
	
	
	
	
}
