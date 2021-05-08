package model;

import model.bellman.ford.MetricaCalculo;

import java.sql.ResultSet;
import java.util.Map;
import java.util.Objects;

/**
 * Classe responsável pelo objeto que representa uma aresta
 * É um objeto que representa diretamente como os dados sao armazenados no banco de dados,
 * tendo a funçao de fazer a ponte entre a Aresta usada na aplicaçao com o que e armazenado no banco
 * Possui como atributo o seu id na tabela Conexoes, a medida de distancia, custo, se estaa se encontra ativa e os vertices
 * que se encontram em cada extremidade
 */
public class Aresta {

  private int idConexao;
  private int distancia;
  private int custo;
  private Vertice origem;
  private boolean ativa;
  private Vertice destino;

  /**
   * Metodo que instancia um novo objeto Aresta a partir dos dados contidos no banco
   * @param rs representa o ResultSet em uma determinada posiçao para que seja buscado os atributos das colunas
   * @param mapaCidades um HashMap com todas as cidades (vertices) tendo o id delas como chave, assim e  buscado a origem
   *                    e destino a partir de seus ids
   * @return um objeto Aresta
   * @throws Exception caso a linha atual do ResultSet seja nula ou os dados inválidos
   */
  public static Aresta instanciarDeResultSet(ResultSet rs, Map<Integer, Vertice> mapaCidades) throws Exception {
    Aresta conexao = new Aresta();
    conexao.setIdConexao(rs.getInt("Id_conexao"));
    conexao.setOrigem(mapaCidades.get(rs.getInt("Id_cidade1")));
    conexao.setDestino(mapaCidades.get(rs.getInt("Id_cidade2")));
    conexao.setDistancia(rs.getInt("distancia"));
    conexao.setCusto(rs.getInt("custo"));
    conexao.setAtiva(rs.getBoolean("ativo"));
    return conexao;
  }

  /**
   * Metodo que instancia um objeto Aresta a partir dos dados fornecidos pelo usuario
   * @param cidade1 a origem da Aresta
   * @param cidade2 o destino da Aresta
   * @param distancia a medida de distancia das duas cidades
   * @param custo o custo ao ir de uma cidade a outra
   * @return um objeto Aresta
   */
  public static Aresta instanciarNova(Vertice cidade1, Vertice cidade2, int distancia, int custo) {
    Aresta conexao = new Aresta();
    conexao.setOrigem(cidade1);
    conexao.setDestino(cidade2);
    conexao.setDistancia(distancia);
    conexao.setCusto(custo);
    return conexao;
  }

  public Aresta() {}

  public int getDistancia() {
    return distancia;
  }

  public void setDistancia(int distancia) {
    this.distancia = distancia;
  }

  public int getCusto() {
    return custo;
  }

  public void setCusto(int custo) {
    this.custo = custo;
  }

  public Vertice getOrigem() {
    return origem;
  }

  public void setOrigem(Vertice origem) {
    this.origem = origem;
  }

  public Vertice getDestino() {
    return destino;
  }

  public void setDestino(Vertice destino) {
    this.destino = destino;
  }

  public boolean isAtiva() {
    return ativa;
  }

  public void setAtiva(boolean ativa) {
    this.ativa = ativa;
  }

  public int getIdConexao() {
    return idConexao;
  }

  public void setIdConexao(int idConexao) {
    this.idConexao = idConexao;
  }

  public boolean getArestaDisponivel() {
    return ativa && getOrigem().getAtivo() && getDestino().getAtivo();
  }

  /**
   * Metodo que seleciona a metrica desejada da Aresta em questao
   * @param metrica o tipo de metrica desejada, podendo ser distancia, salto ou custo
   * @return um numero inteiro representando o valor da metrica desejada
   */
  public int getPeso(MetricaCalculo metrica) {
    if (metrica == MetricaCalculo.SALTO) return 1;
    return metrica == MetricaCalculo.DISTANCIA ?
        getDistancia() :
        getCusto();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Aresta)) return false;
    Aresta aresta = (Aresta) o;
    return getDistancia() == aresta.getDistancia() && getCusto() == aresta.getCusto() && isAtiva() == aresta.isAtiva()
        && cidadesIguais(aresta);
  }

  private boolean cidadesIguais(Aresta aresta) {
    return (getOrigem().equals(aresta.getOrigem()) && getDestino().equals(aresta.getDestino())) ||
        (getOrigem().equals(aresta.getDestino()) && getDestino().equals(aresta.getOrigem()));
  }


}
