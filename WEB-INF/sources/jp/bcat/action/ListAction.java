package jp.bcat.action;
import jp.bcat.*;
import java.util.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public class ListAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BookCatalog catalog = BookCatalog.getInstance();
		Book[] books = catalog.getBooks();
		request.setAttribute("books", Arrays.asList(books));
		return mapping.findForward("success");
	}
}
