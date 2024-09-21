/* Ficheiro principal para a resolução da US 103 */
#include <stdio.h>
#include <stdlib.h>
#include "fill_array_With_random_values.h"
#include "generate_sensor_values.h"
#include "limit_sensor.h"
#include "limit_functions.h"

#define NUM_TYPE_SENSORS 6
#define NUM_FIELDS_PER_TYPE_SENSOR 3

#define SENS_TEMP_READING_FREQUENCY 2 // Minutos
#define SENS_VELC_VENTO_READING_FREQUENCY 5
#define SENS_DIR_VENTO_READING_FREQUENCY 5
#define SENS_HUMD_ATM_READING_FREQUENCY 10
#define SENS_HUMD_SOLO_READING_FREQUENCY 10
#define SENS_PLUVIO_READING_FREQUENCY 5

#define TOTAL_MINUTES_IN_ONE_DAY 1440

#define N 25

int main() {

	int *ptr_random_values;
	
	printf("Sensores reiniciados após %d leituras consecutivas erradas!\n\n", N);
	
	// Sensor temperatura
    int sens_temp_reads = TOTAL_MINUTES_IN_ONE_DAY / SENS_TEMP_READING_FREQUENCY;
	ptr_random_values = fill_array_with_random_values(sens_temp_reads);

	if (ptr_random_values == NULL) {
		printf("Algo correu mal! Tente mais tarde.");
		return 1;
	}

	short *ptr_sens_temp_reads;
	ptr_sens_temp_reads = generate_sens_temp_values(ptr_random_values, sens_temp_reads);
	
	printf("SENSOR DA TEMPERATURA (LIMITE MÍNIMO: %d --- LIMITE MÁXIMO: %d)\n", MIN_SENS_TEMP, MAX_SENS_TEMP);
	char check_sens_temp = checkValuesInSens(ptr_sens_temp_reads, sens_temp_reads, MIN_SENS_TEMP, MAX_SENS_TEMP);
	
	if (check_sens_temp >= N) {
		ptr_sens_temp_reads = generate_sens_temp_values(ptr_random_values, sens_temp_reads);
		
		printf("O sensor da temperatura foi reiniciado!\n\n");
	} else {
		printf("Não foi necessário reiniciar o sensor da temperatura!\n\n");
	}

	free(ptr_random_values);
	if (ptr_sens_temp_reads == NULL) {
		printf("Algo correu mal! Tente mais tarde.");
		return 1;
	}
	
	// Sensor velocidade do vento
	int sens_velc_vento_reads = TOTAL_MINUTES_IN_ONE_DAY / SENS_VELC_VENTO_READING_FREQUENCY;
	ptr_random_values = fill_array_with_random_values(sens_velc_vento_reads);

	if (ptr_random_values == NULL) {
		printf("Algo correu mal! Tente mais tarde.");
		return 1;
	}

	short *ptr_sens_velc_vento_reads;
	ptr_sens_velc_vento_reads = generate_sens_velc_vento_values(ptr_random_values, sens_velc_vento_reads);
	
	printf("SENSOR DA VELOCIDADE DO VENTO (LIMITE MÍNIMO: %d --- LIMITE MÁXIMO: %d)\n", MIN_SENS_VELC_VENTO, MAX_SENS_VELC_VENTO);
	char check_sens_velc_vento = checkValuesInSens(ptr_sens_velc_vento_reads, sens_velc_vento_reads, MIN_SENS_VELC_VENTO, MAX_SENS_VELC_VENTO);

	if (check_sens_velc_vento >= N) {
		ptr_sens_velc_vento_reads = generate_sens_velc_vento_values(ptr_random_values, sens_velc_vento_reads);
		
		printf("O sensor da velocidade do vento foi reiniciado!\n\n");
	} else {
		printf("Não foi necessário reiniciar o sensor da velocidade do vento!\n\n");
	}

	free(ptr_random_values);
	if (ptr_sens_velc_vento_reads == NULL) {
		printf("Algo correu mal! Tente mais tarde.");
		return 1;
	}
	
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
	
	printf("SENSOR DA DIREÇÃO DO VENTO (LIMITE MÍNIMO: %d --- LIMITE MÁXIMO: %d)\n", MIN_SENS_DIR_VENTO, MAX_SENS_DIR_VENTO);
	char check_sens_dir_vento = checkValuesInSens(ptr_sens_dir_vento_reads, sens_dir_vento_reads, MIN_SENS_DIR_VENTO, MAX_SENS_DIR_VENTO);

	if (check_sens_dir_vento >= N) {
		ptr_sens_dir_vento_reads = generate_sens_dir_vento_values(ptr_random_values, sens_dir_vento_reads);
		
		printf("O sensor da direção do vento foi reiniciado!\n\n");
	} else {
		printf("Não foi necessário reiniciar o sensor da direção do vento!\n\n");
	}

	free(ptr_random_values);
	if (ptr_sens_dir_vento_reads == NULL) {
		printf("Algo correu mal! Tente mais tarde.");
		return 1;
	}

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
	
	printf("SENSOR DA PLUVIOSIDADE (LIMITE MÍNIMO: %d --- LIMITE MÁXIMO: %d)\n", MIN_SENS_PLUVIO, MAX_SENS_PLUVIO);
	
	char check_sens_pluvio = checkValuesInSens(ptr_sens_pluvio_reads, sens_pluvio_reads, MIN_SENS_PLUVIO, MAX_SENS_PLUVIO);

	if (check_sens_pluvio >= N) {
		ptr_sens_pluvio_reads = generate_sens_pluvio_values(ptr_random_values, sens_pluvio_reads, SENS_PLUVIO_READING_FREQUENCY, ptr_sens_temp_reads, SENS_TEMP_READING_FREQUENCY);
		
		printf("O sensor da pluviosidade foi reiniciado!\n\n");
	} else {
		printf("Não foi necessário reiniciar o sensor da pluviosidade!\n\n");
	}

	free(ptr_random_values);
	if (ptr_sens_pluvio_reads == NULL) {
		printf("Algo correu mal! Tente mais tarde.");
		return 1;
	}

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
	
	printf("SENSOR DA HUMIDADE ATMOSFÉRICA (LIMITE MÍNIMO: %d --- LIMITE MÁXIMO: %d)\n", MIN_SENS_HUMD_ATM, MAX_SENS_HUMD_ATM);
	
	char check_sens_humd_atm = checkValuesInSens(ptr_sens_humd_atm_reads, sens_humd_atm_reads, MIN_SENS_HUMD_ATM, MAX_SENS_HUMD_ATM);

	if (check_sens_humd_atm >= N) {
		ptr_sens_humd_atm_reads = generate_sens_humd_atm_values(ptr_random_values, sens_humd_atm_reads, SENS_HUMD_ATM_READING_FREQUENCY, ptr_sens_pluvio_reads, SENS_PLUVIO_READING_FREQUENCY);
		
		printf("O sensor da humidade atmósferica foi reiniciado!\n\n");
	} else {
		printf("Não foi necessário reiniciar o sensor da humidade atmósferica!\n\n");
	}
	
	free(ptr_random_values);
	if (ptr_sens_humd_atm_reads == NULL) {
		printf("Algo correu mal! Tente mais tarde.");
		return 1;
	}
	
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
	
	printf("SENSOR DA HUMIDADE DO SOLO (LIMITE MÍNIMO: %d --- LIMITE MÁXIMO: %d)\n", MIN_SENS_HUMD_SOLO, MAX_SENS_HUMD_SOLO);
	
	char check_sens_humd_solo = checkValuesInSens(ptr_sens_humd_solo_reads, sens_humd_solo_reads, MIN_SENS_HUMD_SOLO, MAX_SENS_HUMD_SOLO);

	if (check_sens_humd_solo >= N) {
		ptr_sens_humd_solo_reads = generate_sens_humd_solo_values(ptr_random_values, sens_humd_solo_reads, SENS_HUMD_SOLO_READING_FREQUENCY, ptr_sens_pluvio_reads, SENS_PLUVIO_READING_FREQUENCY);
		
		printf("O sensor da humidade do solo foi reiniciado!\n\n");
	} else {
		printf("Não foi necessário reiniciar o sensor da humidade do solo!\n\n");
	}

	free(ptr_random_values);
	if (ptr_sens_humd_solo_reads == NULL) {
		printf("Algo correu mal! Tente mais tarde.");
		return 1;
	}

	free(ptr_sens_humd_solo_reads);
	free(ptr_sens_pluvio_reads);

	return 0;
} 
