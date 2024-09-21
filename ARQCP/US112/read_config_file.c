#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "convert_char_number.h"

#define NUM_SENS_TEMP "NUM_SENS_TEMP"
#define NUM_SENS_VELC_VENTO "NUM_SENS_VELC_VENTO"
#define NUM_SENS_DIR_VENTO "NUM_SENS_DIR_VENTO"
#define NUM_SENS_HUMD_ATM "NUM_SENS_HUMD_ATM"
#define NUM_SENS_HUMD_SOLO "NUM_SENS_HUMD_SOLO"
#define NUM_SENS_PLUVIO "NUM_SENS_PLUVIO"

#define SENS_TEMP_READING_FREQUENCY "SENS_TEMP_READING_FREQUENCY"
#define SENS_VELC_VENTO_READING_FREQUENCY "SENS_VELC_VENTO_READING_FREQUENCY"
#define SENS_DIR_VENTO_READING_FREQUENCY "SENS_DIR_VENTO_READING_FREQUENCY"
#define SENS_HUMD_ATM_READING_FREQUENCY "SENS_HUMD_ATM_READING_FREQUENCY"
#define SENS_HUMD_SOLO_READING_FREQUENCY "SENS_HUMD_SOLO_READING_FREQUENCY"
#define SENS_PLUVIO_READING_FREQUENCY "SENS_PLUVIO_READING_FREQUENCY"

unsigned char read_config_file(char *config_file_name, int *num_sens_temp, int *num_sens_velc_vento, int *num_sens_dir_vento,
	                                int *num_sens_humd_atm, int *num_sens_humd_solo, int *num_sens_pluvio,
	                                long *sens_temp_reading_frequency, long *sens_velc_vento_reading_frequency, 
                                    long *sens_dir_vento_reading_frequency, long *sens_humd_atm_reading_frequency,
                                    long *sens_humd_solo_reading_frequency, long *sens_pluvio_reading_frequency) {
	
    FILE *file = fopen(config_file_name, "r"); // O r significa que vai abrir o ficheiro em modo de leitura

	if (file == NULL) {
		return 0;
	}

	char line[256];

	while (fgets(line, sizeof(line), file) != NULL) {
		
		if (!(line[0] == '\n' || line[0] == '#')) { 
			char *content = strtok(line, " = ");				// O strtok representa o split
			
			if (strcmp(content, NUM_SENS_TEMP) == 0) {				
				convert_char_to_int(strtok(NULL, " = "), num_sens_temp);
			
			} else if (strcmp(content, NUM_SENS_VELC_VENTO) == 0) {				
				convert_char_to_int(strtok(NULL, " = "), num_sens_velc_vento);
			
			} else if (strcmp(content, NUM_SENS_DIR_VENTO) == 0) {				
				convert_char_to_int(strtok(NULL, " = "), num_sens_dir_vento);
			
			} else if (strcmp(content, NUM_SENS_HUMD_ATM) == 0) {				
				convert_char_to_int(strtok(NULL, " = "), num_sens_humd_atm);
			
			} else if (strcmp(content, NUM_SENS_HUMD_SOLO) == 0) {				
				convert_char_to_int(strtok(NULL, " = "), num_sens_humd_solo);
			
			} else if (strcmp(content, NUM_SENS_PLUVIO) == 0) {				
				convert_char_to_int(strtok(NULL, " = "), num_sens_pluvio);
			
			} else if (strcmp(content, SENS_TEMP_READING_FREQUENCY) == 0) {				
				convert_char_to_long(strtok(NULL, " = "), sens_temp_reading_frequency);
			
			} else if (strcmp(content, SENS_VELC_VENTO_READING_FREQUENCY) == 0) {				
				convert_char_to_long(strtok(NULL, " = "), sens_velc_vento_reading_frequency);
			
			} else if (strcmp(content, SENS_DIR_VENTO_READING_FREQUENCY) == 0) {				
				convert_char_to_long(strtok(NULL, " = "), sens_dir_vento_reading_frequency);
			
			} else if (strcmp(content, SENS_HUMD_ATM_READING_FREQUENCY) == 0) {				
				convert_char_to_long(strtok(NULL, " = "), sens_humd_atm_reading_frequency);
			
			} else if (strcmp(content, SENS_HUMD_SOLO_READING_FREQUENCY) == 0) {				
				convert_char_to_long(strtok(NULL, " = "), sens_humd_solo_reading_frequency);
			
			} else if (strcmp(content, SENS_PLUVIO_READING_FREQUENCY) == 0) {				
				convert_char_to_long(strtok(NULL, " = "), sens_pluvio_reading_frequency);
			}
		}
	}

	fclose(file);

	return *num_sens_temp > 0 && *num_sens_velc_vento > 0 && *num_sens_dir_vento > 0 
		&& *num_sens_humd_atm > 0 && *num_sens_humd_solo > 0 && *num_sens_pluvio > 0
		&& *sens_temp_reading_frequency > 0 && *sens_velc_vento_reading_frequency > 0
		&& *sens_dir_vento_reading_frequency > 0 && *sens_humd_atm_reading_frequency > 0
		&& *sens_humd_solo_reading_frequency > 0 && *sens_pluvio_reading_frequency > 0 ? 1 : 0;
}