package jp.bcat.action;
import jp.bcat.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public class DeleteAction extends AuthorizedAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		try {
			User user = getUser(request);
			if (user == null)
				return forwardToLogin(request, mapping);

			DynaActionForm deleteForm = (DynaActionForm)form;
			BookCatalog catalog = BookCatalog.getInstance();
			String step = (String)deleteForm.get("step");
			String bookId = (String)deleteForm.get("bookId");
			if ("prepare".equals(step)) {
				Book book = catalog.getBook(bookId);
				if (book != null) {
					request.setAttribute("book", book);
					return mapping.findForward("confirmForm");
				}
				errors.add("", new ActionError("error.bookid.invalid"));
			} else if ("confirm".equals(step)) {
				String password = (String)deleteForm.get("password");
				deleteForm.set("password", "");
				if (checkAdministrator(password)) {
					catalog.deleteBook(bookId);
					deleteForm.set("password", "");
					return mapping.findForward("success");
				}
				errors.add("", new ActionError("error.password.mismatch"));
				saveErrors(request, errors);
				Book book = catalog.getBook(bookId);
				request.setAttribute("book", book);
				return mapping.findForward("confirmForm");
			}
		} catch (Exception ex) {
			errors.add("", new ActionError("error.sql.failure"));
		}
		if (!errors.isEmpty())
			saveErrors(request, errors);
		return mapping.findForward("failure");
	}
}
