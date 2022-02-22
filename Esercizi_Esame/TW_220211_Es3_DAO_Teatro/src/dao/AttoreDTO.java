package dao;
import java.io.Serializable;

public class AttoreDTO implements Serializable
{
	private long id;
	private String nome;
	private String cognome;
	private String ruolo;
	private long idSpettacolo;
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
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	public long getIdSpettacolo() {
		return idSpettacolo;
	}
	public void setIdSpettacolo(long idSpettacolo) {
		this.idSpettacolo = idSpettacolo;
	}
}