package utils;

import testHelper.TestMapsHelper;
import model.*;
import org.junit.*;
import org.junit.rules.ExpectedException;
import utils.exception.InvalidFileLineException;
import utils.exception.NetworkPointNotFoundException;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe para realizar testes à classe FileToBasket.
 */
public class FileToBasketTest {

    /**
     * Atributo que representa o objeto DistributionNetwork
     */
    private Graph<NetworkPoint, Integer> distributionNetworkGraph;

    /**
     * Map que contem os produtos disponíveis em cabazes.
     */
    private final Map<Integer, Map<AgriculturalProducer, Basket>> productsAvailable = new HashMap<>();

    /**
     * Map que contem os produtos requeridos para cabezes.
     */
    private final Map<Integer, Map<Costumer, Basket>> productsRequest = new HashMap<>();

    /**
     * Representa o ficheiro com alguma informação de cabazes a ser lido.
     */
    final String SMALL_BASKET_FILE = "files/test/cabazes_small_test.csv";

    /**
     * Atributo para indicar a exceção esperada quando é feito algo que lance uma exceção.
     */
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    /**
     * Antes de correr os testes, construir a rede de distribuição DistributionNetwork.
     */
    @Before
    public void setUp() {
        DistributionNetwork distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork("files/small/clientes-produtores_small.csv",
                "files/small/distancias_small.csv");
        this.distributionNetworkGraph = distributionNetwork.getDistributionNetwork();
    }

    /**
     * Método para testar quando existe um dia inválido no ficheiro de importação de cabazes.
     */
    @Test
    public void readFileToBasketTest1() {
        exceptionRule.expect(NumberFormatException.class);
        exceptionRule.expectMessage("Verifique o valor do dia na linha 5 do ficheiro, pois encontra-se inválido.");
        String invalid_day_file = "files/test/cabazes_small_test_fail_wrong_day.csv";
        FileToBasket.readFileToBasket(distributionNetworkGraph, productsAvailable, productsRequest, invalid_day_file);
    }

    /**
     * Método para testar quando uma quantidade está inválido o ficheiro de importação de cabazes.
     */
    @Test
    public void readFileToBasketTest2() {
        exceptionRule.expect(NumberFormatException.class);
        exceptionRule.expectMessage("Foi encontrado um erro na linha 10 do ficheiro. Verifique as quantidades " +
                "existentes para os produtos nessa mesma linha.");
        String invalid_amount_file = "files/test/cabazes_small_test_fail_invalid_amount.csv";
        FileToBasket.readFileToBasket(distributionNetworkGraph, productsAvailable, productsRequest, invalid_amount_file);
    }

    /**
     * Método para testar quando é indicado um cliente ou produtor no ficheiro de importação de cabazes, e que este não
     * existe na rede de distribuição.
     */
    @Test
    public void readFileToBasketTest3() {
        exceptionRule.expect(NetworkPointNotFoundException.class);
        exceptionRule.expectMessage("O cliente ou produtor específicado na linha 20 do ficheiro, não foi " +
                "encontrado na rede de distribuição. Por favor, verifique se está correto.");
        String nonexistent_codidentifier_file = "files/test/cabazes_small_test_fail_nonexistent_codIdentifier.csv";
        FileToBasket.readFileToBasket(distributionNetworkGraph, productsAvailable, productsRequest, nonexistent_codidentifier_file);
    }

    /**
     * Método para testar quando uma linha de um ficheiro tem um número de colunas diferente ao número de colunas que o
     * cabeçalho desse mesmo ficheiro tem.
     */
    @Test
    public void readFileToBasketTest4() {
        exceptionRule.expect(InvalidFileLineException.class);
        exceptionRule.expectMessage(new InvalidFileLineException(19).getMessage());
        String invalid_line_size_file = "files/test/cabazes_small_test_fail_invalid_line_size.csv";
        FileToBasket.readFileToBasket(distributionNetworkGraph, productsAvailable, productsRequest, invalid_line_size_file);
    }

    /**
     * Método para testar quando o caminho de um ficheiro passado para o método, não existe.
     */
    @Test
    public void readFileToBasketTest5() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Não existe nenhum ficheiro no caminho indicado.");
        String inexistent_file = "não existe este ficheiro";
        FileToBasket.readFileToBasket(distributionNetworkGraph, productsAvailable, productsRequest, inexistent_file);
    }

    /**
     * Método que testa quando a rede de distribuição é nula ou não contem vértices.
     */
    @Test
    public void readFileToBasketTest6() {
        DistributionNetwork nullDistributionNetwork = new DistributionNetwork();
        FileToBasket.readFileToBasket(nullDistributionNetwork.getDistributionNetwork(), productsAvailable, productsRequest, SMALL_BASKET_FILE);
        assertEquals(new HashMap<>(), productsAvailable);
        assertEquals(new HashMap<>(), productsRequest);
    }

    /**
     * Método que verifica se o mapa de produtos disponíveis (em cabaz) é construído corretamente.
     */
    @Test
    public void readFileToBasketTest7() {
        FileToBasket.readFileToBasket(distributionNetworkGraph, productsAvailable, productsRequest, SMALL_BASKET_FILE);
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

        TestMapsHelper.testProductsAvailableOrRequestedMaps(expectedMap, productsAvailable);

    }

    /**
     * Método que verifica se o mapa de produtos pedidos (em cabaz) é construído corretamente.
     */
    @Test
    public void readFileToBasketTest8() {
        FileToBasket.readFileToBasket(distributionNetworkGraph, productsAvailable, productsRequest, SMALL_BASKET_FILE);
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

        TestMapsHelper.testProductsAvailableOrRequestedMaps(expectedMap, productsRequest);

    }

}
