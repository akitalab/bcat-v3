package jp.bcat.action;
import jp.bcat.*;
import jp.bcat.form.EditBookForm;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public class EditAction extends AuthorizedAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		try {
			if (getUser(request) == null)
				return forwardToLogin(request, mapping);

			EditBookForm bookForm = (EditBookForm) form;
			String step = bookForm.getStep();
			if ("input".equals(step)) {
				BookCatalog catalog = BookCatalog.getInstance();
				String bookId = bookForm.getBookId();
				Book book = catalog.getBook(bookId);
				bookForm.setBeforeBookId(book.getBookId());
				bookForm.setTitle(book.getTitle());
				bookForm.setAuthor(book.getAuthor());
				bookForm.setTranslator(book.getTranslator());
				bookForm.setPublisher(book.getPublisher());
				bookForm.setPublicationDate(book.getPublicationDate());
				bookForm.setCode(book.getCode());
				bookForm.setStatus(book.getStatus());
				bookForm.setKeyword(book.getKeyword());
				bookForm.setMemo(book.getMemo());
				bookForm.setDataCreator(book.getDataCreator());
				bookForm.setDataCreatedDate(book.getDataCreatedDate());
				return mapping.getInputForward();
			} else if ("prepare".equals(step)) {
				String password = bookForm.getPassword();
				bookForm.setPassword("");
				if (checkAdministrator(password)) {
					request.setAttribute("book", bookForm);
					bookForm.setPassword("");
					return mapping.findForward("confirmForm");
				}
				errors.add("", new ActionError("error.password.mismatch"));
			} else if ("confirm".equals(step)) {
				DatabaseBookCatalog catalog = BookCatalog.getInstance();
				catalog.updateBook(bookForm.getBeforeBookId(), bookForm);
				return mapping.findForward("success");
			} else
				return mapping.findForward("top");
		} catch (Exception ex) {
			errors.add("", new ActionError("error.sql.failure", ex));
		}
		if (!errors.isEmpty())
			saveErrors(request, errors);
		return mapping.getInputForward();
	}
}
