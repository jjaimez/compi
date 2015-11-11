.globl  main
.type main, @function
assert:
  pushl %ebp
  movl %esp, %ebp
  subl $260,%esp
  movl $4,-4(%ebp)
  pushl -4(%ebp)
  pushl $4
  call assertEquals
  movl  %eax, -16(%ebp)
  movl $4, %eax
  addl $5, %eax
  movl  %eax, -20(%ebp)
  movl -20(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $9
  call assertEquals
  movl  %eax, -24(%ebp)
  movl $0,-4(%ebp)
  movl -4(%ebp), %eax
  addl $4, %eax
  movl  %eax, -28(%ebp)
  movl -28(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $4
  call assertEquals
  movl  %eax, -32(%ebp)
  movl $0,-4(%ebp)
  movl -4(%ebp), %eax
  addl $4, %eax
  movl  %eax, -36(%ebp)
  movl -36(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $4
  call assertEquals
  movl  %eax, -40(%ebp)
  movl $0,-4(%ebp)
  movl -4(%ebp), %eax
  movl -4(%ebp), %edx
  addl %edx, %eax
  movl  %eax, -44(%ebp)
  movl -44(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $0
  call assertEquals
  movl  %eax, -48(%ebp)
  movl $5, %eax
  subl $4, %eax
  movl  %eax, -52(%ebp)
  movl -52(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $1
  call assertEquals
  movl  %eax, -56(%ebp)
  movl $0,-4(%ebp)
  movl -4(%ebp), %eax
  subl $4, %eax
  movl  %eax, -60(%ebp)
  movl -60(%ebp), %eax
  movl %eax, -4(%ebp)
  movl $4, %eax
  not  %eax
  movl  %eax, -64(%ebp)
  pushl -4(%ebp)
  pushl -64(%ebp)
  call assertEquals
  movl  %eax, -68(%ebp)
  movl $2,-4(%ebp)
  movl $4, %eax
  subl -4(%ebp), %eax
  movl  %eax, -72(%ebp)
  movl -72(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $2
  call assertEquals
  movl  %eax, -76(%ebp)
  movl $5,-4(%ebp)
  movl -4(%ebp), %eax
  movl -4(%ebp), %edx
  subl %edx, %eax
  movl  %eax, -80(%ebp)
  movl -80(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $0
  call assertEquals
  movl  %eax, -84(%ebp)
  movl $3, %eax
  imull $2, %eax
  movl  %eax, -88(%ebp)
  movl -88(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $6
  call assertEquals
  movl  %eax, -92(%ebp)
  movl $2,-4(%ebp)
  movl -4(%ebp), %eax
  imull $4, %eax
  movl  %eax, -96(%ebp)
  movl -96(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $8
  call assertEquals
  movl  %eax, -100(%ebp)
  movl $2,-4(%ebp)
  movl -4(%ebp), %eax
  imull $4, %eax
  movl  %eax, -104(%ebp)
  movl -104(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $8
  call assertEquals
  movl  %eax, -108(%ebp)
  movl $5,-4(%ebp)
  movl -4(%ebp), %eax
  movl -4(%ebp), %edx
  imull %edx, %eax
  movl  %eax, -112(%ebp)
  movl -112(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $25
  call assertEquals
  movl  %eax, -116(%ebp)
  movl $0, %edx
  movl $6, %eax
  movl $2, %ecx
  cltd
  idivl %ecx
  movl  %eax, -120(%ebp)
  movl -120(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $3
  call assertEquals
  movl  %eax, -124(%ebp)
  movl $8,-4(%ebp)
  movl $0, %edx
  movl -4(%ebp), %eax
  movl $4, %ecx
  cltd
  idivl %ecx
  movl  %eax, -128(%ebp)
  movl -128(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $2
  call assertEquals
  movl  %eax, -132(%ebp)
  movl $2,-4(%ebp)
  movl $0, %edx
  movl $4, %eax
  movl -4(%ebp), %ecx
  cltd
  idivl %ecx
  movl  %eax, -136(%ebp)
  movl -136(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $2
  call assertEquals
  movl  %eax, -140(%ebp)
  movl $5,-4(%ebp)
  movl $0, %edx
  movl -4(%ebp), %eax
  movl -4(%ebp), %ecx
  cltd
  idivl %ecx
  movl  %eax, -144(%ebp)
  movl -144(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $1
  call assertEquals
  movl  %eax, -148(%ebp)
  movl $0, %edx
  movl $6, %eax
  movl $2, %ecx
  cltd
  idivl %ecx
  movl  %edx, -152(%ebp)
  movl -152(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $0
  call assertEquals
  movl  %eax, -156(%ebp)
  movl $8,-4(%ebp)
  movl $0, %edx
  movl -4(%ebp), %eax
  movl $3, %ecx
  cltd
  idivl %ecx
  movl  %eax, -160(%ebp)
  movl -160(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $2
  call assertEquals
  movl  %eax, -164(%ebp)
  movl $2,-4(%ebp)
  movl $0, %edx
  movl $4, %eax
  movl -4(%ebp), %ecx
  cltd
  idivl %ecx
  movl  %eax, -168(%ebp)
  movl -168(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $0
  call assertEquals
  movl  %eax, -172(%ebp)
  movl $5,-4(%ebp)
  movl $0, %edx
  movl -4(%ebp), %eax
  movl -4(%ebp), %ecx
  cltd
  idivl %ecx
  movl  %eax, -176(%ebp)
  movl -176(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $0
  call assertEquals
  movl  %eax, -180(%ebp)
  movl $1,-8(%ebp)
  movl $1, %eax
  and $1, %eax
  movl  %eax, -184(%ebp)
  movl -184(%ebp), %eax
  cmp $1, %eax
  jne .LIF2
  call printIf
  movl  %eax, -188(%ebp)
 jmp .LEIF3
.LIF2:
.LEIF3:
  movl -8(%ebp), %eax
  and $1, %eax
  movl  %eax, -192(%ebp)
  movl -192(%ebp), %eax
  cmp $1, %eax
  jne .LIF4
  call printIf
  movl  %eax, -196(%ebp)
 jmp .LEIF5
.LIF4:
.LEIF5:
  movl -8(%ebp), %eax
  and $1, %eax
  movl  %eax, -200(%ebp)
  movl -200(%ebp), %eax
  cmp $1, %eax
  jne .LIF6
  call printIf
  movl  %eax, -204(%ebp)
 jmp .LEIF7
.LIF6:
.LEIF7:
  movl -8(%ebp), %eax
  and -8(%ebp), %eax
  movl  %eax, -208(%ebp)
  movl -208(%ebp), %eax
  cmp $1, %eax
  jne .LIF8
  call printIf
  movl  %eax, -212(%ebp)
 jmp .LEIF9
.LIF8:
.LEIF9:
  movl $0,-8(%ebp)
  movl $0, %eax
  and $1, %eax
  movl  %eax, -216(%ebp)
  movl -216(%ebp), %eax
  cmp $1, %eax
  jne .LIF10
  call printIf
  movl  %eax, -220(%ebp)
 jmp .LEIF11
.LIF10:
  call printElse
  movl  %eax, -224(%ebp)
.LEIF11:
  movl -8(%ebp), %eax
  and $1, %eax
  movl  %eax, -228(%ebp)
  movl -228(%ebp), %eax
  cmp $1, %eax
  jne .LIF12
  call printIf
  movl  %eax, -232(%ebp)
 jmp .LEIF13
.LIF12:
  call printElse
  movl  %eax, -236(%ebp)
.LEIF13:
  movl -8(%ebp), %eax
  and $1, %eax
  movl  %eax, -240(%ebp)
  movl -240(%ebp), %eax
  cmp $1, %eax
  jne .LIF14
  call printIf
  movl  %eax, -244(%ebp)
 jmp .LEIF15
.LIF14:
  call printElse
  movl  %eax, -248(%ebp)
.LEIF15:
  movl -8(%ebp), %eax
  and -8(%ebp), %eax
  movl  %eax, -252(%ebp)
  movl -252(%ebp), %eax
  cmp $1, %eax
  jne .LIF16
  call printIf
  movl  %eax, -256(%ebp)
 jmp .LEIF17
.LIF16:
  call printElse
  movl  %eax, -260(%ebp)
.LEIF17:
  leave
  ret

main:
  pushl %ebp
  movl %esp, %ebp
  subl $4,%esp
  call assert
  movl  %eax, -4(%ebp)
  leave
  ret

