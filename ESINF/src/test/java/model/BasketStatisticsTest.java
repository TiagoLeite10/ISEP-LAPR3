package model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe para realizar testes à classe BasketStatistics.
 */
public class BasketStatisticsTest {

    /**
     * Testa o método fullySatisfiedBasket com um cabaz totalmente satisfeito.
     */
    @Test
    public void fullySatisfiedBasketTest1() {
        BasketStatistics basketStatistics = new BasketStatistics(1, 0, 0, 1);

        boolean result = basketStatistics.fullySatisfiedBasket();

        assertTrue(result);
    }

    /**
     * Testa o método fullySatisfiedBasket com um cabaz que não é totalmente satisfeito.
     */
    @Test
    public void fullySatisfiedBasketTest2() {
        BasketStatistics basketStatistics = new BasketStatistics(1, 2, 0, 1);

        boolean result = basketStatistics.fullySatisfiedBasket();

        assertFalse(result);
    }

    /**
     * Testa o método fullySatisfiedBasket com um cabaz parcialmente satisfeito.
     */
    @Test
    public void partiallySatisfiedBasketTest1() {
        BasketStatistics basketStatistics = new BasketStatistics(1, 2, 3, 1);

        boolean result = basketStatistics.partiallySatisfiedBasket();

        assertTrue(result);
    }

    /**
     * Testa o método fullySatisfiedBasket com um cabaz que não é parcialmente satisfeito.
     */
    @Test
    public void partiallySatisfiedBasketTest2() {
        BasketStatistics basketStatistics = new BasketStatistics(1, 0, 0, 1);

        boolean result = basketStatistics.partiallySatisfiedBasket();

        assertFalse(result);
    }

    /**
     * Testa o método equals com dois objetos BasketStatistics iguais.
     */
    @Test
    public void equalsTest1() {
        BasketStatistics basketStatistics1 = new BasketStatistics(1, 2, 0, 1);
        BasketStatistics basketStatistics2 = new BasketStatistics(1, 2, 0, 1);

        assertEquals(basketStatistics1, basketStatistics2);
    }

    /**
     * Testa o método equals com dois objetos BasketStatistics diferentes.
     */
    @Test
    public void equalsTest2() {
        BasketStatistics basketStatistics1 = new BasketStatistics(1, 2, 0, 1);
        BasketStatistics basketStatistics2 = new BasketStatistics(0, 5, 0, 2);

        assertNotEquals(basketStatistics1, basketStatistics2);
    }
}
