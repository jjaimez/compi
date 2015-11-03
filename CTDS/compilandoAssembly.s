.globl  main
.type main, @function
.comm global, 4, 4
inc:
  movl 4(%ebp), %eax
  addl $1, %eax
  movl  %eax, -4(%ebp)
  movl -4(%ebp), %eax
  leave
  ret

printInt:
  leave
  ret

main:
      movl -12(%ebp), %eax
      movl %eax, -4(%ebp)
  leave
  ret

