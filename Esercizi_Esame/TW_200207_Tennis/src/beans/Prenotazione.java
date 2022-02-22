package beans;

import java.time.LocalDateTime;

public class Prenotazione {

	private User giocatore1;
	private User giocatore2;
	
	LocalDateTime orario;

	public Prenotazione(User giocatore1, LocalDateTime orario) {
		super();
		this.giocatore1 = giocatore1;
		this.orario = orario;
	}
	
	public void finalizePrenotazione(User giocatore2) {
		this.giocatore2 = giocatore2;
	}

	public User getGiocatore1() {
		return giocatore1;
	}

	public void setGiocatore1(User giocatore1) {
		this.giocatore1 = giocatore1;
	}

	public User getGiocatore2() {
		return giocatore2;
	}

	public void setGiocatore2(User giocatore2) {
		this.giocatore2 = giocatore2;
	}

	public LocalDateTime getOrario() {
		return orario;
	}

	public void setOrario(LocalDateTime orario) {
		this.orario = orario;
	}
	
	
}
