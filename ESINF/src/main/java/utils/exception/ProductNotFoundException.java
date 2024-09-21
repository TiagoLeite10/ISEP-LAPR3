package utils.exception;

/**
 * Classe para lançar uma exceção quando um dado produto não é encontrado (em RunTime)
 */
public class ProductNotFoundException extends RuntimeException {
    /**
     * Construtor para lançar uma mensagem por defeito.
     */
    public ProductNotFoundException() {
        super("O produto do qual pretende adicionar quantidade não existe!");
    }

    /**
     * Construtor para lançar a exceção com uma mensagem personalizada.
     *
     * @param message A mensagem personalizada.
     */
    public ProductNotFoundException(String message) {
        super(message);
    }
}
