package model.bellman.ford;

import model.Aresta;

/**
 * Classe responsável pelo objeto referente a uma Aresta, sendo auxiliar somente para a execução do algoritmo de Bellman Ford.
 * Possui atributos para a aresta a qual o Edge é referente e os Nodes de origem e destino
 */
public class Edge {

  public Aresta arestaAtual;

  public Node origem, destino;

  public Edge(Node origem, Node destino, Aresta aresta) {
    this.origem = origem;
    this.destino = destino;
    arestaAtual = aresta;
  }

  public Aresta getArestaAtual() {
    return arestaAtual;
  }

  public void setArestaAtual(Aresta arestaAtual) {
    this.arestaAtual = arestaAtual;
  }

  public Node getOrigem() {
    return origem;
  }

  public void setOrigem(Node origem) {
    this.origem = origem;
  }

  public Node getDestino() {
    return destino;
  }

  public void setDestino(Node destino) {
    this.destino = destino;
  }

  public Integer getPeso(MetricaCalculo metrica) {
    return arestaAtual.getPeso(metrica);
  }
}
