package utils;

/**
 * Enumerador que contêm a posição de cada coluna do ficheiro de cliente-produtores.
 */
public enum IndexColumnsFileProducerCustomer {
    /**
     * Posição de cada coluna do ficheiro de cliente-produtores.
     */
    LOCALIZATION_ID(0), LATITUDE(1), LONGITUDE(2), NAME(3), TOTAL_COLUMNS(4);

    /**
     * Posição da coluna no ficheiro.
     */
    private final int index;

    /**
     * Construtor para inicializar uma instância do objeto IndexColumnsFileProducerCustomer com o atributo da posição da coluna.
     *
     * @param index Posição da coluna no ficheiro.
     */
    IndexColumnsFileProducerCustomer(int index) {
        this.index = index;
    }

    /**
     * Método para devolver a posição da coluna no ficheiro.
     *
     * @return Posição da coluna no ficheiro.
     */
    public int getIndex() {
        return index;
    }
}
