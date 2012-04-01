package jp.bcat;
import java.security.Security;
import javax.security.auth.callback.*;
import javax.security.auth.login.*;

public class NetworkUserManager extends UserManager {
	public NetworkUserManager() {
		String loginConfigURL = getClass().getResource("LoginConfig.properties").toString();
		for (int i = 1;; i++) {
			String p = Security.getProperty("login.config.url." + i);
			if (p == null) {
				Security.setProperty("login.config.url." + i, loginConfigURL);
				break;
			} else if (p.equals(loginConfigURL))
				break;
		}
	}
	public User authenticate(final String userId, final String password) throws Exception {
		CallbackHandler handler = new CallbackHandler() {
			public void handle(Callback[] callbacks) throws UnsupportedCallbackException {
				for (int i = 0; i < callbacks.length; i++) {
					if (callbacks[i] instanceof NameCallback) {
						NameCallback nc = (NameCallback)callbacks[i];
						nc.setName(userId);
					} else if (callbacks[i] instanceof PasswordCallback) {
						PasswordCallback pc = (PasswordCallback)callbacks[i]; 
						pc.setPassword(password.toCharArray()); 
					} else { 
						throw(new UnsupportedCallbackException(callbacks[i], 
							"Callback class not supported")); 
					}
				}
			}
		};
		LoginContext context = new LoginContext("bcat", handler);
		try {
			context.login();
		} catch (FailedLoginException ex) {
			return null;
		}
		return new User(userId);
	}
}
