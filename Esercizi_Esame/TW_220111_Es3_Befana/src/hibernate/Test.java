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

		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();

			Befana b0 = new Befana(0, "Carmela", "rossa", 133);
			Befana b1 = new Befana(1, "Rossella", "rosa", 255);
			Befana b2 = new Befana(2, "Magda", "blu", 420);
			Befana b3 = new Befana(3, "Gina", "marrone", 883);

			Calza c0 = new Calza(0, "verde", "Antonio", "Regali");
			Calza c1 = new Calza(1, "nera", "Giuseppe", "Carbone");
			Calza c2 = new Calza(2, "rosa", "Nora", "Regali");
			Calza c3 = new Calza(3, "rosa", "Bora", "Regali");

			// Associazioni

			c0.setBefana(b0);
			c1.setBefana(b1);
			c2.setBefana(b2);
			c3.setBefana(b2);

			b0.getCalze().add(c0);
			b1.getCalze().add(c1);
			b2.getCalze().add(c2);
			b2.getCalze().add(c3);

			session.persist(b0);
			session.persist(b1);
			session.persist(b2);
			session.persist(b3);

			session.persist(c0);
			session.persist(c1);
			session.persist(c2);
			session.persist(c3);

			tx.commit();
			System.out.println("Fine");

			PrintWriter pw = new PrintWriter("HUGEOldHag.txt");
			Query q1 = session.createQuery("FROM " + Befana.class.getSimpleName());
			List<Befana> listaBefane = q1.list();
			Befana bestBefana = null;
			int bestCount = 0;
			for (Befana befana : listaBefane) {
				if (befana.getCalze().size() > bestCount) {
					bestCount = befana.getCalze().size();
					bestBefana = befana;
				}
			}

			pw.println("La best befana e':");
			System.out.println("La best befana e':");
			pw.println("ID: " + bestBefana.getId() + "\tNome: " + bestBefana.getNomeBefana());
			System.out.println("ID: " + bestBefana.getId() + "\tNome: " + bestBefana.getNomeBefana() + "  Con n calze: " + bestBefana.getCalze().size());

			pw.close();

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
