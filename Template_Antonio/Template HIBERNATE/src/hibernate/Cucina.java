package hibernate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Cucina implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long id;
	private String nome;
	private String regione;
	private Set<Ristorante> ristoranti = new HashSet<Ristorante>(5);
	
	
	public Cucina(long id, String nome, String regione) {
		super();
		this.id = id;
		this.nome = nome;
		this.regione = regione;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRegione() {
		return regione;
	}
	public void setRegione(String regione) {
		this.regione = regione;
	}

	public Set<Ristorante> getRistoranti() {
		return ristoranti;
	}

	public void setRistoranti(Set<Ristorante> ristoranti) {
		this.ristoranti = ristoranti;
	}
}