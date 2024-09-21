#ifndef SENSOR_HELPER_H
#define SENSOR_HELPER_H
    #include "sensor.h"
    void fill_array_of_sensores_data(Sensor *ptr_sens, int num_sens, unsigned short *sensor_id, unsigned char sensor_type, unsigned short max_limit, unsigned short min_limit, unsigned long frequency, unsigned long readings_size, unsigned short first_read);
    void fill_array_of_sensores_data_with_dependent_sensores(Sensor *ptr_dependent_sens, int num_dependent_sensores, unsigned short *sensor_id, unsigned char sensor_type, unsigned short max_limit, unsigned short min_limit, unsigned long frequency, unsigned long readings_size, unsigned short first_read, Sensor *ptr_independent_sens, int num_independent_sensores);
    void fill_sensor_data(Sensor *sens, unsigned short *sensor_id, unsigned char sensor_type, unsigned short max_limit, unsigned short min_limit, unsigned long frequency, unsigned long readings_size, unsigned short first_read);
    void free_sensores_readings(Sensor *sensores, int total_sensores);
#endif