package hibernate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Befana implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long id;
	private String nomeBefana;
	private String colore;
	private int anni;
	private Set<Calza> calze = new HashSet<Calza>();
	
	
	
	public Befana(long id, String nomeBefana, String colore, int anni) {
		super();
		this.id = id;
		this.nomeBefana = nomeBefana;
		this.colore = colore;
		this.anni = anni;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomeBefana() {
		return nomeBefana;
	}
	public void setNomeBefana(String nomeBefana) {
		this.nomeBefana = nomeBefana;
	}
	public String getColore() {
		return colore;
	}
	public void setColore(String colore) {
		this.colore = colore;
	}
	public int getAnni() {
		return anni;
	}
	public void setAnni(int anni) {
		this.anni = anni;
	}
	public Set<Calza> getCalze() {
		return calze;
	}
	public void setCalze(Set<Calza> calze) {
		this.calze = calze;
	}
}