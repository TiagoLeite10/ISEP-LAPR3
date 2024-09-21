package model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe para realizar testes à classe NetworkPoint.
 */
public class NetworkPointTest {

    /**
     * Testa o método equals com dois objetos NetworkPoint iguais.
     */
    @Test
    public void equalsTest1() {
        NetworkPoint networkPoint1 = new NetworkPoint("CT1");
        NetworkPoint networkPoint2 = new NetworkPoint("CT1");

        assertEquals(networkPoint1, networkPoint2);
    }

    /**
     * Testa o método equals com dois objetos NetworkPoint diferentes.
     */
    @Test
    public void equalsTest2() {
        NetworkPoint networkPoint1 = new NetworkPoint("CT1");
        NetworkPoint networkPoint2 = new NetworkPoint("CF1");

        assertNotEquals(networkPoint1, networkPoint2);
    }

    /**
     * Testa o método hashCode.
     */
    @Test
    public void hashCodeTest1() {
        NetworkPoint networkPoint = new NetworkPoint("CT1");

        int result = networkPoint.hashCode();
        int expResult = 67040;

        assertEquals(expResult, result);
    }

    /**
     * Testa o método hashCode.
     */
    @Test
    public void hashCodeTest2() {
        NetworkPoint networkPoint = new NetworkPoint("CT1");

        int result = networkPoint.hashCode();
        int expResult = 1;

        assertNotEquals(expResult, result);
    }
}
