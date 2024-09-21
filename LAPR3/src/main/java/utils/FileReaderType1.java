package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável pela leitura do ficheiro.
 */
public class FileReaderType1 implements FileReader {

    /**
     * O character que separa os dados.
     */
    private final String DATA_SEPARATOR = ",";

    /**
     * Método que lê os dados de um ficheiro e os armazena numa lista de arrays de 'strings'.
     *
     * @param filePath Caminho do ficheiro.
     * @return Uma lista de arrays de 'strings' que contém as informações de cada linha do ficheiro.
     */
    public List<String[]> readFile(String filePath) throws FileNotFoundException {
        if (filePath == null) {
            throw new FileNotFoundException();
        }

        File file = new File(filePath);
        Scanner scannerFile = new Scanner(file);

        List<String[]> fileData = new ArrayList<>();
        String sentence;

        while (scannerFile.hasNext()) {
            sentence = scannerFile.nextLine();
            fileData.add(sentence.split(DATA_SEPARATOR));
        }

        scannerFile.close();

        return fileData;
    }
}
