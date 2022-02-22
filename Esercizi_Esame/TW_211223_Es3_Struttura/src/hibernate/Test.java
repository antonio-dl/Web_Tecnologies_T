package hibernate;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Test {

	/**
	 * @param argv
	 */
	public static void main(String[] argv) {
		System.out.println("INIZIO");

		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();

			Prenotazione p0 = new Prenotazione(0, "A", 0, 10, 2);
			Prenotazione p1 = new Prenotazione(1, "B", 11, 15, 3);
			Prenotazione p2 = new Prenotazione(2, "C", 0, 77, 5);

			Struttura s0 = new Struttura(0, "Hotel bello", "albergo", "stagionale", "Montagna");
			Struttura s1 = new Struttura(1, "Hotel lato", "ostello", "settimanale", "Montagna");
			Struttura s2 = new Struttura(2, "Paradiso", "villaggio", "settimanale", "Costa Pugliese");

			Esperienza e0 = new Esperienza(0, "Gita in montagna", "Montagna", "Passeggiata tra i monti", 33);
			Esperienza e1 = new Esperienza(1, "Gita nella fattoria", "Fattoria ia ia o", "Conoscenza degli animali",
					33);
			Esperienza e2 = new Esperienza(2, "Giornata al mare", "Mare", "Gita al mare, portare i costumi", 23);

			p0.setStruttura(s0);
			s0.getPrenotazioni().add(p0);

			p1.setStruttura(s1);
			s1.getPrenotazioni().add(p1);

			p2.setStruttura(s1);
			s1.getPrenotazioni().add(p2);

			e0.getStrutture().add(s0);
			e0.getStrutture().add(s1);
			e1.getStrutture().add(s0);
			e1.getStrutture().add(s1);
			e2.getStrutture().add(s2);

			s0.getEsperienze().add(e0);
			s0.getEsperienze().add(e1);
			s1.getEsperienze().add(e0);
			s1.getEsperienze().add(e1);
			s2.getEsperienze().add(e2);

			session.persist(p0);
			session.persist(p1);
			session.persist(p2);

			session.persist(s0);
			session.persist(s1);
			session.persist(s2);

			session.persist(e0);
			session.persist(e1);
			session.persist(e2);

			tx.commit();

			// PrintWriter pw = new PrintWriter("Risultati.txt");

			Query q0 = session.createQuery("From " + Struttura.class.getSimpleName() + " WHERE luogo = 'Montagna'");
			// Query q1 = session.createQuery("From " + Cucina.class.getSimpleName());

			List<Struttura> listaS = q0.list();

			List<Esperienza> temp = new ArrayList<Esperienza>(5);
			for (Struttura s : listaS) {
				boolean trovato = false;
				for (Prenotazione p : s.getPrenotazioni()) {
					if (p.getNumeroPersone() >= 4)
						trovato = true;
				}
				if (trovato) {
					for (Esperienza e : s.getEsperienze()) {
						if (e.getLuogo().equals("Montagna"))
							temp.add(e);
					}
				}
			}

			// temp = temp.stream().filter((e) -> e.getLuogo().equals("Montagna")).collect(Collectors.toList());
			temp.sort(Comparator.comparing(Esperienza::getNumeroPersone).reversed().thenComparing(Esperienza::getNome));

			PrintWriter pw = new PrintWriter("Risultati.txt");

			pw.println("Esperienze trovate: ");

			for (Esperienza e : temp) {
				pw.println(e.getNome() + " " + e.getLuogo() + "  " + e.getDescrizione() + "  N.Persone: "
						+ e.getNumeroPersone());
			}

			pw.close();

			System.out.println("FINE");

//
//			}
//			pw.close();

		} catch (Exception e1) {
			if (tx != null) {
				try {
					tx.rollback();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			e1.printStackTrace();
		} finally {
			session.close();
		}
	}

}
