package Connection;

import model.Aresta;
import model.Vertice;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
      "\t(1 , 3, 7 , 2),\n" +
      "  (2 , 3, 1 , 3),\n" +
      "  (2 , 4, 2 , 5),\n" +
      "  (2 , 8, 12 , 10),\n" +
      "  (3 , 4, 2 , 5),\n" +
      "  (4 , 5, 6 , 2),\n" +
      "  (4 , 6, 5 , 10),\n" +
      "  (5 , 6, 7 , 2),\n" +
      "  (5 , 12, 3 , 2),\n" +
      "  (6 , 8, 5 , 15),\n" +
      "  (6 , 10, 1 , 7),\n" +
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

  private static final String MAIOR_ID = "SELECT MAX(Id_conexao) FROM conexoes";

  private static final String ALTERAR_STATUS = "UPDATE conexoes SET ativo = %s WHERE Id_conexao = %s";

  private static final String EXCLUIR_CONEXAO = "DELETE FROM conexoes WHERE Id_conexao = %s";

  private static final String EXCLUIR_CONEXOES_LISTA = "DELETE from conexoes WHERE Id_conexao IN (%s)";

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

  public List<Aresta> buscarTodas(Map<Integer, Vertice> mapaCidades) throws Exception {
    String query = "SELECT * FROM conexoes";
    List<Aresta> listaAresta = new ArrayList<>();
    ResultSet rs = conexao.buscar(query);
    while (rs.next()) {
      listaAresta.add(Aresta.instanciarDeResultSet(rs, mapaCidades));
    }
    return listaAresta;
  }

  public void inserir(Aresta aresta, List<Aresta> listaAresta) throws Exception {
    if (!arestaValida(aresta) || !naoContemAresta(aresta, listaAresta)) throw new Exception("Aresta inv??lida");
    String query = String.format("INSERT INTO conexoes (Id_cidade1, Id_cidade2, distancia, custo) VALUES (" +
        "%s, %s, %s, %s)", aresta.getOrigem().getIdCidade(), aresta.getDestino().getIdCidade(), aresta.getDistancia(), aresta.getCusto());
    conexao.executar(query);
    aresta.setIdConexao(ultimoId());
  }

  private static boolean arestaValida(Aresta aresta) {
    return !aresta.getOrigem().equals(aresta.getDestino());
  }

  private static boolean naoContemAresta(Aresta aresta, List<Aresta> listaAresta) {
    return listaAresta.stream().noneMatch(aresta::equals);
  }

  private int ultimoId() throws Exception {
    ResultSet rs = conexao.buscar(MAIOR_ID);
    return rs.getInt(0);
  }

  public void alterarStatus(Aresta aresta, boolean novoStatus) throws Exception {
    String sql = String.format(
        ALTERAR_STATUS,
        novoStatus ? 1 : 0,
        aresta.getIdConexao()
    );
    conexao.executar(sql);
    aresta.setAtiva(novoStatus);
  }

  public void excluirConexao(Aresta aresta) throws Exception {
    String sql = String.format(
        EXCLUIR_CONEXAO,
        aresta.getIdConexao()
    );
    conexao.executar(sql);
  }

  public void excluirConexoes(List<Aresta> listaArestas) throws Exception {
    String idsExcuir = listaArestas.stream().map(Aresta::getIdConexao)
        .map(String::valueOf).collect(Collectors.joining(","));
    String sql = String.format(EXCLUIR_CONEXOES_LISTA, idsExcuir);
    conexao.executar(sql);
  }

}