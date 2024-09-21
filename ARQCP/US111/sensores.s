.section .data
	.equ OFFSET_ZERO, 32				# Constante que representa o limite de um intervalo nomeado de ZERO
	.equ OFFSET_ONE, 64				# Constante que representa o limite de um intervalo nomeado de ONE
	.equ OFFSET_TWO, 96				# Constante que representa o limite de um intervalo nomeado de TWO
	.equ ZERO_DEGREES, 0				# Constante que representa o valor de 0 graus
	.equ ONE_DEGREES, 1				# Constante que representa o valor de 1 grau
	.equ TWO_DEGREES, 2				# Constante que representa o valor de 2 graus
	.equ THREE_DEGREES, 3				# Constante que representa o valor de 3 graus
	
	.equ TWO_PERCENTAGE_ATMOSPHERIC, 2				# Constante que representa o valor de 2% de humidade atmosférica
	.equ FOUR_PERCENTAGE_ATMOSPHERIC, 4				# Constante que representa o valor de 4% de humidade atmosférica
	.equ SIX_PERCENTAGE_ATMOSPHERIC, 6				# Constante que representa o valor de 6% de humidade atmosférica
	.equ EIGTH_PERCENTAGE_ATMOSPHERIC, 8			# Constante que representa o valor de 8% de humidade atmosférica
	
	.equ FIVE_PERCENTAGE_GROUND, 5				# Constante que representa o valor de 5% de humidade no solo
	.equ TEN_PERCENTAGE_GROUND, 10				# Constante que representa o valor de 10% de humidade no solo
	.equ FIFTEEN_PERCENTAGE_GROUND, 15				# Constante que representa o valor de 15% de humidade no solo
	.equ TWENTY_PERCENTAGE_GROUND, 20		# Constante que representa o valor de 20% de humidade no solo

	.equ OUT_OF_RANGE_DIRECTION_DEGREES, 360	# Constante que representa o limite aberto do valor permitido de graus (ou seja, 360 é o primeiro valor alto que não é permitido)
	.equ MINIMUM_DIRECTION_DEGREES, 0		# Cosntante que representa o limite mínimo do valor permitido de graus (este é o valor mais baixo permitido)

	.equ ZERO_PRECIPITATION, 0				# Constante que representa o valor de 0 mm
	.equ ONE_PRECIPITATION, 1				# Constante que representa o valor de 1 mm
	.equ TWO_PRECIPITATION, 2				# Constante que representa o valor de 2 mm
	.equ THREE_PRECIPITATION, 3				# Constante que representa o valor de 3 mm
	.equ MINIMUM_DEGREES, 20		# Constante que representa o limite mínimo da temperatura para pouca variavção da pluviosidade


.section .text
	.global sens_temp 				# char sens_temp(char ult_temp, char comp_rand);
	.global sens_velc_vento				# unsigned char sens_velc_vento(unsigned char ult_velc_vento, char comp_rand);
	.global sens_dir_vento				# unsigned short sens_dir_vento(unsigned short ult_dir_vento, short comp_rand);
	.global sens_humd_atm 				# unsigned char sens_humd_atm(unsigned char ult_hmd_atm, unsigned char ult_pluvio, char comp_rand);
	.global sens_humd_solo				# unsigned char sens_humd_solo(unsigned char ult_hmd_solo, unsigned char ult_pluvio, char comp_rand);
	.global sens_pluvio				# unsigned char sens_pluvio(unsigned char ult_pluvio, char ult_temp, char comp_rand);

# ---------- TEMPERATURA ------------------
sens_temp:

	cmpb $0, %sil
	jl sens_temp_negative				# comp_rand < 0

	movb $ZERO_DEGREES, %r8b			# %r8b -> $ZERO_DEGREES
	cmpb $OFFSET_ZERO, %sil
	jle sens_temp_end				# comp_rand <= $OFFSET_ZERO

	movb $ONE_DEGREES, %r8b				# %r8b -> $ONE_DEGREES
	cmpb $OFFSET_ONE, %sil
	jle sens_temp_end				# comp_rand <= $OFFSET_ONE

	movb $TWO_DEGREES, %r8b				# %r8b -> $TWO_DEGREES
	cmpb $OFFSET_TWO, %sil
	jle sens_temp_end				# comp_rand <= $OFFSET_TWO

	movb $THREE_DEGREES, %r8b			# %r8b -> $THREE_DEGREES
	jmp sens_temp_end

sens_temp_negative:
	movb $-ZERO_DEGREES, %r8b			# %r8b -> $-ZERO_DEGREES
	cmpb $-OFFSET_ZERO, %sil
	jge sens_temp_end				# comp_rand >= $-OFFSET_ZERO

	movb $-ONE_DEGREES, %r8b			# %r8b -> $-ONE_DEGREES
	cmpb $-OFFSET_ONE, %sil
	jge sens_temp_end				# comp_rand >= $-OFFSET_ONE

	movb $-TWO_DEGREES, %r8b			# %r8b -> $-TWO_DEGREES
	cmpb $-OFFSET_TWO, %sil
	jge sens_temp_end				# comp_rand >= $-OFFSET_TWO

	movb $-THREE_DEGREES, %r8b			# %r8b -> $-THREE_DEGREES
	
sens_temp_end:
	addb %r8b, %dil					# Soma os graus necessários à medição de temperatura (ult_temp = ult_temp + %r8b)
	movb %dil, %al					# Valor do return
	ret

# ---------- VELOCIDADE DO VENTO -----------
sens_velc_vento:
	sarb $2, %sil			# Divide %sil por 4
	addb %sil, %dil			# Soma o resultado da divisão ao valor anterior da velocidade do vento

	cmpb $0, %dil			# Se o valor da velocidade do vento for positiva
	jge sens_velc_vento_end

	movb $0, %dil			# O valor da velocidade do vento não pode ser negativo

