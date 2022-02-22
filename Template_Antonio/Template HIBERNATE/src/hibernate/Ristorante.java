package hibernate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Ristorante implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long id;
	private String nome;
	private String indirizzo;
	private String citta;
	private int capacita;
	private Set<Cucina> cucine = new HashSet<Cucina>(5);
	
	
	
	public Ristorante(long id, String nome, String indirizzo, String citta, int capacita) {
		super();
		this.id = id;
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.capacita = capacita;
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
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public int getCapacita() {
		return capacita;
	}
	public void setCapacita(int capacita) {
		this.capacita = capacita;
	}

	public Set<Cucina> getCucine() {
		return cucine;
	}

	public void setCucine(Set<Cucina> cucine) {
		this.cucine = cucine;
	}
	
}