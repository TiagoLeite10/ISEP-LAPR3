#include <stdio.h>

#include "sensor.h"
#include "sensor_helper.h"

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
    unsigned int total_options = 5;

    printf("\n----------------------------------------------------------\n");
    printf("|-------------------- Menu Secundário -------------------|\n");
    printf("----------------------------------------------------------\n");
    printf("| 1 - Consultar matriz diária                            |\n");
    printf("| 2 - Verificar limites dos valores produzidos           |\n");
    printf("| 3 - Acrescentar um novo sensor                         |\n");
    printf("| 4 - Remover um sensor                                  |\n");
    printf("| 5 - Alterar a frequência de leituras de um sensor      |\n");
    printf("| 0 - Sair da aplicação                                  |\n");
    printf("----------------------------------------------------------\n");

    printf("Introduza a opção que pretende: \n");

    return read_option(total_options);
}

unsigned char choose_sensor_type_menu() {
    unsigned int total_options = 6;

    printf("\n----------------------------------------------------------\n");
    printf("|---------------- Escolha o tipo de sensor --------------|\n");
    printf("----------------------------------------------------------\n");
    printf("| 1 - Sensor de temperatura                              |\n");
    printf("| 2 - Sensor de velocidade do vento                      |\n");
    printf("| 3 - Sensor de direção do vento                         |\n");
    printf("| 4 - Sensor de humidade atmosférica                     |\n");
    printf("| 5 - Sensor de humidade do solo                         |\n");
    printf("| 6 - Sensor de pluviosidade                             |\n");
    printf("| 0 - Voltar ao menu anterior                            |\n");
    printf("----------------------------------------------------------\n");

    printf("Escolha o tipo de sensor que pretende: \n");

    return read_option(total_options);
}

unsigned char choose_sensor(Sensor *sensores, int num_sensores) {
    printf("\n***** Lista de sensores *****\n\n");
    list_all_sensores_from_array(sensores, num_sensores);
    printf("\nEscolha a opção que contém o sensor (introduza 0 para cancelar a operação):\n");
    return read_option(num_sensores);
}