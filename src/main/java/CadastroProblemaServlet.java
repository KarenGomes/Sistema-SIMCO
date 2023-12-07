

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DAO.Conexao;

/**
 * Servlet implementation class CadastroProblemaServlet
 */
@WebServlet("/CadastroProblemaServlet")
public class CadastroProblemaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private boolean verificarExistenciaId(Connection conexao, String tabela, String colunaId, int id) throws SQLException {
	    String query = "SELECT COUNT(*) FROM " + tabela + " WHERE " + colunaId + " = ?";
	    try (PreparedStatement stmt = conexao.prepareStatement(query)) {
	        stmt.setInt(1, id);
	        ResultSet resultSet = stmt.executeQuery();
	        if (resultSet.next()) {
	            int count = resultSet.getInt(1);
	            return count > 0;
	        }
	    }
	    return false;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();

       
        String descr = request.getParameter("descr");
        String tipo = request.getParameter("tipo");
        String date = request.getParameter("date");
        int id_pc = Integer.parseInt(request.getParameter("id_pc"));
        int id_user = Integer.parseInt(request.getParameter("id_user"));
        int id_labs = Integer.parseInt(request.getParameter("labs"));

        Connection conexao = null;
        PreparedStatement stmtConsulta = null;
        PreparedStatement stmtInsercao = null;
       
        
        try {
        	
            conexao = Conexao.getConnection();
            

            String query = "SELECT MAX(id_problema) FROM problemas";
            stmtConsulta = conexao.prepareStatement(query); 
            ResultSet resultSet = stmtConsulta.executeQuery();
            
            int maiorID = 0;
            if (resultSet.next()) {
                maiorID = resultSet.getInt(1);
            }

            int novoID = maiorID + 1; 
            
            if (!verificarExistenciaId(conexao, "pc", "id_pc", id_pc)) {
                out.println("<html><head><link rel=\"stylesheet\" href=\"./Style.css\"></head><body>");
                out.println("<h2>ID de PC inválido!</h2>");
                out.println("<form action='index.jsp'>");
                out.println("<input type='submit' value='Voltar para o Index' id=\"cadastrar\">");
                out.println("</form>");
                out.println("</body></html>");
                return;
            }
            
            if (!verificarExistenciaId(conexao, "usuarios", "id_user", id_user)) {
                out.println("<html><head><link rel=\"stylesheet\" href=\"./Style.css\"></head><body>");
                out.println("<h2>ID de Usuário inválido!</h2>");
                out.println("<form action='index.jsp'>");
                out.println("<input type='submit' value='Voltar para o Index' id=\"cadastrar\">");
                out.println("</form>");
                out.println("</body></html>");
                return;
            }
            
            
            
            String insertQuery = "INSERT INTO problemas (id_problema, descr, tipo, date, id_pc, id_user, id_labs) VALUES (?, ?, ?, ?, ?, ?, ? )";
            stmtInsercao = conexao.prepareStatement(insertQuery);
            stmtInsercao.setInt(1, novoID);
            stmtInsercao.setString(2, descr);
            stmtInsercao.setString(3, tipo);
            stmtInsercao.setString(4, date);
            stmtInsercao.setInt(5, id_pc);
            stmtInsercao.setInt(6, id_user);
            stmtInsercao.setInt(7, id_labs);

            
            stmtInsercao.executeUpdate();
            
            resultSet.close();
            stmtConsulta.close();
            stmtInsercao.close();
            conexao.close();
            
            

            response.setContentType("text/html");
            out.println("<html><head><link rel=\"stylesheet\" href=\"./Style.css\"></head><body>");
            out.println("<h2>Problema cadastrado com sucesso!</h2>");
            out.println("<form action='index.jsp'>");
            out.println("<input type='submit' value='Voltar para o Index' id=\"cadastrar\">");
            out.println("</form>");
            out.println("</body></html>");
        }catch(SQLException e) {
        	e.printStackTrace();
            out.println("<html><head><link rel=\"stylesheet\" href=\"./Style.css\"></head><body>");
            out.println("<h2>Erro ao cadastrar o problema!</h2>");
            out.println("<form action='index.jsp'>");
            out.println("<input type='submit' value='Voltar para o Index' id=\"cadastrar\">");
            out.println("</form>");
            out.println("</body></html>");
        }finally {
        	try {
                if (stmtConsulta  != null) {
                	stmtConsulta.close();
                }
                if (stmtInsercao  != null) {
                	stmtInsercao.close();
                } 
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}

}
