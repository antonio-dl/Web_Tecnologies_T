package beans;

public class Prenotazione {
	private int camera;
	private int inizio;
	private int fine;
	
	public Prenotazione(int camera, int inizio, int fine) {
		super();
		this.camera = camera;
		this.inizio = inizio;
		this.fine = fine;
	}

	public int getCamera() {
		return camera;
	}

	public int getInizio() {
		return inizio;
	}

	public int getFine() {
		return fine;
	}

	public boolean doesCollide(int ini, int fin) {
		return !doesNotCollide(ini,fin);
		
	}

	private boolean doesNotCollide(int ini, int fin) {
		return ((ini>this.inizio && ini > this.fine)||(fin<this.inizio && fin < this.fine));
		
	}
	
	
	

}
