package model;

import testHelper.TestMapsHelper;
import auxiliariesTestMethods.AuxiliariesTestMethods;
import dataStructure.graph.matrix.MatrixGraph;
import org.junit.*;
import org.junit.rules.ExpectedException;
import utils.Graph;
import utils.exception.InvalidFileLineException;
import utils.exception.NetworkPointNotFoundException;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Classe para realizar testes à classe DistributionNetwork.
 */
public class DistributionNetworkTest {
    private final String FILE_PATH_PRODUCER_COSTUMER_SMALL = "files/small/clientes-produtores_small.csv";
    private final String FILE_PATH_DISTANCES_SMALL = "files/small/distancias_small.csv";
    private final String FILE_PATH_CABAZES_SMALL = "files/small/cabazes_small.csv";

    private final String FILE_PATH_PRODUCER_COSTUMER_BIG = "files/big/clientes-produtores_big.csv";
    private final String FILE_PATH_DISTANCES_BIG = "files/big/distancias_big.csv";
    private final String FILE_PATH_CABAZES_BIG = "files/big/cabazes_big.csv";

    /**
     * Representa o ficheiro com alguma informação de cabazes a ser lido.
     */
    private final String SMALL_BASKET_FILE = "files/test/cabazes_small_test.csv";

    /**
     * Representa o ficheiro 2 com alguma informação de cabazes a ser lido.
     */
    private final String SMALL_BASKET_FILE_TWO = "files/test/cabazes_small_test_two.csv";

    /**
     * Representa o ficheiro 3 com alguma informação de cabazes a ser lido.
     */
    private final String SMALL_BASKET_FILE_THREE = "files/test/cabazes_small_test_three.csv";

    /**
     * Representa o ficheiro 4 com alguma informação de cabazes a ser lido.
     */
    private final String SMALL_BASKET_FILE_FOUR = "files/test/cabazes_small_test_four.csv";

    /**
     * Representa a rede de distribuição.
     */
    private DistributionNetwork distributionNetwork;

