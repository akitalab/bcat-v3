package jp.bcat.action;
import jp.bcat.*;
import jp.bcat.form.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public class AddAction extends AuthorizedAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		try {
			User user = getUser(request);
			if (user == null)
				return forwardToLogin(request, mapping);

			EditBookForm bookForm = (EditBookForm)form;
			String step = bookForm.getStep();
			if ("prepare".equals(step)) {
				String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				bookForm.setDataCreatedDate(now);
				bookForm.setDataCreator(user.getUserId());
				request.setAttribute("book", bookForm);
        			return mapping.findForward("confirmForm");
			} else if ("confirm".equals(step)) {
				BookCatalog catalog = BookCatalog.getInstance();
				Book book = catalog.addBook(bookForm);
				request.setAttribute("book", book);
				request.getSession().removeAttribute(mapping.getAttribute());
				return mapping.findForward("success");
			}
		} catch (Exception ex) {
    			errors.add("", new ActionError("error.sql.failure", ex));
		}
		if (!errors.isEmpty())
			saveErrors(request, errors);
		return mapping.getInputForward();
	}
}
