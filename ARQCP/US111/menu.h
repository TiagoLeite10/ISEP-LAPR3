#ifndef MENU_H
#define MENU_H
    unsigned char read_option(unsigned int total_options);
    unsigned char main_menu();
    unsigned char secondary_menu();
    unsigned char choose_sensor_type_menu();
    unsigned char choose_sensor(Sensor *sensores, int num_sensores);
#endif