public interface TeatroSpettacoloMappingDAO
{
	//CRUD
	public void create(long idT, long idS);
	public boolean delete(long idT, long idS);

	public boolean createTable();
	public boolean dropTable();

	//Altri metodi

}