package utils;

/**
 * Enumerador que contêm a posição de cada coluna do ficheiro das distâncias.
 */
public enum IndexColumnsFileDistances {
    /**
     * Posição de cada coluna do ficheiro das distâncias.
     */
    LOCALIZATION_ID_1(0), LOCALIZATION_ID_2(1), DISTANCES(2), TOTAL_COLUMNS(3);

    /**
     * Posição da coluna no ficheiro.
     */
    private final int index;

    /**
     * Construtor para inicializar uma instância do objeto IndexColumnsFileDistances com o atributo da posição da coluna.
     *
     * @param index Posição da coluna no ficheiro.
     */
    IndexColumnsFileDistances(int index) {
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
