package dataStructure.graph;

import dataStructure.graph.matrix.MatrixGraph;
import utils.Graph;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.BinaryOperator;

/**
 * @author DEI-ISEP
 */
public class Algorithms {

    /**
     * Performs breadth-first search of a Graph starting in a vertex
     *
     * @param g    Graph instance
     * @param vert vertex that will be the source of the search
     * @return a LinkedList with the vertices of breadth-first search
     */
    public static <V, E> LinkedList<V> BreadthFirstSearch(Graph<V, E> g, V vert) {
        if (!g.validVertex(vert))
            return null;

        boolean[] visited = new boolean[g.numVertices()];

        LinkedList<V> qbfs = new LinkedList<>();
        qbfs.add(vert);

        LinkedList<V> qaux = new LinkedList<>();
        qaux.add(vert);

        visited[g.key(vert)] = true;

        while (!qaux.isEmpty()) {
            vert = qaux.removeFirst();
            for (V vAdj : g.adjVertices(vert)) {
                int vAdjKey = g.key(vAdj);
                if (!visited[vAdjKey]) {
                    qbfs.add(vAdj);
                    qaux.add(vAdj);
                    visited[vAdjKey] = true;
                }
            }
        }

        return qbfs;
    }

    /**
     * Performs depth-first search starting in a vertex
     *
     * @param g       Graph instance
     * @param vOrig   vertex of graph g that will be the source of the search
     * @param visited set of previously visited vertices
     * @param qdfs    return LinkedList with vertices of depth-first search
     */
    private static <V, E> void DepthFirstSearch(Graph<V, E> g, V vOrig, boolean[] visited, LinkedList<V> qdfs) {
        int vOrigKey = g.key(vOrig);
        if (visited[vOrigKey])
            return;

        qdfs.add(vOrig);

        visited[vOrigKey] = true;

        for (V vAdj : g.adjVertices(vOrig)) {
            DepthFirstSearch(g, vAdj, visited, qdfs);
        }
    }

    /**
     * Performs depth-first search starting in a vertex
     *
     * @param g    Graph instance
     * @param vert vertex of graph g that will be the source of the search
     * @return a LinkedList with the vertices of depth-first search
     */
    public static <V, E> LinkedList<V> DepthFirstSearch(Graph<V, E> g, V vert) {
        if (!g.validVertex(vert))
            return null;

        LinkedList<V> qdfs = new LinkedList<>();
        boolean[] visited = new boolean[g.numVertices()];

        DepthFirstSearch(g, vert, visited, qdfs);
        return qdfs;
    }

    /**
     * Returns all paths from vOrig to vDest
     *
     * @param g       Graph instance
     * @param vOrig   Vertex that will be the source of the path
     * @param vDest   Vertex that will be the end of the path
     * @param visited set of discovered vertices
     * @param path    stack with vertices of the current path (the path is in reverse order)
     * @param paths   ArrayList with all the paths (in correct order)
     */
    private static <V, E> void allPaths(Graph<V, E> g, V vOrig, V vDest, boolean[] visited,
                                        LinkedList<V> path, ArrayList<LinkedList<V>> paths) {

        path.add(vOrig);
        visited[g.key(vOrig)] = true;

        for (V vAdj : g.adjVertices(vOrig)) {
            if (vAdj.equals(vDest)) {
                path.add(vDest);
                paths.add(path);
                path.removeLast();
            } else {
                if (!visited[g.key(vAdj)])
                    allPaths(g, vAdj, vDest, visited, path, paths);
            }
        }

        path.removeLast();

    }

    /**
     * Returns all paths from vOrig to vDest
     *
     * @param g     Graph instance
     * @param vOrig information of the Vertex origin
     * @param vDest information of the Vertex destination
     * @return paths ArrayList with all paths from vOrig to vDest
     */
    public static <V, E> ArrayList<LinkedList<V>> allPaths(Graph<V, E> g, V vOrig, V vDest) {
        if (!g.validVertex(vOrig) || !g.validVertex(vDest))
            return null;

        ArrayList<LinkedList<V>> paths = new ArrayList<>();
        boolean[] visited = new boolean[g.numVertices()];

        LinkedList<V> path = new LinkedList<>();
        allPaths(g, vOrig, vDest, visited, path, paths);
        return paths;
    }

    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with non-negative edge weights
     * This implementation uses Dijkstra's algorithm
     *
     * @param g        Graph instance
     * @param vOrig    Vertex that will be the source of the path
     * @param visited  set of previously visited vertices
     * @param pathKeys minimum path vertices keys
     * @param dist     minimum distances
     */
    private static <V, E> void shortestPathDijkstra(Graph<V, E> g, V vOrig,
                                                    Comparator<E> ce, BinaryOperator<E> sum, E zero,
                                                    boolean[] visited, V[] pathKeys, E[] dist) {

        int vKey = g.key(vOrig);
        dist[vKey] = zero;
        pathKeys[vKey] = vOrig;

        while (vOrig != null) {
            int keyVOrig = g.key(vOrig);
            visited[keyVOrig] = true;
            for (Edge<V, E> edge : g.outgoingEdges(vOrig)) {
                int keyVAdj = g.key(edge.getVDest());
                E sumLength = sum.apply(dist[keyVOrig], edge.getWeight());
                if (!visited[keyVAdj] && (dist[keyVAdj] == null || ce.compare(dist[keyVAdj], sumLength) > 0)) {
                    dist[keyVAdj] = sumLength;
                    pathKeys[keyVAdj] = vOrig;
                }
            }

            E minDist = null;
            vOrig = null;
            for (V vertex : g.vertices()) {
                int vertexKey = g.key(vertex);
                if (!visited[vertexKey] && dist[vertexKey] != null && (minDist == null || ce.compare(dist[vertexKey], minDist) < 0)) {
                    minDist = dist[vertexKey];
                    vOrig = vertex;
                }
            }
        }

    }

