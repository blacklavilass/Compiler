package nodes;

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

    BinaryOperator(String name) {
        this.name = name;
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
}
