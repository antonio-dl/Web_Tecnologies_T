package dao;
import java.util.List;

public interface AttoreDAO
{
	//CRUD
	public void create(AttoreDTO obj);
	public AttoreDTO read(long id);
	public boolean update(AttoreDTO obj);
	public boolean delete(long id);

	public boolean createTable();
	public boolean dropTable();

	//Altri metodi

	public String getSpettacoliInBolognaFromId(long idA, String ruolo);
	
	
}