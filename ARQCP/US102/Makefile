# Makefile

# Variáveis
INCLUDES = pcg32_random_r.h read_rnd.h sensores.h
SOURCES = pcg32_random_r.s read_rnd.c sensores.s main.c
OBJFILES = pcg32_random_r.o read_rnd.o sensores.o main.o
EXEC = US102

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
