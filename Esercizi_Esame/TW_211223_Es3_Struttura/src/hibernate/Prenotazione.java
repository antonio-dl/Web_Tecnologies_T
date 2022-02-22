package hibernate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Prenotazione implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long id;
	private String nome;
	private int dataArrivo;
	private int dataPartenza;
	private int numeroPersone;
	
	private Struttura struttura;

	
	
	public Prenotazione(long id, String nome, int dataArrivo, int dataPartenza, int numeroPersone) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataArrivo = dataArrivo;
		this.dataPartenza = dataPartenza;
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

	public int getDataArrivo() {
		return dataArrivo;
	}

	public void setDataArrivo(int dataArrivo) {
		this.dataArrivo = dataArrivo;
	}

	public int getDataPartenza() {
		return dataPartenza;
	}

	public void setDataPartenza(int dataPartenza) {
		this.dataPartenza = dataPartenza;
	}

	public int getNumeroPersone() {
		return numeroPersone;
	}

	public void setNumeroPersone(int numeroPersone) {
		this.numeroPersone = numeroPersone;
	}

	public Struttura getStruttura() {
		return struttura;
	}

	public void setStruttura(Struttura struttura) {
		this.struttura = struttura;
	}
	
	
}