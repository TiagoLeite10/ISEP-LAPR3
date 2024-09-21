package dev;

import model.WateringController;
import utils.FileToWateringController;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Classe principal
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Indique o caminho do ficheiro onde se encontra o plano de rega: ");
        String filePathWatering = scanner.nextLine();

        try {
            List<WateringController> wateringControllersList = FileToWateringController.readFileToWateringController(filePathWatering);
            System.out.println("Controlador da rega carregado com sucesso!\n");

            String answer;
            do {
                System.out.println("Deseja verificar se o controlador está a regar ou não? (S - Sim) (Outro caracter - Terminar verificação)");
                answer = scanner.nextLine();

                if (answer.equalsIgnoreCase("S")) {
                    viewWateringController(wateringControllersList);
                }
            } while (answer.equalsIgnoreCase("S"));

        } catch (FileNotFoundException e) {
            System.out.println("Não existe nenhum ficheiro no caminho indicado.");
        }
    }

    /**
     * Método para verificar e imprimir se um controlador está a regar ou não
     *
     * @param wateringControllersList Uma lista com os controladores de rega
     */
    private static void viewWateringController(List<WateringController> wateringControllersList) {
        boolean isWatering = false;

        for (WateringController wc : wateringControllersList) {
            if (wc.getIsWatering()) {
                System.out.println("*** Controlador da rega está a regar ***");
                System.out.println("Setor: " + wc.getPortion().getSector());
                System.out.println("Tempo restante: : " + wc.minutesOfWateringLeft());

                isWatering = true;
            }
        }

        if (!isWatering) {
            System.out.println("O controlador da rega não está a regar!\n");
        }
    }
}
