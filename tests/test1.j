.class public Fibonacci
.super java/lang/Object

; Конструктор по умолчанию
.method public <init>()V
    aload_0
    invokespecial java/lang/Object/<init>()V
    return
.end method

; Метод вычисления числа Фибоначчи (n >= 0)
; Сигнатура: fib(I)I
.method public static fib(I)I
    .limit stack 3
    .limit locals 1

    ; if (n <= 1) return n;
    iload_0         ; Загружаем n в стек
    iconst_1        ; Загружаем 1 в стек
    if_icmpgt else  ; Если n > 1, переходим на else
    iload_0         ; Иначе возвращаем n
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

; Главный метод (выводит числа от 0 до 9)
.method public static main([Ljava/lang/String;)V
    .limit stack 3
    .limit locals 3

    ; Инициализация цикла (i = 0)
    iconst_0
    istore_1        ; i = 0

loop:
    ; Проверка условия (i < 10)
    iload_1
    bipush 10
    if_icmpge exit  ; Если i >= 10, выходим

    ; Вычисляем fib(i)
    iload_1
    invokestatic Fibonacci/fib(I)I
    istore_2        ; Сохраняем результат в локальную переменную 2

    ; Выводим fib(i)
    getstatic java/lang/System/out Ljava/io/PrintStream;
    iload_2
    invokevirtual java/io/PrintStream/println(I)V

    ; i++
    iinc 1 1        ; Увеличиваем i на 1
    goto loop       ; Возвращаемся в начало цикла

exit:
    return
.end method