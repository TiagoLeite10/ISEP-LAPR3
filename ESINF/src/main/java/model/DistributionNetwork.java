package model;

import dataStructure.graph.Algorithms;
import dataStructure.graph.matrix.MatrixGraph;
import utils.FileToBasket;
import utils.FileToGraph;
import utils.Graph;

import java.util.*;

/**
 * Classe que representa uma rede de distribuição.
 */
public class DistributionNetwork {

    /**
     * Contante que representa os dias máximos em que o cabaz fica disponível antes de ser eliminado.
     */
    public static final int MAX_DAYS_BEFORE_BASKET_EXPIRE = 3;

    /**
     * 'Grafo' da rede de distribuição.
     */
    private final Graph<NetworkPoint, Integer> distributionNetwork;

    /**
     * Grafo com a distância mínima dos pontos da rede de distribuição.
     */
    private MatrixGraph<NetworkPoint, Integer> minDistDistributionNetwork;

    /**
     * Map que contem os produtos disponíveis em cabazes.
     */
    private final Map<Integer, Map<AgriculturalProducer, Basket>> productsAvailable;

    /**
     * Map que contem os produtos requeridos para cabezes.
     */
    private final Map<Integer, Map<Costumer, Basket>> productsRequest;

    /**
     * Construtor para inicializar uma instância do objeto DistributionNetwork.
     */
    public DistributionNetwork() {
        this.distributionNetwork = new MatrixGraph<>(false);
        this.productsRequest = new HashMap<>();
        this.productsAvailable = new HashMap<>();
    }


    /**
     * Método para devolver um clone do 'Grafo' da rede de distribuição.
     *
     * @return 'Grafo' da rede de distribuição.
     */
    public Graph<NetworkPoint, Integer> getDistributionNetwork() {
        return distributionNetwork.clone();
    }

    /**
     * Função para retornar o grafo com a distância mínima dos pontos da rede de distribuição.
     *
     * @return Grafo com a distância mínima dos pontos da rede de distribuição.
     */
    public MatrixGraph<NetworkPoint, Integer> getMinDistDistributionNetwork() {
        return minDistDistributionNetwork;
    }

    /**
     * Método para devolver um clone do mapa com os cabazes e respetivos produtos disponíveis para colocar encomendas.
     *
     * @return Mapa com a informação acerca dos cabazes e respetivos produtos disponíveis.
     */
    public Map<Integer, Map<AgriculturalProducer, Basket>> getProductsAvailable() {

        if (this.productsAvailable.isEmpty())
            return null;

        Map<Integer, Map<AgriculturalProducer, Basket>> map = new HashMap<>();

        for (Integer day : this.productsAvailable.keySet()) {
            Map<AgriculturalProducer, Basket> agriculturalProducerBasketMap = this.productsAvailable.get(day);
            map.put(day, new HashMap<>(agriculturalProducerBasketMap));
        }

        return map;
    }

    /**
     * Método para devolver um clone do mapa com os cabazes colocados (pedidos) pelos clientes.
     *
     * @return Mapa com a informação acerca dos cabazes pedidos pelos clientes.
     */
    public Map<Integer, Map<Costumer, Basket>> getProductsRequest() {
        if (this.productsAvailable.isEmpty())
            return null;

        Map<Integer, Map<Costumer, Basket>> map = new HashMap<>();

        for (Integer day : this.productsRequest.keySet()) {
            Map<Costumer, Basket> costumerBasketMap = this.productsRequest.get(day);
            map.put(day, new HashMap<>(costumerBasketMap));
        }

        return map;
    }

    /**
     * Método para construir a rede de distribuição a partir do ficheiro dos clientes-produtores e do ficheiro das
     * distâncias
     *
     * @param filePathVertex Caminho do ficheiro dos clientes-produtores.
     * @param filePathEdge   Caminho do ficheiro das distâncias.
     */
    public void buildDistributionNetwork(String filePathVertex, String filePathEdge) {
        fileToVertex(filePathVertex);
        fileToEdge(filePathEdge);
        this.minDistDistributionNetwork = Algorithms.minDistGraph(distributionNetwork, Integer::compare, Integer::sum);
    }

    /**
     * Método para converter o ficheiro dos clientes-produtores nos vértices do 'grafo' da rede de distribuição.
     *
     * @param filePathVertex Caminho do ficheiro dos clientes-produtores.
     */
    public void fileToVertex(String filePathVertex) {
        FileToGraph.readFileToVertex(distributionNetwork, filePathVertex);
    }

    /**
     * Método para converter o ficheiro das distâncias nas arestas do 'grafo' da rede de distribuição.
     *
     * @param filePathEdge Caminho do ficheiro das distâncias.
     */
    public void fileToEdge(String filePathEdge) {
        FileToGraph.readFileToEdge(distributionNetwork, filePathEdge);
    }

    /**
     * Método para carregar os dados presentes num ficheiro com informação acerca de cabazes.
     *
     * @param filePathBasket Caminho do ficheiro dos cabazes.
     */
    public void fileToBasket(String filePathBasket) {
        FileToBasket.readFileToBasket(distributionNetwork, productsAvailable, productsRequest, filePathBasket);
    }

