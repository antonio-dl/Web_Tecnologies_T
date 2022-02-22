package beans;


public class Campo {

	private int id;
	private Prenotazione prenotazione;

	public Campo(int id) {
		super();
		this.id = id;

	}

	public int getId() {
		return id;
	}

	public Prenotazione getPrenotazione() {
		return prenotazione;
	}

	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}

	public boolean isPrenotabile() {
		if (prenotazione == null)
			return true;
		return false;
	}

	public void eliminaPrenotazione() {
		this.prenotazione = null;
		return;

	}

	public boolean isGiocando() {
		if (prenotazione != null && prenotazione.getGiocatore2() != null)
			return true;
		return false;
	}

}
