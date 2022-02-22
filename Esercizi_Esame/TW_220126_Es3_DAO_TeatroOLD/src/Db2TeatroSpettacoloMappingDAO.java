import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.Statement; 
import java.util.HashSet; 
import java.util.Set; 
 
public class Db2TeatroSpettacoloMappingDAO implements TeatroSpettacoloMappingDAO 
{ 
	//statement metodi CRUD 
	 
		//INSERT 
	private static final String insert = "INSERT INTO teatrispettacolimapping (idTeatro,idSpettacolo) VALUES (?,?)";
 
		 
		//DELETE 
	private static final String delete = "DELETE FROM teatrispettacolimapping WHERE idTeatro=? AND idSpettacolo=?";
 
 
 
	// ------------------------------------------------------------------------------------- 
 
		// create table 
		private static String create = 
"CREATE TABLE teatrispettacolimapping (idTeatro BIGINT NOT NULL REFERENCES teatri(id),idSpettacolo BIGINT NOT NULL REFERENCES spettacoli(id))"; 
			; 
	 
		//drop table 
	private static String drop = "DROP TABLE teatrispettacolimapping";
 
	 
			 
	 
	// === METODI DAO ========================================================================= 
	 
	@Override 
	public void create(long idT, long idS) { 
		 
		Connection conn = Db2DAOFactory.createConnection(); 
		try 
		{ 
			PreparedStatement statement = conn.prepareStatement(insert); 
			statement.clearParameters(); 
			 
			statement.setLong(1,idT);
			statement.setLong(2,idS);
 
			 
			statement.executeUpdate(); 
			 
			statement.close(); 
		} 
		catch (Exception e) 
		{ 
			e.printStackTrace(); 
		} 
		 
	} 
 
	// ------------------------------------------------------------------------------------- 
 
	 
 
	// ------------------------------------------------------------------------------------- 
 
	 
 
	// ------------------------------------------------------------------------------------- 
 
	@Override 
	public boolean delete(long idT, long idS) 
	{ 
		boolean result = false; 
		if (idT<0 || idS<0) 
		{ 
			return result; 
		} 
 
		Connection conn=Db2DAOFactory.createConnection(); 
 
		try 
		{ 
			PreparedStatement statement=conn.prepareStatement(delete); 
			statement.clearParameters(); 
			statement.setLong(1,idT); 
			statement.setLong(2,idS); 
			statement.executeUpdate(); 
			result = true; 
			statement.close(); 
		} 
		catch (Exception e) 
		{ 
			e.printStackTrace(); 
		} 
		finally 
		{ 
			Db2DAOFactory.closeConnection(conn); 
		} 
 
		return result; 
	} 
 
	// ------------------------------------------------------------------------------------- 
	 
	public boolean createTable() 
	{ 
		boolean result = false; 
		Connection conn = Db2DAOFactory.createConnection(); 
		try 
		{ 
			Statement statement = conn.createStatement(); 
			statement.execute(create); 
			result = true; 
			statement.close(); 
		} 
		catch (Exception e) 
		{ 
			e.printStackTrace(); 
		} 
		finally 
		{ 
			Db2DAOFactory.closeConnection(conn); 
		} 
 
		return result; 
	} 
 
	// ------------------------------------------------------------------------------------- 
 
	public boolean dropTable() 
	{ 
		boolean result=false; 
 
		Connection conn = Db2DAOFactory.createConnection(); 
 
		try 
		{ 
 
			Statement stmt = conn.createStatement(); 
			stmt.execute(drop); 
			result = true; 
			stmt.close(); 
		} 
		catch (Exception e) 
		{ 
			e.printStackTrace(); 
		} 
		 
		finally 
		{ 
			Db2DAOFactory.closeConnection(conn); 
		} 
		 
		return result; 
	} 
	 
	 
	 
	 
	 
	 
	// Eventuali metodi aggiuntivi per l'esercizio 
	 
	 
	 
	 
} 
