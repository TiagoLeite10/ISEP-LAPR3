package utils;

import java.io.IOException;
import java.util.List;

/**
 * 'Interface' que implementa um método para ler dados de um ficheiro de sistema legado.
 */
public interface FileReader {
    /**
     * Método que lê os dados de um ficheiro e os armazena numa lista de arrays de 'strings'.
     *
     * @return Uma lista de arrays de 'strings' que contém as informações de cada linha do ficheiro
     */
    List<String[]> readFile(String filePath) throws IOException;
}
