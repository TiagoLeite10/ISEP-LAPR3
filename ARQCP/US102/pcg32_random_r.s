.section .data
	.global state, inc

.section .text
	.global pcg32_random_r #uint32_t pcg32_random_r(void)

pcg32_random_r:
	pushq %rbp
	movq %rsp, %rbp

	movq state(%rip), %r8
	movq inc(%rip), %r9
			
	#uint64_t oldstate = state;
	movq %r8, -32(%rbp)
	movq %r8, -36(%rbp)

	#state = oldstate * 6364136223846793005ULL + (inc | 1);
	movabsq $6364136223846793005, %rdx
	imulq -32(%rbp), %rdx
	orq $1, %r9
	addq %r9, %rdx
	movq %rdx, state(%rip)

	#uint32_t xorshifted = ((oldstate >> 18u) ^ oldstate) >> 27u;
	shrq $18, -32(%rbp) 
	xorq %r8, -32(%rbp)
	shrq $27, -32(%rbp)
	movq -32(%rbp), %rcx 
	movq %rcx, -24(%rbp)
	movq %rcx, -20(%rbp)
	
	#uint32_t rot = oldstate >> 59u;
	shrq $59, -36(%rbp)
	movq -36(%rbp), %rcx
	movq %rcx, -40(%rbp)

	#return (xorshifted >> rot) | (xorshifted << ((-rot) & 31));
	movq -40(%rbp), %rcx
	shrq %rcx, -24(%rbp)	
	not %rcx
	and $31, %rcx
	shlq %rcx, -20(%rbp)
	movq -20(%rbp), %rcx
	or -24(%rbp), %rcx

	movq %rcx, %rax

	movq %rbp, %rsp
	popq %rbp
	ret