    /**
     * Shortest-path between two vertices
     *
     * @param g         graph
     * @param vOrig     origin vertex
     * @param vDest     destination vertex
     * @param ce        comparator between elements of type E
     * @param sum       sum two elements of type E
     * @param zero      neutral element of the sum in elements of type E
     * @param shortPath returns the vertices which make the shortest path
     * @return if vertices exist in the graph and are connected, true, false otherwise
     */
    public static <V, E> E shortestPath(Graph<V, E> g, V vOrig, V vDest,
                                        Comparator<E> ce, BinaryOperator<E> sum, E zero,
                                        LinkedList<V> shortPath) {

        if (!g.validVertex(vOrig) || !g.validVertex(vDest))
            return null;

        shortPath.clear();

        boolean[] visited = new boolean[g.numVertices()];
        V[] pathKeys;
        E[] dist;

        if (vOrig.getClass().getSuperclass().getSuperclass() != null)
            pathKeys = (V[]) Array.newInstance(vOrig.getClass().getSuperclass().getSuperclass(), g.numVertices());
        else
            pathKeys = (V[]) Array.newInstance(vOrig.getClass().getSuperclass(), g.numVertices());

        if (zero.getClass().getSuperclass().getSuperclass() != null)
            dist = (E[]) Array.newInstance(zero.getClass().getSuperclass().getSuperclass(), g.numVertices());
        else
            dist = (E[]) Array.newInstance(zero.getClass().getSuperclass(), g.numVertices());

        shortestPathDijkstra(g, vOrig, ce, sum, zero, visited, pathKeys, dist);

        E lengthToReachDest = dist[g.key(vDest)];
        if (lengthToReachDest == null)
            return null;

        getPath(g, vOrig, vDest, pathKeys, shortPath);

        return lengthToReachDest;
    }

