package jp.bcat.action;
import jp.bcat.*;
import jp.bcat.form.LoginForm;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public abstract class AuthorizedAction extends Action {
	public User getUser(HttpServletRequest request) {
		return (User)request.getSession().getAttribute("userObject");
	}

	public ActionForward forwardToLogin(HttpServletRequest request, ActionMapping mapping) {
		String contextPath = request.getRequestURI().substring(request.getContextPath().length());
		LoginForm form = new LoginForm();
		form.setNextAction(contextPath);
		request.setAttribute("loginForm", form);
		return mapping.findForward("login");
	}

	public boolean checkAdministrator(String password) {
		return Globals.ADMIN_PASSWORD.equals(password);
	}
}
