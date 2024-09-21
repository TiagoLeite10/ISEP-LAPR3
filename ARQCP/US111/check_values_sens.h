#ifndef CHECK_VALUES_SENS_H
#define CHECK_VALUES_SENS_H
	#include "sensor.h"
	int check_values_in_sens(Sensor *sens);
	char* get_sensor_type_name(unsigned char sensor_type);
	void print_header_check_values_in_sens(Sensor *sens);
	void print_check_values_in_sens(Sensor *sens, int num_sens, int n);
	void print_check_values_in_sens_that_depends_on_another(Sensor *dependent_sens, Sensor *independent_sens, int num_sens, int n);
#endif