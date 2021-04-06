package model;

import Connection.ConnectionFactory;
import Connection.ServicoCidade;
import Connection.ServicoConexoes;
import model.bellman.ford.CaminhoMinimo;
import model.bellman.ford.MetricaCalculo;

import java.util.HashMap;
import java.util.List;

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
}
