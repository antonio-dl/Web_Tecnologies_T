package hibernate;

import java.io.Serializable;

public class Calza implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long id;
	private String colore;
	private String destinatario;
	private String contenuto;
	private Befana befana;
	
	
	
	public Calza(long id, String colore, String destinatario, String contenuto) {
		super();
		this.id = id;
		this.colore = colore;
		this.destinatario = destinatario;
		this.contenuto = contenuto;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getColore() {
		return colore;
	}
	public void setColore(String colore) {
		this.colore = colore;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getContenuto() {
		return contenuto;
	}
	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
	}
	public Befana getBefana() {
		return befana;
	}
	public void setBefana(Befana befana) {
		this.befana = befana;
	}
}