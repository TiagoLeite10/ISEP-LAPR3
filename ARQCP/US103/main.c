/* Ficheiro principal para a resolução da US 103 */
#include <stdio.h>
#include <stdlib.h>
#include "fill_array_With_random_values.h"
#include "array_arithmetics.h"
#include "generate_sensor_values.h"
#include "handle_sensors_daily_matrix.h"

#define NUM_TYPE_SENSORS 6
#define NUM_FIELDS_PER_TYPE_SENSOR 3

#define SENS_TEMP_INDEX 0
#define SENS_VELC_VENTO_INDEX 1
#define SENS_DIR_VENTO_INDEX 2
#define SENS_HUMD_ATM_INDEX 3
#define SENS_HUMD_SOLO_INDEX 4
#define SENS_PLUVIO_INDEX 5

#define MAX_COLUMN_INDEX 0
#define MIN_COLUMN_INDEX 1
#define AVERAGE_COLUMN_INDEX 2

#define SENS_TEMP_READING_FREQUENCY 2 // Minutos
#define SENS_VELC_VENTO_READING_FREQUENCY 5
#define SENS_DIR_VENTO_READING_FREQUENCY 5
#define SENS_HUMD_ATM_READING_FREQUENCY 10
#define SENS_HUMD_SOLO_READING_FREQUENCY 10
#define SENS_PLUVIO_READING_FREQUENCY 5

#define TOTAL_MINUTES_IN_ONE_DAY 1440

