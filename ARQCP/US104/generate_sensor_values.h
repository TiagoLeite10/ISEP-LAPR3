#ifndef GENERATE_SENSOR_VALUES_H
#define GENERATE_SENSOR_VALUES_H
	short* generate_sens_temp_values(int *ptr_random_values, int size);
	short* generate_sens_velc_vento_values(int *ptr_random_values, int size);
	short* generate_sens_dir_vento_values(int *ptr_random_values, int size);
	short* generate_sens_humd_atm_values(int *ptr_random_values, int size, int frequency_humd_atm_reads, short *ptr_sens_pluvio_reads, int frequency_pluvio_reads);
	short* generate_sens_humd_solo_values(int *ptr_random_values, int size, int frequency_humd_solo_reads, short *ptr_sens_pluvio_reads, int frequency_pluvio_reads);
	short* generate_sens_pluvio_values(int *ptr_random_values, int size, int frequency_pluvio_reads, short *ptr_sens_temp_reads, int frequency_temp_reads);
#endif
