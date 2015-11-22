.globl  main
.type main, @function
factorial:
  pushl %ebp
  movl %esp, %ebp
  subl $28,%esp
  movl $15,-4(%ebp)
  movl 8(%ebp), %eax
  cmp -4(%ebp), %eax
  jg  .true0
  movl $0, %eax
  jmp  .endtrue0
.true0:
  movl $1, %eax
.endtrue0:
  movl  %eax, -8(%ebp)
  movl -8(%ebp), %eax
  cmp $1, %eax
  jne .LIF2
  movl $-1, %eax
  leave
  ret

 jmp .LEIF3
.LIF2:
.LEIF3:
  movl $0,-12(%ebp)
  movl $1,-16(%ebp)
.BI4:
  movl -12(%ebp), %eax
  cmp 8(%ebp), %eax
  jl  .true1
  movl $0, %eax
  jmp  .endtrue1
.true1:
  movl $1, %eax
.endtrue1:
  movl  %eax, -20(%ebp)
  movl -20(%ebp), %eax
  cmp $1, %eax
  jne .EI5
  movl -12(%ebp), %eax
  addl $1, %eax
  movl  %eax, -24(%ebp)
  movl -24(%ebp), %eax
  movl %eax, -12(%ebp)
  movl -16(%ebp), %eax
  movl -12(%ebp), %edx
  imull %edx, %eax
  movl  %eax, -28(%ebp)
  movl -28(%ebp), %eax
  movl %eax, -16(%ebp)
 jmp .BI4
.EI5:
  movl -16(%ebp), %eax
  leave
  ret

factorialFor:
  pushl %ebp
  movl %esp, %ebp
  subl $28,%esp
  movl $15,-4(%ebp)
  movl 8(%ebp), %eax
  cmp -4(%ebp), %eax
  jg  .true2
  movl $0, %eax
  jmp  .endtrue2
.true2:
  movl $1, %eax
.endtrue2:
  movl  %eax, -8(%ebp)
  movl -8(%ebp), %eax
  cmp $1, %eax
  jne .LIF7
  movl $-1, %eax
  leave
  ret

 jmp .LEIF8
.LIF7:
.LEIF8:
  movl $0,-12(%ebp)
  movl $1,-16(%ebp)
.BI9:
  movl -12(%ebp), %eax
  cmp 8(%ebp), %eax
  jl  .true3
  movl $0, %eax
  jmp  .endtrue3
.true3:
  movl $1, %eax
.endtrue3:
  movl  %eax, -20(%ebp)
  movl -20(%ebp), %eax
  cmp $1, %eax
  jne .EI10
  movl -12(%ebp), %eax
  addl $1, %eax
  movl  %eax, -24(%ebp)
  movl -24(%ebp), %eax
  movl %eax, -12(%ebp)
  movl -16(%ebp), %eax
  movl -12(%ebp), %edx
  imull %edx, %eax
  movl  %eax, -28(%ebp)
  movl -28(%ebp), %eax
  movl %eax, -16(%ebp)
 jmp .BI9
.EI10:
  movl -16(%ebp), %eax
  leave
  ret

factorialF:
  pushl %ebp
  movl %esp, %ebp
  subl $28,%esp
      movl .LF0, %eax
      movl %eax, -4(%ebp)
      flds 8(%ebp)
      flds -4(%ebp)
      fucompp
      fnstsw %ax
      andb $69,%ah
  jg  .true4
  movl $0, %eax
  jmp  .endtrue4
.true4:
  movl $1, %eax
.endtrue4:
  movl  %eax, -16(%ebp)
  movl -16(%ebp), %eax
  cmp $1, %eax
  jne .LIF12
      movl .LF1, %eax
  leave
  ret

 jmp .LEIF13
.LIF12:
.LEIF13:
      movl .LF2, %eax
      movl %eax, -8(%ebp)
      movl .LF3, %eax
      movl %eax, -12(%ebp)
.BI14:
      flds -8(%ebp)
      flds 8(%ebp)
      fucompp
      fnstsw %ax
      andb $69,%ah
      cmpb $1,%ah
  jl  .true5
  movl $0, %eax
  jmp  .endtrue5
.true5:
  movl $1, %eax
