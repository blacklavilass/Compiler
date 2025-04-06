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
    public void initialize(Scope scope) {
    }

    @Override
    public Type getType() {
        return VariableType.getType(value);
    }
}