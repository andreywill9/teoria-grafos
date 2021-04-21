package Connection;

import java.sql.ResultSet;

public class ServicoEstados {

  private ConnectionFactory conexao;

  public ServicoEstados(ConnectionFactory conexao) throws Exception {
    this.conexao = conexao;
    criarTabela();
    popularDados();
  }

  private void criarTabela() throws Exception {
    String query = ArquivoUtils.arquivoParaString("estados/create-estados.txt");
    conexao.executar(query);
  }

  private void popularDados() throws Exception {
    String queryContar = "SELECT COUNT(*) FROM estados";
    ResultSet rsBusca = conexao.buscar(queryContar);
    rsBusca.next();
    if (rsBusca.getInt("COUNT(*)") == 0) {
      String query = ArquivoUtils.arquivoParaString("estados/insert-estados.txt");
      conexao.executar(query);
    }
  }

}
