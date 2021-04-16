package model;

import Connection.ConnectionFactory;
import Connection.ServicoCidade;
import Connection.ServicoConexoes;
import model.bellman.ford.CaminhoMinimo;
import model.bellman.ford.MetricaCalculo;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationFactory {

  private final ServicoCidade svcCidade;

  private final ServicoConexoes svcConexoes;

  private final ConnectionFactory conexao;

  private List<Aresta> todasConexoes;

  private List<Vertice> todasCidades;

  public ApplicationFactory() throws Exception {
    conexao = new ConnectionFactory();
    svcCidade = new ServicoCidade(conexao);
    svcConexoes = new ServicoConexoes(conexao);
    buscarCidades();
    buscarConexoes();
  }

  public void buscarCidades() throws Exception {
    todasCidades = svcCidade.listarCidades();
  }

  public void buscarConexoes() throws Exception {
    HashMap<Integer, Vertice> mapa = new HashMap<>();
    todasCidades.forEach(cidade -> mapa.put(cidade.getIdCidade(), cidade));
    todasConexoes = svcConexoes.buscarTodas(mapa);
  }

  public void bellmanFord(Vertice origem, Vertice destino, MetricaCalculo metrica) throws Exception {
    CaminhoMinimo.bellmanFord(todasConexoes, todasCidades, origem, destino, metrica);
  }

  public List<Aresta> getTodasConexoes() {
    return todasConexoes;
  }

  public List<Vertice> getTodasCidades() {
    return todasCidades;
  }

  public void cadastrarCidade(String nomeCidade, String sigla, int cordenadaX, int cordenadaY) throws Exception {
    Vertice novaCidade = Vertice.instanciarNovo(nomeCidade, sigla, cordenadaX, cordenadaY);
    svcCidade.inserir(novaCidade);
    todasCidades.add(novaCidade);
  }

  public void cadastrarConexao(Vertice cidade1, Vertice cidade2, int distancia, int custo) throws Exception {
    Aresta novaConexao = Aresta.instanciarNova(cidade1, cidade2, distancia, custo);
    svcConexoes.inserir(novaConexao, todasConexoes);
    todasConexoes.add(novaConexao);
  }

  public void excluirCidade(Vertice cidade) throws Exception {
    List<Aresta> listaArestas = getTodasConexoes().stream()
        .filter(aresta -> aresta.getOrigem().equals(cidade) || aresta.getDestino().equals(cidade))
        .collect(Collectors.toList());
    svcConexoes.excluirConexoes(listaArestas);
    svcCidade.excluirCidade(cidade);
    getTodasConexoes().removeAll(listaArestas);
    getTodasCidades().remove(cidade);
  }

  public void excluirConexao(Aresta aresta) throws Exception {
    svcConexoes.excluirConexao(aresta);
    getTodasConexoes().remove(aresta);
  }
}
