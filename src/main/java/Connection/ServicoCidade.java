package Connection;

import model.Vertice;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServicoCidade {

  private static final String CREATE = "CREATE TABLE IF NOT EXISTS`cidades` (\n" +
      "  `Id_cidade` int NOT NULL AUTO_INCREMENT,\n" +
      "  `nome_cidade` varchar(45) NOT NULL,\n" +
      "  `status` tinyint NOT NULL DEFAULT '1',\n" +
      "  `sigla` varchar(3) NOT NULL,\n" +
      "   `cordenada_x` int NOT NULL, \n" +
      "   `cordenada_y` int NOT NULL, \n" +
      "  PRIMARY KEY (`Id_cidade`),\n" +
      "  UNIQUE KEY `Id_cidade_UNIQUE` (`Id_cidade`),\n" +
      "  UNIQUE KEY `sigla_UNIQUE` (`sigla`)\n" +
      " )";

  private static final String INSERT_PADRAO = "INSERT INTO cidades (nome_cidade, sigla, cordenada_x, cordenada_y) values\n" +
      "\t('Porto Alegre','POA', 471, 727), \n" +
      "  ('Florianópolis','FLO', 525, 677), \n" +
      "  ('Blumenau','BLU', 515, 663), \n" +
      "  ('Curitiba','CUR', 511, 633), \n" +
      "  ('Londrina','LON', 472, 589), \n" +
      "  ('São Paulo','SPO', 565, 594), \n" +
      "  ('São José dos Campos','SJC', 580, 587),\n" +
      "  ('Rio de Janeiro','RJO', 635, 581), \n" +
      "  ('Belo Horizonte','BHO', 620, 520), \n" +
      "  ('Campinas','CMP', 556, 581), \n" +
      "  ('Ribeirão Preto','RBP', 541, 546), \n" +
      "  ('Bauru','BAU', 515, 569), \n" +
      "  ('Campo Grande','CPG', 402, 531), \n" +
      "  ('Cuiabá','CUI', 372, 432), \n" +
      "  ('Manaus','MAN', 291, 176), \n" +
      "  ('Belém','BEL', 527, 142),\n" +
      "  ('Brasília','BSB', 538, 435), \n" +
      "  ('Natal','NTL', 798, 231), \n" +
      "  ('Recife','REC', 805, 277), \n" +
      "  ('Salvador','SLV', 731, 378)";

  private static final String MAIOR_ID = "SELECT MAX(Id_cidade) FROM cidades;";

  private static final String ALTERAR_STATUS = "UPDATE cidades SET status = %s WHERE Id_cidade = %s";

  private static final String EXCLUIR_CIDADE = "DELETE FROM cidades WHERE Id_cidade = %s";

  private ConnectionFactory conexao;

  public ServicoCidade(ConnectionFactory conexao) throws Exception {
    this.conexao = conexao;
    this.conexao.executar(CREATE);
    popularBanco();
  }

  public void popularBanco() throws Exception {
    String queryContar = "SELECT COUNT(*) FROM cidades";
    ResultSet rsBusca = conexao.buscar(queryContar);
    rsBusca.next();
    if (rsBusca.getInt("COUNT(*)") == 0) {
      conexao.executar(INSERT_PADRAO);
    }
  }

  public List<Vertice> listarCidades() throws Exception {
    String sql = "SELECT * FROM cidades";
    List<Vertice> cidades = new ArrayList<>();
    ResultSet rs = conexao.buscar(sql);
    while (rs.next()) {
      cidades.add(Vertice.instanciarDeResultSet(rs));
    }
    return cidades;
  }

  public void inserir(Vertice cidade) throws Exception {
    String sql = String.format("INSERT INTO cidades (nome_cidade, sigla, cordenada_x, cordenada_y) VALUES ('%s', '%s', %s, %s)",
        cidade.getNomeCidade(), cidade.getSigla(), cidade.getCordenadaX(), cidade.getCordenadaY());
    conexao.executar(sql);
    cidade.setIdCidade(ultimoId());
  }

  private int ultimoId() throws Exception {
    ResultSet rs = conexao.buscar(MAIOR_ID);
    return rs.getInt(0);
  }

  public void alterarStatus(Vertice cidade, boolean novoStatus) throws Exception {
    String sql = String.format(
        ALTERAR_STATUS,
        novoStatus ? 1 : 0,
        cidade.getIdCidade()
    );
    conexao.executar(sql);
    cidade.setAtivo(novoStatus);
  }

  public void excluirCidade(Vertice cidade) throws Exception {
    String sql = String.format(
        EXCLUIR_CIDADE,
        cidade.getIdCidade()
    );
    conexao.executar(sql);
  }

}
