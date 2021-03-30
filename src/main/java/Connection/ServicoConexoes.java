package Connection;

public class ServicoConexoes {

  private static final String CREATE_CONEXOES = "CREATE TABLE `conexoes` (\n" +
      "  `Id_conexao` int NOT NULL AUTO_INCREMENT,\n" +
      "  `Id_cidade1` int NOT NULL,\n" +
      "  `Id_cidade2` int NOT NULL,\n" +
      "  `salto` int NOT NULL,\n" +
      "  `distancia` int NOT NULL,\n" +
      "  `custo` int NOT NULL,\n" +
      "  `ativo` tinyint NOT NULL DEFAULT '1',\n" +
      "  PRIMARY KEY (`Id_conexao`),\n" +
      "  UNIQUE KEY `Id_cidade1_UNIQUE` (`Id_cidade1`),\n" +
      "  UNIQUE KEY `Id_cidade2_UNIQUE` (`Id_cidade2`),\n" +
      "  CONSTRAINT `Destino` FOREIGN KEY (`Id_cidade2`) REFERENCES `cidades` (`Id_cidade`),\n" +
      "  CONSTRAINT `Origem` FOREIGN KEY (`Id_cidade1`) REFERENCES `cidades` (`Id_cidade`)\n" +
      ")";

  private ConnectionFactory conexao;

  public ServicoConexoes(ConnectionFactory conexao) throws Exception {
    this.conexao = conexao;
    this.conexao.executar(CREATE_CONEXOES);
  }

}
