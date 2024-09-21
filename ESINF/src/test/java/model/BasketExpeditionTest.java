package model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Classe para realizar teste aos métodos da classe BasketExpedition
 */
public class BasketExpeditionTest {

    /**
     * Caminho do ficheiro pequeno com as distâncias
     */
    private final String FILE_PATH_DISTANCES_SMALL = "files/small/distancias_small.csv";

    /**
     * Caminho do ficheiro pequeno com as informações sobre clientes-produtores
     */
    private final String FILE_PATH_PRODUCER_COSTUMER_SMALL = "files/small/clientes-produtores_small.csv";

    /**
     * Caminho do ficheiro pequeno com a informação sobre os cabazes
     */
    private final String FILE_PATH_CABAZES_SMALL = "files/small/cabazes_small.csv";

    /**
     * Testa o método addProductProvided da classe BasketExpedition, em que são inseridos objetos do tipo
     * ProductProvided com valores de produto diferentes, sendo então inseridos todos com sucesso.
     */
    @Test
    public void addProductProvidedTest1() {
        ParticularCostumer particularCostumerOne = new ParticularCostumer("CT1", 40.6389, -8.6553, "C1");
        BasketExpedition basketExpedition = new BasketExpedition(particularCostumerOne, 1);

        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");

        Product productOne = new Product("Prod1");
        Product productTwo = new Product("Prod2");
        Product productThree = new Product("Prod3");

        ProductProvided productProvidedOne = new ProductProvided(productOne, 10, 10, p1);
        ProductProvided productProvidedTwo = new ProductProvided(productTwo, 10, 10, p1);
        ProductProvided productProvidedThree = new ProductProvided(productThree, 10, 10, p1);

        assertTrue(basketExpedition.addProductProvided(productProvidedOne));
        assertTrue(basketExpedition.addProductProvided(productProvidedTwo));
        assertTrue(basketExpedition.addProductProvided(productProvidedThree));

    }

    /**
     * Testa o método addProductProvided da classe BasketExpedition, em que são inseridos objetos do tipo
     * ProductProvided com produtos de nomes iguais, sendo que estes não são inseridos e é devolvido o valor false.
     */
    @Test
    public void addProductProvidedTest2() {
        ParticularCostumer particularCostumerOne = new ParticularCostumer("CT1", 40.6389, -8.6553, "C1");
        BasketExpedition basketExpedition = new BasketExpedition(particularCostumerOne, 1);

        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");

        Product productOne = new Product("Prod1");
        Product productTwo = new Product("Prod2");

        ProductProvided productProvidedOne = new ProductProvided(productOne, 10, 10, p1);
        ProductProvided productProvidedTwo = new ProductProvided(productTwo, 10, 10, p1);
        ProductProvided productProvidedThree = new ProductProvided(productOne, 10, 10, p1);

        // Insere com sucesso o produtor fornecido 1
        assertTrue(basketExpedition.addProductProvided(productProvidedOne));
        // Tenta inserir o mesmo objeto
        assertFalse(basketExpedition.addProductProvided(productProvidedOne));

        // Insere com sucesso o produtor fornecido 2
        assertTrue(basketExpedition.addProductProvided(productProvidedTwo));

        // Tenta inserir um ProductProvided que tem um produto com o mesmo nome de outro já inserido.
        assertFalse(basketExpedition.addProductProvided(productProvidedThree));

    }

    /**
     * Método que testa se dois objetos de BasketExpedition tem os mesmos valores nos atributos.
     */
    @Test
    public void equalsTest1() {
        ParticularCostumer particularCostumerOne = new ParticularCostumer("CT1", 40.6389, -8.6553, "C1");
        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");
        AgriculturalProducer p2 = new AgriculturalProducer("CT6", 40.2111, -8.4291, "P2");
        int day = 1;
        Product productOne = new Product("Prod1");
        Product productTwo = new Product("Prod2");
        Product productThree = new Product("Prod3");

        ProductProvided productProvidedCostumerOne1 = new ProductProvided(productOne, 10, 10, p1);
        ProductProvided productProvidedCostumerOne2 = new ProductProvided(productTwo, 20, 10, p1);
        ProductProvided productProvidedCostumerOne3 = new ProductProvided(productThree, 15, 15, p2);
        BasketExpedition basketExpeditionCostumerOne1 = new BasketExpedition(particularCostumerOne, day);
        basketExpeditionCostumerOne1.addProductProvided(productProvidedCostumerOne1);
        basketExpeditionCostumerOne1.addProductProvided(productProvidedCostumerOne2);
        basketExpeditionCostumerOne1.addProductProvided(productProvidedCostumerOne3);

        // Aqui cria-se outro objeto produto com o mesmo valor que productOne, para testar se mesmo assim o produto
        // continua a ser considerado igual.
        ProductProvided productProvidedCostumerTwo1 = new ProductProvided(new Product("Prod1"), 10, 10, p1);
        ProductProvided productProvidedCostumerTwo2 = new ProductProvided(productTwo, 20, 10, p1);
        ProductProvided productProvidedCostumerTwo3 = new ProductProvided(productThree, 15, 15, p2);
        BasketExpedition basketExpeditionCostumerOne2 = new BasketExpedition(particularCostumerOne, day);
        basketExpeditionCostumerOne2.addProductProvided(productProvidedCostumerTwo1);
        basketExpeditionCostumerOne2.addProductProvided(productProvidedCostumerTwo2);
        basketExpeditionCostumerOne2.addProductProvided(productProvidedCostumerTwo3);

        assertEquals(basketExpeditionCostumerOne1, basketExpeditionCostumerOne2);

    }

