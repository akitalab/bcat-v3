package jp.bcat;

public interface Book {
	public String getBookId();
	public String getTitle();
	public String getAuthor();
	public String getTranslator();
	public String getPublisher();
	public String getPublicationDate();
	public String getCode();
	public String getStatus();
	public String getKeyword();
	public String getMemo();
	public String getDataCreator();
	public String getDataCreatedDate();
	public void setBookId(String value);
	public void setTitle(String value);
	public void setAuthor(String value);
	public void setTranslator(String value);
	public void setPublisher(String value);
	public void setPublicationDate(String value);
	public void setCode(String value);
	public void setStatus(String value);
	public void setKeyword(String value);
	public void setMemo(String value);
	public void setDataCreator(String value);
	public void setDataCreatedDate(String value);
}