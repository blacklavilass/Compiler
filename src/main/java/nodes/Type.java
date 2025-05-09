package nodes;

public enum Type {
    INTEGER("integer", "I", "0", "i"),
    REAL("real", "F", "0.0", "f"),
    STRING("string", "Ljava/lang/String;", "\"\"", "a"),
    CHAR("char", "C", "0", "i"),
    BOOLEAN("boolean", "Z", "0", "i"),
    UNDEFINED("undefined", "V", "", "");

    private final String name;
    private final String abbreviation;
    private final String defaultValue;
    private final String commandWordPrefix;

    Type(String name, String abbreviation, String defaultValue, String commandWordPrefix) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.defaultValue = defaultValue;
        this.commandWordPrefix = commandWordPrefix;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public static Type fromString(String name) {
        for (Type op : Type.values()) {
            if (op.name.equals(name.toLowerCase())) {
                return op;
            }
        }
        throw new IllegalArgumentException(name);
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getCommandWordPrefix() {
        return commandWordPrefix;
    }
}
