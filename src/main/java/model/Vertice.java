package model;

import java.sql.ResultSet;

/**
 * Classe responsável pelo objeto que representa um Vértice
 * É um objeto que representa diretamente como os dados sao armazenados no banco de dados,
 * tendo a funçao de fazer a ponte entre o Vértice usado na aplicaçao com o que e armazenado no banco
 * Possui como atributo o seu id na tabela Cidades, a sigla que representa a cidade, o nome da mesma, se está ativada e
 * as coordenadas de x e y para serem mostradas na interface com o mapa
 */
public class Vertice {

  private String sigla;
  private String nomeCidade;
  private Boolean ativo;
  private int idCidade, cordenadaX, cordenadaY;

  /**
   * Método que instancia um novo objeto Vértice a partir dos dados contidos no banco
   * @param rs representa o ResultSet em uma determinada posição para que seja buscado os atributos das colunas
   * @return um objeto Vértice
   * @throws Exception caso a linha atual do Resultset seja nula ou com dados inválidos
   */
  public static Vertice instanciarDeResultSet(ResultSet rs) throws Exception {
    Vertice cidade = new Vertice();
    cidade.setNomeCidade(rs.getString("nome_cidade"));
    cidade.setSigla(rs.getString("sigla"));
    cidade.setIdCidade(rs.getInt("Id_cidade"));
    cidade.setAtivo(rs.getBoolean("status"));
    cidade.setCordenadaX(rs.getInt("cordenada_x"));
    cidade.setCordenadaY(rs.getInt("cordenada_y"));
    return cidade;
  }

  /**
   * Método que instancia um novo Vértice com os dados fornecidos pelo usuário
   * @param nomeCidade o nome da cidade que está sendo criada
   * @param sigla a sigla que representa a cidade
   * @param cordenadaX a coordenada em x do local em que a cidade é representada no mapa
   * @param cordenadaY a coordenada y do local em que a cidade é representada no mapa
   * @return um objeto Vértice
   */
  public static Vertice instanciarNovo(String nomeCidade, String sigla, int cordenadaX, int cordenadaY) {
    Vertice cidade = new Vertice();
    cidade.setNomeCidade(nomeCidade);
    cidade.setSigla(sigla);
    cidade.setCordenadaX(cordenadaX);
    cidade.setCordenadaY(cordenadaY);
    return cidade;
  }

  public Vertice() {}

  @Override
  public String toString() {
    return this.sigla;
  }

  public String getSigla() {
    return sigla;
  }

  public void setSigla(String sigla) {
    this.sigla = sigla;
  }

  public String getNomeCidade() {
    return nomeCidade;
  }

  public void setNomeCidade(String nomeCidade) {
    this.nomeCidade = nomeCidade;
  }

  public Boolean getAtivo() {
    return ativo;
  }

  public void setAtivo(Boolean ativo) {
    this.ativo = ativo;
  }

  public int getIdCidade() {
    return idCidade;
  }

  public void setIdCidade(int idCidade) {
    this.idCidade = idCidade;
  }

  public int getCordenadaX() {
    return cordenadaX;
  }

  public void setCordenadaX(int cordenadaX) {
    this.cordenadaX = cordenadaX;
  }

  public int getCordenadaY() {
    return cordenadaY;
  }

  public void setCordenadaY(int cordenadaY) {
    this.cordenadaY = cordenadaY;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Vertice vertice = (Vertice) o;

    return idCidade == vertice.idCidade;
  }

  @Override
  public int hashCode() {
    return idCidade;
  }
}
