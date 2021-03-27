package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author joao
 */
public class ConnectionFactory {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";    
    private static final String URL = "jdbc:mysql://localhost:3306/dbloja";    
    private static final String USER = "root";    
    private static final String PASS = "Pass1234";    
    
    public static Connection getConnection() {
        try {
            
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
            
        } catch (ClassNotFoundException | SQLException ex) {
            
            throw new RuntimeException("Erro na conexão:", ex);
        }
    }
    
    public static void closeConection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                throw new RuntimeException("não foi possivel fechar à conexão", ex);
            }
        }
    }
    
    public static void closeConection(Connection con, PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                throw new RuntimeException("não foi possivel fechar à conexão", ex);
            }
        }
        
        closeConection(con);
    }
    
    public static void closeConection(Connection con, PreparedStatement stmt,ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                throw new RuntimeException("não foi possivel fechar à conexão", ex);
            }
        }
        
  
        closeConection(con, stmt);
    }
}
