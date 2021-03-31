package Connection;

import model.Vertice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            "\t('Porto Alegre','POA'),\n" +
            "    ('Florianópolis','FLO'),\n" +
            "    ('Blumenal','BLU'),\n" +
            "    ('Curitiba','CUR'),\n" +
            "    ('Londrina','LON'),\n" +
            "    ('São Paulo','SPO'),\n" +
            "    ('São José dos Campos','SJC'),\n" +
            "    ('Rio de Janeiro','RJO'),\n" +
            "    ('Belo Horizonte','BHO'),\n" +
            "    ('Campinas','CMP'),\n" +
            "    ('São José dos Campos','RBO'),\n" +
            "    ('Bauru','BAU'),\n" +
            "    ('Campo Grande','CPG'),\n" +
            "    ('Cuiabá','CUI'),\n" +
            "    ('Manaus','MAN'),\n" +
            "    ('Belém','BEL'),\n" +
            "    ('Brasília','BSB'),\n" +
            "    ('Natal','NTL'),\n" +
            "    ('Recife','REC'),\n" +
            "    ('Salvador','SLV')";

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

  /*public void listarCidades(ConnectionFactory conexao) throws Exception {
    this.conexao = conexao;
    listar();
  }

  public List<Vertice> listar() {
    ResultSet rs = null;
    String sql = "SELECT * FROM cidades";

    List<Vertice> cidades = new ArrayList();
    try {

      rs = conexao.buscar(sql);

      while (rs.next()) {
        Vertice cidade = new Vertice("","");
        cidade.setNome_cidade(rs.getString("nome_cidade"));
        cidade.setSigla(rs.getString("sigla"));
        cidade.setId_cidade(rs.getInt("Id_cidade"));
        cidade.setAtivo(rs.getBoolean("ativo"));
        cidades.add(cidade);
      }
    } catch (Exception e) {
      System.out.println("Erro não foi possivel Exibir a lista de cidades " + e);
    }

    return cidades;

  }
  public boolean inserir(Vertice cidade){
  String sql ="INSERT INTO cidades (nome_cidade, sigla) VALUES\n" +
            "\t\t(\"\",\"\");";
    PreparedStatement stmt = null;
    try {
      stmt = conexao.prepareStatement(sql) //verificar tirar duvida
      stmt.setString(1, cidade.getNome_cidade());
      stmt.setString(2, cidade.getSigla());

      stmt.executeUpdate();
      return true;
    } catch (SQLException ex) {
      System.out.println("não foi possivel inserir o produto" + ex);
      return false;
    }
  }*/

}
