#include <stdlib.h>
#include <stdio.h>

#include "sensor.h"
#include "fill_array_with_random_values.h"
#include "generate_sensor_values.h"

void fill_sensor_data(Sensor *sens, unsigned short *sensor_id, unsigned char sensor_type, unsigned short max_limit, unsigned short min_limit, unsigned long frequency, unsigned long readings_size, unsigned short first_read) {
	sens->id = *sensor_id;
	sens->sensor_type = sensor_type;
	sens->max_limit = max_limit;
	sens->min_limit = min_limit;
	sens->frequency = frequency;
	sens->readings_size = readings_size;
	sens->first_read = first_read;

	sens->readings = (unsigned short *)malloc(sens->readings_size << 1);
	if (sens->readings == NULL) {
		printf("Algo correu mal ao tentar alocar memória! Por favor, tente mais tarde.\n");
		exit(1);
	}

	// Incrementa 1 ao id para que o próximo sensor tenha um id diferente
	*sensor_id += 1;
}

void fill_array_of_sensores_data(Sensor *ptr_sens, int num_sens, unsigned short *sensor_id, unsigned char sensor_type, unsigned short max_limit, unsigned short min_limit, unsigned long frequency, unsigned long readings_size, unsigned short first_read) {
	int *ptr_random_values;

	for(int num = 0; num < num_sens; num++) {

		fill_sensor_data(ptr_sens, sensor_id, sensor_type, max_limit, min_limit, frequency, readings_size, first_read);		

		ptr_random_values = fill_array_with_random_values(ptr_sens->readings_size);

		generate_values_for_sensor(ptr_random_values, ptr_sens);

		free(ptr_random_values);

		ptr_sens++;
	}
}

void fill_array_of_sensores_data_with_dependent_sensores(Sensor *ptr_dependent_sens, int num_dependent_sensores, unsigned short *sensor_id, unsigned char sensor_type, unsigned short max_limit, unsigned short min_limit, unsigned long frequency, unsigned long readings_size, unsigned short first_read, Sensor *ptr_independent_sens, int num_independent_sensores) {
	int *ptr_random_values;

	// Para garantir que não somamos mais vezes o pointer do que o número de sensores que temos (primeiro sensor posição 0, último sensor posição n-1)
	int max_offset_of_independent_sensores = num_independent_sensores - 1;

	for(int num = 0; num < num_dependent_sensores; num++) {	

		fill_sensor_data(ptr_dependent_sens, sensor_id, sensor_type, max_limit, min_limit, frequency, readings_size, first_read);		

		ptr_random_values = fill_array_with_random_values(ptr_dependent_sens->readings_size);

		generate_values_for_sensor_that_depends_on_another(ptr_random_values, ptr_dependent_sens, ptr_independent_sens);

		free(ptr_random_values);

		if (num < max_offset_of_independent_sensores) {
			ptr_independent_sens++;
		}

		ptr_dependent_sens++;
	}
}

void free_sensores_readings(Sensor *sensores, int total_sensores) {
    for(int sens = 0; sens < total_sensores; sens++) {
		free(sensores->readings);
		sensores++;
	}
}