.endtrue5:
  movl  %eax, -20(%ebp)
  movl -20(%ebp), %eax
  cmp $1, %eax
  jne .EI15
      flds -8(%ebp)
      flds .LF4
      faddp %st, %st(1)
      fstps -24(%ebp)
  movl -24(%ebp), %eax
  movl %eax, -8(%ebp)
      flds -12(%ebp)
      flds -8(%ebp)
      fmulp %st, %st(1)
      fstps -28(%ebp)
  movl -28(%ebp), %eax
  movl %eax, -12(%ebp)
 jmp .BI14
.EI15:
  movl -12(%ebp), %eax
  leave
  ret

factorialArray:
  pushl %ebp
  movl %esp, %ebp
  subl $104,%esp
  movl $15,-4(%ebp)
  movl $0,-8(%ebp)
.BI17:
  movl -8(%ebp), %eax
  cmp -4(%ebp), %eax
  jl  .true6
  movl $0, %eax
  jmp  .endtrue6
.true6:
  movl $1, %eax
.endtrue6:
  movl  %eax, -72(%ebp)
  movl -72(%ebp), %eax
  cmp $1, %eax
  jne .EI18
  movl $0,-76(%ebp)
  movl $1,-80(%ebp)
.BI19:
  movl -76(%ebp), %eax
  cmp -8(%ebp), %eax
  jl  .true7
  movl $0, %eax
  jmp  .endtrue7
.true7:
  movl $1, %eax
.endtrue7:
  movl  %eax, -84(%ebp)
  movl -84(%ebp), %eax
  cmp $1, %eax
  jne .EI20
  movl -76(%ebp), %eax
  addl $1, %eax
  movl  %eax, -88(%ebp)
  movl -88(%ebp), %eax
  movl %eax, -76(%ebp)
  movl -80(%ebp), %eax
  movl -76(%ebp), %edx
  imull %edx, %eax
  movl  %eax, -92(%ebp)
  movl -92(%ebp), %eax
  movl %eax, -80(%ebp)
 jmp .BI19
.EI20:
  movl -8(%ebp), %edx
  movl -80(%ebp), %eax
  movl %eax, -68(%ebp,%edx,4)
  movl -8(%ebp), %eax
  addl $1, %eax
  movl  %eax, -96(%ebp)
  movl -96(%ebp), %eax
  movl %eax, -8(%ebp)
 jmp .BI17
.EI18:
  movl -4(%ebp), %eax
  subl $1, %eax
  movl  %eax, -100(%ebp)
  movl 8(%ebp), %eax
  cmp -100(%ebp), %eax
  jg  .true8
  movl $0, %eax
  jmp  .endtrue8
.true8:
  movl $1, %eax
.endtrue8:
  movl  %eax, -104(%ebp)
  movl -104(%ebp), %eax
  cmp $1, %eax
  jne .LIF21
  movl $-1, %eax
  leave
  ret

 jmp .LEIF22
.LIF21:
  movl 8(%ebp), %edx
  movl -68(%ebp,%edx,4), %eax
  leave
  ret

.LEIF22:
  movl $-1, %eax
  leave
  ret

nthprime:
  pushl %ebp
  movl %esp, %ebp
  subl $56,%esp
  movl $0,-4(%ebp)
  movl $2,-8(%ebp)
  movl 8(%ebp), %eax
  addl $1, %eax
  movl  %eax, -12(%ebp)
  movl -12(%ebp), %eax
  movl %eax, 8(%ebp)
.BI24:
  movl 8(%ebp), %eax
  cmp $0, %eax
  jg  .true9
  movl $0, %eax
  jmp  .endtrue9
.true9:
  movl $1, %eax
.endtrue9:
  movl  %eax, -16(%ebp)
  movl -16(%ebp), %eax
  cmp $1, %eax
  jne .EI25
  movl $0,-20(%ebp)
  movl -4(%ebp), %eax
  addl $1, %eax
  movl  %eax, -24(%ebp)
  movl -24(%ebp), %eax
  movl %eax, -4(%ebp)
.BI26:
  movl -20(%ebp), %eax
  cmp $1, %eax
  jne  .true10
  movl $0, %eax
  jmp  .endtrue10
.true10:
  movl $1, %eax
.endtrue10:
  movl  %eax, -28(%ebp)
  movl -8(%ebp), %eax
  cmp -4(%ebp), %eax
  jl  .true11
  movl $0, %eax
  jmp  .endtrue11
.true11:
  movl $1, %eax
