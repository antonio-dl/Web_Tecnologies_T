import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class DAOTest {

	public static final int DAO = DAOFactory.DB2;

	public static void main(String[] args) {

		// pattern: creo Factory, creo OggettoDAO, droptable e create table

		DAOFactory daoFactoryInstance = DAOFactory.getDAOFactory(DAO);
		TeatroDAO teatroDAO = daoFactoryInstance.getTeatroDAO();
		teatroDAO.dropTable();
		teatroDAO.createTable();

		SpettacoloDAO spettacoloDAO = daoFactoryInstance.getSpettacoloDAO();
		spettacoloDAO.dropTable();
		spettacoloDAO.createTable();

		AttoreDAO attoreDAO = daoFactoryInstance.getAttoreDAO();
		attoreDAO.dropTable();
		attoreDAO.createTable();
		
		TeatroSpettacoloMappingDAO tsmDAO = daoFactoryInstance.getTeatroSpettacoloMappingDAO();
		tsmDAO.dropTable();
		tsmDAO.createTable();
		// inserimento oggetti nel db: creo oggettoDTO, lo popolo, e invoco
		// OggettoDAO.create(oggettoDTO)
		AttoreDTO attore0 = new AttoreDTO();
		attore0.setId(0);
		attore0.setNome("Gino");
		attore0.setCognome("Gino");
		attore0.setIdSpettacolo(0);
		attore0.setRuolo("protagonista");
		
		AttoreDTO attore1 = new AttoreDTO();
		attore1.setId(1);
		attore1.setNome("ASD");
		attore1.setCognome("DSA");
		attore1.setIdSpettacolo(0);
		attore1.setRuolo("protagonista");
		
		SpettacoloDTO sp = new SpettacoloDTO();
		sp.setId(0);
		sp.setNomeRegista("QQQ");
		sp.setTipologia("commedia");
		sp.setTitolo("Nonnismo");
		
		TeatroDTO tr = new TeatroDTO();
		tr.setId(0);
		tr.setNomeTeatro("Ariston");
		tr.setCitta("Roma");
		
		
		
		teatroDAO.create(tr);
		
		spettacoloDAO.create(sp);
		
		attoreDAO.create(attore0);
		attoreDAO.create(attore1);
		
		
		
		tsmDAO.create(0, 0);
		
		try {
			PrintWriter pw = new PrintWriter("TEATRO.txt");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		

	}
}
