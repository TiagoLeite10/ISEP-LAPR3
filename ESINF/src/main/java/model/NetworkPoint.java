package model;

/**
 * Classe que representa um ponto da rede.
 */
public class NetworkPoint implements Comparable<NetworkPoint> {
    /**
     * 'Id' da localização do ponto da rede.
     */
    private String localizationId;

    /**
     * Latitude da localização do ponto da rede.
     */
    private Double latitude;

    /**
     * Longitude da localização do ponto da rede.
     */
    private Double longitude;

    /**
     * Representa o hub mais perto de um ponto da rede.
     */
    private CompanyCostumer nearestHub;

    /**
     * Construtor para inicializar uma instância do objeto NetworkPoint com os atributos 'Id' da localização, latitude,
     * longitude do ponto de rede.
     *
     * @param localizationId 'Id' da localização do ponto da rede.
     * @param latitude       Latitude da localização do ponto da rede.
     * @param longitude      Longitude da localização do ponto da rede.
     */
    public NetworkPoint(String localizationId, Double latitude, Double longitude) {
        this.localizationId = localizationId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Construtor para inicializar uma instância do objeto NetworkPoint com os atributos 'Id' da localização do ponto de rede.
     *
     * @param localizationId 'Id' da localização do ponto da rede.
     */
    public NetworkPoint(String localizationId) {
        this.localizationId = localizationId;
    }

    /**
     * Método para definir o hub mais próximo.
     *
     * @param nearestHub O hub mais próximo.
     */
    public void defineNearestHub(CompanyCostumer nearestHub) {
        this.nearestHub = nearestHub;
    }

    /**
     * Método para retornar o hub mais próximo.
     */
    public CompanyCostumer getNearestHub() {
        return nearestHub;
    }

    /**
     * Método para verificar se este objeto atual é igual a outro objeto do mesmo tipo.
     *
     * @param obj O objeto a ser comparado com este atual.
     * @return true se o outro objeto representa um 'id' da localização do ponto da rede igual a este, caso contrário
     * devolve false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        NetworkPoint otherNetworkPoint = (NetworkPoint) obj;

        return (localizationId.equals(otherNetworkPoint.localizationId));
    }

    /**
     * Calcula o hashcode para este objeto utilizando o 'id' da localização do ponto da rede.
     *
     * @return O valor de hash calculado.
     */
    @Override
    public int hashCode() {
        return localizationId.hashCode();
    }


    /**
     * Método para comparar este objeto a outro.
     *
     * @param otherNetworkPoint O objeto que será comparado a este.
     * @return O valor 0 se o outro objeto tiver um 'id' da localização igual a este. Um valor menos de 0 se a String do
     * 'id' da localização deste objeto for lexicalmente menor do que o do outro objeto. Um valor mais de 0 se a String
     * do 'id' da localização deste objeto for lexicalmente maior do que o do outro objeto.
     */
    @Override
    public int compareTo(NetworkPoint otherNetworkPoint) {
        return localizationId.compareTo(otherNetworkPoint.localizationId);
    }

    /**
     * Método para imprimir informação sobre este objeto.
     *
     * @return Uma String que representa o objeto.
     */
    @Override
    public String toString() {
        return localizationId;
    }
}
