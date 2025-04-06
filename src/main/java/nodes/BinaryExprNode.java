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
    Type type = Type.UNDEFINED;

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
    public void semanticCheck() {
        expr1.semanticCheck();
        expr2.semanticCheck();

        for (var types : operator.supportableTypes()) {
            if (expr1.getType().equals(types.typeLeft) && expr2.getType().equals(types.typeRight)) {
                type = operator.getReturnType(expr1.getType(), expr2.getType());
                return;
            }
        }

        for (var types : operator.supportableTypes()) {
            if (expr1.getType().equals(types.typeLeft) || (TypeConvertibility.canConvert(expr1.getType(), types.typeLeft))
                    && (expr2.getType().equals(types.typeRight) || TypeConvertibility.canConvert(expr2.getType(), types.typeRight))) {
                if (!expr1.getType().equals(types.typeLeft)) {
                    expr1 = new CastNode(types.typeLeft, expr1, scope);
                }
                if (!expr2.getType().equals(types.typeLeft)) {
                    expr2 = new CastNode(types.typeLeft, expr2, scope);
                }

                type = operator.getReturnType(expr1.getType(), expr2.getType());
                return;
            }
        }

        throw new SemanticException("Operation " + operator.name() + " is not supported with " + expr1.getType() + " and " + expr2.getType());
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
        return type;
    }
}
