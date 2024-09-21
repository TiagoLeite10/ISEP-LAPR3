#include <stdio.h>
#include <stdint.h>
#include "pcg32_random_r.h"
#include "read_rnd.h"

uint64_t state = 0;
uint64_t inc = 0;

int main() {
	state = read_rnd();
	inc = read_rnd();

	int i;
	for(i = 0; i < 32; i++) {
		printf("%u \n", pcg32_random_r());
	}
	
	return 0;
} 
