# Makefile

# Variáveis
INCLUDES = pcg32_random_r.h read_rnd.h sensores.h fill_array_with_random_values.h generate_sensor_values.h limit_sensor.h limit_functions.h
SOURCES = pcg32_random_r.s read_rnd.c sensores.s fill_array_with_random_values.c generate_sensor_values.c limit_functions.c main.c 
OBJFILES = pcg32_random_r.o read_rnd.o sensores.o fill_array_with_random_values.o generate_sensor_values.o limit_functions.o main.o
EXEC = US104

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
