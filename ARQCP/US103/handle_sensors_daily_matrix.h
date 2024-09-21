#ifndef HANDLE_SENSORS_DAILY_MATRIX_H
#define HANDLE_SENSORS_DAILY_MATRIX_H
    void fill_daily_matrix_field(short *daily_matrix, int num_total_columns, short value, int pos_needed_sensor, int pos_needed_column);
    void print_daily_matrix(short *daily_matrix, int num_total_lines, int num_total_columns);
#endif