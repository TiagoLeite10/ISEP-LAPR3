#ifndef HANDLE_SENSORS_DAILY_MATRIX_H
#define HANDLE_SENSORS_DAILY_MATRIX_H

    #include "sensor.h"

    #define MAX_COLUMN_INDEX 0
    #define MIN_COLUMN_INDEX 1
    #define AVERAGE_COLUMN_INDEX 2

    #define SENS_TEMP_INDEX 0
    #define SENS_VELC_VENTO_INDEX 1
    #define SENS_DIR_VENTO_INDEX 2
    #define SENS_HUMD_ATM_INDEX 3
    #define SENS_HUMD_SOLO_INDEX 4
    #define SENS_PLUVIO_INDEX 5

    void fill_sensor_daily_matrix(short **daily_matrix, Sensor *ptr_sensores, int num_sensores, char sens_index);
    void print_daily_matrix(short **daily_matrix, int num_total_lines, int num_total_columns);
#endif