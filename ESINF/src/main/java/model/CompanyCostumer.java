package model;

import utils.CodeType;

/**
 * Classe que representa um cliente do 'tipo' empresa.
 */
public class CompanyCostumer extends Costumer {

    /**
     * Representa se o cliente do 'tipo' empresa é um hub
     */
    private boolean hub;

    private HubStatistics hubStatistics;

    /**
     * Construtor para inicializar uma instância do objeto CompanyCostumer com os atributos 'Id' da localização,
     * latitude, longitude do ponto de rede e código do cliente do 'tipo' empresa.
     *
     * @param localizationId 'Id' da localização do ponto da rede.
     * @param latitude       Latitude da localização do ponto da rede.
     * @param longitude      Longitude da localização do ponto da rede.
     * @param companyCode    Código do cliente do 'tipo' empresa.
     */
    public CompanyCostumer(String localizationId, Double latitude, Double longitude, String companyCode) {
        super(localizationId, latitude, longitude, companyCode);
        checkCompanyCode(companyCode);
        this.hub = false;
    }

    /**
     * Construtor para inicializar uma instância do objeto CompanyCostumer com os atributos, 'Id' da localização do
     * ponto de rede e o código do cliente do 'tipo' empresa.
     *
     * @param localizationId 'Id' da localização do ponto da rede.
     * @param companyCode    Código do cliente do 'tipo' empresa.
     */
    public CompanyCostumer(String localizationId, String companyCode) {
        super(localizationId, companyCode);
        checkCompanyCode(companyCode);
        this.hub = false;
    }

    /**
     * Método para verificar as regras do código do cliente do 'tipo' empresa.
     *
     * @param companyCode Código do cliente do 'tipo' empresa.
     */
    private void checkCompanyCode(String companyCode) {
        char charCompanyCode = CodeType.COSTUMER_COMPANY.getCharacter();

        if (companyCode.charAt(0) != charCompanyCode) {
            throw new IllegalArgumentException("O código do cliente do 'tipo' empresa tem de começar obrigatoriamente com " +
                    "a letra " + charCompanyCode + ".");
        }
    }

    /**
     * Método para definir se o cliente do 'tipo' empresa é um huh.
     *
     * @param hub true se for um hub, caso contrário falso.
     */
    public void setHub(boolean hub) {
        this.hub = hub;
    }

    public void setHubStatistics(HubStatistics hubStatistics) {
        this.hubStatistics = hubStatistics;
    }

    public HubStatistics getHubStatistics() {
        return hubStatistics;
    }

    /**
     * Função para indicar se o cliente do 'tipo' empresa é um huh.
     *
     * @return true se for um hub, caso contrário falso.
     */
    public boolean isHub() {
        return hub;
    }

}
