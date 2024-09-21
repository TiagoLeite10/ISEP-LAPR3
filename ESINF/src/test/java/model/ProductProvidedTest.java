package model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Classe para realizar teste aos métodos da classe ProductProvided
 */
public class ProductProvidedTest {

    /**
     * Atributo para indicar a exceção esperada quando é feito algo que lance uma exceção.
     */
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    /**
     * Método que verificar se ao instanciar um objeto da class ProductProvided utilizando um produto nulo, é lançada a
     * excçeão esperada.
     * Testa o método checkProductProvided da classe ProductProvided.
     */
    @Test
    public void checkProductProvidedTest1() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("O produto não pode ser nulo.");

        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");
        ProductProvided productProvided = new ProductProvided(null, 10, 10, p1);
    }

    /**
     * Método que verifica se ao instanciar um objeto da class ProductProvided utilizando um produto não nulo, o objeto
     * é criado corretamente.
     * Testa o método checkProductProvided da classe ProductProvided.
     */
    @Test
    public void checkProductProvidedTest2() {
        Product product = new Product("Prod1");
        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");

        ProductProvided productProvided = new ProductProvided(product, 10, 10, p1);

    }

    /**
     * Método para verificar se o método equals funciona corretamente quando todos os atributos têm igual valor.
     */
    @Test
    public void equalsTest1() {

        Product productOne = new Product("Prod1");
        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");

        ProductProvided productProvidedOne = new ProductProvided(productOne, 5, 4, p1);
        ProductProvided productProvidedTwo = new ProductProvided(productOne, 5, 4, p1);

        assertEquals(productProvidedOne, productProvidedTwo);

    }

    /**
     * Método para verificar se o método equals funciona corretamente quando o produto é diferente para os dois objetos.
     */
    @Test
    public void equalsTest2() {

        Product productOne = new Product("Prod1");
        Product productTwo = new Product("Prod2");
        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");

        ProductProvided productProvidedOne = new ProductProvided(productOne, 5, 4, p1);
        ProductProvided productProvidedTwo = new ProductProvided(productTwo, 5, 4, p1);

        assertNotEquals(productProvidedOne, productProvidedTwo);

    }

    /**
     * Método para verificar se o método equals funciona corretamente quando a quantidade pedida difere nos dois
     * objetos.
     */
    @Test
    public void equalsTest3() {
        Product productOne = new Product("Prod1");
        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");

        ProductProvided productProvidedOne = new ProductProvided(productOne, 5, 4, p1);
        ProductProvided productProvidedTwo = new ProductProvided(productOne, 10, 4, p1);

        assertNotEquals(productProvidedOne, productProvidedTwo);
    }

    /**
     * Método para verificar se o método equals funciona corretamente quando a quantidade entregue difere nos dois
     * objetos.
     */
    @Test
    public void equalsTest4() {
        Product productOne = new Product("Prod1");
        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");

        ProductProvided productProvidedOne = new ProductProvided(productOne, 5, 4, p1);
        ProductProvided productProvidedTwo = new ProductProvided(productOne, 5, 10, p1);

        assertNotEquals(productProvidedOne, productProvidedTwo);
    }

    /**
     * Método para verificar se o método equals funciona corretamente quando o fornecedor do produto difere nos dois
     * objetos.
     */
    @Test
    public void equalsTest5() {
        Product productOne = new Product("Prod1");
        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");
        AgriculturalProducer p2 = new AgriculturalProducer("CT6", 40.2111, -8.4291, "P2");

        ProductProvided productProvidedOne = new ProductProvided(productOne, 5, 4, p1);
        ProductProvided productProvidedTwo = new ProductProvided(productOne, 5, 4, p2);

        assertNotEquals(productProvidedOne, productProvidedTwo);
    }

    /**
     * Método para verificar se o método equals funciona corretamente quando o fornecedor do produto é null num dos
     * objetos e no outro não.
     */
    @Test
    public void equalsTest6() {
        Product productOne = new Product("Prod1");
        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");

        ProductProvided productProvidedOne = new ProductProvided(productOne, 5, 4, p1);
        ProductProvided productProvidedTwo = new ProductProvided(productOne, 5, 4, null);

        assertNotEquals(productProvidedOne, productProvidedTwo);
    }

    /**
     * Método para verificar se o método equals funciona corretamente quando o fornecedor do produto é null nos dois
     * objetos.
     */
    @Test
    public void equalsTest7() {
        Product productOne = new Product("Prod1");

        ProductProvided productProvidedOne = new ProductProvided(productOne, 5, 4, null);
        ProductProvided productProvidedTwo = new ProductProvided(productOne, 5, 4, null);

        assertEquals(productProvidedOne, productProvidedTwo);
    }

    /**
     * Método que testa o hashcode gerado para o produto com o nome de Prod1.
     * O hashcode é gerado sob a fórmula: s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1].
     */
    @Test
    public void hashCodeTest1() {
        Product productOne = new Product("Prod1");
        int generatedHashCode = productOne.hashCode();
        int expectedHashCode = 77387674;
        assertEquals(expectedHashCode, generatedHashCode);
    }

    /**
     * Método que testa o hashcode gerado para o produto com o nome de Prod2.
     * O hashcode é gerado sob a fórmula: s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1].
     */
    @Test
    public void hashCodeTest2() {
        Product productOne = new Product("Prod2");
        int generatedHashCode = productOne.hashCode();
        int expectedHashCode = 77387675;
        assertEquals(expectedHashCode, generatedHashCode);
    }

    /**
     * Método que testa o hashcode gerado para o produto com o nome de Bananas.
     * O hashcode é gerado sob a fórmula: s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1].
     */
    @Test
    public void hashCodeTest3() {
        Product productOne = new Product("Bananas");
        int generatedHashCode = productOne.hashCode();
        int expectedHashCode = 1327314318;
        assertEquals(expectedHashCode, generatedHashCode);
    }

}
