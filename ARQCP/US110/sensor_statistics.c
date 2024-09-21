#include "sensor.h"
#include <limits.h>

short max_value_readed_by_sensor(Sensor *sens) {
    unsigned short *ptr_sens_readings = sens->readings;
    short max = SHRT_MIN;
    
    for (int pos = 0; pos < sens->readings_size; pos++) {
        switch (sens->sensor_type) {
        case SENS_TEMP_TYPE:
            if (max < (short)(char)*ptr_sens_readings)
                max = (short)(char)*ptr_sens_readings;
            break;
        case SENS_DIR_VENTO_TYPE:
            if (max < (short)*ptr_sens_readings)
                max = (short)*ptr_sens_readings;
            break;
        default:
            if (max < (short)(unsigned char)*ptr_sens_readings)
                max = (short)(unsigned char)*ptr_sens_readings;
            break;
        }

        ptr_sens_readings++;
    }

    return max;
}

short max_value_readed_in_array_of_sensors(Sensor *sensores, int size) {

    short max = SHRT_MIN;

    for (int pos_sens = 0; pos_sens < size; pos_sens++) {
        short temp_max = max_value_readed_by_sensor(sensores);

        if (temp_max > max)
            max = temp_max;

        sensores++;
    }

    return max;

}

short min_value_readed_by_sensor(Sensor *sens) {
    unsigned short *ptr_sens_readings = sens->readings;
    short min = (short)*ptr_sens_readings;

    for (int pos = 0; pos < sens->readings_size; pos++) {
        switch (sens->sensor_type) {
        case SENS_TEMP_TYPE:
            if (min > (short)(char)*ptr_sens_readings)
                min = (short)(char)*ptr_sens_readings;
            break;
        case SENS_DIR_VENTO_TYPE:
            if (min > (short)*ptr_sens_readings)
                min = (short)*ptr_sens_readings;
            break;
        default:
            if (min > (short)(unsigned char)*ptr_sens_readings)
                min = (short)(unsigned char)*ptr_sens_readings;
            break;
        }

        ptr_sens_readings++;
    }

    return min;
}

short min_value_readed_in_array_of_sensors(Sensor *sensores, int size) {

    short min = SHRT_MAX;

    for (int pos_sens = 0; pos_sens < size; pos_sens++) {
        short temp_min = min_value_readed_by_sensor(sensores);
        
        if (temp_min < min)
            min = temp_min;

        sensores++;
    }

    return min;

}

long sum_of_values_readed_by_sensor(Sensor *sens) {
    unsigned short *ptr_sens_readings = sens->readings;
    long sum_all_values = 0;

    for (int pos = 0; pos < sens->readings_size; pos++) {
        switch (sens->sensor_type) {
        case SENS_TEMP_TYPE:
            sum_all_values += (long)(char)*ptr_sens_readings;
            break;
        case SENS_DIR_VENTO_TYPE:
            sum_all_values += (long)*ptr_sens_readings;
            break;
        default:
            sum_all_values += (long)(unsigned char)*ptr_sens_readings;
            break;
        }
        
        ptr_sens_readings++;
    }

    return sum_all_values;

}

short average_value_readed_in_array_of_sensors(Sensor *sensores, int size) {

    long sum_all_values = 0;
    long total_readed_values = 0;

    for (int pos_sens = 0; pos_sens < size; pos_sens++) {
    
        sum_all_values += sum_of_values_readed_by_sensor(sensores);
        total_readed_values += (long)sensores->readings_size;

        sensores++;
    }

    return (short)(sum_all_values / total_readed_values);

}