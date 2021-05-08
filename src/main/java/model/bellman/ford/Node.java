package model.bellman.ford;

import model.Vertice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe que responsável pelo objeto Node referente a um Vértice, sendo auxiliar somente para o algoritmo de Bellman Ford.
 * Possui atributos referentes ao vértice o qual o Node é referente, o caminho de todos os vértices até chegar até ele e o valor percorrido pelo caminho
 */
public class Node {

  private List<Vertice> caminho;

  private Vertice verticeAtual;

  private Integer valorPercorrido;

  public Node(Vertice vertice) {
    verticeAtual = vertice;
    caminho = Collections.singletonList(vertice);
  }

  public List<Vertice> getCaminho() {
    return caminho;
  }

  public void setCaminho(List<Vertice> caminho) {
    this.caminho = caminho;
  }

  public Vertice getVerticeAtual() {
    return verticeAtual;
  }

  public void setVerticeAtual(Vertice verticeAtual) {
    this.verticeAtual = verticeAtual;
  }

  public Integer getValorPercorrido() {
    return valorPercorrido;
  }

  public void setValorPercorrido(Integer valorPercorrido) {
    this.valorPercorrido = valorPercorrido;
  }

  public void adicionarArestaCaminho(Vertice novoNode) {
    caminho.add(novoNode);
  }

  /**
   * Método que atualiza o caminho até chegar ao vértice ao qual o Node é referente, de forma que o caminho sempre seja
   * formado pelo menor caminho possivel ao fim da execução de Bellman Ford
   * @param node o objeto Node o qual deve ser passado para chegar até o Node atual, assumindo que este Node seja o menor caminho
   */
  public void atualizarCaminho(Node node) {
    List<Vertice> novoCaminho = new ArrayList<>(node.getCaminho());
    novoCaminho.add(getVerticeAtual());
    caminho = novoCaminho;
  }
}