.endtrue11:
  movl  %eax, -32(%ebp)
  movl -28(%ebp), %eax
  and -32(%ebp), %eax
  movl  %eax, -36(%ebp)
  movl -36(%ebp), %eax
  cmp $1, %eax
  jne .EI27
  movl $0, %edx
  movl -4(%ebp), %eax
  movl -8(%ebp), %ecx
  cltd
  idivl %ecx
  movl  %edx, -40(%ebp)
  movl -40(%ebp), %eax
  cmp $0, %eax
  je  .true12
  movl $0, %eax
  jmp  .endtrue12
.true12:
  movl $1, %eax
.endtrue12:
  movl  %eax, -44(%ebp)
  movl -44(%ebp), %eax
  cmp $1, %eax
  jne .LIF28
  movl $1,-20(%ebp)
 jmp .LEIF29
.LIF28:
  movl -8(%ebp), %eax
  addl $1, %eax
  movl  %eax, -48(%ebp)
  movl -48(%ebp), %eax
  movl %eax, -8(%ebp)
.LEIF29:
 jmp .BI26
.EI27:
  movl $2,-8(%ebp)
  movl -20(%ebp), %eax
  cmp $1, %eax
  jne  .true13
  movl $0, %eax
  jmp  .endtrue13
.true13:
  movl $1, %eax
.endtrue13:
  movl  %eax, -52(%ebp)
  movl -52(%ebp), %eax
  cmp $1, %eax
  jne .LIF30
  movl 8(%ebp), %eax
  subl $1, %eax
  movl  %eax, -56(%ebp)
  movl -56(%ebp), %eax
  movl %eax, 8(%ebp)
 jmp .LEIF31
.LIF30:
.LEIF31:
 jmp .BI24
.EI25:
  movl -4(%ebp), %eax
  leave
  ret

nthprimeArray:
  pushl %ebp
  movl %esp, %ebp
  subl $420,%esp
  movl $0,-4(%ebp)
.BI33:
  movl -4(%ebp), %eax
  cmp $100, %eax
  jl  .true14
  movl $0, %eax
  jmp  .endtrue14
.true14:
  movl $1, %eax
.endtrue14:
  movl  %eax, -408(%ebp)
  movl -408(%ebp), %eax
  cmp $1, %eax
  jne .EI34
  pushl -4(%ebp)
  call nthprime
  movl  %eax, -412(%ebp)
  movl -4(%ebp), %edx
  movl -412(%ebp), %eax
  movl %eax, -404(%ebp,%edx,4)
  movl -4(%ebp), %eax
  addl $1, %eax
  movl  %eax, -416(%ebp)
  movl -416(%ebp), %eax
  movl %eax, -4(%ebp)
 jmp .BI33
.EI34:
  movl 8(%ebp), %eax
  subl $1, %eax
  movl  %eax, -420(%ebp)
  movl -420(%ebp), %edx
  movl -404(%ebp,%edx,4), %eax
  leave
  ret

int2bin:
  pushl %ebp
  movl %esp, %ebp
  subl $68,%esp
  movl $0,-4(%ebp)
  movl $0,-12(%ebp)
.BI36:
  movl $1, %eax
  cmp $1, %eax
  jne .EI37
  movl 8(%ebp), %eax
  cmp $1, %eax
  jg  .true15
  movl $0, %eax
  jmp  .endtrue15
.true15:
  movl $1, %eax
.endtrue15:
  movl  %eax, -16(%ebp)
  movl -16(%ebp), %eax
  cmp $1, %eax
  jne .LIF38
  movl $0, %edx
  movl 8(%ebp), %eax
  movl $2, %ecx
  cltd
  idivl %ecx
  movl  %edx, -24(%ebp)
  movl -24(%ebp), %eax
  movl %eax, -8(%ebp)
  movl $0,-20(%ebp)
.BI40:
  movl $1, %eax
  cmp $1, %eax
  jne .EI41
  movl -20(%ebp), %eax
  cmp -12(%ebp), %eax
  jl  .true16
  movl $0, %eax
  jmp  .endtrue16
.true16:
  movl $1, %eax
.endtrue16:
  movl  %eax, -28(%ebp)
  movl -28(%ebp), %eax
  cmp $1, %eax
  jne .LIF42
  movl -8(%ebp), %eax
  imull $10, %eax
  movl  %eax, -32(%ebp)
  movl -32(%ebp), %eax
  movl %eax, -8(%ebp)
  movl -20(%ebp), %eax
  addl $1, %eax
  movl  %eax, -36(%ebp)
  movl -36(%ebp), %eax
  movl %eax, -20(%ebp)
 jmp .BI40
 jmp .LEIF43
