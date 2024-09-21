#include <stdio.h>

unsigned char read_option(unsigned int total_options) {
    unsigned char option = -1;
    unsigned char valid_option = 0;

    while (valid_option != 1) {
        scanf("%hhd", &option);

        if (option >= 0 && option <= total_options)
            valid_option = 1;
        else
            printf("Opção inválida! Por favor, insira uma opção válida:\n");

        while (getchar() != '\n') {
			continue;
		}
    }

    printf("\n");
    
    return option;
}

unsigned char main_menu() {
    unsigned int total_options = 2;

    printf("----------------------------------------------------------\n");
    printf("|-------------------- Menu Principal --------------------|\n");
    printf("----------------------------------------------------------\n");
    printf("| 1 - Carregar ficheiro de configuração                  |\n");
    printf("| 2 - Carregar dados manualmente                         |\n");
    printf("| 0 - Sair da aplicação                                  |\n");
    printf("----------------------------------------------------------\n");

    printf("Introduza a opção que pretende: \n");

    return read_option(total_options);
}

unsigned char secondary_menu() {
    unsigned int total_options = 2;

    printf("\n----------------------------------------------------------\n");
    printf("|-------------------- Menu Secundário -------------------|\n");
    printf("----------------------------------------------------------\n");
    printf("| 1 - Consultar matriz diária                            |\n");
    printf("| 2 - Verificar limites dos valores produzidos           |\n");
    printf("| 0 - Sair da aplicação                                  |\n");
    printf("----------------------------------------------------------\n");

    printf("Introduza a opção que pretende: \n");

    return read_option(total_options);
}