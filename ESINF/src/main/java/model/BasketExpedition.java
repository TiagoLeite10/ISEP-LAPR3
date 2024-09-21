package model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Classe que representa a expedição de um cabaz.
 */
public class BasketExpedition {

    /**
     * O cliente para quem se destina o cabaz.
     */
    private Costumer costumer;

    /**
     * O dia em que o cabaz foi expedido.
     */
    private int day;

    /**
     * Lista dos produtos fornecidos para este cabaz.
     */
    private Set<ProductProvided> productsProvided;

    /**
     * Estatísticas do cabaz.
     */
    private BasketStatistics statisticsBasket;

    /**
     * Construtor para inicializar uma nova instância desta classe com os atributos costumer e day.
     *
     * @param costumer O cliente a quem se destina o cabaz.
     * @param day      O dia em que o cabaz é expedido.
     */
    public BasketExpedition(Costumer costumer, int day) {
        this.costumer = costumer;
        this.day = day;
        this.productsProvided = new HashSet<>();
    }

    /**
     * Método para retornar o cliente para quem se destina o cabaz.
     *
     * @return O cliente para quem se destina o cabaz.
     */
    public Costumer getCostumer() {
        return costumer;
    }

    /**
     * Método para retornar o dia em que o cabaz foi expedido.
     *
     * @return Dia em que o cabaz foi expedido.
     */
    public int getDay() {
        return day;
    }

    /**
     * Método para retornar a lista dos produtos fornecidos para este cabaz.
     *
     * @return Lista dos produtos fornecidos para este cabaz.
     */
    public Set<ProductProvided> getProductsProvided() {
        return productsProvided;
    }

    /**
     * Método para retornar as estatísticas do cabaz.
     *
     * @return Estatísticas do cabaz.
     */
    public BasketStatistics getStatisticsBasket() {
        return statisticsBasket;
    }

    /**
     * Função para construir uma lista com os fornecedores dos produtos fornecidos para este cabaz.
     *
     * @return Lista dos fornecedores dos produtos fornecidos para este cabaz.
     */
    public Set<AgriculturalProducer> getProviderList() {
        Set<AgriculturalProducer> providerList = new HashSet<>();

        for (ProductProvided productProvided : productsProvided) {
            AgriculturalProducer provider = productProvided.getProvider();

            if (provider != null) {
                providerList.add(productProvided.getProvider());
            }
        }

        return providerList;
    }

    /**
     * Adiciona um novo produto fornecido ao set de produtos fornecidos.
     *
     * @param productProvided O novo produto fornecido.
     * @return true se ainda não existir esse produto no cabaz de expedição e ele for inserido com sucesso. false se
     * esse produto já existir no cabaz (não sendo inserido novamente).
     */
    public boolean addProductProvided(ProductProvided productProvided) {
        return this.productsProvided.add(productProvided);
    }

    /**
     * Método para calcular as estatísticas do cabaz.
     */
    public void calculateBasketStatistics() {
        int fullySatisfiedProducts = 0;
        int partiallySatisfiedProducts = 0;
        int unsatisfiedProducts = 0;
        Set<AgriculturalProducer> providerList = new HashSet<>();

        for (ProductProvided productProvided : productsProvided) {

            if (productProvided.unsatisfiedProduct()) {
                unsatisfiedProducts++;
            } else {
                if (productProvided.fullySatisfiedProduct()) {
                    fullySatisfiedProducts++;
                } else if (productProvided.partiallySatisfiedProduct()) {
                    partiallySatisfiedProducts++;
                }

                providerList.add(productProvided.getProvider());
            }
        }

        this.statisticsBasket = new BasketStatistics(fullySatisfiedProducts, partiallySatisfiedProducts, unsatisfiedProducts,
                providerList.size());
    }

    /**
     * Método para devolver o hub de entrega deste cabaz (hub mais próximo do cliente).
     *
     * @return O hub de entrega deste cabaz.
     */
    public CompanyCostumer getDeliveryLocation() {
        return costumer.getNearestHub();
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
        if (otherObj == null)
            return false;

        if (getClass() != otherObj.getClass())
            return false;

        BasketExpedition otherBasketExpedition = (BasketExpedition) otherObj;

        boolean equalsProductsProvided = true;
        if (productsProvided.size() != otherBasketExpedition.productsProvided.size()) {
            equalsProductsProvided = false;
        } else {
            Iterator<ProductProvided> thisProductsProvided = productsProvided.iterator();

            while (thisProductsProvided.hasNext() && equalsProductsProvided) {
                Iterator<ProductProvided> otherProductsProvided = otherBasketExpedition.productsProvided.iterator();
                boolean sameProductProvidedFound = false;
                ProductProvided thisProductProv = thisProductsProvided.next();

                while (otherProductsProvided.hasNext() && !sameProductProvidedFound) {
                    ProductProvided otherProductProv = otherProductsProvided.next();

                    if (thisProductProv.equals(otherProductProv)) {
                        sameProductProvidedFound = true;
                    }
                }

                if (!sameProductProvidedFound)
                    equalsProductsProvided = false;
            }
        }

        return costumer.equals(otherBasketExpedition.costumer) && day == otherBasketExpedition.day && equalsProductsProvided;
    }

}
