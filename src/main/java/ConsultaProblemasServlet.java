

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

@WebServlet("/ConsultaProblemasServlet")
public class ConsultaProblemasServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String opcaoConsulta = request.getParameter("opcaoConsulta");
        String sqlQuery = "";

        
        try {
    
            Connection conexao = Conexao.getConnection();

            if (opcaoConsulta.equals("problemasData")) {
                String dataInicial = request.getParameter("dataInicial");
                String dataFinal = request.getParameter("dataFinal");
                sqlQuery = "SELECT * FROM problemas WHERE date BETWEEN '" + dataInicial + "' AND '" + dataFinal + "'";
            } else if (opcaoConsulta.equals("problemasLaboratorio")) {
                String labEspecifico = request.getParameter("labEspecifico");
                sqlQuery = "SELECT * FROM problemas WHERE id_labs = '" + labEspecifico + "'";
            } else if (opcaoConsulta.equals("problemasUsuario")) {
                String nomeUsuario = request.getParameter("idUsuario");
                sqlQuery = "SELECT * FROM problemas WHERE id_user = '" + nomeUsuario + "'";
            } else if (opcaoConsulta.equals("problemasComputador")) {
                String idPC = request.getParameter("idPC");
                sqlQuery = "SELECT * FROM problemas WHERE id_pc = '" + idPC + "'";
            } else if (opcaoConsulta.equals("problemasHardware")) {
                sqlQuery = "SELECT * FROM problemas WHERE tipo = 'hardware'";
            } else if (opcaoConsulta.equals("problemasSoftware")) {
                sqlQuery = "SELECT * FROM problemas WHERE tipo = 'software'";
            }
            else if (opcaoConsulta.equals("todosProblemas")) {
                sqlQuery = "SELECT * FROM problemas";
            }

   
            java.sql.Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            out.println("<html><head><link rel=\"stylesheet\" href=\"./Style.css\"><body>");
            out.println("<h2>Resultados da Consulta:</h2>");
            out.println("<table border='1'>");
            while (resultSet.next()) {
                out.println("________________________________________" + "<br><br>" +
                		"ID do PC: " + resultSet.getInt("id_pc") + "<br>" 
                   		+ "ID do Usuário: "+resultSet.getInt("id_user") + "<br>"
                		+"Descrição do problema: "+resultSet.getString("descr") + "<br>" 
                        +"Tipo de problema: "+resultSet.getString("tipo") + "<br>" 
               		    +"Data do cadastro do problema: "+resultSet.getDate("date") + "<br>" + "<br>");
                
             
            }
            out.println("________________________________________");
            out.println("</table>");
            out.println("<br><br><button><a href=\"ChecarProblemas.jsp\">Realize mais consultas</button>");
            out.println("<br><button><a href=\"index.jsp\">Pagina inicial</a></button>");
            out.println("</body></html>");


            resultSet.close();
            statement.close();
            conexao.close();
        } catch (SQLException e) {
            out.println("Erro: " + e.getMessage());
        } finally {
            out.close();
        }
    }
}