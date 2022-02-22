package beans;

public class UserSession {

	private User user;
	private boolean finito;
	private long whenFinito;
	
	
	public UserSession(User currentUser, boolean b) {
		this.user = currentUser;
		this.finito = b;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isFinito() {
		return finito;
	}
	public void setFinito(boolean finito) {
		this.finito = finito;
	}
	public long getWhenFinito() {
		return whenFinito;
	}
	public void setWhenFinito(long whenFinito) {
		this.whenFinito = whenFinito;
	}
	
	
}
