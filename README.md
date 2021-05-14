# Simulação da Execução:

Um vídeo com a execução e detalhes do projeto podem ser encontrados clicando [aqui](https://www.youtube.com/watch?v=o_tP7F2AJjs&ab_channel=AlvaroRodrigues)

---

# Descrição do banco de dados:

Para armazenar os dados de nosso projeto optamos por uma solução em MySQL criando um banco de dados com 4 tabelas sendo:

- "cidades", armazenamento de vértices
- "conexoes", armazenamento dos enlaces
- "estados" e "municipios", armazenamento das coordenadas x e y, latitude e longitude em relação ao IBGE e a imagem do mapa do brasil na janela principal do programa.

![tabelas criadas e utilizadas](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/d61f1a3a-abea-4ab5-acaf-ebfd61fbaf7f/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210514%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210514T225312Z&X-Amz-Expires=86400&X-Amz-Signature=56283bcf20599573a79f09bfb7fb0258f801e3b72f06fe4dbf9cadaa053eea43&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22)

`tabelas criadas e utilizadas`

## Criação das tabelas e suas visualizações no MySQL Workbench

Tabela "ciadades":

```sql
CREATE TABLE cidades (
	Id_cidade int NOT NULL AUTO_INCREMENT,
	nome_cidade varchar(45) NOT NULL,
	status tinyint NOT NULL DEFAULT '1',
	sigla varchar(3) NOT NULL,
	cordenada_x int NOT NULL,
	cordenada_y int NOT NULL,
	PRIMARY KEY (Id_cidade),
	UNIQUE KEY Id_cidade_UNIQUE (Id_cidade),
	UNIQUE KEY sigla_UNIQUE (sigla)
);
```

![colunas da tabela "cidades"](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/dd50b911-a0a1-4d0b-b52e-2a7a4483e4a8/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210514%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210514T230126Z&X-Amz-Expires=86400&X-Amz-Signature=eb87b5053bb5f1d4c84950d090f736d6f24e46703857c66488a7d4701f054795&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22)

`colunas da tabela "cidades"`

Tabela "conexoes":

A tabela "conexoes" guarda as informações dos enlaces sendo assim utiliza como chaves estrangeiras 'Id_cidade1' e 'Id_cidade2' com os valores correspondentes da chave primária "Id_cidade" da tabela "cidades" (que armazena os vértices).

```sql
CREATE TABLE conexoes (
	Id_conexao int NOT NULL AUTO_INCREMENT,
	Id_cidade1 int NOT NULL,
	Id_cidade2 int NOT NULL,
	salto int NOT NULL DEFAULT '1',
	distancia int NOT NULL,
	custo int NOT NULL,
	ativo tinyint NOT NULL DEFAULT '1',
	PRIMARY KEY (Id_conexao),
	KEY Id_cidade2 (Id_cidade2),
	KEY Id_cidade1 (Id_cidade1),
	CONSTRAINT conexoes_ibfk_1 FOREIGN KEY (Id_cidade2) REFERENCES cidades (Id_cidade),
	CONSTRAINT conexoes_ibfk_2 FOREIGN KEY (Id_cidade1) REFERENCES cidades (Id_cidade)
);
```

![colunas da tabela "conexoes"](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/20b60b69-dc90-4669-a93f-8e1ecceb7991/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210514%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210514T230158Z&X-Amz-Expires=86400&X-Amz-Signature=21210d74ef08f95d4c915e137dc5520151994e4c5373f9c55a9fa0942f04ca6d&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22)

`colunas da tabela "conexoes"`

Tabela "estados":

```sql
CREATE TABLE estados (
	codigo_uf int NOT NULL,
	uf varchar(2) NOT NULL,
	nome varchar(100) NOT NULL,
	latitude float NOT NULL,
	longitude float NOT NULL,
	PRIMARY KEY (codigo_uf)
);
```

![colunas da tabela "estados"](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/2981fdf0-5b62-42a5-a235-b390947627f7/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210514%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210514T230225Z&X-Amz-Expires=86400&X-Amz-Signature=559024c86b1b022b31e0c92ee90357005c1b21247de4cdf2b8414b455d9fecd1&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22)

`colunas da tabela "estados"`

Tabela "municipios":

A tabela "municipios" utiliza como chave estrangeira o 'codigo_uf' sendo seu valor correspondente à chave primária da tabela "estados" 'codigo_uf'.

```sql
CREATE TABLE municipios (
	codigo_ibge int NOT NULL,
	nome varchar(100) NOT NULL,
	latitude float NOT NULL,
	longitude float NOT NULL,
	capital tinyint(1) NOT NULL,
	codigo_uf int NOT NULL,
	PRIMARY KEY (codigo_ibge),
	KEY codigo_uf (codigo_uf),
	CONSTRAINT municipios_ibfk_1 FOREIGN KEY (codigo_uf) REFERENCES estados (codigo_uf)
);
```

![colunas da tabela "municipios"](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/0508ddb9-ebe8-4854-9cb7-fb80088ec150/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210514%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210514T230240Z&X-Amz-Expires=86400&X-Amz-Signature=28b78ab85f59281f910892a195d08c4317fb0ec7ccb837572b10dd4641aea96b&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22)

`colunas da tabela "municipios"`

---

# Código fonte:

Organização do escopo do projeto, sua separação se dá através dos pacotes:

- **Connection**: responsável pela comunicação e manipulação com o banco de dados.
- **front**: contém a interface de comunicação e interação com o usuário.
- **model**: codificação e definição principal do que constitui o projeto sendo aresta e vértice (elementos que compõem o grafo), juntamente da classe "ApplicationFactory", responsável pelo controle e funcionamento das demais classes da aplicação.
- **bellman.ford**: classes que definem o funcionamento do algoritmo e seus meios para alcançar o caminho mínimo.

![](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/6f660d64-23b2-48ee-a5a2-e8e85c4afb97/Organizao.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210514%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210514T230449Z&X-Amz-Expires=86400&X-Amz-Signature=81806efbf1037d4243e16109f854ea39ea7463b1805d1a7dbd480168308be012&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Organizao.png%22)

## ApplicationFactory:

```java
package model;

import Connection.*;
import model.bellman.ford.CaminhoMinimo;
import model.bellman.ford.MetricaCalculo;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import model.bellman.ford.ResultCaminho;

/**
 * Classe que é responsável pelo funcionamento geral da aplicação, tendo a função
	 de concentrar a lógica central e
 * servir como mediador entre a interface e as classes especificas de 
	 cada funcionalidade
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
   * @throws Exception caso haja algum problema em estabeler a 
			conexão com o banco de dados
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
   * @throws Exception caso haja problemas em estabelecer conexão 
     com o banco de dados ou a lista de cidades for nula no momento da chamada
   */
  public void buscarConexoes() throws Exception {
    HashMap<Integer, Vertice> mapa = new HashMap<>();
    todasCidades.forEach(cidade -> mapa.put(cidade.getIdCidade(), cidade));
    todasConexoes = svcConexoes.buscarTodas(mapa);
  }

  /**
   * Método que inicia a execução do calculo do menor caminho, utilizando o 
     algoritmo de Bellman Ford
   * @param origem o ponto incial do caminho mínimo
   * @param destino o destino em que se quer chegar utilizando o 
     algoritmo do menor caminho
   * @param metrica a métrica selecionada para que seja calculado o menor caminho
   * @return um objetpo ResultCaminho contendo as informações do caminho encontrado
   * @throws Exception caso algum dos vértices esteja inativo
   */
  public ResultCaminho bellmanFord(Vertice origem, Vertice destino,
    MetricaCalculo metrica) throws Exception {
    return CaminhoMinimo.bellmanFord(todasConexoes, todasCidades, origem, 
    destino, metrica);
  }

  public List<Aresta> getTodasConexoes() {
    return todasConexoes;
  }

  public List<Vertice> getTodasCidades() {
    return todasCidades;
  }

  public void cadastrarCidade(String nomeCidade, String sigla, int cordenadaX,
    int cordenadaY) throws Exception {
    Vertice novaCidade = Vertice.instanciarNovo(nomeCidade, sigla, cordenadaX,
    cordenadaY);
    svcCidade.inserir(novaCidade);
    todasCidades.add(novaCidade);
  }

  public void cadastrarConexao(Vertice cidade1, Vertice cidade2, int distancia, 
    int custo) throws Exception {
    Aresta novaConexao = Aresta.instanciarNova(cidade1, cidade2, distancia, custo);
    svcConexoes.inserir(novaConexao, todasConexoes);
    todasConexoes.add(novaConexao);
  }

  public void excluirCidade(Vertice cidade) throws Exception {
    List<Aresta> listaArestas = getTodasConexoes().stream()
        .filter(aresta -> aresta.getOrigem().equals(cidade) ||
					 aresta.getDestino().equals(cidade))
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
```

### ServicoCidade:

Interage com o banco de dados criando, armazenando e manipulando os vértices do grafo em tabelas.

```java
package Connection;

import model.Vertice;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServicoCidade {

  private static final String CREATE = "CREATE TABLE IF NOT EXISTS`cidades` (\n" +
      "  `Id_cidade` int NOT NULL AUTO_INCREMENT,\n" +
      "  `nome_cidade` varchar(45) NOT NULL,\n" +
      "  `status` tinyint NOT NULL DEFAULT '1',\n" +
      "  `sigla` varchar(3) NOT NULL,\n" +
      "   `cordenada_x` int NOT NULL, \n" +
      "   `cordenada_y` int NOT NULL, \n" +
      "  PRIMARY KEY (`Id_cidade`),\n" +
      "  UNIQUE KEY `Id_cidade_UNIQUE` (`Id_cidade`),\n" +
      "  UNIQUE KEY `sigla_UNIQUE` (`sigla`)\n" +
      " )";

  private static final String INSERT_PADRAO = 
     "INSERT INTO cidades (nome_cidade, sigla, cordenada_x, cordenada_y) values\n" +
      "\t('Porto Alegre','POA', 471, 727), \n" +
      "  ('Florianópolis','FLO', 525, 677), \n" +
      "  ('Blumenau','BLU', 515, 663), \n" +
      "  ('Curitiba','CUR', 511, 633), \n" +
      "  ('Londrina','LON', 472, 589), \n" +
      "  ('São Paulo','SPO', 565, 594), \n" +
      "  ('São José dos Campos','SJC', 580, 587),\n" +
      "  ('Rio de Janeiro','RJO', 635, 581), \n" +
      "  ('Belo Horizonte','BHO', 620, 520), \n" +
      "  ('Campinas','CMP', 556, 581), \n" +
      "  ('Ribeirão Preto','RBP', 541, 546), \n" +
      "  ('Bauru','BAU', 515, 569), \n" +
      "  ('Campo Grande','CPG', 402, 531), \n" +
      "  ('Cuiabá','CUI', 372, 432), \n" +
      "  ('Manaus','MAN', 291, 176), \n" +
      "  ('Belém','BEL', 527, 142),\n" +
      "  ('Brasília','BSB', 538, 435), \n" +
      "  ('Natal','NTL', 798, 231), \n" +
      "  ('Recife','REC', 805, 277), \n" +
      "  ('Salvador','SLV', 731, 378)";

  private static final String MAIOR_ID =
   "SELECT MAX(Id_cidade) FROM cidades;";

  private static final String ALTERAR_STATUS = 
   "UPDATE cidades SET status = %s WHERE Id_cidade = %s";

  private static final String EXCLUIR_CIDADE = 
    "DELETE FROM cidades WHERE Id_cidade = %s";

  private ConnectionFactory conexao;

  public ServicoCidade(ConnectionFactory conexao) throws Exception {
    this.conexao = conexao;
    this.conexao.executar(CREATE);
    popularBanco();
  }

  public void popularBanco() throws Exception {
    String queryContar = "SELECT COUNT(*) FROM cidades";
    ResultSet rsBusca = conexao.buscar(queryContar);
    rsBusca.next();
    if (rsBusca.getInt("COUNT(*)") == 0) {
      conexao.executar(INSERT_PADRAO);
    }
  }

  public List<Vertice> listarCidades() throws Exception {
    String sql = "SELECT * FROM cidades";
    List<Vertice> cidades = new ArrayList<>();
    ResultSet rs = conexao.buscar(sql);
    while (rs.next()) {
      cidades.add(Vertice.instanciarDeResultSet(rs));
    }
    return cidades;
  }

  public void inserir(Vertice cidade) throws Exception {
    String sql = String.format(
     "INSERT INTO cidades (nome_cidade, sigla, cordenada_x, cordenada_y) "
       "VALUES ('%s', '%s', %s, %s)",
        cidade.getNomeCidade(), cidade.getSigla(), cidade.getCordenadaX(),
        cidade.getCordenadaY());
    conexao.executar(sql);
    cidade.setIdCidade(ultimoId());
  }

  private int ultimoId() throws Exception {
    ResultSet rs = conexao.buscar(MAIOR_ID);
    return rs.getInt(0);
  }

  public void alterarStatus(Vertice cidade, boolean novoStatus) throws Exception {
    String sql = String.format(
        ALTERAR_STATUS,
        novoStatus ? 1 : 0,
        cidade.getIdCidade()
    );
    conexao.executar(sql);
    cidade.setAtivo(novoStatus);
  }

  public void excluirCidade(Vertice cidade) throws Exception {
    String sql = String.format(
        EXCLUIR_CIDADE,
        cidade.getIdCidade()
    );
    conexao.executar(sql);
  }

}
```

### ServicoConexoes:

Interage com o banco de dados criando, armazenando e manipulando os enlaces do grafo em tabelas.

```java
package Connection;

import model.Aresta;
import model.Vertice;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ServicoConexoes {

  private static final String CREATE_CONEXOES = 
     "CREATE TABLE IF NOT EXISTS `conexoes` (\n" +
      "  `Id_conexao` int NOT NULL AUTO_INCREMENT,\n" +
      "  `Id_cidade1` int NOT NULL,\n" +
      "  `Id_cidade2` int NOT NULL,\n" +
      "  `salto` int NOT NULL DEFAULT '1',\n" +
      "  `distancia` int NOT NULL,\n" +
      "  `custo` int NOT NULL,\n" +
      "  `ativo` tinyint NOT NULL DEFAULT '1',\n" +
      "  PRIMARY KEY (`Id_conexao`),\n" +
      "  FOREIGN KEY (`Id_cidade2`) REFERENCES `cidades` (`Id_cidade`),\n" +
      "  FOREIGN KEY (`Id_cidade1`) REFERENCES `cidades` (`Id_cidade`) \n" +
      ")";

  private static final String INSERT_CONEXOES = 
    "INSERT INTO conexoes (Id_cidade1, Id_cidade2, distancia, custo) VALUES\n" +
      "\t(1 , 2, 6 , 2),\n" +
      "\t(1 , 3, 7 , 2),\n" +
      "  (2 , 3, 1 , 3),\n" +
      "  (2 , 4, 2 , 5),\n" +
      "  (2 , 8, 12 , 10),\n" +
      "  (3 , 4, 2 , 5),\n" +
      "  (4 , 5, 6 , 2),\n" +
      "  (4 , 6, 5 , 10),\n" +
      "  (5 , 6, 7 , 2),\n" +
      "  (5 , 12, 3 , 2),\n" +
      "  (6 , 8, 5 , 15),\n" +
      "  (6 , 10, 1 , 7),\n" +
      "  (6 , 7, 2 , 16),\n" +
      "  (7 , 10, 2 , 10),\n" +
      "  (8 , 7, 3 , 10),\n" +
      "  (8 , 9, 7 , 6),\n" +
      "  (8 , 20, 20 , 6),\n" +
      "  (9 , 7, 7 , 8),\n" +
      "  (9 , 17, 9 , 6),\n" +
      "  (10 , 12, 3 , 6),\n" +
      "  (10 , 11, 2 , 4),\n" +
      "  (11 , 17, 8 , 4),\n" +
      "  (12 , 13, 10 , 3),\n" +
      "  (13 , 14, 8 , 2),\n" +
      "  (14 , 15, 20 , 3),\n" +
      "  (15 , 16, 18 , 2),\n" +
      "  (16 , 18, 21 , 3),\n" +
      "  (17 , 15, 22 , 6),\n" +
      "  (17 , 18, 22 , 7),\n" +
      "  (18 , 19, 4 , 3),\n" +
      "  (19 , 20, 8 , 5),\n" +
      "  (20 , 18, 15 , 4)";

  private static final String MAIOR_ID = "SELECT MAX(Id_conexao) FROM conexoes";

  private static final String ALTERAR_STATUS = 
  "UPDATE conexoes SET ativo = %s WHERE Id_conexao = %s";

  private static final String EXCLUIR_CONEXAO = 
  "DELETE FROM conexoes WHERE Id_conexao = %s";

  private static final String EXCLUIR_CONEXOES_LISTA = 
  "DELETE from conexoes WHERE Id_conexao IN (%s)";

  private ConnectionFactory conexao;

  public ServicoConexoes(ConnectionFactory conexao) throws Exception {
    this.conexao = conexao;
    this.conexao.executar(CREATE_CONEXOES);
    popularBanco();
  }

  public void popularBanco() throws Exception {
    String queryContar = "SELECT COUNT(*) FROM conexoes";
    ResultSet rsBusca = conexao.buscar(queryContar);
    rsBusca.next();
    if (rsBusca.getInt("COUNT(*)") == 0) {
      conexao.executar(INSERT_CONEXOES);
    }
  }

  public List<Aresta> buscarTodas(Map<Integer, Vertice> mapaCidades) throws Exception {
    String query = "SELECT * FROM conexoes";
    List<Aresta> listaAresta = new ArrayList<>();
    ResultSet rs = conexao.buscar(query);
    while (rs.next()) {
      listaAresta.add(Aresta.instanciarDeResultSet(rs, mapaCidades));
    }
    return listaAresta;
  }

  public void inserir(Aresta aresta, List<Aresta> listaAresta) throws Exception {
    if (!arestaValida(aresta) || !naoContemAresta(aresta, listaAresta)) 
    throw new Exception("Aresta inválida");
    String query = String.format(
    "INSERT INTO conexoes (Id_cidade1, Id_cidade2, distancia, custo) VALUES (" +
        "%s, %s, %s, %s)", aresta.getOrigem().getIdCidade(), 
    aresta.getDestino().getIdCidade(), aresta.getDistancia(), aresta.getCusto());
    conexao.executar(query);
    aresta.setIdConexao(ultimoId());
  }

  private static boolean arestaValida(Aresta aresta) {
    return !aresta.getOrigem().equals(aresta.getDestino());
  }

  private static boolean naoContemAresta(Aresta aresta, List<Aresta> listaAresta) {
    return listaAresta.stream().noneMatch(aresta::equals);
  }

  private int ultimoId() throws Exception {
    ResultSet rs = conexao.buscar(MAIOR_ID);
    return rs.getInt(0);
  }

  public void alterarStatus(Aresta aresta, boolean novoStatus) throws Exception {
    String sql = String.format(
        ALTERAR_STATUS,
        novoStatus ? 1 : 0,
        aresta.getIdConexao()
    );
    conexao.executar(sql);
    aresta.setAtiva(novoStatus);
  }

  public void excluirConexao(Aresta aresta) throws Exception {
    String sql = String.format(
        EXCLUIR_CONEXAO,
        aresta.getIdConexao()
    );
    conexao.executar(sql);
  }

  public void excluirConexoes(List<Aresta> listaArestas) throws Exception {
    String idsExcuir = listaArestas.stream().map(Aresta::getIdConexao)
        .map(String::valueOf).collect(Collectors.joining(","));
    String sql = String.format(EXCLUIR_CONEXOES_LISTA, idsExcuir);
    conexao.executar(sql);
  }

}
```

## Composição do grafo e algoritmo implementado (Bellman Ford):

### Vértice e Aresta:

composição e instancias de ResultSet dos mesmos:

```java
/**
 * Classe responsável pelo objeto que representa uma cidade em formato de vértice
 * É um objeto que representa diretamente como os dados sao armazenados no banco de dados,
 * sua função é representar um PoP (em inglês Point of Presence) 
 * no mapa e definir uma identificação para o mesmo
 * através de idCidade e sua localização "cordenadaX" e "cordenadaY".
 */
public class Vertice {

  private String sigla;
  private String nomeCidade;
  private Boolean ativo;
  private int idCidade, cordenadaX, cordenadaY;

  public static Vertice instanciarDeResultSet(ResultSet rs) throws Exception {
    Vertice cidade = new Vertice();
    cidade.setNomeCidade(rs.getString("nome_cidade"));
    cidade.setSigla(rs.getString("sigla"));
    cidade.setIdCidade(rs.getInt("Id_cidade"));
    cidade.setAtivo(rs.getBoolean("status"));
    cidade.setCordenadaX(rs.getInt("cordenada_x"));
    cidade.setCordenadaY(rs.getInt("cordenada_y"));
    return cidade;
  }

public class Aresta {

  private int idConexao;
  private int distancia;
  private int custo;
  private Vertice origem;
  private boolean ativa;
  private Vertice destino;
  /**
   * Metodo que instancia um novo objeto Aresta a partir dos dados contidos no banco
   * @param rs representa o ResultSet em uma determinada posiçao para que seja
   * buscado os atributos das colunas
   * @param mapaCidades um HashMap com todas as cidades (vertices) tendo o id delas como
   * chave, assim e  buscado a origem e destino a partir de seus ids
   * @return um objeto Aresta
   * @throws Exception caso a linha atual do ResultSet seja nula ou os dados inválidos
   */
  public static Aresta instanciarDeResultSet(ResultSet rs, 
    Map<Integer, Vertice> mapaCidades) throws Exception {
    Aresta conexao = new Aresta();
    conexao.setIdConexao(rs.getInt("Id_conexao"));
    conexao.setOrigem(mapaCidades.get(rs.getInt("Id_cidade1")));
    conexao.setDestino(mapaCidades.get(rs.getInt("Id_cidade2")));
    conexao.setDistancia(rs.getInt("distancia"));
    conexao.setCusto(rs.getInt("custo"));
    conexao.setAtiva(rs.getBoolean("ativo"));
    return conexao;
  }
```

## Bellman Ford:

### Node:

```java
/**
 * Classe que responsável pelo objeto Node referente a um Vértice, sendo auxiliar 
 * somente para o algoritmo de Bellman Ford, Possui atributos referentes ao vértice
 *  o qual o Node é referente, o caminho de todos os vértices até chegar até ele e o
 *  valor percorrido pelo caminho
 */
public class Node {

  private List<Vertice> caminho;

  private Vertice verticeAtual;

  private Integer valorPercorrido;

  public Node(Vertice vertice) {
    verticeAtual = vertice;
    caminho = Collections.singletonList(vertice);
  }

  public List<Vertice> getCaminho() {
    return caminho;
  }

  public void setCaminho(List<Vertice> caminho) {
    this.caminho = caminho;
  }

  public Vertice getVerticeAtual() {
    return verticeAtual;
  }

  public void setVerticeAtual(Vertice verticeAtual) {
    this.verticeAtual = verticeAtual;
  }

  public Integer getValorPercorrido() {
    return valorPercorrido;
  }

  public void setValorPercorrido(Integer valorPercorrido) {
    this.valorPercorrido = valorPercorrido;
  }

  public void adicionarArestaCaminho(Vertice novoNode) {
    caminho.add(novoNode);
  }

  /**
   * Método que atualiza o caminho até chegar ao vértice ao qual o Node 
   * é referente, de forma que o caminho sempre seja
   * formado pelo menor caminho possivel ao fim da execução de Bellman Ford
   * @param node o objeto Node o qual deve ser passado para chegar até 
   * o Node atual, assumindo que este Node seja o menor caminho
   */
  public void atualizarCaminho(Node node) {
    List<Vertice> novoCaminho = new ArrayList<>(node.getCaminho());
    novoCaminho.add(getVerticeAtual());
    caminho = novoCaminho;
  }
}
```

### Edge:

```java
/**
 * Classe responsável pelo objeto referente a uma Aresta, sendo auxiliar
 * somente para a execução do algoritmo de Bellman Ford.
 * Possui atributos para a aresta a qual o Edge é referente e
 * os Nodes de origem e destino
 */
public class Edge {

  public Aresta arestaAtual;

  public Node origem, destino;

  public Edge(Node origem, Node destino, Aresta aresta) {
    this.origem = origem;
    this.destino = destino;
    arestaAtual = aresta;
  }

  public Aresta getArestaAtual() {
    return arestaAtual;
  }

  public void setArestaAtual(Aresta arestaAtual) {
    this.arestaAtual = arestaAtual;
  }

  public Node getOrigem() {
    return origem;
  }

  public void setOrigem(Node origem) {
    this.origem = origem;
  }

  public Node getDestino() {
    return destino;
  }

  public void setDestino(Node destino) {
    this.destino = destino;
  }

  public Integer getPeso(MetricaCalculo metrica) {
    return arestaAtual.getPeso(metrica);
  }
```

### MetricaCalculo:

```java
public enum MetricaCalculo {
  CUSTO, DISTANCIA, SALTO;
}
```

### CaminhoMinimo:

```java
/**
 * Classe em que está contida toda a lógica referente ao cálculo de caminho mínimo
 */
public abstract class CaminhoMinimo {

  /**
   * Método com a função de executar o cálculo do menor caminho entre um local
     e outro selecionado
   * @param arestas a lista de todas as ligações entre cidades disponíveis
   * @param vertices a lista de todas as cidades disponíveis
   * @param origem a cidade em que se quer começar o trajeto
   * @param destino a cidade em que se quer terminar o trajeto
   * @param metrica a métrica utilizada para se calcular o menor caminho
   * @return um objeto ResultCaminho com os dados do menor caminho encontrado
     entre as duas cidades
   * @throws Exception caso um vértice importante para o caminho mínimo esteja desativado
   */
  public static ResultCaminho bellmanFord
  (List<Aresta> arestas, List<Vertice> vertices, Vertice origem, 
   Vertice destino, MetricaCalculo metrica) 
          throws Exception {
    List<Node> verticesDisponiveis = vertices.stream().filter(Vertice::getAtivo).
             map(Node::new).
            collect(Collectors.toList());
    List<Edge> arestasDisponiveis = new ArrayList<>();
    arestas.stream().filter(Aresta::getArestaDisponivel).forEach(aresta ->
      adicionarArestaLista(aresta, getNode(aresta.getOrigem(), verticesDisponiveis),
          getNode(aresta.getDestino(), verticesDisponiveis), arestasDisponiveis)
    );
    Node nodeOrigem = getNode(origem, verticesDisponiveis);
    Node nodeDestino = getNode(destino, verticesDisponiveis);
    if (nodeDestino == null || nodeOrigem == null) 
    throw new Exception("Origem ou destino está inativo");
    bellmanFord(nodeOrigem, verticesDisponiveis, arestasDisponiveis, metrica);
    return ResultCaminho.instanciar(nodeDestino);
  }

  /**
   * Método que adiciona a partir de uma aresta seus Edge equivalentes, 
     considerando ida e volta
   * @param aresta a aresta a qual se quer gerar Edges
   * @param origem um objeto Node representando o vértice de origem da aresta
   * @param destino um objeto Node representando o vértice de destino da aresta
   * @param listaAresta a lista em que estão sendo armazenados todos os Edges
   */
  private static void adicionarArestaLista(Aresta aresta, Node origem, 
    Node destino, List<Edge> listaAresta) {
    listaAresta.add(new Edge(origem, destino, aresta));
    listaAresta.add(new Edge(destino, origem, aresta));
  }

  /**
   * Método que busca na lista de Nodes qual o equivalente a um Vértice em especifico
   * @param vertice o vértice a qual se quer achar o Node correspondente
   * @param nodes a lista em que estão armazenados todos os Nodes
   * @return um objeto Node que corresponde ao Vértice desejado, ou null caso
     não seja encontrado
   */
  private static Node getNode(Vertice vertice, List<Node> nodes) {
    return nodes.stream().filter(node -> node.getVerticeAtual().getIdCidade() == 
            vertice.getIdCidade()).findFirst()
        .orElse(null);
  }

  /**
   * Método responsável pela lógica por trás do algoritmo de Bellman Ford.
   * @param origem objeto Node representando o local de origem
   * @param vertices a lista de todos os Nodes referentes a todas as cidades disponíveis
   * @param arestas a lista de todos os Edges referentes a todas as arestas disponiveis
   * @param metrica a métrica que é utilizada para o cálculo do caminho mínimo
   */
  private static void bellmanFord(Node origem, List<Node> vertices,
      List<Edge> arestas, MetricaCalculo metrica) {
    // Inicialmente o valor percorrido de todos os Nodes são null,
    // então inicia-se a distância de origem para 0
    origem.setValorPercorrido(0);
    // Faz-se necessária iteração uma quantidade de vezes igual a quantidade de Nodes - 1
    for (int i = 0; i < vertices.size() - 1; i++) {
      // Em cada iteração, percorre todas os Edges disponiveis
      for (Edge aresta : arestas) {
        // Caso o valor percorrido da origem do Edge seja null ignora esta iteração
        if (aresta.getOrigem().getValorPercorrido() == null) continue;
        Node origemAresta = aresta.getOrigem();
        Node destinoAresta = aresta.getDestino();
        // Caso nçao seja ignorada esta iteração é feita uma verificação se o
        // caminho considerando a aresta atual é
        // menor que o caminho já estabelecido anteriormente. Se for, o menor caminho
        // é atualizado para o destino da aresta
        int novoValor = origemAresta.getValorPercorrido() + aresta.getPeso(metrica);
        int valorDestino = destinoAresta.getValorPercorrido() != null ?
            destinoAresta.getValorPercorrido() : Integer.MAX_VALUE;
        if (novoValor < valorDestino) {
          destinoAresta.setValorPercorrido(novoValor);
          destinoAresta.atualizarCaminho(origemAresta);
        }
      }
    }
  }

}
```

### ResultCaminho:

```java
public class ResultCaminho {
  private int ValorPercorrido;
  private List<Vertice> vertice;

  public static ResultCaminho instanciar(Node node) {
    return new ResultCaminho(new ArrayList<>(node.getCaminho()), node.getValorPercorrido());
  }

  ResultCaminho(List<Vertice> vertice, int ValorPercorrido) {
    this.vertice = vertice;
    this.ValorPercorrido = ValorPercorrido;
  }

  public int getValorPercorrido() {
    return this.ValorPercorrido;
  }

  public List<Vertice> getVertice() {
    return this.vertice;
  }
}
```