    /**
     * Método para determinar a rede que conecte todos os clientes e produtores agrícolas com uma distância total mínima.
     *
     * @return Árvore de extensão mínima.
     */
    public Graph<NetworkPoint, Integer> minimumSpanningTree() {
        return Algorithms.kruskal(distributionNetwork);
    }

    /**
     * Método para verificar se o grafo é conexo.
     *
     * @return se é ou nao conexo.
     */
    public boolean checkConnectivity() {
        return Algorithms.isConnected(distributionNetwork);
    }

    /**
     * Método para calcular o número mínimo de ligações.
     *
     * @return número mínimo de ligações.
     */
    public int minimumNumberNetwork() {
        return Algorithms.graphDiameter(distributionNetwork, Integer::compare, Integer::sum, 0);
    }


    /**
     * Método para calcular o número mínimo de ligações se e so se o grafo em questao for conexo.
     *
     * @return número mínimo de ligações.
     */
    public int calculateMinimumNumberNetwork() {
        return checkConnectivity() ? minimumNumberNetwork() : 0;
    }

    /**
     * Método que recebe todas as distâncias e caminhos de um vértice para todos os outros vértices e calcula a proximidade média entre o
     * vértice fonte e todos os vértices que representam uma instância de CompanyCostumer e retorna um par com o vértice que representa uma
     * instância de CompanyCostumer com a menor proximidade média e a proximidade média entre o vértice original e o vértice referido anteriormente.
     *
     * @param vOrig Vértice que é o ponto de partida.
     * @param paths Um ArrayList que contém todos os caminhos do vértice inicial para todos os outros vértices.
     * @param dists Um ArrayList que contém todos os valores de distância para todos os caminhos desde o vértice inicial até todos os outros vértices.
     * @return Um par que contém um vértice que representa um hub potencial e um número inteiro que representa o valor médio de proximidade.
     */
    private Pair<CompanyCostumer, Integer> averageProximity(NetworkPoint vOrig, ArrayList<LinkedList<NetworkPoint>> paths, ArrayList<Integer> dists) {
        List<NetworkPoint> vertices = distributionNetwork.vertices();
        Pair<CompanyCostumer, Integer> potencialHub;
        int distance;
        int minDistance = Integer.MAX_VALUE;
        int totalPaths = 0;
        int average;
        CompanyCostumer costumer = null;

        //Iterates through all the vertices in the graph
        for (int i = 0; i < vertices.size(); i++) {
            //Verifys if the starting vertice is the same as the ending vertice and if the ending vertice is a companyCostumer
            if (vOrig != vertices.get(i) && (vertices.get(i) instanceof CompanyCostumer)) {
                //Assign to the variable the distance between two vertices
                distance = dists.get(distributionNetwork.key(vertices.get(i)));
                //Updates the value of the minDistance
                if (minDistance > distance) {
                    minDistance = distance;
                    totalPaths = paths.get(distributionNetwork.key(vertices.get(i))).size();
                    costumer = (CompanyCostumer) vertices.get(i);
                }
            }
        }

        //Calculates the average proximity
        average = minDistance / totalPaths;
        potencialHub = new Pair<>(costumer, average);

        return potencialHub;
    }

    /**
     * Método que atualiza o valor da variável do tipo booleano que identifica uma instância da classe CompanyCostumer como hub,
     * alterando para verdadeiro o valor do identificador booleano das N empresas mais próximas de todos os pontos da rede.
     *
     * @param n Numero máximo de hubs.
     */
    public List<Pair<CompanyCostumer, Integer>> defineHubs(int n) {
        boolean flag;
        int j = 0;
        //Create a list to store all the vertices
        List<NetworkPoint> vertices = distributionNetwork.vertices();
        //Create a list to store an companyCostumer and its average proximity to the inteded point
        List<Pair<CompanyCostumer, Integer>> selectedHubs = new LinkedList<>();

        //Iterates through all the vertices in the graph
        for (int i = 0; i < vertices.size(); i++) {
            //Creates a list to receive all the paths between two vertices
            ArrayList<LinkedList<NetworkPoint>> paths = new ArrayList<>();
            //Creates a list to receive all the weithed values of the paths
            ArrayList<Integer> dists = new ArrayList<>();

            //Assign to the variable flag true if it is  possible to calculate a path between two vertices, assign false if not
            flag = Algorithms.shortestPaths(distributionNetwork, vertices.get(i), Integer::compare, Integer::sum, 0, paths, dists);
            //Calls averageProximity only if it is possible to create a path between the two previous vertices
            if (flag) {
                //Calculates the average proximity of two vertices
                Pair<CompanyCostumer, Integer> potencialHub = averageProximity(vertices.get(i), paths, dists);
                boolean existingHub = false;

                //Iterates the list of selected hubs
                for (Pair<CompanyCostumer, Integer> hub : selectedHubs) {
                    //Verifys if there is already a hub equal to the potencial in the selectedHubs list
                    if (hub.equals(potencialHub)) {
                        existingHub = true;

                        //As the hub already exists in the selectedHubs list verifys if it is nessessary to change the weight
                        if (hub.getValue() > potencialHub.getValue()) {
                            hub.setValue(potencialHub.getValue());
                        }
                    }
                }

                //In case that the selected hub does not exist it is added
                if (!existingHub) {
                    selectedHubs.add(potencialHub);
                }
            }
        }
        //Sorts the list selectedHubs
        Collections.sort(selectedHubs);

        //For every n value or for every selectedHub existing
        while (j < n && j < selectedHubs.size()) {
            //Updates the value of the variable hub of the companyCostumer to true
            selectedHubs.get(j).getKey().setHub(true);
            j++;
        }

        return selectedHubs;
    }

