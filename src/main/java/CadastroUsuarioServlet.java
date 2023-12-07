import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DAO.Conexao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.ResultSet;

@WebServlet("/CadastroUsuarioServlet")
public class CadastroUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");

        Connection conexao = null;
        PreparedStatement stmtConsulta = null;
        PreparedStatement stmtInsercao = null;
        

        try {
     
            conexao = Conexao.getConnection();
            
            String query = "SELECT MAX(id_user) FROM usuarios";
            stmtConsulta = conexao.prepareStatement(query);
            ResultSet resultSet = stmtConsulta.executeQuery();
            
            int maiorID = 0;
            if (resultSet.next()) {
                maiorID = resultSet.getInt(1); 
            }

            int novoID = maiorID + 1;
            
            String insertQuery = "INSERT INTO usuarios (id_user, nome, email) VALUES (?, ?, ?)";
            stmtInsercao = conexao.prepareStatement(insertQuery);
            stmtInsercao.setInt(1, novoID);
            stmtInsercao.setString(2, nome);
            stmtInsercao.setString(3, email);

            // Execução da inserção
            stmtInsercao.executeUpdate();
            
            resultSet.close();
            stmtConsulta.close();
            stmtInsercao.close();
            conexao.close();
            
            

            response.setContentType("text/html");
            out.println("<html><head><link rel=\"stylesheet\" href=\"./Style.css\"><body>");
            out.println("<h2>Usuário Cadastrado com Sucesso!</h2>");
            out.println("<form action='index.jsp'>");
            out.println("<input type='submit' value='Voltar para o Index' id=\"cadastrar\">");
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
