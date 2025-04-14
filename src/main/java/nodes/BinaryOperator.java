package nodes;

import exception.SemanticException;
import java.util.Iterator;
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

    BinaryOperator(String name) {
        this.name = name;
        supportable = new TreeMap<>();
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

        ADD.supportable.put(new TypePair(Type.INTEGER, Type.INTEGER), Type.INTEGER);
        ADD.supportable.put(new TypePair(Type.REAL, Type.REAL), Type.REAL);
        ADD.supportable.put(new TypePair(Type.STRING, Type.STRING), Type.STRING);
        ADD.supportable.put(new TypePair(Type.CHAR, Type.CHAR), Type.STRING);

        SUBTRACT.supportable.put(new TypePair(Type.INTEGER, Type.INTEGER), Type.INTEGER);
        SUBTRACT.supportable.put(new TypePair(Type.REAL, Type.REAL), Type.REAL);

        DIVIDE.supportable.put(new TypePair(Type.REAL, Type.REAL), Type.REAL);

        DIV.supportable.put(new TypePair(Type.INTEGER, Type.INTEGER), Type.INTEGER);

        MOD.supportable.put(new TypePair(Type.INTEGER, Type.INTEGER), Type.INTEGER);

        AND.supportable.put(new TypePair(Type.INTEGER, Type.INTEGER), Type.INTEGER);
        AND.supportable.put(new TypePair(Type.BOOLEAN, Type.BOOLEAN), Type.BOOLEAN);

        OR.supportable.put(new TypePair(Type.INTEGER, Type.INTEGER), Type.INTEGER);
        OR.supportable.put(new TypePair(Type.BOOLEAN, Type.BOOLEAN), Type.BOOLEAN);

        GREATER_EQUAL.supportable.put(new TypePair(Type.INTEGER, Type.INTEGER), Type.BOOLEAN);
        GREATER_EQUAL.supportable.put(new TypePair(Type.REAL, Type.REAL), Type.BOOLEAN);
        GREATER_EQUAL.supportable.put(new TypePair(Type.BOOLEAN, Type.BOOLEAN), Type.BOOLEAN);
        GREATER_EQUAL.supportable.put(new TypePair(Type.STRING, Type.STRING), Type.BOOLEAN); // хочется выпилить
        GREATER_EQUAL.supportable.put(new TypePair(Type.CHAR, Type.CHAR), Type.BOOLEAN);

        GREATER_THAN.supportable.put(new TypePair(Type.INTEGER, Type.INTEGER), Type.BOOLEAN);
        GREATER_THAN.supportable.put(new TypePair(Type.REAL, Type.REAL), Type.BOOLEAN);
        GREATER_THAN.supportable.put(new TypePair(Type.BOOLEAN, Type.BOOLEAN), Type.BOOLEAN);
        GREATER_THAN.supportable.put(new TypePair(Type.STRING, Type.STRING), Type.BOOLEAN);
        GREATER_THAN.supportable.put(new TypePair(Type.CHAR, Type.CHAR), Type.BOOLEAN);

        LESS_EQUAL.supportable.put(new TypePair(Type.INTEGER, Type.INTEGER), Type.BOOLEAN);
        LESS_EQUAL.supportable.put(new TypePair(Type.REAL, Type.REAL), Type.BOOLEAN);
        LESS_EQUAL.supportable.put(new TypePair(Type.BOOLEAN, Type.BOOLEAN), Type.BOOLEAN);
        LESS_EQUAL.supportable.put(new TypePair(Type.STRING, Type.STRING), Type.BOOLEAN);
        LESS_EQUAL.supportable.put(new TypePair(Type.CHAR, Type.CHAR), Type.BOOLEAN);

        LESS_THAN.supportable.put(new TypePair(Type.INTEGER, Type.INTEGER), Type.BOOLEAN);
        LESS_THAN.supportable.put(new TypePair(Type.REAL, Type.REAL), Type.BOOLEAN);
        LESS_THAN.supportable.put(new TypePair(Type.BOOLEAN, Type.BOOLEAN), Type.BOOLEAN);
        LESS_THAN.supportable.put(new TypePair(Type.STRING, Type.STRING), Type.BOOLEAN);
        LESS_THAN.supportable.put(new TypePair(Type.CHAR, Type.CHAR), Type.BOOLEAN);

        EQUAL.supportable.put(new TypePair(Type.INTEGER, Type.INTEGER), Type.BOOLEAN);
        EQUAL.supportable.put(new TypePair(Type.REAL, Type.REAL), Type.BOOLEAN);
        EQUAL.supportable.put(new TypePair(Type.BOOLEAN, Type.BOOLEAN), Type.BOOLEAN);
        EQUAL.supportable.put(new TypePair(Type.STRING, Type.STRING), Type.BOOLEAN);
        EQUAL.supportable.put(new TypePair(Type.CHAR, Type.CHAR), Type.BOOLEAN);

        NOT_EQUAL.supportable.put(new TypePair(Type.INTEGER, Type.INTEGER), Type.BOOLEAN);
        NOT_EQUAL.supportable.put(new TypePair(Type.REAL, Type.REAL), Type.BOOLEAN);
        NOT_EQUAL.supportable.put(new TypePair(Type.BOOLEAN, Type.BOOLEAN), Type.BOOLEAN);
        NOT_EQUAL.supportable.put(new TypePair(Type.STRING, Type.STRING), Type.BOOLEAN);
        NOT_EQUAL.supportable.put(new TypePair(Type.CHAR, Type.CHAR), Type.BOOLEAN);
    }
}
