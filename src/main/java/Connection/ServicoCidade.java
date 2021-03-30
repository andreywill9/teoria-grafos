package Connection;

public class ServicoCidade {

  private static final String CREATE_CIDADES = "CREATE TABLE IF NOT EXISTS `cidades` (\n" +
      "  `nome_cidade` varchar(45) NOT NULL,\n" +
      "  `sigla` varchar(45) NOT NULL,\n" +
      "  `ativo` tinyint NOT NULL DEFAULT '1',\n" +
      "  UNIQUE KEY `nome_cidade_UNIQUE` (`nome_cidade`),\n" +
      "  UNIQUE KEY `sigla_UNIQUE` (`sigla`)\n" +
      ")";

  private ConnectionFactory conexao;

  public ServicoCidade(ConnectionFactory conexao) throws Exception {
    this.conexao = conexao;
    this.conexao.executar(CREATE_CIDADES);
  }
}
