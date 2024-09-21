package utils;

import dataStructure.graph.matrix.MatrixGraph;
import model.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe para realizar testes à classe FileToGraph.
 */
public class FileToGraphTest {

    /**
     * Testa o método readFileToVertex para verificar se os dados de um dado ficheiro cliente-produtores foram adicionados
     * corretamente aos vértices de um determinado grafo.
     */
    @Test
    public void fileToVertexTest1() {
        String filePathTest = "files/test/clientes-produtores_small_test1.csv";

        Graph<NetworkPoint, Integer> result = new MatrixGraph<>(true);
        FileToGraph.readFileToVertex(result, filePathTest);

        Graph<NetworkPoint, Integer> expectedResult = new MatrixGraph<>(true);
        expectedResult.addVertex(new ParticularCostumer("CT1", 40.6389, -8.6553, "C1"));
        expectedResult.addVertex(new ParticularCostumer("CT2", 38.0333, -7.8833, "C2"));
        expectedResult.addVertex(new ParticularCostumer("CT3", 41.5333, -8.4167, "C3"));
        expectedResult.addVertex(new CompanyCostumer("CT14", 38.5243, -8.8926, "E1"));
        expectedResult.addVertex(new CompanyCostumer("CT11", 39.3167, -7.4167, "E2"));
        expectedResult.addVertex(new CompanyCostumer("CT5", 39.823, -7.4931, "E3"));
        expectedResult.addVertex(new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1"));
        expectedResult.addVertex(new AgriculturalProducer("CT6", 40.2111, -8.4291, "P2"));
        expectedResult.addVertex(new AgriculturalProducer("CT10", 39.7444, -8.8072, "P3"));

        assertEquals(expectedResult, result);

    }

    /**
     * Testa o método readFileToVertex para verificar se os dados duplicados de um dado ficheiro cliente-produtores foram
     * adicionados corretamente aos vértices de um determinado grafo.
     */
    @Test
    public void fileToVertexTest2() {
        String filePathTest = "files/test/clientes-produtores_small_test2.csv";

        Graph<NetworkPoint, Integer> result = new MatrixGraph<>(true);
        FileToGraph.readFileToVertex(result, filePathTest);

        Graph<NetworkPoint, Integer> expectedResult = new MatrixGraph<>(true);
        expectedResult.addVertex(new ParticularCostumer("CT1", 40.6389, -8.6553, "C1"));
        expectedResult.addVertex(new ParticularCostumer("CT2", 38.0333, -7.8833, "C2"));
        expectedResult.addVertex(new ParticularCostumer("CT3", 41.5333, -8.4167, "C3"));

        assertEquals(expectedResult, result);
    }

    /**
     * Testa o método readFileToVertex para verificar se dado um ficheiro cliente-produtores completamente vazio, os vértices
     * de um determinado grafo continuam iguais.
     */
    @Test
    public void fileToVertexTest3() {
        String filePathTest = "files/test/clientes-produtores_small_test3.csv";

        Graph<NetworkPoint, Integer> result = new MatrixGraph<>(true);
        FileToGraph.readFileToVertex(result, filePathTest);

        Graph<NetworkPoint, Integer> expectedResult = new MatrixGraph<>(true);

        assertEquals(expectedResult, result);
    }

    /**
     * Testa o método readFileToEdge para verificar se os dados de um dado ficheiro de distâncias foram adicionados corretamente
     * às arestas de um determinado grafo.
     */
    @Test
    public void fileToEdgeTest1() {
        String filePathTest = "files/test/distancias_small_test1.csv";

        Graph<NetworkPoint, Integer> result = new MatrixGraph<>(true);
        FileToGraph.readFileToEdge(result, filePathTest);

        Graph<NetworkPoint, Integer> expectedResult = new MatrixGraph<>(true);
        expectedResult.addEdge(new NetworkPoint("CT10"), new NetworkPoint("CT6"), 63448);
        expectedResult.addEdge(new NetworkPoint("CT10"), new NetworkPoint("CT4"), 67584);
        expectedResult.addEdge(new NetworkPoint("CT10"), new NetworkPoint("CT1"), 110848);
        expectedResult.addEdge(new NetworkPoint("CT10"), new NetworkPoint("CT5"), 125041);
        expectedResult.addEdge(new NetworkPoint("CT12"), new NetworkPoint("CT3"), 50467);
        expectedResult.addEdge(new NetworkPoint("CT12"), new NetworkPoint("CT1"), 62877);
        expectedResult.addEdge(new NetworkPoint("CT12"), new NetworkPoint("CT10"), 70717);
        expectedResult.addEdge(new NetworkPoint("CT11"), new NetworkPoint("CT5"), 62655);
        expectedResult.addEdge(new NetworkPoint("CT14"), new NetworkPoint("CT1"), 121584);

        assertEquals(expectedResult, result);
    }

    /**
     * Testa o método readFileToEdge para verificar se os dados duplicados de um dado ficheiro de distâncias foram
     * adicionados corretamente às arestas de um determinado grafo.
     */
    @Test
    public void fileToEdgeTest2() {
        String filePathTest = "files/test/distancias_small_test2.csv";

        Graph<NetworkPoint, Integer> result = new MatrixGraph<>(true);
        FileToGraph.readFileToEdge(result, filePathTest);

        Graph<NetworkPoint, Integer> expectedResult = new MatrixGraph<>(true);
        expectedResult.addEdge(new NetworkPoint("CT10"), new NetworkPoint("CT6"), 63448);
        expectedResult.addEdge(new NetworkPoint("CT10"), new NetworkPoint("CT4"), 67584);
        expectedResult.addEdge(new NetworkPoint("CT10"), new NetworkPoint("CT1"), 110848);

        assertEquals(expectedResult, result);
    }

    /**
     * Testa o método readFileToEdge para verificar se dado um ficheiro de distâncias completamente vazio, as arestas
     * de um determinado grafo continuam iguais.
     */
    @Test
    public void fileToEdgeTest3() {
        String filePathTest = "files/test/distancias_small_test3.csv";

        Graph<NetworkPoint, Integer> result = new MatrixGraph<>(true);
        FileToGraph.readFileToEdge(result, filePathTest);

        Graph<NetworkPoint, Integer> expectedResult = new MatrixGraph<>(true);

        assertEquals(expectedResult, result);
    }
}
