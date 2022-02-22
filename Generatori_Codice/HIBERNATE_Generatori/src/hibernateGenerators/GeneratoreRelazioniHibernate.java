package hibernateGenerators;
// Genera il codice relativo alle relazioni HIBERNATE 1-N e M-N e li stampa in std_output

public class GeneratoreRelazioniHibernate {
	public static void main(String[] args) {
		// Inserisci qui il nome singolari e plurali delle due relazioni
		// Metti a true se la relazione e' M-N
		boolean isMtoN = false;

		// Qui va il nome di 1 (1-N) o M (M-N) CON LA MAIUSCOLA!!!!!
		String singular_1orM = "Ristorante"; // RICORDATI LA MAIUSCOLA
		String plural_1orM = "Ristoranti"; // RICORDATI LA MAIUSCOLA

		// (1-N)N.B.: ^^^Questa^^^ puo' avere piu' di una di vvvquestavvv

		// Qui va il nome di N CON LA MAIUSCOLA!!!!!!
		String singular_N = "Cucina"; // RICORDATI LA MAIUSCOLA
		String plural_N = "Cucine"; // RICORDATI LA MAIUSCOLA

		// FINE MODIFICHE

		if (isMtoN) {
			genera_MtoN(singular_1orM, plural_1orM, singular_N, plural_N);
		} else {
			genera_1toN(singular_1orM, plural_1orM, singular_N, plural_N);
		}

	}

	static private void genera_MtoN(String singular_M, String plural_M, String singular_N, String plural_N) { // WorkPackage(M)
																												// Partner(N)
		String nome_table_di_associazione = plural_M.toLowerCase() + "_" + plural_N.toLowerCase();
		// Generazione di (M)
		System.out.println("DA INCOLLARE IN: " + singular_M + ".java(M)\n");
		System.out.println(
				"private Set<" + singular_N + ">" + plural_N.toLowerCase() + " = new HashSet<" + singular_N + ">(5);\n");
		System.out.println("DA INCOLLARE IN: " + singular_M + ".hbm.xml(M)\n");

		System.out.println("<set name=\"" + plural_N.toLowerCase() + "\" table=\"" + nome_table_di_associazione + "\"");
		System.out.println("	inverse=\"true\" fetch=\"join\" cascade=\"all\">");
		System.out.println("	<key column=\"" + "ID_" + singular_M + "\"></key>");
		System.out.println("	<many-to-many column=\"" + "ID_" + singular_N + "\"");
		System.out.println("		class=\"hibernate." + singular_N + "\" />");
		System.out.println("</set>\n");

		System.out.println("-----------------------------------------------------------------------");

		// Generazione di (N)
		System.out.println("DA INCOLLARE IN: " + singular_N + ".java(N)\n");
		System.out.println(
				"private Set<" + singular_M + ">" + plural_M.toLowerCase() + " = new HashSet<" + singular_M + ">(5);\n");
		System.out.println("DA INCOLLARE IN: " + singular_N + ".hbm.xml(N)\n");
		System.out.println("<set name=\"" + plural_M.toLowerCase() + "\" table=\"" + nome_table_di_associazione + "\"");
		System.out.println("	inverse=\"false\" fetch=\"join\" cascade=\"all\">");
		System.out.println("	<key column=\"" + "ID_" + singular_N + "\"></key>");
		System.out.println("	<many-to-many column=\"" + "ID_" + singular_M + "\"");
		System.out.println("		class=\"hibernate." + singular_M + "\" />");
		System.out.println("</set>");

	}

	static private void genera_1toN(String singular_1, String plural_1, String singular_N, String plural_N) { // Befana(1)
																												// Calza
																												// (N)

		// Generazione di (1)
		System.out.println("DA INCOLLARE IN: " + singular_1 + ".java(1)\n");
		System.out.println(
				"Set<" + singular_N + ">" + plural_N.toLowerCase() + " = new HashSet<" + singular_N + ">(5);\n");

		System.out.println("DA INCOLLARE IN: " + singular_1 + ".hbm.xml(1)\n");
		System.out.println("<set name=\"" + plural_N.toLowerCase() + "\" table=\"" + plural_N.toLowerCase()
				+ "\" inverse=\"false\"");
		System.out.println("	fetch=\"join\" lazy=\"true\">"); // si puo cancellare il lazy
		System.out.println("	<key>");
		System.out.println("		<column name=\"" + "ID_" + singular_1 + "\"></column>"); // FK:"ID_$Nome1"
		System.out.println("	</key>");
		System.out.println("	<one-to-many class=\"hibernate." + singular_N + "\" />");
		System.out.println("</set>");

		System.out.println("-----------------------------------------------------------------------");

		// Generazione di (N)
		System.out.println();
		System.out.println("DA INCOLLARE IN: " + singular_N + ".java(N)\n");
		System.out.println(singular_1 + " " + singular_1.toLowerCase() + ";\n");
		System.out.println("DA INCOLLARE IN " + singular_N + ".hbm.xml(N)\n");
		System.out.println("<many-to-one name=\"" + singular_1.toLowerCase() + "\" class=\"hibernate." + singular_1
				+ "\" fetch=\"select\" >");
		System.out.println("	<column name=\"" + "ID_" + singular_1 + "\"></column>"); // FK:"ID_$Nome1"
		System.out.println("</many-to-one>");

	}

}