    /**
     * Método para determinar para cada cliente (particular ou empresa) o hub mais próximo.
     */
    public void determineNearestHub() {
        // Calls US303's method to define existing hubs, defining all companies as a hub
        defineHubs(totalNumberCompany());

        //Traverse all vertices of the graph
        for (NetworkPoint networkPoint : distributionNetwork.vertices()) {
            //Checks if the current vertex is a customer
            if (networkPoint instanceof Costumer) {
                //Find the nearest hub
                CompanyCostumer nearestHub = nearestHub(networkPoint);

                //Defines the nearest hub
                networkPoint.defineNearestHub(nearestHub);
            }
        }
    }

    /**
     * Função para determinar o hub mais próximo de um ponto da rede.
     *
     * @param costumer Um ponto da rede.
     * @return O hub mais próximo.
     */
    private CompanyCostumer nearestHub(NetworkPoint costumer) {
        //Calls the method to get a list of hubs
        List<CompanyCostumer> listHubs = determineListHubs();

        int shorterDistance = Integer.MAX_VALUE;
        CompanyCostumer nearestHub = null;

        //Scrolls through the list of hubs
        for (CompanyCostumer hub : listHubs) {
            //Checks if the network point passed by parameter is not a hub
            if (costumer != hub) {
                //Calls the shortestPath algorithm that returns the distance between two points
                int distanceToHub = minDistDistributionNetwork.edge(costumer, hub).getWeight();

                //Checks the distance, to update the nearest hub if necessary
                if (shorterDistance > distanceToHub) {
                    shorterDistance = distanceToHub;
                    nearestHub = hub;
                }
            }
        }

        //Returns the nearest hub
        return nearestHub;
    }

    /**
     * Função para encontrar e retornar a lista de hubs existentes.
     *
     * @return Lista de hubs.
     */
    private List<CompanyCostumer> determineListHubs() {
        List<CompanyCostumer> determineListHubs = new ArrayList<>();

        //Traverse all vertices of the graph
        for (NetworkPoint networkPoint : distributionNetwork.vertices()) {

            //Checks if the network point is a company
            if (networkPoint instanceof CompanyCostumer) {
                //See if the company is a hub
                boolean isHub = ((CompanyCostumer) networkPoint).isHub();

                //If it is a hub, add it to the list
                if (isHub) {
                    determineListHubs.add((CompanyCostumer) networkPoint);
                }
            }
        }

        //Returns the list of hubs
        return determineListHubs;
    }

    /**
     * Função para contar o número de empresas existentes no grafo.
     *
     * @return O número de empresas existentes na rede de distribuição.
     */
    private int totalNumberCompany() {
        int total = 0;

        //It traverses all vertices of the distribution network
        for (NetworkPoint networkPoint : distributionNetwork.vertices()) {
            //Checks if this vertex is a company, if so add +1 to the total
            if (networkPoint instanceof CompanyCostumer) {
                total++;
            }
        }

        return total;
    }

    /**
     * Método para gerar uma lista de expedição de cabazes para um determinado dia, sem restrições enquanto os
     * produtores.
     *
     * @param day Dia da lista de expedição a ser gerada.
     * @return A lista de expedição.
     */
    public List<BasketExpedition> generateExpeditionListWithoutRestriction(int day) {

        if (productsRequest.get(day) == null || productsAvailable.isEmpty())
            return null;

        // Determina os hubs mais próximos dos clientes
        determineNearestHub();

        List<BasketExpedition> basketExpeditionsList = new ArrayList<>();

        int minimumDay = Math.max(day - MAX_DAYS_BEFORE_BASKET_EXPIRE, 1);

        populateExpeditionList(basketExpeditionsList, day, minimumDay);

        return basketExpeditionsList;
    }

