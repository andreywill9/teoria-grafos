package model;

public class Implementação {

    public static void main(String[] args) {

        BellmanFord bf = new BellmanFord();
        //vertices
        bf.adicionarVertice(new Vertice("POA"));
        bf.adicionarVertice(new Vertice("FLO"));
        bf.adicionarVertice(new Vertice("BLU"));
        bf.adicionarVertice(new Vertice("CUR"));
        bf.adicionarVertice(new Vertice("LON"));
        bf.adicionarVertice(new Vertice("SPO"));
        bf.adicionarVertice(new Vertice("SJC"));
        bf.adicionarVertice(new Vertice("RJO"));
        bf.adicionarVertice(new Vertice("BHO"));
        bf.adicionarVertice(new Vertice("CMP"));
        bf.adicionarVertice(new Vertice("RBP"));
        bf.adicionarVertice(new Vertice("BAU"));
        bf.adicionarVertice(new Vertice("CPG"));
        bf.adicionarVertice(new Vertice("CUI"));
        bf.adicionarVertice(new Vertice("MAN"));
        bf.adicionarVertice(new Vertice("BEL"));
        bf.adicionarVertice(new Vertice("BSB"));
        bf.adicionarVertice(new Vertice("NTL"));
        bf.adicionarVertice(new Vertice("REC"));
        bf.adicionarVertice(new Vertice("SLV"));
        
        //arestas
        bf.adicionarAresta(6, 2, "POA", "FLO");
        bf.adicionarAresta(7, 2, "POA", "BLU");
        bf.adicionarAresta(1, 3, "FLO", "BLU");
        bf.adicionarAresta(2, 5, "FLO", "CUR");
        bf.adicionarAresta(12, 10, "FLO", "RJO");
        bf.adicionarAresta(2, 5, "BLU", "CUR");
        bf.adicionarAresta(6, 2, "CUR", "LON");
        bf.adicionarAresta(5, 10, "CUR", "SPO");
        bf.adicionarAresta(7, 2, "LON", "SPO");
        bf.adicionarAresta(3, 2, "LON", "BAU");
        bf.adicionarAresta(5, 15, "SPO", "RJO");
        bf.adicionarAresta(1, 7, "SPO", "CMP");
        bf.adicionarAresta(2, 16, "SPO", "SJC");
        bf.adicionarAresta(2, 10, "SJC", "CMP");
        bf.adicionarAresta(3, 10, "RJO", "SJC");
        bf.adicionarAresta(7, 16, "RJO", "BHO");
        bf.adicionarAresta(20, 6, "RJO", "SLV");
        bf.adicionarAresta(7, 8, "BHO", "SJC");
        bf.adicionarAresta(9, 6, "BHO", "BSB");
        bf.adicionarAresta(3, 6, "CMP", "BAU");
        bf.adicionarAresta(2, 4, "CMP", "RBP");
        bf.adicionarAresta(8, 4, "RBP", "BSB");
        bf.adicionarAresta(10, 3, "BAU", "CPG");
        bf.adicionarAresta(8, 2, "CPG", "CUI");
        bf.adicionarAresta(20, 3, "CUI", "MAN");
        bf.adicionarAresta(18, 2, "MAN", "BEL");
        bf.adicionarAresta(21, 3, "BEL", "NTL");
        bf.adicionarAresta(22, 6, "BSB", "MAN");
        bf.adicionarAresta(22, 7, "BSB", "NTL");
        bf.adicionarAresta(4, 3, "NTL", "REC");
        bf.adicionarAresta(8, 5, "REC", "SLV");
        bf.adicionarAresta(15, 4, "SLV", "NTL");
        

        

        bf.menorNumerodeSaltos("MAN", "NTL");//hops        
        bf.menorDistancia("MAN", "NTL");//distancia
        bf.menorCusto("MAN", "NTL");//custo


        
        
       


        
    }

}
