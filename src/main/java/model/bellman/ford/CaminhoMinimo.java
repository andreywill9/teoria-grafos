package model.bellman.ford;

import model.Aresta;
import model.Vertice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe em que está contida toda a lógica referente ao cálculo de caminho mínimo
 */
public abstract class CaminhoMinimo {

  /**
   * Método com a função de executar o cálculo do menor caminho entre um local e outro selecionado
   * @param arestas a lista de todas as ligações entre cidades disponíveis
   * @param vertices a lista de todas as cidades disponíveis
   * @param origem a cidade em que se quer começar o trajeto
   * @param destino a cidade em que se quer terminar o trajeto
   * @param metrica a métrica utilizada para se calcular o menor caminho
   * @return um objeto ResultCaminho com os dados do menor caminho encontrado entre as duas cidades
   * @throws Exception caso um vértice importante para o caminho mínimo esteja desativado
   */
  public static ResultCaminho bellmanFord(List<Aresta> arestas, List<Vertice> vertices, Vertice origem, Vertice destino, MetricaCalculo metrica) throws Exception {
    List<Node> verticesDisponiveis = vertices.stream().filter(Vertice::getAtivo).map(Node::new).collect(Collectors.toList());
    List<Edge> arestasDisponiveis = new ArrayList<>();
    arestas.stream().filter(Aresta::getArestaDisponivel).forEach(aresta ->
      adicionarArestaLista(aresta, getNode(aresta.getOrigem(), verticesDisponiveis),
          getNode(aresta.getDestino(), verticesDisponiveis), arestasDisponiveis)
    );
    Node nodeOrigem = getNode(origem, verticesDisponiveis);
    Node nodeDestino = getNode(destino, verticesDisponiveis);
    if (nodeDestino == null || nodeOrigem == null) throw new Exception("Origem ou destino está inativo");
    bellmanFord(nodeOrigem, verticesDisponiveis, arestasDisponiveis, metrica);
    return ResultCaminho.instanciar(nodeDestino);
  }

  /**
   * Método que adiciona a partir de uma aresta seus Edge equivalentes, considerando ida e volta
   * @param aresta a aresta a qual se quer gerar Edges
   * @param origem um objeto Node representando o vértice de origem da aresta
   * @param destino um objeto Node representando o vértice de destino da aresta
   * @param listaAresta a lista em que estão sendo armazenados todos os Edges
   */
  private static void adicionarArestaLista(Aresta aresta, Node origem, Node destino, List<Edge> listaAresta) {
    listaAresta.add(new Edge(origem, destino, aresta));
    listaAresta.add(new Edge(destino, origem, aresta));
  }

  /**
   * Método que busca na lista de Nodes qual o equivalente a um Vértice em especifico
   * @param vertice o vértice a qual se quer achar o Node correspondente
   * @param nodes a lista em que estão armazenados todos os Nodes
   * @return um objeto Node que corresponde ao Vértice desejado, ou null caso não seja encontrado
   */
  private static Node getNode(Vertice vertice, List<Node> nodes) {
    return nodes.stream().filter(node -> node.getVerticeAtual().getIdCidade() == vertice.getIdCidade()).findFirst()
        .orElse(null);
  }

  /**
   * Método responsável pela lógica por trás do algoritmo de Bellman Ford.
   * @param origem objeto Node representando o local de origem
   * @param vertices a lista de todos os Nodes referentes a todas as cidades disponíveis
   * @param arestas a lista de todos os Edges referentes a todas as arestas disponiveis
   * @param metrica a métrica que é utilizada para o cálculo do caminho mínimo
   */
  private static void bellmanFord(Node origem, List<Node> vertices, List<Edge> arestas, MetricaCalculo metrica) {
    // Inicialmente o valor percorrido de todos os Nodes são null, então inicia-se a distância de origem para 0
    origem.setValorPercorrido(0);
    // Faz-se necessária iteração uma quantidade de vezes igual a quantidade de Nodes - 1
    for (int i = 0; i < vertices.size() - 1; i++) {
      // Em cada iteração, percorre todas os Edges disponiveis
      for (Edge aresta : arestas) {
        // Caso o valor percorrido da origem do Edge seja null ignora esta iteração
        if (aresta.getOrigem().getValorPercorrido() == null) continue;
        Node origemAresta = aresta.getOrigem();
        Node destinoAresta = aresta.getDestino();
        // Caso nçao seja ignorada esta iteração é feita uma verificação se o caminho considerando a aresta atual é
        // menor que o caminho já estabelecido anteriormente. Se for, o menor caminho é atualizado para o destino da aresta
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