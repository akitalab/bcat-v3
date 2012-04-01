package jp.bcat.action;
import jp.bcat.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public class TakeoutAction extends AuthorizedAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		try {
			User user = getUser(request);
			if (user == null)
				return forwardToLogin(request, mapping);

			DynaActionForm takeoutForm = (DynaActionForm)form;
			DatabaseBookCatalog catalog = BookCatalog.getInstance();
			String step = (String) takeoutForm.get("step");
			String bookId = (String) takeoutForm.get("bookId");
			if ("prepare".equals(step)) {
				Book book = catalog.getBook(bookId);
				if (book != null) {
					request.setAttribute("book", book);
					return mapping.findForward("confirmForm");
				}
				errors.add("", new ActionError("error.bookid.invalid"));
			} else if ("confirm".equals(step)) {
				catalog.takeoutBook(bookId, user.getUserId());
				Book book = catalog.getBook(bookId);
				request.setAttribute("book", book);
				return mapping.findForward("success");
			}
		} catch (Exception ex) {
			errors.add("", new ActionError("error.sql.failure", ex));
		}
		if (!errors.isEmpty())
			saveErrors(request, errors);
		return mapping.findForward("failure");
	}
}