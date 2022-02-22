package hibernate;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
			Cucina c0 = new Cucina(0, "Bolognese", "Italiana");
			Cucina c1 = new Cucina(1, "Giapponese", "Asiatica");
			Cucina c2 = new Cucina(2, "Peruviana", "SudAmerica");
			Cucina c3 = new Cucina(3, "Cinese", "Asiatica");
			
			
			Ristorante r0 = new Ristorante(0, "Vez", "Via Larga", "Bologna", 30);
			Ristorante r1 = new Ristorante(1, "Fukoshi", "Via Stretta", "Bologna", 30);
			Ristorante r2 = new Ristorante(2, "Da Francisco", "Via Cubo", "Bologna", 30);
			Ristorante r3 = new Ristorante(3, "Cappelletti", "Via Alta", "Bologna", 30);
			
			c0.getRistoranti().add(r0);
			c1.getRistoranti().add(r1);
			c2.getRistoranti().add(r2);
			c3.getRistoranti().add(r1);
			
			r0.getCucine().add(c0);
			r1.getCucine().add(c1);
			r1.getCucine().add(c3);
			r2.getCucine().add(c2);
			 
			
			
			session.persist(c0);
			session.persist(c1);
			session.persist(c2);
			session.persist(c3);
			
			session.persist(r0);
			session.persist(r1);
			session.persist(r2);
			session.persist(r3);
			
			tx.commit();
			

			//PrintWriter pw = new PrintWriter("Risultati.txt");
			
			Query q0 = session.createQuery("From " + Ristorante.class.getSimpleName());
			Query q1 = session.createQuery("From " + Cucina.class.getSimpleName());
			
			List<Ristorante> listaR = q0.list();
			List<Cucina> listaC = q1.list();
			
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
