
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;

public class DAOgenerator 
{
	static String gestioneClasse;
	static String tableName;
	static LinkedHashSet<String> attr;
	static String objName;
	static String objPlural;
	static String objNameDAO;
	static String objNameDAODb2;
	static String lastOne;
	static int size=0;
	
	public static void main(String[] args) throws IOException 
	{
		attr=new LinkedHashSet<String>();
				
		//CAMBIARE DA QUI...
		objName="TeatroSpettacoloMapping"; //nome del bean (iniziale maiuscola!!)
		objPlural="TeatriSpettacoliMapping"; //nome del bean in forma plurale (iniziale maiuscola!!)

		//non inserire "long id"!! (generato automaticamente)
		attr.add("long idTeatro"); //coppie "tipo nome" separate da uno spazio
		attr.add("long idSpettacolo"); //coppie "tipo nome" separate da uno spazio
		///...A QUI
		
		objNameDAO=objName+"DAO";
		objName=objName+"DTO";
		objNameDAODb2="Db2"+objNameDAO;

		size=attr.size();
		
		tableName=objPlural.toLowerCase();
		
		gestioneClasse=objNameDAODb2;
		
		//GENERAZIONE DB2OggettoDAO
		FileWriter fstream = new FileWriter(objNameDAODb2+".java");
		BufferedWriter out = new BufferedWriter(fstream);
		BufferedReader br=new BufferedReader(new FileReader("Db2OggettoDAO_temp.txt"));
		
		String line="";
		
		while((line=br.readLine())!=null)
		{
			String modifiedLine=modifyLine(line);
			out.write(modifiedLine+"\n");
		}
		
		br.close();
		out.close();
		///////////////////////////
		
		//GENERAZIONE OggettoDAO
		fstream = new FileWriter(objNameDAO+".java");
		out = new BufferedWriter(fstream);
				
		out.write("public interface "+objNameDAO+"\n{\n");
		out.write("\t//CRUD\n");
		out.write("\tpublic void create("+objName+" obj);\n");
		out.write("\tpublic "+objName+" read(long id);\n");
		out.write("\tpublic boolean update("+objName+" obj);\n");
		out.write("\tpublic boolean delete(long id);\n");
		out.write("\n\tpublic boolean createTable();\n\tpublic boolean dropTable();\n");
		out.write("\n\t//Altri metodi\n");
		out.write("\n}");
		//br.close();
		out.close();
		///////////////////////////
		
		//GENERAZIONE OggettoDTO
		fstream = new FileWriter(objName+".java");
		out = new BufferedWriter(fstream);
				
		out.write("import java.io.Serializable;\n\n");
		out.write("public class "+objName+" implements Serializable\n{");
		out.write("\n\tprivate long id;\n");
		for(String temp : attr)
			out.write("\tprivate "+temp+";\n");
		out.write("}");
		out.close();
		///////////////////////////
	}

	private static String modifyLine(String line) 
	{
		String[] words=line.split(" ");
		String ret="";
		for(int i=0;i<words.length;i++)
		{
			ret+=checkWord(words[i])+" ";
		}
		return ret;
	}

	private static String checkWord(String word) 
	{
		String ret=word;
		
		if(word.trim().equals("#GESTIONE_NOMECLASSE#"))
			ret=gestioneClasse;
		if(word.trim().equals("#NOMECLASSE#"))
			ret=objName;
		if(word.trim().equals("#NOMECLASSEDAODB2#"))
			ret=objNameDAODb2;
		if(word.trim().equals("#NOMECLASSEDAO#"))
			ret=objNameDAO;
		if(word.trim().equals("#NOMETABELLA#"))
			ret=tableName;
		if(word.trim().equals("#INSERT#"))
			ret=generateInsert(word);
		if(word.trim().equals("#DELETE#"))
			ret=generateDelete(word);
		if(word.trim().equals("#UPDATE#"))
			ret=generateUpdate(word);
		if(word.trim().equals("#READ#"))
			ret=generateReadById(word);
		if(word.trim().equals("#CREATE_TABLE#"))
			ret=createTable(word);
		if(word.trim().equals("#DROPTABLE#"))
			ret=generateDrop(word);
		if(word.trim().equals("#INSERT_STATEMENTS#"))
			ret=generateInsertCRUD(word);
		if(word.trim().equals("#UPDATE_STATEMENTS#"))
			ret=generateUpdateCRUD(word);
		if(word.trim().equals("#READ_STATEMENTS#"))
			ret=generateReadCRUD(word);
		
		return ret;
	}

