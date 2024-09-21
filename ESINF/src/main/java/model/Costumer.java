package model;

/**
 * Classe que representa um cliente.
 */
public abstract class Costumer extends NetworkPoint {

    /**
     * Atributo que representa o identificador do cliente.
     */
    private final String costumerIdentifier;

    /**
     * Estatistica do cliente.
     */
    private CostumerStatistics costumerStatistics;

    /**
     * Construtor para criar uma instância do objeto Costumer com os atributos 'id' da localização, latitude e longitude
     * do ponto da rede. Inicia também com o identificador do cliente.
     *
     * @param localizationId     'Id' da localização do ponto da rede.
     * @param latitude           Latitude da localização do ponto da rede.
     * @param longitude          Longitude da localização do ponto da rede.
     * @param costumerIdentifier O identificador do cliente.
     */
    public Costumer(String localizationId, Double latitude, Double longitude, String costumerIdentifier) {
        super(localizationId, latitude, longitude);
        this.costumerIdentifier = costumerIdentifier;
        this.costumerStatistics = new CostumerStatistics();
    }

    /**
     * Construtor para criar uma instância do objeto Costumer com os atributos 'id' da localização e com o identificador
     * do cliente.
     *
     * @param localizationId     'Id' da localização do ponto da rede.
     * @param costumerIdentifier O identificador do cliente.
     */
    public Costumer(String localizationId, String costumerIdentifier) {
        super(localizationId);
        this.costumerIdentifier = costumerIdentifier;
    }

    /**
     * Método para devolver o identificador do cliente.
     *
     * @return O identificador do cliente.
     */
    public String getCostumerIdentifier() {
        return this.costumerIdentifier;
    }

    /**
     * Método para devolver as estatisticas do cliente.
     *
     * @return As estatisticas do cliente.
     */
    public CostumerStatistics getCostumerStatistics() {
        return this.costumerStatistics;
    }

}