    /**
     * Método para construir uma lista com os mapas de cabazes disponibilizados à rede de distribuição pelos
     * produtores, em que esses mapas pertençam a um dado intervalo de dias.
     *
     * @param maximumDay O dia mais atual.
     * @param minimumDay O dia necessário e anterior ao atual.
     * @return A lista que contem os mapas de cabazes disponibilizados à rede de distruibuição pelos produtores no
     * intervalo de dias fornecido.
     * @throws IllegalArgumentException Caso os dias se encontrem com valores inválidos (o dia mais baixo não pode ser
     *                                  maior que o dia mais alto, e o dia mais baixo não pode ser menor que 1).
     */
    private List<Map<AgriculturalProducer, Basket>> getListWithMapsFromAIntervalOfDays(int maximumDay, int minimumDay) {
        if (maximumDay < minimumDay)
            throw new IllegalArgumentException("O dia mais alto não pode ser menor do que o dia mais baixo.");

        if (minimumDay < 1)
            throw new IllegalArgumentException("O dia mais baixo não pode ser menor do que 1.");

        List<Map<AgriculturalProducer, Basket>> producerBasketMapList = new ArrayList<>();
        // Para cada dia do intervalo.
        for (int actualDay = maximumDay; actualDay >= minimumDay; actualDay--) {
            producerBasketMapList.add(this.productsAvailable.get(actualDay));
        }

        return producerBasketMapList;
    }

    /**
     * Método para popular a lista de expedição de cabazes para um dado dia.
     *
     * @param basketExpeditionsList A lista de expedição de cabazes a popular.
     * @param day                   O dia para o qual esta lista de expedição é criada.
     * @param minimumDay            O último dia ao qual ainda podemos ir buscar produtos fornecidos.
     */
    private void populateExpeditionList(List<BasketExpedition> basketExpeditionsList, int day, int minimumDay) {
        // Mapa com os cabazes requeridos para o dia day.
        Map<Costumer, Basket> costumerBasketMap = this.productsRequest.get(day);
        // Lista com os mapas de cabazes fornecidos em cada dia por produtores, no intervalo de dias dado.
        List<Map<AgriculturalProducer, Basket>> producerBasketMapList = getListWithMapsFromAIntervalOfDays(day, minimumDay);

        Set<Costumer> costumerSet = costumerBasketMap.keySet();

        // Será necessário procurar cada produto que cada cliente deseja.
        for (Costumer costumer : costumerSet) {
            Basket costumerBasket = costumerBasketMap.get(costumer);
            BasketExpedition basketExpedition = new BasketExpedition(costumer, day);

            // Para cada produto que se encontra requerido no cabaz do cliente.
            for (Product product : costumerBasket.getProductsSet()) {
                ProductProvided productProvided = findProducerAndProvideAProduct(product, producerBasketMapList, costumerBasket);

                if (productProvided != null)
                    basketExpedition.addProductProvided(productProvided);

            }

            basketExpeditionsList.add(basketExpedition);

        }

    }

    /**
     * Método para procurar um produtor para fornecer um dado produto. Este produto ao ser fornecido, atualiza a
     * informação dos mapas que contem a informação dos cabazes pedidos e dos cabazes fornecidos.
     *
     * @param product               O produto desejado pelo cliente.
     * @param producerBasketMapList Lista com os mapas de cabazes fornecidos em cada dia por produtores.
     * @param costumerBasket        O cabaz atual requerido pelo cliente.
     * @return O produto fornecido por um dado produtor, ou nulo se nenhum produtor tiver stock deste produto nos seus
     * cabazes.
     */
    private ProductProvided findProducerAndProvideAProduct(
            Product product,
            List<Map<AgriculturalProducer, Basket>> producerBasketMapList,
            Basket costumerBasket) {

        double costumerRequestedQuantity = costumerBasket.getProductQuantity(product);
        ProductProvided productProvided = new ProductProvided(product, costumerRequestedQuantity, 0, null);
        boolean productFound = false;

        // Para cada mapa, verificar se existe um produtor que contém o produto desejado.
        Iterator<Map<AgriculturalProducer, Basket>> producerBasketMapsIterator = producerBasketMapList.iterator();
        while (producerBasketMapsIterator.hasNext() && !productFound) {
            Map<AgriculturalProducer, Basket> producerBasketMap = producerBasketMapsIterator.next();

            // Para cada produtor, verificar se este contém o produto requerido pelo cliente em cabaz e scom stock.
            Iterator<AgriculturalProducer> providersIterator = producerBasketMap.keySet().iterator();
            while (providersIterator.hasNext() && !productFound) {
                AgriculturalProducer provider = providersIterator.next();

                Basket producerBasket = producerBasketMap.get(provider);
                double producerAvailableQuantity = producerBasket.getProductQuantity(product);

                // Se o produto tiver o produto em quantidades acima de 0
                if (producerBasket.hasProduct(product) && producerAvailableQuantity > 0.0) {
                    // Diferença de quantidade que o produtor tem com a quantidade requerida pelo cliente.
                    double quantityDifference = producerAvailableQuantity - costumerRequestedQuantity;
                    // A quantidade do produto a ser entregue.
                    double deliveredQuantity = quantityDifference < 0 ? producerAvailableQuantity : costumerRequestedQuantity;
                    // Subtrai a quantidade deste produto agora a receber ao cabaz pedido pelo cliente.
                    costumerBasket.subtractProductQuantity(product, deliveredQuantity);
                    // Subtrai a quantidade fornecida pelo produtor ao cabaz do produtor.
                    producerBasket.subtractProductQuantity(product, deliveredQuantity);
                    productProvided = new ProductProvided(product, costumerRequestedQuantity, deliveredQuantity, provider);
                    productFound = true;
                }
            }
        }

        return productProvided;

    }

