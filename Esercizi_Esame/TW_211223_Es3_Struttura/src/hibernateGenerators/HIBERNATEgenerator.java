package hibernateGenerators;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;

public class HIBERNATEgenerator 
{
	static String gestioneClasse;
	static String tableName;
	static LinkedHashSet<String> attr;
	static String objName;
	static String objPlural;
	static String lastOne;
	static int size=0;
	
	public static void main(String[] args) throws IOException 
	{
		attr=new LinkedHashSet<String>();
		
		
		//CAMBIARE DA QUI...
		objName="Esperienza"; //nome del bean (iniziale maiuscola!!)
		objPlural="Esperienze"; //nome del bean in forma plurale (iniziale maiuscola!!)

		//non inserire "long id"!! (generato automaticamente)
		attr.add("String nome"); //coppie "tipo nome" separate da uno spazio
		attr.add("String luogo"); //coppie "tipo nome" separate da uno spazio
		attr.add("String descrizione"); //coppie "tipo nome" separate da uno spazio
		attr.add("int numeroPersone"); //coppie "tipo nome" separate da uno spazio
		///...A QUI

		size=attr.size();
		tableName=objPlural.toLowerCase();
		
		//GENERAZIONE Nomeoggetto.hbm.xml
		FileWriter fstream = new FileWriter(objName+".hbm.xml");
		BufferedWriter out = new BufferedWriter(fstream);
		
		String line="";
		out.write("<?xml version=\"1.0\"?>\n\n");
		out.write("<!DOCTYPE hibernate-mapping PUBLIC\n");
		out.write("\"-//Hibernate/Hibernate Mapping DTD 3.0//EN\"\n");
		out.write("\"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\">\n\n");
		out.write("<hibernate-mapping>\n");
		out.write("\t<class name=\"hibernate."+objName+"\" table=\""+tableName+"\">\n");
		out.write("\t\t<id name=\"id\" column=\"id\"/>\n");
		for(String temp : attr)
			out.write("\t\t<property name=\""+temp.split(" ")[1]+"\" column=\""+temp.split(" ")[1]+"\"/>\n");
		out.write("\t</class>\n");
		out.write("</hibernate-mapping>");		
		out.close();
		///////////////////////////
		
		//GENERAZIONE Nomeoggetto.java
		fstream = new FileWriter(objName+".java");
		out = new BufferedWriter(fstream);
				
		out.write("package hibernate;\n\n");
		out.write("import java.io.Serializable;\n\n");
		out.write("public class "+objName+" implements Serializable\n{");
		out.write("\n\tprivate static final long serialVersionUID = 1L;\n");
		out.write("\n\tprivate long id;\n");
		for(String temp : attr)
			out.write("\tprivate "+temp+";\n");
		out.write("}");
		out.close();
		///////////////////////////
	}

	
	

	
}