    /**
     * Método que testa se dois objetos de BasketExpedition tem os mesmos valores nos atributos, com exceção do produto.
     */
    @Test
    public void equalsTest2() {
        ParticularCostumer particularCostumerOne = new ParticularCostumer("CT1", 40.6389, -8.6553, "C1");
        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");
        int day = 1;
        Product productOne = new Product("Prod1");
        Product productTwo = new Product("Prod2");

        ProductProvided productProvidedCostumerOne1 = new ProductProvided(productOne, 10, 10, p1);
        BasketExpedition basketExpeditionCostumerOne1 = new BasketExpedition(particularCostumerOne, day);
        basketExpeditionCostumerOne1.addProductProvided(productProvidedCostumerOne1);

        ProductProvided productProvidedCostumerTwo1 = new ProductProvided(productTwo, 10, 10, p1);
        BasketExpedition basketExpeditionCostumerOne2 = new BasketExpedition(particularCostumerOne, day);
        basketExpeditionCostumerOne2.addProductProvided(productProvidedCostumerTwo1);

        assertNotEquals(basketExpeditionCostumerOne1, basketExpeditionCostumerOne2);

    }

    /**
     * Método que testa se dois objetos de BasketExpedition tem os mesmos valores nos atributos, com exceção da
     * quantidade requisitada.
     */
    @Test
    public void equalsTest3() {
        ParticularCostumer particularCostumerOne = new ParticularCostumer("CT1", 40.6389, -8.6553, "C1");
        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");
        int day = 1;
        Product productOne = new Product("Prod1");

        ProductProvided productProvidedCostumerOne1 = new ProductProvided(productOne, 10, 10, p1);
        BasketExpedition basketExpeditionCostumerOne1 = new BasketExpedition(particularCostumerOne, day);
        basketExpeditionCostumerOne1.addProductProvided(productProvidedCostumerOne1);

        ProductProvided productProvidedCostumerTwo1 = new ProductProvided(productOne, 10.1, 10, p1);
        BasketExpedition basketExpeditionCostumerOne2 = new BasketExpedition(particularCostumerOne, day);
        basketExpeditionCostumerOne2.addProductProvided(productProvidedCostumerTwo1);

        // Teste 1 -> 10 vs 10.1
        assertNotEquals(basketExpeditionCostumerOne1, basketExpeditionCostumerOne2);

        productProvidedCostumerTwo1 = new ProductProvided(productOne, 9.9, 10, p1);
        basketExpeditionCostumerOne2 = new BasketExpedition(particularCostumerOne, day);
        basketExpeditionCostumerOne2.addProductProvided(productProvidedCostumerTwo1);

        // Teste 2 -> 10 vs 9.9
        assertNotEquals(basketExpeditionCostumerOne1, basketExpeditionCostumerOne2);

        productProvidedCostumerTwo1 = new ProductProvided(productOne, 11, 10, p1);
        basketExpeditionCostumerOne2 = new BasketExpedition(particularCostumerOne, day);
        basketExpeditionCostumerOne2.addProductProvided(productProvidedCostumerTwo1);

        // Teste 3 -> 10 vs 11
        assertNotEquals(basketExpeditionCostumerOne1, basketExpeditionCostumerOne2);

        productProvidedCostumerTwo1 = new ProductProvided(productOne, 9, 10, p1);
        basketExpeditionCostumerOne2 = new BasketExpedition(particularCostumerOne, day);
        basketExpeditionCostumerOne2.addProductProvided(productProvidedCostumerTwo1);

        // Teste 4 -> 10 vs 9
        assertNotEquals(basketExpeditionCostumerOne1, basketExpeditionCostumerOne2);

    }

