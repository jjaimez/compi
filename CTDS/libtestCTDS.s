	.file	"libtestCTDS.c"
	.comm	fp,4,4
	.comm	buffer,100,32
	.section	.rodata
.LC0:
	.string	"r"
.LC1:
	.string	"input"
.LC2:
	.string	"input1"
	.text
	.globl	init_input
	.type	init_input, @function
init_input:
.LFB0:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	subl	$8, %esp
	cmpl	$1, 8(%ebp)
	jne	.L2
	subl	$8, %esp
	pushl	$.LC0
	pushl	$.LC1
	call	fopen
	addl	$16, %esp
	movl	%eax, fp
	jmp	.L3
.L2:
	subl	$8, %esp
	pushl	$.LC0
	pushl	$.LC2
	call	fopen
	addl	$16, %esp
	movl	%eax, fp
.L3:
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE0:
	.size	init_input, .-init_input
	.globl	close_input
	.type	close_input, @function
close_input:
.LFB1:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	subl	$8, %esp
	movl	fp, %eax
	subl	$12, %esp
	pushl	%eax
	call	fclose
	addl	$16, %esp
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE1:
	.size	close_input, .-close_input
	.section	.rodata
.LC3:
	.string	"%s"
.LC4:
	.string	"%d"
	.text
	.globl	get_int
	.type	get_int, @function
get_int:
.LFB2:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	subl	$24, %esp
	movl	fp, %eax
	subl	$4, %esp
	pushl	$buffer
	pushl	$.LC3
	pushl	%eax
	call	__isoc99_fscanf
	addl	$16, %esp
	subl	$4, %esp
	leal	-12(%ebp), %eax
	pushl	%eax
	pushl	$.LC4
	pushl	$buffer
	call	__isoc99_sscanf
	addl	$16, %esp
	movl	-12(%ebp), %eax
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE2:
	.size	get_int, .-get_int
	.section	.rodata
.LC5:
	.string	"%d\n"
	.text
	.globl	print_int
	.type	print_int, @function
print_int:
.LFB3:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	subl	$8, %esp
	subl	$8, %esp
	pushl	8(%ebp)
	pushl	$.LC5
	call	printf
	addl	$16, %esp
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE3:
	.size	print_int, .-print_int
	.section	.rodata
.LC6:
	.string	"%f"
	.text
	.globl	get_float
	.type	get_float, @function
get_float:
.LFB4:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	subl	$24, %esp
	movl	fp, %eax
	subl	$4, %esp
	pushl	$buffer
	pushl	$.LC3
	pushl	%eax
	call	__isoc99_fscanf
	addl	$16, %esp
	subl	$4, %esp
	leal	-12(%ebp), %eax
	pushl	%eax
	pushl	$.LC6
	pushl	$buffer
	call	__isoc99_sscanf
	addl	$16, %esp
	flds	-12(%ebp)
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE4:
	.size	get_float, .-get_float
	.section	.rodata
.LC8:
	.string	"%f***\n"
	.text
	.globl	print_float
	.type	print_float, @function
print_float:
.LFB5:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	subl	$8, %esp
	flds	8(%ebp)
	subl	$4, %esp
	leal	-8(%esp), %esp
	fstpl	(%esp)
	pushl	$.LC8
	call	printf
	addl	$16, %esp
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE5:
	.size	print_float, .-print_float
	.section	.rodata
	.align 4
.LC9:
	.string	"---------------------------------------------------------"
	.align 4
.LC10:
	.string	"Factorial Enteros----------------------------------"
	.align 4
.LC11:
	.string	"Factorial Reales----------------------------------"
	.align 4
.LC12:
	.string	"Factorial Array Enteros----------------------------------"
	.align 4
.LC13:
	.string	"Nthprime Enteros----------------------------------"
	.align 4
.LC14:
	.string	"Nthprime Array Enteros----------------------------------"
	.align 4
.LC15:
	.string	"Int2Bin Enteros----------------------------------"
	.align 4
.LC16:
	.string	"GCD Enteros----------------------------------"
	.align 4
.LC17:
	.string	"test----------------------------------"
	.align 4
.LC18:
	.string	"test1----------------------------------"
	.text
	.globl	print_string
	.type	print_string, @function
print_string:
.LFB6:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	subl	$8, %esp
	cmpl	$1, 8(%ebp)
	jne	.L12
	subl	$12, %esp
	pushl	$.LC9
	call	puts
	addl	$16, %esp
.L12:
	cmpl	$2, 8(%ebp)
	jne	.L13
	subl	$12, %esp
	pushl	$.LC10
	call	puts
	addl	$16, %esp
.L13:
	cmpl	$3, 8(%ebp)
	jne	.L14
	subl	$12, %esp
	pushl	$.LC11
	call	puts
	addl	$16, %esp
.L14:
	cmpl	$4, 8(%ebp)
	jne	.L15
	subl	$12, %esp
	pushl	$.LC12
	call	puts
	addl	$16, %esp
.L15:
	cmpl	$5, 8(%ebp)
	jne	.L16
	subl	$12, %esp
	pushl	$.LC13
	call	puts
	addl	$16, %esp
.L16:
	cmpl	$6, 8(%ebp)
	jne	.L17
	subl	$12, %esp
	pushl	$.LC14
	call	puts
	addl	$16, %esp
.L17:
	cmpl	$7, 8(%ebp)
	jne	.L18
	subl	$12, %esp
	pushl	$.LC15
	call	puts
	addl	$16, %esp
.L18:
	cmpl	$8, 8(%ebp)
	jne	.L19
	subl	$12, %esp
	pushl	$.LC16
	call	puts
	addl	$16, %esp
.L19:
	cmpl	$9, 8(%ebp)
	jne	.L20
	subl	$12, %esp
	pushl	$.LC17
	call	puts
	addl	$16, %esp
.L20:
	cmpl	$10, 8(%ebp)
	jne	.L22
	subl	$12, %esp
	pushl	$.LC18
	call	puts
	addl	$16, %esp
.L22:
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE6:
	.size	print_string, .-print_string
	.ident	"GCC: (Debian 5.2.1-23) 5.2.1 20151028"
	.section	.note.GNU-stack,"",@progbits
