package model;

import java.sql.ResultSet;

public class Vertice {

  private String sigla;
  private String nomeCidade;
  private Boolean ativo;
  private int idCidade, cordenadaX, cordenadaY;

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

  public static Vertice instanciarNovo(String nomeCidade, String sigla, int cordenadaX, int cordenadaY) {
    Vertice cidade = new Vertice();
    cidade.setNomeCidade(nomeCidade);
    cidade.setSigla(sigla);
    cidade.setCordenadaX(cordenadaX);
    cidade.setCordenadaY(cordenadaY);
    return cidade;
  }

  public Vertice(String sigla, String nomeCidade) {
    this.sigla = sigla;
    this.nomeCidade = nomeCidade;
    this.ativo = true;
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