    /**
     * Função para uma lista de expedição diária gerar o percurso de entrega que minimiza a distância
     * total percorrida.
     *
     * @param basketExpeditionsList A lista de expedição de cabazes a ser populada.
     * @return A rota de entrega.
     */
    public DeliveryRoute generateDeliveryRoute(List<BasketExpedition> basketExpeditionsList) {
        // Cabazes entregues em cada hub
        Map<CompanyCostumer, Set<BasketExpedition>> basketsDelivered = getBasketsDelivered(basketExpeditionsList);

        // Lista de produtores
        List<AgriculturalProducer> providerList = getProviderList(basketExpeditionsList);
        Collections.sort(providerList);
        // Lista de hubs
        List<NetworkPoint> hubList = new LinkedList<>(basketsDelivered.keySet());
        Collections.sort(hubList);

        // Cria uma lista para receber o caminho mais curto
        LinkedList<NetworkPoint> waypoints = new LinkedList<>();

        int totalDistance = 0;
        //Verifica o caminho mais curto entre todos os produtores
        totalDistance += generateRoute(providerList, waypoints, false);

        // Para haver uma conexão entre os produtores e os hub, é colocado o último produtor
        int positionLastProvider = providerList.size() - 1;
        hubList.add(0, waypoints.get(positionLastProvider));
        waypoints.remove(positionLastProvider);

        // Verifica o caminho mais curto entre o último produtor e todos os hub
        totalDistance += generateRoute(hubList, waypoints, true);

        // Retorna uma rota de entrega
        return new DeliveryRoute(waypoints, basketsDelivered, totalDistance, minDistDistributionNetwork);
    }

    /**
     * Função para retornar uma lista dos fornecedores dos produtos fornecidos para a lista de expedição.
     *
     * @param basketExpeditionsList A lista de expedição de cabazes a ser populada.
     * @return Lista dos fornecedores dos produtos fornecidos para a lista de expedição.
     */
    private List<AgriculturalProducer> getProviderList(List<BasketExpedition> basketExpeditionsList) {
        List<AgriculturalProducer> providerList = new LinkedList<>();

        if (basketExpeditionsList != null) {
            for (BasketExpedition basketExpedition : basketExpeditionsList) {

                for (AgriculturalProducer agriculturalProducer : basketExpedition.getProviderList()) {
                    if (!providerList.contains(agriculturalProducer)) {
                        providerList.add(agriculturalProducer);
                    }
                }
            }
        }

        return providerList;
    }

    /**
     * Função para construir um mapa com os cabazes a entregar em cada hub.
     *
     * @param basketExpeditionsList A lista de expedição de cabazes a ser populada.
     * @return Um mapa com os cabazes a entregar em cada hub.
     */
    private Map<CompanyCostumer, Set<BasketExpedition>> getBasketsDelivered(List<BasketExpedition> basketExpeditionsList) {
        Map<CompanyCostumer, Set<BasketExpedition>> basketsDelivered = new HashMap<>();

        if (basketExpeditionsList != null) {
            for (BasketExpedition basketExpedition : basketExpeditionsList) {
                CompanyCostumer hub = basketExpedition.getCostumer().getNearestHub();

                Set<BasketExpedition> basketExpeditionSet = basketsDelivered.get(hub);
                if (basketExpeditionSet == null) {
                    basketExpeditionSet = new HashSet<>();
                    basketsDelivered.put(hub, basketExpeditionSet);
                }

                basketExpeditionSet.add(basketExpedition);
            }
        }

        return basketsDelivered;
    }

    /**
     * Função para gerar uma rota que minimiza a distância total percorrida entre uma lista de pontos da rede de distribuição.
     *
     * @param waypoints          Lista de pontos da rede de distribuição.
     * @param shortPath          Lista para receber o caminho mais curto entre os pontos da rede de distribuição analisada.
     * @param firstFixedPosition Verdadeiro caso a posição inicial do percurso deva ser fixa, caso contrário falso.
     * @return Distância total entre todos os pontos da rede de distribuição analisada.
     */
    private <V extends NetworkPoint> int generateRoute(List<V> waypoints, LinkedList<NetworkPoint> shortPath, boolean firstFixedPosition) {
        int betterDistance = Integer.MAX_VALUE;
        LinkedList<NetworkPoint> betterShortPath = new LinkedList<>();

        int waypointsSize = waypoints.size();
        for (int i = 0; i < waypointsSize; i++) {
            int currentDistance = 0;

            // Soma as distâncias entre todos os pontos do percurso a ser analisado atualmente
            for (int j = 0; j < waypointsSize - 1; j++) {
                currentDistance += minDistDistributionNetwork.edge(waypoints.get(j), waypoints.get(j + 1)).getWeight();
            }

            // Verifica se a distância do percurso a ser verificado é menor do que a melhor atualmente
            if (betterDistance > currentDistance) {
                betterShortPath.clear();
                betterShortPath.addAll(waypoints);
                betterDistance = currentDistance;
            }

            // Realiza uma rotação no array do percurso para verificar todas as possibilidades
            Collections.rotate(waypoints, 1);

            // Se a primeira posição do percurso for fixa, volta a colocá-la em primeiro
            if (firstFixedPosition) {
                V firstPosition = waypoints.get(1);
                waypoints.remove(firstPosition);
                waypoints.add(0, firstPosition);
            }
        }

        // Adiciona o melhor percurso à lista passada por parâmetro
        shortPath.addAll(betterShortPath);

        return betterDistance;
    }

