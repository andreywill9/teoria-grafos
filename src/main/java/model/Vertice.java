package model;

import java.util.ArrayList;
import java.util.List;

public class Vertice {

    private String sigla;
    private String nome_cidade;
    private Boolean ativo;
    private int id_cidade;
    private List<Aresta> listaAdjascencia;
    int distanciaMinima = Integer.MAX_VALUE;
    int custoMinimo = Integer.MAX_VALUE;
    int saltos = Integer.MAX_VALUE;
    private Vertice procedente;
    

    public Vertice(String sigla, String nome_cidade) {
        this.sigla = sigla;
        this.nome_cidade = nome_cidade;
        this.listaAdjascencia = new ArrayList();
        this.ativo = true;

    }

    public void adicionarAresta(Aresta aresta) {
        this.listaAdjascencia.add(aresta);
    }

    @Override
    public String toString() {
        return this.sigla;
    }
    
    //metodos de acesso



    public String getSigla() {
        return sigla;
    }

    public String getNome_cidade() {
        return nome_cidade;
    }

    public void setNome_cidade(String nome_cidade) {
        this.nome_cidade = nome_cidade;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public int getId_cidade() {
        return id_cidade;
    }

    public void setId_cidade(int id_cidade) {
        this.id_cidade = id_cidade;
    }

    public List<Aresta> getListaAdjascencia() {
        return listaAdjascencia;
    }

    public void setListaAdjascencia(List<Aresta> listaAdjascencia) {
        this.listaAdjascencia = listaAdjascencia;
    }

    public int getDistanciaMinima() {
        return distanciaMinima;
    }

    public void setDistanciaMinima(int distanciaMinima) {
        this.distanciaMinima = distanciaMinima;
    }

    public Vertice getProcedente() {
        return procedente;
    }

    public void setProcedente(Vertice procedente) {
        this.procedente = procedente;
    }

    public int getCustoMinimo() {
        return custoMinimo;
    }

    public void setCustoMinimo(int custoMinimo) {
        this.custoMinimo = custoMinimo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public int getSaltos() {
        return saltos;
    }

    public void setSaltos(int saltos) {
        this.saltos = saltos;
    }
    
    

}
