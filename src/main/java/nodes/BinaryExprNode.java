package nodes;

import exception.SemanticException;
import semantic.Scope;
import semantic.TypeConvertibility;

import java.util.ArrayList;
import java.util.List;

public class BinaryExprNode extends BasicNode implements ExprNode {
    ExprNode expr1;
    ExprNode expr2;
    BinaryOperator operator;

    public BinaryExprNode(BinaryOperator operator, ExprNode expr1, ExprNode expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.operator = operator;
    }

    @Override
    public List<? extends Node> children() {
        List<Node> children = new ArrayList<>();
        children.add(expr1);
        children.add(expr2);
        return children;
    }

    @Override
    public void initialize(Scope scope) {
        this.scope = scope;
        expr1.initialize(scope);
        expr2.initialize(scope);
    }

    @Override
    public String toString() {
        return operator.toString();
    }

    @Override
    public Type getType() {
        if (expr1.getType() != expr2.getType()) {
            if (TypeConvertibility.canConvert(expr1.getType(), expr2.getType())) {
                expr1 = new CastNode(expr2.getType(), expr1);
            } else if (TypeConvertibility.canConvert(expr2.getType(), expr1.getType())) {
                expr2 = new CastNode(expr1.getType(), expr2);
            } else {
                throw new SemanticException("Binary operator with types " + expr1.getType() + " and " + expr2.getType() + " is not supported");
            }
        }
        return operator.getReturnType(expr1.getType(), expr2.getType());
    }
}