.LIF42:
 jmp .EI41
.LEIF43:
 jmp .BI40
.EI41:
  movl -4(%ebp), %eax
  movl -8(%ebp), %edx
  addl %edx, %eax
  movl  %eax, -40(%ebp)
  movl -40(%ebp), %eax
  movl %eax, -4(%ebp)
  movl -12(%ebp), %eax
  addl $1, %eax
  movl  %eax, -44(%ebp)
  movl -44(%ebp), %eax
  movl %eax, -12(%ebp)
  movl $0, %edx
  movl 8(%ebp), %eax
  movl $2, %ecx
  cltd
  idivl %ecx
  movl  %eax, -48(%ebp)
  movl -48(%ebp), %eax
  movl %eax, 8(%ebp)
 jmp .BI36
 jmp .LEIF39
.LIF38:
 jmp .EI37
.LEIF39:
 jmp .BI36
.EI37:
  movl $0,-52(%ebp)
.BI44:
  movl $1, %eax
  cmp $1, %eax
  jne .EI45
  movl -52(%ebp), %eax
  cmp -12(%ebp), %eax
  jl  .true17
  movl $0, %eax
  jmp  .endtrue17
.true17:
  movl $1, %eax
.endtrue17:
  movl  %eax, -56(%ebp)
  movl -56(%ebp), %eax
  cmp $1, %eax
  jne .LIF46
  movl 8(%ebp), %eax
  imull $10, %eax
  movl  %eax, -60(%ebp)
  movl -60(%ebp), %eax
  movl %eax, 8(%ebp)
  movl -52(%ebp), %eax
  addl $1, %eax
  movl  %eax, -64(%ebp)
  movl -64(%ebp), %eax
  movl %eax, -52(%ebp)
 jmp .BI44
 jmp .LEIF47
.LIF46:
 jmp .EI45
.LEIF47:
 jmp .BI44
.EI45:
  movl -4(%ebp), %eax
  movl 8(%ebp), %edx
  addl %edx, %eax
  movl  %eax, -68(%ebp)
  movl -68(%ebp), %eax
  leave
  ret

gcd:
  pushl %ebp
  movl %esp, %ebp
  subl $40,%esp
  movl $1,-4(%ebp)
  movl -4(%ebp), %eax
  movl %eax, -8(%ebp)
.BI49:
  movl 8(%ebp), %eax
  movl 12(%ebp), %edx
  addl %edx, %eax
  movl  %eax, -12(%ebp)
  movl -4(%ebp), %eax
  cmp -12(%ebp), %eax
  jl  .true18
  movl $0, %eax
  jmp  .endtrue18
.true18:
  movl $1, %eax
.endtrue18:
  movl  %eax, -16(%ebp)
  movl -16(%ebp), %eax
  cmp $1, %eax
  jne .EI50
  movl $0, %edx
  movl 8(%ebp), %eax
  movl -4(%ebp), %ecx
  cltd
  idivl %ecx
  movl  %edx, -20(%ebp)
  movl -20(%ebp), %eax
  cmp $0, %eax
  je  .true19
  movl $0, %eax
  jmp  .endtrue19
.true19:
  movl $1, %eax
.endtrue19:
  movl  %eax, -24(%ebp)
  movl $0, %edx
  movl 12(%ebp), %eax
  movl -4(%ebp), %ecx
  cltd
  idivl %ecx
  movl  %edx, -28(%ebp)
  movl -28(%ebp), %eax
  cmp $0, %eax
  je  .true20
  movl $0, %eax
  jmp  .endtrue20
.true20:
  movl $1, %eax
.endtrue20:
  movl  %eax, -32(%ebp)
  movl -24(%ebp), %eax
  and -32(%ebp), %eax
  movl  %eax, -36(%ebp)
  movl -36(%ebp), %eax
  cmp $1, %eax
  jne .LIF51
  movl -4(%ebp), %eax
  movl %eax, -8(%ebp)
 jmp .LEIF52
.LIF51:
.LEIF52:
  movl -4(%ebp), %eax
  addl $1, %eax
  movl  %eax, -40(%ebp)
  movl -40(%ebp), %eax
  movl %eax, -4(%ebp)
 jmp .BI49
