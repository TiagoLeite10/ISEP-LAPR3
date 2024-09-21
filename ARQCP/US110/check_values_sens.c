#include <stdio.h>
#include <stdlib.h>
#include "fill_array_With_random_values.h"
#include "generate_sensor_values.h"
#include "sensor.h"

int check_values_in_sens(Sensor *sens) {

	unsigned short *ptr_sens_readings = sens->readings;
	int count = 0;

	for(int i = 0; i < sens->readings_size; i++) {

		char out_limits = 0;

		switch (sens->sensor_type) {
		case SENS_TEMP_TYPE:
			out_limits = (char)*ptr_sens_readings < (char)sens->min_limit || (char)*ptr_sens_readings > (char)sens->max_limit ? 1 : 0;
			break;
		default:
			out_limits = *ptr_sens_readings < sens->min_limit || *ptr_sens_readings > sens->max_limit ? 1 : 0;
			break;
		}
		
		if (out_limits == 1) {
			if (count > 0) {
				count++;
			} else {
				count = 1;
			}
		} else {			
			count = 0;
		}
		
		ptr_sens_readings++;
	}

	return count;
}

char * get_sensor_type_name(unsigned char sensor_type) {
	switch(sensor_type)  {
		case SENS_TEMP_TYPE:
			return "TEMPERATURA";
		
		case SENS_VELC_VENTO_TYPE:
			return "VELOCIDADE DO VENTO";
		
		case SENS_DIR_VENTO_TYPE:
			return "DIREÇÃO DO VENTO";
		
		case SENS_HUMD_ATM_TYPE:
			return "HUMIDADE ATMOSFÉRICA";
		
		case SENS_HUMD_SOLO_TYPE:
			return "HUMIDADE DO SOLO";
		
		case SENS_PLUVIO_TYPE:
			return "PLUVIOSIDADE";

		default:
			return "INDEFINIDO";
	}
}

void print_header_check_values_in_sens(Sensor *sens) {
	char *sensor_type_name = get_sensor_type_name(sens->sensor_type);
	
	short min_limit = sens->min_limit;
	short max_limit = sens->max_limit;

	if (sens->sensor_type == SENS_TEMP_TYPE) {
		min_limit = (char) sens->min_limit;
		max_limit = (char) sens->max_limit;
	}

	printf("SENSOR DA %s (LIMITE MÍNIMO: %d --- LIMITE MÁXIMO: %d)\n", sensor_type_name, min_limit, max_limit);
}

int print_check_values_in_sens(Sensor *sens, int num_sens, int n) {
	print_header_check_values_in_sens(sens);

	for(int num = 0; num < num_sens; num++) {

		Sensor *ptr_sens = (sens + num);

		int check_sens = check_values_in_sens(ptr_sens);
		
		if (check_sens >= n) {			
			int *ptr_random_values = fill_array_with_random_values(ptr_sens->readings_size);

			generate_values_for_sensor(ptr_random_values, ptr_sens);

			free(ptr_random_values);
			
			printf("Sensor nº %d: Reiniciado!\n", ptr_sens->id);
		} else {
			printf("Sensor nº %d: Não foi reiniciado!\n", ptr_sens->id);
		}
	}

	printf("\n");

	return 0;
}

int print_check_values_in_sens_that_depends_on_another(Sensor *dependent_sens, Sensor *independent_sens, int num_sens, int n) {
	print_header_check_values_in_sens(dependent_sens);

	for(int num = 0; num < num_sens; num++) {

		Sensor *ptr_sens = (dependent_sens + num);

		int check_sens = check_values_in_sens(ptr_sens);
		
		if (check_sens >= n) {	
			int *ptr_random_values = fill_array_with_random_values(ptr_sens->readings_size);

			generate_values_for_sensor_that_depends_on_another(ptr_random_values, ptr_sens, independent_sens);

			free(ptr_random_values);
			
			printf("Sensor nº %d: Reiniciado!\n", ptr_sens->id);
		} else {
			printf("Sensor nº %d: Não foi reiniciado!\n", ptr_sens->id);
		}
	}

	printf("\n");

	return 0;
}