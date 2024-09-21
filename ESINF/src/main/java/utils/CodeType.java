package utils;

/**
 * Enumerador que representa o character dos tipos de códigos existentes.
 */
public enum CodeType {
    /**
     * Character dos tipos de códigos existentes.
     */
    COSTUMER_PARTICULAR('C'), COSTUMER_COMPANY('E'), PRODUCER('P');

    /**
     * Character do tipo de código.
     */
    private final char character;

    /**
     * Construtor para inicializar uma instância do objeto CodeType com o atributo character do 'tipo' de código.
     *
     * @param character Character do 'tipo' de código.
     */
    CodeType(char character) {
        this.character = character;
    }

    /**
     * Método para devolver o character do tipo de código.
     *
     * @return Character do tipo de código.
     */
    public char getCharacter() {
        return character;
    }
}
