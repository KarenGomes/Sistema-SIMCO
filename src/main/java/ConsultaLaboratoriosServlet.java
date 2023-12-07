

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import DAO.Conexao;

/**
 * Servlet implementation class ConsultaLaboratoriosServlet
 */
@WebServlet("/ConsultaLaboratoriosServlet")
public class ConsultaLaboratoriosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaLaboratoriosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@SuppressWarnings("resource")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=UTF-8");
	     PrintWriter out = response.getWriter();
	       
	     String opcao = request.getParameter("opcao");
	     String sqlQuery = "";

	     try {
	         
	         Connection conexao = Conexao.getConnection();
	         java.sql.Statement statement = conexao.createStatement();

	         ResultSet resultSet = null;

	         out.println("<html><head><link rel=\"stylesheet\" href=\"./Style.css\"></head><body>");
	         out.println("<h1>Resultados da Consulta:</h1>");
	         out.println("<table border='1'>");

	         if (opcao.equals("TodosHardwares")) {
	             sqlQuery = "SELECT * FROM pc;";
	             resultSet = statement.executeQuery(sqlQuery);
	             while (resultSet.next()) {
	                 out.println("________________________________________" + "<br><br>");
	                 out.println("ID do PC: " + resultSet.getInt("id_pc") + "<br>");
	                 out.println("ID do Laboratório: " + resultSet.getInt("id_labs") + "<br>");
	                 
	             }
	         } else if (opcao.equals("TodosSoftwares")) {
	        	 sqlQuery = "SELECT s.*, p.id_labs FROM softwares s JOIN pc p ON s.id_pc = p.id_pc JOIN labs l ON p.id_labs = l.id_labs;";
	        	    resultSet = statement.executeQuery(sqlQuery);
	        	    while (resultSet.next()) {
	        	        out.println("________________________________________" + "<br><br>");
	        	        out.println("ID do Software: " + resultSet.getInt("id_software") + "<br>");
	        	        out.println("Nome do Software: " + resultSet.getString("nome") + "<br>");
	        	        out.println("ID do PC: " + resultSet.getInt("id_pc") + "<br>");
	        	        out.println("ID do Laboratório: " + resultSet.getInt("id_labs") + "<br>");
	        	        
	        	    }
	         } else if (opcao.equals("EspecificoHardware")) {
	             String labEspecifico = request.getParameter("labEspecifico");
	             sqlQuery = "SELECT pc.* FROM pc WHERE pc.id_labs = '" + labEspecifico + "';";
	             resultSet = statement.executeQuery(sqlQuery);
	             while (resultSet.next()) {
	                 out.println("________________________________________" + "<br><br>");
	                 out.println("ID do PC: " + resultSet.getInt("id_pc") + "<br>");
	                 out.println("ID do Laboratório: " + resultSet.getInt("id_labs") + "<br>");
	                
	             }
	         } else if (opcao.equals("EspecificoSoftware")) {
	        	 String labEspecifico = request.getParameter("labEspecifico");
	        	    sqlQuery = "SELECT s.*, p.id_labs FROM softwares s JOIN pc p ON s.id_pc = p.id_pc JOIN labs l ON p.id_labs = l.id_labs WHERE l.id_labs = '"
	        	            + labEspecifico + "';";
	        	    resultSet = statement.executeQuery(sqlQuery);
	        	    while (resultSet.next()) {
	        	        out.println("________________________________________" + "<br><br>");
	        	        out.println("ID do Software: " + resultSet.getInt("id_software") + "<br>");
	        	        out.println("ID do PC: " + resultSet.getInt("id_pc") + "<br>");
	        	        out.println("ID do Laboratório: " + resultSet.getInt("id_labs") + "<br>");
	        	    
	        	    }
	         }
	         out.println("________________________________________" + "<br>");
	         out.println("</table>");
	         out.println("<br><br><button><a href=\"Laboratorios.jsp\">Realize mais consultas</button>");
	         out.println("<br><button><a href=\"index.jsp\">Página inicial</a></button>");
	         out.println("</body></html>");

	         resultSet.close();
	         statement.close();
	         conexao.close();
	     }catch (SQLException e) {
	    	 out.println("Erro: " + e.getMessage());
	     }finally {
	    	 out.close();
	     }
	}

}
