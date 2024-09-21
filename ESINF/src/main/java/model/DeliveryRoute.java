package model;

import dataStructure.graph.matrix.MatrixGraph;

import java.util.*;

/**
 * Classe que representa uma rota de entrega.
 */
public class DeliveryRoute {

    /**
     * Lista com os pontos de passagem do percurso (produtores e hubs).
     */
    private LinkedList<NetworkPoint> waypoints;

    /**
     * Mapa que contêm os cabazes entregues em cada hub.
     */
    private Map<CompanyCostumer, Set<BasketExpedition>> basketsDelivered;

    /**
     * Distância total entre todos os pontos do percurso.
     */
    private int totalDistance;

    /**
     * Grafo com a distância mínima dos pontos de passagem do percurso.
     */
    private MatrixGraph<NetworkPoint, Integer> minDistDeliveryRoute;

    /**
     * Construtor para inicializar uma nova instância desta classe com os atributos waypoints, basketsDelivered,
     * unsatisfiedProducts e totalDistance.
     *
     * @param waypoints            Lista com os pontos de passagem do percurso (produtores e hubs).
     * @param basketsDelivered     Mapa que contêm os cabazes entregues em cada hub.
     * @param totalDistance        Distância total entre todos os pontos do percurso.
     * @param minDistDeliveryRoute Grafo com a distância mínima dos pontos de passagem do percurso.
     */
    public DeliveryRoute(LinkedList<NetworkPoint> waypoints, Map<CompanyCostumer, Set<BasketExpedition>> basketsDelivered, int totalDistance, MatrixGraph<NetworkPoint, Integer> minDistDeliveryRoute) {
        this.waypoints = waypoints;
        this.basketsDelivered = basketsDelivered;
        this.totalDistance = totalDistance;
        this.minDistDeliveryRoute = minDistDeliveryRoute;
    }

    /**
     * Construtor para inicializar uma nova instância desta classe com os atributos waypoints e totalDistance.
     *
     * @param waypoints     Lista com os pontos de passagem do percurso (produtores e hubs).
     * @param totalDistance Distância total entre todos os pontos do percurso.
     */
    public DeliveryRoute(LinkedList<NetworkPoint> waypoints, int totalDistance) {
        this.waypoints = waypoints;
        this.totalDistance = totalDistance;
    }

    /**
     * Função para retornar uma lista com os cabazes a ser entregues num hub.
     *
     * @param hub Hub.
     * @return Lista com os cabazes a ser entregues num hub.
     */
    public Set<BasketExpedition> getBasketsDeliveredHub(CompanyCostumer hub) {
        return basketsDelivered.get(hub);
    }

    /**
     * Função que retorna a distância entre dois pontos de passagem do percurso.
     *
     * @param point1 Ponto 1 de passagem do percurso.
     * @param point2 Ponto 2 de passagem do percurso.
     * @return Distância entre dois pontos de passagem do percurso.
     */
    public int distanceBetweenTwoWaypoints(NetworkPoint point1, NetworkPoint point2) {
        if (waypoints.contains(point1) && waypoints.contains(point1)) {
            return minDistDeliveryRoute.edge(point1, point2).getWeight();
        } else {
            throw new IllegalArgumentException("O ponto indicado não pertence à passagem do percurso.");
        }
    }

    /**
     * Método para verificar se este objeto atual é igual a outro objeto do mesmo tipo.
     *
     * @param obj O objeto a ser comparado com este atual.
     * @return true se o outro objeto representa uma rota de entrega igual a esta, caso contrário devolve false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        DeliveryRoute otherDeliveryRoute = (DeliveryRoute) obj;

        return (waypoints.equals(otherDeliveryRoute.waypoints) &&
                totalDistance == otherDeliveryRoute.totalDistance);
    }
}
