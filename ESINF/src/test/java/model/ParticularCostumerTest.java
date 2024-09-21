package model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe para realizar testes à classe ParticularCostumer.
 */
public class ParticularCostumerTest {

    /**
     * Testa o método equals com dois objetos ParticularCostumer iguais.
     */
    @Test
    public void equalsTest1() {
        ParticularCostumer particularCostumer1 = new ParticularCostumer("CT1", 40.6389, -8.6553, "C1");
        ParticularCostumer particularCostumer2 = new ParticularCostumer("CT1", 40.6389, -8.6553, "C1");

        assertEquals(particularCostumer1, particularCostumer2);
    }

    /**
     * Testa o método equals com dois objetos ParticularCostumer diferentes.
     */
    @Test
    public void equalsTest2() {
        ParticularCostumer particularCostumer1 = new ParticularCostumer("CT1", 40.6389, -8.6553, "C1");
        ParticularCostumer particularCostumer2 = new ParticularCostumer("CT2", 38.0333, -7.8833, "C2");

        assertNotEquals(particularCostumer1, particularCostumer2);
    }

    /**
     * Testa o método hashCode.
     */
    @Test
    public void hashCodeTest1() {
        ParticularCostumer particularCostumer = new ParticularCostumer("CT1", 40.6389, -8.6553, "C1");

        int result = particularCostumer.hashCode();
        int expResult = 67040;

        assertEquals(expResult, result);
    }

    /**
     * Testa o método hashCode.
     */
    @Test
    public void hashCodeTest2() {
        ParticularCostumer particularCostumer = new ParticularCostumer("CT1", 40.6389, -8.6553, "C1");

        int result = particularCostumer.hashCode();
        int expResult = 1;

        assertNotEquals(expResult, result);
    }
}
