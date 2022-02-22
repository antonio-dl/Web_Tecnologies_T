import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Db2SpettacoloDAO implements SpettacoloDAO {
	// statement metodi CRUD

	// INSERT
	private static final String insert = "INSERT INTO spettacoli (id,titolo,tipologia,nomeRegista) VALUES (?,?,?,?)";

	// DELETE
	private static final String delete = "DELETE FROM spettacoli WHERE id=?";

	// UPDATE
	private static final String update = "UPDATE spettacoli SET titolo=?,tipologia=?,nomeRegista=? WHERE id=?";

	// READ
	private static String read_by_id = "SELECT * FROM spettacoli WHERE id=?";

	// READ ATTORI
	private static String read_attori_by_id = "SELECT * FROM attori WHERE id=?";

	// -------------------------------------------------------------------------------------

	// create table
	private static String create = "CREATE TABLE spettacoli (id BIGINT NOT NULL PRIMARY KEY,titolo VARCHAR(50) NOT NULL,tipologia VARCHAR(50) NOT NULL,nomeRegista VARCHAR(50) NOT NULL)";;

	// drop table
	private static String drop = "DROP TABLE spettacoli";

	// === METODI DAO
	// =========================================================================

	@Override
	public void create(SpettacoloDTO obj) {

		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement statement = conn.prepareStatement(insert);
			statement.clearParameters();

			statement.setLong(1, obj.getId());
			statement.setString(2, obj.getTitolo());
			statement.setString(3, obj.getTipologia());
			statement.setString(4, obj.getNomeRegista());

			statement.executeUpdate();

			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// -------------------------------------------------------------------------------------

	@Override
	public SpettacoloDTO read(long id) {
		SpettacoloDTO result = null;

		if (id < 0)
			return result;

		Connection conn = Db2DAOFactory.createConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(read_by_id);
			statement.clearParameters();
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				SpettacoloDTO res = new SpettacoloDTO();

				res.setId(rs.getLong("id"));
				res.setTitolo(rs.getString("titolo"));
				res.setTipologia(rs.getString("tipologia"));
				res.setNomeRegista(rs.getString("nomeRegista"));

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
	public boolean update(SpettacoloDTO obj) {
		boolean result = false;
		if (obj == null) {
			return result;
		}

		Connection conn = Db2DAOFactory.createConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(update);
			statement.clearParameters();

			statement.setString(1, obj.getTitolo());
			statement.setString(2, obj.getTipologia());
			statement.setString(3, obj.getNomeRegista());
			statement.setLong(4, obj.getId());

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
	public List<AttoreDTO> getAttoriFromSpettacolo(long idS) {

		if (idS < 0)
			return null;

		List<AttoreDTO> result = new ArrayList<AttoreDTO>();
		Connection conn = Db2DAOFactory.createConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(read_attori_by_id);
			statement.clearParameters();
			statement.setLong(1, idS);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				AttoreDTO res = new AttoreDTO();

				res.setId(rs.getLong("id"));
				res.setNome(rs.getString("nome"));
				res.setCognome(rs.getString("cognome"));
				res.setRuolo(rs.getString("ruolo"));
				res.setIdSpettacolo(rs.getLong("idSpettacolo"));

				result.add(res);
			}

			rs.close();
			statement.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			Db2DAOFactory.closeConnection(conn);
		}

		return null;
	}
}

// Eventuali metodi aggiuntivi per l'esercizio
