package model;

import utils.CodeType;

/**
 * Classe que representa um produtor agrícola.
 */
public class AgriculturalProducer extends NetworkPoint {
    /**
     * Código do produtor agrícola.
     */
    private final String producerCode;

    /**
     * Estatísticas do agricultor.
     */
    private AgriculturalProducerStatistics agriculturalProducerStatistics;

    /**
     * Construtor para inicializar uma instância do objeto AgriculturalProducer com os atributos 'Id' da localização, latitude,
     * longitude do ponto de rede e código do produtor agrícola.
     *
     * @param localizationId 'Id' da localização do ponto da rede.
     * @param latitude       Latitude da localização do ponto da rede.
     * @param longitude      Longitude da localização do ponto da rede.
     * @param producerCode   Código do produtor agrícola.
     */
    public AgriculturalProducer(String localizationId, Double latitude, Double longitude, String producerCode) {
        super(localizationId, latitude, longitude);

        checkProducerCode(producerCode);
        this.producerCode = producerCode;
        this.agriculturalProducerStatistics = new AgriculturalProducerStatistics();
    }

    /**
     * Método para devolver o código do produtor agrícula.
     *
     * @return O código do produtor agrícula.
     */
    public String getProducerCode() {
        return producerCode;
    }

    public AgriculturalProducerStatistics getAgriculturalProducerStatistics() {
        return agriculturalProducerStatistics;
    }

    /**
     * Método para verificar as regras do código do produtor agrícola.
     *
     * @param producerCode Código do produtor agrícola.
     */
    private void checkProducerCode(String producerCode) {
        char charProducerCode = CodeType.PRODUCER.getCharacter();

        if (producerCode.charAt(0) != charProducerCode) {
            throw new IllegalArgumentException("O código do produtor agrícola tem de começar obrigatoriamente com a letra "
                    + charProducerCode + ".");
        }
    }

}
