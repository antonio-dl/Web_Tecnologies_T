import java.io.FileNotFoundException;
import java.util.Arrays;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;

// 
// Decompiled by Procyon v0.5.36
// 

public class WebXMLGenerator
{
    public static void main(final String[] args) {
        try {
            final List<String> servlets = retriveServletList();
            final PrintWriter pw = new PrintWriter("web/WEB-INF/web.xml");
            pw.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
            pw.println("<web-app xmlns=\"http://JAVA.sun.com/xml/ns/j2ee\"");
            pw.println("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
            pw.println("xsi:schemaLocation=\"http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd\"");
            pw.println("version=\"2.4\">");
            pw.println("<display-name>Template</display-name>");
            pw.println("<description>");
            pw.println("An empty project to use as a template for your own ones");
            pw.println("</description>");
            pw.println("<!-- Context attributes to be used throughout the application -->");
            pw.println("<context-param>");
            pw.println("<param-name>dao</param-name>");
            pw.println("<param-value>IDENTIFICATIVO_DELL_IMPLEMENTAZIONE</param-value>");
            pw.println("</context-param>");
            pw.println("");
            pw.println("<!-- Define servlets that are included in the application -->");
            pw.println("");
            for (final String s : servlets) {
                final String noDotJava = s.substring(0, s.length() - 5);
                pw.println("<servlet>");
                pw.format("<servlet-name>%s</servlet-name>\n<servlet-class>servlets.%s</servlet-class>", noDotJava, noDotJava);
                pw.println("</servlet>");
            }
            for (final String s : servlets) {
                final String noDotJava = s.substring(0, s.length() - 5);
                pw.println("<servlet-mapping>");
                pw.format("<servlet-name>%s</servlet-name>\n<url-pattern>/%s</url-pattern>", noDotJava, noDotJava);
                pw.println("</servlet-mapping>");
            }
            pw.println("");
            pw.println("");
            pw.println("<!-- Map some URL's to the  servlet --> ");
            pw.println("");
            pw.println("");
            pw.println("");
            pw.println("<welcome-file-list>");
            pw.println("<welcome-file>login.jsp</welcome-file>");
            pw.println("<welcome-file>login.html</welcome-file>");
            pw.println("<welcome-file>home.jsp</welcome-file>");
            pw.println("<welcome-file>index.jsp</welcome-file>");
            pw.println("</welcome-file-list>");
            pw.println("");
            pw.println("<!-- Handle exceptions and errors -->");
            pw.println("");
            pw.println("<error-page>");
            pw.println("<error-code>404</error-code>");
            pw.println("<location>/errors/notfound.html</location>");
            pw.println("</error-page>");
            pw.println("<error-page>");
            pw.println("<exception-type>javax.servlet.ServletException</exception-type>");
            pw.println("<location>/errors/failure.jsp</location>");
            pw.println("</error-page>");
            pw.println("");
            pw.println("</web-app>");
            pw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static List<String> retriveServletList() throws FileNotFoundException {
        final List<String> result = new ArrayList<String>(10);
        final File cartellaServlet = new File("src/servlets");
        if (cartellaServlet.exists()) {
            final List<File> listaServlet = Arrays.asList(cartellaServlet.listFiles());
            for (final File file : listaServlet) {
                final String nameFile = file.getName();
                if (nameFile.toLowerCase().contains("servlet")) {
                    result.add(nameFile);
                    System.out.println("Trovata servlet: " + nameFile);
                }
            }
            return result;
        }
        throw new FileNotFoundException("La cartella servlet non esiste!");
    }
}