int main() {

	short daily_matrix[NUM_TYPE_SENSORS][NUM_FIELDS_PER_TYPE_SENSOR] = {0};
	short *ptr_daily_matrix;
	ptr_daily_matrix = &daily_matrix[0][0];

	int *ptr_random_values;

	// Sensor temperatura
    int sens_temp_reads = TOTAL_MINUTES_IN_ONE_DAY / SENS_TEMP_READING_FREQUENCY;
	ptr_random_values = fill_array_with_random_values(sens_temp_reads);

	if (ptr_random_values == NULL) {
		printf("Algo correu mal! Tente mais tarde.");
		return 1;
	}

	short *ptr_sens_temp_reads;
	ptr_sens_temp_reads = generate_sens_temp_values(ptr_random_values, sens_temp_reads);
	free(ptr_random_values);
	if (ptr_sens_temp_reads == NULL) {
		printf("Algo correu mal! Tente mais tarde.");
		return 1;
	}
	
	fill_daily_matrix_field(ptr_daily_matrix, NUM_FIELDS_PER_TYPE_SENSOR, max_value_in_array(ptr_sens_temp_reads, sens_temp_reads), SENS_TEMP_INDEX, MAX_COLUMN_INDEX);
	fill_daily_matrix_field(ptr_daily_matrix, NUM_FIELDS_PER_TYPE_SENSOR, min_value_in_array(ptr_sens_temp_reads, sens_temp_reads), SENS_TEMP_INDEX, MIN_COLUMN_INDEX);
	fill_daily_matrix_field(ptr_daily_matrix, NUM_FIELDS_PER_TYPE_SENSOR, average_of_values_in_array(ptr_sens_temp_reads, sens_temp_reads), SENS_TEMP_INDEX, AVERAGE_COLUMN_INDEX);

	// Sensor velocidade do vento
	int sens_velc_vento_reads = TOTAL_MINUTES_IN_ONE_DAY / SENS_VELC_VENTO_READING_FREQUENCY;
	ptr_random_values = fill_array_with_random_values(sens_velc_vento_reads);

	if (ptr_random_values == NULL) {
		printf("Algo correu mal! Tente mais tarde.");
		return 1;
	}

	short *ptr_sens_velc_vento_reads;
	ptr_sens_velc_vento_reads = generate_sens_velc_vento_values(ptr_random_values, sens_velc_vento_reads);
	free(ptr_random_values);
	if (ptr_sens_velc_vento_reads == NULL) {
		printf("Algo correu mal! Tente mais tarde.");
		return 1;
	}
	
	fill_daily_matrix_field(ptr_daily_matrix, NUM_FIELDS_PER_TYPE_SENSOR, max_value_in_array(ptr_sens_velc_vento_reads, sens_velc_vento_reads), SENS_VELC_VENTO_INDEX, MAX_COLUMN_INDEX);
	fill_daily_matrix_field(ptr_daily_matrix, NUM_FIELDS_PER_TYPE_SENSOR, min_value_in_array(ptr_sens_velc_vento_reads, sens_velc_vento_reads), SENS_VELC_VENTO_INDEX, MIN_COLUMN_INDEX);
	fill_daily_matrix_field(ptr_daily_matrix, NUM_FIELDS_PER_TYPE_SENSOR, average_of_values_in_array(ptr_sens_velc_vento_reads, sens_velc_vento_reads), SENS_VELC_VENTO_INDEX, AVERAGE_COLUMN_INDEX);
	free(ptr_sens_velc_vento_reads);

	// Sensor direção do vento
	int sens_dir_vento_reads = TOTAL_MINUTES_IN_ONE_DAY / SENS_DIR_VENTO_READING_FREQUENCY;
	ptr_random_values = fill_array_with_random_values(sens_dir_vento_reads);

	if (ptr_random_values == NULL) {
		printf("Algo correu mal! Tente mais tarde.");
		return 1;
	}

	short *ptr_sens_dir_vento_reads;
	ptr_sens_dir_vento_reads = generate_sens_dir_vento_values(ptr_random_values, sens_dir_vento_reads);
	free(ptr_random_values);
	if (ptr_sens_dir_vento_reads == NULL) {
		printf("Algo correu mal! Tente mais tarde.");
		return 1;
	}
	
	fill_daily_matrix_field(ptr_daily_matrix, NUM_FIELDS_PER_TYPE_SENSOR, max_value_in_array(ptr_sens_dir_vento_reads, sens_dir_vento_reads), SENS_DIR_VENTO_INDEX, MAX_COLUMN_INDEX);
	fill_daily_matrix_field(ptr_daily_matrix, NUM_FIELDS_PER_TYPE_SENSOR, min_value_in_array(ptr_sens_dir_vento_reads, sens_dir_vento_reads), SENS_DIR_VENTO_INDEX, MIN_COLUMN_INDEX);
	fill_daily_matrix_field(ptr_daily_matrix, NUM_FIELDS_PER_TYPE_SENSOR, average_of_values_in_array(ptr_sens_dir_vento_reads, sens_dir_vento_reads), SENS_DIR_VENTO_INDEX, AVERAGE_COLUMN_INDEX);
	free(ptr_sens_dir_vento_reads);

	// Sensor pluviosidade
	int sens_pluvio_reads = TOTAL_MINUTES_IN_ONE_DAY / SENS_PLUVIO_READING_FREQUENCY;
	ptr_random_values = fill_array_with_random_values(sens_pluvio_reads);

	if (ptr_random_values == NULL) {
		printf("Algo correu mal! Tente mais tarde.");
		return 1;
	}

	short *ptr_sens_pluvio_reads;
	ptr_sens_pluvio_reads = generate_sens_pluvio_values(ptr_random_values, sens_pluvio_reads, SENS_PLUVIO_READING_FREQUENCY, ptr_sens_temp_reads, SENS_TEMP_READING_FREQUENCY);
	free(ptr_random_values);
	if (ptr_sens_pluvio_reads == NULL) {
		printf("Algo correu mal! Tente mais tarde.");
		return 1;
	}
	
	fill_daily_matrix_field(ptr_daily_matrix, NUM_FIELDS_PER_TYPE_SENSOR, max_value_in_array(ptr_sens_pluvio_reads, sens_pluvio_reads), SENS_PLUVIO_INDEX, MAX_COLUMN_INDEX);
	fill_daily_matrix_field(ptr_daily_matrix, NUM_FIELDS_PER_TYPE_SENSOR, min_value_in_array(ptr_sens_pluvio_reads, sens_pluvio_reads), SENS_PLUVIO_INDEX, MIN_COLUMN_INDEX);
	fill_daily_matrix_field(ptr_daily_matrix, NUM_FIELDS_PER_TYPE_SENSOR, average_of_values_in_array(ptr_sens_pluvio_reads, sens_pluvio_reads), SENS_PLUVIO_INDEX, AVERAGE_COLUMN_INDEX);
	
	free(ptr_sens_temp_reads);

	// Sensor humidade atmosférica
	int sens_humd_atm_reads = TOTAL_MINUTES_IN_ONE_DAY / SENS_HUMD_ATM_READING_FREQUENCY;
	ptr_random_values = fill_array_with_random_values(sens_humd_atm_reads);

	if (ptr_random_values == NULL) {
		printf("Algo correu mal! Tente mais tarde.");
		return 1;
	}

	short *ptr_sens_humd_atm_reads;
	ptr_sens_humd_atm_reads = generate_sens_humd_atm_values(ptr_random_values, sens_humd_atm_reads, SENS_HUMD_ATM_READING_FREQUENCY, ptr_sens_pluvio_reads, SENS_PLUVIO_READING_FREQUENCY);
	free(ptr_random_values);
	if (ptr_sens_humd_atm_reads == NULL) {
		printf("Algo correu mal! Tente mais tarde.");
		return 1;
	}
	
	fill_daily_matrix_field(ptr_daily_matrix, NUM_FIELDS_PER_TYPE_SENSOR, max_value_in_array(ptr_sens_humd_atm_reads, sens_humd_atm_reads), SENS_HUMD_ATM_INDEX, MAX_COLUMN_INDEX);
	fill_daily_matrix_field(ptr_daily_matrix, NUM_FIELDS_PER_TYPE_SENSOR, min_value_in_array(ptr_sens_humd_atm_reads, sens_humd_atm_reads), SENS_HUMD_ATM_INDEX, MIN_COLUMN_INDEX);
	fill_daily_matrix_field(ptr_daily_matrix, NUM_FIELDS_PER_TYPE_SENSOR, average_of_values_in_array(ptr_sens_humd_atm_reads, sens_humd_atm_reads), SENS_HUMD_ATM_INDEX, AVERAGE_COLUMN_INDEX);

	free(ptr_sens_humd_atm_reads);

	// Sensor humidade do solo	
	int sens_humd_solo_reads = TOTAL_MINUTES_IN_ONE_DAY / SENS_HUMD_SOLO_READING_FREQUENCY;
	ptr_random_values = fill_array_with_random_values(sens_humd_solo_reads);

	if (ptr_random_values == NULL) {
		printf("Algo correu mal! Tente mais tarde.");
		return 1;
	}

	short *ptr_sens_humd_solo_reads;
	ptr_sens_humd_solo_reads = generate_sens_humd_solo_values(ptr_random_values, sens_humd_solo_reads, SENS_HUMD_SOLO_READING_FREQUENCY, ptr_sens_pluvio_reads, SENS_PLUVIO_READING_FREQUENCY);
	free(ptr_random_values);
	if (ptr_sens_humd_solo_reads == NULL) {
		printf("Algo correu mal! Tente mais tarde.");
		return 1;
	}
	
	fill_daily_matrix_field(ptr_daily_matrix, NUM_FIELDS_PER_TYPE_SENSOR, max_value_in_array(ptr_sens_humd_solo_reads, sens_humd_solo_reads), SENS_HUMD_SOLO_INDEX, MAX_COLUMN_INDEX);
	fill_daily_matrix_field(ptr_daily_matrix, NUM_FIELDS_PER_TYPE_SENSOR, min_value_in_array(ptr_sens_humd_solo_reads, sens_humd_solo_reads), SENS_HUMD_SOLO_INDEX, MIN_COLUMN_INDEX);
	fill_daily_matrix_field(ptr_daily_matrix, NUM_FIELDS_PER_TYPE_SENSOR, average_of_values_in_array(ptr_sens_humd_solo_reads, sens_humd_solo_reads), SENS_HUMD_SOLO_INDEX, AVERAGE_COLUMN_INDEX);
	
	free(ptr_sens_humd_solo_reads);
	free(ptr_sens_pluvio_reads);

	print_daily_matrix(ptr_daily_matrix, NUM_TYPE_SENSORS, NUM_FIELDS_PER_TYPE_SENSOR);

	return 0;
} 
