.globl  main
.type main, @function
assert:
  pushl %ebp
  movl %esp, %ebp
  subl $184,%esp
  movl $3,-4(%ebp)
  movl $1,-8(%ebp)
  movl $2, %eax
  cmp $2, %eax
  je  .true0
  movl $0, %eax
  jmp  .endtrue0
.true0:
  movl $1, %eax
.endtrue0:
  movl  %eax, -20(%ebp)
  movl -20(%ebp), %eax
  cmp $1, %eax
  jne .LIF2
  call printIf
  movl  %eax, -24(%ebp)
 jmp .LEIF3
.LIF2:
.LEIF3:
  movl $1, %eax
  cmp -8(%ebp), %eax
  je  .true1
  movl $0, %eax
  jmp  .endtrue1
.true1:
  movl $1, %eax
.endtrue1:
  movl  %eax, -28(%ebp)
  movl -28(%ebp), %eax
  cmp $1, %eax
  jne .LIF4
  call printIf
  movl  %eax, -32(%ebp)
 jmp .LEIF5
.LIF4:
.LEIF5:
  movl -4(%ebp), %eax
  cmp $3, %eax
  je  .true2
  movl $0, %eax
  jmp  .endtrue2
.true2:
  movl $1, %eax
.endtrue2:
  movl  %eax, -36(%ebp)
  movl -36(%ebp), %eax
  cmp $1, %eax
  jne .LIF6
  call printIf
  movl  %eax, -40(%ebp)
 jmp .LEIF7
.LIF6:
.LEIF7:
  movl -4(%ebp), %eax
  cmp -4(%ebp), %eax
  je  .true3
  movl $0, %eax
  jmp  .endtrue3
.true3:
  movl $1, %eax
.endtrue3:
  movl  %eax, -44(%ebp)
  movl -44(%ebp), %eax
  cmp $1, %eax
  jne .LIF8
  call printIf
  movl  %eax, -48(%ebp)
 jmp .LEIF9
.LIF8:
.LEIF9:
  movl $1, %eax
  cmp $2, %eax
  je  .true4
  movl $0, %eax
  jmp  .endtrue4
.true4:
  movl $1, %eax
.endtrue4:
  movl  %eax, -52(%ebp)
  movl -52(%ebp), %eax
  cmp $1, %eax
  jne .LIF10
  call printIf
  movl  %eax, -56(%ebp)
 jmp .LEIF11
.LIF10:
  call printElse
  movl  %eax, -60(%ebp)
.LEIF11:
  movl $3, %eax
  cmp -8(%ebp), %eax
  je  .true5
  movl $0, %eax
  jmp  .endtrue5
.true5:
  movl $1, %eax
.endtrue5:
  movl  %eax, -64(%ebp)
  movl -64(%ebp), %eax
  cmp $1, %eax
  jne .LIF12
  call printIf
  movl  %eax, -68(%ebp)
 jmp .LEIF13
.LIF12:
  call printElse
  movl  %eax, -72(%ebp)
.LEIF13:
  movl -8(%ebp), %eax
  cmp $2, %eax
  je  .true6
  movl $0, %eax
  jmp  .endtrue6
.true6:
  movl $1, %eax
.endtrue6:
  movl  %eax, -76(%ebp)
  movl -76(%ebp), %eax
  cmp $1, %eax
  jne .LIF14
  call printIf
  movl  %eax, -80(%ebp)
 jmp .LEIF15
.LIF14:
  call printElse
  movl  %eax, -84(%ebp)
.LEIF15:
  movl -8(%ebp), %eax
  cmp -4(%ebp), %eax
  je  .true7
  movl $0, %eax
  jmp  .endtrue7
.true7:
  movl $1, %eax
.endtrue7:
  movl  %eax, -88(%ebp)
  movl -88(%ebp), %eax
  cmp $1, %eax
  jne .LIF16
  call printIf
  movl  %eax, -92(%ebp)
 jmp .LEIF17
.LIF16:
  call printElse
  movl  %eax, -96(%ebp)
.LEIF17:
  call printSeparador
  movl  %eax, -100(%ebp)
      movl .LF0, %eax
      movl %eax, -12(%ebp)
      movl .LF1, %eax
      movl %eax, -16(%ebp)
      flds .LF2
      flds .LF3
      fucompp
      fnstsw %ax
  je  .true8
  movl $0, %eax
  jmp  .endtrue8
