.globl  main
.type main, @function
assert:
  pushl %ebp
  movl %esp, %ebp
  subl $36,%esp
      movl .LF0, %eax
      movl %eax, -4(%ebp)
      flds .LF1
      flds .LF2
      fdivp %st, %st(1)
      fstps -8(%ebp)
  movl -8(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl .LF3
  pushl -4(%ebp)
  call assertEqualsFloat
  movl  %eax, -12(%ebp)
      movl .LF4, %eax
      movl %eax, -4(%ebp)
      flds -4(%ebp)
      flds .LF5
      fdivp %st, %st(1)
      fstps -16(%ebp)
  movl -16(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl .LF6
  pushl -4(%ebp)
  call assertEqualsFloat
  movl  %eax, -20(%ebp)
      movl .LF7, %eax
      movl %eax, -4(%ebp)
      flds -4(%ebp)
      flds .LF8
      fdivp %st, %st(1)
      fstps -24(%ebp)
  movl -24(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl .LF9
  pushl -4(%ebp)
  call assertEqualsFloat
  movl  %eax, -28(%ebp)
      movl .LF10, %eax
      movl %eax, -4(%ebp)
      flds -4(%ebp)
      flds -4(%ebp)
      fdivp %st, %st(1)
      fstps -32(%ebp)
  movl -32(%ebp), %eax
  movl %eax, -4(%ebp)
  pushl .LF11
  pushl -4(%ebp)
  call assertEqualsFloat
  movl  %eax, -36(%ebp)
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

.LF0:
      .float 4.4
.LF1:
      .float 2.5
.LF2:
      .float 3.2
.LF3:
      .float 1.28
.LF4:
      .float 2.5
.LF5:
      .float 5.0
.LF6:
      .float 0.5
.LF7:
      .float 2.5
.LF8:
      .float 4.9
.LF9:
      .float 1.96
.LF10:
      .float 5.5
.LF11:
      .float 1.0