.EI50:
  movl -8(%ebp), %eax
  leave
  ret

potenciaR:
  pushl %ebp
  movl %esp, %ebp
  subl $28,%esp
      movl .LF5, %eax
      movl %eax, -8(%ebp)
  movl $1,-4(%ebp)
.BI54:
  movl $1, %eax
  cmp $1, %eax
  jne .EI55
  movl -4(%ebp), %eax
  cmp 12(%ebp), %eax
  jl  .true21
  movl $0, %eax
  jmp  .endtrue21
.true21:
  movl $1, %eax
.endtrue21:
  movl  %eax, -12(%ebp)
  movl -4(%ebp), %eax
  cmp 12(%ebp), %eax
  je  .true22
  movl $0, %eax
  jmp  .endtrue22
.true22:
  movl $1, %eax
.endtrue22:
  movl  %eax, -16(%ebp)
  movl -12(%ebp), %eax
  or -16(%ebp), %eax
  movl  %eax, -20(%ebp)
  movl -20(%ebp), %eax
  cmp $1, %eax
  jne .LIF56
      flds -8(%ebp)
      flds 8(%ebp)
      fmulp %st, %st(1)
      fstps -24(%ebp)
  movl -24(%ebp), %eax
  movl %eax, -8(%ebp)
  movl -4(%ebp), %eax
  addl $1, %eax
  movl  %eax, -28(%ebp)
  movl -28(%ebp), %eax
  movl %eax, -4(%ebp)
 jmp .LEIF57
.LIF56:
 jmp .EI55
.LEIF57:
 jmp .BI54
.EI55:
  movl -8(%ebp), %eax
  leave
  ret

