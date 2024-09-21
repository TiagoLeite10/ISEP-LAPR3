package model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe para realizar testes à classe CompanyCostumer.
 */
public class CompanyCostumerTest {

    /**
     * Testa o método equals com dois objetos CompanyCostumer iguais.
     */
    @Test
    public void equalsTest1() {
        CompanyCostumer companyCostumer1 = new CompanyCostumer("CT14", 38.5243, -8.8926, "E1");
        CompanyCostumer companyCostumer2 = new CompanyCostumer("CT14", 38.5243, -8.8926, "E1");

        assertEquals(companyCostumer1, companyCostumer2);
    }

    /**
     * Testa o método equals com dois objetos CompanyCostumer diferentes.
     */
    @Test
    public void equalsTest2() {
        CompanyCostumer companyCostumer1 = new CompanyCostumer("CT11", 39.3167, -7.4167, "E2");
        CompanyCostumer companyCostumer2 = new CompanyCostumer("CT14", 38.5243, -8.8926, "E1");

        assertNotEquals(companyCostumer1, companyCostumer2);
    }

    /**
     * Testa o método hashCode.
     */
    @Test
    public void hashCodeTest1() {
        CompanyCostumer companyCostumer = new CompanyCostumer("CT14", 38.5243, -8.8926, "E1");

        int result = companyCostumer.hashCode();
        int expResult = 2078292;

        assertEquals(expResult, result);
    }

    /**
     * Testa o método hashCode.
     */
    @Test
    public void hashCodeTest2() {
        CompanyCostumer companyCostumer = new CompanyCostumer("CT14", 38.5243, -8.8926, "E1");

        int result = companyCostumer.hashCode();
        int expResult = 1;

        assertNotEquals(expResult, result);
    }
}
