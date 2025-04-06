package nodes;

public enum Type {
    INTEGER("integer"),
    REAL("real"),
    STRING("string"),
    CHAR("char"),
    BOOLEAN("boolean"),
    UNDEFINED("undefined");

    private final String name;

    Type(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Type fromString(String name) {
        for (Type op : Type.values()) {
            if (op.name.equals(name.toLowerCase())) {
                return op;
            }
        }
        throw new IllegalArgumentException(name);
    }
}
