package beans;

public class Richiesta {
	int idAlbergo;
	int din;
	int dout;
	public Richiesta(int idAlbergo, int din, int dout) {
		super();
		this.idAlbergo = idAlbergo;
		this.din = din;
		this.dout = dout;
	}
	public int getIdAlbergo() {
		return idAlbergo;
	}
	public int getDin() {
		return din;
	}
	public int getDout() {
		return dout;
	}
	
	public boolean doesCollide(int ini, int fin) {
		return !doesNotCollide(ini,fin);
		
	}
	
	public boolean doesNotCollide(int ini, int fin) {
		return ((ini>this.din && ini > this.dout)||(fin<this.din && fin < this.dout));
		
	}
	
}
