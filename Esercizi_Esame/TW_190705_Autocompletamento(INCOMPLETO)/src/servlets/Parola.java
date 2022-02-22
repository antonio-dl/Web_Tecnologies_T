package servlets;

public class Parola {
	private String parola;
	private int usi;
	public Parola(String parola, int usi) {
		super();
		this.parola = parola;
		this.usi = usi;
	}
	public String getParola() {
		return parola;
	}
	public void setParola(String parola) {
		this.parola = parola;
	}
	public int getUsi() {
		return usi;
	}
	public void setUsi(int usi) {
		this.usi = usi;
	}
}
