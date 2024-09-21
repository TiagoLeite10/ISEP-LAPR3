#include <stdio.h>

#define TOTAL_SECONDS_IN_ONE_DAY 86400

int read_num_sens(char *text) {
	int num = 0;

	printf(text);
    scanf("%d", &num);

	while (num <= 0) {
		while (getchar() != '\n') {
			continue;
		}

		printf("O número de sensores não pode ser menor ou igual a 0. Introduza novamente: \n");
    	scanf("%d", &num);
	}

	return num;
}

long read_frequency_sens(char *text) {
	long num = 0;

	printf(text);
    scanf("%ld", &num);

	while (num <= 0 || num > TOTAL_SECONDS_IN_ONE_DAY) {
		while (getchar() != '\n') {
			continue;
		}

		printf("A frequência de um sensor não pode ser menor ou igual a 0 nem maior que o nº de segundos de um dia (86400). Introduza novamente: \n");
    	scanf("%ld", &num);
	}

	return num;
}