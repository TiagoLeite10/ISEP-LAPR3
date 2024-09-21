# Makefile

# Variáveis
INCLUDES = pcg32_random_r.h read_rnd.h matrix_helper.h sensores.h fill_array_with_random_values.h generate_sensor_values.h sensor_statistics.h daily_matrix.h limit_sensor.h check_values_sens.h read_number.h read_config_file.h convert_char_number.h sensor_helper.h menu.h
SOURCES = pcg32_random_r.s read_rnd.c matrix_helper.c sensores.s fill_array_with_random_values.c generate_sensor_values.c sensor_statistics.c daily_matrix.c check_values_sens.c read_number.c read_config_file.c convert_char_number.c menu.c sensor_helper.c main.c 
OBJFILES = pcg32_random_r.o read_rnd.o matrix_helper.o sensores.o fill_array_with_random_values.o generate_sensor_values.o sensor_statistics.o daily_matrix.o check_values_sens.o read_number.o read_config_file.o convert_char_number.o menu.o sensor_helper.o main.o
EXEC = US110

# Regras para o sufixo
.SUFFIXES : .s .c .o

.s.o .c.o:
	gcc -Wall -g -c $<

${EXEC}: ${OBJFILES}
	gcc -Wall -g -o ${EXEC} ${OBJFILES}

${OBJFILES}: ${SOURCES} ${INCLUDES}

clean:
	rm -f ${OBJFILES} ${EXEC}

run: ${EXEC}
	valgrind ./${EXEC}
