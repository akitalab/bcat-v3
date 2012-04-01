package jp.bcat.action;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public class LogoutAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("userObject");
		session.removeAttribute("user");
		return mapping.findForward("success");
	}
}