.true8:
  movl $1, %eax
.endtrue8:
  movl  %eax, -104(%ebp)
  movl -104(%ebp), %eax
  cmp $1, %eax
  jne .LIF18
  call printIf
  movl  %eax, -108(%ebp)
 jmp .LEIF19
.LIF18:
.LEIF19:
      flds -16(%ebp)
      flds .LF4
      fucompp
      fnstsw %ax
  je  .true9
  movl $0, %eax
  jmp  .endtrue9
.true9:
  movl $1, %eax
.endtrue9:
  movl  %eax, -112(%ebp)
  movl -112(%ebp), %eax
  cmp $1, %eax
  jne .LIF20
  call printIf
  movl  %eax, -116(%ebp)
 jmp .LEIF21
.LIF20:
.LEIF21:
      flds -12(%ebp)
      flds .LF5
      fucompp
      fnstsw %ax
  je  .true10
  movl $0, %eax
  jmp  .endtrue10
.true10:
  movl $1, %eax
.endtrue10:
  movl  %eax, -120(%ebp)
  movl -120(%ebp), %eax
  cmp $1, %eax
  jne .LIF22
  call printIf
  movl  %eax, -124(%ebp)
 jmp .LEIF23
.LIF22:
.LEIF23:
      flds -12(%ebp)
      flds -12(%ebp)
      fucompp
      fnstsw %ax
  je  .true11
  movl $0, %eax
  jmp  .endtrue11
.true11:
  movl $1, %eax
.endtrue11:
  movl  %eax, -128(%ebp)
  movl -128(%ebp), %eax
  cmp $1, %eax
  jne .LIF24
  call printIf
  movl  %eax, -132(%ebp)
 jmp .LEIF25
.LIF24:
  call printIf
  movl  %eax, -136(%ebp)
.LEIF25:
      flds .LF6
      flds .LF7
      fucompp
      fnstsw %ax
  je  .true12
  movl $0, %eax
  jmp  .endtrue12
.true12:
  movl $1, %eax
.endtrue12:
  movl  %eax, -140(%ebp)
  movl -140(%ebp), %eax
  cmp $1, %eax
  jne .LIF26
  call printIf
  movl  %eax, -144(%ebp)
 jmp .LEIF27
.LIF26:
  call printElse
  movl  %eax, -148(%ebp)
.LEIF27:
      flds -16(%ebp)
      flds .LF8
      fucompp
      fnstsw %ax
  je  .true13
  movl $0, %eax
  jmp  .endtrue13
.true13:
  movl $1, %eax
.endtrue13:
  movl  %eax, -152(%ebp)
  movl -152(%ebp), %eax
  cmp $1, %eax
  jne .LIF28
  call printIf
  movl  %eax, -156(%ebp)
 jmp .LEIF29
.LIF28:
  call printElse
  movl  %eax, -160(%ebp)
.LEIF29:
      flds -16(%ebp)
      flds .LF9
      fucompp
      fnstsw %ax
  je  .true14
  movl $0, %eax
  jmp  .endtrue14
.true14:
  movl $1, %eax
.endtrue14:
  movl  %eax, -164(%ebp)
  movl -164(%ebp), %eax
  cmp $1, %eax
  jne .LIF30
  call printIf
  movl  %eax, -168(%ebp)
 jmp .LEIF31
.LIF30:
  call printElse
  movl  %eax, -172(%ebp)
.LEIF31:
      flds -16(%ebp)
      flds -12(%ebp)
      fucompp
      fnstsw %ax
  je  .true15
  movl $0, %eax
  jmp  .endtrue15
.true15:
  movl $1, %eax
.endtrue15:
  movl  %eax, -176(%ebp)
  movl -176(%ebp), %eax
  cmp $1, %eax
  jne .LIF32
  call printIf
  movl  %eax, -180(%ebp)
 jmp .LEIF33
.LIF32:
  call printElse
  movl  %eax, -184(%ebp)
.LEIF33:
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
      .float 3.3
.LF1:
      .float 1.5
.LF2:
      .float 2.9
.LF3:
      .float 2.9
.LF4:
      .float 1.5
.LF5:
      .float 3.3
.LF6:
      .float 1.5
.LF7:
      .float 2.9
.LF8:
      .float 3.3
.LF9:
      .float 2.9
