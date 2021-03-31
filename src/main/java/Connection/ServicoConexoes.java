package Connection;

import model.Aresta;

import java.sql.ResultSet;

public class ServicoConexoes {

  private static final String CREATE_CONEXOES = "CREATE TABLE IF NOT EXISTS `conexoes` (\n" +
      "  `Id_conexao` int NOT NULL AUTO_INCREMENT,\n" +
      "  `Id_cidade1` int NOT NULL,\n" +
      "  `Id_cidade2` int NOT NULL,\n" +
      "  `salto` int NOT NULL DEFAULT '1',\n" +
      "  `distancia` int NOT NULL,\n" +
      "  `custo` int NOT NULL,\n" +
      "  `ativo` tinyint NOT NULL DEFAULT '1',\n" +
      "  PRIMARY KEY (`Id_conexao`),\n" +
      "  FOREIGN KEY (`Id_cidade2`) REFERENCES `cidades` (`Id_cidade`),\n" +
      "  FOREIGN KEY (`Id_cidade1`) REFERENCES `cidades` (`Id_cidade`) \n" +
      ")";

  private static final String INSERT_CONEXOES = "INSERT INTO conexoes (Id_cidade1, Id_cidade2, distancia, custo) VALUES\n" +
      "\t(1 , 2, 6 , 2),\n" +
      "  (1 , 3, 7 , 2),\n" +
      "  (2 , 3, 1 , 3),\n" +
      "  (2 , 4, 2 , 5),\n" +
      "  (2 , 8, 12 , 10),\n" +
      "  (3 , 4, 2 , 5),\n" +
      "  (4 , 5, 6 , 2),\n" +
      "  (4 , 6, 5 , 10),\n" +
      "  (5 , 6, 7 , 2),\n" +
      "  (5 , 12, 3 , 2),\n" +
      "  (6 , 8, 5 , 15),\n" +
      "  (6 , 10, 2 , 10),\n" +
      "  (6 , 7, 2 , 16),\n" +
      "  (7 , 10, 2 , 10),\n" +
      "  (8 , 7, 3 , 10),\n" +
      "  (8 , 9, 7 , 6),\n" +
      "  (8 , 20, 20 , 6),\n" +
      "  (9 , 7, 7 , 8),\n" +
      "  (9 , 17, 9 , 6),\n" +
      "  (10 , 12, 3 , 6),\n" +
      "  (10 , 11, 2 , 4),\n" +
      "  (11 , 17, 8 , 4),\n" +
      "  (12 , 13, 10 , 3),\n" +
      "  (13 , 14, 8 , 2),\n" +
      "  (14 , 15, 20 , 3),\n" +
      "  (15 , 16, 18 , 2),\n" +
      "  (16 , 18, 21 , 3),\n" +
      "  (17 , 15, 22 , 6),\n" +
      "  (17 , 18, 22 , 7),\n" +
      "  (18 , 19, 4 , 3),\n" +
      "  (19 , 20, 8 , 5),\n" +
      "  (20 , 18, 15 , 4)";

  private ConnectionFactory conexao;

  public ServicoConexoes(ConnectionFactory conexao) throws Exception {
    this.conexao = conexao;
    this.conexao.executar(CREATE_CONEXOES);
    popularBanco();
  }

  public void popularBanco() throws Exception {
    String queryContar = "SELECT COUNT(*) FROM conexoes";
    ResultSet rsBusca = conexao.buscar(queryContar);
    rsBusca.next();
    if (rsBusca.getInt("COUNT(*)") == 0) {
      conexao.executar(INSERT_CONEXOES);
    }
  }

  public void inserir(Aresta aresta) throws Exception {
    String query = String.format("INSERT INTO conexoes (Id_cidade1, Id_cidade2, distancia, custo) VALUES (" +
        "%s, %s, %s, %s)", aresta.getOrigem().getIdCidade(), aresta.getDestino().getIdCidade(), aresta.getDistancia(), aresta.getCusto());
    conexao.executar(query);
  }
}