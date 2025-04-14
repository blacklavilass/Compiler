package semantic;

import nodes.Type;

public class VariableType {
    public static Type getType(String literal) {
        if (literal == null || literal.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }

        if (literal.length() == 3 && literal.startsWith("'") && literal.endsWith("'")) {
            return Type.CHAR;
        }

        if (literal.length() > 3 && literal.startsWith("'") && literal.endsWith("'")) {
            return Type.STRING;
        }

        if (literal.equalsIgnoreCase("true") || literal.equalsIgnoreCase("false")) {
            return Type.BOOLEAN;
        }

        try {
            Integer.parseInt(literal);
            return Type.INTEGER;
        } catch (NumberFormatException e) {
        }

        try {
            Double.parseDouble(literal);
            return Type.REAL;
        } catch (NumberFormatException e) {
        }

        throw new IllegalArgumentException("Cannot resolve type for : " + literal);
    }
}
