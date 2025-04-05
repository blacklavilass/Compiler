package nodes;

import semantic.Scope;
import semantic.VariableType;

import java.util.List;

public class LiteralNode extends BasicNode implements ExprNode {
    public final String value;

    public LiteralNode(String value) {
        this.value = value;
    }

    @Override
    public List<? extends Node> children() {
        return List.of();
    }

    @Override
    public List<String> tree() {
        return List.of();
    }

    @Override
    public void initialize(Scope scope) {
    }

    @Override
    public Type getType() {
        return VariableType.getType(value);
    }
}
