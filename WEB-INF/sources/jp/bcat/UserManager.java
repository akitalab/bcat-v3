package jp.bcat;

public abstract class UserManager {
	static UserManager sharedInstance;
	public static UserManager getInstance() {
		if (sharedInstance == null) {
			sharedInstance = new FileUserManager();
			//sharedInstance = new NetworkUserManager();
		}
		return sharedInstance;
	}
	public abstract User authenticate(String userId, String password) throws Exception;
}
