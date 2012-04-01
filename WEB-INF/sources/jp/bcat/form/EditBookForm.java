package jp.bcat.form;
import jp.bcat.Book;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public class EditBookForm extends ActionForm implements Book {
	protected String bookId;
	protected String title;
	protected String author;
	protected String translator;
	protected String publisher;
	protected String publicationDate;
	protected String code;
	protected String status;
	protected String keyword;
	protected String memo;
	protected String dataCreator;
	protected String dataCreatedDate;

	protected String step;
	protected String beforeBookId;
	protected String password;

	public String getBookId() {
		return bookId;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public String getTranslator() {
		return translator;
	}
	public String getPublisher() {
		return publisher;
	}
	public String getPublicationDate() {
		return publicationDate;
	}
	public String getCode() {
		return code;
	}
	public String getStatus() {
		return status;
	}
	public String getKeyword() {
		return keyword;
	}
	public String getMemo() {
		return memo;
	}
	public String getDataCreator() {
		return dataCreator;
	}
	public String getDataCreatedDate() {
		return dataCreatedDate;
	}
	public void setBookId(String value) {
		bookId = value;
	}
	public String getStep() {
		return step;
	}
	public String getBeforeBookId() {
		return beforeBookId;
	}
	public String getPassword() {
		return password;
	}
	public void setTitle(String value) {
		title = value;
	}
	public void setAuthor(String value) {
		author = value;
	}
	public void setTranslator(String value) {
		translator = value;
	}
	public void setPublisher(String value) {
		publisher = value;
	}
	public void setPublicationDate(String value) {
		publicationDate = value;
	}
	public void setCode(String value) {
		code = value;
	}
	public void setStatus(String value) {
		status = value;
	}
	public void setKeyword(String value) {
		keyword = value;
	}
	public void setMemo(String value) {
		memo = value;
	}
	public void setDataCreator(String value) {
		dataCreator = value;
	}
	public void setDataCreatedDate(String value) {
		dataCreatedDate = value;
	}
	public void setStep(String value) {
		step = value;
	}
	public void setBeforeBookId(String value) {
		beforeBookId = value;
	}
	public void setPassword(String value) {
		password = value;
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		if (!"prepare".equals(step))
			return null;
		ActionErrors errors = new ActionErrors();
		if (title == null || title.length() == 0) {
			errors.add("title", new ActionError("error.add.title.required"));
		}
		if (author == null || author.length() == 0) {
			errors.add("author", new ActionError("error.add.author.required"));
		}
		if (publisher == null || publisher.length() == 0) {
			errors.add("publisher", new ActionError("error.add.publisher.required"));
		}
		if (publicationDate == null || publicationDate.length() == 0) {
			errors.add("publicationDate", new ActionError("error.add.publicationDate.required"));
		}
		return errors;
	}
}
