package beans;

import java.util.*;

public class Albergo {
	private int id;
	private int camereTotali;
	private double prezzo;

	private Map<Integer,List<Prenotazione>> prenotazioni;;

	public int getId() {
		return id;
	}

	public Map<Integer, List<Prenotazione>> getPrenotazioni() {
		return prenotazioni;
	}

	public int getCamereTotali() {
		return camereTotali;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public Albergo(int id, int camereTotali, double prezzo) {
		super();
		this.id = id;
		this.camereTotali = camereTotali;
		this.prezzo = prezzo;
		prenotazioni = new HashMap<Integer,List<Prenotazione>>();
		for(int i = 1; i<=camereTotali;i++) {
			prenotazioni.put(i, new ArrayList<Prenotazione>());
		}
	}

	public void addPrenotazione(int camera, int inizio, int fine) {
		List<Prenotazione> prenotazioniCamera =  prenotazioni.get(camera);
		prenotazioniCamera.add(new Prenotazione(camera, inizio, fine));
		prenotazioniCamera.sort(Comparator.comparing(Prenotazione::getInizio));
	}

	public int getCameraLibera(int inizio, int fine) { // return camera se c'e' una camera libera, 0
		int camera = 1;
		for (camera = 1; camera <= this.getCamereTotali(); camera++) {
			List<Prenotazione> preCam = prenotazioni.get(camera);
			if(preCam.stream().filter(p -> p.doesCollide(inizio,fine)).count() == 0)
				return camera;
		}
		return 0;
	}

}
