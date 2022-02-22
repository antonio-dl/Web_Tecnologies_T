package db2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.SpettacoloDTO;
import dao.TeatroDAO;
import dao.TeatroDTO; 
 
public class Db2TeatroDAO implements TeatroDAO 
{ 
	//statement metodi CRUD 
	 
		//INSERT 
	private static final String insert = "INSERT INTO teatri (id,nomeTeatro,citta) VALUES (?,?,?)";
 
		 
		//DELETE 
	private static final String delete = "DELETE FROM teatri WHERE id=?";
 
 
		//UPDATE 
	private static final String update = "UPDATE teatri SET nomeTeatro=?,citta=? WHERE id=?";
 
		 
		//READ 
	private static String read_by_id = "SELECT * FROM teatri WHERE id=?";

	private static String read_by_all = "SELECT * FROM teatri";
	
	
 
	private static String read_spettacoli_by_idTeatro = "SELECT * FROM teatrispettacolimapping TSM, spettacoli S WHERE TSM.idSpettacolo=S.id AND TSM.idTeatro=?";
	
 
	// ------------------------------------------------------------------------------------- 
 
		// create table 
		private static String create = 
"CREATE TABLE teatri (id BIGINT NOT NULL PRIMARY KEY,nomeTeatro VARCHAR(50) NOT NULL,citta VARCHAR(50) NOT NULL)"; 
			; 
	 
		//drop table 
	private static String drop = "DROP TABLE teatri";
 
	 
			 
	 
	// === METODI DAO ========================================================================= 
	 
	@Override 
	public void create( TeatroDTO obj) { 
		 
		Connection conn = Db2DAOFactory.createConnection(); 
		try 
		{ 
			PreparedStatement statement = conn.prepareStatement(insert); 
			statement.clearParameters(); 
			 
			statement.setLong(1,obj.getId());
			statement.setString(2,obj.getNomeTeatro());
			statement.setString(3,obj.getCitta());
 
			 
			statement.executeUpdate(); 
			 
			statement.close(); 
		} 
		catch (Exception e) 
		{ 
			e.printStackTrace(); 
		} 
		 
	} 
 
	// ------------------------------------------------------------------------------------- 
 
	@Override 
	public TeatroDTO read(long id) 
	{ 
TeatroDTO result = null; 
 
		if(id<0) 
			return result; 
 
		Connection conn=Db2DAOFactory.createConnection(); 
 
		try 
		{ 
			PreparedStatement statement=conn.prepareStatement(read_by_id); 
			statement.clearParameters(); 
			statement.setLong(1, id); 
			ResultSet rs=statement.executeQuery(); 
			 
			if(rs.next()) 
			{ 
TeatroDTO res = new TeatroDTO (); 
				 
				res.setId(rs.getLong("id"));
				res.setNomeTeatro(rs.getString("nomeTeatro"));
				res.setCitta(rs.getString("citta"));
 
				 
				result = res; 
			} 
 
			rs.close(); 
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
 
	@Override 
	public boolean update( TeatroDTO obj) 
	{ 
		boolean result = false; 
		if (obj==null) 
		{ 
			return result; 
		} 
 
		Connection conn = Db2DAOFactory.createConnection(); 
 
		try 
		{ 
			PreparedStatement statement=conn.prepareStatement(update); 
			statement.clearParameters(); 
 
			statement.setString(1,obj.getNomeTeatro());
			statement.setString(2,obj.getCitta());
			statement.setLong(3,obj.getId());
 
			 
			statement.executeUpdate(); 
			result=true; 
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
 
	@Override 
	public boolean delete(long id) 
	{ 
		boolean result = false; 
		if (id<0) 
		{ 
			return result; 
		} 
 
		Connection conn=Db2DAOFactory.createConnection(); 
 
		try 
		{ 
			PreparedStatement statement=conn.prepareStatement(delete); 
			statement.clearParameters(); 
			statement.setLong(1,id); 
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

	@Override
	public List<SpettacoloDTO> getSpettacoliFromTeatro(long idT) {

		if (idT < 0)
			return null;

		List<SpettacoloDTO> result = new ArrayList<SpettacoloDTO>();
		Connection conn = Db2DAOFactory.createConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(read_spettacoli_by_idTeatro);
			statement.clearParameters();
			statement.setLong(1, idT);
			ResultSet rs = statement.executeQuery();

		while (rs.next()) {
				SpettacoloDTO res = new SpettacoloDTO();

				res.setId(rs.getLong("id"));
				res.setTitolo(rs.getString("titolo"));
				res.setTipologia(rs.getString("tipologia"));
				res.setNomeRegista(rs.getString("nomeRegista"));
				res.setAttori(new Db2SpettacoloDAO().getAttoriFromSpettacolo(res.getId()));
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

		return result;
	}

	@Override
	public List<TeatroDTO> getAllTeatri() {
		List<TeatroDTO> result = new ArrayList<TeatroDTO>();
		 
		Connection conn=Db2DAOFactory.createConnection(); 
 
		try 
		{ 
			PreparedStatement statement=conn.prepareStatement(read_by_all); 
			statement.clearParameters(); 
			ResultSet rs=statement.executeQuery(); 
			 
			while(rs.next()) 
			{ 
				TeatroDTO res = new TeatroDTO (); 
				 
				res.setId(rs.getLong("id"));
				res.setNomeTeatro(rs.getString("nomeTeatro"));
				res.setCitta(rs.getString("citta"));
 
				 
				result.add(res); 
			} 
 
			rs.close(); 
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
		
	 
}
	 
	 
	
