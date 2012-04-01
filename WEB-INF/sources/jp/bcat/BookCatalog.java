package jp.bcat;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class BookCatalog {
	public abstract Book getBook(String no) throws Exception;
	public abstract Book[] getBooks() throws Exception;
	public abstract Book addBook(Book book) throws Exception;
	public abstract void deleteBook(String bookId) throws Exception;
	public abstract Book[] searchBooks(String word) throws Exception;

	protected String createUniqueBookId() throws Exception {
		Date now = new Date();
		String id;
		id = new SimpleDateFormat("yyyyMMddHH").format(now);
		while (getBook(id) != null) {
			int intId = Integer.parseInt(id);
			id = Integer.toString(intId + 1);
		}
		return id;
	}

	static DatabaseBookCatalog sharedInstance;
	static public DatabaseBookCatalog getInstance() {
		if (sharedInstance == null) {
			//sharedInstance = new FileBookCatalog();
			sharedInstance = new DatabaseBookCatalog();
		}
		return sharedInstance;
	}
}