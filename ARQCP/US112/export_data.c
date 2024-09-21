#include <stdio.h>
#include <stddef.h>
#include "sensor.h"

unsigned char export_daily_matrix (char *file_path, short **daily_matrix, int num_total_lines) {
    FILE *file = fopen(file_path, "a");

    if (file == NULL) {
        return 0;
    }

    char sensor_names[6][26] = {"Temperatura", 
                                "Velocidade do vento", 
                                "Direcao do vento", 
                                "Humidade atmosferica", 
                                "Humidade do solo", 
                                "Pluviosidade"};

    fprintf(file, "sensor, maximo, minimo, media\n");

    for (int line = 0; line < num_total_lines; line++) {        
        fprintf(file,"%s, %d, %d, %d\n", sensor_names[line], *(*(daily_matrix + line) + 0), *(*(daily_matrix + line) + 1), *(*(daily_matrix + line) + 2));
    }

    fclose(file);

    return 1;
}

char * sensor_type_name(unsigned char sensor_type) {
	switch(sensor_type)  {
		case SENS_TEMP_TYPE:
			return "Temperatura";
		
		case SENS_VELC_VENTO_TYPE:
			return "Velocidade do vento";
		
		case SENS_DIR_VENTO_TYPE:
			return "Direcao do vento";
		
		case SENS_HUMD_ATM_TYPE:
			return "Humidade atmosferica";
		
		case SENS_HUMD_SOLO_TYPE:
			return "Humidade do solo";
		
		case SENS_PLUVIO_TYPE:
			return "Pluviosidade";

		default:
			return "Indefinido";
	}
}

void header_csv(char *file_path) {
    FILE *file = fopen(file_path, "w");

    if (file == NULL) {
        return;
    }

    fprintf(file, "id, tipo, leituras\n");

    fclose(file);
}

unsigned char export_sensor_data(char *file_path, Sensor *sensores, int size ) {
    FILE *file = fopen(file_path, "a");

    if (file == NULL) {
        return 0;
    }

    for (int pos_sens = 0; pos_sens < size; pos_sens++) {
    
        unsigned short *ptr_sens_readings = sensores->readings;

    	char *type_name = sensor_type_name(sensores->sensor_type);

        fprintf(file, "%d, %s", sensores->id, type_name); 

        for (int pos = 0; pos < sensores->readings_size; pos++) {
            short value = (short)*ptr_sens_readings;

            switch (sensores->sensor_type) {
            case SENS_TEMP_TYPE:
                    value = (short)(char)*ptr_sens_readings;
                break;
            case SENS_DIR_VENTO_TYPE:
                    value = (short)*ptr_sens_readings;
                break;
            default:
                    value = (short)(unsigned char)*ptr_sens_readings;
                break;
            }
            fprintf(file, ", %d", value);

            ptr_sens_readings++;
         }

        fprintf(file, "\n");
        sensores++;
    }

    fclose(file);

    return 1;
}