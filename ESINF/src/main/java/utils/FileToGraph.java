package utils;

import model.*;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Classe responsável por converter os ficheiros de clientes-produtores e distâncias num grafo.
 */
public class FileToGraph {

    /**
     * Método para converter o ficheiro dos clientes-produtores nos vértices do 'grafo' da rede de distribuição.
     *
     * @param distributionNetwork 'Grafo' da rede de distribuição.
     * @param filePathVertex      Caminho do ficheiro dos clientes-produtores.
     */
    public static void readFileToVertex(Graph<NetworkPoint, Integer> distributionNetwork, String filePathVertex) {
        try {
            List<String[]> contentFile = new FileReaderType1().readFile(filePathVertex, true);

            for (String[] columns : contentFile) {
                if (columns.length == IndexColumnsFileProducerCustomer.TOTAL_COLUMNS.getIndex()) {

                    NetworkPoint networkPoint = createNetworkPoint(columns[IndexColumnsFileProducerCustomer.LOCALIZATION_ID.getIndex()],
                            columns[IndexColumnsFileProducerCustomer.LATITUDE.getIndex()],
                            columns[IndexColumnsFileProducerCustomer.LONGITUDE.getIndex()],
                            columns[IndexColumnsFileProducerCustomer.NAME.getIndex()]);

                    if (networkPoint != null) {
                        distributionNetwork.addVertex(networkPoint);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Não existe nenhum ficheiro no caminho indicado.");
        }
    }

    /**
     * Método para converter o ficheiro das distâncias nas arestas do 'grafo' da rede de distribuição.
     *
     * @param distributionNetwork 'Grafo' da rede de distribuição.
     * @param filePathEdge        Caminho do ficheiro das distâncias.
     */
    public static void readFileToEdge(Graph<NetworkPoint, Integer> distributionNetwork, String filePathEdge) {
        try {
            List<String[]> contentFile = new FileReaderType1().readFile(filePathEdge, true);

            for (String[] columns : contentFile) {
                if (columns.length == IndexColumnsFileDistances.TOTAL_COLUMNS.getIndex()) {

                    NetworkPoint networkPoint1 = new NetworkPoint(columns[IndexColumnsFileDistances.LOCALIZATION_ID_1.getIndex()]);
                    NetworkPoint networkPoint2 = new NetworkPoint(columns[IndexColumnsFileDistances.LOCALIZATION_ID_2.getIndex()]);
                    Integer distancie = parseStringToInteger(columns[IndexColumnsFileDistances.DISTANCES.getIndex()]);

                    distributionNetwork.addEdge(networkPoint1, networkPoint2, distancie);
                }
            }

        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Não existe nenhum ficheiro no caminho indicado.");
        }
    }

    /**
     * Função para criar um objeto NetworkPoint do tipo produtor agrícola, cliente particular ou empresarial dependendo
     * do código passado por parâmetro.
     *
     * @param localizationId 'Id' da localização do ponto da rede.
     * @param latitude       Latitude da localização do ponto da rede.
     * @param longitude      Longitude da localização do ponto da rede.
     * @param code           Código do produtor agrícola ou do cliente.
     * @return Um ponto da rede.
     */
    private static NetworkPoint createNetworkPoint(String localizationId, String latitude, String longitude, String code) {
        char codeInitialCharacter = code.charAt(0);

        if (codeInitialCharacter == CodeType.COSTUMER_PARTICULAR.getCharacter()) {
            return new ParticularCostumer(localizationId, parseStringToDouble(latitude), parseStringToDouble(longitude), code);

        } else if (codeInitialCharacter == CodeType.COSTUMER_COMPANY.getCharacter()) {
            return new CompanyCostumer(localizationId, parseStringToDouble(latitude), parseStringToDouble(longitude), code);

        } else if (codeInitialCharacter == CodeType.PRODUCER.getCharacter()) {
            return new AgriculturalProducer(localizationId, parseStringToDouble(latitude), parseStringToDouble(longitude), code);
        }

        return null;
    }

    /**
     * Função para converter uma 'string' num número inteiro. Caso não seja possível é retornado o valor 0.
     *
     * @param number String a converter.
     * @return Número do tipo inteiro.
     */
    private static Integer parseStringToInteger(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException n) {
            return 0;
        }
    }

    /**
     * Função para converter uma 'string' num número double. Caso não seja possível é retornado o valor 0.
     *
     * @param number String a converter.
     * @return Número do tipo double.
     */
    private static Double parseStringToDouble(String number) {
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException n) {
            return 0D;
        }
    }
}
