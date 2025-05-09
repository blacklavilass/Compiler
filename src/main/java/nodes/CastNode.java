package nodes;

import exception.SemanticException;
import semantic.Scope;
import semantic.TypeConvertibility;

import java.util.List;

public class CastNode extends BasicNode implements ExprNode {
    Type toType;
    ExprNode expr;

    public CastNode(Type toType, ExprNode expr) {
        this.toType = toType;
        this.expr = expr;
    }

    public CastNode(Type toType, ExprNode expr, Scope scope) {
        this.toType = toType;
        this.expr = expr;
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "cast to " + toType;
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
    public void semanticCheck() {
        expr.semanticCheck();
        if (!TypeConvertibility.canConvert(expr.getType(), toType)) {
            throw new SemanticException("Type convertibility failed: from " + expr.getType() + " to " + toType);
        }
    }

    @Override
    public StringBuilder generateCode() {
        return new StringBuilder().append(TypeConvertibility.generateCode(expr.getType(), toType));
    }

    @Override
    public void initialize(Scope scope) {
        this.scope = scope;
        this.expr.initialize(scope);
    }
}
