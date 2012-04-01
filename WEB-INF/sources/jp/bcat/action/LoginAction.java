package jp.bcat.action;
import jp.bcat.*;
import jp.bcat.form.LoginForm;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public class LoginAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		LoginForm loginForm = (LoginForm)form;
		String userId = loginForm.getUser();
		String password = loginForm.getPassword();
		loginForm.setPassword("");
		User user = null;
		try {
			user = UserManager.getInstance().authenticate(userId, password);
			if (user != null) {
				request.getSession().setAttribute("userObject", user);
				request.getSession().setAttribute("user", user.getUserId());
			} else
				errors.add("", new ActionError("error.password.mismatch"));
		} catch (Exception ex) {
			errors.add("", new ActionError("error.password.failure", ex.getMessage()));
		}
		if (!errors.isEmpty() || user == null) {
			saveErrors(request, errors);
			return mapping.getInputForward();
		}
		String next = loginForm.getNextAction();
		if (next != null && next.length() != 0) {
			return new RedirectingActionForward(next);
		}
		return mapping.findForward("success");
	}
}
