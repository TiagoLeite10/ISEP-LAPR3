#include <stdio.h>
#include <stdlib.h>

/* Função para reservar dinamicamente um bloco de memória para armazenar uma matriz linhas x colunas. A função retorna o endereço da matriz recém-criada. */
short **new_matrix(int lines, int columns) {
	short **ptr_matrix = (short **) malloc(lines * sizeof(short*)); // Reserva o nº de linhas * 4 bytes
	
	for(int i = 0; i < lines; i++) {
		*(ptr_matrix + i) = (short *) calloc(columns, sizeof(short)); // Reserva para cada linha o nº de colunas * 4 bytes e coloca-os a 0. Cada linha contêm um endereço para as suas colunas
	}
	
	return ptr_matrix; // Retorna o apontador da matriz		
}

/*
 * Função para libertar a memória da heap alocada pela matriz
 *
 * matrix -> Apontador para a matriz da qual queremos libertar o espaço da heap
 * num_lines -> Número de linhas que a matriz contém
 */
void free_matrix(short **matrix, int num_lines) {

	for (int line = 0; line < num_lines; line++) {
		free(*(matrix + line));
	}

	free(matrix);
}
