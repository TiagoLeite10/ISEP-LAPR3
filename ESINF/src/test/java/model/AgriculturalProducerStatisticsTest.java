package model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe para realizar testes à classe AgriculturalProducerStatistics.
 */
public class AgriculturalProducerStatisticsTest {

    /**
     * Testa o método equals com dois objetos AgriculturalProducerStatistics iguais.
     */
    @Test
    public void equalsTest1() {
        AgriculturalProducerStatistics agriculturalProducerStatistics1 = new AgriculturalProducerStatistics(2, 1, 2, 1, 3);
        AgriculturalProducerStatistics agriculturalProducerStatistics2 = new AgriculturalProducerStatistics(2, 1, 2, 1, 3);

        assertEquals(agriculturalProducerStatistics1, agriculturalProducerStatistics2);
    }

    /**
     * Testa o método equals com dois objetos AgriculturalProducerStatistics diferentes.
     */
    @Test
    public void equalsTest2() {
        AgriculturalProducerStatistics agriculturalProducerStatistics1 = new AgriculturalProducerStatistics(2, 1, 2, 1, 3);
        AgriculturalProducerStatistics agriculturalProducerStatistics2 = new AgriculturalProducerStatistics(2, 1, 2, 1, 2);

        assertNotEquals(agriculturalProducerStatistics1, agriculturalProducerStatistics2);
    }
}
