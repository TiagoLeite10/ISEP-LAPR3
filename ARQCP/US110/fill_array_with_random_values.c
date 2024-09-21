#include <stdint.h>
#include <stdlib.h>
#include <stdio.h>

#include "pcg32_random_r.h"
#include "read_rnd.h"

uint64_t state = 0;
uint64_t inc = 0;

int *fill_array_with_random_values(long amount) {

	state = read_rnd();
	inc = read_rnd();
	
	int *prt_random_values;
	prt_random_values = (int *)malloc(amount * sizeof(int));

	if (prt_random_values == NULL) {
		printf("Algo correu mal ao tentar alocar memória! Por favor, tente mais tarde.\n");
		exit(1);
	}

	int *temp_ptr;
	temp_ptr = prt_random_values;

	for (long read = 0; read < amount; read++) {
		*temp_ptr = pcg32_random_r();
		temp_ptr++;
	}

	return prt_random_values;

}
