import java.io.Serializable;
import java.util.List;

public class SpettacoloDTO implements Serializable
{
	private long id;
	private String titolo;
	private String tipologia;
	private String nomeRegista;
	private List<AttoreDTO> attori;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getTipologia() {
		return tipologia;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	public String getNomeRegista() {
		return nomeRegista;
	}
	public void setNomeRegista(String nomeRegista) {
		this.nomeRegista = nomeRegista;
	}
	public List<AttoreDTO> getAttori() {
		return attori;
	}
	public void setAttori(List<AttoreDTO> attori) {
		this.attori = attori;
	}
}