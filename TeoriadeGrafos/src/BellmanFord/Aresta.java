package BellmanFord;

public class Aresta {

    private int distancia;
    private int custo;
    private Vertice origem;
    private int hops;
    private boolean ativa;
    private Vertice destino;

    public Aresta(int distancia, int custo, Vertice origem, Vertice destino) {
        this.distancia = distancia;
        this.custo = custo;
        this.origem = origem;
        this.destino = destino;
        this.hops = 1;
        this.ativa = true;
    }
    
    //metodos de acesso

    public int getHops() {
        return hops;
    }

    public void setHops(int hops) {
        this.hops = hops;
    }
    
    

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public Vertice getOrigem() {
        return origem;
    }

    public void setOrigem(Vertice origem) {
        this.origem = origem;
    }

    public Vertice getDestino() {
        return destino;
    }

    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

}
