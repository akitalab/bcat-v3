package jp.bcat;

public class User implements java.io.Serializable {
	String userId;
	public User(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}
}
