#ifndef READ_CONFIG_FILE_H
#define READ_CONFIG_FILE_H
    unsigned char read_config_file(char *config_file_name, int *num_sens_temp, int *num_sens_velc_vento, int *num_sens_dir_vento,
	                                int *num_sens_humd_atm, int *num_sens_humd_solo, int *num_sens_pluvio,
	                                long *sens_temp_reading_frequency, long *sens_velc_vento_reading_frequency, 
                                    long *sens_dir_vento_reading_frequency, long *sens_humd_atm_reading_frequency,
                                    long *sens_humd_solo_reading_frequency, long *sens_pluvio_reading_frequency);
#endif