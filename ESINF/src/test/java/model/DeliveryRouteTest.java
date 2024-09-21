package model;

import dataStructure.graph.matrix.MatrixGraph;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Classe para realizar testes à classe DeliveryRoute.
 */
public class DeliveryRouteTest {

    /**
     * Caminho do ficheiro pequeno com as distâncias
     */
    private final String FILE_PATH_DISTANCES_SMALL = "files/small/distancias_small.csv";

    /**
     * Caminho do ficheiro pequeno com as informações sobre clientes-produtores
     */
    private final String FILE_PATH_PRODUCER_COSTUMER_SMALL = "files/small/clientes-produtores_small.csv";

    /**
     * Testa o método getBasketsDeliveredHub para verificar se a lista com os cabazes a ser entregues num hub encontra-se
     * correta.
     */
    @Test
    public void getBasketsDeliveredHubTest1() {
        DistributionNetwork distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);

        LinkedList<NetworkPoint> waypoints = new LinkedList<>();
        waypoints.add(new NetworkPoint("CT17"));
        waypoints.add(new NetworkPoint("CT6"));
        waypoints.add(new NetworkPoint("CT10"));
        waypoints.add(new NetworkPoint("CT4"));
        waypoints.add(new NetworkPoint("CT5"));
        waypoints.add(new NetworkPoint("CT9"));
        waypoints.add(new NetworkPoint("CT11"));
        waypoints.add(new NetworkPoint("CT14"));

        int totalDistance = 1179654;

        MatrixGraph<NetworkPoint, Integer> minDistDeliveryRoute = distributionNetwork.getMinDistDistributionNetwork();

        ParticularCostumer particularCostumerOne = new ParticularCostumer("CT1", 40.6389, -8.6553, "C1");
        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");
        AgriculturalProducer p2 = new AgriculturalProducer("CT6", 40.2111, -8.4291, "P2");
        int day = 1;
        Product productOne = new Product("Prod1");
        Product productTwo = new Product("Prod2");
        Product productThree = new Product("Prod3");

        ProductProvided productProvidedCostumerOne1 = new ProductProvided(productOne, 10, 10, p1);
        ProductProvided productProvidedCostumerOne2 = new ProductProvided(productTwo, 20, 10, p1);
        ProductProvided productProvidedCostumerOne3 = new ProductProvided(productThree, 15, 15, p2);
        BasketExpedition basketExpedition = new BasketExpedition(particularCostumerOne, day);
        basketExpedition.addProductProvided(productProvidedCostumerOne1);
        basketExpedition.addProductProvided(productProvidedCostumerOne2);
        basketExpedition.addProductProvided(productProvidedCostumerOne3);

        CompanyCostumer hub = new CompanyCostumer("CT14", 38.5243, -8.8926, "E1");

        Map<CompanyCostumer, Set<BasketExpedition>> basketsDelivered = new HashMap<>();
        Set<BasketExpedition> basketExpeditionSet = new HashSet<>();
        basketExpeditionSet.add(basketExpedition);
        basketsDelivered.put(hub, basketExpeditionSet);

        DeliveryRoute deliveryRoute = new DeliveryRoute(waypoints, basketsDelivered, totalDistance, minDistDeliveryRoute);

        Set<BasketExpedition> basketExpeditionResult = deliveryRoute.getBasketsDeliveredHub(hub);

        Set<BasketExpedition> basketExpeditionExpected = new HashSet<>();
        basketExpeditionExpected.add(basketExpedition);

