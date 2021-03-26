package BellmanFord;

import java.util.ArrayList;
import java.util.List;

public class BellmanFord {

    private List<Vertice> vertices;
    private List<Aresta> arestas;

    public BellmanFord(List<Vertice> vertices, List<Aresta> arestas) {
        this.vertices = vertices;
        this.arestas = arestas;
    }

    public BellmanFord() {
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
    }

    public void adicionarVertice(Vertice newV) {

        this.vertices.add(newV);
    }

    
    public void adicionarAresta(int distancia,int custo, String origem, String fim){
        Vertice inicio = getVertice(origem);
        Vertice destino = getVertice(fim);
        Aresta novaAresta = new Aresta(distancia, custo, inicio, destino);
        
       
        
        this.arestas.add(novaAresta);//armazena no array
        
    }
    
        private Vertice getVertice(String nome){
         Vertice v = new Vertice("null");
        for (int i = 0; i < this.vertices.size(); i++) {
            if (this.vertices.get(i).getNome().equals(nome)) {
                v = this.vertices.get(i);
                break;//sai do for de busca
            }
        }
        
        return v;
    }
        
        public void menorNumerodeSaltos(String inicio, String fim) {
        Vertice origem = getVertice(inicio);
        Vertice destino = getVertice(fim);
        origem.setSaltos(0);


        int tamanho = vertices.size();
        

        for (int i = 0; i < tamanho - 1; i++) {

            for (Aresta a : arestas) {

                if (a.getOrigem().getSaltos() == Integer.MAX_VALUE) {
                    continue;
                }

                Vertice v = a.getOrigem();
                Vertice u = a.getDestino();

                int novoSalto = v.getSaltos() + a.getHops();

                if (novoSalto < u.getSaltos()) {
                    u.setSaltos(novoSalto);
                    u.setProcedente(v);
                }
            }

        }
        for (Aresta a : arestas) {
            if (a.getOrigem().getSaltos() != Integer.MAX_VALUE) {
                if (saltoPossivel(a)) {
                    System.out.println("o custo é incalculavel...");
                    return;
                }
            }
            if (destino.getSaltos()== Integer.MAX_VALUE) {
                System.out.println("não há caminho da origem até o destino");
            } else {
                System.out.println("O numero de hops até o destino é: " + destino.getSaltos());
                break;
            }
        }
    }

    private boolean saltoPossivel(Aresta a) {
        return (a.getOrigem().getSaltos() + a.getHops())
                < a.getDestino().getSaltos(); //for menor
    }

    public void menorDistancia(String inicio, String fim) {
        Vertice origem = getVertice(inicio);
        Vertice destino = getVertice(fim);
        origem.setDistanciaMinima(0);

        int tamanho = this.vertices.size();

        for (int i = 0; i < tamanho - 1; i++) {

            for (Aresta a : arestas) {

                if (a.getOrigem().getDistanciaMinima() == Integer.MAX_VALUE) {
                    continue;
                }

                Vertice v = a.getOrigem();
                Vertice u = a.getDestino();

                int novaDistancia = v.getDistanciaMinima() + a.getDistancia();

                if (novaDistancia < u.getDistanciaMinima()) {
                    u.setDistanciaMinima(novaDistancia);
                    u.setProcedente(v);
                }
            }

        }
        for (Aresta a : arestas) {
            if (a.getOrigem().getDistanciaMinima() != Integer.MAX_VALUE) {
                if (hasCycle(a)) {
                    System.out.println("há um ciclo negativo...");
                    return;
                }
            }
            if (destino.getDistanciaMinima() == Integer.MAX_VALUE) {
                System.out.println("não há caminho da origem até o destino");
            } else {
                System.out.println("O caminho mais curto é: " + destino.getDistanciaMinima());
                break;
            }
        }
    }

    private boolean hasCycle(Aresta a) {
        return (a.getOrigem().getDistanciaMinima() + a.getDistancia())
                < a.getDestino().getDistanciaMinima(); //for menor
    }

    public void menorCusto(String inicio, String fim) {
        Vertice origem = getVertice(inicio);
        Vertice destino = getVertice(fim);
        origem.setCustoMinimo(0);

        int tamanho = vertices.size();

        for (int i = 0; i < tamanho - 1; i++) {

            for (Aresta a : arestas) {

                if (a.getOrigem().getCustoMinimo() == Integer.MAX_VALUE) {
                    continue;
                }

                Vertice v = a.getOrigem();
                Vertice u = a.getDestino();

                int novoCusto = v.getCustoMinimo() + a.getCusto();

                if (novoCusto < u.getCustoMinimo()) {
                    u.setCustoMinimo(novoCusto);
                    u.setProcedente(v);
                }
            }

        }
        for (Aresta a : arestas) {
            if (a.getOrigem().getCustoMinimo() != Integer.MAX_VALUE) {
                if (custoPossivel(a)) {
                    System.out.println("o custo é incalculavel...");
                    return;
                }
            }
            if (destino.getCustoMinimo() == Integer.MAX_VALUE) {
                System.out.println("não há caminho da origem até o destino");
            } else {
                System.out.println("O caminho mais barato é: " + destino.getCustoMinimo());
                break;
            }
        }
    }

    private boolean custoPossivel(Aresta a) {
        return (a.getOrigem().getCustoMinimo() + a.getCusto())
                < a.getDestino().getCustoMinimo(); //for menor
    }

    public List<Vertice> getVertices() {
        return this.vertices;
    }

    public void setVertices(List<Vertice> vertices) {
        this.vertices = vertices;
    }

    public List<Aresta> getArestas() {
        return this.arestas;
    }

    public void setArestas(List<Aresta> arestas) {
        this.arestas = arestas;
    }

    
    
}