    /**
     * Método para calcular para uma lista de expedição as estatísticas por cabaz.
     *
     * @param basketExpeditionsList A lista de expedição de cabazes a ser populada.
     */
    public void calculateStatisticsByBasket(List<BasketExpedition> basketExpeditionsList) {
        if (basketExpeditionsList != null) {
            for (BasketExpedition basketExpedition : basketExpeditionsList) {
                basketExpedition.calculateBasketStatistics();
            }
        }
    }

    /**
     * Metodo para calcular para uma lista de expedicao as caracteristicas do nº de clientes distintos fornecidos
     *
     * @param getCostumerProducer mapa com Agricultores e clientes
     */
    private void calculateDistinctClientsProvided(Map<AgriculturalProducer, Set<Costumer>> getCostumerProducer) {

        for (AgriculturalProducer agriculturalProducer : getCostumerProducer.keySet()) {
            int numberDistinctClientsProvided = getCostumerProducer.get(agriculturalProducer).size();
            agriculturalProducer.getAgriculturalProducerStatistics().setDistinctClientsProvided(numberDistinctClientsProvided);
        }
    }

    /**
     * Metodo para calcular para uma lista de expedicao as caracteristicas do nº de produtos totalmente esgotados
     *
     * @param agriculturalProducers set com os produtores agriculas
     * @param day                   dia relativo a lista de expedicao
     */
    private void calculateCompletelySoldProducts(Set<AgriculturalProducer> agriculturalProducers, int day) {

        Map<AgriculturalProducer, Basket> agriculturalProducerBasketMap = productsAvailable.get(day);

        if (agriculturalProducerBasketMap != null) {
            int counter = 0;
            Iterator<AgriculturalProducer> agriculturalProducerIterator = agriculturalProducers.iterator();

            while (agriculturalProducerIterator.hasNext()) {

                AgriculturalProducer agriculturalProducer = agriculturalProducerIterator.next();
                Basket basket = agriculturalProducerBasketMap.get(agriculturalProducer);

                Iterator<Product> productIterator = basket.getProductsSet().iterator();
                while (productIterator.hasNext()) {
                    if (basket.getProductQuantity(productIterator.next()) == 0.0) {
                        counter++;
                    }
                }
                agriculturalProducer.getAgriculturalProducerStatistics().setCompletelySoldProducts(counter);
            }
        }
    }

    /**
     * Metodo para calcular para uma lista de expedicao as caracteristicas do nº de hubs fornecidos
     *
     * @param getCostumerProducer mapa com Agricultores e clientes
     */
    private void calculateProvidedHud(Map<AgriculturalProducer, Set<Costumer>> getCostumerProducer) {

        for (AgriculturalProducer agriculturalProducer : getCostumerProducer.keySet()) {
            Set<Costumer> costumerSet = getCostumerProducer.get(agriculturalProducer);
            Set<CompanyCostumer> hubSet = new HashSet<>();

            if (costumerSet != null) {

                Iterator<Costumer> costumerIterator = costumerSet.iterator();
                while (costumerIterator.hasNext()) {
                    hubSet.add(costumerIterator.next().getNearestHub());
                }
                agriculturalProducer.getAgriculturalProducerStatistics().setSuppliedHubs(hubSet.size());

            }
        }
    }

    /**
     * Método para calcular para uma lista de expedição as estatísticas por produtor.
     *
     * @param basketExpeditionsList A lista de expedição de cabazes a ser populada.
     */
    public void calculateStatisticsByAgriculturalProducer(List<BasketExpedition> basketExpeditionsList) {

        Map<AgriculturalProducer, Set<Costumer>> getCostumerProducer = new HashMap<>();
        int basketExpeditionDay = 0;

        if (basketExpeditionsList != null) {
            for (BasketExpedition basketExpedition : basketExpeditionsList) {
                for (ProductProvided productProvided : basketExpedition.getProductsProvided()) {

                    AgriculturalProducer agriculturalProducer = productProvided.getProvider();

                    if (agriculturalProducer != null) {
                        // basketFullySupplied & basketPartiallySupplied
                        if (productProvided.fullySatisfiedProduct()) {
                            agriculturalProducer.getAgriculturalProducerStatistics().addBasketFullySupplied();
                        } else {
                            agriculturalProducer.getAgriculturalProducerStatistics().addBasketPartiallySupplied();
                        }

                        Set<Costumer> costumerSet = getCostumerProducer.get(agriculturalProducer);
                        if (costumerSet == null) {
                            costumerSet = new HashSet<>();
                            getCostumerProducer.put(agriculturalProducer, costumerSet);
                        }
                        costumerSet.add(basketExpedition.getCostumer());
                    }

                    basketExpeditionDay = basketExpedition.getDay();

                }
            }
            //distinctClientsProvided
            calculateDistinctClientsProvided(getCostumerProducer);

            //completelySoldProducts
            calculateCompletelySoldProducts(getCostumerProducer.keySet(), basketExpeditionDay);

            //suppliedHubs
            calculateProvidedHud(getCostumerProducer);

        }
    }

