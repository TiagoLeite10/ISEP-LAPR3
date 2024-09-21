package dataStructure.graph;

import model.AgriculturalProducer;
import model.NetworkPoint;
import model.Product;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;

public class ProductTest {

    /**
     * Testa o método equals com dois objetos Product iguais.
     */
    @Test
    public void equalsTest1() {
        Product product1 = new Product("A11");
        Product product2 = new Product("A11");
        boolean result = product1.equals(product2);

        assertTrue(result);
    }

    /**
     * Testa o método equals com dois objetos Product diferentes.
     */
    @Test
    public void equalsTest2() {
        Product product1 = new Product("A11");
        Product product2 = new Product("A21");

        boolean result = product1.equals(product2);
        assertFalse(result);
    }


    /**
     * Testa o método hashCode.
     */
    @Test
    public void hashCodeTest1() {
        Product product = new Product("A11");

        int result = product.hashCode();
        int expResult = 64033;

        assertEquals(expResult, result);
    }

    /**
     * Testa o método hashCode.
     */
    @Test
    public void hashCodeTest2() {
        Product product = new Product("A11");

        int result = product.hashCode();
        int expResult = 1;

        assertNotEquals(expResult, result);
    }


}
