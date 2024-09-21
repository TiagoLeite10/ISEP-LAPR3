package model;

/**
 * Classe que representa a estatistica do hub.
 */
public class HubStatistics {

    /**
     * O nº de clientes distintos que recolhem cabazes em cada hub.
     */
    private int differentCostumersFetchHub;

    /**
     * O nº de produtores distintos que fornecem cabazes para o hub.
     */
    private int differentProducersGiveHub;

    /**
     * Construtor para inicializar uma nova instância desta classe com os atributos differentCostumersFetchHub
     * e differentProducersGiveHub.
     *
     * @param differentCostumersFetchHub O nº de clientes distintos que recolhem cabazes em cada hub.
     * @param differentProducersGiveHub  O nº de produtores distintos que fornecem cabazes para o hub.
     */
    public HubStatistics(int differentCostumersFetchHub, int differentProducersGiveHub) {
        this.differentCostumersFetchHub = differentCostumersFetchHub;
        this.differentProducersGiveHub = differentProducersGiveHub;
    }

    /**
     * Método para verificar se este objeto atual é igual a outro objeto do mesmo tipo.
     *
     * @param o O objeto a ser comparado com este atual.
     * @return true se o outro objeto representa as estatísticas do hub igual a este, caso contrário devolve false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        HubStatistics that = (HubStatistics) o;
        return differentCostumersFetchHub == that.differentCostumersFetchHub && differentProducersGiveHub == that.differentProducersGiveHub;
    }
}
