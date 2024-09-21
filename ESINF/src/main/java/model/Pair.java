package model;

/**
 * Classe para representar um objeto com uma chave e um valor de qualquer tipo.
 *
 * @param <T> Tipo do objeto chave.
 * @param <S> Tipo do objeto valor.
 */
public class Pair<T, S extends Integer> implements Comparable<Pair<T, S>> {

    /**
     * Chave do objeto.
     */
    private T key;

    /**
     * Valor do objeto.
     */
    private S value;

    /**
     * Construtor para inicializar o objeto com a chave e o valor desejado.
     *
     * @param key   O objeto chave.
     * @param value O objeto valor.
     */
    public Pair(T key, S value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Método para retornar o objeto chave.
     *
     * @return O objeto chave.
     */
    public T getKey() {
        return key;
    }

    /**
     * Método para retornar o objeto valor.
     *
     * @return O objeto valor.
     */
    public S getValue() {
        return value;
    }

    /**
     * Método para definir o objeto valor.
     *
     * @param value O objeto valor.
     */
    public void setValue(S value) {
        this.value = value;
    }

    /**
     * Método para verificar se este objeto atual é igual a outro objeto do mesmo tipo.
     *
     * @param otherObj O objeto a ser comparado com este atual.
     * @return true se o outro objeto representa uma chave igual a este, caso contrário devolve false.
     */
    @Override
    public boolean equals(Object otherObj) {
        if (otherObj == null) {
            return false;
        }

        if (getClass() != otherObj.getClass()) {
            return false;
        }

        Pair<T, S> otherPair = (Pair<T, S>) otherObj;
        return (key.equals(otherPair.key));
    }

    /**
     * Método para comparar este objeto a outro.
     *
     * @param o O objeto que será comparado a este.
     * @return O valor 0 se o outro objeto tiver um valor inteiro igual a este. Um valor menos de 0 se o valor inteiro
     * deste objeto for menor que o do outro objeto. Um valor maior do que 0 se o valor inteiro deste objeto for maior
     * que o do outro objeto.
     */
    @Override
    public int compareTo(Pair<T, S> o) {
        return value.intValue() - o.value.intValue();
    }
}