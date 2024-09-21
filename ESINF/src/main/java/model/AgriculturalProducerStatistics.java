package model;

/**
 * Classe que representa as estatísticas do produtor.
 */
public class AgriculturalProducerStatistics {

    /**
     * O nº de cabazes fornecidos totalmente.
     */
    private int basketFullySupplied;

    /**
     * O nº de cabazes fornecidos parcialmente.
     */
    private int basketPartiallySupplied;

    /**
     * O nº de clientes distintos fornecidos.
     */
    private int distinctClientsProvided;

    /**
     * O nº de produtos totalmente esgotados.
     */
    private int completelySoldProducts;

    /**
     * O nº de hubs fornecidos.
     */
    private int suppliedHubs;

    /**
     * Construtor para inicializar uma nova instância desta classe com os atributos passados por parametro.
     *
     * @param basketFullySupplied     O nº de cabazes fornecidos totalmente.
     * @param basketPartiallySupplied O nº de cabazes fornecidos parcialmente.
     * @param distinctClientsProvided O nº de clientes distintos fornecidos.
     * @param completelySoldProducts  O nº de produtos totalmente esgotados.
     * @param suppliedHubs            O nº de hubs fornecidos.
     */
    public AgriculturalProducerStatistics(int basketFullySupplied, int basketPartiallySupplied, int distinctClientsProvided, int completelySoldProducts, int suppliedHubs) {
        this.basketFullySupplied = basketFullySupplied;
        this.basketPartiallySupplied = basketPartiallySupplied;
        this.distinctClientsProvided = distinctClientsProvided;
        this.completelySoldProducts = completelySoldProducts;
        this.suppliedHubs = suppliedHubs;
    }

    /**
     * Construtor para inicializar uma nova instância desta classe sem parametros.
     **/
    public AgriculturalProducerStatistics() {
    }

    /**
     * Metódo incrementador da variavel basketFullySupplied.
     */
    public void addBasketFullySupplied() {
        this.basketFullySupplied++;
    }

    /**
     * Metódo incrementador da variavel basketPartiallySupplied.
     */
    public void addBasketPartiallySupplied() {
        this.basketPartiallySupplied++;
    }

    /**
     * Metódo alterar o valor da variavel distinctClientsProvided.
     */
    public void setDistinctClientsProvided(int distinctClientsProvided) {
        this.distinctClientsProvided = distinctClientsProvided;
    }

    /**
     * Metódo alterar o valor da variavel completelySoldProducts.
     */
    public void setCompletelySoldProducts(int completelySoldProducts) {
        this.completelySoldProducts = completelySoldProducts;
    }

    /**
     * Metódo alterar o valor da variavel suppliedHubs.
     */
    public void setSuppliedHubs(int suppliedHubs) {
        this.suppliedHubs = suppliedHubs;
    }

    /**
     * Metódo para verificar se este objeto atual é igual a outro objeto do mesmo tipo.
     *
     * @param obj O objeto a ser comparado com este atual.
     * @return true se o outro objeto representa as estatísticas do cabaz igual a este, caso contrário
     * devolve false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        AgriculturalProducerStatistics agriculturalProducerStatistics = (AgriculturalProducerStatistics) obj;

        return (basketFullySupplied == agriculturalProducerStatistics.basketFullySupplied &&
                basketPartiallySupplied == agriculturalProducerStatistics.basketPartiallySupplied &&
                distinctClientsProvided == agriculturalProducerStatistics.distinctClientsProvided &&
                completelySoldProducts == agriculturalProducerStatistics.completelySoldProducts &&
                suppliedHubs == agriculturalProducerStatistics.suppliedHubs);
    }
}