    /**
     * Atributo para indicar a exceção esperada quando é feito algo que lance uma exceção.
     */
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void setUp() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
    }

    /**
     * Testa o método fileToVertex para verificar se os dados de um dado ficheiro cliente-produtores foram adicionados
     * corretamente aos vértices de um determinado grafo.
     */
    @Test
    public void fileToVertexTest1() {
        String filePathTest = "files/test/clientes-produtores_small_test1.csv";
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.fileToVertex(filePathTest);

        Graph<NetworkPoint, Integer> expectedResult = new MatrixGraph<>(false);
        expectedResult.addVertex(new ParticularCostumer("CT1", 40.6389, -8.6553, "C1"));
        expectedResult.addVertex(new ParticularCostumer("CT2", 38.0333, -7.8833, "C2"));
        expectedResult.addVertex(new ParticularCostumer("CT3", 41.5333, -8.4167, "C3"));
        expectedResult.addVertex(new CompanyCostumer("CT14", 38.5243, -8.8926, "E1"));
        expectedResult.addVertex(new CompanyCostumer("CT11", 39.3167, -7.4167, "E2"));
        expectedResult.addVertex(new CompanyCostumer("CT5", 39.823, -7.4931, "E3"));
        expectedResult.addVertex(new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1"));
        expectedResult.addVertex(new AgriculturalProducer("CT6", 40.2111, -8.4291, "P2"));
        expectedResult.addVertex(new AgriculturalProducer("CT10", 39.7444, -8.8072, "P3"));

        assertEquals(expectedResult, distributionNetwork.getDistributionNetwork());
    }

    /**
     * Testa o método fileToVertex para verificar se os dados duplicados de um dado ficheiro cliente-produtores foram
     * adicionados corretamente aos vértices de um determinado grafo.
     */
    @Test
    public void fileToVertexTest2() {
        String filePathTest = "files/test/clientes-produtores_small_test2.csv";
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.fileToVertex(filePathTest);

        Graph<NetworkPoint, Integer> expectedResult = new MatrixGraph<>(false);
        expectedResult.addVertex(new ParticularCostumer("CT1", 40.6389, -8.6553, "C1"));
        expectedResult.addVertex(new ParticularCostumer("CT2", 38.0333, -7.8833, "C2"));
        expectedResult.addVertex(new ParticularCostumer("CT3", 41.5333, -8.4167, "C3"));

        assertEquals(expectedResult, distributionNetwork.getDistributionNetwork());
    }

    /**
     * Testa o método fileToVertex para verificar se dado um ficheiro cliente-produtores completamente vazio, os vértices
     * de um determinado grafo continuam iguais.
     */
    @Test
    public void fileToVertexTest3() {
        String filePathTest = "files/test/clientes-produtores_small_test3.csv";
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.fileToVertex(filePathTest);

        Graph<NetworkPoint, Integer> expectedResult = new MatrixGraph<>(false);

        assertEquals(expectedResult, distributionNetwork.getDistributionNetwork());
    }

    /**
     * Testa o método fileToEdge para verificar se os dados de um dado ficheiro de distâncias foram adicionados corretamente
     * às arestas de um determinado grafo.
     */
    @Test
    public void fileToEdgeTest1() {
        String filePathTest = "files/test/distancias_small_test1.csv";
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.fileToEdge(filePathTest);

        Graph<NetworkPoint, Integer> expectedResult = new MatrixGraph<>(false);
        expectedResult.addEdge(new NetworkPoint("CT10"), new NetworkPoint("CT6"), 63448);
        expectedResult.addEdge(new NetworkPoint("CT10"), new NetworkPoint("CT4"), 67584);
        expectedResult.addEdge(new NetworkPoint("CT10"), new NetworkPoint("CT1"), 110848);
        expectedResult.addEdge(new NetworkPoint("CT10"), new NetworkPoint("CT5"), 125041);
        expectedResult.addEdge(new NetworkPoint("CT12"), new NetworkPoint("CT3"), 50467);
        expectedResult.addEdge(new NetworkPoint("CT12"), new NetworkPoint("CT1"), 62877);
        expectedResult.addEdge(new NetworkPoint("CT12"), new NetworkPoint("CT10"), 717);
        expectedResult.addEdge(new NetworkPoint("CT11"), new NetworkPoint("CT5"), 62655);
        expectedResult.addEdge(new NetworkPoint("CT14"), new NetworkPoint("CT1"), 121584);

        assertEquals(expectedResult, distributionNetwork.getDistributionNetwork());
    }

    /**
     * Testa o método fileToEdge para verificar se os dados duplicados de um dado ficheiro de distâncias foram
     * adicionados corretamente às arestas de um determinado grafo.
     */
    @Test
    public void fileToEdgeTest2() {
        String filePathTest = "files/test/distancias_small_test2.csv";
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.fileToEdge(filePathTest);

        Graph<NetworkPoint, Integer> expectedResult = new MatrixGraph<>(false);
        expectedResult.addEdge(new NetworkPoint("CT10"), new NetworkPoint("CT6"), 63448);
        expectedResult.addEdge(new NetworkPoint("CT10"), new NetworkPoint("CT4"), 67584);
        expectedResult.addEdge(new NetworkPoint("CT10"), new NetworkPoint("CT1"), 110848);

        assertEquals(expectedResult, distributionNetwork.getDistributionNetwork());
    }

    /**
     * Testa o método fileToEdge para verificar se dado um ficheiro de distâncias completamente vazio, as arestas
     * de um determinado grafo continuam iguais.
     */
    @Test
    public void fileToEdgeTest3() {
        String filePathTest = "files/test/distancias_small_test3.csv";
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.fileToEdge(filePathTest);

        Graph<NetworkPoint, Integer> expectedResult = new MatrixGraph<>(false);

        assertEquals(expectedResult, distributionNetwork.getDistributionNetwork());
    }

    /**
     * Método que testa o método que trata de chamar a leitura do ficheiro de cabazes quando existe um dia inválido no ficheiro de
     * importação de cabazes.
     */
    @Test
    public void fileToBasketTest1() {
        exceptionRule.expect(NumberFormatException.class);
        exceptionRule.expectMessage("Verifique o valor do dia na linha 5 do ficheiro, pois encontra-se inválido.");
        String invalid_day_file = "files/test/cabazes_small_test_fail_wrong_day.csv";
        distributionNetwork.fileToBasket(invalid_day_file);
    }

    /**
     * Método que testa o método que trata de chamar a leitura do ficheiro de cabazes quando uma quantidade está
     * inválido o ficheiro de importação de cabazes.
     */
    @Test
    public void fileToBasketTest2() {
        exceptionRule.expect(NumberFormatException.class);
        exceptionRule.expectMessage("Foi encontrado um erro na linha 10 do ficheiro. Verifique as quantidades " +
                "existentes para os produtos nessa mesma linha.");
        String invalid_amount_file = "files/test/cabazes_small_test_fail_invalid_amount.csv";
        distributionNetwork.fileToBasket(invalid_amount_file);
    }

    /**
     * Método que testa o método que trata de chamar a leitura do ficheiro de cabazes quando é indicado um cliente ou
     * produtor no ficheiro de importação de cabazes, e que este não existe na rede de distribuição.
     */
    @Test
    public void fileToBasketTest3() {
        exceptionRule.expect(NetworkPointNotFoundException.class);
        exceptionRule.expectMessage("O cliente ou produtor específicado na linha 20 do ficheiro, não foi " +
                "encontrado na rede de distribuição. Por favor, verifique se está correto.");
        String nonexistent_codidentifier_file = "files/test/cabazes_small_test_fail_nonexistent_codIdentifier.csv";
        distributionNetwork.fileToBasket(nonexistent_codidentifier_file);
    }

    /**
     * Método que testa o método que trata de chamar a leitura do ficheiro de cabazes quando uma linha de um ficheiro
     * tem um número de colunas diferente ao número de colunas que o cabeçalho desse mesmo ficheiro tem.
     */
    @Test
    public void fileToBasketTest4() {
        exceptionRule.expect(InvalidFileLineException.class);
        exceptionRule.expectMessage(new InvalidFileLineException(19).getMessage());
        String invalid_line_size_file = "files/test/cabazes_small_test_fail_invalid_line_size.csv";
        distributionNetwork.fileToBasket(invalid_line_size_file);
    }

    /**
     * Método que testa o método que trata de chamar a leitura do ficheiro de cabazes quando o caminho de um ficheiro
     * passado para o método, não existe.
     */
    @Test
    public void fileToBasketTest5() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Não existe nenhum ficheiro no caminho indicado.");
        String inexistent_file = "não existe este ficheiro";
        distributionNetwork.fileToBasket(inexistent_file);
    }

    /**
     * Método que testa o método que trata de chamar a leitura do ficheiro de cabazes quando a rede de distribuição é
     * nula ou não contem vértices.
     */
    @Test
    public void fileToBasketTest6() {
        DistributionNetwork nullDistributionNetwork = new DistributionNetwork();
        nullDistributionNetwork.fileToBasket(SMALL_BASKET_FILE);
        Assert.assertNull(nullDistributionNetwork.getProductsAvailable());
        Assert.assertNull(nullDistributionNetwork.getProductsRequest());
    }

    /**
     * Método que verifica se o método que trata de chamar a leitura do ficheiro de cabazes constrói corretamente o
     * mapa de produtos disponíveis (em cabaz), e se posteriormente este é clonado corretamente.
     */
    @Test
    public void fileToBasketTest7() {
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE);
        Map<Integer, Map<AgriculturalProducer, Basket>> expectedMap = new HashMap<>();
        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");
        AgriculturalProducer p2 = new AgriculturalProducer("CT6", 40.2111, -8.4291, "P2");
        AgriculturalProducer p3 = new AgriculturalProducer("CT10", 39.7444, -8.8072, "P3");

        Map<AgriculturalProducer, Basket> dayOneMap = new HashMap<>();
        Map<AgriculturalProducer, Basket> dayTwoMap = new HashMap<>();

        Product productOne = new Product("Prod1");
        Product productTwo = new Product("Prod2");
        Product productThree = new Product("Prod3");

        // Apenas produtos com quantidade superior a 0.0 são armazenados
        // Produtor 1
        Basket p1BasketDayOne = new Basket();
        p1BasketDayOne.addProduct(productTwo, 7.5);
        p1BasketDayOne.addProduct(productThree, 9.0);
        dayOneMap.put(p1, p1BasketDayOne);

        Basket p1BasketDayTwo = new Basket();
        p1BasketDayTwo.addProduct(productOne, 3.0);
        dayTwoMap.put(p1, p1BasketDayTwo);

        // Produtor 2
        Basket p2BasketDayOne = new Basket();
        p2BasketDayOne.addProduct(productOne, 7.5);
        p2BasketDayOne.addProduct(productTwo, 6.5);
        p2BasketDayOne.addProduct(productThree, 1.5);
        dayOneMap.put(p2, p2BasketDayOne);

        Basket p2BasketDayTwo = new Basket();
        p2BasketDayTwo.addProduct(productOne, 6.0);
        p2BasketDayTwo.addProduct(productThree, 10.0);
        dayTwoMap.put(p2, p2BasketDayTwo);

        // Produtor 3
        Basket p3BasketDayOne = new Basket();
        p3BasketDayOne.addProduct(productOne, 2.5);
        p3BasketDayOne.addProduct(productTwo, 2.0);
        dayOneMap.put(p3, p3BasketDayOne);

        expectedMap.put(1, dayOneMap);
        expectedMap.put(2, dayTwoMap);

        TestMapsHelper.testProductsAvailableOrRequestedMaps(expectedMap, distributionNetwork.getProductsAvailable());

    }

    /**
     * Método que verifica se o método que trata de chamar a leitura do ficheiro de cabazes, constrói corretamente o
     * mapa de produtos pedidos (em cabaz), e se posteriormente este é clonado corretamente.
     */
    @Test
    public void fileToBasketTest8() {
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE);
        Map<Integer, Map<Costumer, Basket>> expectedMap = new HashMap<>();

        ParticularCostumer particularCostumerOne = new ParticularCostumer("CT1", 40.6389, -8.6553, "C1");
        ParticularCostumer particularCostumerTwo = new ParticularCostumer("CT2", 38.0333, -7.8833, "C2");
        ParticularCostumer particularCostumerThree = new ParticularCostumer("CT3", 41.5333, -8.4167, "C3");
        CompanyCostumer companyCostumerOne = new CompanyCostumer("CT14", 38.5243, -8.8926, "E1");
        CompanyCostumer companyCostumerTwo = new CompanyCostumer("CT11", 39.3167, -7.4167, "E2");
        CompanyCostumer companyCostumerThree = new CompanyCostumer("CT5", 39.823, -7.4931, "E3");

        Map<Costumer, Basket> dayOneMap = new HashMap<>();
        Map<Costumer, Basket> dayTwoMap = new HashMap<>();

        Product productOne = new Product("Prod1");
        Product productTwo = new Product("Prod2");
        Product productThree = new Product("Prod3");

        // Apenas produtos com quantidade superior a 0.0 são armazenados como pedido
        // Particular Costumer 1
        Basket c1BasketDayTwo = new Basket();
        c1BasketDayTwo.addProduct(productOne, 4.5);
        c1BasketDayTwo.addProduct(productTwo, 6.0);
        c1BasketDayTwo.addProduct(productThree, 3.5);
        dayTwoMap.put(particularCostumerOne, c1BasketDayTwo);

        // Particular Costumer 2
        Basket c2BasketDayOne = new Basket();
        c2BasketDayOne.addProduct(productTwo, 5.5);
        c2BasketDayOne.addProduct(productThree, 4.5);
        dayOneMap.put(particularCostumerTwo, c2BasketDayOne);

        Basket c2BasketDayTwo = new Basket();
        c2BasketDayTwo.addProduct(productOne, 9.0);
        c2BasketDayTwo.addProduct(productTwo, 7.0);
        dayTwoMap.put(particularCostumerTwo, c2BasketDayTwo);

        // Particular Costumer 3
        Basket c3BasketDayTwo = new Basket();
        c3BasketDayTwo.addProduct(productOne, 10.0);
        c3BasketDayTwo.addProduct(productTwo, 20.0);
        dayTwoMap.put(particularCostumerThree, c3BasketDayTwo);

        // Company Costumer 1
        Basket e1BasketDayTwo = new Basket();
        e1BasketDayTwo.addProduct(productThree, 9.5);
        dayTwoMap.put(companyCostumerOne, e1BasketDayTwo);

        // Company Costumer 2
        Basket e2BasketDayOne = new Basket();
        e2BasketDayOne.addProduct(productOne, 9.0);
        e2BasketDayOne.addProduct(productTwo, 6.0);
        e2BasketDayOne.addProduct(productThree, 9.0);
        dayOneMap.put(companyCostumerTwo, e2BasketDayOne);

        Basket e2BasketDayTwo = new Basket();
        e2BasketDayTwo.addProduct(productThree, 7.5);
        dayTwoMap.put(companyCostumerTwo, e2BasketDayTwo);

        // Company Costumer 3
        Basket e3BasketDayTwo = new Basket();
        e3BasketDayTwo.addProduct(productOne, 8.5);
        e3BasketDayTwo.addProduct(productTwo, 5.0);
        dayTwoMap.put(companyCostumerThree, e3BasketDayTwo);

        expectedMap.put(1, dayOneMap);
        expectedMap.put(2, dayTwoMap);

        TestMapsHelper.testProductsAvailableOrRequestedMaps(expectedMap, distributionNetwork.getProductsRequest());

    }

    /**
     * Método para testar o valor devolvido pelo método getProductsAvailable quando não existe dados na rede de
     * distribuição.
     */
    @Test
    public void getProductsAvailableTest() {
        DistributionNetwork distributionNetworkTest = new DistributionNetwork();
        assertNull(distributionNetworkTest.getProductsAvailable());
    }

    /**
     * Método para testar o valor devolvido pelo método getProductsRequest quando não existe dados na rede de
     * distribuição.
     */
    @Test
    public void getProductsRequestTest() {
        DistributionNetwork distributionNetworkTest = new DistributionNetwork();
        assertNull(distributionNetworkTest.getProductsRequest());
    }

    /**
     * Testa o método buildDistributionNetwork para verificar se os dados de um dado ficheiro de cliente-produtores e distâncias
     * foram adicionados corretamente aos vértices e arestas de um determinado grafo.
     */
    @Test
    public void buildDistributionNetworkTest1() {
        String filePathProducerCostumer = "files/test/clientes-produtores_small_test1.csv";
        String filePathDistances = "files/test/distancias_small_test1.csv";

        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(filePathProducerCostumer, filePathDistances);

        Graph<NetworkPoint, Integer> expectedResult = new MatrixGraph<>(false);
        // Vertex
        expectedResult.addVertex(new ParticularCostumer("CT1", 40.6389, -8.6553, "C1"));
        expectedResult.addVertex(new ParticularCostumer("CT2", 38.0333, -7.8833, "C2"));
        expectedResult.addVertex(new ParticularCostumer("CT3", 41.5333, -8.4167, "C3"));
        expectedResult.addVertex(new CompanyCostumer("CT14", 38.5243, -8.8926, "E1"));
        expectedResult.addVertex(new CompanyCostumer("CT11", 39.3167, -7.4167, "E2"));
        expectedResult.addVertex(new CompanyCostumer("CT5", 39.823, -7.4931, "E3"));
        expectedResult.addVertex(new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1"));
        expectedResult.addVertex(new AgriculturalProducer("CT6", 40.2111, -8.4291, "P2"));
        expectedResult.addVertex(new AgriculturalProducer("CT10", 39.7444, -8.8072, "P3"));

        // Edge
        expectedResult.addEdge(new NetworkPoint("CT10"), new NetworkPoint("CT6"), 63448);
        expectedResult.addEdge(new NetworkPoint("CT10"), new NetworkPoint("CT4"), 67584);
        expectedResult.addEdge(new NetworkPoint("CT10"), new NetworkPoint("CT1"), 110848);
        expectedResult.addEdge(new NetworkPoint("CT10"), new NetworkPoint("CT5"), 125041);
        expectedResult.addEdge(new NetworkPoint("CT12"), new NetworkPoint("CT3"), 50467);
        expectedResult.addEdge(new NetworkPoint("CT12"), new NetworkPoint("CT1"), 62877);
        expectedResult.addEdge(new NetworkPoint("CT12"), new NetworkPoint("CT10"), 70717);
        expectedResult.addEdge(new NetworkPoint("CT11"), new NetworkPoint("CT5"), 62655);
        expectedResult.addEdge(new NetworkPoint("CT14"), new NetworkPoint("CT1"), 121584);

        assertEquals(expectedResult, distributionNetwork.getDistributionNetwork());
    }

    /**
     * Testa o método buildDistributionNetwork para verificar se os dados duplicados de um dado ficheiro de cliente-produtores
     * e distâncias foram adicionados corretamente aos vértices e arestas de um determinado grafo.
     */
    @Test
    public void buildDistributionNetworkTest2() {
        String filePathProducerCostumer = "files/test/clientes-produtores_small_test2.csv";
        String filePathDistances = "files/test/distancias_small_test2.csv";

        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(filePathProducerCostumer, filePathDistances);

        Graph<NetworkPoint, Integer> expectedResult = new MatrixGraph<>(false);
        // Vertex
        expectedResult.addVertex(new ParticularCostumer("CT1", 40.6389, -8.6553, "C1"));
        expectedResult.addVertex(new ParticularCostumer("CT2", 38.0333, -7.8833, "C2"));
        expectedResult.addVertex(new ParticularCostumer("CT3", 41.5333, -8.4167, "C3"));

        // Edge
        expectedResult.addEdge(new NetworkPoint("CT10"), new NetworkPoint("CT6"), 63448);
        expectedResult.addEdge(new NetworkPoint("CT10"), new NetworkPoint("CT4"), 67584);
        expectedResult.addEdge(new NetworkPoint("CT10"), new NetworkPoint("CT1"), 110848);

        assertEquals(expectedResult, distributionNetwork.getDistributionNetwork());
    }

    /**
     * Testa o método buildDistributionNetwork para verificar se dado um ficheiro de cliente-produtores e distâncias
     * completamente vazios, os vértices e arestas de um determinado grafo continuam iguais.
     */
    @Test
    public void buildDistributionNetworkTest3() {
        String filePathProducerCostumer = "files/test/clientes-produtores_small_test3.csv";
        String filePathDistances = "files/test/distancias_small_test3.csv";

        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(filePathProducerCostumer, filePathDistances);

        Graph<NetworkPoint, Integer> expectedResult = new MatrixGraph<>(false);

        assertEquals(expectedResult, distributionNetwork.getDistributionNetwork());
    }

    /**
     * Testa o método minimumSpanningTree para verificar se dado um ficheiro de cliente-produtores e distâncias, a árvore
     * de extensão mínima que conecta todos os clientes e produtores agrícolas com uma distância total mínima encontra-se correta.
     */
    @Test
    public void minimumSpanningTreeTest1() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);

        Graph<NetworkPoint, Integer> result = distributionNetwork.minimumSpanningTree();
        Graph<NetworkPoint, Integer> expectedResult = new MatrixGraph<>(false);
        expectedResult.addEdge(new NetworkPoint("CT1"), new NetworkPoint("CT6"), 56717);
        expectedResult.addEdge(new NetworkPoint("CT1"), new NetworkPoint("CT12"), 62877);
        expectedResult.addEdge(new NetworkPoint("CT1"), new NetworkPoint("CT17"), 69282);
        expectedResult.addEdge(new NetworkPoint("CT2"), new NetworkPoint("CT7"), 65574);
        expectedResult.addEdge(new NetworkPoint("CT2"), new NetworkPoint("CT8"), 125105);
        expectedResult.addEdge(new NetworkPoint("CT3"), new NetworkPoint("CT12"), 50467);
        expectedResult.addEdge(new NetworkPoint("CT3"), new NetworkPoint("CT15"), 43598);
        expectedResult.addEdge(new NetworkPoint("CT3"), new NetworkPoint("CT16"), 68957);
        expectedResult.addEdge(new NetworkPoint("CT4"), new NetworkPoint("CT16"), 110133);
        expectedResult.addEdge(new NetworkPoint("CT5"), new NetworkPoint("CT9"), 90186);
        expectedResult.addEdge(new NetworkPoint("CT5"), new NetworkPoint("CT11"), 62655);
        expectedResult.addEdge(new NetworkPoint("CT6"), new NetworkPoint("CT10"), 67584);
        expectedResult.addEdge(new NetworkPoint("CT7"), new NetworkPoint("CT14"), 95957);
        expectedResult.addEdge(new NetworkPoint("CT9"), new NetworkPoint("CT17"), 62879);
        expectedResult.addEdge(new NetworkPoint("CT10"), new NetworkPoint("CT13"), 63448);
        expectedResult.addEdge(new NetworkPoint("CT13"), new NetworkPoint("CT14"), 89813);

        assertEquals(expectedResult, result);
    }

    /**
     * Testa o método minimumSpanningTree para verificar se dado um ficheiro de cliente-produtores e distâncias com apenas
     * 3 pontos de rede, a árvore de extensão mínima que conecta todos os clientes e produtores agrícolas com uma distância
     * total mínima encontra-se correta.
     */
    @Test
    public void minimumSpanningTreeTest2() {
        String filePathProducerCostumer = "files/test/clientes-produtores_small_test4.csv";
        String filePathDistances = "files/test/distancias_small_test4.csv";

        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(filePathProducerCostumer, filePathDistances);

        Graph<NetworkPoint, Integer> result = distributionNetwork.minimumSpanningTree();
        Graph<NetworkPoint, Integer> expectedResult = new MatrixGraph<>(false);
        expectedResult.addEdge(new NetworkPoint("CT1"), new NetworkPoint("CT2"), 63448);
        expectedResult.addEdge(new NetworkPoint("CT2"), new NetworkPoint("CT3"), 63448);


        assertEquals(expectedResult, result);
    }

    /**
     * Testa o método minimumSpanningTree para verificar se dado um ficheiro de cliente-produtores e distâncias completamente
     * vazios, a árvore de extensão mínima também encontra-se vazia.
     */
    @Test
    public void minimumSpanningTreeTest3() {
        String filePathProducerCostumer = "files/test/clientes-produtores_small_test3.csv";
        String filePathDistances = "files/test/distancias_small_test3.csv";

        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(filePathProducerCostumer, filePathDistances);

        Graph<NetworkPoint, Integer> result = distributionNetwork.minimumSpanningTree();
        Graph<NetworkPoint, Integer> expectedResult = new MatrixGraph<>(false);

        assertEquals(expectedResult, result);
    }

    /**
     * Testa o método checkConnectivity para verificar se o grafo é conexo
     */
    @Test
    public void checkConnectivityTest1() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);

        boolean result = distributionNetwork.checkConnectivity();

        assertTrue(result);
    }

    /**
     * Testa o método checkConnectivity para verificar se o grafo não é conexo
     */
    @Test
    public void checkConnectivityTest2() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork("files/test/clientes-produtores_small_test5.csv", "files/test/distancias_small_test5.csv");

        boolean result = distributionNetwork.checkConnectivity();

        assertFalse(result);
    }

    /**
     * Testa o método minimumNumberNetwork para verificar o número mínimo de ligações do grafo.
     */
    @Test
    public void minimumNumberNetworkTest1() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);

        int result = distributionNetwork.minimumNumberNetwork();
        int expectedResult = 605261;
        assertEquals(expectedResult, result);
    }

    /**
     * Testa o método minimumNumberNetwork para verificar o número mínimo de ligações do grafo.
     */
    @Test
    public void minimumNumberNetworkTest2() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork("files/test/clientes-produtores_small_test5.csv", "files/test/distancias_small_test5.csv");

        int result = distributionNetwork.minimumNumberNetwork();
        int expectedResult = 67584;
        assertEquals(expectedResult, result);
    }

    /**
     * Testa o metodo calculateMinimumNumberNetwor para verfificar se o grafo é conexo e
     * caso seja calcular o número mínimo de ligações.
     */
    @Test
    public void calculateMinimumNumberNetworkTest1() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);

        int result = distributionNetwork.calculateMinimumNumberNetwork();
        int expectedResult = 605261;
        assertEquals(expectedResult, result);
    }

    /**
     * Testa o metodo calculateMinimumNumberNetwor para verfificar se o grafo é conexo e
     * caso seja calcular o número mínimo de ligações.
     */
    @Test
    public void calculateMinimumNumberNetworkTest2() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork("files/test/clientes-produtores_small_test5.csv", "files/test/distancias_small_test5.csv");

        int result = distributionNetwork.calculateMinimumNumberNetwork();
        int expectedResult = 0;
        assertEquals(expectedResult, result);
    }

    /**
     * Testa o método defineHubs para definir os hubs da rede de distribuição, recebendo por parametro um valor que fará com que sejam criados dois hubs.
     */
    @Test
    public void defineHubsTest1() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);

        distributionNetwork.defineHubs(2);

        NetworkPoint cc1 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT14"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected1 = ((CompanyCostumer) cc1).isHub();

        NetworkPoint cc2 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT11"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected2 = ((CompanyCostumer) cc2).isHub();

        NetworkPoint cc3 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT5"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected3 = ((CompanyCostumer) cc3).isHub();

        NetworkPoint cc4 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT9"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected4 = ((CompanyCostumer) cc4).isHub();

        NetworkPoint cc5 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT4"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected5 = ((CompanyCostumer) cc5).isHub();

        assertFalse(expected1);
        assertTrue(expected2);
        assertTrue(expected3);
        assertFalse(expected4);
        assertFalse(expected5);
    }

    /**
     * Testa o método defineHubs para definir os hubs da rede de distribuição, recebendo por parametro um valor que fará com que sejam criados três hubs.
     */
    @Test
    public void defineHubsTest2() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);

        distributionNetwork.defineHubs(3);

        NetworkPoint cc1 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT14"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected1 = ((CompanyCostumer) cc1).isHub();

        NetworkPoint cc2 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT11"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected2 = ((CompanyCostumer) cc2).isHub();

        NetworkPoint cc3 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT5"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected3 = ((CompanyCostumer) cc3).isHub();

        NetworkPoint cc4 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT9"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected4 = ((CompanyCostumer) cc4).isHub();

        NetworkPoint cc5 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT4"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected5 = ((CompanyCostumer) cc5).isHub();

        assertFalse(expected1);
        assertTrue(expected2);
        assertTrue(expected3);
        assertTrue(expected4);
        assertFalse(expected5);
    }

    /**
     * Testa o método defineHubs para definir os hubs da rede de distribuição, recebendo por parametro um valor que fará com que sejam criados cinco hubs, todos os possiveis com os dados usados.
     */
    @Test
    public void defineHubsTest3() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);

        distributionNetwork.defineHubs(5);

        NetworkPoint cc1 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT14"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected1 = ((CompanyCostumer) cc1).isHub();

        NetworkPoint cc2 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT11"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected2 = ((CompanyCostumer) cc2).isHub();

        NetworkPoint cc3 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT5"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected3 = ((CompanyCostumer) cc3).isHub();

        NetworkPoint cc4 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT9"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected4 = ((CompanyCostumer) cc4).isHub();

        NetworkPoint cc5 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT4"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected5 = ((CompanyCostumer) cc5).isHub();

        assertTrue(expected1);
        assertTrue(expected2);
        assertTrue(expected3);
        assertTrue(expected4);
        assertTrue(expected5);
    }

    /**
     * Testa o método defineHubs para definir os hubs da rede de distribuição, recebendo por parametro um valor que fará com que sejam criados cinco hubs, todos os possiveis com os dados usados.
     */
    @Test
    public void defineHubsTest4() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);

        distributionNetwork.defineHubs(69);

        NetworkPoint cc1 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT14"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected1 = ((CompanyCostumer) cc1).isHub();

        NetworkPoint cc2 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT11"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected2 = ((CompanyCostumer) cc2).isHub();

        NetworkPoint cc3 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT5"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected3 = ((CompanyCostumer) cc3).isHub();

        NetworkPoint cc4 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT9"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected4 = ((CompanyCostumer) cc4).isHub();

        NetworkPoint cc5 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT4"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected5 = ((CompanyCostumer) cc5).isHub();

        assertTrue(expected1);
        assertTrue(expected2);
        assertTrue(expected3);
        assertTrue(expected4);
        assertTrue(expected5);
    }

    /**
     * Testa o método defineHubs para definir os hubs da rede de distribuição, recebendo por parametro um valor que fará com que sejam criados zero hubs.
     */
    @Test
    public void defineHubsTest5() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);

        distributionNetwork.defineHubs(0);

        NetworkPoint cc1 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT14"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected1 = ((CompanyCostumer) cc1).isHub();

        NetworkPoint cc2 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT11"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected2 = ((CompanyCostumer) cc2).isHub();

        NetworkPoint cc3 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT5"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected3 = ((CompanyCostumer) cc3).isHub();

        NetworkPoint cc4 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT9"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected4 = ((CompanyCostumer) cc4).isHub();

        NetworkPoint cc5 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT4"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected5 = ((CompanyCostumer) cc5).isHub();

        assertFalse(expected1);
        assertFalse(expected2);
        assertFalse(expected3);
        assertFalse(expected4);
        assertFalse(expected5);
    }

    /**
     * Testa o método defineHubs para definir os hubs da rede de distribuição, recebendo por parametro um valor que fará com que sejam criados zero hubs.
     */
    @Test
    public void defineHubsTest6() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);

        distributionNetwork.defineHubs(-1);

        NetworkPoint cc1 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT14"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected1 = ((CompanyCostumer) cc1).isHub();

        NetworkPoint cc2 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT11"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected2 = ((CompanyCostumer) cc2).isHub();

        NetworkPoint cc3 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT5"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected3 = ((CompanyCostumer) cc3).isHub();

        NetworkPoint cc4 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT9"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected4 = ((CompanyCostumer) cc4).isHub();

        NetworkPoint cc5 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT4"), distributionNetwork.getDistributionNetwork().vertices());
        boolean expected5 = ((CompanyCostumer) cc5).isHub();

        assertFalse(expected1);
        assertFalse(expected2);
        assertFalse(expected3);
        assertFalse(expected4);
        assertFalse(expected5);
    }

    /**
     * Testa o método determineNearestHub para verificar se todos os clientes tem hub mais próximo esperado.
     */
    @Test
    public void determineNearestHubTest1() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);

        distributionNetwork.determineNearestHub();

        NetworkPoint cc1 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT1"), distributionNetwork.getDistributionNetwork().vertices());
        CompanyCostumer result1 = cc1.getNearestHub();
        CompanyCostumer expected1 = (CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT9"), distributionNetwork.getDistributionNetwork().vertices());

        NetworkPoint cc2 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT2"), distributionNetwork.getDistributionNetwork().vertices());
        CompanyCostumer result2 = cc2.getNearestHub();
        CompanyCostumer expected2 = (CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT14"), distributionNetwork.getDistributionNetwork().vertices());

        NetworkPoint cc3 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT3"), distributionNetwork.getDistributionNetwork().vertices());
        CompanyCostumer result3 = cc3.getNearestHub();
        CompanyCostumer expected3 = (CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT4"), distributionNetwork.getDistributionNetwork().vertices());

        NetworkPoint cc4 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT15"), distributionNetwork.getDistributionNetwork().vertices());
        CompanyCostumer result4 = cc4.getNearestHub();
        CompanyCostumer expected4 = (CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT4"), distributionNetwork.getDistributionNetwork().vertices());

        NetworkPoint cc5 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT16"), distributionNetwork.getDistributionNetwork().vertices());
        CompanyCostumer result5 = cc5.getNearestHub();
        CompanyCostumer expected5 = (CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT9"), distributionNetwork.getDistributionNetwork().vertices());

        NetworkPoint cc6 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT12"), distributionNetwork.getDistributionNetwork().vertices());
        CompanyCostumer result6 = cc6.getNearestHub();
        CompanyCostumer expected6 = (CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT9"), distributionNetwork.getDistributionNetwork().vertices());

        NetworkPoint cc7 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT7"), distributionNetwork.getDistributionNetwork().vertices());
        CompanyCostumer result7 = cc7.getNearestHub();
        CompanyCostumer expected7 = (CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT14"), distributionNetwork.getDistributionNetwork().vertices());

        NetworkPoint cc8 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT8"), distributionNetwork.getDistributionNetwork().vertices());
        CompanyCostumer result8 = cc8.getNearestHub();
        CompanyCostumer expected8 = (CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT14"), distributionNetwork.getDistributionNetwork().vertices());

        NetworkPoint cc9 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT13"), distributionNetwork.getDistributionNetwork().vertices());
        CompanyCostumer result9 = cc9.getNearestHub();
        CompanyCostumer expected9 = (CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT14"), distributionNetwork.getDistributionNetwork().vertices());

        NetworkPoint cc10 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT14"), distributionNetwork.getDistributionNetwork().vertices());
        CompanyCostumer result10 = cc10.getNearestHub();
        CompanyCostumer expected10 = (CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT11"), distributionNetwork.getDistributionNetwork().vertices());

        NetworkPoint cc11 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT11"), distributionNetwork.getDistributionNetwork().vertices());
        CompanyCostumer result11 = cc11.getNearestHub();
        CompanyCostumer expected11 = (CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT5"), distributionNetwork.getDistributionNetwork().vertices());

        NetworkPoint cc12 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT5"), distributionNetwork.getDistributionNetwork().vertices());
        CompanyCostumer result12 = cc12.getNearestHub();
        CompanyCostumer expected12 = (CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT11"), distributionNetwork.getDistributionNetwork().vertices());

        NetworkPoint cc13 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT9"), distributionNetwork.getDistributionNetwork().vertices());
        CompanyCostumer result13 = cc13.getNearestHub();
        CompanyCostumer expected13 = (CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT5"), distributionNetwork.getDistributionNetwork().vertices());

        NetworkPoint cc14 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT4"), distributionNetwork.getDistributionNetwork().vertices());
        CompanyCostumer result14 = cc14.getNearestHub();
        CompanyCostumer expected14 = (CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT9"), distributionNetwork.getDistributionNetwork().vertices());

        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);
        assertEquals(expected4, result4);
        assertEquals(expected5, result5);
        assertEquals(expected6, result6);
        assertEquals(expected7, result7);
        assertEquals(expected8, result8);
        assertEquals(expected9, result9);
        assertEquals(expected10, result10);
        assertEquals(expected11, result11);
        assertEquals(expected12, result12);
        assertEquals(expected13, result13);
        assertEquals(expected14, result14);
    }

    /**
     * Testa o método determineNearestHub para verifiicar se os produtores agrícolas não têm nenhum hub.
     */
    @Test
    public void determineNearestHubTest2() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);

        distributionNetwork.determineNearestHub();

        NetworkPoint cc1 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT17"), distributionNetwork.getDistributionNetwork().vertices());
        CompanyCostumer result1 = cc1.getNearestHub();

        NetworkPoint cc2 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT6"), distributionNetwork.getDistributionNetwork().vertices());
        CompanyCostumer result2 = cc2.getNearestHub();

        NetworkPoint cc3 = AuxiliariesTestMethods.itemInList(new NetworkPoint("CT10"), distributionNetwork.getDistributionNetwork().vertices());
        CompanyCostumer result3 = cc3.getNearestHub();

        assertNull(result1);
        assertNull(result2);
        assertNull(result3);
    }

    /**
     * Método para testar se é possivel gerar uma lista de expedição sem restrições, para um dado dia que não tem dados
     * registados.
     */
    @Test
    public void generateExpeditionListWithoutRestrictionTest1() {
        List<BasketExpedition> basketExpeditionsList = distributionNetwork.generateExpeditionListWithoutRestriction(10);
        assertNull(basketExpeditionsList);
    }

    /**
     * Método que testa se a lista de expedição sem restrições gerada para o dia 1 do ficheiro SMALL_BASKET_FILE, é
     * gerada corretamente.
     */
    @Test
    public void generateExpeditionListWithoutRestrictionTest2() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE);

        int day = 1;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        List<BasketExpedition> expectedBasketExpeditions = new ArrayList<>();
        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");
        AgriculturalProducer p2 = new AgriculturalProducer("CT6", 40.2111, -8.4291, "P2");
        Product productOne = new Product("Prod1");
        Product productTwo = new Product("Prod2");
        Product productThree = new Product("Prod3");
        ParticularCostumer particularCostumerTwo = new ParticularCostumer("CT2", 38.0333, -7.8833, "C2");
        CompanyCostumer companyCostumerTwo = new CompanyCostumer("CT11", 39.3167, -7.4167, "E2");

        // Cabaz de expedição para o cliente C2
        BasketExpedition basketExpeditionC2Day1 = new BasketExpedition(particularCostumerTwo, day);
        ProductProvided productProvidedOneC2Day1 = new ProductProvided(productTwo, 5.5, 5.5, p2);
        ProductProvided productProvidedTwoC2Day1 = new ProductProvided(productThree, 4.5, 1.5, p2);
        basketExpeditionC2Day1.addProductProvided(productProvidedOneC2Day1);
        basketExpeditionC2Day1.addProductProvided(productProvidedTwoC2Day1);
        expectedBasketExpeditions.add(basketExpeditionC2Day1);

        // Cabaz de expedição para o cliente E2
        BasketExpedition basketExpeditionE2Day1 = new BasketExpedition(companyCostumerTwo, day);
        ProductProvided productProvidedOneE2Day1 = new ProductProvided(productOne, 9, 7.5, p2);
        ProductProvided productProvidedTwoE2Day1 = new ProductProvided(productTwo, 6, 1, p2);
        ProductProvided productProvidedThreeE2Day1 = new ProductProvided(productThree, 9, 9, p1);

        basketExpeditionE2Day1.addProductProvided(productProvidedOneE2Day1);
        basketExpeditionE2Day1.addProductProvided(productProvidedTwoE2Day1);
        basketExpeditionE2Day1.addProductProvided(productProvidedThreeE2Day1);
        expectedBasketExpeditions.add(basketExpeditionE2Day1);

        assertEquals(expectedBasketExpeditions, actualBasketExpeditions);
    }

    /**
     * Método que testa se os valores dos mapas dos cabazes pedidos pelos clientes e dos cabazes fornecidos pelos
     * produtores, são atualizados corretamente. Neste teste é feita a expedição dos produtos para o dia 1 da lista
     * gerada pela utilização dos dados presentes no ficheiro SMALL_BASKET_FILE.
     * Teste complemento do teste generateExpeditionListWithoutRestrictionTest2().
     */
    @Test
    public void generateExpeditionListWithoutRestrictionTest3() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE);

        int day = 1;
        distributionNetwork.generateExpeditionListWithoutRestriction(day);

        Map<Integer, Map<AgriculturalProducer, Basket>> productsAvailable = distributionNetwork.getProductsAvailable();
        Map<Integer, Map<Costumer, Basket>> productsRequest = distributionNetwork.getProductsRequest();

        Map<AgriculturalProducer, Basket> actualProductsAvailable = productsAvailable.get(day);
        Map<Costumer, Basket> actualProductsRequest = productsRequest.get(day);

        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");
        AgriculturalProducer p2 = new AgriculturalProducer("CT6", 40.2111, -8.4291, "P2");
        AgriculturalProducer p3 = new AgriculturalProducer("CT10", 39.7444, -8.8072, "P3");

        Product productOne = new Product("Prod1");
        Product productTwo = new Product("Prod2");
        Product productThree = new Product("Prod3");

        ParticularCostumer particularCostumerTwo = new ParticularCostumer("CT2", 38.0333, -7.8833, "C2");
        CompanyCostumer companyCostumerTwo = new CompanyCostumer("CT11", 39.3167, -7.4167, "E2");

        // Testa o cabaz pedido por C2
        assertEquals(0, actualProductsRequest.get(particularCostumerTwo).getProductQuantity(productTwo), 0);
        assertEquals(3.0, actualProductsRequest.get(particularCostumerTwo).getProductQuantity(productThree), 0);

        // Testa o cabaz pedido por E2
        assertEquals(1.5, actualProductsRequest.get(companyCostumerTwo).getProductQuantity(productOne), 0);
        assertEquals(5.0, actualProductsRequest.get(companyCostumerTwo).getProductQuantity(productTwo), 0);
        assertEquals(0, actualProductsRequest.get(companyCostumerTwo).getProductQuantity(productThree), 0);

        // Testa o cabaz disponibilizado por P1
        assertEquals(0, actualProductsAvailable.get(p1).getProductQuantity(productOne), 0);
        assertEquals(7.5, actualProductsAvailable.get(p1).getProductQuantity(productTwo), 0);
        assertEquals(0, actualProductsAvailable.get(p1).getProductQuantity(productThree), 0);

        // Testa o cabaz disponibilizado por P2
        assertEquals(0, actualProductsAvailable.get(p2).getProductQuantity(productOne), 0);
        assertEquals(0, actualProductsAvailable.get(p2).getProductQuantity(productTwo), 0);
        assertEquals(0, actualProductsAvailable.get(p2).getProductQuantity(productThree), 0);

        // Testa o cabaz disponibilizado por P3
        assertEquals(2.5, actualProductsAvailable.get(p3).getProductQuantity(productOne), 0);
        assertEquals(2, actualProductsAvailable.get(p3).getProductQuantity(productTwo), 0);
        assertEquals(0, actualProductsAvailable.get(p3).getProductQuantity(productThree), 0);
    }

    /**
     * Método que testa se a lista de expedição sem restrições gerada para o dia 2 do ficheiro SMALL_BASKET_FILE, é
     * gerada corretamente. Aqui, alguns produtos serão procurados consequentemente nos cabazes disponibilizados no dia
     * 1.
     */
    @Test
    public void generateExpeditionListWithoutRestrictionTest4() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE);

        int day = 2;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        List<BasketExpedition> expectedBasketExpeditions = new ArrayList<>();
        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");
        AgriculturalProducer p2 = new AgriculturalProducer("CT6", 40.2111, -8.4291, "P2");
        AgriculturalProducer p3 = new AgriculturalProducer("CT10", 39.7444, -8.8072, "P3");

        Product productOne = new Product("Prod1");
        Product productTwo = new Product("Prod2");
        Product productThree = new Product("Prod3");

        ParticularCostumer particularCostumerOne = new ParticularCostumer("CT1", 40.6389, -8.6553, "C1");
        ParticularCostumer particularCostumerTwo = new ParticularCostumer("CT2", 38.0333, -7.8833, "C2");
        ParticularCostumer particularCostumerThree = new ParticularCostumer("CT3", 41.5333, -8.4167, "C3");
        CompanyCostumer companyCostumerOne = new CompanyCostumer("CT14", 38.5243, -8.8926, "E1");
        CompanyCostumer companyCostumerTwo = new CompanyCostumer("CT11", 39.3167, -7.4167, "E2");
        CompanyCostumer companyCostumerThree = new CompanyCostumer("CT5", 39.823, -7.4931, "E3");

        // Cabaz de expedição para o cliente C1
        BasketExpedition basketExpeditionC1Day2 = new BasketExpedition(particularCostumerOne, day);
        ProductProvided productProvidedOneC1Day2 = new ProductProvided(productOne, 4.5, 3, p1);
        ProductProvided productProvidedTwoC1Day2 = new ProductProvided(productTwo, 6, 6, p1); // De dia 1
        ProductProvided productProvidedThreeC1Day2 = new ProductProvided(productThree, 3.5, 3.5, p2);
        basketExpeditionC1Day2.addProductProvided(productProvidedOneC1Day2);
        basketExpeditionC1Day2.addProductProvided(productProvidedTwoC1Day2);
        basketExpeditionC1Day2.addProductProvided(productProvidedThreeC1Day2);

        // Cabaz de expedição para o cliente C2
        BasketExpedition basketExpeditionC2Day2 = new BasketExpedition(particularCostumerTwo, day);
        ProductProvided productProvidedOneC2Day2 = new ProductProvided(productOne, 9, 6, p2);
        ProductProvided productProvidedTwoC2Day2 = new ProductProvided(productTwo, 7, 6.5, p2); // De dia 1
        basketExpeditionC2Day2.addProductProvided(productProvidedOneC2Day2);
        basketExpeditionC2Day2.addProductProvided(productProvidedTwoC2Day2);

        // Cabaz de expedição para o cliente C3
        BasketExpedition basketExpeditionC3Day2 = new BasketExpedition(particularCostumerThree, day);
        ProductProvided productProvidedOneC3Day2 = new ProductProvided(productOne, 10, 7.5, p2); // De dia 1
        ProductProvided productProvidedTwoC3Day2 = new ProductProvided(productTwo, 20, 1.5, p1); // De dia 1
        basketExpeditionC3Day2.addProductProvided(productProvidedOneC3Day2);
        basketExpeditionC3Day2.addProductProvided(productProvidedTwoC3Day2);

        // Cabaz de expedição para o cliente E1
        BasketExpedition basketExpeditionE1Day1 = new BasketExpedition(companyCostumerOne, day);
        ProductProvided productProvidedThreeE1Day1 = new ProductProvided(productThree, 9.5, 6.5, p2);
        basketExpeditionE1Day1.addProductProvided(productProvidedThreeE1Day1);

        // Cabaz de expedição para o cliente E2
        BasketExpedition basketExpeditionE2Day1 = new BasketExpedition(companyCostumerTwo, day);
        ProductProvided productProvidedThreeE2Day1 = new ProductProvided(productThree, 7.5, 1.5, p2); // De dia 1
        basketExpeditionE2Day1.addProductProvided(productProvidedThreeE2Day1);

        // Cabaz de expedição para o cliente E3
        BasketExpedition basketExpeditionE3Day2 = new BasketExpedition(companyCostumerThree, day);
        ProductProvided productProvidedOneE3Day2 = new ProductProvided(productOne, 8.5, 2.5, p3); // De dia 1
        ProductProvided productProvidedTwoE3Day2 = new ProductProvided(productTwo, 5, 2, p3); // De dia 1
        basketExpeditionE3Day2.addProductProvided(productProvidedOneE3Day2);
        basketExpeditionE3Day2.addProductProvided(productProvidedTwoE3Day2);

        expectedBasketExpeditions.add(basketExpeditionC2Day2);
        expectedBasketExpeditions.add(basketExpeditionC1Day2);
        expectedBasketExpeditions.add(basketExpeditionC3Day2);
        expectedBasketExpeditions.add(basketExpeditionE3Day2);
        expectedBasketExpeditions.add(basketExpeditionE1Day1);
        expectedBasketExpeditions.add(basketExpeditionE2Day1);

        assertEquals(expectedBasketExpeditions, actualBasketExpeditions);
    }

    /**
     * Método que testa se os valores dos mapas dos cabazes pedidos pelos clientes e dos cabazes fornecidos pelos
     * produtores, são atualizados corretamente. Neste teste é feita a expedição dos produtos para o dia 2 da lista
     * gerada pela utilização dos dados presentes no ficheiro SMALL_BASKET_FILE. Aqui, consequentemente os produtos de
     * dia 1 que foram fornecidos, são também utilizados e atualizados para construir os cabazes.
     * Teste complemento do teste generateExpeditionListWithoutRestrictionTest4().
     */
    @Test
    public void generateExpeditionListWithoutRestrictionTest5() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE);

        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");
        AgriculturalProducer p2 = new AgriculturalProducer("CT6", 40.2111, -8.4291, "P2");
        AgriculturalProducer p3 = new AgriculturalProducer("CT10", 39.7444, -8.8072, "P3");

        Product productOne = new Product("Prod1");
        Product productTwo = new Product("Prod2");
        Product productThree = new Product("Prod3");

        ParticularCostumer particularCostumerOne = new ParticularCostumer("CT1", 40.6389, -8.6553, "C1");
        ParticularCostumer particularCostumerTwo = new ParticularCostumer("CT2", 38.0333, -7.8833, "C2");
        ParticularCostumer particularCostumerThree = new ParticularCostumer("CT3", 41.5333, -8.4167, "C3");
        CompanyCostumer companyCostumerOne = new CompanyCostumer("CT14", 38.5243, -8.8926, "E1");
        CompanyCostumer companyCostumerTwo = new CompanyCostumer("CT11", 39.3167, -7.4167, "E2");
        CompanyCostumer companyCostumerThree = new CompanyCostumer("CT5", 39.823, -7.4931, "E3");

        // Dia 2
        int day = 2;
        distributionNetwork.generateExpeditionListWithoutRestriction(day);

        Map<Integer, Map<AgriculturalProducer, Basket>> productsAvailable = distributionNetwork.getProductsAvailable();
        Map<Integer, Map<Costumer, Basket>> productsRequest = distributionNetwork.getProductsRequest();

        Map<AgriculturalProducer, Basket> actualProductsAvailable = productsAvailable.get(day);
        Map<Costumer, Basket> actualProductsRequest = productsRequest.get(day);

        // Testa o cabaz pedido por C1
        assertEquals(1.5, actualProductsRequest.get(particularCostumerOne).getProductQuantity(productOne), 0);
        assertEquals(0, actualProductsRequest.get(particularCostumerOne).getProductQuantity(productTwo), 0);
        assertEquals(0, actualProductsRequest.get(particularCostumerOne).getProductQuantity(productThree), 0);

        // Testa o cabaz pedido por C2
        assertEquals(3, actualProductsRequest.get(particularCostumerTwo).getProductQuantity(productOne), 0);
        assertEquals(0.5, actualProductsRequest.get(particularCostumerTwo).getProductQuantity(productTwo), 0);

        // Testa o cabaz pedido por C3
        assertEquals(2.5, actualProductsRequest.get(particularCostumerThree).getProductQuantity(productOne), 0);
        assertEquals(18.5, actualProductsRequest.get(particularCostumerThree).getProductQuantity(productTwo), 0);

        // Testa o cabaz pedido por E1
        assertEquals(3, actualProductsRequest.get(companyCostumerOne).getProductQuantity(productThree), 0);

        // Testa o cabaz pedido por E2
        assertEquals(6, actualProductsRequest.get(companyCostumerTwo).getProductQuantity(productThree), 0);

        // Testa o cabaz pedido por E3
        assertEquals(6, actualProductsRequest.get(companyCostumerThree).getProductQuantity(productOne), 0);
        assertEquals(3, actualProductsRequest.get(companyCostumerThree).getProductQuantity(productTwo), 0);

        // Testa o cabaz disponibilizado por P1
        assertEquals(0, actualProductsAvailable.get(p1).getProductQuantity(productOne), 0);
        assertEquals(0, actualProductsAvailable.get(p1).getProductQuantity(productTwo), 0);
        assertEquals(0, actualProductsAvailable.get(p1).getProductQuantity(productThree), 0);

        // Testa o cabaz disponibilizado por P2
        assertEquals(0, actualProductsAvailable.get(p2).getProductQuantity(productOne), 0);
        assertEquals(0, actualProductsAvailable.get(p2).getProductQuantity(productTwo), 0);
        assertEquals(0, actualProductsAvailable.get(p2).getProductQuantity(productThree), 0);

        // Dia 1 dos produtos disponíveis
        day = 1;
        productsAvailable = distributionNetwork.getProductsAvailable();
        actualProductsAvailable = productsAvailable.get(day);

        // Testa o cabaz disponibilizado por P1
        assertEquals(0, actualProductsAvailable.get(p1).getProductQuantity(productOne), 0);
        assertEquals(0, actualProductsAvailable.get(p1).getProductQuantity(productTwo), 0);
        assertEquals(9, actualProductsAvailable.get(p1).getProductQuantity(productThree), 0);

        // Testa o cabaz disponibilizado por P2
        assertEquals(0, actualProductsAvailable.get(p2).getProductQuantity(productOne), 0);
        assertEquals(0, actualProductsAvailable.get(p2).getProductQuantity(productTwo), 0);
        assertEquals(0, actualProductsAvailable.get(p2).getProductQuantity(productThree), 0);

        // Testa o cabaz disponibilizado por P3
        assertEquals(0, actualProductsAvailable.get(p3).getProductQuantity(productOne), 0);
        assertEquals(0, actualProductsAvailable.get(p3).getProductQuantity(productTwo), 0);
        assertEquals(0, actualProductsAvailable.get(p3).getProductQuantity(productThree), 0);

    }

    /**
     * Método que testa se a lista de expedição sem restrições gerada para o dia 2 do ficheiro SMALL_BASKET_FILE_TWO, é
     * gerada corretamente. Aqui, será testado se um produto fornecido quando não há produtores que tenham o produto
     * disponibilizado, vem com o valor correto.
     */
    @Test
    public void generateExpeditionListWithoutRestrictionTest6() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE_TWO);

        int day = 2;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        List<BasketExpedition> expectedBasketExpeditions = new ArrayList<>();
        AgriculturalProducer p2 = new AgriculturalProducer("CT6", 40.2111, -8.4291, "P2");

        Product productOne = new Product("Prod1");
        Product productTwo = new Product("Prod2");
        Product productThree = new Product("Prod3");

        ParticularCostumer particularCostumerOne = new ParticularCostumer("CT1", 40.6389, -8.6553, "C1");

        // Cabaz de expedição para o cliente C1
        BasketExpedition basketExpeditionC1Day2 = new BasketExpedition(particularCostumerOne, day);
        ProductProvided productProvidedOneC1Day2 = new ProductProvided(productOne, 4.5, 4.5, p2);
        ProductProvided productProvidedTwoC1Day2 = new ProductProvided(productTwo, 6, 0, null);
        ProductProvided productProvidedThreeC1Day2 = new ProductProvided(productThree, 3.5, 3.5, p2);
        basketExpeditionC1Day2.addProductProvided(productProvidedOneC1Day2);
        basketExpeditionC1Day2.addProductProvided(productProvidedTwoC1Day2);
        basketExpeditionC1Day2.addProductProvided(productProvidedThreeC1Day2);

        expectedBasketExpeditions.add(basketExpeditionC1Day2);

        assertEquals(expectedBasketExpeditions, actualBasketExpeditions);
    }

    /**
     * Método que testa se os valores dos mapas dos cabazes pedidos pelos clientes e dos cabazes fornecidos pelos
     * produtores, são atualizados corretamente. Neste teste é feita a expedição dos produtos para o dia 2 da lista
     * gerada pela utilização dos dados presentes no ficheiro SMALL_BASKET_FILE_TWO. Aqui, consequentemente os produtos
     * de dia 1 que foram fornecidos, são também utilizados e atualizados para construir os cabazes.
     * Teste complemento do teste generateExpeditionListWithoutRestrictionTest6().
     */
    @Test
    public void generateExpeditionListWithoutRestrictionTest7() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE_TWO);

        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");
        AgriculturalProducer p2 = new AgriculturalProducer("CT6", 40.2111, -8.4291, "P2");
        Product productOne = new Product("Prod1");
        Product productTwo = new Product("Prod2");
        Product productThree = new Product("Prod3");
        ParticularCostumer particularCostumerOne = new ParticularCostumer("CT1", 40.6389, -8.6553, "C1");

        // Dia 2
        int day = 2;
        distributionNetwork.generateExpeditionListWithoutRestriction(day);

        Map<Integer, Map<AgriculturalProducer, Basket>> productsAvailable = distributionNetwork.getProductsAvailable();
        Map<Integer, Map<Costumer, Basket>> productsRequest = distributionNetwork.getProductsRequest();

        Map<AgriculturalProducer, Basket> actualProductsAvailable = productsAvailable.get(day);
        Map<Costumer, Basket> actualProductsRequest = productsRequest.get(day);

        // Testa o cabaz pedido por C1
        assertEquals(0, actualProductsRequest.get(particularCostumerOne).getProductQuantity(productOne), 0);
        assertEquals(6, actualProductsRequest.get(particularCostumerOne).getProductQuantity(productTwo), 0);
        assertEquals(0, actualProductsRequest.get(particularCostumerOne).getProductQuantity(productThree), 0);

        // Testa o cabaz disponibilizado por P1
        assertEquals(3, actualProductsAvailable.get(p1).getProductQuantity(productOne), 0);
        assertEquals(0, actualProductsAvailable.get(p1).getProductQuantity(productTwo), 0);
        assertEquals(0, actualProductsAvailable.get(p1).getProductQuantity(productThree), 0);

        // Testa o cabaz disponibilizado por P2
        assertEquals(1.5, actualProductsAvailable.get(p2).getProductQuantity(productOne), 0);
        assertEquals(0, actualProductsAvailable.get(p2).getProductQuantity(productTwo), 0);
        assertEquals(6.5, actualProductsAvailable.get(p2).getProductQuantity(productThree), 0);

        // Dia 1 dos produtos disponíveis
        day = 1;
        productsAvailable = distributionNetwork.getProductsAvailable();
        actualProductsAvailable = productsAvailable.get(day);

        // Testa o cabaz disponibilizado por P1
        assertEquals(0, actualProductsAvailable.get(p1).getProductQuantity(productOne), 0);
        assertEquals(0, actualProductsAvailable.get(p1).getProductQuantity(productTwo), 0);
        assertEquals(9, actualProductsAvailable.get(p1).getProductQuantity(productThree), 0);

        // Testa o cabaz disponibilizado por P2
        assertEquals(7.5, actualProductsAvailable.get(p2).getProductQuantity(productOne), 0);
        assertEquals(0, actualProductsAvailable.get(p2).getProductQuantity(productTwo), 0);
        assertEquals(1.5, actualProductsAvailable.get(p2).getProductQuantity(productThree), 0);

    }

    /**
     * Método que testa se o percurso de entrega que minimiza a distância total percorrida para a lista de expedição
     * gerada para o dia 2 do ficheiro FILE_PATH_CABAZES_SMALL, é gerada corretamente.
     */
    @Test
    public void generateDeliveryRouteTest1() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(FILE_PATH_CABAZES_SMALL);

        int day = 2;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        DeliveryRoute deliveryRouteResult = distributionNetwork.generateDeliveryRoute(actualBasketExpeditions);

        LinkedList<NetworkPoint> waypoints = new LinkedList<>();
        waypoints.add(new NetworkPoint("CT17"));
        waypoints.add(new NetworkPoint("CT6"));
        waypoints.add(new NetworkPoint("CT10"));
        waypoints.add(new NetworkPoint("CT4"));
        waypoints.add(new NetworkPoint("CT5"));
        waypoints.add(new NetworkPoint("CT9"));
        waypoints.add(new NetworkPoint("CT11"));
        waypoints.add(new NetworkPoint("CT14"));

        int totalDistance = 1179654;

        DeliveryRoute deliveryRouteExpected = new DeliveryRoute(waypoints, totalDistance);

        assertEquals(deliveryRouteExpected, deliveryRouteResult);
    }

    /**
     * Método que testa se o percurso de entrega que minimiza a distância total percorrida para a lista de expedição
     * gerada para o dia 5 do ficheiro FILE_PATH_CABAZES_SMALL, é gerada corretamente.
     */
    @Test
    public void generateDeliveryRouteTest2() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(FILE_PATH_CABAZES_SMALL);

        int day = 5;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        DeliveryRoute deliveryRouteResult = distributionNetwork.generateDeliveryRoute(actualBasketExpeditions);

        LinkedList<NetworkPoint> waypoints = new LinkedList<>();
        waypoints.add(new NetworkPoint("CT17"));
        waypoints.add(new NetworkPoint("CT6"));
        waypoints.add(new NetworkPoint("CT10"));
        waypoints.add(new NetworkPoint("CT4"));
        waypoints.add(new NetworkPoint("CT5"));
        waypoints.add(new NetworkPoint("CT9"));
        waypoints.add(new NetworkPoint("CT11"));
        waypoints.add(new NetworkPoint("CT14"));

        int totalDistance = 1179654;

        DeliveryRoute deliveryRouteExpected = new DeliveryRoute(waypoints, totalDistance);

        assertEquals(deliveryRouteExpected, deliveryRouteResult);
    }

    /**
     * Método que testa se o percurso de entrega que minimiza a distância total percorrida para a lista de expedição
     * gerada para o dia 2 do ficheiro SMALL_BASKET_FILE_TWO, é gerada corretamente.
     */
    @Test
    public void generateDeliveryRouteTest3() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE_TWO);

        int day = 2;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        DeliveryRoute deliveryRouteResult = distributionNetwork.generateDeliveryRoute(actualBasketExpeditions);

        LinkedList<NetworkPoint> waypoints = new LinkedList<>();
        waypoints.add(new NetworkPoint("CT6"));
        waypoints.add(new NetworkPoint("CT9"));

        int totalDistance = 136707;

        DeliveryRoute deliveryRouteExpected = new DeliveryRoute(waypoints, totalDistance);

        assertEquals(deliveryRouteExpected, deliveryRouteResult);
    }

    /**
     * Método que testa se o percurso de entrega que minimiza a distância total percorrida para a lista de expedição
     * gerada para o dia 1 do ficheiro SMALL_BASKET_FILE, é gerada corretamente.
     */
    @Test
    public void generateDeliveryRouteTest4() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE);

        int day = 1;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        DeliveryRoute deliveryRouteResult = distributionNetwork.generateDeliveryRoute(actualBasketExpeditions);

        LinkedList<NetworkPoint> waypoints = new LinkedList<>();
        waypoints.add(new NetworkPoint("CT17"));
        waypoints.add(new NetworkPoint("CT6"));
        waypoints.add(new NetworkPoint("CT5"));
        waypoints.add(new NetworkPoint("CT14"));

        int totalDistance = 448443;

        DeliveryRoute deliveryRouteExpected = new DeliveryRoute(waypoints, totalDistance);

        assertEquals(deliveryRouteExpected, deliveryRouteResult);
    }

    /**
     * Método que testa se o percurso de entrega que minimiza a distância total percorrida para a lista de expedição
     * gerada para o dia 1 do ficheiro FILE_PATH_CABAZES_BIG, é gerada corretamente.
     */
    @Test
    public void generateDeliveryRouteTest5() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_BIG, FILE_PATH_DISTANCES_BIG);
        distributionNetwork.fileToBasket(FILE_PATH_CABAZES_BIG);

        int day = 1;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        DeliveryRoute deliveryRouteResult = distributionNetwork.generateDeliveryRoute(actualBasketExpeditions);

        LinkedList<NetworkPoint> waypoints = new LinkedList<>();
        waypoints.add(new NetworkPoint("CT7"));
        waypoints.add(new NetworkPoint("CT86"));
        waypoints.add(new NetworkPoint("CT90"));
        waypoints.add(new NetworkPoint("CT95"));
        waypoints.add(new NetworkPoint("CT1"));
        waypoints.add(new NetworkPoint("CT120"));
        waypoints.add(new NetworkPoint("CT126"));
        waypoints.add(new NetworkPoint("CT139"));
        waypoints.add(new NetworkPoint("CT14"));
        waypoints.add(new NetworkPoint("CT140"));
        waypoints.add(new NetworkPoint("CT153"));
        waypoints.add(new NetworkPoint("CT160"));
        waypoints.add(new NetworkPoint("CT164"));
        waypoints.add(new NetworkPoint("CT169"));
        waypoints.add(new NetworkPoint("CT178"));
        waypoints.add(new NetworkPoint("CT183"));
        waypoints.add(new NetworkPoint("CT185"));
        waypoints.add(new NetworkPoint("CT190"));
        waypoints.add(new NetworkPoint("CT195"));
        waypoints.add(new NetworkPoint("CT204"));
        waypoints.add(new NetworkPoint("CT207"));
        waypoints.add(new NetworkPoint("CT211"));
        waypoints.add(new NetworkPoint("CT217"));
        waypoints.add(new NetworkPoint("CT228"));
        waypoints.add(new NetworkPoint("CT229"));
        waypoints.add(new NetworkPoint("CT24"));
        waypoints.add(new NetworkPoint("CT253"));
        waypoints.add(new NetworkPoint("CT266"));
        waypoints.add(new NetworkPoint("CT271"));
        waypoints.add(new NetworkPoint("CT283"));
        waypoints.add(new NetworkPoint("CT285"));
        waypoints.add(new NetworkPoint("CT287"));
        waypoints.add(new NetworkPoint("CT299"));
        waypoints.add(new NetworkPoint("CT301"));
        waypoints.add(new NetworkPoint("CT311"));
        waypoints.add(new NetworkPoint("CT318"));
        waypoints.add(new NetworkPoint("CT321"));
        waypoints.add(new NetworkPoint("CT35"));
        waypoints.add(new NetworkPoint("CT48"));
        waypoints.add(new NetworkPoint("CT50"));
        waypoints.add(new NetworkPoint("CT51"));
        waypoints.add(new NetworkPoint("CT53"));
        waypoints.add(new NetworkPoint("CT6"));
        waypoints.add(new NetworkPoint("CT64"));
        waypoints.add(new NetworkPoint("CT66"));
        waypoints.add(new NetworkPoint("CT307"));
        waypoints.add(new NetworkPoint("CT317"));
        waypoints.add(new NetworkPoint("CT33"));
        waypoints.add(new NetworkPoint("CT34"));
        waypoints.add(new NetworkPoint("CT36"));
        waypoints.add(new NetworkPoint("CT5"));
        waypoints.add(new NetworkPoint("CT61"));
        waypoints.add(new NetworkPoint("CT62"));
        waypoints.add(new NetworkPoint("CT70"));
        waypoints.add(new NetworkPoint("CT77"));
        waypoints.add(new NetworkPoint("CT81"));
        waypoints.add(new NetworkPoint("CT83"));
        waypoints.add(new NetworkPoint("CT85"));
        waypoints.add(new NetworkPoint("CT88"));
        waypoints.add(new NetworkPoint("CT89"));
        waypoints.add(new NetworkPoint("CT98"));
        waypoints.add(new NetworkPoint("CT101"));
        waypoints.add(new NetworkPoint("CT110"));
        waypoints.add(new NetworkPoint("CT112"));
        waypoints.add(new NetworkPoint("CT113"));
        waypoints.add(new NetworkPoint("CT114"));
        waypoints.add(new NetworkPoint("CT117"));
        waypoints.add(new NetworkPoint("CT12"));
        waypoints.add(new NetworkPoint("CT124"));
        waypoints.add(new NetworkPoint("CT128"));
        waypoints.add(new NetworkPoint("CT131"));
        waypoints.add(new NetworkPoint("CT132"));
        waypoints.add(new NetworkPoint("CT133"));
        waypoints.add(new NetworkPoint("CT135"));
        waypoints.add(new NetworkPoint("CT142"));
        waypoints.add(new NetworkPoint("CT145"));
        waypoints.add(new NetworkPoint("CT146"));
        waypoints.add(new NetworkPoint("CT149"));
        waypoints.add(new NetworkPoint("CT151"));
        waypoints.add(new NetworkPoint("CT152"));
        waypoints.add(new NetworkPoint("CT159"));
        waypoints.add(new NetworkPoint("CT16"));
        waypoints.add(new NetworkPoint("CT161"));
        waypoints.add(new NetworkPoint("CT162"));
        waypoints.add(new NetworkPoint("CT170"));
        waypoints.add(new NetworkPoint("CT171"));
        waypoints.add(new NetworkPoint("CT177"));
        waypoints.add(new NetworkPoint("CT179"));
        waypoints.add(new NetworkPoint("CT18"));
        waypoints.add(new NetworkPoint("CT186"));
        waypoints.add(new NetworkPoint("CT187"));
        waypoints.add(new NetworkPoint("CT19"));
        waypoints.add(new NetworkPoint("CT191"));
        waypoints.add(new NetworkPoint("CT2"));
        waypoints.add(new NetworkPoint("CT200"));
        waypoints.add(new NetworkPoint("CT201"));
        waypoints.add(new NetworkPoint("CT209"));
        waypoints.add(new NetworkPoint("CT223"));
        waypoints.add(new NetworkPoint("CT231"));
        waypoints.add(new NetworkPoint("CT236"));
        waypoints.add(new NetworkPoint("CT237"));
        waypoints.add(new NetworkPoint("CT238"));
        waypoints.add(new NetworkPoint("CT240"));
        waypoints.add(new NetworkPoint("CT242"));
        waypoints.add(new NetworkPoint("CT244"));
        waypoints.add(new NetworkPoint("CT247"));
        waypoints.add(new NetworkPoint("CT251"));
        waypoints.add(new NetworkPoint("CT252"));
        waypoints.add(new NetworkPoint("CT254"));
        waypoints.add(new NetworkPoint("CT256"));
        waypoints.add(new NetworkPoint("CT26"));
        waypoints.add(new NetworkPoint("CT262"));
        waypoints.add(new NetworkPoint("CT263"));
        waypoints.add(new NetworkPoint("CT265"));
        waypoints.add(new NetworkPoint("CT27"));
        waypoints.add(new NetworkPoint("CT274"));
        waypoints.add(new NetworkPoint("CT279"));
        waypoints.add(new NetworkPoint("CT280"));
        waypoints.add(new NetworkPoint("CT282"));
        waypoints.add(new NetworkPoint("CT292"));
        waypoints.add(new NetworkPoint("CT295"));
        waypoints.add(new NetworkPoint("CT297"));
        waypoints.add(new NetworkPoint("CT3"));
        waypoints.add(new NetworkPoint("CT30"));
        waypoints.add(new NetworkPoint("CT302"));

        int totalDistance = 29526706;

        DeliveryRoute deliveryRouteExpected = new DeliveryRoute(waypoints, totalDistance);

        assertEquals(deliveryRouteExpected, deliveryRouteResult);
    }

    /**
     * Método que testa se o cálculo das estatísticas por cabaz para a lista de expedição gerada para o dia 1 do ficheiro
     * SMALL_BASKET_FILE, é gerada corretamente.
     */
    @Test
    public void calculateStatisticsByBasketTest1() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE);

        int day = 1;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        distributionNetwork.calculateStatisticsByBasket(actualBasketExpeditions);

        BasketStatistics basketStatisticsExpected0 = new BasketStatistics(1, 1, 0, 1);
        BasketStatistics basketStatisticsExpected1 = new BasketStatistics(1, 2, 0, 2);

        assertEquals(basketStatisticsExpected0, actualBasketExpeditions.get(0).getStatisticsBasket());
        assertEquals(basketStatisticsExpected1, actualBasketExpeditions.get(1).getStatisticsBasket());
    }

    /**
     * Método que testa se o cálculo das estatísticas por cabaz para a lista de expedição gerada para o dia 2 do ficheiro
     * SMALL_BASKET_FILE, é gerada corretamente.
     */
    @Test
    public void calculateStatisticsByBasketTest2() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE);

        int day = 2;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        distributionNetwork.calculateStatisticsByBasket(actualBasketExpeditions);

        BasketStatistics basketStatisticsExpected0 = new BasketStatistics(0, 2, 0, 1);
        BasketStatistics basketStatisticsExpected1 = new BasketStatistics(2, 1, 0, 2);
        BasketStatistics basketStatisticsExpected2 = new BasketStatistics(0, 2, 0, 2);
        BasketStatistics basketStatisticsExpected3 = new BasketStatistics(0, 2, 0, 1);
        BasketStatistics basketStatisticsExpected4 = new BasketStatistics(0, 1, 0, 1);
        BasketStatistics basketStatisticsExpected5 = new BasketStatistics(0, 1, 0, 1);

        assertEquals(basketStatisticsExpected0, actualBasketExpeditions.get(0).getStatisticsBasket());
        assertEquals(basketStatisticsExpected1, actualBasketExpeditions.get(1).getStatisticsBasket());
        assertEquals(basketStatisticsExpected2, actualBasketExpeditions.get(2).getStatisticsBasket());
        assertEquals(basketStatisticsExpected3, actualBasketExpeditions.get(3).getStatisticsBasket());
        assertEquals(basketStatisticsExpected4, actualBasketExpeditions.get(4).getStatisticsBasket());
        assertEquals(basketStatisticsExpected5, actualBasketExpeditions.get(5).getStatisticsBasket());
    }

    /**
     * Método que testa se o cálculo das estatísticas por cabaz para a lista de expedição gerada para o dia 2 do ficheiro
     * SMALL_BASKET_FILE_TWO, é gerada corretamente.
     */
    @Test
    public void calculateStatisticsByBasketTest3() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE_TWO);

        int day = 2;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        distributionNetwork.calculateStatisticsByBasket(actualBasketExpeditions);

        BasketStatistics basketStatisticsExpected = new BasketStatistics(2, 0, 1, 1);

        assertEquals(basketStatisticsExpected, actualBasketExpeditions.get(0).getStatisticsBasket());
    }

    /**
     * Método que testa se o cálculo das estatísticas por cabaz para a lista de expedição gerada para o dia 4 do ficheiro
     * FILE_PATH_CABAZES_SMALL, é gerada corretamente.
     */
    @Test
    public void calculateStatisticsByBasketTest4() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(FILE_PATH_CABAZES_SMALL);

        int day = 4;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        distributionNetwork.calculateStatisticsByBasket(actualBasketExpeditions);

        BasketStatistics basketStatisticsExpected0 = new BasketStatistics(1, 2, 0, 1);
        BasketStatistics basketStatisticsExpected1 = new BasketStatistics(0, 5, 0, 2);
        BasketStatistics basketStatisticsExpected2 = new BasketStatistics(3, 1, 0, 2);
        BasketStatistics basketStatisticsExpected3 = new BasketStatistics(1, 3, 0, 2);
        BasketStatistics basketStatisticsExpected4 = new BasketStatistics(3, 0, 0, 2);
        BasketStatistics basketStatisticsExpected5 = new BasketStatistics(1, 4, 0, 3);
        BasketStatistics basketStatisticsExpected6 = new BasketStatistics(2, 2, 0, 2);
        BasketStatistics basketStatisticsExpected7 = new BasketStatistics(1, 3, 0, 2);

        assertEquals(basketStatisticsExpected0, actualBasketExpeditions.get(0).getStatisticsBasket());
        assertEquals(basketStatisticsExpected1, actualBasketExpeditions.get(1).getStatisticsBasket());
        assertEquals(basketStatisticsExpected2, actualBasketExpeditions.get(2).getStatisticsBasket());
        assertEquals(basketStatisticsExpected3, actualBasketExpeditions.get(3).getStatisticsBasket());
        assertEquals(basketStatisticsExpected4, actualBasketExpeditions.get(4).getStatisticsBasket());
        assertEquals(basketStatisticsExpected5, actualBasketExpeditions.get(5).getStatisticsBasket());
        assertEquals(basketStatisticsExpected6, actualBasketExpeditions.get(6).getStatisticsBasket());
        assertEquals(basketStatisticsExpected7, actualBasketExpeditions.get(7).getStatisticsBasket());
    }

    /**
     * Método que testa se o cálculo das estatísticas por produtor para a lista de expedição gerada para o dia 2 do ficheiro
     * FILE_PATH_CABAZES_SMALL, é gerada corretamente.
     */
    @Test
    public void calculateAgriculturalProducerStatisticsTest1() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(FILE_PATH_CABAZES_SMALL);

        int day = 2;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        distributionNetwork.calculateStatisticsByAgriculturalProducer(actualBasketExpeditions);

        AgriculturalProducerStatistics result1 = ((AgriculturalProducer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT6"), distributionNetwork.getDistributionNetwork().vertices())).getAgriculturalProducerStatistics();
        AgriculturalProducerStatistics result2 = ((AgriculturalProducer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT17"), distributionNetwork.getDistributionNetwork().vertices())).getAgriculturalProducerStatistics();
        AgriculturalProducerStatistics result3 = ((AgriculturalProducer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT10"), distributionNetwork.getDistributionNetwork().vertices())).getAgriculturalProducerStatistics();

        AgriculturalProducerStatistics expected1 = new AgriculturalProducerStatistics(7, 12, 8, 6, 5);
        AgriculturalProducerStatistics expected2 = new AgriculturalProducerStatistics(8, 8, 8, 12, 4);
        AgriculturalProducerStatistics expected3 = new AgriculturalProducerStatistics(7, 7, 6, 17, 3);


        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);

    }


    /**
     * Método que testa se o cálculo das estatísticas por produtor para a lista de expedição gerada para o dia 1 do ficheiro
     * FILE_PATH_CABAZES_SMALL, é gerada corretamente.
     */
    @Test
    public void calculateAgriculturalProducerStatisticsTest2() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(FILE_PATH_CABAZES_SMALL);

        int day = 1;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        distributionNetwork.calculateStatisticsByAgriculturalProducer(actualBasketExpeditions);

        AgriculturalProducerStatistics result1 = ((AgriculturalProducer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT6"), distributionNetwork.getDistributionNetwork().vertices())).getAgriculturalProducerStatistics();
        AgriculturalProducerStatistics result2 = ((AgriculturalProducer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT17"), distributionNetwork.getDistributionNetwork().vertices())).getAgriculturalProducerStatistics();
        AgriculturalProducerStatistics result3 = ((AgriculturalProducer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT10"), distributionNetwork.getDistributionNetwork().vertices())).getAgriculturalProducerStatistics();

        AgriculturalProducerStatistics expected1 = new AgriculturalProducerStatistics(4, 5, 4, 7, 3);
        AgriculturalProducerStatistics expected2 = new AgriculturalProducerStatistics(5, 4, 6, 12, 4);
        AgriculturalProducerStatistics expected3 = new AgriculturalProducerStatistics(4, 7, 8, 19, 5);


        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);

    }


    /**
     * Método que testa se o cálculo das estatísticas por produtor para a lista de expedição gerada para o dia 2 do ficheiro
     * SMALL_BASKET_FILE_TWO, é gerada corretamente.
     */
    @Test
    public void calculateAgriculturalProducerStatisticsTest3() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE_TWO);

        int day = 2;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        distributionNetwork.calculateStatisticsByAgriculturalProducer(actualBasketExpeditions);

        AgriculturalProducerStatistics result1 = ((AgriculturalProducer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT6"), distributionNetwork.getDistributionNetwork().vertices())).getAgriculturalProducerStatistics();

        AgriculturalProducerStatistics expected1 = new AgriculturalProducerStatistics(2, 0, 1, 0, 1);

        assertEquals(expected1, result1);

    }

    /**
     * Método que testa se o cálculo das estatísticas por cliente para a lista de expedição gerada para o dia 1 do ficheiro SMALL_BASKET_FILE, é gerada corretamente.
     */
    @Test
    public void calculateStatisticsByCostumerTest1() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE);

        int day = 1;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        distributionNetwork.calculateStatisticsByCostumer(actualBasketExpeditions);

        CostumerStatistics result1 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT2"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result2 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT11"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();

        CostumerStatistics expected1 = new CostumerStatistics(0, 1, 1);
        CostumerStatistics expected2 = new CostumerStatistics(0, 1, 2);

        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
    }

    /**
     * Método que testa se o cálculo das estatísticas por cliente para a lista de expedição gerada para o dia 2 do ficheiro SMALL_BASKET_FILE, é gerada corretamente.
     */
    @Test
    public void calculateStatisticsByCostumerTest2() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE);

        int day = 2;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        distributionNetwork.calculateStatisticsByCostumer(actualBasketExpeditions);

        CostumerStatistics result1 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT2"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result2 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT1"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result3 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT3"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result4 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT5"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result5 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT14"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result6 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT11"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();

        CostumerStatistics expected1 = new CostumerStatistics(0, 1, 1);
        CostumerStatistics expected2 = new CostumerStatistics(0, 1, 2);
        CostumerStatistics expected3 = new CostumerStatistics(0, 1, 2);
        CostumerStatistics expected4 = new CostumerStatistics(0, 1, 1);
        CostumerStatistics expected5 = new CostumerStatistics(0, 1, 1);
        CostumerStatistics expected6 = new CostumerStatistics(0, 1, 1);

        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);
        assertEquals(expected4, result4);
        assertEquals(expected5, result5);
        assertEquals(expected6, result6);
    }

    /**
     * Método que testa se o cálculo das estatísticas por cliente para a lista de expedição gerada para o dia 1 do ficheiro SMALL_BASKET_FILE_TWO, é gerada corretamente.
     */
    @Test
    public void calculateStatisticsByCostumerTest3() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE_TWO);

        int day = 1;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        distributionNetwork.calculateStatisticsByCostumer(actualBasketExpeditions);

        CostumerStatistics result1 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT1"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();

        CostumerStatistics expected1 = new CostumerStatistics(0, 0, 0);

        assertEquals(expected1, result1);
    }

    /**
     * Método que testa se o cálculo das estatísticas por cliente para a lista de expedição gerada para o dia 2 do ficheiro SMALL_BASKET_FILE_THREE, é gerada corretamente.
     */
    @Test
    public void calculateStatisticsByCostumerTest4() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE_THREE);

        int day = 2;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        distributionNetwork.calculateStatisticsByCostumer(actualBasketExpeditions);

        CostumerStatistics result1 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT1"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();

        CostumerStatistics expected1 = new CostumerStatistics(1, 0, 1);

        assertEquals(expected1, result1);
    }

    /**
     * Método que testa se o cálculo das estatísticas por cliente para a lista de expedição gerada para o dia 1 do ficheiro SMALL_BASKET_FILE_FOUR, é gerada corretamente.
     */
    @Test
    public void calculateStatisticsByCostumerTest5() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE_FOUR);

        int day = 1;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        distributionNetwork.calculateStatisticsByCostumer(actualBasketExpeditions);

        CostumerStatistics result1 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT2"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result2 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT1"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result3 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT4"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result4 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT3"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result5 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT5"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result6 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT8"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result7 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT9"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result8 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT13"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result9 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT12"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result10 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT11"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();

        CostumerStatistics expected1 = new CostumerStatistics(0, 1, 2);
        CostumerStatistics expected2 = new CostumerStatistics(1, 0, 2);
        CostumerStatistics expected3 = new CostumerStatistics(0, 1, 2);
        CostumerStatistics expected4 = new CostumerStatistics(0, 1, 2);
        CostumerStatistics expected5 = new CostumerStatistics(1, 0, 1);
        CostumerStatistics expected6 = new CostumerStatistics(1, 0, 1);
        CostumerStatistics expected7 = new CostumerStatistics(1, 0, 1);
        CostumerStatistics expected8 = new CostumerStatistics(1, 0, 1);
        CostumerStatistics expected9 = new CostumerStatistics(1, 0, 1);
        CostumerStatistics expected10 = new CostumerStatistics(1, 0, 1);

        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);
        assertEquals(expected4, result4);
        assertEquals(expected5, result5);
        assertEquals(expected6, result6);
        assertEquals(expected7, result7);
        assertEquals(expected8, result8);
        assertEquals(expected9, result9);
        assertEquals(expected10, result10);
    }

    /**
     * Método que testa se o cálculo das estatísticas por cliente para a lista de expedição gerada para o dia 2 do ficheiro SMALL_BASKET_FILE_FOUR, é gerada corretamente.
     */
    @Test
    public void calculateStatisticsByCostumerTest6() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE_FOUR);

        int day = 2;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        distributionNetwork.calculateStatisticsByCostumer(actualBasketExpeditions);

        CostumerStatistics result1 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT2"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result2 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT1"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result3 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT4"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result4 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT3"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result5 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT7"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result6 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT15"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result7 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT14"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result8 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT13"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result9 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT11"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();

        CostumerStatistics expected1 = new CostumerStatistics(0, 1, 2);
        CostumerStatistics expected2 = new CostumerStatistics(0, 1, 2);
        CostumerStatistics expected3 = new CostumerStatistics(1, 0, 2);
        CostumerStatistics expected4 = new CostumerStatistics(0, 1, 2);
        CostumerStatistics expected5 = new CostumerStatistics(0, 1, 2);
        CostumerStatistics expected6 = new CostumerStatistics(1, 0, 1);
        CostumerStatistics expected7 = new CostumerStatistics(1, 0, 1);
        CostumerStatistics expected8 = new CostumerStatistics(1, 0, 1);
        CostumerStatistics expected9 = new CostumerStatistics(1, 0, 1);

        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);
        assertEquals(expected4, result4);
        assertEquals(expected5, result5);
        assertEquals(expected6, result6);
        assertEquals(expected7, result7);
        assertEquals(expected8, result8);
        assertEquals(expected9, result9);
    }

    /**
     * Método que testa se o cálculo das estatísticas por cliente para a lista de expedição gerada para o dia 3 do ficheiro SMALL_BASKET_FILE_FOUR, é gerada corretamente.
     */
    @Test
    public void calculateStatisticsByCostumerTest7() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE_FOUR);

        int day = 3;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        distributionNetwork.calculateStatisticsByCostumer(actualBasketExpeditions);

        CostumerStatistics result1 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT2"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result2 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT1"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result3 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT4"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result4 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT8"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result5 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT7"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result6 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT16"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result7 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT9"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result8 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT15"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result9 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT14"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result10 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT13"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result11 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT12"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result12 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT11"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();

        CostumerStatistics expected1 = new CostumerStatistics(0, 1, 1);
        CostumerStatistics expected2 = new CostumerStatistics(0, 1, 2);
        CostumerStatistics expected3 = new CostumerStatistics(0, 1, 2);
        CostumerStatistics expected4 = new CostumerStatistics(0, 1, 2);
        CostumerStatistics expected5 = new CostumerStatistics(1, 0, 1);
        CostumerStatistics expected6 = new CostumerStatistics(1, 0, 1);
        CostumerStatistics expected7 = new CostumerStatistics(1, 0, 1);
        CostumerStatistics expected8 = new CostumerStatistics(1, 0, 1);
        CostumerStatistics expected9 = new CostumerStatistics(1, 0, 1);
        CostumerStatistics expected10 = new CostumerStatistics(1, 0, 1);
        CostumerStatistics expected11 = new CostumerStatistics(1, 0, 1);
        CostumerStatistics expected12 = new CostumerStatistics(1, 0, 1);

        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);
        assertEquals(expected4, result4);
        assertEquals(expected5, result5);
        assertEquals(expected6, result6);
        assertEquals(expected7, result7);
        assertEquals(expected8, result8);
        assertEquals(expected9, result9);
        assertEquals(expected10, result10);
        assertEquals(expected11, result11);
        assertEquals(expected12, result12);
    }

    /**
     * Método que testa se o cálculo das estatísticas por cliente para a lista de expedição gerada para o dia 4 do ficheiro SMALL_BASKET_FILE_FOUR, é gerada corretamente.
     */
    @Test
    public void calculateStatisticsByCostumerTest8() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE_FOUR);

        int day = 4;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        distributionNetwork.calculateStatisticsByCostumer(actualBasketExpeditions);

        CostumerStatistics result1 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT2"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result2 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT1"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result3 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT3"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result4 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT5"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result5 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT16"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result6 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT14"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result7 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT12"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result8 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT11"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();

        CostumerStatistics expected1 = new CostumerStatistics(1, 0, 1);
        CostumerStatistics expected2 = new CostumerStatistics(1, 0, 1);
        CostumerStatistics expected3 = new CostumerStatistics(1, 0, 1);
        CostumerStatistics expected4 = new CostumerStatistics(1, 0, 1);
        CostumerStatistics expected5 = new CostumerStatistics(1, 0, 1);
        CostumerStatistics expected6 = new CostumerStatistics(1, 0, 1);
        CostumerStatistics expected7 = new CostumerStatistics(1, 0, 1);
        CostumerStatistics expected8 = new CostumerStatistics(1, 0, 1);

        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);
        assertEquals(expected4, result4);
        assertEquals(expected5, result5);
        assertEquals(expected6, result6);
        assertEquals(expected7, result7);
        assertEquals(expected8, result8);
    }

    /**
     * Método que testa se o cálculo das estatísticas por cliente para a lista de expedição gerada para o dia 1 do ficheiro FILE_PATH_CABAZES_SMALL, é gerada corretamente.
     */
    @Test
    public void calculateStatisticsByCostumerTest9() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(FILE_PATH_CABAZES_SMALL);

        int day = 1;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        distributionNetwork.calculateStatisticsByCostumer(actualBasketExpeditions);

        CostumerStatistics result1 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT2"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result2 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT1"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result3 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT4"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result4 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT3"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result5 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT5"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result6 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT8"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result7 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT9"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result8 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT13"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result9 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT12"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result10 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT11"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();

        CostumerStatistics expected1 = new CostumerStatistics(0, 1, 2);
        CostumerStatistics expected2 = new CostumerStatistics(1, 0, 3);
        CostumerStatistics expected3 = new CostumerStatistics(0, 1, 2);
        CostumerStatistics expected4 = new CostumerStatistics(0, 1, 3);
        CostumerStatistics expected5 = new CostumerStatistics(0, 1, 1);
        CostumerStatistics expected6 = new CostumerStatistics(0, 1, 1);
        CostumerStatistics expected7 = new CostumerStatistics(0, 1, 2);
        CostumerStatistics expected8 = new CostumerStatistics(0, 1, 0);
        CostumerStatistics expected9 = new CostumerStatistics(0, 1, 2);
        CostumerStatistics expected10 = new CostumerStatistics(0, 1, 2);

        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);
        assertEquals(expected4, result4);
        assertEquals(expected5, result5);
        assertEquals(expected6, result6);
        assertEquals(expected7, result7);
        assertEquals(expected8, result8);
        assertEquals(expected9, result9);
        assertEquals(expected10, result10);
    }

    /**
     * Método que testa se o cálculo das estatísticas por cliente para a lista de expedição gerada para o dia 2 do ficheiro FILE_PATH_CABAZES_SMALL, é gerada corretamente.
     */
    @Test
    public void calculateStatisticsByCostumerTest10() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(FILE_PATH_CABAZES_SMALL);

        int day = 2;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        distributionNetwork.calculateStatisticsByCostumer(actualBasketExpeditions);

        CostumerStatistics result1 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT2"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result2 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT1"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result3 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT4"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result4 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT3"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result5 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT7"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result6 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT15"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result7 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT14"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result8 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT13"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();
        CostumerStatistics result9 = ((Costumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT11"), distributionNetwork.getDistributionNetwork().vertices())).getCostumerStatistics();

        CostumerStatistics expected1 = new CostumerStatistics(0, 1, 3);
        CostumerStatistics expected2 = new CostumerStatistics(0, 1, 3);
        CostumerStatistics expected3 = new CostumerStatistics(0, 1, 3);
        CostumerStatistics expected4 = new CostumerStatistics(0, 1, 2);
        CostumerStatistics expected5 = new CostumerStatistics(0, 1, 3);
        CostumerStatistics expected6 = new CostumerStatistics(0, 1, 2);
        CostumerStatistics expected7 = new CostumerStatistics(0, 1, 2);
        CostumerStatistics expected8 = new CostumerStatistics(0, 1, 3);
        CostumerStatistics expected9 = new CostumerStatistics(0, 1, 1);

        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);
        assertEquals(expected4, result4);
        assertEquals(expected5, result5);
        assertEquals(expected6, result6);
        assertEquals(expected7, result7);
        assertEquals(expected8, result8);
        assertEquals(expected9, result9);
    }

    /**
     * Método que testa se o cálculo das estatísticas por hub para a lista de expedição gerada para o dia 2 do ficheiro SMALL_BASKET_FILE_THREE, é gerada corretamente.
     */
    @Test
    public void calculateStatisticsByHubTest1() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE_THREE);

        int day = 2;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        distributionNetwork.calculateStatisticsByHub(actualBasketExpeditions);

        HubStatistics result1 = ((CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT9"), distributionNetwork.getDistributionNetwork().vertices())).getHubStatistics();

        HubStatistics expected1 = new HubStatistics(1, 1);

        assertEquals(expected1, result1);

    }

    /**
     * Método que testa se o cálculo das estatísticas por hub para a lista de expedição gerada para o dia 1 do ficheiro FILE_PATH_CABAZES_SMALL, é gerada corretamente.
     */
    @Test
    public void calculateStatisticsByHubTest2() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(FILE_PATH_CABAZES_SMALL);

        int day = 1;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        distributionNetwork.calculateStatisticsByHub(actualBasketExpeditions);

        HubStatistics result1 = ((CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT14"), distributionNetwork.getDistributionNetwork().vertices())).getHubStatistics();
        HubStatistics result2 = ((CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT11"), distributionNetwork.getDistributionNetwork().vertices())).getHubStatistics();
        HubStatistics result3 = ((CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT5"), distributionNetwork.getDistributionNetwork().vertices())).getHubStatistics();
        HubStatistics result4 = ((CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT9"), distributionNetwork.getDistributionNetwork().vertices())).getHubStatistics();
        HubStatistics result5 = ((CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT4"), distributionNetwork.getDistributionNetwork().vertices())).getHubStatistics();

        HubStatistics expected1 = new HubStatistics(3, 3);
        HubStatistics expected2 = new HubStatistics(1, 1);
        HubStatistics expected3 = new HubStatistics(2, 2);
        HubStatistics expected4 = new HubStatistics(3, 3);
        HubStatistics expected5 = new HubStatistics(1, 3);

        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);
        assertEquals(expected4, result4);
        assertEquals(expected5, result5);

    }

    /**
     * Método que testa se o cálculo das estatísticas por hub para a lista de expedição gerada para o dia 2 do ficheiro FILE_PATH_CABAZES_SMALL, é gerada corretamente.
     */
    @Test
    public void calculateStatisticsByHubTeste3() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(FILE_PATH_CABAZES_SMALL);

        int day = 2;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        distributionNetwork.calculateStatisticsByHub(actualBasketExpeditions);

        HubStatistics result1 = ((CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT14"), distributionNetwork.getDistributionNetwork().vertices())).getHubStatistics();
        HubStatistics result2 = ((CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT11"), distributionNetwork.getDistributionNetwork().vertices())).getHubStatistics();
        HubStatistics result3 = ((CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT5"), distributionNetwork.getDistributionNetwork().vertices())).getHubStatistics();
        HubStatistics result4 = ((CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT9"), distributionNetwork.getDistributionNetwork().vertices())).getHubStatistics();
        HubStatistics result5 = ((CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT4"), distributionNetwork.getDistributionNetwork().vertices())).getHubStatistics();

        HubStatistics expected1 = new HubStatistics(3, 3);
        HubStatistics expected2 = new HubStatistics(1, 2);
        HubStatistics expected3 = new HubStatistics(1, 1);
        HubStatistics expected4 = new HubStatistics(2, 3);
        HubStatistics expected5 = new HubStatistics(2, 3);

        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);
        assertEquals(expected4, result4);
        assertEquals(expected5, result5);
    }

    /**
     * Método que testa se o cálculo das estatísticas por hub para a lista de expedição gerada para o dia 3 do ficheiro SMALL_BASKET_FILE_FOUR, é gerada corretamente.
     */
    @Test
    public void calculateStatisticsByHubTeste4() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE_FOUR);

        int day = 3;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        distributionNetwork.calculateStatisticsByHub(actualBasketExpeditions);

        HubStatistics result1 = ((CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT14"), distributionNetwork.getDistributionNetwork().vertices())).getHubStatistics();
        HubStatistics result2 = ((CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT11"), distributionNetwork.getDistributionNetwork().vertices())).getHubStatistics();
        HubStatistics result3 = ((CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT5"), distributionNetwork.getDistributionNetwork().vertices())).getHubStatistics();
        HubStatistics result4 = ((CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT9"), distributionNetwork.getDistributionNetwork().vertices())).getHubStatistics();
        HubStatistics result5 = ((CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT4"), distributionNetwork.getDistributionNetwork().vertices())).getHubStatistics();

        HubStatistics expected1 = new HubStatistics(4, 2);
        HubStatistics expected2 = new HubStatistics(1, 1);
        HubStatistics expected3 = new HubStatistics(2, 1);
        HubStatistics expected4 = new HubStatistics(4, 2);
        HubStatistics expected5 = new HubStatistics(1, 1);

        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);
        assertEquals(expected4, result4);
        assertEquals(expected5, result5);
    }

    /**
     * Método que testa se o cálculo das estatísticas por hub para a lista de expedição gerada para o dia 1 do ficheiro SMALL_BASKET_FILE_FOUR, é gerada corretamente.
     */
    @Test
    public void calculateStatisticsByHubTeste5() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE_FOUR);

        int day = 1;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        distributionNetwork.calculateStatisticsByHub(actualBasketExpeditions);

        HubStatistics result1 = ((CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT14"), distributionNetwork.getDistributionNetwork().vertices())).getHubStatistics();
        HubStatistics result2 = ((CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT11"), distributionNetwork.getDistributionNetwork().vertices())).getHubStatistics();
        HubStatistics result3 = ((CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT5"), distributionNetwork.getDistributionNetwork().vertices())).getHubStatistics();
        HubStatistics result4 = ((CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT9"), distributionNetwork.getDistributionNetwork().vertices())).getHubStatistics();
        HubStatistics result5 = ((CompanyCostumer) AuxiliariesTestMethods.itemInList(new NetworkPoint("CT4"), distributionNetwork.getDistributionNetwork().vertices())).getHubStatistics();

        HubStatistics expected1 = new HubStatistics(3, 2);
        HubStatistics expected2 = new HubStatistics(1, 1);
        HubStatistics expected3 = new HubStatistics(2, 1);
        HubStatistics expected4 = new HubStatistics(3, 2);
        HubStatistics expected5 = new HubStatistics(1, 2);

        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);
        assertEquals(expected4, result4);
        assertEquals(expected5, result5);
    }

    /**
     * Método para testar se é possivel gerar uma lista de expedição com os n hubs mais próximos, para um dado dia que não tem dados registados.
     */
    @Test
    public void generateExpeditionListWithNClosestHubsTest1() {
        List<BasketExpedition> basketExpeditionsList = distributionNetwork.generateExpeditionListWithNClosestHubs(1, 10);
        assertNull(basketExpeditionsList);
    }

    /**
     * Método para testar se é possivel gerar uma lista de expedição com os n hubs mais próximos, para um número de hubs impossivel.
     */
    @Test
    public void generateExpeditionListWithNClosestHubsTest2() {
        List<BasketExpedition> basketExpeditionsList = distributionNetwork.generateExpeditionListWithNClosestHubs(-1, 1);
        assertNull(basketExpeditionsList);
    }

    /**
     * Método que testa se a lista de expedição com os n hubs mais próximos gerada para o dia 1 do ficheiro SMALL_BASKET_FILE, é
     * gerada corretamente.
     */
    @Test
    public void generateExpeditionListWithNClosestHubsTest3() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE);

        int day = 1;
        int n = 2;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithNClosestHubs(n, day);

        List<BasketExpedition> expectedBasketExpeditions = new ArrayList<>();
        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 39.7444, -8.8072, "P1");
        AgriculturalProducer p2 = new AgriculturalProducer("CT6", 40.2111, -8.4291, "P2");
        Product productOne = new Product("Prod1");
        Product productTwo = new Product("Prod2");
        Product productThree = new Product("Prod3");
        ParticularCostumer particularCostumerTwo = new ParticularCostumer("CT2", 38.0333, -7.8833, "C2");
        CompanyCostumer companyCostumerTwo = new CompanyCostumer("CT11", 39.3167, -7.4167, "E2");

        // Cabaz de expedição para o cliente C2
        BasketExpedition basketExpeditionC2Day1 = new BasketExpedition(particularCostumerTwo, day);
        ProductProvided productProvidedOneC2Day1 = new ProductProvided(productTwo, 5.5, 5.5, p2);
        ProductProvided productProvidedTwoC2Day1 = new ProductProvided(productThree, 4.5, 1.5, p2);
        basketExpeditionC2Day1.addProductProvided(productProvidedOneC2Day1);
        basketExpeditionC2Day1.addProductProvided(productProvidedTwoC2Day1);
        expectedBasketExpeditions.add(basketExpeditionC2Day1);

        // Cabaz de expedição para o cliente E2
        BasketExpedition basketExpeditionE2Day1 = new BasketExpedition(companyCostumerTwo, day);
        ProductProvided productProvidedOneE2Day1 = new ProductProvided(productOne, 9, 7.5, p2);
        ProductProvided productProvidedTwoE2Day1 = new ProductProvided(productTwo, 6, 1, p2);
        ProductProvided productProvidedThreeE2Day1 = new ProductProvided(productThree, 9, 9, p1);

        basketExpeditionE2Day1.addProductProvided(productProvidedOneE2Day1);
        basketExpeditionE2Day1.addProductProvided(productProvidedTwoE2Day1);
        basketExpeditionE2Day1.addProductProvided(productProvidedThreeE2Day1);
        expectedBasketExpeditions.add(basketExpeditionE2Day1);

        assertEquals(expectedBasketExpeditions, actualBasketExpeditions);
    }

    /**
     * Método que testa se os valores dos mapas dos cabazes pedidos pelos clientes e dos cabazes fornecidos pelos
     * produtores, são atualizados corretamente. Neste teste é feita a expedição dos produtos para o dia 1 da lista
     * gerada pela utilização dos dados presentes no ficheiro SMALL_BASKET_FILE.
     * Teste complemento do teste generateExpeditionListWithNClosestHubsTest3().
     */
    @Test
    public void generateExpeditionListWithNClosestHubsTest4() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE);

        int day = 1;
        int n = 2;
        distributionNetwork.generateExpeditionListWithNClosestHubs(n, day);

        Map<Integer, Map<AgriculturalProducer, Basket>> productsAvailable = distributionNetwork.getProductsAvailable();
        Map<Integer, Map<Costumer, Basket>> productsRequest = distributionNetwork.getProductsRequest();

        Map<AgriculturalProducer, Basket> actualProductsAvailable = productsAvailable.get(day);
        Map<Costumer, Basket> actualProductsRequest = productsRequest.get(day);

        AgriculturalProducer p2 = new AgriculturalProducer("CT6", 40.2111, -8.4291, "P2");
        AgriculturalProducer p3 = new AgriculturalProducer("CT10", 39.7444, -8.8072, "P3");

        Product productOne = new Product("Prod1");
        Product productTwo = new Product("Prod2");
        Product productThree = new Product("Prod3");

        ParticularCostumer particularCostumerTwo = new ParticularCostumer("CT2", 38.0333, -7.8833, "C2");
        CompanyCostumer companyCostumerTwo = new CompanyCostumer("CT11", 39.3167, -7.4167, "E2");

        // Testa o cabaz pedido por C2
        assertEquals(0, actualProductsRequest.get(particularCostumerTwo).getProductQuantity(productTwo), 0);
        assertEquals(3.0, actualProductsRequest.get(particularCostumerTwo).getProductQuantity(productThree), 0);

        // Testa o cabaz pedido por E2
        assertEquals(1.5, actualProductsRequest.get(companyCostumerTwo).getProductQuantity(productOne), 0);
        assertEquals(5.0, actualProductsRequest.get(companyCostumerTwo).getProductQuantity(productTwo), 0);
        assertEquals(0, actualProductsRequest.get(companyCostumerTwo).getProductQuantity(productThree), 0);

        // Testa o cabaz disponibilizado por P2
        assertEquals(0, actualProductsAvailable.get(p2).getProductQuantity(productOne), 0);
        assertEquals(0, actualProductsAvailable.get(p2).getProductQuantity(productTwo), 0);
        assertEquals(0, actualProductsAvailable.get(p2).getProductQuantity(productThree), 0);

        // Testa o cabaz disponibilizado por P3
        assertEquals(2.5, actualProductsAvailable.get(p3).getProductQuantity(productOne), 0);
        assertEquals(2, actualProductsAvailable.get(p3).getProductQuantity(productTwo), 0);
        assertEquals(0, actualProductsAvailable.get(p3).getProductQuantity(productThree), 0);
    }

    /**
     * Método que testa se a lista de expedição com os n hubs mais próximos gerada para o dia 2 do ficheiro SMALL_BASKET_FILE, é
     * gerada corretamente. Aqui, alguns produtos serão procurados consequentemente nos cabazes disponibilizados no dia 1.
     */
    @Test
    public void generateExpeditionListWithNClosestHubsTest5() {
        distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(SMALL_BASKET_FILE);

        int day = 2;
        int n = 2;
        List<BasketExpedition> actualBasketExpeditions = distributionNetwork.generateExpeditionListWithNClosestHubs(n, day);

        List<BasketExpedition> expectedBasketExpeditions = new ArrayList<>();
        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");
        AgriculturalProducer p2 = new AgriculturalProducer("CT6", 40.2111, -8.4291, "P2");
        AgriculturalProducer p3 = new AgriculturalProducer("CT10", 39.7444, -8.8072, "P3");

        Product productOne = new Product("Prod1");
        Product productTwo = new Product("Prod2");
        Product productThree = new Product("Prod3");

        ParticularCostumer particularCostumerOne = new ParticularCostumer("CT1", 40.6389, -8.6553, "C1");
        ParticularCostumer particularCostumerTwo = new ParticularCostumer("CT2", 38.0333, -7.8833, "C2");
        ParticularCostumer particularCostumerThree = new ParticularCostumer("CT3", 41.5333, -8.4167, "C3");
        CompanyCostumer companyCostumerOne = new CompanyCostumer("CT14", 38.5243, -8.8926, "E1");
        CompanyCostumer companyCostumerTwo = new CompanyCostumer("CT11", 39.3167, -7.4167, "E2");
        CompanyCostumer companyCostumerThree = new CompanyCostumer("CT5", 39.823, -7.4931, "E3");

        // Cabaz de expedição para o cliente C1
        BasketExpedition basketExpeditionC1Day2 = new BasketExpedition(particularCostumerOne, day);
        ProductProvided productProvidedOneC1Day2 = new ProductProvided(productOne, 4.5, 3, p1);
        ProductProvided productProvidedTwoC1Day2 = new ProductProvided(productTwo, 6, 6, p1); // De dia 1
        ProductProvided productProvidedThreeC1Day2 = new ProductProvided(productThree, 3.5, 3.5, p2);
        basketExpeditionC1Day2.addProductProvided(productProvidedOneC1Day2);
        basketExpeditionC1Day2.addProductProvided(productProvidedTwoC1Day2);
        basketExpeditionC1Day2.addProductProvided(productProvidedThreeC1Day2);

        // Cabaz de expedição para o cliente C2
        BasketExpedition basketExpeditionC2Day2 = new BasketExpedition(particularCostumerTwo, day);
        ProductProvided productProvidedOneC2Day2 = new ProductProvided(productOne, 9, 6, p2);
        ProductProvided productProvidedTwoC2Day2 = new ProductProvided(productTwo, 7, 6.5, p2); // De dia 1
        basketExpeditionC2Day2.addProductProvided(productProvidedOneC2Day2);
        basketExpeditionC2Day2.addProductProvided(productProvidedTwoC2Day2);

        // Cabaz de expedição para o cliente C3
        BasketExpedition basketExpeditionC3Day2 = new BasketExpedition(particularCostumerThree, day);
        ProductProvided productProvidedOneC3Day2 = new ProductProvided(productOne, 10, 7.5, p2); // De dia 1
        ProductProvided productProvidedTwoC3Day2 = new ProductProvided(productTwo, 20, 1.5, p1); // De dia 1
        basketExpeditionC3Day2.addProductProvided(productProvidedOneC3Day2);
        basketExpeditionC3Day2.addProductProvided(productProvidedTwoC3Day2);

        // Cabaz de expedição para o cliente E1
        BasketExpedition basketExpeditionE1Day1 = new BasketExpedition(companyCostumerOne, day);
        ProductProvided productProvidedThreeE1Day1 = new ProductProvided(productThree, 9.5, 6.5, p2);
        basketExpeditionE1Day1.addProductProvided(productProvidedThreeE1Day1);

        // Cabaz de expedição para o cliente E2
        BasketExpedition basketExpeditionE2Day1 = new BasketExpedition(companyCostumerTwo, day);
        ProductProvided productProvidedThreeE2Day1 = new ProductProvided(productThree, 7.5, 1.5, p2); // De dia 1
        basketExpeditionE2Day1.addProductProvided(productProvidedThreeE2Day1);

        // Cabaz de expedição para o cliente E3
        BasketExpedition basketExpeditionE3Day2 = new BasketExpedition(companyCostumerThree, day);
        ProductProvided productProvidedOneE3Day2 = new ProductProvided(productOne, 8.5, 2.5, p3); // De dia 1
        ProductProvided productProvidedTwoE3Day2 = new ProductProvided(productTwo, 5, 2, p3); // De dia 1
        basketExpeditionE3Day2.addProductProvided(productProvidedOneE3Day2);
        basketExpeditionE3Day2.addProductProvided(productProvidedTwoE3Day2);

        expectedBasketExpeditions.add(basketExpeditionC2Day2);
        expectedBasketExpeditions.add(basketExpeditionC1Day2);
        expectedBasketExpeditions.add(basketExpeditionC3Day2);
        expectedBasketExpeditions.add(basketExpeditionE3Day2);
        expectedBasketExpeditions.add(basketExpeditionE1Day1);
        expectedBasketExpeditions.add(basketExpeditionE2Day1);

        assertEquals(expectedBasketExpeditions, actualBasketExpeditions);
    }

}