package nodes;

import exception.SemanticException;

import java.util.Map;
import java.util.TreeMap;

public enum BinaryOperator {
    MULTIPLY("*"),
    ADD("+"),
    SUBTRACT("-"),
    DIVIDE("/"),
    DIV("div"),
    MOD("mod"),
    AND("and"),
    OR("or"),
    GREATER_EQUAL(">="),
    GREATER_THAN(">"),
    LESS_EQUAL("<="),
    LESS_THAN("<"),
    EQUAL("="),
    NOT_EQUAL("<>");

    private final String name;
    private final Map<TypePair, Type> supportable;
    private final Map<TypePair, StringBuilder> JVMOperations;
    private static int currentID = -1;

    private static StringBuilder genComparisonPrefix() {
        currentID++;
        return new StringBuilder(
                " ELSE" + currentID + "\n" +
                "iconst 0\n" +
                "goto FINISH" + currentID + "\n" +
                "ELSE" + currentID + ":\n" +
                "iconst 1\n" +
                "FINISH" + currentID + ":\n");
    }

    BinaryOperator(String name) {
        this.name = name;
        supportable = new TreeMap<>();
        JVMOperations = new TreeMap<>();
    }

    public Type getReturnType(Type first, Type second) {
        TypePair pair = new TypePair(first, second);
        if (supportable.containsKey(pair)) {
            return supportable.get(pair);
        }
        throw new SemanticException("Operation " + name + " is not supported with " + first + " and " + second);
    }

    public Iterable<TypePair> supportableTypes() {
        return supportable.keySet();
    }

    @Override
    public String toString() {
        return name;
    }

    public static BinaryOperator fromString(String name) {
        for (BinaryOperator op : BinaryOperator.values()) {
            if (op.name.equals(name)) {
                return op;
            }
        }
        throw new IllegalArgumentException();
    }

    public static class TypePair implements Comparable<TypePair> {
        Type typeLeft, typeRight;

        public TypePair(Type typeLeft, Type typeRight) {
            this.typeLeft = typeLeft;
            this.typeRight = typeRight;
        }

        @Override
        public int compareTo(TypePair o) {
            int left = typeLeft.compareTo(o.typeLeft);
            if (left != 0) return left;
            return typeRight.compareTo(o.typeRight);
        }
    }

