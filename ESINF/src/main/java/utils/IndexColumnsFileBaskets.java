package utils;

/**
 * Enumerador que contêm a posição de cada coluna do ficheiro dos cabazes.
 */
public enum IndexColumnsFileBaskets {

    /**
     * Índice do identificador de produtor ou cliente no ficheiro.
     */
    COSTUMERS_PRODUCERS(0),
    /**
     * Índice onde se encontra o dia em que o cabaz foi lançado.
     */
    DAY(1),
    /**
     * Índice onde começa a identificação dos produtos.
     */
    START_PRODUCT_INDEX(2);

    /**
     * Valor do atributo.
     */
    private final int value;

    /**
     * Construtor para inicializar uma instância do objeto IndexColumnsFileBaskets com o valor do atributo.
     *
     * @param value O valor para o atributo.
     */
    IndexColumnsFileBaskets(int value) {
        this.value = value;
    }

    /**
     * Método para devolver o valor de um dado atributo.
     *
     * @return O valor do atributo.
     */
    public int getValue() {
        return value;
    }

}
