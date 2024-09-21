#include <stdio.h>

#include "daily_matrix.h"
#include "sensor_statistics.h"

void fill_sensor_daily_matrix(short **daily_matrix, Sensor *ptr_sensores, int num_sensores, char sens_index) {
    if (ptr_sensores == NULL) {
        *(*(daily_matrix + sens_index) + MAX_COLUMN_INDEX) = 0;
        *(*(daily_matrix + sens_index) + MIN_COLUMN_INDEX) = 0;
        *(*(daily_matrix + sens_index) + AVERAGE_COLUMN_INDEX) = 0;
    } else {       
        *(*(daily_matrix + sens_index) + MAX_COLUMN_INDEX) = max_value_readed_in_array_of_sensors(ptr_sensores, num_sensores);
        *(*(daily_matrix + sens_index) + MIN_COLUMN_INDEX) = min_value_readed_in_array_of_sensors(ptr_sensores, num_sensores);
        *(*(daily_matrix + sens_index) + AVERAGE_COLUMN_INDEX) = average_value_readed_in_array_of_sensors(ptr_sensores, num_sensores);
    }
}

void print_daily_matrix(short **daily_matrix, int num_total_lines, int num_total_columns) {

    char sensor_names[6][26] = {"Temperatura", 
                                "Velocidade do Vento", 
                                "Direcao do Vento", 
                                "Humidade atmosferica", 
                                "Humidade do solo", 
                                "Pluviosidade"};

    printf("--------------------------------------------------------------------------------------------------\n");
    printf("|------------------------- Matriz diária dos valores lidos nos sensores -------------------------|\n");
    printf("--------------------------------------------------------------------------------------------------\n");
    printf("|          Sensor          |        MÁXIMO        |        MÍNIMO        |          MÉDIA        |\n");
    printf("--------------------------------------------------------------------------------------------------\n");
    
    for (int line = 0; line < num_total_lines; line++) {
        printf("|%26s|%*d|%*d|%*d|\n", sensor_names[line], 22, *(*(daily_matrix + line) + 0), 22, *(*(daily_matrix + line) + 1), 23, *(*(daily_matrix + line) + 2));
    }
    
    printf("--------------------------------------------------------------------------------------------------\n");
}