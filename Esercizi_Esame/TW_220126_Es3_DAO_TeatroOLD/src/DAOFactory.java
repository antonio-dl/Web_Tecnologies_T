public abstract class DAOFactory 
{
	public static final int DB2 = 0;

	public static DAOFactory getDAOFactory(int whichFactory) 
	{
		switch(whichFactory) 
		{
		case DB2:
			return new Db2DAOFactory();
		default:
			return null;
		}
	}
	
	//CAMBIARE DA QUI...
	public abstract AttoreDAO getAttoreDAO();
	public abstract SpettacoloDAO getSpettacoloDAO(); 
	public abstract TeatroDAO getTeatroDAO();
	public abstract TeatroSpettacoloMappingDAO getTeatroSpettacoloMappingDAO();
	//...A QUI
	
}
