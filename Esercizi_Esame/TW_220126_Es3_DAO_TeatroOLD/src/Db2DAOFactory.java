import java.sql.Connection;
import java.sql.DriverManager;

public class Db2DAOFactory extends DAOFactory 
{
	//public static final String DRIVER = "COM.ibm.db2.jdbc.app.DB2Driver";
	// oppure, per connettersi da casa
	public static final String DRIVER = "com.ibm.db2.jcc.DB2Driver";
	
	//public static final String DBURL = "jdbc:db2:tw_stud";
	// oppure, per connettersi da casa
	public static final String DBURL = "jdbc:db2://diva.deis.unibo.it:50000/tw_stud";

	public static final String USERNAME = "xxxxx";
	public static final String PASSWORD = "xxxxx";

	// --------------------------------------------

	static 
	{
		try 
		{
			Class.forName(DRIVER);
		} 
		catch (Exception e) 
		{
			System.err.println("HsqldbDAOFactory: failed to load DB2 JDBC driver" + "\n" + e.toString());
			e.printStackTrace();
		}
	}

	public static Connection createConnection() 
	{
		try 
		{
			return DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
		} 
		catch (Exception e) 
		{
			System.err.println(Db2DAOFactory.class.getName() + ".createConnection(): failed creating connection" + "\n" + e.toString());
			e.printStackTrace();
			System.err.println("Was the database started? Is the database URL right?");
			return null;
		}
	}
	
	public static void closeConnection(Connection conn) 
	{
		try 
		{
			conn.close();
		}
		catch (Exception e) 
		{
			System.err.println(Db2DAOFactory.class.getName() + ".closeConnection(): failed closing connection" + "\n" + e.toString());
			e.printStackTrace();
		}
	}

	@Override
	public AttoreDAO getAttoreDAO() {
		// TODO Auto-generated method stub
		return new Db2AttoreDAO();
	}

	@Override
	public SpettacoloDAO getSpettacoloDAO() {
		// TODO Auto-generated method stub
return new Db2SpettacoloDAO();	}

	@Override
	public TeatroDAO getTeatroDAO() {
		// TODO Auto-generated method stub
return new Db2TeatroDAO();	}

	@Override
	public TeatroSpettacoloMappingDAO getTeatroSpettacoloMappingDAO() {
		// TODO Auto-generated method stub
return new Db2TeatroSpettacoloMappingDAO();	}


	//CAMBIARE DA QUI...

	
	//...A QUI
}
