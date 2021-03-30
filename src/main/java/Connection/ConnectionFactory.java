package Connection;

import java.sql.*;

public class ConnectionFactory {

  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  private static final String URL = "jdbc:mysql://localhost:3306/";
  private static final String NOME_BANCO = "projetoGrafos";
  private static final String URL_BANCO = URL.concat(NOME_BANCO);
  private static final String USER = "USUARIO";
  private static final String PASS = "SENHA";

  private Connection conexao;

  public ConnectionFactory() throws Exception {
    conexao = getConnection(URL);
    executar("CREATE SCHEMA IF NOT EXISTS " + NOME_BANCO);
    conexao = getConnection(URL_BANCO);
  }

  private Connection getConnection(String url) {
    try {
      Class.forName(DRIVER);
      return DriverManager.getConnection(url, USER, PASS);
    } catch (ClassNotFoundException | SQLException ex) {
      throw new RuntimeException("Erro na conexão:", ex);
    }
  }

  public ResultSet buscar(String query) throws Exception {
    PreparedStatement ps = conexao.prepareStatement(query);
    return ps.executeQuery();
  }

  public void executar(String query) throws Exception {
    PreparedStatement ps = conexao.prepareStatement(query);
    if (ps.executeUpdate() < 0) {
      throw new Exception("Erro ao executar a query");
    }
  }

  public void fecharConexao() throws SQLException {
    if (!conexao.isClosed()) conexao.close();
  }

  public void abrirConexao() throws SQLException {
    if (conexao.isClosed()) {
      conexao = getConnection(URL_BANCO);
    }
  }
}
