package model.bellman.ford;

import model.Vertice;

import java.util.ArrayList;
import java.util.List;

/*
 * Classe que representa o resultado do cálculo do caminho mínimo, contento o valor percorrido na execução do algoritmo
 * e também a ordem dos vértices que devem ser percorridos para que seja encontrado tal valor.
 */
public class ResultCaminho {
  private int ValorPercorrido;
  private List<Vertice> vertice;

  public static ResultCaminho instanciar(Node node) {
    return new ResultCaminho(new ArrayList<>(node.getCaminho()), node.getValorPercorrido());
  }

  ResultCaminho(List<Vertice> vertice, int ValorPercorrido) {
    this.vertice = vertice;
    this.ValorPercorrido = ValorPercorrido;
  }

  public int getValorPercorrido() {
    return this.ValorPercorrido;
  }

  public List<Vertice> getVertice() {
    return this.vertice;
  }
}
