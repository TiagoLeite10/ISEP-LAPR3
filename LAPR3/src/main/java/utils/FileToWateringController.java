package utils;

import model.Portion;
import model.WateringController;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por converter o ficheiro de rega num controlador de rega
 */
public class FileToWateringController {

    /**
     * Método para converter o ficheiro de numa lista de controladores de rega
     *
     * @param filePathWatering Caminho do ficheiro de rega
     * @return Uma lista com os controladores de rega
     */
    public static List<WateringController> readFileToWateringController(String filePathWatering) throws FileNotFoundException {
        List<WateringController> wateringControllersList = new ArrayList<>();

        List<String[]> contentFile = new FileReaderType1().readFile(filePathWatering);

        boolean header = false;

        ArrayList<LocalTime> timesToWatering = new ArrayList<>();

        for (String[] columns : contentFile) {
            if (!header) {
                timesToWatering.add(parseStringToLocalTime(columns[IndexColumnsFileWatering.START_TIME.getIndex()]));
                timesToWatering.add(parseStringToLocalTime(columns[IndexColumnsFileWatering.END_TIME.getIndex()]));

                header = true;
            } else {
                Portion portion = new Portion(columns[IndexColumnsFileWatering.PORTION.getIndex()]);
                LocalDateTime creationDate = LocalDateTime.now();
                int duration = parseStringToInteger(columns[IndexColumnsFileWatering.DURATION.getIndex()]);
                char regularity = columns[IndexColumnsFileWatering.REGULARITY.getIndex()].charAt(0);

                WateringController wateringController = new WateringController(portion, creationDate, timesToWatering, duration, regularity);

                wateringControllersList.add(wateringController);
            }
        }

        return wateringControllersList;
    }

    /**
     * Função para converter uma 'string' num número inteiro. Caso não seja possível é retornado o valor 0
     *
     * @param number String a converter
     * @return Número do tipo inteiro
     */
    private static Integer parseStringToInteger(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException n) {
            return 0;
        }
    }

    /**
     * Função para converter uma 'string' numa hora
     *
     * @param localTime String a converter
     * @return Hora dp tipo LocalTime
     */
    private static LocalTime parseStringToLocalTime(String localTime) {
        String[] split = localTime.split(":");

        int hour = parseStringToInteger(split[0]);
        int minute = parseStringToInteger(split[1]);

        return LocalTime.of(hour, minute);
    }
}
