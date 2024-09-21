package model;

/**
 * Classe que representa as estatísticas do cliente.
 */
public class CostumerStatistics {

    /**
     * O nº de cabazes totalmente satisfeitos.
     */
    private int fullySatisfiedBaskets;

    /**
     * O nº de cabazes parcialmente satisfeitos.
     */
    private int partiallySatisfiedBaskets;

    /**
     * O nº de fornecedores distintos que forneceram todos os seus cabazes.
     */
    private int differentSupliersGiveBaskets;

    /**
     * Construtor para inicializar uma nova instância, sem atributos, desta classe.
     */
    public CostumerStatistics() {
    }

    /**
     * Construtor para inicializar uma nova instância desta classe com os atributos fullySatisfiedBaskets, partiallySatisfiedBaskets, differentSupliersGiveBaskets.
     *
     * @param fullySatisfiedBaskets
     * @param partiallySatisfiedBaskets
     * @param differentSupliersGiveBaskets
     */
    public CostumerStatistics(int fullySatisfiedBaskets, int partiallySatisfiedBaskets, int differentSupliersGiveBaskets) {
        this.fullySatisfiedBaskets = fullySatisfiedBaskets;
        this.partiallySatisfiedBaskets = partiallySatisfiedBaskets;
        this.differentSupliersGiveBaskets = differentSupliersGiveBaskets;
    }

    /**
     * Metodo incrementador da variavel fullySatisfiedBaskets.
     */
    public void addFullySatisfiedBaskets() {
        this.fullySatisfiedBaskets++;
    }

    /**
     * Metodo incrementador da variavel partiallySatisfiedBaskets.
     */
    public void addPartiallySatisfiedBaskets() {
        this.partiallySatisfiedBaskets++;
    }

    /**
     * Metodo alterar o valor da variavel differentSupliersGiveBaskets.
     *
     * @param differentSupliersGiveBaskets
     */
    public void setDifferentSupliersGiveBaskets(int differentSupliersGiveBaskets) {
        this.differentSupliersGiveBaskets = differentSupliersGiveBaskets;
    }

    /**
     * Método para verificar se este objeto atual é igual a outro objeto do mesmo tipo.
     *
     * @param obj O objeto a ser comparado com este atual.
     * @return true se o outro objeto representa as estatísticas do cliente igual a este, caso contrário devolve false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || (!(obj instanceof CostumerStatistics that))) {
            return false;
        }

        return (fullySatisfiedBaskets == that.fullySatisfiedBaskets &&
                partiallySatisfiedBaskets == that.partiallySatisfiedBaskets &&
                differentSupliersGiveBaskets == that.differentSupliersGiveBaskets);
    }

}