package auxiliariesTestMethods;

import java.util.List;

/**
 * Classe com métodos auxiliares para os testes.
 */
public class AuxiliariesTestMethods {

    /**
     * Método para validar se duas listas tem todos os seus elementos iguais pela mesma ordem.
     *
     * @param list1 Primeira lista.
     * @param list2 Segunda lista.
     * @param <E>   Tipo dos elementos das listas.
     * @return true se todos os elementos das duas listas forem iguais pela mesma ordem, ou false em caso contrário.
     */
    public static <E> boolean assertEqualsLists(List<E> list1, List<E> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }

        int pos = 0;
        int finalPos = list1.size();
        boolean areEquals = true;

        while (pos < finalPos && areEquals) {
            if (!list1.get(pos).equals(list2.get(pos))) {
                areEquals = false;
            }
            pos++;
        }

        return areEquals;
    }

    /**
     * Method that searches for a given item in a list and returns it.
     *
     * @param item The item that the method looks for.
     * @param list The list where the method is searching.
     * @return The item if found, null if not found.
     */
    public static <V> V itemInList(V item, List<V> list) {
        int pos = 0;
        int finalPos = list.size();

        while (pos < finalPos) {
            V itemAnalyze = list.get(pos);

            if (itemAnalyze.equals(item)) {
                return itemAnalyze;
            }
            pos++;
        }

        return item;
    }
}
