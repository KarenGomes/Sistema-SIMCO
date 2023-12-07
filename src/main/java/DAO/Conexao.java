package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {

	    private static final String URL = "jdbc:mysql://localhost:3306/universidade_db?serverTimezone=America/Sao_Paulo";
	    private static final String USER = "root";
	    private static final String PASSWORD = "1234";

	    static {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }

	    public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(URL, USER, PASSWORD);
	    }
}

