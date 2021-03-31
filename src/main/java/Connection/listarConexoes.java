package Connection;

import model.Aresta;
import model.Vertice;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class listarConexoes {


    private ConnectionFactory conexao;

    public listarConexoes(ConnectionFactory conexao) throws Exception {
        this.conexao = conexao;
        listar();
    }

    public List<Aresta> listar() {
        ResultSet rs = null;
        String sql = "SELECT * FROM cidades";

        List<Aresta> conexoes = new ArrayList();
        try {

            rs = conexao.buscar(sql);

            while (rs.next()) {
                Aresta conexao = new Aresta(0,0,new Vertice(""),new Vertice(""));
                conexao.setOrigem(rs.getInt("Id_cidade1"));//pensar nisso reformular construtor aresta
                conexao.setDestino(rs.getInt("Id_cidade2"));//pensar nisso reformular construtor aresta
                conexao.setDistancia(rs.getInt("distancia"));
                conexao.setCusto(rs.getInt("custo"));
                conexao.setAtiva(rs.getBoolean("ativo"));
                conexoes.add(conexao);
            }
        } catch (Exception e) {
            System.out.println("Erro não foi possivel Exibir a lista de conexoes " + e);
        }

        return conexoes;

    }

}
