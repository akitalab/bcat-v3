package jp.bcat;
import java.util.Date;

public class TakeoutRecord {
	String bookId;
	String user;
	Date takeoutDate;
	Date returnDate;

	public TakeoutRecord(String bookId, String user, Date takeoutDate, Date returnDate) {
		this.bookId = bookId;
		this.user = user;
		this.takeoutDate = takeoutDate;
		this.returnDate = returnDate;
	}
	public String getBookId() {
		return bookId;
	}
	public String getUser() {
		return user;
	}
	public Date getTakeoutDate() {
		return takeoutDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
}
