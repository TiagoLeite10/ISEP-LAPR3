#ifndef GENERATE_SENSOR_VALUES_H
#define GENERATE_SENSOR_VALUES_H
	#include "sensor.h"
	unsigned short get_independent_sens_read(Sensor *sens, unsigned short ult_sens_read, int comp_rand);
	void generate_values_for_sensor(int *ptr_random_values, Sensor *sens);
	unsigned short get_offset_next_last_read_independent_sensor(Sensor *independent_sens, int actual_dependent_sens_reads);
	unsigned short get_dependent_sens_read(Sensor *dependent_sens, unsigned short ult_dependent_sens_read, unsigned short ult_independent_sens_read, int comp_rand);
	void generate_values_for_sensor_that_depends_on_another(int *ptr_random_values, Sensor *dependent_sens, Sensor *independent_sens);
#endif
