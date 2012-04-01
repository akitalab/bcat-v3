package jp.bcat.action;
import jp.bcat.*;
import java.util.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public class ListTakingoutAction extends AuthorizedAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = getUser(request);
		if (user == null)
			return forwardToLogin(request, mapping);

		DatabaseBookCatalog catalog = BookCatalog.getInstance();
		Book[] books = catalog.searchTakingoutBooks(user.getUserId());
		request.setAttribute("books", Arrays.asList(books));
		return mapping.findForward("success");
	}
}
