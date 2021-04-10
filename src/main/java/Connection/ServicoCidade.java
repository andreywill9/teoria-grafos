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
      "  PRIMARY KEY (`Id_cidade`),\n" +
      "  UNIQUE KEY `Id_cidade_UNIQUE` (`Id_cidade`),\n" +
      "  UNIQUE KEY `sigla_UNIQUE` (`sigla`)\n" +
      " )";

  private static final String INSERT_PADRAO = "INSERT INTO cidades (nome_cidade, sigla) values\n" +
      "\t('Porto Alegre','POA'), \n" +
      "  ('Florianópolis','FLO'), \n" +
      "  ('Blumenal','BLU'), \n" +
      "  ('Curitiba','CUR'), \n" +
      "  ('Londrina','LON'), \n" +
      "  ('São Paulo','SPO'), \n" +
      "  ('São José dos Campos','SJC'),\n" +
      "  ('Rio de Janeiro','RJO'), \n" +
      "  ('Belo Horizonte','BHO'), \n" +
      "  ('Campinas','CMP'), \n" +
      "  ('Ribeirão Preto','RBP'), \n" +
      "  ('Bauru','BAU'), \n" +
      "  ('Campo Grande','CPG'), \n" +
      "  ('Cuiabá','CUI'), \n" +
      "  ('Manaus','MAN'), \n" +
      "  ('Belém','BEL'),\n" +
      "  ('Brasília','BSB'), \n" +
      "  ('Natal','NTL'), \n" +
      "  ('Recife','REC'), \n" +
      "  ('Salvador','SLV')";

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
    String sql = String.format("INSERT INTO cidades (nome_cidade, sigla) VALUES ('%s', '%s')",
        cidade.getNomeCidade(), cidade.getSigla());
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
