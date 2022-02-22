package db2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import dao.AttoreDAO;
import dao.AttoreDTO;
import dao.SpettacoloDTO;
import dao.TeatroDTO;

public class Db2AttoreDAO implements AttoreDAO {
	// statement metodi CRUD

	// INSERT
	private static final String insert = "INSERT INTO attori (id,nome,cognome,ruolo,idSpettacolo) VALUES (?,?,?,?,?)";

	// DELETE
	private static final String delete = "DELETE FROM attori WHERE id=?";

	// UPDATE
	private static final String update = "UPDATE attori SET nome=?,cognome=?,ruolo=?,idSpettacolo=? WHERE id=?";

	// READ
	private static String read_by_id = "SELECT * FROM attori WHERE id=?";

	// -------------------------------------------------------------------------------------

	// create table
	private static String create = "CREATE TABLE attori (id BIGINT NOT NULL PRIMARY KEY,nome VARCHAR(50) NOT NULL,cognome VARCHAR(50) NOT NULL,ruolo VARCHAR(50) NOT NULL,idSpettacolo BIGINT REFERENCES spettacoli(id))";;

	// drop table
	private static String drop = "DROP TABLE attori";

	// === METODI DAO
	// =========================================================================

	@Override
	public void create(AttoreDTO obj) {

		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement statement = conn.prepareStatement(insert);
			statement.clearParameters();

			statement.setLong(1, obj.getId());
			statement.setString(2, obj.getNome());
			statement.setString(3, obj.getCognome());
			statement.setString(4, obj.getRuolo());
			statement.setLong(5, obj.getIdSpettacolo());

			statement.executeUpdate();

			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// -------------------------------------------------------------------------------------

	@Override
	public AttoreDTO read(long id) {
		AttoreDTO result = null;

		if (id < 0)
			return result;

		Connection conn = Db2DAOFactory.createConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(read_by_id);
			statement.clearParameters();
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				AttoreDTO res = new AttoreDTO();

				res.setId(rs.getLong("id"));
				res.setNome(rs.getString("nome"));
				res.setCognome(rs.getString("cognome"));
				res.setRuolo(rs.getString("ruolo"));
				res.setIdSpettacolo(rs.getLong("idSpettacolo"));

				result = res;
			}

			rs.close();
			statement.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			Db2DAOFactory.closeConnection(conn);
		}

		return result;
	}

	// -------------------------------------------------------------------------------------

	@Override
	public boolean update(AttoreDTO obj) {
		boolean result = false;
		if (obj == null) {
			return result;
		}

		Connection conn = Db2DAOFactory.createConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(update);
			statement.clearParameters();

			statement.setString(1, obj.getNome());
			statement.setString(2, obj.getCognome());
			statement.setString(3, obj.getRuolo());
			statement.setLong(4, obj.getIdSpettacolo());
			statement.setLong(5, obj.getId());

			statement.executeUpdate();
			result = true;
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Db2DAOFactory.closeConnection(conn);
		}

		return result;
	}

	// -------------------------------------------------------------------------------------

	@Override
	public boolean delete(long id) {
		boolean result = false;
		if (id < 0) {
			return result;
		}

		Connection conn = Db2DAOFactory.createConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(delete);
			statement.clearParameters();
			statement.setLong(1, id);
			statement.executeUpdate();
			result = true;
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Db2DAOFactory.closeConnection(conn);
		}

		return result;
	}

	// -------------------------------------------------------------------------------------

	public boolean createTable() {
		boolean result = false;
		Connection conn = Db2DAOFactory.createConnection();
		try {
			Statement statement = conn.createStatement();
			statement.execute(create);
			result = true;
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Db2DAOFactory.closeConnection(conn);
		}

		return result;
	}

	// -------------------------------------------------------------------------------------

	public boolean dropTable() {
		boolean result = false;

		Connection conn = Db2DAOFactory.createConnection();

		try {

			Statement stmt = conn.createStatement();
			stmt.execute(drop);
			result = true;
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			Db2DAOFactory.closeConnection(conn);
		}

		return result;
	}

	@Override
	public String getSpettacoliInBolognaFromId(long idA, String ruolo) {
//		String custom_query_all = " SELECT  *\n"
//				+ "		/ FROM attori A, Spettacoli S, Teatro T, teatrispettacolimapping TSM\n"
//				+ "		 WHERE A.id = ? AND Ruolo = ? \n" + "		 AND A.idSpettacolo = S.id\n"
//				+ "		 AND S.id = TSM.idS AND T.id = TSM.idT\n" + "		 AND T.citta = 'Bologna'";

		String spettacolo_query = " SELECT  S.*" + "FROM attori A, Spettacoli S"
				+ " WHERE A.id = ? AND Ruolo = ?" + " AND A.idSpettacolo = S.id";

		// Tutti gli spettacoli e i teatri in cui vengono fatti
		// query be like:
//		 SELECT  *
//		/ FROM attori A, Spettacoli S, Teatro T, teatrispettacolimapping TSM
//		 WHERE A.id = ? AND Ruolo = ? 
//		 AND A.idSpettacolo = S.id
//		 AND S.id = TSM.idS AND T.id = TSM.idT
//		 AND T.citta = 'Bologna'

		String result = "Risultati trovati per AttoreID = " + idA + " Ruolo: " + ruolo + "\n";

		Connection conn = Db2DAOFactory.createConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(spettacolo_query);
			statement.clearParameters();
			statement.setLong(1, idA);
			statement.setString(2, ruolo);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				SpettacoloDTO res = new SpettacoloDTO();

				res.setId(rs.getLong("id"));
				res.setTitolo(rs.getString("titolo"));
				res.setTipologia(rs.getString("tipologia"));
				res.setNomeRegista(rs.getString("nomeRegista"));

				result = "SpettacoloID: " + res.getId() + " Titolo:" + res.getTitolo() + " Tipologia: "
						+ res.getTipologia() + " Regista: " + res.getNomeRegista() +"\nNei teatri:\n";
				result += getTeatriFromSpettacoli(res.getId());

			}

			rs.close();
			statement.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			Db2DAOFactory.closeConnection(conn);
		}

		return result;

	}

	private String getTeatriFromSpettacoli(long idS) {
		if (idS < 0)
			return null;
		String query = "SELECT * FROM teatrispettacolimapping TSM, teatri T WHERE TSM.idTeatro=T.id AND TSM.idSpettacolo=?";
		String result = "";
		Connection conn = Db2DAOFactory.createConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.clearParameters();
			statement.setLong(1, idS);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				TeatroDTO res = new TeatroDTO();

				res.setId(rs.getLong("id"));
				res.setNomeTeatro(rs.getString("nomeTeatro"));
				res.setCitta(rs.getString("citta"));
				
				result += "TeatroID: " + res.getId() + " Nome Teatro = " + res.getNomeTeatro()
						+ " Citta': " + res.getCitta() + "\n";

			}

			rs.close();
			statement.close();

		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

	// Eventuali metodi aggiuntivi per l'esercizio

}
