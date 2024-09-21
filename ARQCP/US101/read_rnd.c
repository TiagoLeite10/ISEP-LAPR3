/*
 * =====================================================================================
 *  Reads random numbers from /dev/random 
 *  Works on Linux/OSX and other "Unixes"  
 *
 *  Taken from: 
 *
 *  David Johnston 
 *  Random Number Generators—Principles and Practices
 *  2018 Walter de Gruyter GmbH
 *  ISBN 978-1-5015-1513-2 
 *
 * =====================================================================================
 */

#include <stdio.h>
#include <stdint.h>

uint64_t read_rnd() {
	uint64_t buffer [1];
	FILE *f;
	int result;
	f = fopen("/dev/urandom", "r");
	if (f == NULL) {
		printf("Error: open() failed to open /dev/random for reading\n");
		return 1;
	}
	result = fread(buffer , sizeof(uint64_t), 1,f);
	if (result < 1) {
		printf("error , failed to read and words\n");
		return 1;
	}

	return buffer[0];
}
