package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Gruppi implements Serializable{

	private List<String> listaGruppi = new ArrayList<String>(10);

	public List<String> getListaGruppi() {
		return listaGruppi;
	}

	public void setListaGruppi(List<String> listaGruppi) {
		this.listaGruppi = listaGruppi;
	}
	
	
}
