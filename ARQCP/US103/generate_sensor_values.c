#include <stdlib.h>
#include "sensores.h"

#define START_VALUE_SENS_TEMP 15
#define START_VALUE_SENS_VELC_VENTO 5 
#define START_VALUE_SENS_DIR_VENTO 90
#define START_VALUE_SENS_HUMD_ATM 5
#define START_VALUE_SENS_HUMD_SOLO 10
#define START_VALUE_SENS_PLUVIO 1

#define TOTAL_MINUTES_IN_ONE_DAY 1440

short* generate_sens_temp_values(int *ptr_random_values, int size) {
    short *ptr_sens_temp_reads;
    ptr_sens_temp_reads = malloc(size << 1);

    short *temp_ptr;
    temp_ptr = ptr_sens_temp_reads;

    char ult_temp = START_VALUE_SENS_TEMP;
    for (int pos = 0; pos < size; pos++) {
        ult_temp = sens_temp(ult_temp, (char)*ptr_random_values);
        *temp_ptr = (short)ult_temp;
        temp_ptr++;
        ptr_random_values++;
    }

    return ptr_sens_temp_reads;
}

short* generate_sens_velc_vento_values(int *ptr_random_values, int size) {
    short *ptr_sens_velc_vento_reads;
    ptr_sens_velc_vento_reads = malloc(size << 1);

    short *temp_ptr;
    temp_ptr = ptr_sens_velc_vento_reads;

    unsigned char ult_velc_vento = START_VALUE_SENS_VELC_VENTO;
    for (int pos = 0; pos < size; pos++) {
        ult_velc_vento = sens_velc_vento(ult_velc_vento, (char)*ptr_random_values);
        *temp_ptr = (short)ult_velc_vento;
        temp_ptr++;
        ptr_random_values++;
    }

    return ptr_sens_velc_vento_reads;
}

short* generate_sens_dir_vento_values(int *ptr_random_values, int size) {
    short *ptr_sens_dir_vento_reads;
    ptr_sens_dir_vento_reads = malloc(size << 1);

    short *temp_ptr;
    temp_ptr = ptr_sens_dir_vento_reads;

    unsigned short ult_dir_vento = START_VALUE_SENS_DIR_VENTO;
    for (int pos = 0; pos < size; pos++) {
        ult_dir_vento = sens_dir_vento(ult_dir_vento, (short)*ptr_random_values);
        *temp_ptr = (short)ult_dir_vento;
        temp_ptr++;
        ptr_random_values++;
    }

    return ptr_sens_dir_vento_reads;
}

short* generate_sens_humd_atm_values(int *ptr_random_values, int size, int frequency_humd_atm_reads, short *ptr_sens_pluvio_reads, int frequency_pluvio_reads) {

    short *ptr_sens_humd_atm_reads;
    ptr_sens_humd_atm_reads = malloc(size << 1);

    short *temp_ptr;
    temp_ptr = ptr_sens_humd_atm_reads;

    int actual_humd_atm_reads = frequency_humd_atm_reads;
    int last_pluvio_read_minutes = frequency_pluvio_reads;
    unsigned char ult_hmd_atm = START_VALUE_SENS_HUMD_ATM;
    for (int pos = 0; pos < size; pos++) {
        
        char last_pluvio_value_found = 0;
        while (last_pluvio_value_found == 0 && last_pluvio_read_minutes < TOTAL_MINUTES_IN_ONE_DAY) {
            if (last_pluvio_read_minutes + frequency_pluvio_reads <= actual_humd_atm_reads) {
                ptr_sens_pluvio_reads++;
                last_pluvio_read_minutes += frequency_pluvio_reads;
            } else {
                last_pluvio_value_found = 1;
            }
        }

        ult_hmd_atm = sens_humd_atm(ult_hmd_atm, (unsigned char)*ptr_sens_pluvio_reads, (char)*ptr_random_values);
        *temp_ptr = (short)ult_hmd_atm;
        temp_ptr++;
        ptr_random_values++;
        actual_humd_atm_reads += frequency_humd_atm_reads;
    }

    return ptr_sens_humd_atm_reads;

}

short* generate_sens_humd_solo_values(int *ptr_random_values, int size, int frequency_humd_solo_reads, short *ptr_sens_pluvio_reads, int frequency_pluvio_reads) {
    short *ptr_sens_humd_solo_reads;
    ptr_sens_humd_solo_reads = malloc(size << 1);

    short *temp_ptr;
    temp_ptr = ptr_sens_humd_solo_reads;

    int actual_humd_solo_reads = frequency_humd_solo_reads;
    int last_pluvio_read_minutes = frequency_pluvio_reads;
    unsigned char ult_hmd_solo = START_VALUE_SENS_HUMD_SOLO;
    for (int pos = 0; pos < size; pos++) {
        char last_pluvio_value_found = 0;
        while (last_pluvio_value_found == 0 && last_pluvio_read_minutes < TOTAL_MINUTES_IN_ONE_DAY) {
            if (last_pluvio_read_minutes + frequency_pluvio_reads <= actual_humd_solo_reads) {
                ptr_sens_pluvio_reads++;
                last_pluvio_read_minutes += frequency_pluvio_reads;
            } else {
                last_pluvio_value_found = 1;
            }
        }

        ult_hmd_solo = sens_humd_solo(ult_hmd_solo, (unsigned char)*ptr_sens_pluvio_reads, (char)*ptr_random_values);
        *temp_ptr = (short)ult_hmd_solo;
        temp_ptr++;
        ptr_random_values++;
        actual_humd_solo_reads += frequency_humd_solo_reads;
    }

    return ptr_sens_humd_solo_reads;
}

short* generate_sens_pluvio_values(int *ptr_random_values, int size, int frequency_pluvio_reads, short *ptr_sens_temp_reads, int frequency_temp_reads) {
    short *ptr_sens_pluvio_reads;
    ptr_sens_pluvio_reads = malloc(size << 1);

    short *temp_ptr;
    temp_ptr = ptr_sens_pluvio_reads;

    int actual_pluvio_reads = frequency_pluvio_reads;
    int last_temp_read_minutes = frequency_temp_reads;
    unsigned char ult_pluvio = START_VALUE_SENS_DIR_VENTO;
    for (int pos = 0; pos < size; pos++) {
        char last_temp_value_found = 0;
        while (last_temp_value_found == 0 && last_temp_read_minutes < TOTAL_MINUTES_IN_ONE_DAY) {
            if (last_temp_read_minutes + frequency_temp_reads <= actual_pluvio_reads) {
                ptr_sens_temp_reads++;
                last_temp_read_minutes += frequency_temp_reads;
            } else {
                last_temp_value_found = 1;
            }
        }

        ult_pluvio = sens_pluvio(ult_pluvio, (char)*ptr_sens_temp_reads, (char)*ptr_random_values);
        *temp_ptr = (short)ult_pluvio;
        temp_ptr++;
        ptr_random_values++;
        actual_pluvio_reads += frequency_pluvio_reads;
    }

    return ptr_sens_pluvio_reads;
}