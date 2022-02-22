package dao;
import java.util.List;

public interface SpettacoloDAO
{
	//CRUD
	public void create(SpettacoloDTO obj);
	public SpettacoloDTO read(long id);
	public boolean update(SpettacoloDTO obj);
	public boolean delete(long id);

	public boolean createTable();
	public boolean dropTable();

	//Altri metodi

	public List<AttoreDTO> getAttoriFromSpettacolo(long idS);
}