#include <stdlib.h>
#include <stdio.h>

#include "sensor.h"
#include "fill_array_with_random_values.h"
#include "generate_sensor_values.h"
#include "read_number.h"

#define TOTAL_SECONDS_IN_ONE_DAY 86400

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

Sensor* add_new_sensor(Sensor *ptr_sensores, int *actual_num_of_sens, unsigned short *sensor_id, unsigned char sensor_type, unsigned short max_limit, unsigned short min_limit, unsigned long frequency, unsigned long readings_size, unsigned short first_read) {
	int old_num_of_sens = *actual_num_of_sens;
	*actual_num_of_sens += 1;
	ptr_sensores = (Sensor *) realloc(ptr_sensores, *actual_num_of_sens * sizeof(Sensor));
	if (ptr_sensores == NULL) {
		printf("Ocorreu um erro ao tentar realocar memória.\n");
		exit(1);
	}

	Sensor *ptr_new_sens;
	ptr_new_sens = ptr_sensores + old_num_of_sens;
	fill_array_of_sensores_data(ptr_new_sens, 1, sensor_id, sensor_type, max_limit, min_limit, frequency, readings_size, first_read);

	return ptr_sensores;
}

Sensor* add_new_dependent_sensor(Sensor *ptr_dependent_sens, int *actual_num_of_dependent_sens, unsigned short *sensor_id, unsigned char sensor_type, unsigned short max_limit, unsigned short min_limit, unsigned long frequency, unsigned long readings_size, unsigned short first_read, Sensor *ptr_independent_sens, int num_independent_sensores) {
	int old_num_of_sens = *actual_num_of_dependent_sens;
	*actual_num_of_dependent_sens += 1;
	ptr_dependent_sens = (Sensor *) realloc(ptr_dependent_sens, *actual_num_of_dependent_sens * sizeof(Sensor));
	if (ptr_dependent_sens == NULL) {
		printf("Ocorreu um erro ao tentar realocar memória.\n");
		exit(1);
	}

	Sensor *ptr_new_sens;
	ptr_new_sens = ptr_dependent_sens + old_num_of_sens;

	fill_sensor_data(ptr_new_sens, sensor_id, sensor_type, max_limit, min_limit, frequency, readings_size, first_read);		

	int *ptr_random_values = fill_array_with_random_values(ptr_new_sens->readings_size);

	// O sensor independente que seja mais próximo do atual em termos de número de sensores
	if (old_num_of_sens < num_independent_sensores) {
		ptr_independent_sens += old_num_of_sens;
	}

	generate_values_for_sensor_that_depends_on_another(ptr_random_values, ptr_new_sens, ptr_independent_sens);

	free(ptr_random_values);

	return ptr_dependent_sens;
}

void copy_sensores_data(Sensor *sens_with_data_to_copy, Sensor *new_sens_data) {
	new_sens_data->id = sens_with_data_to_copy->id;
	new_sens_data->sensor_type = sens_with_data_to_copy->sensor_type;
	new_sens_data->max_limit = sens_with_data_to_copy->max_limit;
	new_sens_data->min_limit = sens_with_data_to_copy->min_limit;
	new_sens_data->frequency = sens_with_data_to_copy->frequency;
	new_sens_data->readings_size = sens_with_data_to_copy->readings_size;

	// Removido
	/*new_sens_data->readings = (unsigned short *)malloc(new_sens_data->readings_size << 1);
	for (int i = 0; i < new_sens_data->readings_size; i++) {
		*(new_sens_data->readings + i) = *(sens_with_data_to_copy->readings + i);
	}
	free(sens_with_data_to_copy->readings);*/

	new_sens_data->readings_size = sens_with_data_to_copy->readings_size;


	new_sens_data->first_read = sens_with_data_to_copy->first_read;
}

Sensor* remove_sensor(Sensor *ptr_sensores, int *num_sensores, int offset_sensor) {

	// Se o número de sensores for 1, libertamos o array de leituras para o único sensor que existe
	if (*num_sensores == 1) {
		free((ptr_sensores)->readings);
		free(ptr_sensores);
		*num_sensores -= 1;
		return NULL;
	// Caso contrário, libertamos o penúltimo, pois mais à frente este irá ficar com as leituras do último sensor, que será removido
	// pelo realloc
	} else {
		free((ptr_sensores + *num_sensores - 1)->readings);
	}

	int next_offset_sensor = offset_sensor + 1;

	Sensor *actual_sens = ptr_sensores + offset_sensor;
	Sensor *next_sens = ptr_sensores + next_offset_sensor;

	for (int actual_pos = next_offset_sensor; actual_pos < *num_sensores; actual_pos++) {
		copy_sensores_data(next_sens, actual_sens);
		actual_sens++;
		next_sens++;
	}

	*num_sensores -= 1;

	ptr_sensores = (Sensor *) realloc(ptr_sensores, (*num_sensores) * sizeof(Sensor));
	if (ptr_sensores == NULL) {
		printf("Ocorreu um erro ao tentar realocar memória.\n");
		exit(1);
	}

	return ptr_sensores;

}

void list_all_sensores_from_array(Sensor *sensores, int num_sensores) {

	if (sensores == NULL)
		return;

	Sensor *ptr_sensor;

	for (int pos = 0; pos < num_sensores; pos++) {
		ptr_sensor = (sensores + pos);
		printf("----- Opção (%d) -----\n", pos + 1);
		printf("Id do sensor: %d\n", ptr_sensor->id);
		printf("Tipo do sensor: %d\n", ptr_sensor->sensor_type);
		printf("Limite máximo: %d\n", (ptr_sensor->sensor_type == SENS_TEMP_TYPE) ? (short)ptr_sensor->max_limit : ptr_sensor->max_limit);
		printf("Limite mínimo: %d\n", (ptr_sensor->sensor_type == SENS_TEMP_TYPE) ? (short)ptr_sensor->min_limit : ptr_sensor->min_limit);
		printf("Frequência de leituras: %ld\n", ptr_sensor->frequency);
		printf("Número de leituras: %ld\n", ptr_sensor->readings_size);
		printf("Valor lido pelo sensor ao ser inicializado: %d\n", ptr_sensor->first_read);
		printf("----- -----\n\n");
	}
	
}

void change_frequency_off_sensor_reading(Sensor *ptr_sensores, int offset_sensor, long new_frequency, Sensor* ptr_independent_sensor) {

	Sensor* sensor_in_analises = ptr_sensores + offset_sensor;
	unsigned long old_readings_size = sensor_in_analises->readings_size;

	sensor_in_analises->frequency = (unsigned long)new_frequency;
	sensor_in_analises->readings_size = TOTAL_SECONDS_IN_ONE_DAY / new_frequency;
	sensor_in_analises->readings = (unsigned short *) realloc(sensor_in_analises->readings, sensor_in_analises->readings_size * sizeof(Sensor));
	ptr_sensores->frequency = new_frequency;

	if (old_readings_size < sensor_in_analises->readings_size) {	
		int *ptr_random_values = fill_array_with_random_values(sensor_in_analises->readings_size);
		if (ptr_independent_sensor != NULL) {
			generate_values_for_sensor_that_depends_on_another(ptr_random_values, sensor_in_analises, ptr_independent_sensor);
		} else {
			generate_values_for_sensor(ptr_random_values, sensor_in_analises);
		}
		free(ptr_random_values);
	}

}

void free_sensores_readings(Sensor *sensores, int total_sensores) {
    for(int sens = 0; sens < total_sensores; sens++) {
		free(sensores->readings);
		sensores++;
	}
}