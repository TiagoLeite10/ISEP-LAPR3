/* Ficheiro principal para a resolução da US 110 */
#include <stdio.h>
#include <stdlib.h>

#include "matrix_helper.h"
#include "fill_array_With_random_values.h"
#include "generate_sensor_values.h"
#include "sensor_statistics.h"
#include "daily_matrix.h"
#include "limit_sensor.h"
#include "sensor.h"
#include "check_values_sens.h"
#include "menu.h"
#include "read_number.h"
#include "read_config_file.h"
#include "sensor_helper.h"

#define CONFIG_FILE "config.cfg"

#define NUM_TYPE_SENSORS 6
#define NUM_FIELDS_PER_TYPE_SENSOR 3

#define TOTAL_SECONDS_IN_ONE_DAY 86400

#define START_VALUE_SENS_TEMP 15
#define START_VALUE_SENS_VELC_VENTO 5 
#define START_VALUE_SENS_DIR_VENTO 90
#define START_VALUE_SENS_HUMD_ATM 5
#define START_VALUE_SENS_HUMD_SOLO 10
#define START_VALUE_SENS_PLUVIO 1

#define N 5

int main() {
	int num_sens_temp = 0;
	int num_sens_velc_vento = 0;
	int num_sens_dir_vento = 0;
	int num_sens_humd_atm = 0;
	int num_sens_humd_solo = 0;
	int num_sens_pluvio = 0;

	long sens_temp_reading_frequency = 0;
	long sens_velc_vento_reading_frequency = 0;
	long sens_dir_vento_reading_frequency = 0;
	long sens_humd_atm_reading_frequency = 0;
	long sens_humd_solo_reading_frequency = 0;
	long sens_pluvio_reading_frequency = 0;

	unsigned char create_sensors = 0;

	unsigned char option_main_menu = main_menu();

	switch (option_main_menu) {
		case 1:
			create_sensors = read_config_file(CONFIG_FILE, &num_sens_temp, &num_sens_velc_vento, &num_sens_dir_vento,
											&num_sens_humd_atm, &num_sens_humd_solo, &num_sens_pluvio,
											&sens_temp_reading_frequency, &sens_velc_vento_reading_frequency,
											&sens_dir_vento_reading_frequency, &sens_humd_atm_reading_frequency,
											&sens_humd_solo_reading_frequency, &sens_pluvio_reading_frequency);
			
			if (create_sensors == 0) {
				printf("Não foi possível carregar o ficheiro de configuração!\n");
			} else {
				printf("Ficheiro de configuração carregado com sucesso!\n");
			}

			break;
		case 2:
			num_sens_temp = read_num_sens("\nIntroduza o número de sensores da temperatura pretendidos: \n");
			num_sens_velc_vento = read_num_sens("\nIntroduza o número de sensores da velocidade do vento pretendidos: \n");
			num_sens_dir_vento = read_num_sens("\nIntroduza o número de sensores da direção do vento pretendidos: \n");
			num_sens_humd_atm = read_num_sens("\nIntroduza o número de sensores da humidade atmosférica pretendidos: \n");
			num_sens_humd_solo = read_num_sens("\nIntroduza o número de sensores da humidade do solo pretendidos: \n");
			num_sens_pluvio = read_num_sens("\nIntroduza o número de sensores da pluviosidade pretendidos: \n");

			sens_temp_reading_frequency = read_frequency_sens("\nIntroduza a frequência do sensor da temperatura: \n");
			sens_velc_vento_reading_frequency = read_frequency_sens("\nIntroduza a frequência do sensor da velocidade do vento: \n");
			sens_dir_vento_reading_frequency = read_frequency_sens("\nIntroduza a frequência do sensor da direção do vento: \n");
			sens_humd_atm_reading_frequency = read_frequency_sens("\nIntroduza a frequência do sensor da humidade atmosférica: \n");
			sens_humd_solo_reading_frequency = read_frequency_sens("\nIntroduza a frequência do sensor da humidade do solo: \n");
			sens_pluvio_reading_frequency = read_frequency_sens("\nIntroduza a frequência do sensor da pluviosidade: \n");
			
			create_sensors = 1;
			break;
	}

	if (create_sensors == 1) {

		Sensor *ptr_sensores_temp = (Sensor *)malloc(num_sens_temp * sizeof(Sensor));
		Sensor *ptr_sensores_velc_vento = (Sensor *)malloc(num_sens_velc_vento * sizeof(Sensor));
		Sensor *ptr_sensores_dir_vento = (Sensor *)malloc(num_sens_dir_vento * sizeof(Sensor));
		Sensor *ptr_sensores_humd_atm = (Sensor *)malloc(num_sens_humd_atm * sizeof(Sensor));
		Sensor *ptr_sensores_humd_solo = (Sensor *)malloc(num_sens_humd_solo * sizeof(Sensor));
		Sensor *ptr_sensores_pluvio = (Sensor *)malloc(num_sens_pluvio * sizeof(Sensor));
		unsigned short sensor_id = 1;

		short **daily_matrix = new_matrix(NUM_TYPE_SENSORS, NUM_FIELDS_PER_TYPE_SENSOR);

		// Sensor temperatura
		long sens_temp_reads_size = TOTAL_SECONDS_IN_ONE_DAY / sens_temp_reading_frequency;

		fill_array_of_sensores_data(ptr_sensores_temp, num_sens_temp, &sensor_id, SENS_TEMP_TYPE, MAX_SENS_TEMP, MIN_SENS_TEMP, sens_temp_reading_frequency, sens_temp_reads_size, START_VALUE_SENS_TEMP);

		// Sensor velocidade do vento
		long sens_velc_vento_reads_size = TOTAL_SECONDS_IN_ONE_DAY / sens_velc_vento_reading_frequency;
		fill_array_of_sensores_data(ptr_sensores_velc_vento, num_sens_velc_vento, &sensor_id, SENS_VELC_VENTO_TYPE, MAX_SENS_VELC_VENTO, MIN_SENS_VELC_VENTO, sens_velc_vento_reading_frequency, sens_velc_vento_reads_size, START_VALUE_SENS_VELC_VENTO);

		// Sensor direção do vento
		long sens_dir_vento_reads_size = TOTAL_SECONDS_IN_ONE_DAY / sens_dir_vento_reading_frequency;
		fill_array_of_sensores_data(ptr_sensores_dir_vento, num_sens_dir_vento, &sensor_id, SENS_DIR_VENTO_TYPE, MAX_SENS_DIR_VENTO, MIN_SENS_DIR_VENTO, sens_dir_vento_reading_frequency, sens_dir_vento_reads_size, START_VALUE_SENS_DIR_VENTO);

		// Sensor pluviosidade
		long sens_pluvio_reads_size = TOTAL_SECONDS_IN_ONE_DAY / sens_pluvio_reading_frequency;
		fill_array_of_sensores_data_with_dependent_sensores(ptr_sensores_pluvio, num_sens_pluvio, &sensor_id, SENS_PLUVIO_TYPE, MAX_SENS_PLUVIO, MIN_SENS_PLUVIO, sens_pluvio_reading_frequency, sens_pluvio_reads_size, START_VALUE_SENS_PLUVIO, ptr_sensores_temp, num_sens_temp);

		// Sensor humidade atmosférica
		long sens_humd_atm_reads_size = TOTAL_SECONDS_IN_ONE_DAY / sens_humd_atm_reading_frequency;
		fill_array_of_sensores_data_with_dependent_sensores(ptr_sensores_humd_atm, num_sens_humd_atm, &sensor_id, SENS_HUMD_ATM_TYPE, MAX_SENS_HUMD_ATM, MIN_SENS_HUMD_ATM, sens_humd_atm_reading_frequency, sens_humd_atm_reads_size, START_VALUE_SENS_HUMD_ATM, ptr_sensores_pluvio, num_sens_pluvio);

		// Sensor humidade do solo	
		long sens_humd_solo_reads_size = TOTAL_SECONDS_IN_ONE_DAY / sens_humd_solo_reading_frequency;
		fill_array_of_sensores_data_with_dependent_sensores(ptr_sensores_humd_solo, num_sens_humd_solo, &sensor_id, SENS_HUMD_SOLO_TYPE, MAX_SENS_HUMD_SOLO, MIN_SENS_HUMD_SOLO, sens_humd_solo_reading_frequency, sens_humd_solo_reads_size, START_VALUE_SENS_HUMD_SOLO, ptr_sensores_pluvio, num_sens_pluvio);

		unsigned char option_secondary_menu = -1;
		
		while (option_secondary_menu != 0) {

			option_secondary_menu = secondary_menu();

			switch (option_secondary_menu) {
				case 1:
					// Opção de consultar matriz diária

					fill_sensor_daily_matrix(daily_matrix, ptr_sensores_temp, num_sens_temp, SENS_TEMP_INDEX);
					fill_sensor_daily_matrix(daily_matrix, ptr_sensores_velc_vento, num_sens_velc_vento, SENS_VELC_VENTO_INDEX);
					fill_sensor_daily_matrix(daily_matrix, ptr_sensores_dir_vento, num_sens_dir_vento, SENS_DIR_VENTO_INDEX);
					fill_sensor_daily_matrix(daily_matrix, ptr_sensores_humd_atm, num_sens_humd_atm, SENS_HUMD_ATM_INDEX);
					fill_sensor_daily_matrix(daily_matrix, ptr_sensores_humd_solo, num_sens_humd_solo, SENS_HUMD_SOLO_INDEX);
					fill_sensor_daily_matrix(daily_matrix, ptr_sensores_pluvio, num_sens_pluvio, SENS_PLUVIO_INDEX);

					print_daily_matrix(daily_matrix, NUM_TYPE_SENSORS, NUM_FIELDS_PER_TYPE_SENSOR);
					break;

				case 2:
					// Opção de verificar limites dos valores produzidos
					printf("Sensores reiniciados após %d leituras consecutivas erradas!\n\n", N);

					if (ptr_sensores_temp != NULL)
						print_check_values_in_sens(ptr_sensores_temp, num_sens_temp, N);
					else
						printf("----- NÃO EXISTEM SENSORES DE TEMPERATURA. -----\n\n");

					if (ptr_sensores_velc_vento != NULL)
						print_check_values_in_sens(ptr_sensores_velc_vento, num_sens_velc_vento, N);
					else
						printf("----- NÃO EXISTEM SENSORES DE VELOCIDADE DO VENTO. -----\n\n");

					if (ptr_sensores_dir_vento != NULL)
						print_check_values_in_sens(ptr_sensores_dir_vento, num_sens_dir_vento, N);
					else
						printf("----- NÃO EXISTEM SENSORES DE DIREÇÃO DO VENTO. -----\n\n");

					if (ptr_sensores_humd_atm != NULL)
						print_check_values_in_sens_that_depends_on_another(ptr_sensores_pluvio, ptr_sensores_temp, num_sens_pluvio, N);
					else
						printf("----- NÃO EXISTEM SENSORES DE HUMIDADE ATMOSFÉRICA. -----\n\n");
					
					if (ptr_sensores_humd_solo != NULL)
						print_check_values_in_sens_that_depends_on_another(ptr_sensores_humd_atm, ptr_sensores_pluvio, num_sens_humd_atm, N);
					else
						printf("----- NÃO EXISTEM SENSORES DE HUMIDADE DO SOLO. -----\n\n");
					
					if (ptr_sensores_pluvio != NULL)
						print_check_values_in_sens_that_depends_on_another(ptr_sensores_humd_solo, ptr_sensores_pluvio, num_sens_humd_solo, N);
					else
						printf("----- NÃO EXISTEM SENSORES DE PLUVIOSIDADE. -----\n\n");
					
					break;

				case 0:
					// Opção de limpar memória e terminar programar
					free_sensores_readings(ptr_sensores_temp, num_sens_temp);
					free_sensores_readings(ptr_sensores_velc_vento, num_sens_velc_vento);
					free_sensores_readings(ptr_sensores_dir_vento, num_sens_dir_vento);
					free_sensores_readings(ptr_sensores_humd_atm, num_sens_humd_atm);
					free_sensores_readings(ptr_sensores_humd_solo, num_sens_humd_solo);
					free_sensores_readings(ptr_sensores_pluvio, num_sens_pluvio);

					free(ptr_sensores_temp);
					free(ptr_sensores_velc_vento);
					free(ptr_sensores_dir_vento);
					free(ptr_sensores_pluvio);
					free(ptr_sensores_humd_atm);
					free(ptr_sensores_humd_solo);
					free_matrix(daily_matrix, NUM_TYPE_SENSORS);
			}
		}
	}

	return 0;
} 
