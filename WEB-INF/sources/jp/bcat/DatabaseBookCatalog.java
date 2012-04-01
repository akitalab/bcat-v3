package jp.bcat;
import java.sql.*;
import java.util.Vector;

public class DatabaseBookCatalog extends BookCatalog {
	Connection connection;

	public DatabaseBookCatalog() {
		try {
			connect();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected boolean connect() throws SQLException {
		if (connection != null) {
			if (connection.getWarnings() == null)
				return true;
			disconnect();
		}
		DriverManager.registerDriver(new org.postgresql.Driver());
		connection = DriverManager.getConnection(Globals.DATABASE_URL, Globals.DATABASE_USER, Globals.DATABASE_PASSWORD);
		if (connection.getWarnings() == null)
			return true;
		disconnect();
		return false;
	}

	protected void disconnect() {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			connection = null;
		}
	}

	public Book getBook(String bookId) throws SQLException {
		Book book = null;
		connect();
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM bookcatalog WHERE bookid = ?");
		statement.setString(1, bookId);
		ResultSet result = statement.executeQuery();
		if (result.next()) 
			book = createBook(result);
		statement.close();
		return book;
	}

	public synchronized Book addBook(Book book) throws Exception {
		connect();
		String bookId = book.getBookId();
		if (bookId == null || bookId.length() == 0 || getBook(bookId) != null)
			bookId = createUniqueBookId();
		PreparedStatement statement = connection.prepareStatement("INSERT INTO bookcatalog (bookid,title,author,translator,publisher,publicationdate,code,status,keyword,memo,datacreator,datacreateddate) VALUES (?,?,?,?,?,to_date(?,'YYYY-MM-DD'),?,?,?,?,?,to_timestamp(?,'YYYY-MM-DD HH24:MI:SS'))");
		statement.setString(1, bookId);
		statement.setString(2, book.getTitle());
		statement.setString(3, book.getAuthor());
		statement.setString(4, book.getTranslator());
		statement.setString(5, book.getPublisher());
		statement.setString(6, book.getPublicationDate());
		statement.setString(7, book.getCode());
		statement.setString(8, book.getStatus());
		statement.setString(9, book.getKeyword());
		statement.setString(10, book.getMemo());
		statement.setString(11, book.getDataCreator());
		statement.setString(12, book.getDataCreatedDate());
		statement.executeUpdate();
		statement.close();
		return getBook(bookId);
	}

	public void deleteBook(String bookId) throws SQLException {
		connect();
		PreparedStatement statement = connection.prepareStatement("DELETE FROM bookcatalog WHERE bookid = ?");
		statement.setString(1, bookId);
		statement.executeUpdate();
	}
   
	public void updateBook(String bookId, Book book) throws SQLException {
		connect();
		PreparedStatement statement = connection.prepareStatement("UPDATE bookcatalog SET bookid=?,title=?,author=?,translator=?,publisher=?,publicationdate=?,code=?,status=?,keyword=?,memo=?,datacreator=?,datacreateddate=? WHERE bookid=?");
		statement.setString(1, book.getBookId());
		statement.setString(2, book.getTitle());
		statement.setString(3, book.getAuthor());
		statement.setString(4, book.getTranslator());
		statement.setString(5, book.getPublisher());
		statement.setString(6, book.getPublicationDate());
		statement.setString(7, book.getCode());
		statement.setString(8, book.getStatus());
		statement.setString(9, book.getKeyword());
		statement.setString(10, book.getMemo());
		statement.setString(11, book.getDataCreator());
		statement.setString(12, book.getDataCreatedDate());
		statement.setString(13, bookId);
		statement.executeUpdate();
		statement.close();

		statement = connection.prepareStatement("UPDATE takeoutrecords SET bookid=? WHERE bookid=?");
		statement.setString(1, book.getBookId());
		statement.setString(2, bookId);
		statement.executeUpdate();
		statement.close();
	}
	
	public Book[] searchBooks(String word) throws SQLException {
		Book [] bookArray = null;
		connect();
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM bookcatalog WHERE bookid LIKE ? OR UPPER(title) LIKE ? OR UPPER(author) LIKE ? OR UPPER(translator) LIKE ? OR UPPER(publisher) LIKE ? OR UPPER(code) LIKE ? OR UPPER(status) LIKE ? OR UPPER(keyword) LIKE ? OR UPPER(memo) LIKE ? OR UPPER(datacreator) LIKE ? ORDER BY datacreateddate DESC");
		String pattern = "%" + word.toUpperCase() + "%";
		for (int i = 1; i <= 10; i++)
			statement.setString(i, pattern);
		ResultSet result = statement.executeQuery();
		bookArray = createBooks(result);
		statement.close();
		return bookArray;
	}

	public Book[] searchBooks(String word, String where) throws SQLException {
		connect();
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM bookcatalog WHERE UPPER(" + where + ") LIKE ? ORDER BY datacreateddate DESC");
		String pattern = "%" + word.toUpperCase() + "%";
		statement.setString(1, pattern);
		ResultSet result = statement.executeQuery();
		Book [] bookArray = createBooks(result);
		statement.close();
		return bookArray;
	}

	public Book[] getBooks() throws SQLException {
		connect();
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM bookcatalog ORDER BY datacreateddate DESC");
		Book [] bookArray = createBooks(result);
		statement.close();
		return bookArray;
	}

	protected Book createBook(ResultSet result) throws SQLException {
		Book book = new BookImpl();
		book.setBookId(result.getString("bookid"));
		book.setTitle(result.getString("title"));
		book.setAuthor(result.getString("author"));
		book.setTranslator(result.getString("translator"));
		book.setPublicationDate(result.getTimestamp("publicationdate").toString().substring(0,10));
		book.setPublisher(result.getString("publisher"));
		book.setCode(result.getString("code"));
		book.setStatus(result.getString("status"));
		book.setKeyword(result.getString("keyword"));
		book.setMemo(result.getString("memo"));
		book.setDataCreatedDate(result.getTimestamp("datacreateddate").toString().substring(0,16));
		book.setDataCreator(result.getString("datacreator"));
		return book;
	}

	protected Book [] createBooks(ResultSet result) throws SQLException {
		Vector books = new Vector();
		while (result.next()) 
			books.add(createBook(result));
		Book [] bookArray = new Book[books.size()];
		books.toArray(bookArray);
		return bookArray;
	}

	public void takeoutBook(String bookId, String user) throws SQLException {
		connect();
		PreparedStatement statement = connection.prepareStatement("DELETE FROM takeoutrecords WHERE bookid = ?");
		statement.setString(1, bookId);
		statement.executeUpdate();
		statement.close();

		statement = connection.prepareStatement("INSERT INTO takeoutrecords (bookid,userid,takeoutdate) VALUES (?,?,?)");
		statement.setString(1, bookId);
		statement.setString(2, user);
		statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
		statement.executeUpdate();
		statement.close();

		statement = connection.prepareStatement("UPDATE bookcatalog SET status = 'out' WHERE bookid = ?");
		statement.setString(1, bookId);
		statement.executeUpdate();
		statement.close();
	}

	public void returnBook(String bookId, String user) throws SQLException {
		connect();
		PreparedStatement statement = connection.prepareStatement("UPDATE takeoutrecords SET userid = ?, returndate = ? WHERE bookid = ?");
		statement.setString(1, user);
		statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
		statement.setString(3, bookId);
		statement.executeUpdate();
		statement.close();

		statement = connection.prepareStatement("UPDATE bookcatalog SET status = '' WHERE bookid = ? AND status = 'out'");
		statement.setString(1, bookId);
		statement.executeUpdate();
		statement.close();
	}

	public TakeoutRecord getTakeoutRecord(String bookId) throws Exception {
		TakeoutRecord record = null;
		connect();
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM takeoutrecords WHERE bookid = ?");
		statement.setString(1, bookId);
		ResultSet result = statement.executeQuery();
		if (result.next()) 
			record = new TakeoutRecord(result.getString("bookid"),
		result.getString("userid"),
		result.getTimestamp("takeoutDate"),
		result.getTimestamp("returnDate"));
		return record;
	}

	public Book [] searchTakingoutBooks(String user) throws Exception {
		connect();
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM bookcatalog,takeoutrecords WHERE bookcatalog.bookid = takeoutrecords.bookid AND userid = ? AND returndate IS NULL ORDER BY takeoutrecords.takeoutdate DESC");
		statement.setString(1, user);
		ResultSet result = statement.executeQuery();
		Book[] bookArray = createBooks(result);
		statement.close();
		return bookArray;
	}
}
