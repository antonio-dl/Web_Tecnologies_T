package dao;
import java.io.Serializable;

public class TeatroDTO implements Serializable
{
	private long id;
	private String nomeTeatro;
	private String citta;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomeTeatro() {
		return nomeTeatro;
	}
	public void setNomeTeatro(String nomeTeatro) {
		this.nomeTeatro = nomeTeatro;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
}