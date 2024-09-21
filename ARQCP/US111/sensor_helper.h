#ifndef SENSOR_HELPER_H
#define SENSOR_HELPER_H
    #include "sensor.h"
    void fill_array_of_sensores_data(Sensor *ptr_sens, int num_sens, unsigned short *sensor_id, unsigned char sensor_type, unsigned short max_limit, unsigned short min_limit, unsigned long frequency, unsigned long readings_size, unsigned short first_read);
    void fill_array_of_sensores_data_with_dependent_sensores(Sensor *ptr_dependent_sens, int num_dependent_sensores, unsigned short *sensor_id, unsigned char sensor_type, unsigned short max_limit, unsigned short min_limit, unsigned long frequency, unsigned long readings_size, unsigned short first_read, Sensor *ptr_independent_sens, int num_independent_sensores);
    void fill_sensor_data(Sensor *sens, unsigned short *sensor_id, unsigned char sensor_type, unsigned short max_limit, unsigned short min_limit, unsigned long frequency, unsigned long readings_size, unsigned short first_read);
    Sensor* add_new_sensor(Sensor *ptr_sensores, int *actual_num_of_sens, unsigned short *sensor_id, unsigned char sensor_type, unsigned short max_limit, unsigned short min_limit, unsigned long frequency, unsigned long readings_size, unsigned short first_read);
    Sensor* add_new_dependent_sensor(Sensor *ptr_dependent_sens, int *actual_num_of_dependent_sens, unsigned short *sensor_id, unsigned char sensor_type, unsigned short max_limit, unsigned short min_limit, unsigned long frequency, unsigned long readings_size, unsigned short first_read, Sensor *ptr_independent_sens, int num_independent_sensores);
    void copy_sensores_data(Sensor *sens_with_data_to_copy, Sensor *new_sens_data);
    Sensor* remove_sensor(Sensor *ptr_sensores, int *num_sensores, int offset_sensor);
    void list_all_sensores_from_array(Sensor *sensores, int num_sensores);
    void change_frequency_off_sensor_reading(Sensor *ptr_sensores, int offset_sensor, long new_frequency, Sensor* ptr_independent_sensor);
    void free_sensores_readings(Sensor *sensores, int total_sensores);
#endif