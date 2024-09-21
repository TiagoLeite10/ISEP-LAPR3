package utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe para realizar testes à classe FileReaderType1.
 */
public class FileReaderType1Test {

    /**
     * Teste ao método para ler dados de um ficheiro em que é esperado que o ficheiro no caminho utilizado não seja
     * encontrado, lançando assim uma exceção.
     *
     * @throws FileNotFoundException Exceção lançada caso não exista o ficheiro com o nome utilizado.
     */
    @Test(expected = FileNotFoundException.class)
    public void readFileTest1() throws FileNotFoundException {
        new FileReaderType1().readFile("files/FicheiroInexistente.csv", true);
    }

    /**
     * Teste ao método para ler dados de um ficheiro em que é passado o valor null em vez de uma String com o caminho
     * para um dado ficheiro.
     *
     * @throws FileNotFoundException Exceção lançada caso não exista o ficheiro com o nome utilizado.
     */
    @Test(expected = FileNotFoundException.class)
    public void readFileTest2() throws FileNotFoundException {
        new FileReaderType1().readFile(null, true);
    }

    /**
     * Teste ao método para ler dados de um ficheiro em que é esperado que o ficheiro no caminho utilizado apenas
     * contenha o header e retorne uma lista vazia.
     *
     * @throws FileNotFoundException Exceção lançada caso não exista o ficheiro com o nome utilizado.
     */
    @Test
    public void readFileTest3() throws FileNotFoundException {
        String filePathTest = "files/test/clientes-produtores_small_test3.csv";

        List<String[]> contentFile = new FileReaderType1().readFile(filePathTest, true);

        assertTrue(contentFile.isEmpty());
    }

    /**
     * Teste ao método readFile em que é verificado se o valor da lista de arrays de 'strings' gerado através da leitura do
     * ficheiro é igual ao esperado.
     */
    @Test
    public void readFileTest4() throws FileNotFoundException {
        String filePathTest = "files/test/distancias_small_test2.csv";

        List<String[]> result = new FileReaderType1().readFile(filePathTest, true);
        List<String[]> expectedResult = new ArrayList<>();
        String sentence;

        sentence = "CT10,CT6,63448";
        expectedResult.add(sentence.split(","));

        sentence = "CT10,CT4,67584";
        expectedResult.add(sentence.split(","));

        sentence = "CT10,CT1,110848";
        expectedResult.add(sentence.split(","));

        sentence = "CT10,CT6,125041";
        expectedResult.add(sentence.split(","));

        sentence = "CT10,CT4,50467";
        expectedResult.add(sentence.split(","));

        sentence = "CT10,CT1,62877";
        expectedResult.add(sentence.split(","));

        for (int i = 0; i < result.size(); i++) {
            assertArrayEquals(expectedResult.get(i), result.get(i));
        }
    }
}