test:
  pushl %ebp
  movl %esp, %ebp
  subl $64,%esp
      movl .LF6, %eax
      movl %eax, -4(%ebp)
  pushl $3
  call factorial
  movl  %eax, -8(%ebp)
  pushl $4
  call factorial
  movl  %eax, -12(%ebp)
  pushl -12(%ebp)
  pushl -8(%ebp)
  call gcd
  movl  %eax, -16(%ebp)
  pushl -16(%ebp)
  call print_int
  movl  %eax, -20(%ebp)
  pushl $3
  call factorial
  movl  %eax, -24(%ebp)
  pushl $4
  call factorial
  movl  %eax, -28(%ebp)
  pushl -28(%ebp)
  pushl -24(%ebp)
  call gcd
  movl  %eax, -32(%ebp)
  pushl -32(%ebp)
  call nthprimeArray
  movl  %eax, -36(%ebp)
  pushl -36(%ebp)
  call print_int
  movl  %eax, -40(%ebp)
  pushl $3
  call factorial
  movl  %eax, -44(%ebp)
  pushl $4
  call factorial
  movl  %eax, -48(%ebp)
  pushl -48(%ebp)
  pushl -44(%ebp)
  call gcd
  movl  %eax, -52(%ebp)
  pushl -52(%ebp)
  call nthprimeArray
  movl  %eax, -56(%ebp)
  pushl -56(%ebp)
  pushl -4(%ebp)
  call potenciaR
  movl  %eax, -60(%ebp)
  movl -60(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  call print_float
  movl  %eax, -64(%ebp)
  leave
  ret

test1:
  pushl %ebp
  movl %esp, %ebp
  subl $12,%esp
      movl .LF7, %eax
      movl %eax, -4(%ebp)
  call test
  movl  %eax, -8(%ebp)
  pushl -4(%ebp)
  call print_float
  movl  %eax, -12(%ebp)
  leave
  ret

main:
  pushl %ebp
  movl %esp, %ebp
  subl $268,%esp
  pushl $1
  call init_input
  movl  %eax, -12(%ebp)
  pushl $2
  call print_string
  movl  %eax, -16(%ebp)
  call get_int
  movl  %eax, -20(%ebp)
  movl -20(%ebp), %eax
  movl %eax, -4(%ebp)
  movl $0,-8(%ebp)
.BI61:
  movl -8(%ebp), %eax
  cmp -4(%ebp), %eax
  jl  .true23
  movl $0, %eax
  jmp  .endtrue23
.true23:
  movl $1, %eax
.endtrue23:
  movl  %eax, -24(%ebp)
  movl -24(%ebp), %eax
  cmp $1, %eax
  jne .EI62
  call get_int
  movl  %eax, -32(%ebp)
  movl -32(%ebp), %eax
  movl %eax, -28(%ebp)
  pushl -28(%ebp)
  call factorial
  movl  %eax, -36(%ebp)
  movl -36(%ebp), %eax
  movl %eax, -28(%ebp)
  pushl -28(%ebp)
  call print_int
  movl  %eax, -40(%ebp)
  movl -8(%ebp), %eax
  addl $1, %eax
  movl  %eax, -8(%ebp)
 jmp .BI61
.EI62:
  pushl $1
  call print_string
  movl  %eax, -44(%ebp)
  pushl $3
  call print_string
  movl  %eax, -48(%ebp)
  call get_int
  movl  %eax, -52(%ebp)
  movl -52(%ebp), %eax
  movl %eax, -4(%ebp)
  movl $0,-8(%ebp)
.BI63:
  movl -8(%ebp), %eax
  cmp -4(%ebp), %eax
  jl  .true24
  movl $0, %eax
  jmp  .endtrue24
.true24:
  movl $1, %eax
.endtrue24:
  movl  %eax, -56(%ebp)
  movl -56(%ebp), %eax
  cmp $1, %eax
  jne .EI64
  call get_float
  fstps -64(%ebp)
  movl -64(%ebp), %eax
  movl %eax, -60(%ebp)
  pushl -60(%ebp)
  call factorialF
  movl  %eax, -68(%ebp)
  movl -68(%ebp), %eax
  movl %eax, -60(%ebp)
  pushl -60(%ebp)
  call print_float
  movl  %eax, -72(%ebp)
  movl -8(%ebp), %eax
  addl $1, %eax
  movl  %eax, -8(%ebp)
 jmp .BI63
.EI64:
  pushl $1
  call print_string
  movl  %eax, -76(%ebp)
  pushl $4
  call print_string
  movl  %eax, -80(%ebp)
  call get_int
  movl  %eax, -84(%ebp)
  movl -84(%ebp), %eax
  movl %eax, -4(%ebp)
  movl $0,-8(%ebp)
.BI65:
  movl -8(%ebp), %eax
  cmp -4(%ebp), %eax
  jl  .true25
  movl $0, %eax
  jmp  .endtrue25
.true25:
  movl $1, %eax
.endtrue25:
  movl  %eax, -88(%ebp)
  movl -88(%ebp), %eax
  cmp $1, %eax
  jne .EI66
  call get_int
  movl  %eax, -96(%ebp)
  movl -96(%ebp), %eax
  movl %eax, -92(%ebp)
  pushl -92(%ebp)
  call factorialArray
  movl  %eax, -100(%ebp)
  movl -100(%ebp), %eax
  movl %eax, -92(%ebp)
  pushl -92(%ebp)
  call print_int
  movl  %eax, -104(%ebp)
  movl -8(%ebp), %eax
  addl $1, %eax
  movl  %eax, -8(%ebp)
 jmp .BI65
.EI66:
  pushl $1
  call print_string
  movl  %eax, -108(%ebp)
  pushl $5
  call print_string
  movl  %eax, -112(%ebp)
  call get_int
  movl  %eax, -116(%ebp)
  movl -116(%ebp), %eax
  movl %eax, -4(%ebp)
  movl $0,-8(%ebp)
.BI67:
  movl -8(%ebp), %eax
  cmp -4(%ebp), %eax
  jl  .true26
  movl $0, %eax
  jmp  .endtrue26
.true26:
  movl $1, %eax
.endtrue26:
  movl  %eax, -120(%ebp)
  movl -120(%ebp), %eax
  cmp $1, %eax
  jne .EI68
  call get_int
  movl  %eax, -128(%ebp)
  movl -128(%ebp), %eax
  movl %eax, -124(%ebp)
  pushl -124(%ebp)
  call nthprime
  movl  %eax, -132(%ebp)
  movl -132(%ebp), %eax
  movl %eax, -124(%ebp)
  pushl -124(%ebp)
  call print_int
  movl  %eax, -136(%ebp)
  movl -8(%ebp), %eax
  addl $1, %eax
  movl  %eax, -8(%ebp)
 jmp .BI67
.EI68:
  pushl $1
  call print_string
  movl  %eax, -140(%ebp)
  pushl $6
  call print_string
  movl  %eax, -144(%ebp)
  call get_int
  movl  %eax, -148(%ebp)
  movl -148(%ebp), %eax
  movl %eax, -4(%ebp)
  movl $0,-8(%ebp)
.BI69:
  movl -8(%ebp), %eax
  cmp -4(%ebp), %eax
  jl  .true27
  movl $0, %eax
  jmp  .endtrue27
.true27:
  movl $1, %eax
.endtrue27:
  movl  %eax, -152(%ebp)
  movl -152(%ebp), %eax
  cmp $1, %eax
  jne .EI70
  call get_int
  movl  %eax, -160(%ebp)
  movl -160(%ebp), %eax
  movl %eax, -156(%ebp)
  pushl -156(%ebp)
  call nthprimeArray
  movl  %eax, -164(%ebp)
  movl -164(%ebp), %eax
  movl %eax, -156(%ebp)
  pushl -156(%ebp)
  call print_int
  movl  %eax, -168(%ebp)
  movl -8(%ebp), %eax
  addl $1, %eax
  movl  %eax, -8(%ebp)
 jmp .BI69
.EI70:
  pushl $1
  call print_string
  movl  %eax, -172(%ebp)
  pushl $7
  call print_string
  movl  %eax, -176(%ebp)
  call get_int
  movl  %eax, -180(%ebp)
  movl -180(%ebp), %eax
  movl %eax, -4(%ebp)
  movl $0,-8(%ebp)
.BI71:
  movl -8(%ebp), %eax
  cmp -4(%ebp), %eax
  jl  .true28
  movl $0, %eax
  jmp  .endtrue28
.true28:
  movl $1, %eax
.endtrue28:
  movl  %eax, -184(%ebp)
  movl -184(%ebp), %eax
  cmp $1, %eax
  jne .EI72
  call get_int
  movl  %eax, -192(%ebp)
  movl -192(%ebp), %eax
  movl %eax, -188(%ebp)
  pushl -188(%ebp)
  call int2bin
  movl  %eax, -196(%ebp)
  movl -196(%ebp), %eax
  movl %eax, -188(%ebp)
  pushl -188(%ebp)
  call print_int
  movl  %eax, -200(%ebp)
  movl -8(%ebp), %eax
  addl $1, %eax
  movl  %eax, -8(%ebp)
 jmp .BI71
.EI72:
  pushl $1
  call print_string
  movl  %eax, -204(%ebp)
  pushl $8
  call print_string
  movl  %eax, -208(%ebp)
  call get_int
  movl  %eax, -212(%ebp)
  movl -212(%ebp), %eax
  movl %eax, -4(%ebp)
  movl $0,-8(%ebp)
.BI73:
  movl -8(%ebp), %eax
  cmp -4(%ebp), %eax
  jl  .true29
  movl $0, %eax
  jmp  .endtrue29
.true29:
  movl $1, %eax
.endtrue29:
  movl  %eax, -216(%ebp)
  movl -216(%ebp), %eax
  cmp $1, %eax
  jne .EI74
  call get_int
  movl  %eax, -224(%ebp)
  call get_int
  movl  %eax, -228(%ebp)
  pushl -228(%ebp)
  pushl -224(%ebp)
  call gcd
  movl  %eax, -232(%ebp)
  movl -232(%ebp), %eax
  movl %eax, -220(%ebp)
  pushl -220(%ebp)
  call print_int
  movl  %eax, -236(%ebp)
  movl -8(%ebp), %eax
  addl $1, %eax
  movl  %eax, -8(%ebp)
 jmp .BI73
.EI74:
  pushl $1
  call print_string
  movl  %eax, -240(%ebp)
  pushl $9
  call print_string
  movl  %eax, -244(%ebp)
  call test
  movl  %eax, -248(%ebp)
  pushl $1
  call print_string
  movl  %eax, -252(%ebp)
  pushl $10
  call print_string
  movl  %eax, -256(%ebp)
  call test1
  movl  %eax, -260(%ebp)
  pushl $1
  call print_string
  movl  %eax, -264(%ebp)
  call close_input
  movl  %eax, -268(%ebp)
  leave
  ret

.LF0:
      .float 15.0
.LF1:
      .float -1.0
.LF2:
      .float 0.0
.LF3:
      .float 1.0
.LF4:
      .float 1.0
.LF5:
      .float 1.0
.LF6:
      .float 2.0
.LF7:
      .float 2.0