sens_velc_vento_end:
	movb %dil, %al			# Valor do return
	ret

# ---------- DIREÇÃO DO VENTO --------------
sens_dir_vento:
	movw %si, %ax					# %ax -> comp_rand
	movw $10, %cx					# Para realizar a divisão do valor aleatório por 10
	cwtd						# Extende o sinal de word presente no registo %ax para double word (long) no registo  %dx:%ax
	idivw %cx					# comp_rand / 10

	addw %dx, %di					# ult_velc_vento += (comp_rand % 10)

	cmpw $OUT_OF_RANGE_DIRECTION_DEGREES, %di 	# Se ult_velc_vento for maior ou igual ao primeiro valor fora do intervalo
	jge sens_dir_vento_new_value

	cmpw $MINIMUM_DIRECTION_DEGREES, %di		# Se ult_velc_vento for maior ou igual ao valor mínimo do intervalo
	jge sens_dir_vento_end

	negw %di					# Nega o número negativo para que fique com sinal positivo

sens_dir_vento_new_value:
	movw %di, %r8w					# %r8w -> %di
	movw $OUT_OF_RANGE_DIRECTION_DEGREES, %di	# %di -> $OUT_OF_RANGE_DIRECTION_DEGREES
	subw %r8w, %di					# %di = $OUT_OF_RANGE_DIRECTION_DEGREES - %r8w

	cmpw $MINIMUM_DIRECTION_DEGREES, %di		# %di >= $MINIMUM_DIRECTION_DEGREES
	jge sens_dir_vento_end

	negw %di					# Se o valor for negativo, fazer a sua negação para que ele fique igual mas com sinal positivo

sens_dir_vento_end:
	movw %di, %ax					# Valor do return
	ret

# ---------- HUMIDADE ATMOSFÉRICA ----------
sens_humd_atm:
	# Último valor da humidade atmosférica em %dil 
	# Último valor de pluviosidade em #sil
	# Componente aleatório em %dl
	
	cmpb $0, %sil
	je sens_humd_atm_pouca_variacao
	
sens_humd_atm_muita_variacao:
	sarb $2, %dl					# Divide %sil por 4
	addb %dl, %dil

	cmpb $0, %dil
	jge sens_humd_atm_end

	movb $0, %dil	
	jmp sens_humd_atm_end
	
sens_humd_atm_pouca_variacao:	
	movb $TWO_PERCENTAGE_ATMOSPHERIC, %r8b
	cmpb $OFFSET_ZERO, %dl
	jle sens_humd_atm_end

	movb $FOUR_PERCENTAGE_ATMOSPHERIC, %r8b
	cmpb $OFFSET_ONE, %dl
	jle sens_humd_atm_end

	movb $SIX_PERCENTAGE_ATMOSPHERIC, %r8b
	cmpb $OFFSET_TWO, %dl
	jle sens_humd_atm_end

	movb $EIGTH_PERCENTAGE_ATMOSPHERIC, %r8b
	addb %r8b, %dil	

sens_humd_atm_end:
	movb %dil, %al
	ret
	
# ---------- HUMIDADE DO SOLO --------------
sens_humd_solo:
	# Último valor da humidade do solo em %dil 
	# Último valor de pluviosidade em #sil
	# Componente aleatório em %dl
	
	cmpb $0, %sil
	je sens_humd_solo_pouca_variacao
	
sens_humd_solo_muita_variacao:
	sarb $2, %dl					# Divide %sil por 4
	addb %dl, %dil

	cmpb $0, %dil
	jge sens_humd_solo_end

	movb $0, %dil	
	jmp sens_humd_solo_end
	
sens_humd_solo_pouca_variacao:
	movb $FIVE_PERCENTAGE_GROUND, %r8b
	cmpb $OFFSET_ZERO, %dl
	jle sens_humd_solo_end

	movb $TEN_PERCENTAGE_GROUND, %r8b
	cmpb $OFFSET_ONE, %dl
	jle sens_humd_solo_end

	movb $FIFTEEN_PERCENTAGE_GROUND, %r8b
	cmpb $OFFSET_TWO, %dl
	jle sens_humd_solo_end

	movb $TWENTY_PERCENTAGE_GROUND, %r8b
	addb %r8b, %dil	

sens_humd_solo_end:
	movb %dil, %al
	ret
	
# ---------- PLUVIOSIDADE -----------------
sens_pluvio:
	# Último valor de pluviosidade em %dil 
	# Último valor de temperatura em #sil
	# Componente aleatório em %dl

	cmpb $MINIMUM_DEGREES, %sil
	jg sens_humd_solo_pouca_variacao
	
sens_pluvio_muita_variacao:
	sarb $1, %dl					# Divide %sil por 2
	addb %dl, %dil

	cmpb $0, %dil
	jge sens_humd_solo_end

	movb $0, %dil	
	jmp sens_humd_solo_end
	
sens_pluvio_pouca_variacao:
	movb $ZERO_PRECIPITATION, %r8b
	cmpb $OFFSET_ZERO, %dl
	jle sens_humd_solo_end

	movb $ONE_PRECIPITATION, %r8b
	cmpb $OFFSET_ONE, %dl
	jle sens_humd_solo_end

	movb $TWO_PRECIPITATION, %r8b
	cmpb $OFFSET_TWO, %dl
	jle sens_humd_solo_end

	movb $THREE_PRECIPITATION, %r8b
	addb %r8b, %dil	

sens_pluvio_end:
	movb %dil, %al
	ret
