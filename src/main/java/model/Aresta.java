package model;

import model.bellman.ford.MetricaCalculo;

import java.sql.ResultSet;
import java.util.Map;
import java.util.Objects;

public class Aresta {

  private int idConexao;
  private int distancia;
  private int custo;
  private Vertice origem;
  private boolean ativa;
  private Vertice destino;

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

  public static Aresta instanciarNova(Vertice cidade1, Vertice cidade2, int distancia, int custo) {
    Aresta conexao = new Aresta();
    conexao.setOrigem(cidade1);
    conexao.setDestino(cidade2);
    conexao.setDistancia(distancia);
    conexao.setCusto(custo);
    return conexao;
  }

  public static Aresta instanciarInversa(Aresta aresta) {
    Aresta novaAresta = new Aresta();
    novaAresta.setIdConexao(aresta.getIdConexao());
    novaAresta.setOrigem(aresta.getDestino());
    novaAresta.setDestino(aresta.getOrigem());
    novaAresta.setDistancia(aresta.getDistancia());
    novaAresta.setCusto(aresta.getCusto());
    novaAresta.setAtiva(aresta.isAtiva());
    return novaAresta;
  }

  public Aresta(int distancia, int custo, Vertice origem, Vertice destino) {
    this.distancia = distancia;
    this.custo = custo;
    this.origem = origem;
    this.destino = destino;
    this.ativa = true;
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