        assertEquals(basketExpeditionExpected, basketExpeditionResult);
    }

    /**
     * Testa o método distanceBetweenTwoWaypoints para verificar se a distância entre dois pontos de passagem do percurso
     * encontra-se correta.
     */
    @Test
    public void distanceBetweenTwoWaypointsTest1() {
        DistributionNetwork distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);

        LinkedList<NetworkPoint> waypoints = new LinkedList<>();
        waypoints.add(new NetworkPoint("CT17"));
        waypoints.add(new NetworkPoint("CT6"));
        waypoints.add(new NetworkPoint("CT10"));
        waypoints.add(new NetworkPoint("CT4"));
        waypoints.add(new NetworkPoint("CT5"));
        waypoints.add(new NetworkPoint("CT9"));
        waypoints.add(new NetworkPoint("CT11"));
        waypoints.add(new NetworkPoint("CT14"));

        int totalDistance = 1179654;

        MatrixGraph<NetworkPoint, Integer> minDistDeliveryRoute = distributionNetwork.getMinDistDistributionNetwork();

        ParticularCostumer particularCostumerOne = new ParticularCostumer("CT1", 40.6389, -8.6553, "C1");
        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");
        AgriculturalProducer p2 = new AgriculturalProducer("CT6", 40.2111, -8.4291, "P2");
        int day = 1;
        Product productOne = new Product("Prod1");
        Product productTwo = new Product("Prod2");
        Product productThree = new Product("Prod3");

        ProductProvided productProvidedCostumerOne1 = new ProductProvided(productOne, 10, 10, p1);
        ProductProvided productProvidedCostumerOne2 = new ProductProvided(productTwo, 20, 10, p1);
        ProductProvided productProvidedCostumerOne3 = new ProductProvided(productThree, 15, 15, p2);
        BasketExpedition basketExpedition = new BasketExpedition(particularCostumerOne, day);
        basketExpedition.addProductProvided(productProvidedCostumerOne1);
        basketExpedition.addProductProvided(productProvidedCostumerOne2);
        basketExpedition.addProductProvided(productProvidedCostumerOne3);

        CompanyCostumer hub = new CompanyCostumer("CT14", 38.5243, -8.8926, "E1");

        Map<CompanyCostumer, Set<BasketExpedition>> basketsDelivered = new HashMap<>();
        Set<BasketExpedition> basketExpeditionSet = new HashSet<>();
        basketExpeditionSet.add(basketExpedition);
        basketsDelivered.put(hub, basketExpeditionSet);

        DeliveryRoute deliveryRoute = new DeliveryRoute(waypoints, basketsDelivered, totalDistance, minDistDeliveryRoute);

        int deliveryRouteResult = deliveryRoute.distanceBetweenTwoWaypoints(new NetworkPoint("CT17"), new NetworkPoint("CT9"));
        int deliveryRouteExpected = 62879;

        assertEquals(deliveryRouteExpected, deliveryRouteResult);
    }

    /**
     * Testa o método distanceBetweenTwoWaypoints para verificar se a distância entre dois pontos de passagem do percurso
     * encontra-se correta.
     */
    @Test
    public void distanceBetweenTwoWaypointsTest2() {
        DistributionNetwork distributionNetwork = new DistributionNetwork();
        distributionNetwork.buildDistributionNetwork(FILE_PATH_PRODUCER_COSTUMER_SMALL, FILE_PATH_DISTANCES_SMALL);

        LinkedList<NetworkPoint> waypoints = new LinkedList<>();
        waypoints.add(new NetworkPoint("CT17"));
        waypoints.add(new NetworkPoint("CT6"));
        waypoints.add(new NetworkPoint("CT10"));
        waypoints.add(new NetworkPoint("CT4"));
        waypoints.add(new NetworkPoint("CT5"));
        waypoints.add(new NetworkPoint("CT9"));
        waypoints.add(new NetworkPoint("CT11"));
        waypoints.add(new NetworkPoint("CT14"));

        int totalDistance = 1179654;

        MatrixGraph<NetworkPoint, Integer> minDistDeliveryRoute = distributionNetwork.getMinDistDistributionNetwork();

        ParticularCostumer particularCostumerOne = new ParticularCostumer("CT1", 40.6389, -8.6553, "C1");
        AgriculturalProducer p1 = new AgriculturalProducer("CT17", 40.6667, -7.9167, "P1");
        AgriculturalProducer p2 = new AgriculturalProducer("CT6", 40.2111, -8.4291, "P2");
        int day = 1;
        Product productOne = new Product("Prod1");
        Product productTwo = new Product("Prod2");
        Product productThree = new Product("Prod3");

        ProductProvided productProvidedCostumerOne1 = new ProductProvided(productOne, 10, 10, p1);
        ProductProvided productProvidedCostumerOne2 = new ProductProvided(productTwo, 20, 10, p1);
        ProductProvided productProvidedCostumerOne3 = new ProductProvided(productThree, 15, 15, p2);
        BasketExpedition basketExpedition = new BasketExpedition(particularCostumerOne, day);
        basketExpedition.addProductProvided(productProvidedCostumerOne1);
        basketExpedition.addProductProvided(productProvidedCostumerOne2);
        basketExpedition.addProductProvided(productProvidedCostumerOne3);

        CompanyCostumer hub = new CompanyCostumer("CT14", 38.5243, -8.8926, "E1");

        Map<CompanyCostumer, Set<BasketExpedition>> basketsDelivered = new HashMap<>();
        Set<BasketExpedition> basketExpeditionSet = new HashSet<>();
        basketExpeditionSet.add(basketExpedition);
        basketsDelivered.put(hub, basketExpeditionSet);

        DeliveryRoute deliveryRoute = new DeliveryRoute(waypoints, basketsDelivered, totalDistance, minDistDeliveryRoute);

        int deliveryRouteResult = deliveryRoute.distanceBetweenTwoWaypoints(new NetworkPoint("CT4"), new NetworkPoint("CT9"));
        int deliveryRouteExpected = 162527;

        assertEquals(deliveryRouteExpected, deliveryRouteResult);
    }

    /**
     * Testa o método equals com dois objetos DeliveryRoute iguais.
     */
    @Test
    public void equalsTest1() {
        LinkedList<NetworkPoint> waypoints1 = new LinkedList<>();
        waypoints1.add(new NetworkPoint("CT17"));
        waypoints1.add(new NetworkPoint("CT6"));
        waypoints1.add(new NetworkPoint("CT10"));

        int totalDistance1 = 1179654;

        LinkedList<NetworkPoint> waypoints2 = new LinkedList<>();
        waypoints2.add(new NetworkPoint("CT17"));
        waypoints2.add(new NetworkPoint("CT6"));
        waypoints2.add(new NetworkPoint("CT10"));

        int totalDistance2 = 1179654;

        DeliveryRoute deliveryRoute1 = new DeliveryRoute(waypoints1, totalDistance1);
        DeliveryRoute deliveryRoute2 = new DeliveryRoute(waypoints2, totalDistance2);

        assertEquals(deliveryRoute1, deliveryRoute2);
    }

    /**
     * Testa o método equals com dois objetos DeliveryRoute diferentes.
     */
    @Test
    public void equalsTest2() {
        LinkedList<NetworkPoint> waypoints1 = new LinkedList<>();
        waypoints1.add(new NetworkPoint("CT17"));
        waypoints1.add(new NetworkPoint("CT6"));
        waypoints1.add(new NetworkPoint("CT10"));

        int totalDistance1 = 1179654;

        LinkedList<NetworkPoint> waypoints2 = new LinkedList<>();
        waypoints2.add(new NetworkPoint("CT17"));
        waypoints2.add(new NetworkPoint("CT8"));
        waypoints2.add(new NetworkPoint("CT11"));

        int totalDistance2 = 1172254;

        DeliveryRoute deliveryRoute1 = new DeliveryRoute(waypoints1, totalDistance1);
        DeliveryRoute deliveryRoute2 = new DeliveryRoute(waypoints2, totalDistance2);

        assertNotEquals(deliveryRoute1, deliveryRoute2);
    }
}