#ifndef EXPORT_DATA_H
#define EXPORT_DATA_H
    unsigned char export_daily_matrix (char *file_path, short **daily_matrix, int num_total_lines);
    char * sensor_type_name(unsigned char sensor_type);
    void header_csv(char *file_path);
    unsigned char export_sensor_data (char *file_path, Sensor *sensores, int size);
#endif