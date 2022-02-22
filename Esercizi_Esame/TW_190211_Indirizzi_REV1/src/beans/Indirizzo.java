package beans;

import java.io.Serializable;

public class Indirizzo implements Serializable{
	private String cognome;
	private String via;
	
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String indirizzo) {
		this.via = indirizzo;
	}
}
