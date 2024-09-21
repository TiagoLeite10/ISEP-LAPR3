#ifndef SENSOR_STATISTICS_H
#define SENSOR_STATISTICS_H
	#include "sensor.h"
	short max_value_readed_by_sensor(Sensor *sens);
	short max_value_readed_in_array_of_sensors(Sensor *sensores, int size);
	short min_value_readed_by_sensor(Sensor *sens);
	short min_value_readed_in_array_of_sensors(Sensor *sensores, int size);
	long sum_of_values_readed_by_sensor(Sensor *sens);
	short average_value_readed_in_array_of_sensors(Sensor *sensores, int size);
#endif
