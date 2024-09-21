package model;

/**
 * Classe que representa uma parcela
 */
public class Portion implements Comparable<Portion> {

    /**
     * Nome da parcela
     */
    private String sector;

    /**
     * Constrói um objeto deste tipo com a inicialização do nome
     *
     * @param sector Nome da parcela
     */
    public Portion(String sector) {
        this.sector = sector;
    }

    /**
     * Função para retornar o nome da parcela
     *
     * @return Nome da parcela
     */
    public String getSector() {
        return sector;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Portion otherItem = (Portion) obj;

        return sector.equals(otherItem.sector);

    }

    @Override
    public int compareTo(Portion o) {
        return sector.compareTo(o.sector);
    }
}
