package model;

import Connection.*;
import model.bellman.ford.CaminhoMinimo;
import model.bellman.ford.MetricaCalculo;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import model.bellman.ford.ResultCaminho;

/**
 * Classe que é responsável pelo funcionamento geral da aplicação, tendo a função de concentrar a lógica central e
 * servir como mediador entre a interface e as classes especificas de cada funcionalidade
 */
public class ApplicationFactory {

  private final ServicoCidade svcCidade;

  private final ServicoConexoes svcConexoes;

  private final ServicoEstados svcEstados;

  private final ServicoMunicipios svcMunicipios;

  private final ConnectionFactory conexao;

  private List<Aresta> todasConexoes;

  private List<Vertice> todasCidades;

  /**
   * Contrutor padrão da classe
   * @throws Exception caso haja algum problema em estabeler a conexão com o banco de dados
   */
  public ApplicationFactory() throws Exception {
    conexao = new ConnectionFactory();
    svcCidade = new ServicoCidade(conexao);
    svcConexoes = new ServicoConexoes(conexao);
    svcEstados = new ServicoEstados(conexao);
    svcMunicipios = new ServicoMunicipios(conexao);
    buscarCidades();
    buscarConexoes();
  }

  /**
   * Método que busca todas as cidades no banco de dados e as guarda numa lista
   * @throws Exception caso haja problemas em estabelecer conexão com o banco de dados
   */
  public void buscarCidades() throws Exception {
    todasCidades = svcCidade.listarCidades();
  }

  /**
   * Busca todas as conexões no banco de dados e guarda em uma lista
   * @throws Exception caso haja problemas em estabelecer conexão com o banco de dados ou a lista de cidades for nula no momento da chamada
   */
  public void buscarConexoes() throws Exception {
    HashMap<Integer, Vertice> mapa = new HashMap<>();
    todasCidades.forEach(cidade -> mapa.put(cidade.getIdCidade(), cidade));
    todasConexoes = svcConexoes.buscarTodas(mapa);
  }

  /**
   * Método que inicia a execução do calculo do menor caminho, utilizando o algoritmo de Bellman Ford
   * @param origem o ponto incial do caminho mínimo
   * @param destino o destino em que se quer chegar utilizando o algoritmo do menor caminho
   * @param metrica a métrica selecionada para que seja calculado o menor caminho
   * @return um objetpo ResultCaminho contendo as informações do caminho encontrado
   * @throws Exception caso algum dos vértices esteja inativo
   */
  public ResultCaminho bellmanFord(Vertice origem, Vertice destino, MetricaCalculo metrica) throws Exception {
    return CaminhoMinimo.bellmanFord(todasConexoes, todasCidades, origem, destino, metrica);
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
    if (!listaArestas.isEmpty()) svcConexoes.excluirConexoes(listaArestas);
    svcCidade.excluirCidade(cidade);
    getTodasConexoes().removeAll(listaArestas);
    getTodasCidades().remove(cidade);
  }

  public void excluirConexao(Aresta conexao) throws Exception {
    svcConexoes.excluirConexao(conexao);
    getTodasConexoes().remove(conexao);
  }

  public void alterarStatusCidade(Vertice cidade) throws Exception {
    svcCidade.alterarStatus(cidade, !cidade.getAtivo());
  }
  
  public void alterarStatusConexao(Aresta conexao) throws Exception {
    svcConexoes.alterarStatus(conexao, !conexao.isAtiva());
  }
}
