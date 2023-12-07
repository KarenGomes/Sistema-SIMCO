import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.ResultSet;
import DAO.Conexao;

@WebServlet("/CadastroComputadorServlet")
public class CadastroComputadorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

       
        String descr = request.getParameter("descr");
        int idlabs = Integer.parseInt(request.getParameter("idlabs"));

        Connection conexao = null;
        PreparedStatement stmtConsulta = null;
        PreparedStatement stmtInsercao = null;

        try {
            conexao = Conexao.getConnection();
            
            String query = "SELECT MAX(id_pc) FROM pc";
            stmtConsulta = conexao.prepareStatement(query); 
            ResultSet resultSet = stmtConsulta.executeQuery();
            
            int maiorID = 0;
            if (resultSet.next()) {
                maiorID = resultSet.getInt(1);
            }

            int novoID = maiorID + 1; 
            
            
            String insertQuery = "INSERT INTO pc (id_pc, descr, id_labs) VALUES (?, ?, ?)";
            stmtInsercao = conexao.prepareStatement(insertQuery);
            stmtInsercao.setInt(1, novoID);
            stmtInsercao.setString(2, descr);
            stmtInsercao.setInt(3, idlabs);

            
            stmtInsercao.executeUpdate();
            
            resultSet.close();
            stmtConsulta.close();
            stmtInsercao.close();
            conexao.close();
            
            

            response.setContentType("text/html");
            out.println("<html><head><link rel=\"stylesheet\" href=\"./Style.css\"><body>");
            out.println("<h2>Computador Cadastrado com Sucesso!</h2>");
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
