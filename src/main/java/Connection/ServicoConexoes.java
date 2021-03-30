package Connection;

public class ServicoConexoes {

  private static final String CREATE_CONEXOES = "CREATE TABLE  IF NOT EXISTS `conexoes` (\n" +
      "  `origem` varchar(3) NOT NULL,\n" +
      "  `destino` varchar(3) NOT NULL,\n" +
      "  `saltos` int DEFAULT NULL,\n" +
      "  `distancia` int DEFAULT NULL,\n" +
      "  `custo` int DEFAULT NULL,\n" +
      "  `id_conexao` varchar(7) NOT NULL,\n" +
      "  `ativa` tinyint DEFAULT '1',\n" +
      "  PRIMARY KEY (`id_conexao`),\n" +
      "  UNIQUE KEY `idconexoes_UNIQUE` (`origem`),\n" +
      "  UNIQUE KEY `conexoescol_UNIQUE` (`destino`),\n" +
      "  UNIQUE KEY `id_conexao_UNIQUE` (`id_conexao`),\n" +
      "  CONSTRAINT `destino` FOREIGN KEY (`destino`) REFERENCES `cidades` (`sigla`),\n" +
      "  CONSTRAINT `origem` FOREIGN KEY (`origem`) REFERENCES `cidades` (`sigla`)\n" +
      ")";

  private ConnectionFactory conexao;

  public ServicoConexoes(ConnectionFactory conexao) throws Exception {
    this.conexao = conexao;
    this.conexao.executar(CREATE_CONEXOES);
  }

}
