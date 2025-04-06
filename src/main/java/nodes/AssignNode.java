package nodes;

import exception.SemanticException;
import semantic.Scope;
import semantic.TypeConvertibility;

import java.util.ArrayList;
import java.util.List;

public class AssignNode extends BasicNode {
    VariableNameNode node;
    ExprNode expr;

    public AssignNode(VariableNameNode varName, ExprNode expr) {
        node = varName;
        this.expr = expr;
    }

    @Override
    public List<? extends Node> children() {
        List<Node> children = new ArrayList<>();
        children.add(node);
        children.add(expr);
        return children;
    }

    @Override
    public void semanticCheck() {
        expr.semanticCheck();
        node.semanticCheck();
        if (expr.getType().equals(node.getType())) return;

        if (TypeConvertibility.canConvert(expr.getType(), node.getType())) {
            expr = new CastNode(node.getType(), expr, scope);
        } else {
            throw new SemanticException("Type mismatch: " + expr.getType() + " can't be converted " + node.getType());
        }
    }

    @Override
    public void initialize(Scope scope) {
        this.scope = scope;
        expr.initialize(scope);
    }

    @Override
    public String toString() {
        return ":=";
    }
}