package utils;

/**
 * Enumerador que contêm a posição de cada coluna do ficheiro da rega.
 */
public enum IndexColumnsFileWatering {
    /**
     * Posição de cada coluna do ficheiro da rega.
     */
    START_TIME(0), END_TIME(1), PORTION(0), DURATION(1), REGULARITY(2);

    /**
     * Posição da coluna no ficheiro.
     */
    private final int index;

    /**
     * Construtor para inicializar uma instância do objeto IndexColumnsFileWatering com o atributo da posição da coluna.
     *
     * @param index Posição da coluna no ficheiro.
     */
    IndexColumnsFileWatering(int index) {
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