    /**
     * Método para calcular para uma lista de expedição as estatísticas por cliente.
     *
     * @param basketExpeditionsList A lista de expedição de cabazes a ser populada.
     */
    public void calculateStatisticsByCostumer(List<BasketExpedition> basketExpeditionsList) {
        if (basketExpeditionsList != null) {
            calculateStatisticsByBasket(basketExpeditionsList);

            Map<Costumer, Set<AgriculturalProducer>> producerByCostumer = new HashMap<>();

            //Percorre a lista passada por parametro
            for (BasketExpedition basketExpedition : basketExpeditionsList) {
                BasketStatistics basketStatistics = basketExpedition.getStatisticsBasket();
                Costumer costumer = basketExpedition.getCostumer();

                //Verfifica se o cabaz está totalmete satisfeitos ou parcialmente satisfeitos
                if (basketStatistics.fullySatisfiedBasket()) {
                    costumer.getCostumerStatistics().addFullySatisfiedBaskets();
                } else if (basketStatistics.partiallySatisfiedBasket()) {
                    costumer.getCostumerStatistics().addPartiallySatisfiedBaskets();
                }

                Set<AgriculturalProducer> producerSet = producerByCostumer.get(costumer);
                //Verifica se o producerSet tem conteudo se não, cria-o
                if (producerSet == null) {
                    producerSet = new HashSet<>();
                    producerByCostumer.put(costumer, producerSet);

                }

                producerSet.addAll(basketExpedition.getProviderList());
            }

            for (Costumer costumer : producerByCostumer.keySet()) {
                costumer.getCostumerStatistics().setDifferentSupliersGiveBaskets(producerByCostumer.get(costumer).size());
            }
        }
    }

    /**
     * Método para calcular para uma lista de expedição as estatísticas por hub
     *
     * @param basketExpeditionsList A lista de expedição de cabazes a ser populada.
     */
    public void calculateStatisticsByHub(List<BasketExpedition> basketExpeditionsList) {
        if (basketExpeditionsList != null) {
            Map<CompanyCostumer, Set<Costumer>> costumersByHub = new HashMap<>();
            Map<CompanyCostumer, Set<AgriculturalProducer>> producerByHub = new HashMap<>();
            //Percorre a lista passada por parâmetro
            for (BasketExpedition basketExpedition : basketExpeditionsList) {
                CompanyCostumer hub = basketExpedition.getDeliveryLocation();

                Set<Costumer> costumerSet = costumersByHub.get(hub);
                //Verifica se o costumerSet tem conteúdo, se não, cría-o
                if (costumerSet == null) {
                    costumerSet = new HashSet<>();
                    costumersByHub.put(hub, costumerSet);
                }
                costumerSet.add(basketExpedition.getCostumer());

                Set<AgriculturalProducer> producerInBasket = basketExpedition.getProviderList();
                Set<AgriculturalProducer> agriculturalProducerSet = producerByHub.get(hub);
                //Verifica se agriculturalProducerSet existe, senão vai

                if (agriculturalProducerSet == null) {
                    producerByHub.put(hub, producerInBasket);
                } else {
                    agriculturalProducerSet.addAll(producerInBasket);
                }
            }

            for (CompanyCostumer hub : costumersByHub.keySet()) {
                int differentCostumersFetchHub = costumersByHub.get(hub).size();
                int differentProducersGiveHub = producerByHub.get(hub).size();
                hub.setHubStatistics(new HubStatistics(differentCostumersFetchHub, differentProducersGiveHub));
            }
        }
    }

    /**
     * Método que gera uma lista de expedição para um determinado dia que forneçe apenas os N
     * produtores agrícolas mais próximos do hub de entrega do cliente.
     *
     * @param n   O número de hubs mais próximos.
     * @param day O dia que se quer analisar.
     * @return Uma lista de BasketExpedition com informação sobre os produtos solicitados pelos clientes num determinado dia.
     */
    public List<BasketExpedition> generateExpeditionListWithNClosestHubs(int n, int day) {

        if (productsRequest.get(day) == null || productsAvailable.isEmpty() || n <= 0) {
            return null;
        }

        // Determina os hubs mais próximos dos clientes
        determineNearestHub();

        List<BasketExpedition> basketExpeditionList = new ArrayList<>();

        int minimumDay = Math.max(day - MAX_DAYS_BEFORE_BASKET_EXPIRE, 1);

        populateExpeditionListWithNClosestHubs(basketExpeditionList, day, n, minimumDay);

        return basketExpeditionList;
    }

