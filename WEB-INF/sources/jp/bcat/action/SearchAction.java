package jp.bcat.action;
import jp.bcat.*;
import java.util.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public class SearchAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DynaActionForm searchForm = (DynaActionForm)form;
		String word = (String)searchForm.get("word");
		String where = (String)searchForm.get("where");
		DatabaseBookCatalog catalog = (DatabaseBookCatalog)BookCatalog.getInstance();
		Book[] books;
		if (where == null || where.length() == 0)
			books = catalog.searchBooks(word);
		else
			books = catalog.searchBooks(word, where);
		request.setAttribute("books", Arrays.asList(books));
		return mapping.findForward("success");
	}
}