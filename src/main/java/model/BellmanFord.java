//package model;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class BellmanFord {
//
//    private List<Vertice> vertices;
//    private List<Aresta> arestas;
//
//    //maneira aderindo listas como parametros
//    public BellmanFord(List<Vertice> vertices, List<Aresta> arestas) {
//        this.vertices = vertices;
//        this.arestas = arestas;
//    }
//
//    //maneira com construtor vazio, criando listas com a classe
//    public BellmanFord() {
//        this.vertices = new ArrayList<>();
//        this.arestas = new ArrayList<>();
//    }
//
//    public void adicionarVertice(Vertice newV) {
//
//        this.vertices.add(newV);
//    }
//
//    public void adicionarAresta(int distancia, int custo,
//                                String origem, String fim) {
//
//        Vertice inicio = getVertice(origem);//busca o vertice na lista
//        Vertice destino = getVertice(fim);//busca o vertice na lista
//        Aresta novaAresta = new Aresta(distancia, custo, inicio, destino);
//        this.arestas.add(novaAresta);//armazena no array a ligação
//
//    }
//
//    //metodo de busca de vertices
//    private Vertice getVertice(String nome) {
//        Vertice v = new Vertice("null","null");//cria retorno
//
//        for (int i = 0; i < this.vertices.size(); i++) {//percorre
//            if (this.vertices.get(i).getSigla().equals(nome)) {//compara
//                v = this.vertices.get(i);//clona valor encontrado
//                break;//sai do for de busca
//            }
//        }
//
//        return v;//retorna vertice com valor do elemento buscado ou null
//    }
//
//    public void menorNumerodeSaltos(String inicio, String fim) {
//        Vertice origem = getVertice(inicio);
//        Vertice destino = getVertice(fim);
//        origem.setSaltos(0);//troca o valor de previo para zero
//
//        int tamanho = vertices.size(); //define o limite de iterações
//
//        for (int i = 0; i < tamanho - 1; i++) {
//
//            for (Aresta a : arestas) {//cria aresta e percorre
//
//                if (a.getOrigem().getSaltos() == Integer.MAX_VALUE) {
//                    continue;//enquanto os saltos não forem alterados continua
//                }
//
//                Vertice v = a.getOrigem();//valor de origem
//                Vertice u = a.getDestino();//valor de destino
//
//                int novoSalto = v.getSaltos() + a.getHops();//soma
//
//                if (novoSalto < u.getSaltos()) {//se for maior substitui
//                    u.setSaltos(novoSalto);//substitui
//                    u.setProcedente(v);//passa o vertice anterior como referencia
//                }
//            }
//
//        }
//        for (Aresta a : arestas) {
//
//            if (destino.getSaltos() == Integer.MAX_VALUE || destino.getSaltos() == 0) {
//                System.out.println("não há caminho da origem até o destino");
//                break;
//            } else {
//                System.out.println("O numero de hops até o destino é: " + destino.getSaltos());
//                break;
//            }
//        }
//    }
//
//
//    public void menorDistancia(String inicio, String fim) {
//        Vertice origem = getVertice(inicio);
//        Vertice destino = getVertice(fim);
//        origem.setDistanciaMinima(0);//troca o valor de previo para zero
//
//        int tamanho = this.vertices.size();//define o limite de iterações
//
//        for (int i = 0; i < tamanho - 1; i++) {
//
//            for (Aresta a : arestas) {//percorre
//
//                if (a.getOrigem().getDistanciaMinima() == Integer.MAX_VALUE) {
//                    continue;//continua o ciclo atpe que seja diferente de max
//                }
//
//                Vertice v = a.getOrigem();//armazena o vertice origem
//                Vertice u = a.getDestino();//armazena o vertice destino
//
//                //soma a distancia minima = 0 + a distancia do vertice da vez "a"
//                int novaDistancia = v.getDistanciaMinima() + a.getDistancia();
//
//                //se o valor for menor do que a distancia minima de destino(max)
//                if (novaDistancia < u.getDistanciaMinima()) {
//                    u.setDistanciaMinima(novaDistancia);//a distancia do destino
//                    u.setProcedente(v);//é substituida e o vertice v se torna precedente
//                }
//            }
//
//        }
//        for (Aresta a : arestas) {
//
//            if (destino.getDistanciaMinima() == Integer.MAX_VALUE || destino.getDistanciaMinima() == 0) {
//                System.out.println("não há caminho da origem até o destino");
//                break;
//            } else {
//                System.out.println("O caminho mais curto é: " + destino.getDistanciaMinima());
//                break;// sai do ciclo
//            }
//        }
//    }
//
//
//    public void menorCusto(String inicio, String fim) {
//        Vertice origem = getVertice(inicio);
//        Vertice destino = getVertice(fim);
//        origem.setCustoMinimo(0);
//
//        int tamanho = vertices.size();
//
//        for (int i = 0; i < tamanho - 1; i++) {
//
//            for (Aresta a : arestas) {
//
//                if (a.getOrigem().getCustoMinimo() == Integer.MAX_VALUE) {
//                    continue;
//                }
//
//                Vertice v = a.getOrigem();
//                Vertice u = a.getDestino();
//
//                int novoCusto = v.getCustoMinimo() + a.getCusto();
//
//                if (novoCusto < u.getCustoMinimo()) {
//                    u.setCustoMinimo(novoCusto);
//                    u.setProcedente(v);
//                }
//            }
//
//        }
//        for (Aresta a : arestas) {
//
//            if (destino.getCustoMinimo() == Integer.MAX_VALUE || destino.getCustoMinimo() == 0) {
//                System.out.println("não há caminho da origem até o destino");
//                break;
//            } else {
//                System.out.println("O caminho mais barato é: " + destino.getCustoMinimo());
//                break;
//            }
//        }
//    }
//
//
//    public List<Vertice> getVertices() {
//        return this.vertices;
//    }
//
//    public void setVertices(List<Vertice> vertices) {
//        this.vertices = vertices;
//    }
//
//    public List<Aresta> getArestas() {
//        return this.arestas;
//    }
//
//    public void setArestas(List<Aresta> arestas) {
//        this.arestas = arestas;
//    }
//
//}