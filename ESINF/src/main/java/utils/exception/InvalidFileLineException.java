package utils.exception;

/**
 * Classe para lançar uma exceção quando alguma linha do ficheiro não se encontra válida (em RunTime)
 */
public class InvalidFileLineException extends RuntimeException {

    /**
     * Construtor para lançar uma mensagem por defeito.
     */
    public InvalidFileLineException() {
        super("Foi encontrado um erro numa linha do ficheiro!");
    }

    /**
     * Construtor para lançar a exceção com uma mesagem a indicar a linha do ficheiro onde erro foi encontrado.
     *
     * @param line A linha do ficheiro que contém erro.
     */
    public InvalidFileLineException(int line) {
        super("Verifique as colunas existentes na linha " + line + " do ficheiro! Por favor, corrija o erro.");
    }

    /**
     * Construtor para lançar a exceção com uma mensagem personalizada.
     *
     * @param message A mensagem personalizada.
     */
    public InvalidFileLineException(String message) {
        super(message);
    }

    /**
     * Construtor para lançar a exceção indicando a linha do ficheiro onde o erro foi encontrado e em que coluna
     * (atributo).
     *
     * @param line      A linha do ficheiro com erro.
     * @param attribute O atributo com erro.
     */
    public InvalidFileLineException(int line, String attribute) {
        super("An error was found in line " + line + " of the file! " + attribute + " is incorrect! Please, " +
                "correct it!");
    }

}
