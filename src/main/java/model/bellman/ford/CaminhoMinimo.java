package model.bellman.ford;

import model.Aresta;
import model.Vertice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CaminhoMinimo {

  public static void bellmanFord(List<Aresta> arestas, List<Vertice> vertices, Vertice origem, Vertice destino, MetricaCalculo metrica) throws Exception {
    List<Node> verticesDisponiveis = vertices.stream().filter(Vertice::getAtivo).map(Node::new).collect(Collectors.toList());
    List<Edge> arestasDisponiveis = new ArrayList<>();
    arestas.stream().filter(Aresta::getArestaDisponivel).forEach(aresta -> {
      Node node1 = getNode(aresta.getOrigem(), verticesDisponiveis);
      Node node2 = getNode(aresta.getDestino(), verticesDisponiveis);
      arestasDisponiveis.add(new Edge(node1, node2, aresta));
      arestasDisponiveis.add(new Edge(node2, node1, aresta));
    });
    Node nodeOrigem = getNode(origem, verticesDisponiveis);
    Node nodeDestino = getNode(destino, verticesDisponiveis);
    if (nodeDestino == null || nodeOrigem == null) throw new Exception("Origem ou destino está inativo");
    bellmanFord(nodeOrigem, verticesDisponiveis, arestasDisponiveis, metrica);
    nodeDestino.getCaminho().forEach(System.out::println);
    System.out.println(nodeDestino.getValorPercorrido());
  }

  public static Node getNode(Vertice vertice, List<Node> nodes) {
    return nodes.stream().filter(node -> node.getVerticeAtual().getIdCidade() == vertice.getIdCidade()).findFirst()
        .orElse(null);
  }

  private static void bellmanFord(Node origem, List<Node> vertices, List<Edge> arestas, MetricaCalculo metrica) {
    origem.setValorPercorrido(0);
    for (int i = 0; i < vertices.size() - 1; i++) {
      for (Edge aresta : arestas) {
        if (aresta.getOrigem().getValorPercorrido() == null) {
          continue;
        }
        Node origemAresta = aresta.getOrigem();
        Node destinoAresta = aresta.getDestino();
        int novoValor = origemAresta.getValorPercorrido() + aresta.getPeso(metrica);
        int valorDestino = destinoAresta.getValorPercorrido() != null ?
            destinoAresta.getValorPercorrido() : Integer.MAX_VALUE;
        if (novoValor < valorDestino) {
          destinoAresta.setValorPercorrido(novoValor);
          destinoAresta.atualizarCaminho(origemAresta);
        }
      }
    }
  }

}
