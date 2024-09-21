package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe que representa um controlador de rega
 */
public class WateringController {

    /**
     * Número de dias para o qual este plano está desenhado
     */
    public final static int PLAN_DAYS = 30;

    /**
     * Constante que representa o valor em que a rega acontece todos os dias
     */
    private final char EVERY_DAYS = 't';

    /**
     * Constante que representa o valor em que a rega acontece nos dias ímpares
     */
    private final char ODD_DAYS = 'i';

    /**
     * Constante que representa o valor em que a rega acontece nos dias pares
     */
    private final char EVEN_DAYS = 'p';

    /**
     * Representa a parcela
     */
    private Portion portion;

    /**
     * Representa a data de criação do controlador
     */
    private final LocalDateTime creationDate;

    /**
     * Representa a data em que o controlador deixa de atuar
     */
    private final LocalDateTime endingDate;

    /**
     * Representa os horários do dia a dia em que deve ocorrer a rega
     */
    private final ArrayList<LocalTime> timesToWatering;

    /**
     * Sabe se ocorre neste momento a rega ou não
     */
    private boolean isWatering;

    /**
     * Representa a duração de rega após o início desta mesma
     */
    private final int duration;

    /**
     * Representa a regularidade de rega: 't' - todos os dias, 'p' - dias pares e 'i' - dias ímpares
     */
    private final char regularity;

    /**
     * Representa o tempo em que a rega que ocorre começou (null se não estiver a ocorrer nenhuma rega no momento)
     */
    private LocalTime timeStartedWatering;

    /**
     * Construtor para inicializar uma instância desta classe
     *
     * @param portion         A parcela controlada por este controlador
     * @param creationDate    Data de criação do controlador
     * @param timesToWatering Os horários de rega em que o controlador deve ordenar a rega
     * @param duration        A duração da rega
     * @param regularity      A regularidade de rega (todos os dias, dias pares, dias ímpares)
     */
    public WateringController(Portion portion, LocalDateTime creationDate, ArrayList<LocalTime> timesToWatering,
                              int duration, char regularity) {
        this.portion = portion;
        this.creationDate = creationDate;
        this.timesToWatering = timesToWatering;
        this.isWatering = false;
        this.duration = duration;
        this.regularity = regularity;
        endingDate = creationDate.plusDays(PLAN_DAYS);
        timeStartedWatering = null;
    }

    /**
     * Função para retornar a parcela controlada por este controlador
     *
     * @return A parcela controlada por este controlador
     */
    public Portion getPortion() {
        return portion;
    }

    /**
     * Método para o controlador verificar se deve ou não começar a regar.
     */
    public void startWatering() {
        if (isWateringDay() && !this.isWatering) {
            LocalTime timeNow = LocalTime.now();

            Iterator<LocalTime> validTimes = timesToWatering.iterator();
            while (validTimes.hasNext() && !this.isWatering) {
                LocalTime validTime = validTimes.next();
                if (validTime.compareTo(timeNow) <= 0 && timeNow.compareTo(timeNow.plusMinutes(duration)) < 0) {
                    this.isWatering = true;
                    this.timeStartedWatering = timeNow;
                }
            }

        }
    }

    /**
     * Método para saber se hoje é um dia em que se deve realizar a rega.
     *
     * @return true se for dia de rega, falso caso contrário
     */
    private boolean isWateringDay() {
        LocalDateTime todayDate = LocalDateTime.now();
        return regularity == EVERY_DAYS
                || regularity == ODD_DAYS && todayDate.getDayOfMonth() % 2 != 0
                || regularity == EVEN_DAYS && todayDate.getDayOfMonth() % 2 == 0
                && creationDate.compareTo(endingDate) <= 0;
    }

    /**
     * Método para o controlador verificar se tem ou não de parar de regar
     */
    public void stopWatering() {
        LocalTime timeNow = LocalTime.now();
        if (this.isWatering && timeNow.compareTo(timeStartedWatering.plusMinutes(this.duration)) > 0) {
            this.isWatering = false;
            timeStartedWatering = null;
        }
    }

    /**
     * Método para sabermos se está a regar neste momento
     *
     * @return true se estiver a regar, falso em caso contrário
     */
    public boolean getIsWatering() {
        return this.isWatering;
    }

    /**
     * Método que calcula e devolve os minutos que faltam para parar a rega.
     *
     * @return Os minutos em falta para parar de regar
     */
    public int minutesOfWateringLeft() {

        if (this.isWatering) {
            LocalTime timeNow = LocalTime.now();
            LocalTime timeEnd = timeStartedWatering.plusMinutes(duration);
            ZoneOffset zoneOffSet = ZoneOffset.of("+00:00");
            long timeLeft = timeEnd.toEpochSecond(LocalDate.now(), zoneOffSet) - timeNow.toEpochSecond(LocalDate.now(), zoneOffSet);

            return (int) timeLeft / 60;
        }

        return 0;

    }
}
