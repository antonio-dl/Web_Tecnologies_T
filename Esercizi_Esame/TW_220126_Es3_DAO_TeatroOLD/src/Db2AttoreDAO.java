import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

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

	// Eventuali metodi aggiuntivi per l'esercizio

}
