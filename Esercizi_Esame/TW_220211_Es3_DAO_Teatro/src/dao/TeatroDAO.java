package dao;
import java.util.List;

public interface TeatroDAO
{
	//CRUD
	public void create(TeatroDTO obj);
	public TeatroDTO read(long id);
	public boolean update(TeatroDTO obj);
	public boolean delete(long id);

	public boolean createTable();
	public boolean dropTable();

	//Altri metodi

	public List<SpettacoloDTO> getSpettacoliFromTeatro(long idT);
	public List<TeatroDTO> getAllTeatri();
}