.globl  main
.type main, @function
breaks:
  pushl %ebp
  movl %esp, %ebp
  subl $28,%esp
  movl $1,-4(%ebp)
  movl -4(%ebp), %eax
  cmp $0, %eax
  jge  .true0
  movl $0, %eax
  jmp  .endtrue0
.true0:
  movl $1, %eax
.endtrue0:
  movl  %eax, -8(%ebp)
  movl -8(%ebp), %eax
  cmp $1, %eax
  jne .LIF2
  pushl -4(%ebp)
  call printInt
  movl  %eax, -12(%ebp)
  movl -4(%ebp), %eax
  subl $1, %eax
  movl  %eax, -16(%ebp)
  movl -16(%ebp), %eax
  movl %eax, -4(%ebp)
  movl -4(%ebp), %eax
  cmp $0, %eax
  jl  .true1
  movl $0, %eax
  jmp  .endtrue1
.true1:
  movl $1, %eax
.endtrue1:
  movl  %eax, -20(%ebp)
  movl -20(%ebp), %eax
  cmp $1, %eax
  jne .LIF4
  pushl -4(%ebp)
  call printInt
  movl  %eax, -24(%ebp)
 jmp .LEIF5
.LIF4:
  pushl -4(%ebp)
  call printInt
  movl  %eax, -28(%ebp)
.LEIF5:
 jmp .LEIF3
.LIF2:
.LEIF3:
  movl -4(%ebp), %eax
  leave
  ret

main:
  pushl %ebp
  movl %esp, %ebp
  subl $12,%esp
  pushl $1
  call breaks
  movl  %eax, -8(%ebp)
  movl -8(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  call printInt
  movl  %eax, -12(%ebp)
  leave
  ret

