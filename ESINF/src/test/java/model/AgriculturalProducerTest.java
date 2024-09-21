package model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe para realizar testes à classe AgriculturalProducer.
 */
public class AgriculturalProducerTest {

    /**
     * Testa o método equals com dois objetos AgriculturalProducer iguais.
     */
    @Test
    public void equalsTest1() {
        AgriculturalProducer agriculturalProducer1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");
        AgriculturalProducer agriculturalProducer2 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");

        boolean result = agriculturalProducer1.equals(agriculturalProducer2);

        assertTrue(result);
    }

    /**
     * Testa o método equals com dois objetos AgriculturalProducer diferentes.
     */
    @Test
    public void equalsTest2() {
        AgriculturalProducer agriculturalProducer1 = new AgriculturalProducer("CT10", 39.7444, -8.8072, "P3");
        AgriculturalProducer agriculturalProducer2 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");

        boolean result = agriculturalProducer1.equals(agriculturalProducer2);

        assertFalse(result);
    }

    /**
     * Testa o método hashCode.
     */
    @Test
    public void hashCodeTest1() {
        AgriculturalProducer agriculturalProducer = new AgriculturalProducer("CT10", 39.7444, -8.8072, "P3");

        int result = agriculturalProducer.hashCode();
        int expResult = 2078288;

        assertEquals(expResult, result);
    }

    /**
     * Testa o método hashCode.
     */
    @Test
    public void hashCodeTest2() {
        AgriculturalProducer agriculturalProducer = new AgriculturalProducer("CT10", 39.7444, -8.8072, "P3");

        int result = agriculturalProducer.hashCode();
        int expResult = 1;

        assertNotEquals(expResult, result);
    }
}
