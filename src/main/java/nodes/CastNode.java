package nodes;

import semantic.Scope;

import java.util.List;

public class CastNode extends BasicNode implements ExprNode{
    Type toType;
    ExprNode expr;

    public CastNode(Type toType, ExprNode expr) {
        this.toType = toType;
        this.expr = expr;
    }

    @Override
    public Type getType() {
        return toType;
    }

    @Override
    public List<? extends Node> children() {
        return List.of(expr);
    }

    @Override
    public void initialize(Scope scope) {
        this.scope = scope;
        this.expr.initialize(scope);
    }
}
