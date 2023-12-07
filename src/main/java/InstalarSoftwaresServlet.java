

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
 * Servlet implementation class InstalarSoftwaresServlet
 */
@WebServlet("/InstalarSoftwaresServlet")
public class InstalarSoftwaresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InstalarSoftwaresServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();

       
        String nome = request.getParameter("nome");
        int id_pc = Integer.parseInt(request.getParameter("id_pc"));

        Connection conexao = null;
        PreparedStatement stmtConsulta = null;
        PreparedStatement stmtInsercao = null;
        
        try {
           
            conexao = Conexao.getConnection();
            
            String query = "SELECT MAX(id_software) FROM softwares";
            stmtConsulta = conexao.prepareStatement(query); 
            ResultSet resultSet = stmtConsulta.executeQuery();
            
            int maiorID = 0;
            if (resultSet.next()) {
                maiorID = resultSet.getInt(1);
            }

            int novoID = maiorID + 1; 
            
            
            String insertQuery = "INSERT INTO softwares (id_software, nome, id_pc) VALUES (?, ?, ?)";
            stmtInsercao = conexao.prepareStatement(insertQuery);
            stmtInsercao.setInt(1, novoID);
            stmtInsercao.setString(2, nome);
            stmtInsercao.setInt(3, id_pc);

            
            stmtInsercao.executeUpdate();
            
            resultSet.close();
            stmtConsulta.close();
            stmtInsercao.close();
            conexao.close();
            
            

            response.setContentType("text/html");
            out.println("<html><head><link rel=\"stylesheet\" href=\"./Style.css\"><body>");
            out.println("<h2>Software instalado com Sucesso!</h2>");
            out.println("<form action='index.jsp'>");
            out.println("<input type='submit' id=\"cadastrar\" value='Voltar para o Index' >");
            out.println("</form>");
            out.println("</body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
