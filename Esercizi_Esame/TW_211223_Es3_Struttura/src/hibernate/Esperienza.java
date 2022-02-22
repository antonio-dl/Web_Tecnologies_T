package hibernate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Esperienza implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long id;
	private String nome;
	private String luogo;
	private String descrizione;
	private int numeroPersone;
	private Set<Struttura>strutture = new HashSet<Struttura>(5);
	
	
	
	public Esperienza(long id, String nome, String luogo, String descrizione, int numeroPersone) {
		super();
		this.id = id;
		this.nome = nome;
		this.luogo = luogo;
		this.descrizione = descrizione;
		this.numeroPersone = numeroPersone;
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


	public String getLuogo() {
		return luogo;
	}


	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public int getNumeroPersone() {
		return numeroPersone;
	}


	public void setNumeroPersone(int numeroPersone) {
		this.numeroPersone = numeroPersone;
	}


	public Set<Struttura> getStrutture() {
		return strutture;
	}


	public void setStrutture(Set<Struttura> strutture) {
		this.strutture = strutture;
	}


}