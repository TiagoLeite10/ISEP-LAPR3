package utils;

import model.*;
import utils.exception.InvalidFileLineException;
import utils.exception.NetworkPointNotFoundException;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Classe responsável por converter os ficheiros de cabazes em mapas com a informação presente no ficheiro.
 */
public class FileToBasket {

    /**
     * Método responsável por ler e armazenar os dados presentes no ficheiro em mapas de informação.
     *
     * @param distributionNetwork 'Grafo' da rede de distribuição.
     * @param productsAvailable   Mapa que armazena cabazes de produtos disponíveis para serem requeridos por clientes.
     * @param productsRequest     Mapa que armazena cabazes de produtos requeridos por clientes.
     * @param filePath            Caminho do ficheiro com a informação acerca dos cabazes.
     */
    public static void readFileToBasket(Graph<NetworkPoint, Integer> distributionNetwork,
                                        Map<Integer, Map<AgriculturalProducer, Basket>> productsAvailable,
                                        Map<Integer, Map<Costumer, Basket>> productsRequest,
                                        String filePath
    ) {

        if (distributionNetwork == null || distributionNetwork.numVertices() == 0)
            return;

        try {

            List<String[]> contentFile = new FileReaderType1().readFile(filePath, false);
            String[] header = contentFile.get(0);
            contentFile.remove(0);

            // Cria os objetos Produto com os nomes dos produtos fornecidos no cabeçaho
            Map<Integer, Product> productMap = createProductsByHeader(header);

            int line = 2;
            for (String[] columns : contentFile) {
                // Se as linhas que sucedem o cabeçalho não tiver o mesmo número de colunas, existe erro.
                if (columns.length != header.length) {
                    throw new InvalidFileLineException(line);
                }

                // Identificador
                String clientOrProductor = columns[IndexColumnsFileBaskets.COSTUMERS_PRODUCERS.getValue()];
                int day = convertDayStringInInt(columns[IndexColumnsFileBaskets.DAY.getValue()], line);
                Basket basket = createBasket(columns, productMap, line);
                NetworkPoint networkPoint = findNetworkPoint(distributionNetwork, clientOrProductor, line);

                // Apenas adiciona o cliente ou o produtor se o cabaz para este dia tiver produtos.
                if (basket.getProductsSetSize() != 0) {
                    // Verifica que é produtor ou cliente
                    if (clientOrProductor.toUpperCase().charAt(0) == CodeType.PRODUCER.getCharacter()) {
                        if (productsAvailable.get(day) == null)
                            productsAvailable.put(day, new HashMap<>());
                        productsAvailable.get(day).put((AgriculturalProducer) networkPoint, basket);
                    } else {
                        if (productsRequest.get(day) == null)
                            productsRequest.put(day, new HashMap<>());
                        productsRequest.get(day).put((Costumer) networkPoint, basket);
                    }
                }

                line++;
            }

        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("Não existe nenhum ficheiro no caminho indicado.");
        }

    }

    /**
     * Método para converter um dia representado por uma String, num dia representado pelo tipo de dados int.
     *
     * @param day  A String que representa um determinado dia.
     * @param line Linha do ficheiro onde este valor se encontra.
     * @return O valor do dia representado pelo tipo de dados int.
     * @throws NumberFormatException Quando não é possível converter o valor em int.
     */
    private static int convertDayStringInInt(String day, int line) {
        int dayValue = 0;

        try {
            dayValue = Integer.parseInt(day);
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("Verifique o valor do dia na linha " + line + " do ficheiro, pois " +
                    "encontra-se inválido.");
        }

        return dayValue;
    }

    /**
     * Método para converter uma quantidade representada por uma String numa quantidade do tipo double.
     *
     * @param columnValue A quantidade em String a ser convertido para double.
     * @param line        Linha do ficheiro em que este valor se encontra.
     * @return A quantidade representada no tipo double.
     * @throws NumberFormatException Quando não é possível converter o valor em double.
     */
    private static double convertAmountStringInDouble(String columnValue, int line) {
        double value = 0.0;

        try {
            value = Double.parseDouble(columnValue);
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("Foi encontrado um erro na linha " + line + " do ficheiro. Verifique as " +
                    "quantidades existentes para os produtos nessa mesma linha.");
        }

        return value;
    }

    /**
     * Método para encontrar e devolver o NetWorkPoint de um dado código identificador de um produtor agrícola ou de um
     * cliente.
     *
     * @param distributionNetwork 'Grafo' da rede de distribuição.
     * @param codeIdentifier      O código identificador de um produtor ou de um cliente.
     * @param line                Linha do ficheiro a ser tratada no momento.
     * @return Devolve o NetworkPoint correspondente a um código identificador de um produto ou de um cliente, ou null
     * no caso de não ser encontrado nenhum NetworkPoint a que pertence ao identificador.
     * @throws NetworkPointNotFoundException Se o codeIdentifier não for encontrado na rede de distribuição como cliente
     *                                       ou como produtor.
     */
    private static NetworkPoint findNetworkPoint(Graph<NetworkPoint, Integer> distributionNetwork,
                                                 String codeIdentifier, int line) {
        // Todos os network points da rede
        List<NetworkPoint> networkPointArrayList = distributionNetwork.vertices();

        for (NetworkPoint networkPoint : networkPointArrayList) {
            if (networkPoint instanceof AgriculturalProducer agriculturalProducer) {
                if (agriculturalProducer.getProducerCode().equals(codeIdentifier))
                    return agriculturalProducer;
            } else if (networkPoint instanceof Costumer costumer) {
                if (costumer.getCostumerIdentifier().equals(codeIdentifier))
                    return costumer;
            }
        }

        throw new NetworkPointNotFoundException("O cliente ou produtor específicado na linha " + line + " do ficheiro, " +
                "não foi encontrado na rede de distribuição. Por favor, verifique se está correto.");
    }

    /**
     * Método para criar produtos a partir de um dado array com o cabeçalho do ficheiro, onde é possível encontrar os
     * nomes dos produtos necessários.
     *
     * @param header Array com os parâmetros do cabeçalho do ficheiro.
     * @return Devolve um mapa com a chave que indica o índice onde esse produto se encontra, e como valor o Produto
     * criado.
     */
    private static Map<Integer, Product> createProductsByHeader(String[] header) {
        Map<Integer, Product> productMap = new HashMap<>();

        for (int pos = IndexColumnsFileBaskets.START_PRODUCT_INDEX.getValue(); pos < header.length; pos++) {
            productMap.put(pos, new Product(header[pos]));
        }

        return productMap;
    }

    /**
     * Método para criar um cabaz e adicionar um conjunto de produtos a esse mesmo cabaz.
     *
     * @param data       Contém a informação sobre a quantidade de cada produto.
     * @param productMap Um mapa que contem os produtos onde a chave para os encontrar representa o índice em que se
     *                   encontra a quantidade no array.
     * @param line       Representa a linha do ficheiro de onde provêm estes dados das quantidades.
     * @return Devolve um cabaz com os produtos e respetivas quantidades adicionadas.
     */
    private static Basket createBasket(String[] data, Map<Integer, Product> productMap, int line) {
        Basket basket = new Basket();

        for (int pos = IndexColumnsFileBaskets.START_PRODUCT_INDEX.getValue(); pos < data.length; pos++) {
            double productAmount = convertAmountStringInDouble(data[pos], line);
            if (productAmount > 0.0)
                basket.addProduct(productMap.get(pos), productAmount);
        }

        return basket;
    }

}
