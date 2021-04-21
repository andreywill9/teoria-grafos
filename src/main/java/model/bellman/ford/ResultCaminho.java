/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bellman.ford;

import model.Vertice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alvaro
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