    /**
     * Shortest-path between a vertex and all other vertices
     *
     * @param g     graph
     * @param vOrig start vertex
     * @param ce    comparator between elements of type E
     * @param sum   sum two elements of type E
     * @param zero  neutral element of the sum in elements of type E
     * @param paths returns all the minimum paths
     * @param dists returns the corresponding minimum distances
     * @return if vOrig exists in the graph true, false otherwise
     */
    public static <V, E> boolean shortestPaths(Graph<V, E> g, V vOrig,
                                               Comparator<E> ce, BinaryOperator<E> sum, E zero,
                                               ArrayList<LinkedList<V>> paths, ArrayList<E> dists) {
        if (!g.validVertex(vOrig))
            return false;

        dists.clear();
        paths.clear();

        int numVertices = g.numVertices();

        for (int num = 0; num < numVertices; num++) {
            dists.add(null);
            paths.add(null);
        }

        boolean[] visited = new boolean[numVertices];

        V[] pathKeys;
        E[] dist;

        if (vOrig.getClass().getSuperclass().getSuperclass() != null)
            pathKeys = (V[]) Array.newInstance(vOrig.getClass().getSuperclass().getSuperclass(), g.numVertices());
        else
            pathKeys = (V[]) Array.newInstance(vOrig.getClass().getSuperclass(), g.numVertices());

        if (zero.getClass().getSuperclass().getSuperclass() != null)
            dist = (E[]) Array.newInstance(zero.getClass().getSuperclass().getSuperclass(), g.numVertices());
        else
            dist = (E[]) Array.newInstance(zero.getClass().getSuperclass(), g.numVertices());


        shortestPathDijkstra(g, vOrig, ce, sum, zero, visited, pathKeys, dist);

        for (V vDest : g.vertices()) {
            int keyVDest = g.key(vDest);
            if (dist[keyVDest] != null) {
                LinkedList<V> shortPath = new LinkedList<>();
                getPath(g, vOrig, vDest, pathKeys, shortPath);
                paths.set(keyVDest, shortPath);
                dists.set(keyVDest, dist[keyVDest]);
            }
        }

        return true;
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf
     * The path is constructed from the end to the beginning
     *
     * @param g        Graph instance
     * @param vOrig    information of the Vertex origin
     * @param vDest    information of the Vertex destination
     * @param pathKeys minimum path vertices keys
     * @param path     stack with the minimum path (correct order)
     */
    private static <V, E> void getPath(Graph<V, E> g, V vOrig, V vDest,
                                       V[] pathKeys, LinkedList<V> path) {

        if (vOrig.equals(vDest))
            path.push(vDest);
        else {
            path.push(vDest);
            int keyVDest = g.key(vDest);
            vDest = pathKeys[keyVDest];
            getPath(g, vOrig, vDest, pathKeys, path);
        }
    }


    /**
     * Calculates the minimum distance graph using Floyd-Warshall
     *
     * @param g   initial graph
     * @param ce  comparator between elements of type E
     * @param sum sum two elements of type E
     * @return the minimum distance graph
     */
    public static <V, E> MatrixGraph<V, E> minDistGraph(Graph<V, E> g, Comparator<E> ce, BinaryOperator<E> sum) {
        int numVerts = g.numVertices();
        if (numVerts == 0)
            return null;

        @SuppressWarnings("unchecked")
        E[][] mat = (E[][]) new Object[numVerts][numVerts];
        for (int i = 0; i < numVerts; i++) {
            for (int j = 0; j < numVerts; j++) {
                Edge<V, E> edge = g.edge(i, j);
                if (edge != null)
                    mat[i][j] = edge.getWeight();
            }
        }

        for (int k = 0; k < numVerts; k++) {
            for (int i = 0; i < numVerts; i++) {
                if (i != k && mat[i][k] != null) {
                    for (int j = 0; j < numVerts; j++) {
                        if (j != k && j != i && mat[k][j] != null) {
                            E s = sum.apply(mat[i][k], mat[k][j]);
                            if (mat[i][j] == null || ce.compare(mat[i][j], s) > 0) {
                                mat[i][j] = s;
                            }
                        }
                    }
                }
            }
        }
        return new MatrixGraph<>(g.isDirected(), g.vertices(), mat);
    }

    /**
     * Kruskal's algorithm finds a minimum spanning forest of an undirected edge-weighted graph.
     *
     * @param g Graph instance
     * @return Minimum spanning tree.
     */
    public static <V, E> Graph<V, E> kruskal(Graph<V, E> g) {
        Graph<V, E> minimumSpanningTree = new MatrixGraph<>(false);
        LinkedList<Edge<V, E>> listEdges = new LinkedList<>();

        for (V vertex : g.vertices()) {
            minimumSpanningTree.addVertex(vertex);
        }

        for (Edge<V, E> edge : g.edges()) {
            listEdges.add(edge);
        }

        Collections.sort(listEdges, new Edge.weightComparator());

        LinkedList<V> connectedVertex;
        for (Edge<V, E> edge : listEdges) {
            connectedVertex = DepthFirstSearch(minimumSpanningTree, edge.getVOrig());

            if (!connectedVertex.contains(edge.getVDest())) {
                minimumSpanningTree.addEdge(edge.getVOrig(), edge.getVDest(), edge.getWeight());
            }
        }

        return minimumSpanningTree;
    }

    /**
     * Method to check if the graph is connected, doing a Depth First Search
     *
     * @param g Graph instance
     * @return true if connected, false if not.
     */
    public static <V, E> boolean isConnected(Graph<V, E> g) {

        int vertices = g.vertices().size();
        boolean[] visited = new boolean[vertices];

        V firstVertice = g.vertices().get(0);
        LinkedList<V> qdfs = new LinkedList<>();

        DepthFirstSearch(g, firstVertice, visited, qdfs);

        boolean connected = true;

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                connected = false;
                break;
            }
        }

        return connected;
    }

    /**
     * Calculate the longest distance chart, first using the Floyd-Warshall algorithm and then
     * selecting the largest value returned by that algorithm
     *
     * @param g    initial graph
     * @param comp comparator between elements of type E
     * @param sum  sum two elements of type E
     * @return the greatest minimum distance of the graph
     */
    public static <V, E> E graphDiameter(Graph<V, E> g, Comparator<E> comp, BinaryOperator<E> sum, E zero) {
        E max = zero;
        MatrixGraph<V, E> result = minDistGraph(g, comp, sum);
        int numVerts = result.numVerts;

        for (int i = 0; i < numVerts; i++) {
            for (int j = 0; j < numVerts; j++) {
                if (result.edge(i, j) != null && comp.compare(result.edge(i, j).getWeight(), max) > 0) {
                    max = result.edge(i, j).getWeight();
                }
            }
        }
        return max;

    }


}