    static {
        MULTIPLY.supportable.put(new TypePair(Type.INTEGER, Type.INTEGER), Type.INTEGER);
        MULTIPLY.supportable.put(new TypePair(Type.REAL, Type.REAL), Type.REAL);

        MULTIPLY.JVMOperations.put(new TypePair(Type.INTEGER, Type.INTEGER), new StringBuilder("imul\n"));
        MULTIPLY.JVMOperations.put(new TypePair(Type.REAL, Type.REAL), new StringBuilder("fmul\n"));

        ADD.supportable.put(new TypePair(Type.INTEGER, Type.INTEGER), Type.INTEGER);
        ADD.supportable.put(new TypePair(Type.REAL, Type.REAL), Type.REAL);
        ADD.supportable.put(new TypePair(Type.STRING, Type.STRING), Type.STRING);
        ADD.supportable.put(new TypePair(Type.CHAR, Type.CHAR), Type.STRING);

        ADD.JVMOperations.put(new TypePair(Type.INTEGER, Type.INTEGER), new StringBuilder("iadd\n"));
        ADD.JVMOperations.put(new TypePair(Type.REAL, Type.REAL), new StringBuilder("fadd\n"));
        ADD.JVMOperations.put(new TypePair(Type.STRING, Type.STRING), new StringBuilder(
                "swap\n" +
                "new java/lang/StringBuilder\n" +
                "dup\n" +
                "invokespecial java/lang/StringBuilder/<init>()V\n" +
                "swap\n" +
                "invokevirtual java/lang/StringBuilder/append(Ljava/lang/String;)Ljava/lang/StringBuilder;\n" +
                "swap\n" +
                "invokevirtual java/lang/StringBuilder/append(Ljava/lang/String;)Ljava/lang/StringBuilder;\n" +
                "invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;\n"
        ));
        ADD.JVMOperations.put(new TypePair(Type.CHAR, Type.CHAR), new StringBuilder(
                "swap\n" +
                "new java/lang/StringBuilder\n" +
                "dup\n" +
                "invokespecial java/lang/StringBuilder/<init>()V\n" +
                "swap\n" +
                "invokevirtual java/lang/StringBuilder/append(Ljava/lang/String;)Ljava/lang/StringBuilder;\n" +
                "swap\n" +
                "invokevirtual java/lang/StringBuilder/append(Ljava/lang/String;)Ljava/lang/StringBuilder;\n" +
                "invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;\n"
        ));

        SUBTRACT.supportable.put(new TypePair(Type.INTEGER, Type.INTEGER), Type.INTEGER);
        SUBTRACT.supportable.put(new TypePair(Type.REAL, Type.REAL), Type.REAL);

        SUBTRACT.JVMOperations.put(new TypePair(Type.INTEGER, Type.INTEGER), new StringBuilder("isub\n"));
        SUBTRACT.JVMOperations.put(new TypePair(Type.REAL, Type.REAL), new StringBuilder("fsub\n"));

        DIVIDE.supportable.put(new TypePair(Type.REAL, Type.REAL), Type.REAL);
        DIVIDE.JVMOperations.put(new TypePair(Type.REAL, Type.REAL), new StringBuilder("fdiv\n"));

        DIV.supportable.put(new TypePair(Type.INTEGER, Type.INTEGER), Type.INTEGER);
        DIV.JVMOperations.put(new TypePair(Type.INTEGER, Type.INTEGER), new StringBuilder("idiv\n"));

        MOD.supportable.put(new TypePair(Type.INTEGER, Type.INTEGER), Type.INTEGER);
        MOD.JVMOperations.put(new TypePair(Type.INTEGER, Type.INTEGER), new StringBuilder("irem\n"));

        AND.supportable.put(new TypePair(Type.INTEGER, Type.INTEGER), Type.INTEGER);
        AND.supportable.put(new TypePair(Type.BOOLEAN, Type.BOOLEAN), Type.BOOLEAN);

        AND.JVMOperations.put(new TypePair(Type.INTEGER, Type.INTEGER), new StringBuilder("iand\n"));
        AND.JVMOperations.put(new TypePair(Type.BOOLEAN, Type.BOOLEAN), new StringBuilder("iand\n"));

        OR.supportable.put(new TypePair(Type.INTEGER, Type.INTEGER), Type.INTEGER);
        OR.supportable.put(new TypePair(Type.BOOLEAN, Type.BOOLEAN), Type.BOOLEAN);

        OR.JVMOperations.put(new TypePair(Type.INTEGER, Type.INTEGER), new StringBuilder("ior\n"));
        OR.JVMOperations.put(new TypePair(Type.BOOLEAN, Type.BOOLEAN), new StringBuilder("ior\n"));

        GREATER_EQUAL.supportable.put(new TypePair(Type.INTEGER, Type.INTEGER), Type.BOOLEAN);
        GREATER_EQUAL.supportable.put(new TypePair(Type.REAL, Type.REAL), Type.BOOLEAN);
        GREATER_EQUAL.supportable.put(new TypePair(Type.BOOLEAN, Type.BOOLEAN), Type.BOOLEAN);
        GREATER_EQUAL.supportable.put(new TypePair(Type.STRING, Type.STRING), Type.BOOLEAN);
        GREATER_EQUAL.supportable.put(new TypePair(Type.CHAR, Type.CHAR), Type.BOOLEAN);

        StringBuilder GEInt = new StringBuilder("if_icmpge").append(genComparisonPrefix());
        GREATER_EQUAL.JVMOperations.put(new TypePair(Type.INTEGER, Type.INTEGER), GEInt);
        GREATER_EQUAL.JVMOperations.put(new TypePair(Type.REAL, Type.REAL), new StringBuilder("fcmpg\n").append("ifge\n").append(genComparisonPrefix()));
        GREATER_EQUAL.JVMOperations.put(new TypePair(Type.BOOLEAN, Type.BOOLEAN), GEInt);
        GREATER_EQUAL.JVMOperations.put(new TypePair(Type.STRING, Type.STRING), new StringBuilder(
                "invokevirtual java/lang/String/compareTo(Ljava/lang/String;)I\n").append("ifge\n").append(genComparisonPrefix()));
        GREATER_EQUAL.JVMOperations.put(new TypePair(Type.CHAR, Type.CHAR), GEInt);

        GREATER_THAN.supportable.put(new TypePair(Type.INTEGER, Type.INTEGER), Type.BOOLEAN);
        GREATER_THAN.supportable.put(new TypePair(Type.REAL, Type.REAL), Type.BOOLEAN);
        GREATER_THAN.supportable.put(new TypePair(Type.BOOLEAN, Type.BOOLEAN), Type.BOOLEAN);
        GREATER_THAN.supportable.put(new TypePair(Type.STRING, Type.STRING), Type.BOOLEAN);
        GREATER_THAN.supportable.put(new TypePair(Type.CHAR, Type.CHAR), Type.BOOLEAN);

        StringBuilder GTInt = new StringBuilder("if_icmpgt").append(genComparisonPrefix());
        GREATER_THAN.JVMOperations.put(new TypePair(Type.INTEGER, Type.INTEGER), GTInt);
        GREATER_THAN.JVMOperations.put(new TypePair(Type.REAL, Type.REAL), new StringBuilder("fcmpl\n").append("ifgt\n").append(genComparisonPrefix()));
        GREATER_THAN.JVMOperations.put(new TypePair(Type.BOOLEAN, Type.BOOLEAN), GTInt);
        GREATER_THAN.JVMOperations.put(new TypePair(Type.STRING, Type.STRING), new StringBuilder(
                "invokevirtual java/lang/String/compareTo(Ljava/lang/String;)I\n").append("ifgt\n").append(genComparisonPrefix()));
        GREATER_THAN.JVMOperations.put(new TypePair(Type.CHAR, Type.CHAR), GTInt);

        LESS_EQUAL.supportable.put(new TypePair(Type.INTEGER, Type.INTEGER), Type.BOOLEAN);
        LESS_EQUAL.supportable.put(new TypePair(Type.REAL, Type.REAL), Type.BOOLEAN);
        LESS_EQUAL.supportable.put(new TypePair(Type.BOOLEAN, Type.BOOLEAN), Type.BOOLEAN);
        LESS_EQUAL.supportable.put(new TypePair(Type.STRING, Type.STRING), Type.BOOLEAN);
        LESS_EQUAL.supportable.put(new TypePair(Type.CHAR, Type.CHAR), Type.BOOLEAN);

        StringBuilder LEInt = new StringBuilder("if_icmple").append(genComparisonPrefix());
        LESS_EQUAL.JVMOperations.put(new TypePair(Type.INTEGER, Type.INTEGER), LEInt);
        LESS_EQUAL.JVMOperations.put(new TypePair(Type.REAL, Type.REAL), new StringBuilder("fcmpl\n").append("ifle\n").append(genComparisonPrefix()));
        LESS_EQUAL.JVMOperations.put(new TypePair(Type.BOOLEAN, Type.BOOLEAN), LEInt);
        LESS_EQUAL.JVMOperations.put(new TypePair(Type.STRING, Type.STRING), new StringBuilder(
                "invokevirtual java/lang/String/compareTo(Ljava/lang/String;)I\n").append("ifle\n").append(genComparisonPrefix()));
        LESS_EQUAL.JVMOperations.put(new TypePair(Type.CHAR, Type.CHAR), LEInt);

        LESS_THAN.supportable.put(new TypePair(Type.INTEGER, Type.INTEGER), Type.BOOLEAN);
        LESS_THAN.supportable.put(new TypePair(Type.REAL, Type.REAL), Type.BOOLEAN);
        LESS_THAN.supportable.put(new TypePair(Type.BOOLEAN, Type.BOOLEAN), Type.BOOLEAN);
        LESS_THAN.supportable.put(new TypePair(Type.STRING, Type.STRING), Type.BOOLEAN);
        LESS_THAN.supportable.put(new TypePair(Type.CHAR, Type.CHAR), Type.BOOLEAN);

        StringBuilder LTInt = new StringBuilder("if_icmplt").append(genComparisonPrefix());
        LESS_THAN.JVMOperations.put(new TypePair(Type.INTEGER, Type.INTEGER), LTInt);
        LESS_THAN.JVMOperations.put(new TypePair(Type.REAL, Type.REAL), new StringBuilder("fcmpl\n").append("iflt\n"));
        LESS_THAN.JVMOperations.put(new TypePair(Type.BOOLEAN, Type.BOOLEAN), LTInt);
        LESS_THAN.JVMOperations.put(new TypePair(Type.STRING, Type.STRING), new StringBuilder(
                "invokevirtual java/lang/String/compareTo(Ljava/lang/String;)I\n").append("iflt\n").append(genComparisonPrefix()));
        LESS_THAN.JVMOperations.put(new TypePair(Type.CHAR, Type.CHAR), LTInt);

        EQUAL.supportable.put(new TypePair(Type.INTEGER, Type.INTEGER), Type.BOOLEAN);
        EQUAL.supportable.put(new TypePair(Type.REAL, Type.REAL), Type.BOOLEAN);
        EQUAL.supportable.put(new TypePair(Type.BOOLEAN, Type.BOOLEAN), Type.BOOLEAN);
        EQUAL.supportable.put(new TypePair(Type.STRING, Type.STRING), Type.BOOLEAN);
        EQUAL.supportable.put(new TypePair(Type.CHAR, Type.CHAR), Type.BOOLEAN);

        StringBuilder EQInt = new StringBuilder("if_icmpeq").append(genComparisonPrefix());
        EQUAL.JVMOperations.put(new TypePair(Type.INTEGER, Type.INTEGER), EQInt);
        EQUAL.JVMOperations.put(new TypePair(Type.REAL, Type.REAL), new StringBuilder("fcmpl\n").append("ifeq\n").append(genComparisonPrefix()));
        EQUAL.JVMOperations.put(new TypePair(Type.BOOLEAN, Type.BOOLEAN), EQInt);
        EQUAL.JVMOperations.put(new TypePair(Type.STRING, Type.STRING), new StringBuilder(
                "invokevirtual java/lang/String/compareTo(Ljava/lang/String;)I\n").append("ifeq\n").append(genComparisonPrefix()));
        EQUAL.JVMOperations.put(new TypePair(Type.CHAR, Type.CHAR), EQInt);

        NOT_EQUAL.supportable.put(new TypePair(Type.INTEGER, Type.INTEGER), Type.BOOLEAN);
        NOT_EQUAL.supportable.put(new TypePair(Type.REAL, Type.REAL), Type.BOOLEAN);
        NOT_EQUAL.supportable.put(new TypePair(Type.BOOLEAN, Type.BOOLEAN), Type.BOOLEAN);
        NOT_EQUAL.supportable.put(new TypePair(Type.STRING, Type.STRING), Type.BOOLEAN);
        NOT_EQUAL.supportable.put(new TypePair(Type.CHAR, Type.CHAR), Type.BOOLEAN);

        StringBuilder NEInt = new StringBuilder("if_icmpne").append(genComparisonPrefix());
        NOT_EQUAL.JVMOperations.put(new TypePair(Type.INTEGER, Type.INTEGER), NEInt);
        NOT_EQUAL.JVMOperations.put(new TypePair(Type.REAL, Type.REAL), new StringBuilder("fcmpl\n").append("ifne\n"));
        NOT_EQUAL.JVMOperations.put(new TypePair(Type.BOOLEAN, Type.BOOLEAN), NEInt);
        NOT_EQUAL.JVMOperations.put(new TypePair(Type.STRING, Type.STRING), new StringBuilder(
                "invokevirtual java/lang/String/compareTo(Ljava/lang/String;)I\n").append("ifne\n").append(genComparisonPrefix()));
        NOT_EQUAL.JVMOperations.put(new TypePair(Type.CHAR, Type.CHAR), NEInt);
    }
}