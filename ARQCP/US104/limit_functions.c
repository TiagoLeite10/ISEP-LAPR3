#include <stdio.h>

	char checkValuesInSens(short *sens, int size, short limitMin, short limitMax) {
		char count = 0;

		for(int i = 0; i < size; i++) {
			
			if (*sens < limitMin || *sens > limitMax) {		
				
				//printf("Encontrado um valor fora do limite do sensor! Valor: %d\n", *sens);

				if (count > 0) {
					count++;
				} else {
					count = 1;
				}
			} else {			
				count = 0;
			}
			
			sens++;
		}

		return count;
	}