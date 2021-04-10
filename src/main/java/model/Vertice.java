package model;

import java.sql.ResultSet;

public class Vertice {

  private String sigla;
  private String nomeCidade;
  private Boolean ativo;
  private int idCidade;

  public static Vertice instanciarDeResultSet(ResultSet rs) throws Exception {
    Vertice cidade = new Vertice();
    cidade.setNomeCidade(rs.getString("nome_cidade"));
    cidade.setSigla(rs.getString("sigla"));
    cidade.setIdCidade(rs.getInt("Id_cidade"));
    cidade.setAtivo(rs.getBoolean("status"));
    return cidade;
  }

  public static Vertice instanciarNovo(String nomeCidade, String sigla) {
    Vertice cidade = new Vertice();
    cidade.setNomeCidade(nomeCidade);
    cidade.setSigla(sigla);
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
}
