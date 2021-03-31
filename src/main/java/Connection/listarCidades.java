package Connection;

import model.Vertice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class listarCidades {


    private ConnectionFactory conexao;

    public listarCidades(ConnectionFactory conexao) throws Exception {
        this.conexao = conexao;
        listar();
    }

    public List<Vertice> listar() {
        ResultSet rs = null;
        String sql = "SELECT * FROM cidades";

        List<Vertice> cidades = new ArrayList();
        try {

            rs = conexao.buscar(sql);

            while (rs.next()) {
                Vertice cidade = new Vertice("");
                cidade.setNome(rs.getString("sigla"));
                cidade.setAtivo(rs.getBoolean("ativo"));
                String cityName =rs.getString("nome_cidade"); //pensar nisso
                cidades.add(cidade);
            }
        } catch (Exception e) {
            System.out.println("Erro não foi possivel Exibir a lista de cidades " + e);
        }

        return cidades;

    }

}