    /**
     * Metodo que preenche uma lista de instâncias BasketExpedition com informações sobre os produtos solicitados pelos
     * clientes em um determinado dia e o AgriculturalProducer mais próximo que possa atender a cada solicitação.
     *
     * @param basketExpeditionList A lista a ser preenchida.
     * @param day                  O dia que se quer analisar.
     * @param n                    O número de hubs mais próximos.
     * @param minimumDay           O menor extremo do intervalo de dias.
     */
    private void populateExpeditionListWithNClosestHubs(List<BasketExpedition> basketExpeditionList, int day, int n, int minimumDay) {
        Map<Costumer, Basket> costumerBasketMap = this.productsRequest.get(day);

        Set<Costumer> costumerSet = costumerBasketMap.keySet();

        for (Costumer costumer : costumerSet) {

            List<Map<AgriculturalProducer, Basket>> producerBasketMapList = getListWithMapsFromAIntervalOfDays(costumer, n, day, minimumDay);

            Basket costumerBasket = costumerBasketMap.get(costumer);
            BasketExpedition basketExpedition = new BasketExpedition(costumer, day);

            // Para cada produto que se encontra requerido no cabaz do cliente.
            for (Product product : costumerBasket.getProductsSet()) {
                ProductProvided productProvided = findProducerAndProvideAProduct(product, producerBasketMapList, costumerBasket);

                if (productProvided != null)
                    basketExpedition.addProductProvided(productProvided);

            }

            basketExpeditionList.add(basketExpedition);
        }

    }

    /**
     * Método que calcula os AgriculturalProducer mais próximos de n hubs.
     *
     * @param costumer   O cliente que queremos analisar.
     * @param n          O número de hubs mais próximos.
     * @param maximumDay O maior extremo do intervalo de dias.
     * @param minimumDay O menor extremo do intervalo de dias.
     * @return Uma lista de mapas com os AgriculturalProducer mais próximos de n hubs e os Basket respetivos.
     */
    private List<Map<AgriculturalProducer, Basket>> getListWithMapsFromAIntervalOfDays(Costumer costumer, int n, int maximumDay, int minimumDay) {
        List<Map<AgriculturalProducer, Basket>> producerBasketMapList = new ArrayList<>();

        List<AgriculturalProducer> agriculturalProducerList = agriculturalProducersClosestToTheHub(costumer, n);

        for (int actualDay = maximumDay; actualDay >= minimumDay; actualDay--) {
            Map<AgriculturalProducer, Basket> agriculturalProducerBasketMap = this.productsAvailable.get(actualDay);
            Map<AgriculturalProducer, Basket> resultAgriculturalProducerBasketMap = new HashMap<>();

            for (AgriculturalProducer agriculturalProducer : agriculturalProducerList) {
                Basket basket = agriculturalProducerBasketMap.get(agriculturalProducer);

                if (basket != null) {
                    resultAgriculturalProducerBasketMap.put(agriculturalProducer, basket);
                }
            }

            producerBasketMapList.add(resultAgriculturalProducerBasketMap);
        }

        return producerBasketMapList;
    }

    /**
     * Método para calcular as instâncias de AgriculturalProducer mais próximas a um hub.
     *
     * @param costumer O cliente que queremos analisar.
     * @param n        O número de hubs mais próximos.
     * @return Uma lista com os n hubs mais próximos do costumer passado por parametro.
     */
    private List<AgriculturalProducer> agriculturalProducersClosestToTheHub(Costumer costumer, int n) {
        CompanyCostumer hub = costumer.getNearestHub();
        List<NetworkPoint> vertices = distributionNetwork.vertices();

        // Cria uma lista para receber todos os caminhos entre dois vértices
        ArrayList<LinkedList<NetworkPoint>> paths = new ArrayList<>();
        // Cria uma lista para receber todos os valores ponderados dos caminhos
        ArrayList<Integer> dists = new ArrayList<>();

        // Calcula o caminho mais curto entre um vértice e todos os outros vértices
        Algorithms.shortestPaths(distributionNetwork, hub, Integer::compare, Integer::sum, 0, paths, dists);

        List<Pair<AgriculturalProducer, Integer>> agriculturalProducerDistanceToHub = new ArrayList<>();

        for (NetworkPoint networkPoint : vertices) {
            // Verifica se o vértice inicial é igual ao vértice final e se o vértice final é um companyCostumer
            if ((networkPoint instanceof AgriculturalProducer)) {
                // Atribuir à variável a distância entre dois vértices
                int distance = dists.get(distributionNetwork.key(networkPoint));

                agriculturalProducerDistanceToHub.add(new Pair<>((AgriculturalProducer) networkPoint, distance));
            }
        }

        Collections.sort(agriculturalProducerDistanceToHub);

        List<AgriculturalProducer> agriculturalProducers = new ArrayList<>();

        for (int i = 0; i < agriculturalProducerDistanceToHub.size(); i++) {
            if (n > i) {
                agriculturalProducers.add(agriculturalProducerDistanceToHub.get(i).getKey());
            }
        }

        return agriculturalProducers;
    }

}