.class public Fibonacci
.super java/lang/Object

; ����������� �� ���������
.method public <init>()V
    aload_0
    invokespecial java/lang/Object/<init>()V
    return
.end method

; ����� ���������� ����� ��������� (n >= 0)
; ���������: fib(I)I
.method public static fib(I)I
    .limit stack 3
    .limit locals 1

    ; if (n <= 1) return n;
    iload_0         ; ��������� n � ����
    iconst_1        ; ��������� 1 � ����
    if_icmpgt else  ; ���� n > 1, ��������� �� else
    iload_0         ; ����� ���������� n
    ireturn

else:
    ; return fib(n-1) + fib(n-2);
    iload_0
    iconst_1
    isub            ; n-1
    invokestatic Fibonacci/fib(I)I  ; fib(n-1)
    
    iload_0
    iconst_2
    isub            ; n-2
    invokestatic Fibonacci/fib(I)I  ; fib(n-2)
    
    iadd            ; fib(n-1) + fib(n-2)
    ireturn
.end method

; ������� ����� (������� ����� �� 0 �� 9)
.method public static main([Ljava/lang/String;)V
    .limit stack 3
    .limit locals 3

    ; ������������� ����� (i = 0)
    iconst_0
    istore_1        ; i = 0

loop:
    ; �������� ������� (i < 10)
    iload_1
    bipush 10
    if_icmpge exit  ; ���� i >= 10, �������

    ; ��������� fib(i)
    iload_1
    invokestatic Fibonacci/fib(I)I
    istore_2        ; ��������� ��������� � ��������� ���������� 2

    ; ������� fib(i)
    getstatic java/lang/System/out Ljava/io/PrintStream;
    iload_2
    invokevirtual java/io/PrintStream/println(I)V

    ; i++
    iinc 1 1        ; ����������� i �� 1
    goto loop       ; ������������ � ������ �����

exit:
    return
.end method