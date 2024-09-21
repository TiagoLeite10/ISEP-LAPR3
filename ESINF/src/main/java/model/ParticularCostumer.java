package model;

import utils.CodeType;

/**
 * Classe que representa um cliente do 'tipo' particular.
 */
public class ParticularCostumer extends Costumer {

    /**
     * Construtor para inicializar uma instância do objeto ParticularCostumer com os atributos 'Id' da localização,
     * latitude, longitude do ponto de rede e código do cliente do 'tipo' particular.
     *
     * @param localizationId 'Id' da localização do ponto da rede.
     * @param latitude       Latitude da localização do ponto da rede.
     * @param longitude      Longitude da localização do ponto da rede.
     * @param particularCode Código do cliente do 'tipo' particular.
     */
    public ParticularCostumer(String localizationId, Double latitude, Double longitude, String particularCode) {
        super(localizationId, latitude, longitude, particularCode);
        checkParticularCode(particularCode);
    }

    /**
     * Construtor para inicializar uma instância do objeto CompanyCostumer com os atributos 'Id' da localização e código
     * do cliente do 'tipo' particular.
     *
     * @param localizationId     'Id' da localização do ponto da rede.
     * @param costumerIdentifier Código do cliente do 'tipo' particular.
     */
    public ParticularCostumer(String localizationId, String costumerIdentifier) {
        super(localizationId, costumerIdentifier);
        checkParticularCode(costumerIdentifier);
    }

    /**
     * Método para verificar as regras do código do cliente do 'tipo' particular.
     *
     * @param particularCode Código do cliente do 'tipo' particular.
     */
    private void checkParticularCode(String particularCode) {
        char charParticularCode = CodeType.COSTUMER_PARTICULAR.getCharacter();

        if (particularCode.charAt(0) != charParticularCode) {
            throw new IllegalArgumentException("O código do cliente do 'tipo' particular tem de começar obrigatoriamente " +
                    "com a letra " + charParticularCode + ".");
        }
    }

}
