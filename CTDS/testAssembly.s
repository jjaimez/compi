.globl  main
.type main, @function
assert:
  pushl %ebp
  movl %esp, %ebp
  subl $140,%esp
  movl $4,-4(%ebp)
  pushl -4(%ebp)
  pushl $4
  call assertEquals
  movl  %eax, -8(%ebp)
  movl $4, %eax
  addl $5, %eax
  movl  %eax, -12(%ebp)
  movl -12(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $9
  call assertEquals
  movl  %eax, -16(%ebp)
  movl $0,-4(%ebp)
  movl -4(%ebp), %eax
  addl $4, %eax
  movl  %eax, -20(%ebp)
  movl -20(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $4
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
  movl -4(%ebp), %edx
  addl %edx, %eax
  movl  %eax, -36(%ebp)
  movl -36(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $0
  call assertEquals
  movl  %eax, -40(%ebp)
  movl $5, %eax
  subl $4, %eax
  movl  %eax, -44(%ebp)
  movl -44(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $1
  call assertEquals
  movl  %eax, -48(%ebp)
  movl $0,-4(%ebp)
  movl -4(%ebp), %eax
  subl $4, %eax
  movl  %eax, -52(%ebp)
  movl -52(%ebp), %eax
  movl %eax, -4(%ebp)
  movl $4, %eax
  not  %eax
  movl  %eax, -56(%ebp)
  pushl -4(%ebp)
  pushl -56(%ebp)
  call assertEquals
  movl  %eax, -60(%ebp)
  movl $2,-4(%ebp)
  movl $4, %eax
  subl -4(%ebp), %eax
  movl  %eax, -64(%ebp)
  movl -64(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $2
  call assertEquals
  movl  %eax, -68(%ebp)
  movl $5,-4(%ebp)
  movl -4(%ebp), %eax
  movl -4(%ebp), %edx
  subl %edx, %eax
  movl  %eax, -72(%ebp)
  movl -72(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $0
  call assertEquals
  movl  %eax, -76(%ebp)
  movl $3, %eax
  imull $2, %eax
  movl  %eax, -80(%ebp)
  movl -80(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $6
  call assertEquals
  movl  %eax, -84(%ebp)
  movl $2,-4(%ebp)
  movl -4(%ebp), %eax
  imull $4, %eax
  movl  %eax, -88(%ebp)
  movl -88(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $8
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
  movl $5,-4(%ebp)
  movl -4(%ebp), %eax
  movl -4(%ebp), %edx
  imull %edx, %eax
  movl  %eax, -104(%ebp)
  movl -104(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $25
  call assertEquals
  movl  %eax, -108(%ebp)
  movl $0, %edx
  movl $6, %eax
  movl $2, %ecx
  cltd
  idivl %ecx
  movl  %eax, -112(%ebp)
  movl -112(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $3
  call assertEquals
  movl  %eax, -116(%ebp)
  movl $8,-4(%ebp)
  movl $0, %edx
  movl -4(%ebp), %eax
  movl $4, %ecx
  cltd
  idivl %ecx
  movl  %eax, -120(%ebp)
  movl -120(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $2
  call assertEquals
  movl  %eax, -124(%ebp)
  movl $2,-4(%ebp)
  movl $0, %edx
  movl $4, %eax
  movl -4(%ebp), %ecx
  cltd
  idivl %ecx
  movl  %eax, -128(%ebp)
  movl -128(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $2
  call assertEquals
  movl  %eax, -132(%ebp)
  movl $5,-4(%ebp)
  movl $0, %edx
  movl -4(%ebp), %eax
  movl -4(%ebp), %ecx
  cltd
  idivl %ecx
  movl  %eax, -136(%ebp)
  movl -136(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl -4(%ebp)
  pushl $1
  call assertEquals
  movl  %eax, -140(%ebp)
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

