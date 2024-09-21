package model;

/**
 * Classe que representa as estatísticas do cabaz.
 */
public class BasketStatistics {
    /**
     * O nº de produtos totalmente satisfeitos.
     */
    private int fullySatisfiedProducts;

    /**
     * O nº de produtos parcialmente satisfeitos.
     */
    private int partiallySatisfiedProducts;

    /**
     * O nº de produtos não satisfeitos.
     */
    private int unsatisfiedProducts;

    /**
     * Percentagem total do cabaz satisfeito.
     */
    private double totalPercentageBasketSatisfied;

    /**
     * O nº de produtores que forneceram o cabaz.
     */
    private int producersProvidedBasket;

    /**
     * Construtor para inicializar uma nova instância desta classe com os atributos productsFullySatisfied, partiallySatisfiedProducts,
     * unsatisfiedProducts e producersProvidedBasket.
     *
     * @param fullySatisfiedProducts     O nº de produtos totalmente satisfeitos.
     * @param partiallySatisfiedProducts O nº de produtos parcialmente satisfeitos.
     * @param unsatisfiedProducts        O nº de produtos não satisfeitos.
     * @param producersProvidedBasket    O nº de produtores que forneceram o cabaz.
     */
    public BasketStatistics(int fullySatisfiedProducts, int partiallySatisfiedProducts, int unsatisfiedProducts, int producersProvidedBasket) {
        this.fullySatisfiedProducts = fullySatisfiedProducts;
        this.partiallySatisfiedProducts = partiallySatisfiedProducts;
        this.unsatisfiedProducts = unsatisfiedProducts;
        this.totalPercentageBasketSatisfied = calculatePercentageBasketSatisfied();
        this.producersProvidedBasket = producersProvidedBasket;
    }

    /**
     * Função para calcular a percentagem total do cabaz satisfeito.
     *
     * @return Percentagem total do cabaz satisfeito.
     */
    private double calculatePercentageBasketSatisfied() {
        int totalProducts = fullySatisfiedProducts + partiallySatisfiedProducts + unsatisfiedProducts;

        return (double) fullySatisfiedProducts / totalProducts * 100;
    }

    /**
     * Função para verificar se um cabaz é totalmente satisfeito.
     *
     * @return Retorna true se o cabaz é totalmente satisfeito, caso contrário devolve false.
     */
    public boolean fullySatisfiedBasket() {
        return fullySatisfiedProducts > 0 && partiallySatisfiedProducts == 0 && unsatisfiedProducts == 0;
    }

    /**
     * Função para verificar se um cabaz é parcialmente satisfeito.
     *
     * @return Retorna true se o cabaz é parcialmente satisfeito, caso contrário devolve false.
     */
    public boolean partiallySatisfiedBasket() {
        return partiallySatisfiedProducts > 0 || unsatisfiedProducts > 0;
    }

    /**
     * Método para verificar se este objeto atual é igual a outro objeto do mesmo tipo.
     *
     * @param obj O objeto a ser comparado com este atual.
     * @return true se o outro objeto representa as estatísticas do cabaz igual a este, caso contrário devolve false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        BasketStatistics otherBasketStatistics = (BasketStatistics) obj;

        return (fullySatisfiedProducts == otherBasketStatistics.fullySatisfiedProducts &&
                partiallySatisfiedProducts == otherBasketStatistics.partiallySatisfiedProducts &&
                unsatisfiedProducts == otherBasketStatistics.unsatisfiedProducts &&
                totalPercentageBasketSatisfied == otherBasketStatistics.totalPercentageBasketSatisfied &&
                producersProvidedBasket == otherBasketStatistics.producersProvidedBasket);
    }
}
