package utils.exception;

/**
 * Classe para lançar uma exceção quando um dado ponto na rede de distribuição não é encontrado (em RunTime)
 */
public class NetworkPointNotFoundException extends RuntimeException {

    /**
     * Construtor para lançar uma mensagem por defeito.
     */
    public NetworkPointNotFoundException() {
        super("O ponto de rede não é válido!");
    }

    /**
     * Construtor para lançar a exceção com uma mesagem a indicar a linha do ficheiro onde erro foi encontrado.
     *
     * @param line A linha do ficheiro que contém erro.
     */
    public NetworkPointNotFoundException(int line) {
        super("O ponto de rede na linha " + line + " do ficheiro, não é valido!");
    }

    /**
     * Construtor para lançar a exceção com uma mensagem personalizada.
     *
     * @param message A mensagem personalizada.
     */
    public NetworkPointNotFoundException(String message) {
        super(message);
    }
}
