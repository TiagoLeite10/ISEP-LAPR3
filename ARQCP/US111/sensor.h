#ifndef SENSOR_H
#define SENSOR_H

	#define SENS_TEMP_TYPE 1
	#define SENS_VELC_VENTO_TYPE 2
	#define SENS_DIR_VENTO_TYPE 3
	#define SENS_HUMD_ATM_TYPE 4
	#define SENS_HUMD_SOLO_TYPE 5
	#define SENS_PLUVIO_TYPE 6

	typedef struct {
		unsigned short id;
		unsigned char sensor_type;
		unsigned short max_limit; // limites do sensor
		unsigned short min_limit;
		unsigned long frequency; // frequência de leituras (em segundos)
		unsigned long readings_size; // tamanho do array de leituras
		unsigned short *readings; // array de leituras diárias
		unsigned short first_read; // o valor lido pelo sensor ao ser inicializado
		// adicionar possíveis campos
	} Sensor;
#endif
