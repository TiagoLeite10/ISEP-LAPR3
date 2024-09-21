package testHelper;

import model.Basket;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Classe para criar métodos que validam mapas de um dado padrão e tipos de dados.
 */
public class TestMapsHelper {

    /**
     * Método para verificar se dois mapas com o formato Map<Integer, Map<V, Basket>>, têm a mesma informação.
     *
     * @param expectedMap Valores expectáveis que o mapa deve ter.
     * @param actualMap   Os verdadeiros valores que o mapa tem.
     * @param <V>         Um dado tipo que será Costumer ou AgriculturalProducer.
     */
    public static <V> void testProductsAvailableOrRequestedMaps(Map<Integer, Map<V, Basket>> expectedMap, Map<Integer, Map<V, Basket>> actualMap) {
        // Verifica se ambos os mapas contem os mesmos dias como chave
        assertEquals(expectedMap.keySet(), actualMap.keySet());
        for (Integer i : actualMap.keySet()) {
            // Verifica se ambos os mapas num dado dia contem os mesmos valores como chave
            assertEquals(expectedMap.get(i).keySet(), actualMap.get(i).keySet());
            for (V entety : actualMap.get(i).keySet()) {
                Basket basketOne = expectedMap.get(i).get(entety);
                Basket basketTwo = actualMap.get(i).get(entety);
                // Verifica se os cabazes contêm a mesma informação
                assertEquals(basketOne, basketTwo);
            }
        }
    }

}
