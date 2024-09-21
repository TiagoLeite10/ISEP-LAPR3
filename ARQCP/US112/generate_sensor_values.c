#include <stdlib.h>
#include "sensores.h"
#include "sensor.h"

#define TOTAL_SECONDS_IN_ONE_DAY 86400

unsigned short get_independent_sens_read(Sensor *sens, unsigned short ult_sens_read, int comp_rand) {
    switch (sens->sensor_type) {
        case SENS_TEMP_TYPE:
            ult_sens_read = (unsigned short)sens_temp((char)ult_sens_read, (char)comp_rand);
            break;
        case SENS_VELC_VENTO_TYPE:
            ult_sens_read = (unsigned short)sens_velc_vento((unsigned char)ult_sens_read, (char)comp_rand);
            break;
        case SENS_DIR_VENTO_TYPE:
            ult_sens_read = sens_dir_vento(ult_sens_read, (short)comp_rand);
            break;
    }

    return ult_sens_read;
}

void generate_values_for_sensor(int *ptr_random_values, Sensor *sens) {

    unsigned short *temp_sens_ptr;
    temp_sens_ptr = sens->readings;

    unsigned short ult_sens_read = sens->first_read;
    for (int pos = 0; pos < sens->readings_size; pos++) {
        ult_sens_read = get_independent_sens_read(sens, ult_sens_read, *ptr_random_values);
        *temp_sens_ptr = ult_sens_read;
        temp_sens_ptr++;
        ptr_random_values++;
    }
}

unsigned short get_offset_next_last_read_independent_sensor(Sensor *independent_sens, int actual_dependent_sens_reads) {
    unsigned short offset_next_last_read = 0;
    char last_independent_sens_read_found = 0;
    long last_independent_sens_read_seconds = 0;

    long independent_sens_frequency = (long)independent_sens->frequency;
    long new_independent_sens_frequency = last_independent_sens_read_seconds;

    while (last_independent_sens_read_found == 0 && last_independent_sens_read_seconds < TOTAL_SECONDS_IN_ONE_DAY) {
        new_independent_sens_frequency += independent_sens_frequency;
        if (new_independent_sens_frequency <= actual_dependent_sens_reads && offset_next_last_read + 1 < independent_sens->readings_size) {
            last_independent_sens_read_seconds = new_independent_sens_frequency;
            offset_next_last_read++;
        } else {
            last_independent_sens_read_found = 1;
        }
    }

    return offset_next_last_read;
}

unsigned short get_dependent_sens_read(Sensor *dependent_sens, unsigned short ult_dependent_sens_read, unsigned short ult_independent_sens_read, int comp_rand) {
        switch (dependent_sens->sensor_type) {
        case SENS_HUMD_ATM_TYPE:
            ult_dependent_sens_read = (unsigned short)sens_humd_atm((unsigned char)ult_dependent_sens_read, (unsigned char)ult_independent_sens_read, (char)comp_rand);
            break;
        case SENS_HUMD_SOLO_TYPE:
            ult_dependent_sens_read = (unsigned short)sens_humd_solo((unsigned char)ult_dependent_sens_read, (unsigned char)ult_independent_sens_read, (char)comp_rand);
            break;
        case SENS_PLUVIO_TYPE:
            ult_dependent_sens_read = (unsigned short)sens_pluvio((unsigned char)ult_dependent_sens_read, (char)ult_independent_sens_read, (char)comp_rand);
            break;
        }

    return ult_dependent_sens_read;

}

void generate_values_for_sensor_that_depends_on_another(int *ptr_random_values, Sensor *dependent_sens, Sensor *independent_sens) {
    
    unsigned short *temp_dependent_sens_readings;
    temp_dependent_sens_readings = dependent_sens->readings;

    int actual_dependent_sens_reads = dependent_sens->frequency;
    unsigned char ult_dependent_sens_read = (unsigned char)dependent_sens->first_read;

    for (int pos = 0; pos < dependent_sens->readings_size; pos++) {

        unsigned short offset_next_last_read = get_offset_next_last_read_independent_sensor(independent_sens, actual_dependent_sens_reads);
        ult_dependent_sens_read = get_dependent_sens_read(dependent_sens, ult_dependent_sens_read, *(independent_sens->readings + offset_next_last_read), *ptr_random_values);

        *temp_dependent_sens_readings = (unsigned short)ult_dependent_sens_read;
        temp_dependent_sens_readings++;
        ptr_random_values++;
        actual_dependent_sens_reads += dependent_sens->frequency;
    }
}