	private static String generateInsert(String word) 
	{
		String ret="";
		ret+="\tprivate static final String insert = \"INSERT INTO ";
		ret+=tableName+" (";
		int index=0;
		ret+="id,";
		for(String temp : attr)
		{
			if(index<size-1)
			{
				ret+=temp.split(" ")[1]+",";
				index++;
			}
			else ret+=temp.split(" ")[1]+"";
		}
		ret+=") VALUES (?,";
		index=0;
		for(@SuppressWarnings("unused") String temp : attr)
		{
			if(index<size-1)
			{
				ret+="?,";
				index++;
			}
			else ret+="?)\";\n";
		}
		System.out.println(ret);
		return ret;
	}
	
	private static String generateDelete(String word) 
	{
		String ret="";
		ret+="\tprivate static final String delete = \"DELETE FROM "+tableName+" WHERE id=?\";\n";

		System.out.println(ret);
		return ret;
	}
	
	private static String generateUpdate(String word) 
	{
		String ret="";
		ret+="\tprivate static final String update = \"UPDATE ";
		ret+=tableName+" SET ";
		int index=0;
		for(String temp : attr)
		{
			if(index<size-1)
			{
				ret+=temp.split(" ")[1]+"=?,";
				index++;
			}
			else ret+=temp.split(" ")[1]+"=?";
		}
		ret+=" WHERE id=?\";\n";
		
		System.out.println(ret);
		return ret;
	}
	
	private static String generateReadById(String word) 
	{
		String ret="";
		ret+="\tprivate static String read_by_id = \"SELECT * FROM "+tableName+" WHERE id=?\";\n";

		System.out.println(ret);
		return ret;
	}
	
	private static String createTable(String word) 
	{
		String ret="";
		ret+="\"CREATE TABLE "+tableName+" (id BIGINT NOT NULL PRIMARY KEY,";
		int index=0;
		for(String temp : attr)
		{
			String type=temp.split(" ")[0];
			String name=temp.split(" ")[1];
			String refClass=null;
			ret+=name+" ";
			if(temp.split(" ").length==3)
			{
				refClass=temp.split(" ")[2];
				if(type.toUpperCase().equals("STRING"))
					ret+="VARCHAR(50)";
				else if(type.toUpperCase().equals("LONG"))
					ret+="BIGINT";
				ret+=" NOT NULL REFERENCES "+refClass;
			}
			else
			{
				if(type.toUpperCase().equals("STRING"))
					ret+="VARCHAR(50) NOT NULL";
				else if(type.toUpperCase().equals("LONG"))
					ret+="BIGINT NOT NULL";
				else ret+=type.toUpperCase()+" NOT NULL";
			}
			if(index==size-1)
				ret+=")\";";
			else 
				ret+=",";
			index++;
		}
		System.out.println(ret);
		return ret;
	}
	
	private static String generateDrop(String word)
	{
		String ret="";
		ret+="\tprivate static String drop = \"DROP TABLE "+tableName+"\";\n";
		System.out.println(ret);
		return ret;
	}
	
	private static String generateInsertCRUD(String word) 
	{
		String ret="";
		ret+="\t\t\tstatement.setLong(1,obj.getId());\n";
		int index=2;
		for(String temp : attr)
		{
			ret+="\t\t\tstatement.set"+temp.split(" ")[0].substring(0,1).toUpperCase()+temp.split(" ")[0].substring(1);
			ret+="("+index+",obj.get"+temp.split(" ")[1].substring(0,1).toUpperCase()+temp.split(" ")[1].substring(1)+"());\n";
			index++;
		}
		System.out.println(ret);
		return ret;
	}

	private static String generateUpdateCRUD(String word) 
	{
		String ret="";
		int index=1;
		for(String temp : attr)
		{
			ret+="\t\t\tstatement.set"+temp.split(" ")[0].substring(0,1).toUpperCase()+temp.split(" ")[0].substring(1);
			ret+="("+index+",obj.get"+temp.split(" ")[1].substring(0,1).toUpperCase()+temp.split(" ")[1].substring(1)+"());\n";
			index++;
		}
		ret+="\t\t\tstatement.setLong("+index+",obj.getId());\n";
		System.out.println(ret);
		return ret;
	}
	
	private static String generateReadCRUD(String word) 
	{
		String ret="";
		ret+="\t\t\t\tres.setId(rs.getLong(\"id\"));\n";
		for(String temp : attr)
		{
			ret+="\t\t\t\tres.set"+temp.split(" ")[1].substring(0,1).toUpperCase()+temp.split(" ")[1].substring(1)+"(rs.get";
			ret+=temp.split(" ")[0].substring(0,1).toUpperCase()+temp.split(" ")[0].substring(1)+"(\""+temp.split(" ")[1]+"\"));\n";
		}
		System.out.println(ret);
		return ret;
	}
}
