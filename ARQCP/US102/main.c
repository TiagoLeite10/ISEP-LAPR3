/* Ficheiro principal para a resolução da US 102 */
#include <stdio.h>
#include <stdint.h>
#include "pcg32_random_r.h"
#include "read_rnd.h"
#include "sensores.h"

uint64_t state = 0;
uint64_t inc = 0;

int main() {
	state = read_rnd();
	inc = read_rnd();
	
	//int i;
	int rand_value = pcg32_random_r();
	/*for(i = 0; i < 32; i++) {
		printf("%u \n", pcg32_random_r());
	}*/
	printf("Valor aleatório: %d\n", rand_value);
	
	char char_random_value = (char)rand_value;
	printf("Valor aleatório (char): %d\n\n", char_random_value);

	// Sensor de Temperatura
	printf("\n----- Sensor de Temperatura -----\n");

	char temperature = sens_temp(20, char_random_value);
	printf("Valor sensor antes %dºC, agora %dºC\n\n", 20, temperature);

	// Teste sensor de temperatura
	// Teste 1, valor expectável: 17
	temperature = sens_temp(20, -97);
	printf("Valor original: 20ºC\n");
	printf("Expectável: 17ºC\n");
	printf("Resultado: %dºC\n\n", temperature);

	// Teste 2, valor expectável: 18
	temperature = sens_temp(20, -96);
	printf("Valor original: 20ºC\n");
	printf("Expectável: 18ºC\n");
	printf("Resultado: %dºC\n\n", temperature);

	// Teste 3, valor expectável: 19
	temperature = sens_temp(20, -64);
	printf("Valor original: 20ºC\n");
	printf("Expectável: 19ºC\n");
	printf("Resultado: %dºC\n\n", temperature);

	// Teste 4, valor expectável: 20
	temperature = sens_temp(20, -32);
	printf("Valor original: 20ºC\n");
	printf("Expectável: 20ºC\n");
	printf("Resultado: %dºC\n\n", temperature);

	// Teste 4, valor expectável: 20
	temperature = sens_temp(20, 32);
	printf("Valor original: 20ºC\n");
	printf("Expectável: 20ºC\n");
	printf("Resultado: %dºC\n\n", temperature);

	// Teste 5, valor expectável: 21
	temperature = sens_temp(20, 64);
	printf("Valor original: 20ºC\n");
	printf("Expectável: 21ºC\n");
	printf("Resultado: %dºC\n\n", temperature);

	// Teste 6, valor expectável: 22
	temperature = sens_temp(20, 96);
	printf("Valor original: 20ºC\n");
	printf("Expectável: 22ºC\n");
	printf("Resultado: %dºC\n\n", temperature);

	// Teste 7, valor expectável: 23
	temperature = sens_temp(20, 97);
	printf("Valor original: 20ºC\n");
	printf("Expectável: 23ºC\n");
	printf("Resultado: %dºC\n\n", temperature);

	// Sensor de velocidade do vento
	printf("\n----- Sensor de velocidade do vento -----\n");

	unsigned char air_speed = sens_velc_vento(20, char_random_value);
	printf("Valor sensor antes %d km/h, agora %d km/h\n\n", 20, air_speed);

	// Teste 1
	air_speed = sens_velc_vento(20, 100);
	printf("Valor original: 20 km/h\n");
	printf("Expectável: 45 km/h\n");
	printf("Resultado: %d km/h\n\n", air_speed);

	// Teste 2
	air_speed = sens_velc_vento(20, -128);
	printf("Valor original: 20 km/h\n");
	printf("Expectável: 0 km/h\n");
	printf("Resultado: %d km/h\n\n", air_speed);

	// Teste 3
	air_speed = sens_velc_vento(20, -20);
	printf("Valor original: 20 km/h\n");
	printf("Expectável: 15 km/h\n");
	printf("Resultado: %d km/h\n\n", air_speed);

	// Teste 4
	air_speed = sens_velc_vento(20, 50);
	printf("Valor original: 20 km/h\n");
	printf("Expectável: 32 km/h\n");
	printf("Resultado: %d km/h\n\n", air_speed);

	// Teste 5
	air_speed = sens_velc_vento(20, 127);
	printf("Valor original: 20 km/h\n");
	printf("Expectável: 51 km/h\n");
	printf("Resultado: %d km/h\n\n", air_speed);

	// Sensor de direção do vento
	printf("\n----- Sensor de direção do vento -----\n");

	short rand_air_direction = (short)rand_value;
	printf("Valor aleatório (short): %d\n", rand_air_direction);
	unsigned short air_direction = sens_dir_vento(20, rand_air_direction);
	printf("Valor sensor antes %d graus, agora %d graus\n\n", 20, air_direction);

	// Teste 1
	air_direction = sens_dir_vento(20, -19);
	printf("Valor original: 20 graus\n");
	printf("Expectável: 11 graus\n");
	printf("Resultado: %d graus\n\n", air_direction);

	// Teste 2
	air_direction = sens_dir_vento(20, 19);
	printf("Valor original: 20 graus\n");
	printf("Expectável: 29 graus\n");
	printf("Resultado: %d graus\n\n", air_direction);

	// Teste 3
	air_direction = sens_dir_vento(359, 12);
	printf("Valor original: 359 graus\n");
	printf("Expectável: 1 graus\n");
	printf("Resultado: %d graus\n\n", air_direction);

	// Teste 4
	air_direction = sens_dir_vento(0, -1);
	printf("Valor original: 0 graus\n");
	printf("Expectável: 359 graus\n");
	printf("Resultado: %d graus\n\n", air_direction);

	// Teste 5
	air_direction = sens_dir_vento(0, -2);
	printf("Valor original: 0 graus\n");
	printf("Expectável: 358 graus\n");
	printf("Resultado: %d graus\n\n", air_direction);
	
	// Teste 6
	air_direction = sens_dir_vento(359, 11);
	printf("Valor original: 359 graus\n");
	printf("Expectável: 0 graus\n");
	printf("Resultado: %d graus\n\n", air_direction);
	
	// Sensor de humidade atmosférica
	printf("\n----- Sensor de humidade atmosférica -----\n");

	unsigned char ult_pluvio = 0;
	unsigned char atmospheric_humidity = sens_humd_atm(20, ult_pluvio, char_random_value);
	printf("Valor sensor antes %d%% sem plusiovidade, agora %d%%\n\n", 20, atmospheric_humidity);

	// Teste 1
	atmospheric_humidity = sens_humd_atm(23, ult_pluvio, 80);
	printf("Valor original: 20%%\n");
	printf("Plusiovidade: %umm\n", ult_pluvio);
	printf("Expectável: 23%% \n");
	printf("Resultado: %u%% \n\n", atmospheric_humidity);
	
	// Teste 2
	atmospheric_humidity = sens_humd_atm(18, ult_pluvio, 55);
	printf("Valor original: 15%%\n");
	printf("Plusiovidade: %umm\n", ult_pluvio);
	printf("Expectável: 18%%\n");
	printf("Resultado: %u%%\n\n", atmospheric_humidity);
	
	// Teste 3
	atmospheric_humidity = sens_humd_atm(0, ult_pluvio, 10);
	printf("Valor original: 0%%\n");
	printf("Plusiovidade: %umm\n", ult_pluvio);
	printf("Expectável: 0%%\n");
	printf("Resultado: %u%%\n\n", atmospheric_humidity);
	
	// Teste 4
	ult_pluvio = 25;
	atmospheric_humidity = sens_humd_atm(45, ult_pluvio, 75);
	printf("Valor original: 45%%\n");
	printf("Plusiovidade: %umm\n", ult_pluvio);
	printf("Expectável: 63%%\n");
	printf("Resultado: %u%%\n\n", atmospheric_humidity);
	
	// Teste 5
	ult_pluvio = 50;
	atmospheric_humidity = sens_humd_atm(50, ult_pluvio, 127);
	printf("Valor original: 50%%\n");
	printf("Plusiovidade: %umm\n", ult_pluvio);
	printf("Expectável: 81%%\n");
	printf("Resultado: %u%%\n\n", atmospheric_humidity);
	
	// Sensor de humidade do solo
	printf("\n----- Sensor de humidade do solo -----\n");

	ult_pluvio = 0;
	unsigned char ground_humidity = sens_humd_atm(20, ult_pluvio, char_random_value);
	printf("Valor sensor antes %d%% sem plusiovidade, agora %d%%\n\n", 20, ground_humidity);

	// Teste 1
	ground_humidity = sens_humd_solo(8, ult_pluvio, -127);
	printf("Valor original: 8%%\n");
	printf("Plusiovidade: %umm\n", ult_pluvio);
	printf("Expectável: 8%% \n");
	printf("Resultado: %u%% \n\n", ground_humidity);
	
	// Teste 2
	ground_humidity = sens_humd_solo(13, ult_pluvio, 96);
	printf("Valor original: 13%%\n");
	printf("Plusiovidade: %umm\n", ult_pluvio);
	printf("Expectável: 13%%\n");
	printf("Resultado: %u%%\n\n", ground_humidity);
	
	// Teste 3
	ground_humidity = sens_humd_solo(0, ult_pluvio, 10);
	printf("Valor original: 0%%\n");
	printf("Plusiovidade: %umm\n", ult_pluvio);
	printf("Expectável: 0%%\n");
	printf("Resultado: %u%%\n\n", ground_humidity);
	
	// Teste 4
	ult_pluvio = 25;
	ground_humidity = sens_humd_solo(30, ult_pluvio, 75);
	printf("Valor original: 30%%\n");
	printf("Plusiovidade: %umm\n", ult_pluvio);
	printf("Expectável: 48%%\n");
	printf("Resultado: %u%%\n\n", ground_humidity);
	
	// Teste 5
	ult_pluvio = 50;
	ground_humidity = sens_humd_solo(40, ult_pluvio, 127);
	printf("Valor original: 40%%\n");
	printf("Plusiovidade: %umm\n", ult_pluvio);
	printf("Expectável: 71%%\n");
	printf("Resultado: %u%%\n\n", ground_humidity);

	// Sensor de pluviosidade
	printf("\n----- Sensor de pluviosidade -----\n");

	char ult_temperature = 19;
	unsigned char precipitation = sens_pluvio(20, ult_temperature, char_random_value);
	printf("Valor sensor antes %d%% , agora %d%%\n\n", 20, precipitation);

	// Teste 1
	ult_temperature = 1;
	precipitation = sens_pluvio(8, ult_temperature, 5);
	printf("Valor original: 8mm\n");
	printf("Temperatura: %dºC\n", ult_temperature);
	printf("Expectável: 10mm \n");
	printf("Resultado: %umm \n\n", precipitation);
	
	// Teste 2
	ult_temperature = 5;
	precipitation = sens_pluvio(13, ult_temperature, 96);
	printf("Valor original: 13mm\n");
	printf("Temperatura: %dºC\n", ult_temperature);
	printf("Expectável: 61mm\n");
	printf("Resultado: %umm\n\n", precipitation);
	
	// Teste 3
	ult_temperature = -4;
	precipitation = sens_pluvio(0, ult_temperature, 10);
	printf("Valor original: 0mm\n");
	printf("Temperatura: %dºC\n", ult_temperature);
	printf("Expectável: 5mm\n");
	printf("Resultado: %umm\n\n", precipitation);
	
	// Teste 4
	ult_temperature = 25;
	precipitation = sens_pluvio(3, ult_temperature, 75);
	printf("Valor original: 3mm\n");
	printf("Temperatura: %dºC\n", ult_temperature);
	printf("Expectável: 3mm\n");
	printf("Resultado: %umm\n\n", precipitation);
	
	// Teste 5
	ult_temperature = 50;
	precipitation = sens_pluvio(0, ult_temperature, 25);
	printf("Valor original: 0mm\n");
	printf("Temperatura: %dºC\n", ult_temperature);
	printf("Expectável: 0mm\n");
	printf("Resultado: %umm\n\n", precipitation);

	return 0;
} 
