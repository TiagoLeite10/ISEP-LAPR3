#include <stdio.h>

void fill_daily_matrix_field(short *daily_matrix, int num_total_columns, short value, int pos_needed_sensor, int pos_needed_column) {
    
    daily_matrix += pos_needed_sensor * num_total_columns; // Colocar na linha certa
    daily_matrix += pos_needed_column; // Colocar na coluna certa

    *daily_matrix = value; // Atribuir o valor
}

void print_daily_matrix(short *daily_matrix, int num_total_lines, int num_total_columns) {

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
        printf("|%26s|%*d|%*d|%*d|\n", sensor_names[line], 22, *daily_matrix, 22, *(daily_matrix + 1), 23, *(daily_matrix + 2));
        daily_matrix += num_total_columns;
    }
    
    printf("--------------------------------------------------------------------------------------------------\n");
}