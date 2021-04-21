package Connection;

import java.sql.ResultSet;

public class ServicoMunicipios {

  private final ConnectionFactory conexao;

  public ServicoMunicipios(ConnectionFactory conexao) throws Exception {
    this.conexao = conexao;
    criarTabela();
    popularDados();
  }

  private void criarTabela() throws Exception {
    String query = ArquivoUtils.arquivoParaString("municipios/create-municipios.txt");
    conexao.executar(query);
  }

  private void popularDados() throws Exception {
    String queryContar = "SELECT COUNT(*) FROM municipios";
    ResultSet rsBusca = conexao.buscar(queryContar);
    rsBusca.next();
    if (rsBusca.getInt("COUNT(*)") == 0) {
      String query = ArquivoUtils.arquivoParaString("municipios/insert-municipios.txt");
      conexao.executar(query);
    }
  }

}
