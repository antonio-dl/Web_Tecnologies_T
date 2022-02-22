package hibernate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Struttura implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private String nome;
	private String tipologia;
	private String tipoResidenza;
	private String luogo;

	private Set<Esperienza> esperienze = new HashSet<Esperienza>(5);
	private Set<Prenotazione> prenotazioni = new HashSet<Prenotazione>(5);

	
	
	public Struttura(long id, String nome, String tipologia, String tipoResidenza, String luogo) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipologia = tipologia;
		this.tipoResidenza = tipoResidenza;
		this.luogo = luogo;
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

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public String getTipoResidenza() {
		return tipoResidenza;
	}

	public void setTipoResidenza(String tipoResidenza) {
		this.tipoResidenza = tipoResidenza;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public Set<Esperienza> getEsperienze() {
		return esperienze;
	}

	public void setEsperienze(Set<Esperienza> esperienze) {
		this.esperienze = esperienze;
	}

	public Set<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}

	public void setPrenotazioni(Set<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}

}