package model;

import java.util.ArrayList;
import java.util.List;

public class Vertice {

    private String nome;
    private Boolean ativo;
    private List<Aresta> listaAdjascencia;
    int distanciaMinima = Integer.MAX_VALUE;
    int custoMinimo = Integer.MAX_VALUE;
    int saltos = Integer.MAX_VALUE;
    private Vertice procedente;
    

    public Vertice(String nome) {
        this.nome = nome;
        this.listaAdjascencia = new ArrayList();
        this.ativo = true;

    }

    public void adicionarAresta(Aresta aresta) {
        this.listaAdjascencia.add(aresta);
    }

    @Override
    public String toString() {
        return this.nome;
    }
    
    //metodos de acesso

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
