#include <stdio.h>
#include <string.h>

void convert_char_to_int(char* content, int *number) {
	sscanf(content, "%d", number);
}

void convert_char_to_long(char* content, long *number) {
	sscanf(content, "%ld", number);
}