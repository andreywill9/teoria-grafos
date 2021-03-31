package model;

public class Aresta {

  private int distancia;
  private int custo;
  private Vertice origem;
  private boolean ativa;
  private Vertice destino;

  public Aresta(int distancia, int custo, Vertice origem, Vertice destino) {
    this.distancia = distancia;
    this.custo = custo;
    this.origem = origem;
    this.destino = destino;
    this.ativa = true;
  }

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

}
