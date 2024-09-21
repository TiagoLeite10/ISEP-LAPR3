package utils.exception;

/**
 * Classe para lançar uma exceção quando uma dada quantidade de produto está inválida (em RunTime)
 */
public class InvalidProductQuantityException extends RuntimeException {

    /**
     * Construtor para lançar uma mensagem por defeito.
     */
    public InvalidProductQuantityException() {
        super("Quantidade de produto inválida.");
    }

    /**
     * Construtor para lançar a exceção com uma mensagem personalizada.
     *
     * @param message A mensagem personalizada.
     */
    public InvalidProductQuantityException(String message) {
        super(message);
    }
}
