package jp.bcat.form;
import org.apache.struts.action.*;

public class LoginForm extends ActionForm {
	String user;
	String password;
	String nextAction;
	public void setUser(String value) {
		user = value;
	}
	public String getUser() {
		return user;
	}
	public void setPassword(String value) {
		password = value;
	}
	public String getPassword() {
		return password;
	}
	public void setNextAction(String value) {
		nextAction = value;
	}
	public String getNextAction() {
		return nextAction;
	}
}
