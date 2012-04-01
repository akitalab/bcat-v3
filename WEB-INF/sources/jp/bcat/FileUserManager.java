package jp.bcat;
import java.util.Properties;

public class FileUserManager extends UserManager {
	Properties users;
	public FileUserManager() {
		users = new Properties();
		try {
			users.load(getClass().getResourceAsStream("Users.properties"));
		} catch (Exception ex) {
		}
	}
	public User authenticate(String userId, String password) throws Exception {
		if (password.equals(users.getProperty(userId)))
			return new User(userId);
		else
			return null;
	}
}
