package beans;

import java.io.Serializable;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private char abilita;
	private String messaggio = "";

	public User(String username, String password,char abilita) {
		this.username = username;
		this.password = password;
		this.abilita = abilita;
	}
	
	
	public String getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}



	public char getAbilita() {
		return abilita;
	}

	public void setAbilita(char abilita) {
		this.abilita = abilita;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean equals(User u) {
		if (this.getUsername().equals(u.getUsername()) && this.getPassword().equals(u.getPassword()))
			return true;
		return false;
	}

}
