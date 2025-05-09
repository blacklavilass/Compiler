package nodes;

import semantic.Scope;
import semantic.Variable;
import semantic.VariableType;

import java.util.List;

public class LiteralNode extends BasicNode implements ExprNode {
    public final String value;

    public LiteralNode(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public List<? extends Node> children() {
        return List.of();
    }

    @Override
    public void semanticCheck() {
        VariableType.getType(value);
    }

    @Override
    public StringBuilder generateCode() {
        Type type = VariableType.getType(value);
        if (type == Type.STRING) {
            StringBuilder sb = new StringBuilder(value);
            sb.setCharAt(0, '"');
            sb.setCharAt(sb.length() - 1, '"');
            return new StringBuilder().append(type.getCommandWordPrefix()).append("ldc ").append(sb).append("\n");
        }
        if (type == Type.REAL) {
            StringBuilder sb = new StringBuilder(value);
            sb.append("f");
            return new StringBuilder().append(type.getCommandWordPrefix()).append("ldc ").append(sb).append("\n");
        }
        if (type == Type.BOOLEAN) {
            if ("true".equalsIgnoreCase(value)) {
                return new StringBuilder().append(type.getCommandWordPrefix()).append("ldc ").append(1).append("\n");
            } else {
                return new StringBuilder().append(type.getCommandWordPrefix()).append("ldc ").append(0).append("\n");
            }
        }
        return new StringBuilder().append(type.getCommandWordPrefix()).append("ldc ").append(value).append("\n");
    }

    @Override
    public void initialize(Scope scope) {
    }

    @Override
    public Type getType() {
        return VariableType.getType(value);
    }
}