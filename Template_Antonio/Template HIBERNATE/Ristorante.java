package hibernate;

import java.io.Serializable;

public class Ristorante implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long id;
	private String nome;
	private String indirizzo;
	private String citta;
	private int capacita;
}