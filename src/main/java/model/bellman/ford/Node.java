package model.bellman.ford;

import model.Vertice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

  public void atualizarCaminho(Node node) {
    List<Vertice> novoCaminho = new ArrayList<>(node.getCaminho());
    novoCaminho.add(getVerticeAtual());
    caminho = novoCaminho;
  }
}
