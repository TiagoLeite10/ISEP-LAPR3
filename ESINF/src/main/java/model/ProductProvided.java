package model;

/**
 * Classe que representa um produto fornecido.
 */
public class ProductProvided {

    /**
     * O produto requerido.
     */
    private Product product;

    /**
     * Quantidade requerida do produto.
     */
    private double requestedQuantity;

    /**
     * Quantidade entregue do produto.
     */
    private double deliveredQuantity;

    /**
     * O fornecedor do produto.
     */
    private AgriculturalProducer provider;

    /**
     * Construtor para inicializar uma nova instância desta classe com os atributos product, requestedQuantity,
     * deliveredQuantity e provider.
     *
     * @param product           O produto fornecido.
     * @param requestedQuantity A quantidade de produto requesitada.
     * @param deliveredQuantity A quantidade de produto entregue.
     * @param provider          O fornecedor do produto.
     */
    public ProductProvided(Product product, double requestedQuantity, double deliveredQuantity, AgriculturalProducer provider) {
        checkProductProvided(product);
        this.product = product;
        this.requestedQuantity = requestedQuantity;
        this.deliveredQuantity = deliveredQuantity;
        this.provider = provider;
    }

    /**
     * Método para verificar se o produto não é nulo.
     *
     * @param product O produto.
     */
    private void checkProductProvided(Product product) {
        if (product == null)
            throw new IllegalArgumentException("O produto não pode ser nulo.");
    }

    /**
     * Função para verificar se um produto é totalmente satisfeito.
     *
     * @return Retorna true se o produto for totalmente satisfeito, caso contrário devolve false.
     */
    public boolean fullySatisfiedProduct() {
        return requestedQuantity == deliveredQuantity;
    }

    /**
     * Função para verificar se um produto é parcialmente satisfeito.
     *
     * @return Retorna true se o produto for parcialmente satisfeito, caso contrário devolve false.
     */
    public boolean partiallySatisfiedProduct() {
        return requestedQuantity != deliveredQuantity && deliveredQuantity != 0;
    }

    /**
     * Função para verificar se um produto é não satisfeito.
     *
     * @return Retorna true se o produto for não satisfeito, caso contrário devolve false.
     */
    public boolean unsatisfiedProduct() {
        return deliveredQuantity == 0;
    }

    /**
     * Método para retornar o fornecedor do produto.
     *
     * @return O fornecedor do produto.
     */
    public AgriculturalProducer getProvider() {
        return provider;
    }

    /**
     * Método para verificar se este objeto atual é igual a outro objeto do mesmo tipo.
     *
     * @param otherObj O objeto a ser comparado com este atual.
     * @return true se o outro objeto representa os mesmo valores dos atributos iguais a este, caso contrário
     * devolve false.
     */
    @Override
    public boolean equals(Object otherObj) {
        if (otherObj == null) {
            return false;
        }

        if (getClass() != otherObj.getClass()) {
            return false;
        }

        ProductProvided otherProductProvided = (ProductProvided) otherObj;

        boolean providerEquals = (provider != null && otherProductProvided.provider != null
                && provider.getProducerCode().equals(otherProductProvided.provider.getProducerCode()))
                || (provider == null && otherProductProvided.provider == null);

        return product.equals(otherProductProvided.product)
                && requestedQuantity == otherProductProvided.requestedQuantity
                && deliveredQuantity == otherProductProvided.deliveredQuantity
                && providerEquals;
    }

    /**
     * Método para gerar o hashcode deste produto fornecido. O haschode é gerado pelo produto.
     *
     * @return O hascode do produto.
     */
    @Override
    public int hashCode() {
        return product.hashCode();
    }
}
