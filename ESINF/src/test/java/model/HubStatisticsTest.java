package model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe para realizar testes à classe HubStatistics.
 */
public class HubStatisticsTest {

    /**
     * Testa o método equals com dois objetos HubStatistics iguais.
     */
    @Test
    public void equalsTest1() {
        HubStatistics hubStatistics1 = new HubStatistics(2, 1);
        HubStatistics hubStatistics2 = new HubStatistics(2, 1);

        assertEquals(hubStatistics1, hubStatistics2);
    }

    /**
     * Testa o método equals com dois objetos HubStatistics diferentes.
     */
    @Test
    public void equalsTest2() {
        HubStatistics hubStatistics1 = new HubStatistics(2, 1);
        HubStatistics hubStatistics2 = new HubStatistics(2, 2);

        assertNotEquals(hubStatistics1, hubStatistics2);
    }
}