    /**
     * Método que testa se dois objetos de BasketExpedition tem os mesmos valores nos atributos, com exceção da
     * quantidade entregue.
     */
    @Test
    public void equalsTest4() {
        ParticularCostumer particularCostumerOne = new ParticularCostumer("CT1", 40.6389, -8.6553, "C1");
        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");
        int day = 1;
        Product productOne = new Product("Prod1");

        ProductProvided productProvidedCostumerOne1 = new ProductProvided(productOne, 10, 10, p1);
        BasketExpedition basketExpeditionCostumerOne1 = new BasketExpedition(particularCostumerOne, day);
        basketExpeditionCostumerOne1.addProductProvided(productProvidedCostumerOne1);

        ProductProvided productProvidedCostumerTwo1 = new ProductProvided(productOne, 10, 10.1, p1);
        BasketExpedition basketExpeditionCostumerOne2 = new BasketExpedition(particularCostumerOne, day);
        basketExpeditionCostumerOne2.addProductProvided(productProvidedCostumerTwo1);

        // Teste 1 -> 10 vs 10.1
        assertNotEquals(basketExpeditionCostumerOne1, basketExpeditionCostumerOne2);

        productProvidedCostumerTwo1 = new ProductProvided(productOne, 10, 9.9, p1);
        basketExpeditionCostumerOne2 = new BasketExpedition(particularCostumerOne, day);
        basketExpeditionCostumerOne2.addProductProvided(productProvidedCostumerTwo1);

        // Teste 2 -> 10 vs 9.9
        assertNotEquals(basketExpeditionCostumerOne1, basketExpeditionCostumerOne2);

        productProvidedCostumerTwo1 = new ProductProvided(productOne, 10, 11, p1);
        basketExpeditionCostumerOne2 = new BasketExpedition(particularCostumerOne, day);
        basketExpeditionCostumerOne2.addProductProvided(productProvidedCostumerTwo1);

        // Teste 3 -> 10 vs 11
        assertNotEquals(basketExpeditionCostumerOne1, basketExpeditionCostumerOne2);

        productProvidedCostumerTwo1 = new ProductProvided(productOne, 10, 9, p1);
        basketExpeditionCostumerOne2 = new BasketExpedition(particularCostumerOne, day);
        basketExpeditionCostumerOne2.addProductProvided(productProvidedCostumerTwo1);

        // Teste 4 -> 10 vs 9
        assertNotEquals(basketExpeditionCostumerOne1, basketExpeditionCostumerOne2);

    }

    /**
     * Método que testa se dois objetos de BasketExpedition tem os mesmos valores nos atributos, com exceção do
     * produtor.
     */
    @Test
    public void equalsTest5() {
        ParticularCostumer particularCostumerOne = new ParticularCostumer("CT1", 40.6389, -8.6553, "C1");
        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");
        AgriculturalProducer p2 = new AgriculturalProducer("CT6", 40.2111, -8.4291, "P2");
        int day = 1;
        Product productOne = new Product("Prod1");

        ProductProvided productProvidedCostumerOne1 = new ProductProvided(productOne, 10, 10, p1);
        BasketExpedition basketExpeditionCostumerOne1 = new BasketExpedition(particularCostumerOne, day);
        basketExpeditionCostumerOne1.addProductProvided(productProvidedCostumerOne1);

        ProductProvided productProvidedCostumerTwo1 = new ProductProvided(productOne, 10, 10, p2);
        BasketExpedition basketExpeditionCostumerOne2 = new BasketExpedition(particularCostumerOne, day);
        basketExpeditionCostumerOne2.addProductProvided(productProvidedCostumerTwo1);

        // Teste 1 -> produtor 1 vs produtor 2
        assertNotEquals(basketExpeditionCostumerOne1, basketExpeditionCostumerOne2);

        productProvidedCostumerTwo1 = new ProductProvided(productOne, 10, 10, null);
        basketExpeditionCostumerOne2 = new BasketExpedition(particularCostumerOne, day);
        basketExpeditionCostumerOne2.addProductProvided(productProvidedCostumerTwo1);

        // Teste 2 -> produtor 1 vs null
        assertNotEquals(basketExpeditionCostumerOne1, basketExpeditionCostumerOne2);

    }

    /**
     * Método que testa o método que devolve a localização de entrega de um dado cabaz em expedição.
     */
    @Test
    public void getDeliveryLocationTest() {
        DistributionNetwork distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);
        distributionNetwork.fileToBasket(FILE_PATH_CABAZES_SMALL);

        int day = 1;
        List<BasketExpedition> basketExpeditions = distributionNetwork.generateExpeditionListWithoutRestriction(day);

        BasketExpedition basketExpeditionOne = basketExpeditions.get(0);
        NetworkPoint deliveryLocationBasketExpeditionOne = basketExpeditionOne.getDeliveryLocation();
        CompanyCostumer companyCostumer1 = new CompanyCostumer("CT14", 38.5243, -8.8926, "E1");
        assertEquals(companyCostumer1, deliveryLocationBasketExpeditionOne);

        BasketExpedition basketExpeditionTwo = basketExpeditions.get(1);
        NetworkPoint deliveryLocationBasketExpeditionTwo = basketExpeditionTwo.getDeliveryLocation();
        CompanyCostumer companyCostumer2 = new CompanyCostumer("CT9", 40.5364, -7.2683, "E4");
        assertEquals(companyCostumer2, deliveryLocationBasketExpeditionTwo);

    }

}
