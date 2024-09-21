package model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe para realizar testes à classe CostumerStatistics.
 */
public class CostumerStatisticsTest {

    /**
     * Testa o método equals com dois objetos CostumerStatistics iguais.
     */
    @Test
    public void equalsTest1() {
        CostumerStatistics costumerStatistics1 = new CostumerStatistics(2, 1, 2);
        CostumerStatistics costumerStatistics2 = new CostumerStatistics(2, 1, 2);

        assertEquals(costumerStatistics1, costumerStatistics2);
    }

    /**
     * Testa o método equals com dois objetos CostumerStatistics diferentes.
     */
    @Test
    public void equalsTest2() {
        CostumerStatistics costumerStatistics1 = new CostumerStatistics(2, 1, 2);
        CostumerStatistics costumerStatistics2 = new CostumerStatistics(2, 1, 1);

        assertNotEquals(costumerStatistics1, costumerStatistics2);
    }

}